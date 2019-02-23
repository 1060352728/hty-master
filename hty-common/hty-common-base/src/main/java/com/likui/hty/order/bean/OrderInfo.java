package com.likui.hty.order.bean;

import lombok.Data;

@Data
public class OrderInfo {

    private Integer orderId;

    private String orderName;

    private String orderContent;

    private String orderIsused;

    private Integer memberId;

}