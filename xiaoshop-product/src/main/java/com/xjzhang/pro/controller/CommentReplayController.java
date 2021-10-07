package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.CommentReplayConvert;
import com.xjzhang.pro.model.entity.CommentReplay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.CommentReplayService;
import com.xjzhang.pro.model.dto.CommentReplayDto;
import com.xjzhang.pro.model.vo.CommentReplayVo;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/commentreplay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "商品评价回复关系管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentReplayController extends BaseController {
    @Autowired
    private CommentReplayService commentReplayService;

    /**
     * 查询商品评价回复关系
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 CommentReplay 信息")
    @RequestMapping("/queryCommentReplayWithPage")
    public BaseWrapper<IPage<CommentReplayVo>> queryCommentReplayWithPage(@RequestBody CommentReplayDto  commentReplayDto) {
        Page<CommentReplay> queryDtoPage = new Page(commentReplayDto.getPageIndex(), commentReplayDto.getPageSize());
        IPage<CommentReplay> tablePage = commentReplayService.page(queryDtoPage);
        IPage<CommentReplayVo> voIPage = CommentReplayConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得商品评价回复关系
     */
    @ApiOperation(httpMethod = "POST", value = "获取 CommentReplay 信息")
    @RequestMapping("/getCommentReplayById")
    public BaseWrapper getCommentReplayById(@PathVariable Long id) {
        CommentReplay commentReplay = commentReplayService.getById(id);
        CommentReplayVo commentReplayVo=  CommentReplayConvert.entity2Vo(commentReplay);

        return ResWrapper.ok(commentReplayVo);
    }

    /**
     * 保存商品评价回复关系
     */
    @PostMapping("/saveCommentReplay")
    @ApiOperation(httpMethod = "POST", value = "保存 CommentReplay 信息")
    public BaseWrapper saveCommentReplay (@RequestBody CommentReplayDto  commentReplayDto) {
        CommentReplay commentReplay = CommentReplayConvert.dto2Entity(commentReplayDto);
        boolean result = commentReplayService.save(commentReplay);

        return super.handleResult(result);
    }

    /**
     * 更新商品评价回复关系
     */
    @PostMapping("/updateCommentReplayById")
    @ApiOperation(httpMethod = "POST", value = "更新CommentReplay 信息")
    public BaseWrapper updateCommentReplayById (@RequestBody CommentReplayDto  commentReplayDto) {
        CommentReplay commentReplay = CommentReplayConvert.dto2Entity(commentReplayDto);
        boolean result =  commentReplayService.updateById(commentReplay);

        return super.handleResult(result);
    }

    /**
     * 根据id删除商品评价回复关系
     */
    @PostMapping("/deleteCommentReplayById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除CommentReplay 信息")
    public BaseWrapper deleteCommentReplayById(@PathVariable Long id) {
        boolean result = commentReplayService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除商品评价回复关系
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除CommentReplay 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] CommentIds){
        boolean result = commentReplayService.removeByIds(Arrays.asList(CommentIds));

        return super.handleResult(result);
    }
}
