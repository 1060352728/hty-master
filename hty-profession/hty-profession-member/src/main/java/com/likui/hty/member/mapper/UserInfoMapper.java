package com.likui.hty.member.mapper;

import com.likui.hty.member.bean.UserInfo;

public interface UserInfoMapper {

    /**
     * 根据openid查询UserInfo对象
     * @param openId
     * @return
     */
    public UserInfo findByOpenid(String openId);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    public UserInfo findByUsername(String username);
}
