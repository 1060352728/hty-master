package com.likui.hty.member.mapper;


import com.likui.hty.member.bean.RoleInfo;

public interface RoleInfoMapper {

    /**
     * 根据roleid查询role
     * @param roleId
     * @return
     */
    public RoleInfo findById(Integer roleId);

}
