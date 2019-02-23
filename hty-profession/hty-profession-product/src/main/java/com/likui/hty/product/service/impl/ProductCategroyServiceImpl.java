package com.likui.hty.product.service.impl;

import com.likui.hty.product.bean.ProductCategroy;
import com.likui.hty.product.mapper.ProductCategroyMapper;
import com.likui.hty.product.service.ProductCategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategroyServiceImpl implements ProductCategroyService {

    @Autowired
    ProductCategroyMapper productCategroyMapper;

    @Override
    public List<ProductCategroy> findByCategroyTypeIn(List<Integer> categroyTypes) {
        return productCategroyMapper.findByCategroyTypeIn(categroyTypes);
    }
}
