package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.AttrAttrgroupRelationConvert;
import com.xjzhang.pro.model.dto.AttrAttrgroupRelationDto;
import com.xjzhang.pro.model.entity.AttrAttrgroupRelation;
import com.xjzhang.pro.model.vo.AttrAttrgroupRelationVo;
import com.xjzhang.pro.service.AttrAttrgroupRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

/**
 * 属性&属性分组关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/attrattrgrouprelation" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "属性&属性分组关联管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttrAttrgroupRelationController extends BaseController {
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 AttrAttrgroupRelation 信息")
    @RequestMapping("/queryAttrAttrgroupRelationWithPage")
    public BaseWrapper<IPage<AttrAttrgroupRelationVo>> queryAttrAttrgroupRelationWithPage(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        IPage<AttrAttrgroupRelation> tablePage = attrAttrgroupRelationService.page(attrAttrgroupRelationDto.getPage());
        IPage<AttrAttrgroupRelationVo> voIPage = AttrAttrgroupRelationConvert.entity2VoPage(tablePage);
        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得属性&属性分组关联
     */
    @ApiOperation(httpMethod = "POST", value = "获取 AttrAttrgroupRelation 信息")
    @RequestMapping("/getAttrAttrgroupRelationById")
    public BaseWrapper getAttrAttrgroupRelationById(@PathVariable Long id) {
        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.getById(id);
        AttrAttrgroupRelationVo attrAttrgroupRelationVo=  AttrAttrgroupRelationConvert.entity2Vo(attrAttrgroupRelation);

        return ResWrapper.ok(attrAttrgroupRelationVo);
    }

    /**
     * 保存属性&属性分组关联
     */
    @PostMapping("/saveAttrAttrgroupRelation")
    @ApiOperation(httpMethod = "POST", value = "保存 AttrAttrgroupRelation 信息")
    public BaseWrapper saveAttrAttrgroupRelation (@RequestBody AttrAttrgroupRelationDto  attrAttrgroupRelationDto) {
        AttrAttrgroupRelation attrAttrgroupRelation = AttrAttrgroupRelationConvert.dto2Entity(attrAttrgroupRelationDto);
        boolean result = attrAttrgroupRelationService.save(attrAttrgroupRelation);

        return super.handleResult(result);
    }

    /**
     * 更新属性&属性分组关联
     */
    @PostMapping("/updateAttrAttrgroupRelationById")
    @ApiOperation(httpMethod = "POST", value = "更新AttrAttrgroupRelation 信息")
    public BaseWrapper updateAttrAttrgroupRelationById (@RequestBody AttrAttrgroupRelationDto  attrAttrgroupRelationDto) {
        AttrAttrgroupRelation attrAttrgroupRelation = AttrAttrgroupRelationConvert.dto2Entity(attrAttrgroupRelationDto);
        boolean result =  attrAttrgroupRelationService.updateById(attrAttrgroupRelation);

        return super.handleResult(result);
    }

    /**
     * 根据id删除属性&属性分组关联
     */
    @PostMapping("/deleteAttrAttrgroupRelationById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除AttrAttrgroupRelation 信息")
    public BaseWrapper deleteAttrAttrgroupRelationById(@PathVariable Long id) {
        boolean result = attrAttrgroupRelationService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除属性&属性分组关联
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除AttrAttrgroupRelation 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] AttrGroupIds){
        boolean result = attrAttrgroupRelationService.removeByIds(Arrays.asList(AttrGroupIds));

        return super.handleResult(result);
    }
}
