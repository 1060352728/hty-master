package com.likui.hty.product.mapper;

import com.likui.hty.product.bean.ProductCategroy;

import java.util.List;

public interface ProductCategroyMapper {

    public List<ProductCategroy> findByCategroyTypeIn(List<Integer> categroyTypes);
}