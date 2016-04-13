package com.zhaidou.product.info.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhaidou.product.info.manager.ProductSkuManager;
import com.zhaidou.product.info.model.ProductSkuModel;

@Controller
@RequestMapping(value="info/sku")
public class ProductSkuController {
    
    private static String UPDATESKU = "info/sku/update_sku";
    
    @Resource
    ProductSkuManager productSkuManager;
    
    @RequestMapping(value="/to_update",method={RequestMethod.GET, RequestMethod.POST })
    public String toUpdateProductSku(ProductSkuModel productSkuModel,Map<String,Object> map){
        
        
        productSkuModel = productSkuManager.getProductSku(productSkuModel);
        map.put("productSkuModel", productSkuModel);
        return UPDATESKU;
    }
}
