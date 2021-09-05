package com.xjzhang.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.common.BaseController;
import com.xjzhang.common.dto.QueryDto;
import com.xjzhang.common.wrapper.BaseWrapper;
import com.xjzhang.common.wrapper.ResWrapper;
import com.xjzhang.generator.model.TableInfo;
import com.xjzhang.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:38
 */
@Slf4j
@RestController
@Api("代码生成")
@RequestMapping(value = "/api/generator/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GeneratorController extends BaseController {
    @Autowired
    private GeneratorService generatorService;

    /**
     * 分页查询表列表
     *
     * @return
     */
    @PostMapping("queryTableListWithPage")
    public BaseWrapper<IPage<TableInfo>> queryTableListWithPage(@RequestBody QueryDto queryDto) {
        Page<QueryDto> queryDtoPage = new Page(queryDto.getPageIndex(), queryDto.getPageSize());
        IPage<TableInfo> tablePage = generatorService.queryTableListWithPage(queryDtoPage);

        return ResWrapper.ok(tablePage);
    }

    @RequestMapping("code")
    public BaseWrapper code(@RequestBody List<String> tableNameList, HttpServletResponse response) {
       return null;
    }
}
