package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SpuCommentConvert;
import com.xjzhang.pro.model.entity.SpuComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.SpuCommentService;
import com.xjzhang.pro.model.dto.SpuCommentDto;
import com.xjzhang.pro.model.vo.SpuCommentVo;

/**
 * 商品评价
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/spucomment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "商品评价管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpuCommentController extends BaseController {
    @Autowired
    private SpuCommentService spuCommentService;

    /**
     * 查询商品评价
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SpuComment 信息")
    @RequestMapping("/querySpuCommentWithPage")
    public BaseWrapper<IPage<SpuCommentVo>> querySpuCommentWithPage(@RequestBody SpuCommentDto  spuCommentDto) {
        Page<SpuComment> queryDtoPage = new Page(spuCommentDto.getPageIndex(), spuCommentDto.getPageSize());
        IPage<SpuComment> tablePage = spuCommentService.page(queryDtoPage);
        IPage<SpuCommentVo> voIPage = SpuCommentConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得商品评价
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SpuComment 信息")
    @RequestMapping("/getSpuCommentById")
    public BaseWrapper getSpuCommentById(@PathVariable Long id) {
        SpuComment spuComment = spuCommentService.getById(id);
        SpuCommentVo spuCommentVo=  SpuCommentConvert.entity2Vo(spuComment);

        return ResWrapper.ok(spuCommentVo);
    }

    /**
     * 保存商品评价
     */
    @PostMapping("/saveSpuComment")
    @ApiOperation(httpMethod = "POST", value = "保存 SpuComment 信息")
    public BaseWrapper saveSpuComment (@RequestBody SpuCommentDto  spuCommentDto) {
        SpuComment spuComment = SpuCommentConvert.dto2Entity(spuCommentDto);
        boolean result = spuCommentService.save(spuComment);

        return super.handleResult(result);
    }

    /**
     * 更新商品评价
     */
    @PostMapping("/updateSpuCommentById")
    @ApiOperation(httpMethod = "POST", value = "更新SpuComment 信息")
    public BaseWrapper updateSpuCommentById (@RequestBody SpuCommentDto  spuCommentDto) {
        SpuComment spuComment = SpuCommentConvert.dto2Entity(spuCommentDto);
        boolean result =  spuCommentService.updateById(spuComment);

        return super.handleResult(result);
    }

    /**
     * 根据id删除商品评价
     */
    @PostMapping("/deleteSpuCommentById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SpuComment 信息")
    public BaseWrapper deleteSpuCommentById(@PathVariable Long id) {
        boolean result = spuCommentService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除商品评价
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SpuComment 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Integer[] CommentTypes){
        boolean result = spuCommentService.removeByIds(Arrays.asList(CommentTypes));

        return super.handleResult(result);
    }
}
