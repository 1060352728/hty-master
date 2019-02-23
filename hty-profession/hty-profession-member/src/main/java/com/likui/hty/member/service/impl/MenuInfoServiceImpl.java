package com.likui.hty.member.service.impl;

import com.likui.hty.member.bean.MenuInfo;
import com.likui.hty.member.bean.RoleMenu;
import com.likui.hty.member.mapper.MenuInfoMapper;
import com.likui.hty.member.mapper.RoleMenuMapper;
import com.likui.hty.member.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {

    @Autowired
    private MenuInfoMapper menuInfoMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuInfo> findMenus(Integer roleId) {
        List<RoleMenu> roleMenuList = roleMenuMapper.findByRoleId(roleId);
        List<Integer> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        //4：根据关联表查询菜单
        List<MenuInfo> menuInfoList = menuInfoMapper.findByMenuIdIn(menuIds);
        return menuInfoList;
    }
}
