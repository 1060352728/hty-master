package com.likui.hty.member.controller;

import com.likui.hty.member.bean.MenuInfo;
import com.likui.hty.member.bean.RoleInfo;
import com.likui.hty.member.bean.UserInfo;
import com.likui.hty.member.service.MenuInfoService;
import com.likui.hty.member.service.RoleService;
import com.likui.hty.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author likui
 * @Classname: UserController
 * @Description: 用户控制器
 * @create 2018-11-16 17:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping("/findbyusername/{username}")
    public UserInfo findByUserName(@PathVariable("username") String username){
        UserInfo userInfo = userService.findByUsername(username);
        return userInfo;
    }

    @RequestMapping("/findrole/{roleId}")
    public RoleInfo findRoleByRoleId(@PathVariable("roleId") Integer roleId){
        RoleInfo roleInfo = roleService.findById(roleId);
        return roleInfo;
    }

    @RequestMapping("/findmenus/{roleId}")
    public List<MenuInfo> findMenus(@PathVariable("roleId") Integer roleId){
        List<MenuInfo> menuInfoList = menuInfoService.findMenus(roleId);
        return menuInfoList;
    }

    @PostMapping("/findbyusername")
    public UserInfo findByUser(@RequestParam("username") String username){
        UserInfo userInfo = userService.findByUsername(username);
        return userInfo;
    }

}
