package com.zhxx.admin.server.controller;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zhxx.admin.server.annotation.LogAnnotation;
import com.zhxx.admin.server.dao.UserDao;
import com.zhxx.admin.server.dto.UserDto;
import com.zhxx.admin.server.model.User;
import com.zhxx.admin.server.page.table.PageTableHandler;
import com.zhxx.admin.server.page.table.PageTableRequest;
import com.zhxx.admin.server.page.table.PageTableResponse;
import com.zhxx.admin.server.page.table.PageTableHandler.CountHandler;
import com.zhxx.admin.server.page.table.PageTableHandler.ListHandler;
import com.zhxx.admin.server.service.UserService;
import com.zhxx.admin.server.utils.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关接口
 */
@Api(tags = "用户")

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存用户")
    @RequiresPermissions("sys:user:add")
    public User saveUser(@RequestBody UserDto userDto) {
        User u = userService.getUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }

        return userService.saveUser(userDto);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改用户")
    @RequiresPermissions("sys:user:add")
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @LogAnnotation
    @PutMapping(params = "headImgUrl")
    @ApiOperation(value = "修改头像")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getCurrentUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);

        userService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
    }

    @LogAnnotation
    @PutMapping("/{username}")
    @ApiOperation(value = "修改密码")
    @RequiresPermissions("sys:user:password")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    /**
     * 删除用户
     * @param id
     * @author 王传营
     * @date 2019.12.06
     */
    @LogAnnotation
    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    @RequiresPermissions("sys:user:del")
    public void delete(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    @RequiresPermissions("sys:user:query")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return userDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<User> list(PageTableRequest request) {
                List<User> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public User currentUser() {
        return UserUtil.getCurrentUser();
    }

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:user:query")
    public User user(@PathVariable Long id) {
        return userDao.getById(id);
    }

//    @LogAnnotation
//    @PutMapping("/{id}")
//    @ApiOperation(value = "修改用户状态")
//    @RequiresPermissions("sys:user:add")
//    public User updateUserStatus(@RequestBody Lang id) {
//        return userService.updateUserStatus(id);
//    }

}
