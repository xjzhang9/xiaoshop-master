package com.xjzhang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.model.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:38
 */
@Slf4j
@RestController
public class GeneratorController {

    Wapper<Page<TableVO>> queryTableList() {
        return null;
    }
}
