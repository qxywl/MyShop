/*
 * 文 件 名:  ProductBrandService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.CountryModel;
import com.zhaidou.product.model.base.SupplierShopModel;





/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SupplierShopService
{

    /**
     * 根据id 获取SupplierShopModel
     * @param SupplierShopModel
     * @return
     */
    public SupplierShopModel getSupplierById(Long  supShopId) throws Exception ;
    
    
    /**
     * 根据idList 获取SupplierShopModel集合
     * @param idList 
     * @return
     */
    public List<SupplierShopModel> getSupplierByIdList(List<Long> idList) throws Exception ;

}
