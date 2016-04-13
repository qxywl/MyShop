/*
 * 文 件 名:  ProductInfoLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.product.info.dao.ProductInfoExamineDao;
import com.zhaidou.product.info.model.ProductInfoExamineModel;
import com.zhaidou.product.info.service.ProductInfoExamineService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoExamineService")
public class ProductInfoExamineServiceImpl implements ProductInfoExamineService
{
    private static final Log logger = LogFactory.getLog(ProductInfoExamineServiceImpl.class);

    @Resource
    private ProductInfoExamineDao  productInfoExamineDao;

	@Override
	public void add(ProductInfoExamineModel productInfoExamineModel)
			throws Exception {
		// TODO Auto-generated method stub
		productInfoExamineDao.add(productInfoExamineModel);
	}

   
}
