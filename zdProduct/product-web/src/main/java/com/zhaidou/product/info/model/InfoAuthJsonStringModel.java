package com.zhaidou.product.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.json.JSONUtil;

@SuppressWarnings("serial")
public class InfoAuthJsonStringModel implements Serializable{
    
    private String update;
    private String insert;
    public String getUpdate() {
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
    }
    
    
    public String getInsert() {
        return insert;
    }
    public void setInsert(String insert) {
        this.insert = insert;
    }
    public static void main(String[] args) {
        InfoAuthJsonStringModel info = new InfoAuthJsonStringModel();
        ProductModel productModel = new ProductModel();
        productModel.setProductCode("asdfdsafsd");
        productModel.setProductName("sadfsafas");
        List<ProductSkuModel> listSku = new ArrayList<ProductSkuModel>();
        ProductSkuModel sku = new ProductSkuModel();
        sku.setAttrColorName("asdgdfafg");
        sku.setAttrColorValue("asdfasgdfg");
        listSku.add(sku);
        
        sku= new ProductSkuModel();
        sku.setAttrColorName("fdghfd");
        sku.setAttrColorValue("rtuyrt");
        listSku.add(sku);
        
        info.setUpdate(JSONUtil.toString(productModel));
//        info.setInsert(JSONUtil.toString(listSku));
        
        System.out.println(JSONUtil.toString(info));
        
        Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(JSONUtil.toString(info), String.class);
//        List<ProductSkuModel> listSku1 = new ArrayList<ProductSkuModel>();
//        ProductModel productModel1 = (ProductModel)JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
        List<ProductSkuModel> insertListSku = (List<ProductSkuModel>)JSONUtil.toCollection(mapValue.get("insert"), ProductSkuModel.class);
        System.out.println(insertListSku.toString());
//        System.out.println(productModel1.getProductCode());
    }
}
