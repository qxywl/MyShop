/*
 * 文 件 名:  ProductPriceLadderManagerImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-27
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.redis.RedisCache;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductPriceLadderManager;
import com.zhaidou.product.info.model.ChanglogModel;
import com.zhaidou.product.info.model.ProductPriceLadderModel;
import com.zhaidou.product.info.service.ChanglogService;
import com.zhaidou.product.info.service.ProductOperateService;
import com.zhaidou.product.info.service.ProductPriceLadderService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author mingbao
 * @version [版本号, 2015-08-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("productPriceLadderManager")
public class ProductPriceLadderManagerImpl implements ProductPriceLadderManager {
	private static final Log logger = LogFactory
			.getLog(ProductPriceLadderManagerImpl.class);

	@Resource
	private ProductPriceLadderService productPriceLadderService;

	@Resource
	private ProductOperateService productOperateService;

	@Resource
	private ChanglogService changlogService;

	@Resource
	private RedisCache redisCache;

	public void addProductPriceLadder(
			ProductPriceLadderModel productPriceLadderModel) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->"
					+ JSONUtil.toString(productPriceLadderModel));
		}
		if (productPriceLadderModel != null) {
			// 添加数据到 product_price_ladder表
			redisCache.deleteObject("product_price_ladder_price"+productPriceLadderModel.getProductCode());
			productPriceLadderService
					.addProductPriceLadder(productPriceLadderModel);
			
		}
		
		
	}

	public void updateProductPriceLadder(
			ProductPriceLadderModel productPriceLadderModel,
			Map<String, Object> user) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->"
					+ JSON.toJSONString(productPriceLadderModel));
		}
		if (productPriceLadderModel != null) {
			try {

				ProductPriceLadderModel oldPriceLadderModel = null;
				if (productPriceLadderModel != null
						&& productPriceLadderModel.getId() != null) {
					oldPriceLadderModel = new ProductPriceLadderModel();
					oldPriceLadderModel = productPriceLadderService
							.getProductPriceLadderById(productPriceLadderModel);

				}

				try {

					Createchangelog(oldPriceLadderModel,
							productPriceLadderModel, user);

				} catch (Exception e) {
					logger.error("创建changlog失败:", e);
				}

				productPriceLadderService
						.updateProductPriceLadder(productPriceLadderModel);
				redisCache.deleteObject("product_price_ladder_price"+productPriceLadderModel.getProductCode());
		
			} catch (Exception e) {
				logger.error("更新ProductPriceLadder异常", e);
				throw new ZhaidouRuntimeException(e);
			}
		}

	}

	private boolean Createchangelog(
			ProductPriceLadderModel oldPriceLadderModel,
			ProductPriceLadderModel newPriceLadderModel,
			Map<String, Object> user) throws Exception {

		if (oldPriceLadderModel == null || newPriceLadderModel == null) {
			return false;

		}

		if (oldPriceLadderModel.getProductCode() == null
				|| newPriceLadderModel.getProductCode() == null) {
			return false;
		}
		List<ChanglogModel> changlogModelList = new ArrayList<ChanglogModel>();
		ChanglogModel changlogModel = null;
		if (newPriceLadderModel.getPriceLevel1() != null) {
			changlogModel = new ChanglogModel();
			changlogModel.setNewValue(newPriceLadderModel.getPriceLevel1()
					.toString());
			if (oldPriceLadderModel.getPriceLevel1() == null) {

			} else if (newPriceLadderModel.getPriceLevel1().intValue() != oldPriceLadderModel
					.getPriceLevel1().intValue()) {
				changlogModel.setColumnName("price_level_1");
				changlogModel.setOldValue(oldPriceLadderModel.getPriceLevel1()
						.toString());
				changlogModelList.add(changlogModel);
			}

		}

		if (newPriceLadderModel.getPriceLevel2() != null) {
			changlogModel = new ChanglogModel();
			changlogModel.setNewValue(newPriceLadderModel.getPriceLevel2()
					.toString());
			if (oldPriceLadderModel.getPriceLevel2() == null) {

			} else if (newPriceLadderModel.getPriceLevel2().intValue() != oldPriceLadderModel
					.getPriceLevel2().intValue()) {
				changlogModel.setColumnName("price_level_2");
				changlogModel.setOldValue(oldPriceLadderModel.getPriceLevel2()
						.toString());
				changlogModelList.add(changlogModel);
			}

		}

		if (newPriceLadderModel.getPriceLevel3() != null) {
			changlogModel = new ChanglogModel();
			changlogModel.setNewValue(newPriceLadderModel.getPriceLevel3()
					.toString());
			if (oldPriceLadderModel.getPriceLevel3() == null) {

			} else if (newPriceLadderModel.getPriceLevel3().intValue() != oldPriceLadderModel
					.getPriceLevel3().intValue()) {
				changlogModel.setColumnName("price_level_3");
				changlogModel.setOldValue(oldPriceLadderModel.getPriceLevel3()
						.toString());
				changlogModelList.add(changlogModel);
			}

		}

		if (newPriceLadderModel.getPriceLevel4() != null) {
			changlogModel = new ChanglogModel();
			changlogModel.setNewValue(newPriceLadderModel.getPriceLevel4()
					.toString());
			if (oldPriceLadderModel.getPriceLevel4() == null) {

			} else if (newPriceLadderModel.getPriceLevel4().intValue() != oldPriceLadderModel
					.getPriceLevel4().intValue()) {
				changlogModel.setColumnName("price_level_4");
				changlogModel.setOldValue(oldPriceLadderModel.getPriceLevel4()
						.toString());
				changlogModelList.add(changlogModel);
			}

		}

		for (ChanglogModel log : changlogModelList) {
			log.setUpdateBy(Long.parseLong(user.get("userId").toString()));
			log.setUpdateUserName(user.get("userName").toString());
			log.setCreateBy(Long.parseLong(user.get("userId").toString()));
			log.setCreateUserName(user.get("userName").toString());
			log.setRecordId(newPriceLadderModel.getProductId());
			log.setRecordCode(newPriceLadderModel.getProductCode());
			log.setTableName("t_product_price_ladder");
			changlogService.addChanglog(log);
		}
		return false;

	}

	public ProductPriceLadderModel getProductPriceLadderById(String id) {
		ProductPriceLadderModel result = null;
		if (logger.isDebugEnabled()) {
			logger.debug("--getProductPriceLadderById Params-->" + id);
		}
		if (!StringUtils.isEmpty(id)) {
			try {
				ProductPriceLadderModel productPriceLadderModel = new ProductPriceLadderModel();
				productPriceLadderModel.setId(Long.parseLong(id));
				result = productPriceLadderService
						.getProductPriceLadderById(productPriceLadderModel);
			} catch (Exception e) {
				logger.error("查询ProductPriceLadder异常", e);
				throw new ZhaidouRuntimeException(e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--getProductPriceLadderById result-->"
					+ JSON.toJSONString(result));
		}
		return result;
	}

	public Map<String, Object> getProductPriceLadder(
			ProductPriceLadderModel productPriceLadderModel, Page page) {

		Map<String, Object> result = new HashMap<String, Object>();
		if (logger.isDebugEnabled()) {
			logger.debug("--getProductPriceLadder Params-->"
					+ JSON.toJSONString(productPriceLadderModel));
		}
		try {
			if (productPriceLadderModel != null) {
				List<ProductPriceLadderModel> list = productPriceLadderService
						.getProductPriceLadder(productPriceLadderModel, page);
				result.put("page", page);
				result.put("list", list);
			}
		} catch (Exception e) {
			logger.error("查询ProductPriceLadder异常", e);
			throw new ZhaidouRuntimeException(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--getProductPriceLadder result-->"
					+ JSON.toJSONString(result));
		}
		return result;
	}

	public void deleteById(String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("--deleteById Params-->" + id);
		}
		if (!StringUtils.isEmpty(id)) {
			try {
				ProductPriceLadderModel productPriceLadderModel = new ProductPriceLadderModel();
				productPriceLadderModel.setId(Long.parseLong(id));
				productPriceLadderModel = productPriceLadderService
						.getProductPriceLadderById(productPriceLadderModel);
				redisCache.deleteObject("product_price_ladder_price"
						+ productPriceLadderModel.getProductCode());

				productPriceLadderService.deleteById(productPriceLadderModel);

			} catch (Exception e) {
				logger.error("删除ProductPriceLadder异常", e);
				throw new ZhaidouRuntimeException(e);
			}
		}
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		if (logger.isDebugEnabled()) {
			logger.debug("--deleteByIds Params-->" + ids);
		}
		if (!ids.isEmpty()) {
			try {
				
				List<ProductPriceLadderModel> priceLadderList = productPriceLadderService
						.getProductPriceLadderByIdList(ids);

				for (ProductPriceLadderModel productPriceLadderModel : priceLadderList) {
					redisCache.deleteObject("product_price_ladder_price"
							+ productPriceLadderModel.getProductCode());
				}
				
				productPriceLadderService.deleteByIds(ids);

				

			} catch (Exception e) {
				logger.error("批量删除ProductPriceLadder异常", e);
				throw new ZhaidouRuntimeException(e);
			}
		}

	}

	@Override
	public void addProductPriceLadder(
			List<ProductPriceLadderModel> productOperateList,
			Map<String, Object> user) throws Exception {
		// 批量添加或者修改 商品加价率
		// 如果添加失败 并且错误是 重复添加，则修改商品加价率
		for (ProductPriceLadderModel productPriceLadderModel : productOperateList) {
			if (logger.isDebugEnabled()) {
				logger.debug("--addProductPriceLadder Params-->"
						+ JSONUtil.toString(productPriceLadderModel));
			}
			if (productPriceLadderModel != null) {
				productPriceLadderModel.setPriceLevel1(null);// 暂时不修改一级加价率
				try {
					
					redisCache.deleteObject("product_price_ladder_price"+productPriceLadderModel.getProductCode());
					productPriceLadderService
							.addProductPriceLadder(productPriceLadderModel);
					

				} catch (Exception e) {
					if (e.getCause() != null
							&& e.getCause().toString() != null
							&& e.getCause()
									.toString()
									.startsWith(
											"org.springframework.dao.DuplicateKeyException")) {
						ProductPriceLadderModel oldPriceLadderModel = null;
						if (productPriceLadderModel != null
								&& productPriceLadderModel.getProductCode() != null) {
							oldPriceLadderModel = new ProductPriceLadderModel();
							oldPriceLadderModel = productPriceLadderService
									.getProductPriceLadderByProductCode(productPriceLadderModel);
							productPriceLadderModel.setId(oldPriceLadderModel
									.getId());

						}
						try {
							Createchangelog(oldPriceLadderModel,
									productPriceLadderModel, user);
						} catch (Exception e1) {
							logger.error("创建changlog失败:", e1);
						}
						productPriceLadderService
								.updateProductPriceLadder(productPriceLadderModel);
						
						redisCache.deleteObject("product_price_ladder_price"+productPriceLadderModel.getProductCode());

					} else {
						throw new ZhaidouRuntimeException(e);
					}

				}
			}
		}
	}

}
