package com.xjzhang.pro.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.model.LoginUserDto;
import com.xjzhang.pro.convert.CategoryConvert;
import com.xjzhang.pro.dao.CategoryDao;
import com.xjzhang.pro.exception.ProBizException;
import com.xjzhang.pro.model.dto.CategoryDto;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.vo.Catelog2Vo;
import com.xjzhang.pro.model.vo.CategoryVo;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import com.xjzhang.pro.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Slf4j
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CategoryBrandRelationService relationService;

    @Override
    public CategoryVo getCategoryById(Long id) {
        Category category = this.getById(id);
        if (category == null) {
            log.error("找不到分类信息id={}", id);
            throw new ProBizException(ErrorCodeEnum.PRO10021001, id);
        }

        Category parentCategory = this.getById(category.getParentCid());
        CategoryVo categoryVo = CategoryConvert.entity2Vo(category);
        if (parentCategory != null) {
            categoryVo.setParentName(parentCategory.getName());
        }

        return categoryVo;
    }

    @Override
    public boolean saveCategory(CategoryDto categoryDto, LoginUserDto loginUserDto) {
        Category parentCategory = this.getById(categoryDto.getParentCid());

        if (parentCategory == null) {
            throw new ProBizException(ErrorCodeEnum.PRO10021002, categoryDto.getParentCid());
        }

        Category category = CategoryConvert.dto2Entity(categoryDto);
        this.save(category);

        redisTemplate.delete(RedisConstant.CATEGORY_LIST_KEY);
//        category.setUpdateInfo(loginUserDto);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = "category", allEntries = true)
    @Override
    public boolean updateCategoryById(Category category) {
        this.updateById(category);
        if (StringUtils.isNotBlank(category.getName())) {
            relationService.updateCategory(category);
        }

        redisTemplate.delete(RedisConstant.CATEGORY_LIST_KEY);

        return true;
    }

    @Override
    public Long[] getCategoryPath(Long id) {
        List<Long> paths = new ArrayList<>();
        findParentPath(id, paths);
        Collections.reverse(paths);
        return paths.toArray(new Long[paths.size()]);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1、收集当前节点id
        paths.add(catelogId);
        Category byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }


    @Override
    public List<CategoryVo> getLevel1Catagories() {
        List<Category> list = this.list(new LambdaQueryWrapper<Category>().eq(Category::getParentCid, 0));
        return CategoryConvert.entity2VoList(list);
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCategoryMap() {
        String categoryJson = (String) redisTemplate.opsForValue().get(RedisConstant.CATEGORY_LIST_KEY);
        Map<String, List<Catelog2Vo>> listMap = null;
        if (TextUtils.isEmpty(categoryJson)) {
            listMap = getCategoriesDb();
            categoryJson = JSON.toJSON(listMap).toString();
            redisTemplate.opsForValue().set(RedisConstant.CATEGORY_LIST_KEY, categoryJson);
        }
        listMap = JSON.parseObject(categoryJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });

        return listMap;
    }

    private Map<String, List<Catelog2Vo>> getCategoriesDb() {
        // 查询出所有分类
        List<Category> list = this.list();
        if (list != null) {
            // 找出所有一级分类
            List<Category> category1List = getCategory(list, 0L);
            if (category1List != null) {
                // 二级分类列表
                Map<String, List<Catelog2Vo>> listMap = category1List.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
                    List<Category> category2List = getCategory(list, v.getCatId());
                    List<Catelog2Vo> catelog2VoList1 = null;
                    if (category2List != null) {
                        // 获取三级分类列表
                        catelog2VoList1 = category2List.stream().map(category2 -> {
                            List<Category> category3List = getCategory(list, category2.getCatId());
                            Catelog2Vo catelog2Vo = new Catelog2Vo();
                            catelog2Vo.setCatalog1ParentCid(category2.getParentCid());
                            catelog2Vo.setId(category2.getCatId());
                            catelog2Vo.setName(category2.getName());
                            catelog2Vo.setCatalog3VoList(CategoryConvert.entity2Catelog3VoList(category3List));

                            return catelog2Vo;
                        }).collect(Collectors.toList());
                    }
                    return catelog2VoList1;
                }));

                return listMap;
            }
        }
        return null;
    }

    /**
     * 根据父分类id，获取所有的子分类
     *
     * @param list
     * @param parentCid
     * @return
     */
    public List<Category> getCategory(List<Category> list, long parentCid) {
        return list.stream().filter(category -> {
            return category.getParentCid() == parentCid;
        }).collect(Collectors.toList());
    }

}
