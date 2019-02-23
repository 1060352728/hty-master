package com.likui.hty.client;

import com.likui.hty.member.bean.MenuInfo;
import com.likui.hty.member.bean.RoleInfo;
import com.likui.hty.member.bean.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(name = "hty-profession-member")
public interface UserFeignApi {

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    @PostMapping("/user/findbyusername/{username}")
    public UserInfo findByUsername(@PathVariable("username") String username);

    @PostMapping("/user/findrole/{roleId}")
    public RoleInfo findRoleByRoleId(@PathVariable("roleId") Integer roleId);

    @PostMapping("/user/findmenus/{roleId}")
    public List<MenuInfo> findMenus(@PathVariable("roleId") Integer roleId);


}
