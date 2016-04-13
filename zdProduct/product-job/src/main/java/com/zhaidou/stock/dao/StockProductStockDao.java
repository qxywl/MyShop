package com.zhaidou.stock.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.stock.model.StockProductStock;

import java.util.List;


public interface StockProductStockDao extends IDao{


    /**
     * 通过skuCode List 获取库存
     * @param skuCodes
     * */
    public List<StockProductStock> queryBySkuCodeList(List<String> skuCodes) throws Exception;

} 
