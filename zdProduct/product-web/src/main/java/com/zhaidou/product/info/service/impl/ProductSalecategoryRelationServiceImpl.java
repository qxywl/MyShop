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
package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.service.MountProductOptType;
import com.zhaidou.product.info.dao.ProductSalecategoryRelationDao;
import com.zhaidou.product.info.model.ProductSalecategoryRelationModel;
import com.zhaidou.product.info.service.ProductSalecategoryRelationService;

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

	public void add(ProductSalecategoryRelationModel productSalecategoryRelationModel) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		try {
			productSalecategoryRelationDao.insert(productSalecategoryRelationModel);
		} catch (Exception e) {
			logger.error("ProductSalecategoryRelationServiceImpl add error", e);
			throw new ZhaidouRuntimeException("添加商品运营分类挂载关系冗余信息失败", e);
		}
	}

	public void update(ProductSalecategoryRelationModel productSalecategoryRelationModel) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		try {
			productSalecategoryRelationDao.update(productSalecategoryRelationModel);
		} catch (Exception e) {
			logger.error("修改商品运营分类挂载关系冗余信息失败", e);
			throw new ZhaidouRuntimeException("修改商品运营分类挂载关系冗余信息失败", e);
		}
	}

	public ProductSalecategoryRelationModel getById(ProductSalecategoryRelationModel productSalecategoryRelationModel) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		ProductSalecategoryRelationModel result;
		try {
			result = productSalecategoryRelationDao
					.queryOne(productSalecategoryRelationModel);
		} catch (Exception e) {
			logger.error("查询商品运营分类挂载关系冗余信息失败", e);
			throw new ZhaidouRuntimeException("查询商品运营分类挂载关系冗余信息失败", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public ProductSalecategoryRelationModel getByProductCode(String productCode) throws ZhaidouRuntimeException {
		try {
			ProductSalecategoryRelationModel psRel = new ProductSalecategoryRelationModel();
			psRel.setProductCode(productCode);
			return productSalecategoryRelationDao.getByProductCode(psRel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZhaidouRuntimeException("根据productCode查找ProductSalecategoryRelationModel", e);
		}
	}

	public long getCount(ProductSalecategoryRelationModel productSalecategoryRelationModel) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		long result;
		try {
			result = productSalecategoryRelationDao
					.countListPage(productSalecategoryRelationModel);
		} catch (Exception e) {
			logger.error("查询商品运营分类挂载关系冗余信息条数失败", e);
			throw new ZhaidouRuntimeException("查询商品运营分类挂载关系冗余信息条数失败", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	public List<ProductSalecategoryRelationModel> getList(ProductSalecategoryRelationModel productSalecategoryRelationModel, Page page) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		List<ProductSalecategoryRelationModel> result = null;
		try {
			long count = productSalecategoryRelationDao.countListPage(productSalecategoryRelationModel);
			if (count > 0) {
					result = productSalecategoryRelationDao
							.queryListPage(productSalecategoryRelationModel, page.getPageNum(), page
									.getNumPerPage());
			}
		} catch (Exception e) {
			logger.error("查询商品运营分类挂载关系冗余信息列表失败", e);
			throw new ZhaidouRuntimeException("查询商品运营分类挂载关系冗余信息列表失败", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	public void deleteById(ProductSalecategoryRelationModel productSalecategoryRelationModel) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productSalecategoryRelationModel);
		}
		try {
			productSalecategoryRelationDao.delete(productSalecategoryRelationModel);
		} catch (Exception e) {
			logger.error("删除商品运营分类挂载关系冗余信息失败", e);
			throw new ZhaidouRuntimeException("删除商品运营分类挂载关系冗余信息失败", e);
		}
	}

	/**
	 * 
	 */
	@Override
	public void insertOrUpdate(ProductSalecategoryRelationModel productCategory, Integer optType) throws ZhaidouRuntimeException{
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + productCategory);
		}
		try{
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
		}catch(Exception e){
			logger.error("批量插入或修改商品运营分类挂载关系冗余信息失败", e);
			throw new ZhaidouRuntimeException("批量插入或修改商品运营分类挂载关系冗余信息失败", e);
		}
//		productSalecategoryRelationDao.(productCategory);
		
	}
	
	@Override
	public Integer updateCategorys(ProductSalecategoryRelationModel model)
			throws ZhaidouRuntimeException {
		try{
			return this.productSalecategoryRelationDao.updateCategorys(model);
		}catch(DaoException e){
			e.printStackTrace();
			logger.error("根据商品编号 ，把运营分类编号替换为空,操作失败:",e);
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("根据商品编号 ，把运营分类编号替换为空,操作失败:",e);
			throw new ZhaidouRuntimeException("根据商品编号 ，把运营分类编号替换为空,操作失败:", e);
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
//		String [] categoryCodeArr = categoryCodes.split(";");
//		categoryCode = categoryCode.substring(1, categoryCode.length()-1);
//		for(int i = 0; i< categoryCodeArr.length;  i++){
//			if(categoryCodeArr[i].equals(categoryCode))
//				return categoryCodes;
//		}
//		//去掉最后一个分号;
//		return categoryCodes.substring(0, categoryCodes.length()-1) + categoryCode;
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
//		List<String> categoryCodeList = new LinkedList<String>(Arrays.asList(categoryCodes.split(";")));
//		Iterator<String> iter =  categoryCodeList.iterator();
//		while(iter.hasNext()){
//			String categoryCodeItem = iter.next();
//			if(categoryCodeItem.equals(categoryCode)){
//				iter.remove();
//			}
//		}
//		//删除后判断是否空了
//		if(categoryCodeList.size() == 0){
//			return "";
//		}
//		
//		//前后加分号，如
//		StringBuffer sb = new StringBuffer(";");
//		for(String item : categoryCodeList){
//			sb.append(item).append(";");
//		}
//		return sb.toString();
		String tmpStr = categoryCodes.replace(categoryCode, ";");
		if(tmpStr.equals(";")){
			return "";
		}
		return tmpStr;
	}
}
