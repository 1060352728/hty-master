package com.likui.hty.product.service;

import com.likui.hty.product.bean.ProductCategroy;

import java.util.List;

/**
 * 产品类目
 */
public interface ProductCategroyService {

    public List<ProductCategroy> findByCategroyTypeIn(List<Integer> categroyTypes);
}
