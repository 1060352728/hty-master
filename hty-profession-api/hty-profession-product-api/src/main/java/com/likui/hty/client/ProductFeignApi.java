package com.likui.hty.client;

import com.likui.hty.hystrix.ProductFeignHystrix;
import com.likui.hty.product.bean.ProductInfoOutput;
import com.likui.hty.product.common.DecreaseStockInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "hty-profession-product",fallback = ProductFeignHystrix.class)
public interface ProductFeignApi {

    @PostMapping("/product/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
