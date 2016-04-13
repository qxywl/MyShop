/*
 * 文 件 名:  ProductServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-23
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.info.dao.ProductMallViewDao;
import com.zhaidou.product.info.service.ProductMallViewService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("productMallViewService")
public class ProductMallViewServiceImpl implements ProductMallViewService {
	private static final Log logger = LogFactory.getLog(ProductMallViewServiceImpl.class);

	@Resource
	private ProductMallViewDao productMallViewDao;

	/**
	 * 根据基础分类Code查询挂在该分类及子分类下的有效商品
	 * 
	 * 有效性： 状态status=2 and (上架 or (下架 and 显示) )
	 * @param categoryCode
	 * @return
	 */
	@Override
	public long countByCatCode(List<String> categoryCodeList) throws ZhaidouRuntimeException {
		if(categoryCodeList == null || categoryCodeList.size() == 0){
			throw new ZhaidouRuntimeException("基础分类编码参数为空");
		}
		long count = 0;
		try{
			count = productMallViewDao.countByCatCodes(categoryCodeList);
		}catch(DaoException e){
			logger.error("根据基础分类code查询挂载在该分类及子分类下的有效", e);
			throw new ZhaidouRuntimeException("根据基础分类code查询挂载在该分类及子分类下的有效", e);
		}
		return count;
	}

}
