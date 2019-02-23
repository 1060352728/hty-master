package com.likui.hty.member.service.impl;

import com.likui.hty.member.bean.RoleInfo;
import com.likui.hty.member.mapper.RoleInfoMapper;
import com.likui.hty.member.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public RoleInfo findById(Integer roleId) {
        return roleInfoMapper.findById(roleId);
    }
}
