/*
 * 文 件 名:  ProductSalecategoryRelationServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.product.dao.ProductSalecategoryRelationDao;
import com.zhaidou.product.enums.MountProductOptType;
import com.zhaidou.product.model.ProductSalecategoryRelationModel;
import com.zhaidou.product.service.ProductSalecategoryRelationService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("productSalecategoryRelationService")
public class ProductSalecategoryRelationServiceImpl implements ProductSalecategoryRelationService {
	private static final Log logger = LogFactory
			.getLog(ProductSalecategoryRelationServiceImpl.class);

	@Resource
	private ProductSalecategoryRelationDao productSalecategoryRelationDao;

	@Override
	public void insertOrUpdate(ProductSalecategoryRelationModel productCategory, Integer optType) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productCategory);
		}
		//给categoryCode两边加分号;
		String categoryCode = productCategory.getCategoryCodes();
		if(!categoryCode.startsWith(";")){
			categoryCode = ";" + categoryCode;
		}
		if(!categoryCode.endsWith(";")){
			categoryCode += ";";
		}
		productCategory.setCategoryCodes(categoryCode);
		
		ProductSalecategoryRelationModel tempProductCategory = productSalecategoryRelationDao.getByProductCode(productCategory);
		if(tempProductCategory != null){
			String tempCategoryCodes = tempProductCategory.getCategoryCodes();
			
			String categoryCodes = null;
			if(MountProductOptType.UPDATE.getValue() == optType){
				categoryCodes = this.addCategoryCode(tempCategoryCodes, categoryCode);
			}else{
				categoryCodes = this.deleteCategoryCode(tempCategoryCodes, categoryCode);
				//如果删除后categoryCodes刚好为空，则删除这条记录
				if(categoryCodes== null || categoryCodes.equals("")){
					this.productSalecategoryRelationDao.delete(tempProductCategory);
					return ;
				}
			}
			tempProductCategory.setCategoryCodes(categoryCodes);
			this.productSalecategoryRelationDao.update(tempProductCategory);
		}else if(MountProductOptType.UPDATE.getValue() == optType){
			this.productSalecategoryRelationDao.insert(productCategory);
		}
	}
	
	@Override
	public Integer updateCategorys(ProductSalecategoryRelationModel model)
			throws Exception {
		try{
			return this.productSalecategoryRelationDao.updateCategorys(model);
		}catch(DaoException e){
			e.printStackTrace();
			logger.error("根据商品编号 ，把运营分类编号替换为空,操作失败:",e);
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("根据商品编号 ，把运营分类编号替换为空,操作失败:",e);
			throw e;
		}
	}

	@Override
	public Integer deleteByCategorysIsEmpty() throws Exception {
		try{
			return this.productSalecategoryRelationDao.deleteByCategorysIsEmpty();
		}catch(DaoException e){
			e.printStackTrace();
			logger.error("删除 运营分类编号字段为空的记录 ,操作失败:",e);
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("删除 运营分类编号字段为空的记录 ,操作失败:",e);
			throw e;
		}
	}

	/**
	 * 
	 * @param categoryCodes	两边加了; ，如： ";01;05;"
	 * @param categoryCode	如：";02;"
	 * @return 返回字符串格式：
	 * 			1 ";01;05;02;"
	 * 			2 ";02;"
	 */	
	private String addCategoryCode(String categoryCodes, String categoryCode){
		if(categoryCodes.indexOf(categoryCode) == -1){
			return categoryCodes.substring(0, categoryCodes.length()-1) + categoryCode;
		}else{
			return categoryCodes;
		}
	}
	
	/**
	 * 
	 * @param categoryCodes	两边加了; ，如： ";01;02;05;"
	 * @param categoryCode	如：";02;"
	 * @return 返回字符串格式：
	 * 			1 ";01;05;"
	 * 			2 ""
	 */	
	private String deleteCategoryCode(String categoryCodes, String categoryCode){
		String tmpStr = categoryCodes.replace(categoryCode, ";");
		if(tmpStr.equals(";")){
			return "";
		}
		return tmpStr;
	}
}
