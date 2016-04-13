package com.zhaidou.product.info.manager;

import java.util.List;

import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;


public interface ProductAttrbuteManager {
    /**
     * 根据基础分类 code 获取属性组，属性项
     *
     * @param categoryCode
     * @return
     */
    public List<ProductCateAttrGroupModel> getAttrByCategory(String categoryCode);
}
