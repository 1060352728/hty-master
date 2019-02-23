package com.likui.hty.service.impl;


import com.likui.hty.client.UserFeignApi;
import com.likui.hty.member.bean.MenuInfo;
import com.likui.hty.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserFeignApi userFeignApi;

    /**
     * @param authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    @Override
    public boolean decide(HttpServletRequest authRequest) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取此访问用户所有角色拥有的权限资源
        List<MenuInfo> userResources = findResourcesByAuthorityRoles(authentication.getAuthorities());
        //用户拥有权限资源 与 url要求的资源进行对比
        return isMatch(authRequest.getServletPath(), userResources);
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     *
     * @param url
     * @param menuInfos
     * @return
     */
    public boolean isMatch(String url, List<MenuInfo> menuInfos) {
        return menuInfos.stream().anyMatch(menuInfo -> menuInfo.getMenuUrl().equals(url));
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     *
     * @param authorityRoles
     * @return
     */
    private List<MenuInfo> findResourcesByAuthorityRoles(Collection<? extends GrantedAuthority> authorityRoles) {
        //用户被授予的角色
        log.debug("用户的授权角色集合信息为:{}", authorityRoles);
        String[] authorityRoleCodes = authorityRoles.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .toArray(new String[authorityRoles.size()]);
        List<MenuInfo> menus = userFeignApi.findMenus(Integer.parseInt(authorityRoleCodes[0]));
        if (log.isDebugEnabled()) {
            log.debug("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", menus.size(), menus);
        }
        return menus;
    }
}
