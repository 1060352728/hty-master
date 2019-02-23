package com.likui.hty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.likui.hty.member.mapper")
public class HtyProfessionMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HtyProfessionMemberApplication.class, args);
    }

}

