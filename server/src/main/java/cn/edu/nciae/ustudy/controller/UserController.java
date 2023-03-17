package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.dto.UserDTO;
import cn.edu.nciae.ustudy.po.User;
import cn.edu.nciae.ustudy.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Api
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @ApiOperation("所有用户信息")
    private List<User> getUsers() {
        return userService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据用户id获取用户信息")
    private User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    @ApiOperation("用户注册")
    private long addUser(UserDTO userDTO) {
        return userService.signup(userDTO);
    }

}

