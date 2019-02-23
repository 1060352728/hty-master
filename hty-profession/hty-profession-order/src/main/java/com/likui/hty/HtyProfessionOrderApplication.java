package com.likui.hty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableHystrixDashboard
@MapperScan("com.likui.hty.order.mapper")
public class HtyProfessionOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HtyProfessionOrderApplication.class, args);
    }

}

