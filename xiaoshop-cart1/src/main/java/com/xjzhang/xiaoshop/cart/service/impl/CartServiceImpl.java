package com.xjzhang.xiaoshop.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.base.exception.BusinessException;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import com.xjzhang.xiaoshop.cart.fegin.api.ProductFeginApi;
import com.xjzhang.xiaoshop.cart.model.vo.CartItemVO;
import com.xjzhang.xiaoshop.cart.model.vo.CartVO;
import com.xjzhang.xiaoshop.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 16:35
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {
    private boolean isLogin = false;

    private String token = "xiao:123";

    private String useId = "xiao:456";

    @Resource
    private ProductFeginApi productFeginApi;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private BoundHashOperations<String, Object, Object> getCartRedisOps(String key) {
        return redisTemplate.boundHashOps(RedisConstant.PREFIX_CART_TOKEN + key);
    }

    private String getUserFlag() {
        if (isLogin) {
            return token;
        } else {
            return useId;
        }
    }

    @Override
    public CartItemVO addCartItem(Long skuId, Integer num) {
        String key = getUserFlag();
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        Object object = hashOperations.get(skuId.toString());
        if (object != null) {
            CartItemVO cartItemVO = (CartItemVO) object;
            cartItemVO.setCount(cartItemVO.getCount() + num);
            hashOperations.put(skuId.toString(), cartItemVO);

            return cartItemVO;
        } else {
            CartItemVO cartItemVO = new CartItemVO();
            cartItemVO.setSkuId(skuId);
            cartItemVO.setCount(num);
            cartItemVO.setCheck(true);

            CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    BaseWrapper<SkuInfoVo> baseWrapper = productFeginApi.getSkuInfoById(skuId);
                    //TODO: spi调用异常如何处理
                    if (baseWrapper != null) {
                        SkuInfoVo skuInfoVo = baseWrapper.getData();
                        cartItemVO.setTittle(skuInfoVo.getSkuTitle());
                        cartItemVO.setImage(skuInfoVo.getSkuDefaultImg());
                        cartItemVO.setPrice(skuInfoVo.getPrice());

                        //TODO:// 时间数据库怎样设置
                    }
                }
            }, threadPoolExecutor);


            CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    BaseWrapper<List<String>> baseWrapperlist = productFeginApi.getSkuSaleAttrValuesAsString(skuId);
                    if (baseWrapperlist != null) {
                        List<String> list = baseWrapperlist.getData();
                        cartItemVO.setSkuAttrValues(list);
                    }
                }
            }, threadPoolExecutor);

            try {
                CompletableFuture.allOf(completableFuture1, completableFuture2).get();
            } catch (InterruptedException e) {
                log.error("加入购物车异步执行出错: e =" + e.getMessage());
                throw new BusinessException("加入购物车异步执行出错：", e);
            } catch (ExecutionException e) {
                log.error("加入购物车异步执行出错: e =" + e.getMessage());
                throw new BusinessException("加入购物车异步执行出错：", e);
            }

            hashOperations.put(skuId.toString(), cartItemVO);

            return cartItemVO;
        }
    }

    @Override
    public CartItemVO getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(getUserFlag());
        Object object = hashOperations.get(skuId.toString());
        if (object != null) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
            CartItemVO cartItemVO = jsonObject.toJavaObject(CartItemVO.class);
            return cartItemVO;
        }
        return null;
    }

    @Override
    public CartVO getCart() {
        CartVO cartVO = new CartVO();
        Set<CartItemVO> cartItemVOS = null;
        Set<CartItemVO> tmpCarts = getCart(useId);
        if (!isLogin) {
            cartItemVOS = tmpCarts;
            cartVO.setItems(cartItemVOS);
        } else {
            cartItemVOS = getCart(token);
            if (tmpCarts != null && tmpCarts.size() > 0) {
                for (CartItemVO tmpCart : tmpCarts) {
                    CartItemVO cartItemVO = addCartItem(tmpCart.getSkuId(), tmpCart.getCount());
                    cartItemVOS.add(cartItemVO);
                }
            }
        }

        cartVO.setItems(cartItemVOS);

        return cartVO;
    }

    private Set<CartItemVO> getCart(String key) {
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        List<Object> values = hashOperations.values();
        Set<CartItemVO> cartItemVOList = null;
        if (values != null && values.size() > 0) {
            cartItemVOList = values.stream().map(value -> {
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(value));
                CartItemVO cartItemVO = jsonObject.toJavaObject(CartItemVO.class);
                return cartItemVO;
            }).collect(Collectors.toSet());
        }

        return cartItemVOList;
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        String key = getUserFlag();
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        Object object = hashOperations.get(skuId.toString());
        if (object != null) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
            CartItemVO cartItemVO = jsonObject.toJavaObject(CartItemVO.class);
            cartItemVO.setCheck(isChecked == 1);
            hashOperations.put(skuId.toString(), cartItemVO);

            return;
        }

//        throw new CartBizException(ErrorCodeEnum.CART1004001);
    }

    @Override
    public void changeItemCount(Long skuId, Integer num) {
        String key = getUserFlag();
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        Object object = hashOperations.get(skuId.toString());
        if (object != null) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
            CartItemVO cartItemVO = jsonObject.toJavaObject(CartItemVO.class);
            cartItemVO.setCount(num);
            hashOperations.put(skuId.toString(), cartItemVO);
            return;
        }

//        throw new CartBiz(ErrorCodeEnum.CART1004001);
    }

    @Override
    public void deleteItem(List<Long> skuIds) {
        if (skuIds == null || skuIds.size() < 0 ) {
            return;
        }

        String key = getUserFlag();
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        for (int i = 0; i< skuIds.size(); i++) {
            hashOperations.delete(skuIds.get(i).toString());
        }
    }

    @Override
    public List<CartItemVO> getCheckedItems() {
        String key = getUserFlag();
        return getCart(key).stream().filter(CartItemVO::getCheck).collect(Collectors.toList());
    }
}
