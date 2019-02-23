package com.likui.hty.member.mapper;


import com.likui.hty.member.bean.MenuInfo;

import java.util.List;

public interface MenuInfoMapper {

    /**
     * 根据菜单集合查询菜单
     * @param menuIds
     * @return
     */
    public List<MenuInfo> findByMenuIdIn(List<Integer> menuIds);
}
