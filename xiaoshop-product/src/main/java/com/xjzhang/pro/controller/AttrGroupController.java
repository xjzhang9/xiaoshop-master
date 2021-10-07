package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.AttrGroupConvert;
import com.xjzhang.pro.model.entity.AttrGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
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
@RestController
@RequestMapping(value = "pro/attrgroup" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "属性分组管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttrGroupController extends BaseController {
    @Autowired
    private AttrGroupService attrGroupService;

    /**
     * 查询属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 AttrGroup 信息")
    @RequestMapping("/queryAttrGroupWithPage")
    public BaseWrapper<IPage<AttrGroupVo>> queryAttrGroupWithPage(@RequestBody AttrGroupDto  attrGroupDto) {
        Page<AttrGroup> queryDtoPage = new Page(attrGroupDto.getPageIndex(), attrGroupDto.getPageSize());
        IPage<AttrGroup> tablePage = attrGroupService.page(queryDtoPage);
        IPage<AttrGroupVo> voIPage = AttrGroupConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "获取 AttrGroup 信息")
    @RequestMapping("/getAttrGroupById")
    public BaseWrapper getAttrGroupById(@PathVariable Long id) {
        AttrGroup attrGroup = attrGroupService.getById(id);
        AttrGroupVo attrGroupVo=  AttrGroupConvert.entity2Vo(attrGroup);

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
        boolean result = attrGroupService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除属性分组
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除AttrGroup 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] AttrGroupIds){
        boolean result = attrGroupService.removeByIds(Arrays.asList(AttrGroupIds));

        return super.handleResult(result);
    }
}
