package com.likui.hty.product.controller;

import com.likui.hty.product.bean.ProductCategroy;
import com.likui.hty.product.bean.ProductInfo;
import com.likui.hty.product.bean.ProductInfoOutput;
import com.likui.hty.product.common.DecreaseStockInput;
import com.likui.hty.product.enums.ProductStatusEnum;
import com.likui.hty.product.service.ProductCategroyService;
import com.likui.hty.product.service.ProductInfoService;
import com.likui.hty.product.vo.ProductInfoVO;
import com.likui.hty.product.vo.ProductVO;
import com.likui.hty.resultvo.ResultVO;
import com.likui.hty.resultvo.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author likui
 * @Classname: ProductController
 * @Description: 产品控制器
 * @create 2018-09-20 11:42
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategroyService productCategroyService;

    /**
     * @methord: findProductList
     * @author： likui
     * @great： 2018/9/30 10:57
     * 1：查询在架的商品
     * 2：获取类目type列表
     * 3：查询类目
     * 4：构造数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询在架的商品")
    public ResultVO<ProductVO> findProductList(){
        //１：查询在架的商品
        List<ProductInfo> productInfos = productInfoService.findByProductStatus(ProductStatusEnum.UP.getCode());
        //２：获取类目type列表
        List<Integer> categroyTypes = productInfos.stream().map(ProductInfo::getCategroyType).collect(Collectors.toList());
        //３：查询类目
        List<ProductCategroy> productCategroys = productCategroyService.findByCategroyTypeIn(categroyTypes);
        //４：构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategroy productCategroy : productCategroys){
            ProductVO productVO = new ProductVO();
            productVO.setCategroyName(productCategroy.getCategroyName());
            productVO.setCategroyType(productCategroy.getCategroyType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfos){
                if(productInfo.getCategroyType().equals(productCategroy.getCategroyType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 根据订单中的产品标号查询产品
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
        return productInfoService.findByProductIdIn(productIdList);
    }

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productInfoService.decreaseStock(decreaseStockInputList);
        /*int i = 10/0;*/
    }
}
