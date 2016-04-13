package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductShelvesLogModel;
import com.zhaidou.product.info.model.ProductShelvesModel;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;

public interface ProductShelvesManager {
    /**
     * 商品 上下架 列表
     *
     * @param productShelvesModel
     * @param page
     * @return
     */
    public List<ProductShelvesModel> getProductShelves(ProductShelvesModel productShelvesModel,Page page);
    
    /**
     * 商品 上下架日志 列表
     *
     * @param productShelvesModel
     * @param page
     * @return
     */
    public List<ProductShelvesLogModel> getProductShelvesLogList(ProductShelvesLogModel productShelvesLogModel,Page page);
    
    /**
     * 导入上下架 操作
     *
     * @param list
     * @return
     */
    public String uploadShelvesExcel(List<List<String>> list,Map<String,String> map);
    
    /**
     * 导出上下架 操作
     *
     * @param list
     * @return
     */
    public String exportShelvesExcel(List<ProductShelvesModel> listShelves,HttpServletResponse response);
    /**
     * 导出上下架日志 操作
     *
     * @param list
     * @return
     */
    public String exportShelvesExcelLog(List<ProductShelvesLogModel> listShelvesLog,HttpServletResponse response);
    
    /**
     * 修改操作
     *
     * @param list
     * @return
     */
    public String updateShelves(ProductShelvesModel productShelvesModel);
    
    /**
     * 上下架操作
     *
     * @param list
     * @return
     */
    public String shelvesOperation(Long[] ids,String shelvesType,String reason,Map<String,String> map);
    
    public void addShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel)throws Exception;
    
    public ProductShelvesTmpModel getShelvesTmpByProductCode(String productCode)throws Exception;
    
    public void updateShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel)throws Exception;
    
    /**
     * 待上架查询
     *
     * @param list
     * @return
     */
    public List<ProductShelvesTmpModel> getShelvesTmpList(ProductShelvesTmpModel productShelvesTmpModel,Page page)throws Exception;
}
