package com.likui.hty.product.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategroy {

    private Integer categroyId;

    private String categroyName;

    private Integer categroyType;

    private Date createTime;

    private Date updateTime;

}