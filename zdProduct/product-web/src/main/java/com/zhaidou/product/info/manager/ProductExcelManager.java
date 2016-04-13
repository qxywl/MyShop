package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.info.model.ProductModel;

public interface ProductExcelManager {
    
    /**
     * 商品新增 模版 导出 操作
     *
     * @param productModel
     * @return
     */
    public String exportProductTemplateExcel(List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,HttpServletResponse response);
    
    /**
     * 商品编辑 模版 下载操作
     *
     * @param productModel
     * @return
     */
    public String exportProductTemplateUpdateExcel(List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,HttpServletResponse response);
    
    /**
     * 商品 新增导入 操作
     *
     * @param productModel
     * @return
     */
    public String uploadProductAddExcel(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,Map<String,Object> map) throws Exception;
    
    /**
     * 商品 导出 操作
     *
     * @param productModel
     * @return
     */
    public String expoutProductLogic(List<ProductModel> listProduct,HttpServletResponse response);
}
