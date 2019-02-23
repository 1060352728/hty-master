package com.likui.hty.product.service.impl;

import com.likui.hty.product.bean.ProductInfo;
import com.likui.hty.product.bean.ProductInfoOutput;
import com.likui.hty.product.common.DecreaseStockInput;
import com.likui.hty.product.enums.ResultEnum;
import com.likui.hty.product.exception.ProductException;
import com.likui.hty.product.mapper.ProductInfoMapper;
import com.likui.hty.product.service.ProductInfoService;
import com.likui.hty.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 根据产品的状态查询在架的产品
     * @param productStatus
     * @return
     */
    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return productInfoMapper.findByProductStatus(productStatus);
    }

    /**
     * 根据订单中的产品标号查询产品
     * @param productIdList
     * @return
     */
    @Override
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIdList) {
        List<ProductInfoOutput> list = productInfoMapper.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 减库存操作
     * @param decreaseStockInputList
     */
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        //修改库存
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
        log.info("向订单服务已经发送下单后的产品信息{}"+JsonUtil.toJson(productInfoOutputList));
    }

    /**
     * 根据订单编号修改库存
     * @param decreaseStockInputList
     * @return
     */
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
            ProductInfo productInfo = productInfoMapper.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (null==productInfo){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //根据订单编号修改库存
            productInfo.setProductStock(result);
            productInfoMapper.updateProductStock(productInfo.getProductId(),result);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
