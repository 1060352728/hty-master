package com.likui.hty.member.service;

import com.likui.hty.member.bean.MenuInfo;

import java.util.List;

public interface MenuInfoService {

    public List<MenuInfo> findMenus(Integer roleId);

}
