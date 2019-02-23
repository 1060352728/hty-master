package com.likui.hty.member.mapper;

import com.likui.hty.member.bean.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {

    /**
     * 根据roleid查询角色菜单关联信息
     * @param roleId
     * @return
     */
    public List<RoleMenu> findByRoleId(Integer roleId);
}
