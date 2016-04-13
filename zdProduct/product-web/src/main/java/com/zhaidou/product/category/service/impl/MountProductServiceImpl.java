/*
 * 文 件 名:  SalecategoryProductRelationServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.common.util.ImportExecl;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.MountProductDao;
import com.zhaidou.product.category.enumration.ShelvesType;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.MountProduct;
import com.zhaidou.product.category.model.MountProductLog;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.MountProductLogService;
import com.zhaidou.product.category.service.MountProductOptType;
import com.zhaidou.product.category.service.MountProductService;
import com.zhaidou.product.category.service.MountType;
import com.zhaidou.product.info.model.ProductMallViewModel;
import com.zhaidou.product.info.model.ProductSalecategoryRelationModel;
import com.zhaidou.product.info.service.ProductSalecategoryRelationService;
import com.zhaidou.product.info.service.ProductService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("mountProductService")
public class MountProductServiceImpl implements MountProductService {
	private static final Log logger = LogFactory.getLog(MountProductServiceImpl.class);
	/** 导出字段和表名 */
	private static final String[] strHeaders = { "商品编码", "删除标志", "排序值" };
	private static final String[] strField = { "productCode", "optType", "orderValue"};
	private static final int limitRows = 3000;
	
	@Resource
	private MountProductDao mountProductDao;

	@Resource
	private ProductService productService;

	@Resource
	private CategoryService saleCategoryService;

	@Resource
	private ProductSalecategoryRelationService productSalecategoryRelationService;

	@Resource
	private MountProductLogService mountProductLogService;

	@Override
	public void insert(MountProduct salecategoryProductRelationModel) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + salecategoryProductRelationModel);
		}
		try {
			mountProductDao.insert(salecategoryProductRelationModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

/*	*//**
	 * 
	 * @param relationList
	 * @param saleCategory
	 *            传递的 运营分类对象，包含 categoryId 和categoryName
	 * @throws Exception
	 *//*
	@Transactional
	@Override
	public void batchInsertOrUpdate(List<MountProduct> relationList, CategoryPO saleCategory) throws DaoException {
		try {
			List<String> productCodeList = getProductCodeList(relationList);
			Map<String, Long> productMap = productService.getMapByProductCodes(productCodeList);

			Long categoryId = saleCategory.getId();
			String categoryCode = saleCategory.getCategoryCode();
			Date currentTime = new Date();
			String user = saleCategory.getUpdateUser();
			MountProduct tempModel = new MountProduct();
			// 产品导入状态
			boolean resultStatus = false;
			String failedReson = null;
			for (MountProduct relationModel : relationList) {
				relationModel.setMountType(MountType.MANUAL.getValue());
				// 默认是更新商品挂载
				if (relationModel.getOptType() != MountProductOptType.DELETE.getValue()) {
					relationModel.setOptType(MountProductOptType.UPDATE.getValue());
				}
				relationModel.setLastUpdateTime(currentTime);
				relationModel.setLastUpdateUser(user);

				// 产品表需要有对应产品数据
				String productCode = relationModel.getProductCode();
				if (!productMap.isEmpty() && productMap.containsKey(productCode)) {
					tempModel.setCategoryCode(categoryCode);
					tempModel.setProductCode(productCode);
					MountProduct model = mountProductDao
							.queryOneByProductAndCategoryCode(tempModel);

					if (model == null) {
						// 之前未挂载过
						if (relationModel.getOptType() == MountProductOptType.UPDATE.getValue()) {
							Long productId = productMap.get(productCode);
							relationModel.setProductId(productId);
							relationModel.setCategoryId(categoryId);
							relationModel.setCategoryCode(categoryCode);
							// 第一次挂载是只能是插入挂载， 删除挂载是不合法的
							if (relationModel.getOrderValue() == null) {
								relationModel.setOrderValue(0L);
							}
							relationModel.setMountTime(currentTime);
							relationModel.setMountUser(user);
							this.insert(relationModel);
							resultStatus = true;
						} else {
							failedReson = "此产品未挂载到该分类下，不需要删除挂载关系";
							resultStatus = false;
						}
					} else {
						// 更新
						Long relationId = model.getRelationId();
						relationModel.setRelationId(relationId);
						this.update(relationModel);
						resultStatus = true;
					}
				} else {
					failedReson = "所要挂载的产品不存在";
					resultStatus = false;
				}

				// 记录商品挂载日志到数据库
				if (resultStatus == true) {
					// 冗余表：更新产品所挂载的分类有哪些
					ProductSalecategoryRelationModel productCategory = new ProductSalecategoryRelationModel();
					productCategory.setProductCode(productCode);
					productCategory.setCategoryCodes(";" + categoryCode + ";");
					this.productSalecategoryRelationService
							.insertOrUpdate(productCategory, relationModel.getOptType());
					// 记录日志
					// recordCategProdRelLog(relationModel, "");
				} else {
					// 记录日志
					// recordCategProdRelLog(relationModel, failedReson);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/

	/**
	 * 
	 * @param relationList
	 * @param categoryCode
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateOrBatchInsert(List<MountProduct> relationList, String categoryCode) throws ZhaidouRuntimeException {
		try {
			List<String> productCodeList = getProductCodeList(relationList);
			Map<String, Long> productMap = productService.getMapByProductCodes(productCodeList);

			MountProduct tmpMountProduct = new MountProduct();
			tmpMountProduct.setCategoryCode(categoryCode);
			List<MountProduct> mpList = this.mountProductDao
					.queryListByCategoryCode(tmpMountProduct);
			List<MountProduct> updateList = new ArrayList<MountProduct>(); // 已存在，需要更新的列表
			List<MountProduct> insertList = new ArrayList<MountProduct>();// 需要插入的列表
			List<MountProduct> notExsitErrorList = new ArrayList<MountProduct>();// 产品关联表中不存在的商品，无法插入的错误列表
			List<MountProduct> delErrorList = new ArrayList<MountProduct>();// 关系表中不存在，无法删除的错误列表

			Map<String, Long> mpIdPcodeMap = getIdPcodeMap(mpList);
			for (MountProduct item : relationList) {
				String productCode = item.getProductCode();
				if (productMap != null && productMap.containsKey(productCode)) {
					Long productId = productMap.get(productCode);
					item.setProductId(productId);
					if (mpIdPcodeMap == null || !mpIdPcodeMap.containsKey(productCode)) {
						// 关系表里没有则加入到插入列表
						Integer optType = item.getOptType();
						if (optType == MountProductOptType.UPDATE.getValue()) {
							// item.setCategoryCode(categoryCode);
							insertList.add(item);
						} else {
							// 既然关系表里没有，则没法删除
							delErrorList.add(item);
						}
					} else {
						// 关系表里存在则更新记录
						item.setRelationId(mpIdPcodeMap.get(productCode));
						updateList.add(item);
					}
				} else {
					notExsitErrorList.add(item);
				}
			}

			// 操作产品挂载表
			if (insertList != null && insertList.size() > 0) {
				this.mountProductDao.batchInsert(insertList);
			}
			if (updateList != null && updateList.size() > 0) {
				for (MountProduct uMp : updateList) {
					this.update(uMp);
				}
			}

			// 冗余表操作
			List<MountProduct> okMPList = new ArrayList<MountProduct>();
			if (insertList != null && insertList.size() > 0) {
				okMPList.addAll(insertList);
			}
			if (updateList != null && updateList.size() > 0) {
				okMPList.addAll(updateList);
			}
			if (okMPList != null && okMPList.size() > 0) {
				for (MountProduct mp : okMPList) {
					// 冗余表：更新产品所挂载的分类有哪些
					ProductSalecategoryRelationModel productCategory = new ProductSalecategoryRelationModel();
					productCategory.setProductCode(mp.getProductCode());
					productCategory.setCategoryCodes(";" + categoryCode + ";");
					this.productSalecategoryRelationService.insertOrUpdate(productCategory, mp
							.getOptType());
				}
			}

			// 日志表操作
			List<MountProductLog> logList = new ArrayList<MountProductLog>();
			if(insertList != null && insertList.size() > 0){
				logList.addAll(this.getMountProductLogList(insertList, null));
			}
			if(updateList != null && updateList.size() > 0){
				logList.addAll(this.getMountProductLogList(updateList, null));
			}
			if(notExsitErrorList != null && notExsitErrorList.size() > 0){
				logList.addAll(this.getMountProductLogList(notExsitErrorList, "所要挂载的产品不存在"));
			}
			if(delErrorList != null && delErrorList.size() > 0){
				logList.addAll(this.getMountProductLogList(delErrorList, "此产品未挂载到该分类下，不需要删除挂载关系"));
			}
			mountProductLogService.batchInsert(logList);
		
		} catch (Exception e) {
			logger.error("批量手动导入产品失败", e);
			throw new ZhaidouRuntimeException("批量手动导入产品失败", e);
		}
	}

	private List<MountProductLog> getMountProductLogList(List<MountProduct> mpList, String failedReson) {
		if (mpList == null || mpList.size() == 0) {
			return null;
		}
		List<MountProductLog> logList = new ArrayList<MountProductLog>(mpList.size());
		for (MountProduct relationModel : mpList) {
			MountProductLog mountProductLog = new MountProductLog();
			mountProductLog.setRelationId(relationModel.getRelationId());
			mountProductLog.setCategoryId(relationModel.getCategoryId());
			mountProductLog.setCategoryCode(relationModel.getCategoryCode());
			mountProductLog.setProductId(relationModel.getProductId());
			mountProductLog.setProductCode(relationModel.getProductCode());
			mountProductLog.setMountType(relationModel.getMountType());
			mountProductLog.setOrderValue(relationModel.getOrderValue());
			mountProductLog.setOptType(relationModel.getOptType());
			if(failedReson != null && failedReson.length() != 0){
				mountProductLog.setDescription(failedReson);
			}
			mountProductLog.setOperateUser(relationModel.getLastUpdateUser());
			mountProductLog.setOperateTime(relationModel.getLastUpdateTime());
			mountProductLog.setLogDate(new Date());
			logList.add(mountProductLog);
		}
		return logList;
	}


	/**
	 * 数据格式： 商品编号 运营分类编号 删除标志 排序值 101 1010 2 10
	 * 
	 * @param relationList
	 * @return
	 */
	private List<String> getProductCodeList(List<MountProduct> relationList) {
		if (relationList != null && relationList.size() > 0) {
			List<String> productCodeList = new ArrayList<String>(relationList.size());
			for (MountProduct item : relationList) {
				productCodeList.add(item.getProductCode());
			}
			return productCodeList;
		} else {
			return null;
		}
	}
	
	private Map<String, Long> getIdPcodeMap(List<MountProduct> relationList) {
		if (relationList != null && relationList.size() > 0) {
			Map<String,Long> mpMap = new HashMap<String,Long>(relationList.size());
			for (MountProduct item : relationList) {
				mpMap.put(item.getProductCode(), item.getRelationId());
			}
			return mpMap;
		} else {
			return null;
		}
	}


	/*
	 * private List<String>
	 * getSaleCategoryCodeList(List<SalecategoryProductRelationModel>
	 * relationList) { if (relationList != null && relationList.size() > 0) {
	 * List<String> productCodeList = new
	 * ArrayList<String>(relationList.size()); for
	 * (SalecategoryProductRelationModel item : relationList) {
	 * productCodeList.add(item.getCategoryCode()); } return productCodeList; }
	 * else { return null; } }
	 */

	@Override
	public void update(MountProduct mountProduct) throws ZhaidouRuntimeException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		try {
			mountProductDao.update(mountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZhaidouRuntimeException(e);
		}
	}

	@Override
	public MountProduct getById(MountProduct mountProduct) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		MountProduct result;
		try {
			result = mountProductDao.queryOne(mountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public long getCount(MountProduct mountProduct) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		long result;
		try {
			result = mountProductDao.countListPage(mountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public List<MountProduct> getList(MountProduct mountProduct, Page page) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		long count;
		List<MountProduct> result = null;
		try {
			count = mountProductDao.countListPage(mountProduct);

			if (count > 0) {
				page.setTotalCount(count);
				result = mountProductDao.queryListPage(mountProduct, page.getPageNum(), page
						.getNumPerPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public void deleteById(MountProduct mountProduct) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		try {
			mountProductDao.delete(mountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseObject processMpExcel(String filename, InputStream ins, CategoryPO saleCategory) throws IOException, ZhaidouRuntimeException{
		ResponseObject responseObj = null;
		
		List<MountProduct> mpList = null;
		ImportExecl importExcel = new ImportExecl();
		List<List<String>> dataList = importExcel.read(filename, ins);
		List<String> headerList = Arrays.asList(strHeaders);
		
		if(dataList.size() > 0){
			if(validateTemplate(headerList, dataList.get(0))){
				try{
					mpList = buildModelList(dataList);
				}catch(NullPointerException e){
					logger.error("从Excel转化生成MountProduct模型失败", e);
					responseObj = new ResponseObject("501", e.getMessage());
					return responseObj;
				}
			}else{
				logger.error("你使用的模板不正确~");
				return new ResponseObject("500", "你使用的模板不正确~");
			}
		}else{
			logger.error("表格里没有数据");
			return new ResponseObject("502", "表格里没有数据");
		}
		
		/**
		 * 这里要修改， 现在是 最多成功导入三千条数据。
		 */
		String message = "导入成功";
		if (mpList.size() > limitRows) {
			message = "你导入的商品超过3000条，丢弃" + (mpList.size() - limitRows) + "条"; 
			mpList = mpList.subList(0, limitRows - 1);
		}
		
		try {
			Long categoryId = saleCategory.getId();
			String categoryCode = saleCategory.getCategoryCode();
			Date currentTime = new Date();
			String user = saleCategory.getUpdateUser();
			for (MountProduct item : mpList) {
				item.setCategoryId(categoryId);
				item.setCategoryCode(categoryCode);
				item.setMountType(MountType.MANUAL.getValue());
//				 默认是更新商品挂载
				if (item.getOptType() != MountProductOptType.DELETE.getValue()) {
					item.setOptType(MountProductOptType.UPDATE.getValue());
				}
				if (item.getOrderValue() == null) {
					item.setOrderValue(0L);
				}
				item.setMountTime(currentTime);
				item.setMountUser(user);
				item.setLastUpdateTime(currentTime);
				item.setLastUpdateUser(user);
			}
			this.updateOrBatchInsert(mpList, categoryCode);
			responseObj = new ResponseObject(ResponseObject.CODE_SUCCESS_VALUE, null, message, null);
		} catch (Exception e) {
			logger.error("手动挂载商品失败---", e);
			throw new ZhaidouRuntimeException("手动挂载商品服务端失败", e);
		}
		return responseObj;
	}

	
	/**
	 * 批量挂载运营分类
	 * */
	@Override
	public ResponseObject batchCategoryImport(String filename, InputStream ins,String operator)
			throws ZhaidouRuntimeException, IOException {

		ResponseObject responseObj = null;
	    String[] headers = { "商品编码","运营分类编码", "删除标志", "排序值" };

		List<MountProduct> mpList = null;
		ImportExecl importExcel = new ImportExecl();
		List<List<String>> dataList = importExcel.read(filename, ins);
		List<String> headerList = Arrays.asList(headers);
		
		if(dataList.size() > 0){
			if(validateTemplate(headerList, dataList.get(0))){
				try{
					mpList = buildBatchCategoryMountModel(dataList);
				}catch(NullPointerException e){
					logger.error("从Excel转化生成MountProduct模型失败", e);
					responseObj = new ResponseObject("501", e.getMessage());
					return responseObj;
				}
			}else{
				logger.error("你使用的模板不正确~");
				return new ResponseObject("500", "你使用的模板不正确~");
			}
		}else{
			logger.error("表格里没有数据");
			return new ResponseObject("502", "表格里没有数据");
		}
		
		
		if(mpList == null || mpList.size()< 1){
			return new ResponseObject("502", "表格里没有数据");
		}
		
		
		/**
		 * 这里要修改， 现在是 最多成功导入三千条数据。
		 */
		String message = "导入成功!";
		if (mpList.size() > limitRows) {
			message = "你导入的商品超过3000条，丢弃" + (mpList.size() - limitRows) + "条"; 
			mpList = mpList.subList(0, limitRows - 1);
		}
		
		
		
		//1 .去重，categoryCode + productCode 只能存在一个
		Map<String,MountProduct>  removeRepeMap = new  HashMap<String,MountProduct>();//去掉重复
		List<String> queryCateCodeList = new ArrayList<String>();
		
		for(MountProduct mp: mpList){
			String key = mp.getCategoryCode() + "###" +mp.getProductCode();
			removeRepeMap.put(key, mp);
			queryCateCodeList.add(mp.getCategoryCode());
		}
		
		//2 .分类，按key:categoryCode  value:List<MountProduct> 
		Map<String ,List<MountProduct>> cateProListMap = new HashMap<String,List<MountProduct>>();
		for (Entry<String, MountProduct> reMp : removeRepeMap.entrySet()) {
			String key = reMp.getKey();
			String [] spl = key.split("[###]");
			if(spl.length < 1){
				continue;
			}
			
			if(!cateProListMap.containsKey(spl[0])){
				cateProListMap.put(spl[0],new ArrayList<MountProduct>());
			}
			cateProListMap.get(spl[0]).add(reMp.getValue());
		}
	
		
		Map<String, Long> saleCateList = saleCategoryService.getListByCategoryCodes(queryCateCodeList);//获取真实存在的运营分类,在导入的表格中，只插入运营分类存在的数据
		if(saleCateList == null || saleCateList.size() < 1){
			return new ResponseObject("503", "导入的表格中的运营分类在数据库中不存在！");
		}
		
		int noLeaf = 0 ;
		StringBuffer noLeafStr = new StringBuffer();
		//3 .过滤 ,去掉 掉运营分类编码不存在的数据，只取数据库中有的数据 
		for ( Entry<String, Long> sale:saleCateList.entrySet()) {
			List<MountProduct > tmpMpList = null;
			if((tmpMpList=cateProListMap.get(sale.getKey())) != null){
				
				for(MountProduct mp: tmpMpList){ 
					mp.setCategoryCode(sale.getKey());
					mp.setCategoryId(sale.getValue());
					mp.setMountType(MountType.MANUAL.getValue());
					// 默认是更新商品挂载
					if (mp.getOptType() != MountProductOptType.DELETE.getValue()) {
						mp.setOptType(MountProductOptType.UPDATE.getValue());
					}
					if (mp.getOrderValue() == null) {
						mp.setOrderValue(0L);
					}
					Date currentTime = new Date();
					mp.setMountTime(currentTime);
					mp.setMountUser(operator);
					mp.setLastUpdateTime(currentTime);
					mp.setLastUpdateUser(operator);
				}
				
				boolean isNode = saleCategoryService.isLeafNode(sale.getKey());
				if(isNode){ // 叶子节点
					this.updateOrBatchInsert(tmpMpList,sale.getKey());
				}else{
					noLeaf ++;
					noLeafStr.append("[categoryCode=" + sale.getKey()+"] ");
				}
				
			}
		}
		
		if(noLeaf > 0){
			message += "运营分类编码" + noLeafStr.toString() + "不是叶子节点，不能挂载商品。" ;
		}
		
		responseObj = new ResponseObject(ResponseObject.CODE_SUCCESS_VALUE, null, message, null);
		
		return responseObj;
		
	}
	
	
	
	private List<MountProduct> buildModelList(List<List<String>> dataList) throws NullPointerException{
		List<MountProduct> mpList = null;
		if (dataList != null && dataList.size() > 0 ) {
			mpList = new ArrayList<MountProduct>();
			Iterator<List<String>> iter = dataList.iterator();
			iter.next(); // 去掉第一行标题
			while (iter.hasNext()) {
				List<String> row = iter.next();
				MountProduct model = new MountProduct();
				String productCode = row.get(0);
				String optType = row.get(1);
				String orderValue = row.get(2);
				
				if(productCode == null || productCode.trim().length() == 0){
//					continue; //过滤掉非法无效行
					throw new NullPointerException("productCode不能为空");
				}else{
					model.setProductCode(productCode.trim());
				}
				
				if (optType != null && optType.trim().length() > 0) {
					model.setOptType(Integer.valueOf(optType.trim()));
				} else {
					model.setOptType(MountProductOptType.UPDATE.getValue()); //默认不填则认为是添加
				}

				if (orderValue != null && orderValue.trim().length() > 0) {
					model.setOrderValue(Long.valueOf(orderValue.trim()));
				} else {
					model.setOrderValue(0L); //默认不填则认为是添加
				}
				mpList.add(model);
			}
		}
		return mpList;
	}

	
	/**
	 * 建立批量挂载运营分类--商品 对象
	 * */
	private List<MountProduct> buildBatchCategoryMountModel(List<List<String>> dataList) throws NullPointerException{
		List<MountProduct> mpList = null;
		if (dataList != null && dataList.size() > 0 ) {
			mpList = new ArrayList<MountProduct>();
			Iterator<List<String>> iter = dataList.iterator();
			iter.next(); // 去掉第一行标题
			while (iter.hasNext()) {
				List<String> row = iter.next();
				MountProduct model = new MountProduct();
				String productCode = row.get(0);
				String categoryCode = row.get(1);
				String optType = row.get(2);
				String orderValue = row.get(3);
				
				if(productCode == null || productCode.trim().length() == 0 ||
						categoryCode == null || categoryCode.trim().length() == 0 
						){
					
					throw new NullPointerException("productCode或categoryCode不能为空!");
				}else{
					model.setProductCode(productCode.trim());
					model.setCategoryCode(categoryCode.trim());
				}
				
				if (optType != null && optType.trim().length() > 0) {
					model.setOptType(Integer.valueOf(optType.trim()));
				} else {
					model.setOptType(MountProductOptType.UPDATE.getValue()); //默认不填则认为是添加
				}

				if (orderValue != null && orderValue.trim().length() > 0) {
					model.setOrderValue(Long.valueOf(orderValue.trim()));
				} else {
					model.setOrderValue(0L); //默认不填则认为是添加
				}
				mpList.add(model);
			}
		}
		return mpList;
	}
	
	
	private boolean validateTemplate(List<String> titleList, List<String> excelTitleList) {
		if (titleList.equals(excelTitleList)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<MountProduct> getProductsByCategoryId(MountProduct relationModel, Page page) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + relationModel);
		}
		long count;
		List<MountProduct> result = null;
		try {
			count = mountProductDao.countProductsListPageByCategoryId(relationModel);

			if (count > 0) {
				page.setTotalCount(count);
				result = mountProductDao
						.queryProductsByCategoryId(relationModel, page.getPageNum(), page
								.getNumPerPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public List<MountProduct> selectOpt1ByCategoryId(MountProduct model)
			throws Exception {
		try {
			return mountProductDao.selectOpt1ByCategoryId(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据运营分类ID加载其下所有opt_type=1的商品,操作失败：",e);
			throw new Exception(e);
		}
	}


	@Override
	public boolean hasProductInCategory(List<String> catgoryCodeList) throws ZhaidouRuntimeException {
		if(catgoryCodeList == null || catgoryCodeList.size() == 0)
			throw new ZhaidouRuntimeException("运营分类ID 为空");
		try{
			Long count = this.mountProductDao.countProductsInSubCategory(catgoryCodeList );
			if(count > 0)
				return true;
			else {
				return false;
			}
		}catch(Exception e){
			throw new ZhaidouRuntimeException("查询运营分类ID为" + catgoryCodeList.toString() + "下挂载的商品失败", e);
		}
	}

	@Override
	public boolean hasMountProduct(Long categoryId) throws ZhaidouRuntimeException{
		if(categoryId == null)
			throw new ZhaidouRuntimeException("运营分类ID 为空");
		try{
			Long count = this.mountProductDao.countProductsByCategoryId(categoryId );
			if(count > 0)
				return true;
			else {
				return false;
			}
		}catch(Exception e){
			throw new ZhaidouRuntimeException("查询运营分类ID为" + categoryId + "下挂载的商品失败", e);
		}
	}

	
	@Override
	public List<MountProduct> queryExprotProdsByCategoryId(Long categoryId)
			throws Exception {
		
		List<MountProduct> resultList = null;
		
		MountProduct query = new MountProduct();
		query.setCategoryId(categoryId);
		resultList = mountProductDao.queryExprotProdsByCategoryId(query);
		
		return resultList;
	}
	
	
	@Override
	public Workbook exportExcel(List<MountProduct> mountList,List<String> titles, List<String> fields) throws Exception{

		
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		
		// XSSFSheet sheet = workBook.createSheet("2015");
		XSSFSheet sheet = workBook.createSheet("2015");// 创建一个Excel的Sheet
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		
		 //1.创建的表头
        Row sysRow = sheet.createRow(0);
        sysRow.setRowStyle(headStyle);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = sysRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles.get(i));
        }

        //2.填写数据
        int j = 1;
        JSONObject json = null;
        ProductMallViewModel mallView = null ;
        for (MountProduct data : mountList) {
           
        	Row sysRowJ = sheet.createRow(j); //创建行
        	mallView = data.getProductMallViewModel();
        	json = (JSONObject) JSON.toJSON(mallView);
            for (int i = 0; i < fields.size(); i++) {
            	
            	String  key = fields.get(i);
            	Object value = json.get(key);

            	Cell cell0 = sysRowJ.createCell(i);
            	value = value == null ? "" : value;
            	if("productShelves".equals(key)){
            		//商品上下架状态 1  上架  0  下架
            		value = ShelvesType.UP.getValue().equals(value)?"上架":"下架";
            	}
            	if("productDownShow".equals(key)){
            		//是否下架显示 1是 2否
            		value = Integer.valueOf(1).equals(value)?"是":"否";
            	}
            	
                cell0.setCellValue(value.toString());
            }
            j++;  // 控制行数
        }
        return workBook;
		
	}

	
	@Override
	public MountProduct getMpOrderValue(Long relationId) throws Exception {
		MountProduct resultModel = null;
		resultModel =  mountProductDao.getMpOrderValByRelId(relationId);
		return resultModel;
	}
	
	
	@Override
	public int updateMpOrderValueByRelId(MountProduct mpModel)
			throws Exception {
		return mountProductDao.updateMpOrderValueByRelId(mpModel);
	}

	
	
}
