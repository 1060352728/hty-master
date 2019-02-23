package com.likui.hty.hystrix;

import com.likui.hty.client.ProductFeignApi;
import com.likui.hty.product.bean.ProductInfoOutput;
import com.likui.hty.product.common.DecreaseStockInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author likui
 * @Classname: ProductClientHystrix
 * @Description: 商品容错回调类
 * @create 2018-10-09 14:16
 **/
@Component
@Slf4j
public class ProductFeignHystrix implements ProductFeignApi {

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        log.info("由于Product出现异常，开启了服务降级，暂时先返回null");
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
