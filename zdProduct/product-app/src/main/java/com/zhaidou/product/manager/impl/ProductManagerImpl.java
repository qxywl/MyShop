package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.log.LogUtil;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.model.ProductPriceLadderModel;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.model.mall.ProductModel;
import com.zhaidou.product.po.ProductPriceLadderPO;
import com.zhaidou.product.po.request.RequestProductInfoPO;
import com.zhaidou.product.service.ProductImageService;
import com.zhaidou.product.service.ProductMallViewService;
import com.zhaidou.product.service.ProductPriceLadderService;
import com.zhaidou.product.service.ProductService;
import com.zhaidou.product.service.ProductSkuViewService;
import com.zhaidou.product.util.ProductConstantUtil;

/**
 * 
 * */
@Service("productManager")
public class ProductManagerImpl implements ProductManager {
	private Logger logger = Logger.getLogger(ProductManagerImpl.class);

	@Resource(name = "productServiceMap")
	private Map<String, ProductService> productServiceMap;

	@Resource(name = "productSkuViewMap")
	private Map<String, ProductSkuViewService> productSkuViewMap;

	@Resource(name = "productImageMap")
	private Map<String, ProductImageService> productImageMap;

	@Resource
	private ProductPriceLadderService productPriceLadderService;

	@Resource
	ProductMallViewService productMallViewService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teshehui.product.manager.ProductManager#getProductList(java.lang.
	 * String, java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseObject getProductList(RequestObject requestObj) {
		logger.info("start_product_list:" + System.currentTimeMillis());
		ResponseObject result = null;
		try {
			Object data = null;
			String businessType = requestObj.getBusinessType();
			ProductService productService = productServiceMap.get(requestObj
					.getVersion());
			// 根据业务类型调用不同服务
			  if (ProductConstantUtil.BIZ_PRODUCT_MALL_CODE
					.equals(businessType)) {
				  
			} else {
				result = new ResponseObject(
						ProductConstantUtil.HANDEL_FAIL,
						ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE,
						"业务类型无法匹配：" + businessType, null);
			}

			if (data != null) {
				result = new ResponseObject(ProductConstantUtil.HANDEL_SUCCESS,
						null, null, data);
			}
		} catch (Exception e) {
			LogUtil.w(
					getClass().getSimpleName(),
					"getProductList illegal,v=" + requestObj.getVersion()
							+ " b=" + requestObj.getBusinessType() + ","
							+ e.getMessage(), e);
			ZhaidouRuntimeException te;
			if (e instanceof ZhaidouRuntimeException) {
				te = (ZhaidouRuntimeException) e;
			} else {
				te = new ZhaidouRuntimeException(
						ProductConstantUtil.BIZ_REQUEST_VERSION_ERROR_CODE001,
						"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
								+ requestObj.getBusinessType(), e);
			}
			result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
					te.getCode(), te.getMessage(), null);
		}
		logger.info("start_product_list:" + System.currentTimeMillis());
		return result;
	}

	@Override
	public ResponseObject getProductInfoDetail(RequestObject requestObj) {
		logger.info("start_product_detail:" + System.currentTimeMillis());
		ResponseObject result = null;
		try {
			Object data = null;
			String businessType = requestObj.getBusinessType();
			ProductService productService = productServiceMap.get(requestObj.getVersion());
			// 根据业务类型选择不同业务
			 if ("01".equals(businessType)||"02".equals(businessType)) {
				// RequestProductInfoPO requestModel = (RequestProductInfoPO)
				// requestObj;
				logger.info("start_product_mall_detail:"
						+ System.currentTimeMillis());
				result = productService.getMallProductInfoDetail(requestObj);
				logger.info("end_product_mall_detail:"
						+ System.currentTimeMillis());
			} else {
				result = new ResponseObject(
						ProductConstantUtil.HANDEL_FAIL,
						ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE,
						"业务类型无法匹配：" + businessType, null);
			}

			if (data != null) {
				result = new ResponseObject(ProductConstantUtil.HANDEL_SUCCESS,
						null, null, data);
			}
		} catch (Exception e) {
			LogUtil.w(getClass().getSimpleName(),
					"getProductInfoDetail illegal," + e.getMessage(), e);
			logger.error("getProductInfoDetail illegal," + e.getMessage(), e);
			ZhaidouRuntimeException te;
			if (e instanceof ZhaidouRuntimeException) {
				te = (ZhaidouRuntimeException) e;
			} else {
				te = new ZhaidouRuntimeException(
						ProductConstantUtil.BIZ_REQUEST_VERSION_ERROR_CODE001,
						"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
								+ requestObj.getBusinessType(), e);
			}
			result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
					te.getCode(), te.getMessage(), null);
		}
		logger.info("end_product_detail:" + System.currentTimeMillis());
		return result;

	}

	 
	@Override
	public ResponseObject getProductInfoForSKU(RequestObject requestObj) {

		logger.info("start_product_detail_forsku:" + System.currentTimeMillis());
		ResponseObject result = null;
		try {
			String businessType = requestObj.getBusinessType();
			ProductService productService = productServiceMap.get(requestObj
					.getVersion());
			// 根据业务类型选择不同业务
			if ("01".equals(businessType)||"02".equals(businessType)) {
				result = productService.getMallProductInfoBySKUCode(requestObj);
			} else {
				result = new ResponseObject(
						ProductConstantUtil.HANDEL_FAIL,
						ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE,
						"业务类型无法匹配：" + businessType, null);
			}

		} catch (Exception e) {
			LogUtil.w(getClass().getSimpleName(),
					"getProductInfoForSKU illegal," + e.getMessage(), e);
			ZhaidouRuntimeException te;
			if (e instanceof ZhaidouRuntimeException) {
				te = (ZhaidouRuntimeException) e;
			} else {
				te = new ZhaidouRuntimeException(
						ProductConstantUtil.BIZ_REQUEST_VERSION_ERROR_CODE001,
						"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
								+ requestObj.getBusinessType(), e);
			}
			result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
					te.getCode(), te.getMessage(), null);
		}
		logger.info("end_product_detail_forsku:" + System.currentTimeMillis());
		return result;
	}

	@Override
	public ResponseObject getProductInfoByCodes(RequestObject requestObj) {

		logger.info("start_product_by_productCodes:"
				+ System.currentTimeMillis());
		ResponseObject result = null;
		try {
			String businessType = requestObj.getBusinessType();
			ProductService productService = productServiceMap.get(requestObj
					.getVersion());
			// 根据业务类型选择不同业务
			if ("01".equals(businessType)||"02".equals(businessType)) {
				result = productService.getMallProductInfoByCodes(requestObj);
			} else {
				result = new ResponseObject(
						ProductConstantUtil.HANDEL_FAIL,
						ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE,
						"业务类型无法匹配：" + businessType, null);
			}

		} catch (Exception e) {
			LogUtil.w(getClass().getSimpleName(),
					"getProductInfoForSKU illegal," + e.getMessage(), e);
			ZhaidouRuntimeException te;
			if (e instanceof ZhaidouRuntimeException) {
				te = (ZhaidouRuntimeException) e;
			} else {
				te = new ZhaidouRuntimeException(
						ProductConstantUtil.BIZ_REQUEST_VERSION_ERROR_CODE001,
						"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
								+ requestObj.getBusinessType(), e);
			}
			result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
					te.getCode(), te.getMessage(), null);
		}
		logger.info("end_product_by_productCodes:" + System.currentTimeMillis());
		return result;
	}

	@Override
	public ResponseObject getProductPriceLadderByCodes(RequestObject requestObj) {
		// TODO Auto-generated method stub

		ResponseObject result = null;
		ProductPriceLadderModel productPriceLadder = null;
		Map<String, ProductPriceLadderPO> productPriceLadderMap = null;
		List<String> codeList = (List<String>) requestObj.getRequestParams();
		ProductPriceLadderPO productPriceLadderPO = null;
		try {
			if (codeList != null) {
				productPriceLadderMap = new HashMap<String, ProductPriceLadderPO>();
				logger.debug("require Params:" + JSON.toJSONString(requestObj));
				for (String code : codeList) {
					productPriceLadder = new ProductPriceLadderModel();
					productPriceLadder.setProductCode(code);
					productPriceLadder = productPriceLadderService
							.getProductPriceLadderByProductCode(productPriceLadder);
					productPriceLadderPO = new ProductPriceLadderPO();
					if (productPriceLadder != null) {
						productPriceLadderPO.setId(productPriceLadder.getId());
						productPriceLadderPO.setProductId(productPriceLadder
								.getProductId());
						productPriceLadderPO.setProductName(productPriceLadder
								.getProductName());
						productPriceLadderPO.setProductCode(productPriceLadder
								.getProductCode());
						productPriceLadderPO.setPriceLevel1(productPriceLadder
								.getPriceLevel1());
						productPriceLadderPO.setPriceLevel2(productPriceLadder
								.getPriceLevel2());
						productPriceLadderPO.setPriceLevel3(productPriceLadder
								.getPriceLevel3());
						productPriceLadderPO.setPriceLevel4(productPriceLadder
								.getPriceLevel4());
						productPriceLadderPO.setCreateTime(productPriceLadder
								.getCreateTime());
						productPriceLadderPO
								.setCreateUserName(productPriceLadder
										.getCreateUserName());
						productPriceLadderPO.setCreateBy(productPriceLadder
								.getCreateBy());
						productPriceLadderPO.setUpdateTime(productPriceLadder
								.getUpdateTime());
						productPriceLadderPO
								.setUpdateUserName(productPriceLadder
										.getUpdateUserName());
						productPriceLadderPO.setUpdateBy(productPriceLadder
								.getCreateBy());
						productPriceLadderPO.setIsDeleted(productPriceLadder
								.getIsDeleted());

					}
					productPriceLadderMap.put(code, productPriceLadderPO);
				}
				result = new ResponseObject(ResponseObject.CODE_SUCCESS_VALUE,
						"", "请求成功！", productPriceLadderMap);
				logger.debug("result Params:" + JSON.toJSONString(result));
			} else {
				result = new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
						"100025", "请求失败,请求参数为空！", null);
			}

		} catch (Exception e) {
			logger.error(e);
			result = new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
					"100026", "请求异常:" + e.getMessage(), null);
		}

		return result;
	}

	 /**
     * 根据ProductCodeList 查询是否支持贵就赔
     * 是否支持贵就赔  1  支持   2   不支持
     * @param requestObj
     * @return Map[ProductCode, IsPayForExpensive] IsPayForExpensiveMap
     */
	@Override
	public ResponseObject getProductIsPayForExpensiveByCodes(
			RequestObject requestObj) {
		// TODO Auto-generated method stub

		ResponseObject result = null;
		ProductModel productModel = null;
		List<String> productCodeList = (List<String>) requestObj
				.getRequestParams();
		List<ProductMallViewModel> ProductMallViewModelList = new ArrayList<ProductMallViewModel>();
		String requestPamas = "";
		try {
			requestPamas = JSON.toJSONString(productCodeList);
		} catch (Exception e) {
		}
		try {

			logger.debug("查询商品是否支持贵就赔请求参数:" + requestPamas);
			if (productCodeList != null && productCodeList.size() >= 1) {
				ProductMallViewModelList = productMallViewService
						.getProductMallViewByProductCodes(productCodeList);
				Map<String, Integer> IsPayForExpensiveMap=new HashMap<String, Integer>();
				if(ProductMallViewModelList!=null && ProductMallViewModelList.size()>0)
				{
					for(ProductMallViewModel productMallView:ProductMallViewModelList)
					{
						IsPayForExpensiveMap.put(productMallView.getProductCode(), productMallView.getIsSupportExpensive());
					}
				}
				

				try {
					logger.debug("查询商品是否支持贵就赔返回参数:"
							+ JSON.toJSONString(IsPayForExpensiveMap));
				} catch (Exception e) {
				}
				return new ResponseObject(ResponseObject.CODE_SUCCESS_VALUE,
						null, "请求成功!", IsPayForExpensiveMap);
			} else {
				return new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
						"28801001", "请求失败,请求参数为空！", null);
			}

		}

		catch (Exception e) {
			logger.error(e);
			result = new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
					"48801001", "请求异常:" + e.getMessage(), null);
		}

		return result;
	}
}
