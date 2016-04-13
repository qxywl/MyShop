/*
 * 文 件 名:  ProductBrandServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhaidou.product.dao.SupplierShopDao;
import com.zhaidou.product.model.base.SupplierShopModel;
import com.zhaidou.product.service.SupplierShopService;





/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("supplierShopService")
@Transactional()
public class SupplierShopServiceImpl implements SupplierShopService
{
    private static final Log logger = LogFactory.getLog(SupplierShopServiceImpl.class);

    @Resource
    private SupplierShopDao  supplierShopDao;

	@Override
	public SupplierShopModel getSupplierById(Long supShopId) throws Exception {
		
		SupplierShopModel resultModel = new SupplierShopModel();
		
		SupplierShopModel queryModel = new SupplierShopModel();
		queryModel.setId(supShopId);
		
		resultModel = supplierShopDao.getSupplierById(queryModel);
		return resultModel;
	}

	@Override
	public List<SupplierShopModel> getSupplierByIdList(
			List<Long> idList) throws Exception {
		
		List<SupplierShopModel> resultList = null;
		
		SupplierShopModel queryModel = new SupplierShopModel();
		if(idList != null && idList.size()>0){
			queryModel.setIdList(idList);
		}
		
		resultList = supplierShopDao.getSupplierByIdList(queryModel);
		return resultList;
	}


    
   
}
