package com.xjzhang.generator.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.common.BaseController;
import com.xjzhang.common.wrapper.Wrapper;
import com.xjzhang.generator.model.TableVO;
import io.swagger.annotations.Api;
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

    Wrapper<Page<TableVO>> queryTableList() {


        return null;
    }
}
