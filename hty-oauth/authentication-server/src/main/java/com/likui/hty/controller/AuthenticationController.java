package com.likui.hty.controller;

import com.likui.hty.result.Result;
import com.likui.hty.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/permission")
    public Result decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Result.success(decide);
    }

}