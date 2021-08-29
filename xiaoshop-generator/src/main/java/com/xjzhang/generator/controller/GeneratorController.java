package com.xjzhang.generator.controller;

import com.xjzhang.common.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:38
 */
@Slf4j
@RestController
@Api("测试")
public class GeneratorController extends BaseController {
    @ApiOperation("接口")
    void queryTableList() {

    }
}
