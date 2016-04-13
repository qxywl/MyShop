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
package com.zhaidou.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.dao.MountProductDao;
import com.zhaidou.product.enums.MountProductOptType;
import com.zhaidou.product.model.MountProductLogModel;
import com.zhaidou.product.model.MountProductModel;
import com.zhaidou.product.model.ProductSalecategoryRelationModel;
import com.zhaidou.product.service.MountProductLogService;
import com.zhaidou.product.service.MountProductService;
import com.zhaidou.product.service.ProductSalecategoryRelationService;
import com.zhaidou.product.service.ProductService;

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
	
	@Resource
	private MountProductDao mountProductDao;

	@Resource
	private ProductService productService;

	@Resource
	private ProductSalecategoryRelationService productSalecategoryRelationService;

	@Resource
	private MountProductLogService mountProductLogService;

	/**
	 * 
	 * @param relationList
	 * @param categoryCode
	 * @throws Exception
	 * 
	 * //@Transactional
	 */
	
	@Override
	public void updateOrBatchInsert(List<MountProductModel> relationList, String categoryCode) throws ZhaidouRuntimeException {
		try {
			List<String> productCodeList = getProductCodeList(relationList);
			Map<String, Long> productMap = productService.getMapByProductCodes(productCodeList);

			MountProductModel tmpMountProduct = new MountProductModel();
			tmpMountProduct.setCategoryCode(categoryCode);
			List<MountProductModel> mpList = this.mountProductDao.queryListByCategoryCode(tmpMountProduct);
			List<MountProductModel> updateList = new ArrayList<MountProductModel>(); // 已存在，需要更新的列表
			List<MountProductModel> insertList = new ArrayList<MountProductModel>();// 需要插入的列表
			List<MountProductModel> notExsitErrorList = new ArrayList<MountProductModel>();// 产品关联表中不存在的商品，无法插入的错误列表
			List<MountProductModel> delErrorList = new ArrayList<MountProductModel>();// 关系表中不存在，无法删除的错误列表

			Map<String, Long> mpIdPcodeMap = getIdPcodeMap(mpList);
			for (MountProductModel item : relationList) {
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
				for (MountProductModel uMp : updateList) {
					this.update(uMp);
				}
			}

			// 冗余表操作
			List<MountProductModel> okMPList = new ArrayList<MountProductModel>();
			if (insertList != null && insertList.size() > 0) {
				okMPList.addAll(insertList);
			}
			if (updateList != null && updateList.size() > 0) {
				okMPList.addAll(updateList);
			}
			if (okMPList != null && okMPList.size() > 0) {
				for (MountProductModel mp : okMPList) {
					// 冗余表：更新产品所挂载的分类有哪些
					ProductSalecategoryRelationModel productCategory = new ProductSalecategoryRelationModel();
					productCategory.setProductCode(mp.getProductCode());
					productCategory.setCategoryCodes(";" + categoryCode + ";");
					this.productSalecategoryRelationService.insertOrUpdate(productCategory, mp
							.getOptType());
				}
			}

			// 日志表操作
			List<MountProductLogModel> logList = new ArrayList<MountProductLogModel>();
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

	private List<MountProductLogModel> getMountProductLogList(List<MountProductModel> mpList, String failedReson) {
		if (mpList == null || mpList.size() == 0) {
			return null;
		}
		List<MountProductLogModel> logList = new ArrayList<MountProductLogModel>(mpList.size());
		for (MountProductModel relationModel : mpList) {
			MountProductLogModel mountProductLog = new MountProductLogModel();
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
	private List<String> getProductCodeList(List<MountProductModel> relationList) {
		if (relationList != null && relationList.size() > 0) {
			List<String> productCodeList = new ArrayList<String>(relationList.size());
			for (MountProductModel item : relationList) {
				productCodeList.add(item.getProductCode());
			}
			return productCodeList;
		} else {
			return null;
		}
	}
	
	private Map<String, Long> getIdPcodeMap(List<MountProductModel> relationList) {
		if (relationList != null && relationList.size() > 0) {
			Map<String,Long> mpMap = new HashMap<String,Long>(relationList.size());
			for (MountProductModel item : relationList) {
				mpMap.put(item.getProductCode(), item.getRelationId());
			}
			return mpMap;
		} else {
			return null;
		}
	}

	@Override
	public void update(MountProductModel mountProduct) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + mountProduct);
		}
		try {
			mountProductDao.update(mountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<MountProductModel> selectOpt1ByCategoryId(MountProductModel model)
			throws Exception {
		try {
			return mountProductDao.selectOpt1ByCategoryId(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据运营分类ID加载其下所有opt_type=1的商品,操作失败：",e);
			throw new Exception(e);
		}
	}

}
