package com.xjzhang.cart.service.impl;

import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.base.exception.BusinessException;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.cart.fegin.api.ProductFeginApi;
import com.xjzhang.cart.model.vo.CartItemVO;
import com.xjzhang.cart.model.vo.CartVO;
import com.xjzhang.cart.service.ICartService;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 16:35
 */
@Service
@Slf4j
public class CartServiceImpl implements ICartService {
    private boolean isLogin = false;

    private String token = "xiao:123";

    private String useId = "xiao:456";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductFeginApi productFeginApi;

//    @Autowired
//    private TaskExecutor taskExecutor;

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
//
//            CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(new Runnable() {
//                @Override
//                public void run() {
//                    BaseWrapper<SkuInfoVo> baseWrapper = productFeginApi.getSkuInfoById(skuId);
//                    //TODO: spi调用异常如何处理
//                    if (baseWrapper != null) {
//                        SkuInfoVo skuInfoVo = baseWrapper.getData();
//                        cartItemVO.setTittle(skuInfoVo.getSkuTitle());
//                        cartItemVO.setImage(skuInfoVo.getSkuDefaultImg());
//                        cartItemVO.setPrice(skuInfoVo.getPrice());
//
//                        //TODO:// 时间数据库怎样设置
//                    }
//                }
//            }, taskExecutor);


//            CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(new Runnable() {
//                @Override
//                public void run() {
//                    BaseWrapper<List<String>> baseWrapperlist = productFeginApi.getSkuSaleAttrValuesAsString(skuId);
//                    if (baseWrapperlist != null) {
//                        List<String> list = baseWrapperlist.getData();
//                        cartItemVO.setSkuAttrValues(list);
//                    }
//                }
//            }, taskExecutor);

//            try {
//                CompletableFuture.allOf(completableFuture1, completableFuture2).get();
//            } catch (InterruptedException e) {
//                log.error("加入购物车异步执行出错: e =" + e.getMessage());
//                throw new BusinessException("加入购物车异步执行出错：", e);
//            } catch (ExecutionException e) {
//                log.error("加入购物车异步执行出错: e =" + e.getMessage());
//                throw new BusinessException("加入购物车异步执行出错：", e);
//            }

            hashOperations.put(skuId.toString(), cartItemVO);

            return cartItemVO;
        }
    }

    @Override
    public CartItemVO getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(getUserFlag());
        Object object = hashOperations.get(skuId.toString());
        if (object != null) {
            CartItemVO cartItemVO = (CartItemVO) object;
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
            cartVO.setCartItemVOList(cartItemVOS);
        } else {
            cartItemVOS = getCart(token);
            if (tmpCarts != null && tmpCarts.size() > 0) {
                for (CartItemVO tmpCart : tmpCarts) {
                    CartItemVO cartItemVO = addCartItem(tmpCart.getSkuId(), tmpCart.getCount());
                    cartItemVOS.add(cartItemVO);
                }
            }
        }

        cartVO.setCartItemVOList(cartItemVOS);

        return cartVO;
    }

    private Set<CartItemVO> getCart(String key) {
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        List<Object> values = hashOperations.values();
        Set<CartItemVO> cartItemVOList = null;
        if (values != null && values.size() > 0) {
            cartItemVOList = values.stream().map(value -> {
                CartItemVO itemVO = (CartItemVO) value;
                return itemVO;
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
            CartItemVO cartItemVO = (CartItemVO) object;
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
            CartItemVO cartItemVO = (CartItemVO) object;
            cartItemVO.setCount(num);
            hashOperations.put(skuId.toString(), cartItemVO);

            return;
        }

//        throw new CartBiz(ErrorCodeEnum.CART1004001);
    }

    @Override
    public void deleteItem(Long skuId) {
        String key = getUserFlag();
        BoundHashOperations<String, Object, Object> hashOperations = getCartRedisOps(key);
        hashOperations.delete(skuId.toString());
    }

    @Override
    public List<CartItemVO> getCheckedItems() {
        String key = getUserFlag();
        return getCart(key).stream().filter(CartItemVO::getCheck).collect(Collectors.toList());
    }
}
