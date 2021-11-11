package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.model.QueryDto;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.AttrGroupConvert;
import com.xjzhang.pro.model.dto.AttrAttrgroupRelationDto;
import com.xjzhang.pro.model.dto.AttrDto;
import com.xjzhang.pro.model.dto.NoRelationAttrDto;
import com.xjzhang.pro.model.entity.AttrAttrgroupRelation;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.vo.AttrVo;
import com.xjzhang.pro.service.AttrService;
import com.xjzhang.pro.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

import com.xjzhang.pro.service.AttrGroupService;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.vo.AttrGroupVo;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Slf4j
@RestController
@RequestMapping(value = "pro/attrgroup" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "属性分组管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttrGroupController extends BaseController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 AttrGroup 信息")
    @RequestMapping("/queryAttrGroupWithPage")
    public BaseWrapper<IPage<AttrGroupVo>> queryAttrGroupWithPage(@RequestBody AttrGroupDto  attrGroupDto) {
        Page<AttrGroup> queryDtoPage = new Page(attrGroupDto.getPageIndex(), attrGroupDto.getPageSize());
        IPage<AttrGroupVo> tablePage = attrGroupService.queryAttrGroupWithPage(queryDtoPage, attrGroupDto);
        return ResWrapper.ok(tablePage);
    }

    /**
     * 查询属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "根据分类查询 AttrGroup 信息")
    @RequestMapping("/list/{catId}")
    public BaseWrapper<List<AttrGroupVo>> queryAttrGroupListByCatId(@PathVariable Long catId) {
        List<AttrGroup> attrGroupList =  attrGroupService.list(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getCatelogId, catId));
        return ResWrapper.ok( AttrGroupConvert.entity2VoList(attrGroupList));
    }



    /**
     * 根据id获得属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "获取 AttrGroup 信息")
    @RequestMapping("/getAttrGroupById/{id}")
    public BaseWrapper getAttrGroupById(@PathVariable Long id) {
        log.info("根据id获得属性分组, attrGroupById={}", id);
        AttrGroup attrGroup = attrGroupService.getById(id);
        AttrGroupVo attrGroupVo=  AttrGroupConvert.entity2Vo(attrGroup);
        Long[] catelogPaths = categoryService.getCategoryPath(attrGroupVo.getCatelogId());
        attrGroupVo.setCatelogPath(catelogPaths);
        return ResWrapper.ok(attrGroupVo);
    }

    /**
     * 保存属性分组
     */
    @PostMapping("/saveAttrGroup")
    @ApiOperation(httpMethod = "POST", value = "保存 AttrGroup 信息")
    public BaseWrapper saveAttrGroup (@RequestBody AttrGroupDto  attrGroupDto) {
        AttrGroup attrGroup = AttrGroupConvert.dto2Entity(attrGroupDto);
        boolean result = attrGroupService.save(attrGroup);

        return super.handleResult(result);
    }

    /**
     * 更新属性分组
     */
    @PostMapping("/updateAttrGroupById")
    @ApiOperation(httpMethod = "POST", value = "更新AttrGroup 信息")
    public BaseWrapper updateAttrGroupById (@RequestBody AttrGroupDto  attrGroupDto) {
        log.info("更新属性分组, attrGroupDto={}", attrGroupDto);
        AttrGroup attrGroup = AttrGroupConvert.dto2Entity(attrGroupDto);
        boolean result =  attrGroupService.updateById(attrGroup);

        return super.handleResult(result);
    }

    /**
     * 根据id删除属性分组
     */
    @PostMapping("/deleteAttrGroupById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除AttrGroup 信息")
    public BaseWrapper deleteAttrGroupById(@PathVariable Long id) {
        log.info("根据id删除属性分组, attrGroupId={}", id);
        boolean result = attrGroupService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除AttrGroup 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] attrGroupIds){
        log.info("批量删除属性分组, attrGroupIds={}", attrGroupIds);
        boolean result = attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return super.handleResult(result);
    }

    @ApiOperation(httpMethod = "POST", value = "查询分组属性")
    @GetMapping("/{attrgroupId}/attr/relation")
    public  BaseWrapper<List<AttrVo>> attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrVo> entities =  attrService.attrRelation(attrgroupId);
        return ResWrapper.ok(entities);
    }

    @ApiOperation(httpMethod = "POST", value = "查询未分组属性")
    @PostMapping("/{attrgroupId}/noattr/relation")
    public BaseWrapper<IPage<AttrVo>> attrNoRelation(@PathVariable Long attrgroupId,
                                        @RequestBody NoRelationAttrDto noRelationAttrDto){
        IPage<AttrVo> page = attrService.getNoRelationAttr(noRelationAttrDto, attrgroupId);
        return ResWrapper.ok(page);
    }


    /**
     * 根据属性分组解除和相关的属性关联
     */
    @PostMapping("/deleteAttrGroupAttrRelation/{attrGroupId}")
    @ApiOperation(httpMethod = "POST", value = "根据属性分组解除和相关的属性关联")
    public BaseWrapper deleteAttrGroupAttrRelation(@PathVariable Long attrGroupId, @RequestBody Long[] attrIds) {
        log.info("根据id删除解除和相关的属性关联, attrGroupId={}", attrGroupId);
        boolean result = attrService.deleteAttrGroupAttrRelation(attrGroupId, attrIds);

        return super.handleResult(result);
    }


    @ApiOperation(httpMethod = "POST", value = "查询分组属性")
    @PostMapping("/addAttrRelation")
    public BaseWrapper addAttrRelation(@RequestBody List<AttrAttrgroupRelationDto> aattrDtos){
        boolean result =  attrService.addAttrRelation(aattrDtos);
        return super.handleResult(result);
    }
}
