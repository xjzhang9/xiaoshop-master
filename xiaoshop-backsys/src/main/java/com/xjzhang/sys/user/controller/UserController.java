package com.xjzhang.sys.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.common.BaseController;
import com.xjzhang.common.wrapper.BaseWrapper;
import com.xjzhang.common.wrapper.ResWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import java.util.Array;
import com.xjzhang.sys.user.service.UserService;
import com.xjzhang.sys.user.model.UserDto;
import com.xjzhang.sys.user.model.UserVo;

/**
 * 系统用户
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 20:45:50
 */

@RestController
@RequestMapping(value = "user/user", , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    @ApiOperation(httpMethod = "POST", value = "分页查询 User 信息")
    @RequestMapping("/queryUserWithPage")
    @RequiresPermissions("user:user:list")
    public BaseWrapper<IPage<UserVo>> queryUserWithPage(@RequestBody UserDto  userDto) {
        Page<UserDto> queryDtoPage = new Page(userDto.getPageIndex(), userDto.getPageSize());
        IPage<User> tablePage = userService.page(queryDtoPage);
        IPage<UserVo> voIPage = UserConvert.entity2VoPage(tablePage);
        return ResWrapper.ok(voIPage);
    }

    @ApiOperation(httpMethod = "POST", value = "获取 User 信息")
    @RequestMapping("/getUserById")
    @RequiresPermissions("user:user:info")
    public BaseWrapper getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        UserVo userVo = new  UserVo();
        UserVo userVo=  UserConvert.entity2Vo(user);
        return ResWrapper.ok(userVo);
    }

    @PostMapping("/saveUser")
    @RequiresPermissions("user:user:save")
    @ApiOperation(httpMethod = "POST", value = "保存 User 信息")
    public BaseWrapper saveUser (@RequestBody UserDto  userDto) {
        User user = new  User();
        User user = UserConvert.dto2Entity(userDto);
        boolean result = userService.save(user);

        result super.handleResult(result);
    }


    @PostMapping("/updateUserById")
    @RequiresPermissions("user:user:update")
    @ApiOperation(httpMethod = "POST", value = "更新User 信息")
    public BaseWrapper updateUserById (@RequestBody UserDto  userDto) {
        User user = new  User();
        User user = UserConvert.dto2Entity(userDto);
        boolean result =  userService.updateById(user);

        result super.handleResult(result);
    }

    @PostMapping("/deleteUserById/{id}")
    @RequiresPermissions("user:user:delete")
    @ApiOperation(httpMethod = "POST", value = "删除User 信息")
    public BaseWrapper deleteUserById(@PathVariable Long id) {
        boolean result = userService.removeById(id);

        return super.handleResult(result);
    }

    @ApiOperation(httpMethod = "POST", value = "批量删除User 信息")
    @RequestMapping("/bathDelete")
    @RequiresPermissions("user:user:bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] UserIds){
        boolean result = userService.removeByIds(Arrays.asList(UserIds));

        return super.handleResult(result);
    }

}
