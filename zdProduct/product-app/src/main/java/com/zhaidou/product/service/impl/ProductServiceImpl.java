/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaidou.framework.common.CommonConstant;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.model.ResultSetModel;
import com.zhaidou.framework.service.impl.BaseServiceImpl;
import com.zhaidou.framework.util.http.HttpClientUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.framework.util.string.StringUtil;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.imageservice.model.request.GetImagePathBatchRequestPO;
import com.zhaidou.imageservice.model.response.GetImageBatchResponsePO;
import com.zhaidou.imageservice.model.response.GetImageResponsePO;
import com.zhaidou.product.dao.ProductAttributeDao;
import com.zhaidou.product.dao.ProductMallViewDao;
import com.zhaidou.product.model.ProductAttributeModel;
import com.zhaidou.product.model.ProductSkuViewModel;
import com.zhaidou.product.model.base.ProductCountModel;
import com.zhaidou.product.model.base.ProductInfoModel;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.model.base.ProductOperateModel;
import com.zhaidou.product.model.base.ProductStockModel;
import com.zhaidou.product.model.base.SupplierShopModel;
import com.zhaidou.product.model.mall.GoodDatas;
import com.zhaidou.product.model.mall.GoodList;
import com.zhaidou.product.model.mall.ProductMallModel;
import com.zhaidou.product.model.mall.ProductMapSkuCodeModel;
import com.zhaidou.product.model.mall.ProductModel;
import com.zhaidou.product.po.AttributePO;
import com.zhaidou.product.po.ProductInfoPO;
import com.zhaidou.product.po.ProductMallPO;
import com.zhaidou.product.po.ProductMallSkuPO;
import com.zhaidou.product.po.ProductSKUPO;
import com.zhaidou.product.po.base.ProductImagePO;
import com.zhaidou.product.po.mall.MallProductInfoPO;
import com.zhaidou.product.po.mall.MallProductListInfoPO;
import com.zhaidou.product.po.request.RequestMallProductPO;
import com.zhaidou.product.po.request.RequestProductInfoPO;
import com.zhaidou.product.po.request.RequestProductListPO;
import com.zhaidou.product.service.ProductCountService;
import com.zhaidou.product.service.ProductImageService;
import com.zhaidou.product.service.ProductInfoService;
import com.zhaidou.product.service.ProductService;
import com.zhaidou.product.service.ProductSkuViewService;
import com.zhaidou.product.service.ProductStockService;
import com.zhaidou.product.service.SupplierShopService;
import com.zhaidou.product.util.APIConfig;
import com.zhaidou.product.util.ProductConstantUtil;

/**
 * 商品管理
 * 
 * @version Revision History
 * 
 * <pre></pre>
 * 
 *          </pre> Author Version Date Changes liq 1.0 2015-1-19 Created
 **/
@Service("productServiceV001")
public class ProductServiceImpl extends BaseServiceImpl implements
		ProductService {
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductMallViewDao productMallViewDao;

	@Resource(name = "productSkuViewMap")
	private Map<String, ProductSkuViewService> productSkuViewMap;

	@Resource(name = "productImageMap")
	private Map<String, ProductImageService> productImageMap;

	@Resource(name = "productCountMap")
	private Map<String, ProductCountService> productCountMap;

	@Resource(name = "productStockServiceMap")
	private Map<String, ProductStockService> productStockServiceMap;

	@Resource(name = "supplierShopServiceMap")
	private Map<String, SupplierShopService> supplierShopMap;

	@Resource(name = "productInfoServiceMap")
	private Map<String, ProductInfoService> productInfoMap;
	
	
	@Autowired
	private ProductAttributeDao productAttributeDao;

	@Resource
	private ImageSearchManager imageSearch;

	private static long cacheTime = 30 * 60 * 1000;
	private static Long MAIN_SKU_LABEL = 1L;// 标记主sku标记

	@Override
	public ProductMallViewModel getProductMallViewBySkuCode(String skuCode,
			String type) throws Exception {
		ProductMallViewModel model = null;
		model = productMallViewDao.getProductMallViewBySkuCode(skuCode, type);
		return model;
	}

	@Override
	public ProductMallViewModel getProductMallViewByProductId(Long productId,
			String type) throws Exception {
		ProductMallViewModel resultModel = null;
		resultModel = productMallViewDao.getProductMallViewByProductId(
				productId, type);
		return resultModel;
	}

	@Override
	public List<ProductMallViewModel> getProductMallViewByProductIdList(
			List<Long> productIdList, String type) throws Exception {
		ProductMallViewModel queryModel = new ProductMallViewModel();
		if (productIdList != null && productIdList.size() > 0) {
			queryModel.setProductIdList(productIdList);
		}
		if (type != null) {
			queryModel.setType(type);
		}
		List<ProductMallViewModel> resultList = null;
		resultList = productMallViewDao
				.getProductMallViewByProductIdList(queryModel);
		return resultList;
	}

	@Override
	public List<ProductMallViewModel> getProductMallViewByProductCodeList(
			List<String> productCodeList, String type) throws Exception {
		ProductMallViewModel queryModel = new ProductMallViewModel();
		if (productCodeList != null && productCodeList.size() > 0) {
			queryModel.setProductCodeList(productCodeList);
		}
		if (type != null) {
			queryModel.setType(type);
		}
		List<ProductMallViewModel> resultList = null;
		resultList = productMallViewDao
				.getProductMallViewByProductCodeList(queryModel);
		return resultList;
	}

	@Override
	public ProductMallViewModel getMallProductMallViewByProductId(Long productId)
			throws Exception {
		ProductMallViewModel resultModel = null;
		resultModel = productMallViewDao.getProductMallViewByProductId(
				productId, ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		return resultModel;
	}

	@Override
	public ProductMallViewModel getMallProductMallViewByProductCode(
			String productCode) throws Exception {
		ProductMallViewModel resultModel = null;
		resultModel = productMallViewDao.getProductMallViewByProductCode(
				productCode, ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		return resultModel;
	}

	@Override
	public ProductMallViewModel getMallProductMallViewBySkuCode(String skuCode)
			throws Exception {
		ProductMallViewModel model = getProductMallViewBySkuCode(skuCode,
				ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		return model;
	}

	@Override
	public List<ProductMallViewModel> getMallProductMallViewBySkuCodeList(
			List<String> skuCodeList) throws Exception {
		List<ProductMallViewModel> list = productMallViewDao
				.getProductMallViewBySKU(skuCodeList,
						ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		return list;
	}

	@Override
	public List<ProductMallViewModel> getMallProductMallViewList(
			ProductMallViewModel mallView, Page page) throws Exception {
		List<ProductMallViewModel> modelList = null;
		mallView.setType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);// 设置分类查询条件
		long count = productMallViewDao.countListPage(mallView);
		if (count > 0) {
			page.setTotalCount(count);// 设置分页信息
			modelList = (List<ProductMallViewModel>) productMallViewDao
					.queryListPage(mallView, page.getPageNum(),
							page.getNumPerPage());
		}
		return modelList;
	};

	// TODO
	public ResponseObject getMallProductList(RequestObject requestObj) {
		ResponseObject responseObject = null;
		RequestMallProductPO requestPO = null;
		Page page = new Page();// 设置分页查询
		try {

			requestPO = (RequestMallProductPO) requestObj.getRequestParams();
			ProductMallViewModel mallView = new ProductMallViewModel();

			page.setPageNum(requestPO.getPage());
			page.setNumPerPage(requestPO.getPageSize());

			List<ProductMallViewModel> list = getMallProductMallViewList(
					mallView, page);
			List<ProductMallPO> poList = mallModelList2PO(list);
			responseObject = new ResponseObject(poList);
		} catch (Exception e) {
			logger.error(
					"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
							+ requestObj.getBusinessType() + "page="
							+ requestPO.getPage() + "pageNum="
							+ requestPO.getPageSize(), e);
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE004,
					"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
							+ requestObj.getBusinessType() + "page="
							+ requestPO.getPage() + "pageNum="
							+ requestPO.getPageSize() + "e=" + e.getMessage());
		}

		return responseObject;
	}

	/***
	 * 商品查询,分以下步骤： 1、先从缓存中查商品以及相关的模块信息，如果有则利用缓存中得到的数据
	 * 2、如果缓存中没有商品，则从product_mall_view 大横表中获取数据，利用该数据，
	 * 拼接返回给用户请求，并且拆分成原来缓存中的数据结构，存入到缓存中 3、如果product_mall_view 中没有获取到该数据，则返回数据不存在
	 * 
	 * 获取订单关联到的数据sku(t_product_sku_view),图片(t_product_image),
	 * 统计(t_product_count),库存(product_stock.t_product_stock 跨库获取)
	 * 
	 * */
	// TODO
	@SuppressWarnings("unused")
	@Override
	public ResponseObject getMallProductInfoDetail(RequestObject requestObj) {
		ResponseObject responseObject = null;
		ProductMallViewModel resultModel = null;
		try {
			String businessType = requestObj.getBusinessType();
			String version = requestObj.getVersion();

			RequestProductInfoPO requestPO = (RequestProductInfoPO) requestObj
					.getRequestParams();
			if (requestPO == null
					|| (StringUtil.isEmpty(requestPO.getProductCode()) && requestPO
							.getProductId() == null)) {

				responseObject = new ResponseObject(
						ProductConstantUtil.BIZ_MALL_DETAIL_DATA_ERROR_CODE001,
						"请求参数必须包含商品编号(productCode) 或者(productId)");
				return responseObject;
			}
			String productCode = requestPO.getProductCode();
			resultModel = getProMallViewByCacheOrDB(productCode,
					requestPO.getProductId());

			ProductInfoPO<MallProductInfoPO> productInfoPO = null;
			if (resultModel != null) {

				productCode = resultModel.getProductCode();// 通过productId 获取时会报错
				resultModel.setProductShelves(getProductShelves(productCode));// 从缓存中获取上下架信息

				ProductSkuViewService productSkuViewService = productSkuViewMap
						.get(requestObj.getVersion());
				ProductImageService productImageService = productImageMap
						.get(requestObj.getVersion());
				ProductCountService productCountService = productCountMap
						.get(requestObj.getVersion());
				SupplierShopService supplierShopService = supplierShopMap
						.get(requestObj.getVersion());
				ProductInfoService productInfoService = productInfoMap
						.get(requestObj.getVersion());

				// 获取skuView列表
				List<ProductSkuViewModel> skuModelList = getProductSkuFromCacheOrDB(
						productSkuViewService, resultModel.getProductId(),
						resultModel.getProductCode());
				List<String> skuCodeList = getSkuCodeListBySkuModelList(skuModelList);

				// 用户操作统计 浏览，收藏，点赞， 统计。。
				ProductCountModel countModel = (ProductCountModel) this
						.getCachedClient().getObject(
								"product_count_" + productCode);
				if (countModel == null) {
					countModel = productCountService
							.getProductCountByProdId(resultModel.getProductId());
					this.getCachedClient().cacheObject(
							"product_count_" + productCode, countModel,
							cacheTime);
				}

				// 获取skuCode 对应的image
				Map<String, List<ProductImagePO>> skuCodeMapImgList = getImageListByCodeList(
						skuCodeList, 1L);
				List<ProductMallSkuPO> skulist = skuModelList2PO(skuModelList);

				// 店铺信息
				SupplierShopModel shopModel = (SupplierShopModel) this
						.getCachedClient().getObject(
								"supplier_shop_" + resultModel.getShopId());
				if (shopModel == null) {
					shopModel = supplierShopService.getSupplierById(resultModel
							.getShopId());
					this.getCachedClient().cacheObject(
							"supplier_shop_" + resultModel.getShopId(),
							shopModel, cacheTime);
				}

				// 描述信息详情
				ProductInfoModel productInfoModel = (ProductInfoModel) this
						.getCachedClient().getObject(
								"product_info_" + productCode);
				if (productInfoModel == null) {
					productInfoModel = productInfoService
							.getProInfoByProductId(resultModel.getProductId());
					if (productInfoModel != null) {
						this.getCachedClient().cacheObject(
								"product_info_" + productCode,
								productInfoModel, cacheTime);
					}
				}

				if (productInfoModel != null) {
					String clientType = requestObj.getClientType();
					if (CommonConstant.CLIENT_TYPE_ENUM.PC.getKey().equals(
							clientType)) {// pc 端
						resultModel.setIntegrityDesc(productInfoModel
								.getPcProductInfo());
					} else if (CommonConstant.CLIENT_TYPE_ENUM.WAP.getKey()
							.equals(clientType)) {
						resultModel.setIntegrityDesc(productInfoModel
								.getAppProductInfo());
					} else {// app 端
						resultModel.setIntegrityDesc(ProductConstantUtil.MALL_PRODUCT_DESCRIPTION_PREFIX+productInfoModel.getAppProductInfo());
					}
				}

				productInfoPO = mallModel2ProudctInfoPO(resultModel,
						countModel, skuModelList, skuCodeMapImgList, shopModel);
			}

			if (productInfoPO != null) {
				responseObject = new ResponseObject(productInfoPO);
			} else {
				responseObject = new ResponseObject("10104002",
						"获取商城信息失败;未能获得有效数据!");
			}

		} catch (Exception e) {
			logger.error("请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
					+ requestObj.getBusinessType() + "e=" + e.getMessage(), e);
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE004,
					"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
							+ requestObj.getBusinessType() + "e="
							+ e.getMessage());
		}
		return responseObject;
	}

	@Override
	public ResponseObject getMallProductInfoBySKUCode(RequestObject requestObj) {
		ResponseObject responseObject = new ResponseObject();

		RequestProductInfoPO requestPO = (RequestProductInfoPO) requestObj
				.getRequestParams();
		if (requestPO == null
				|| ((requestPO.getProductSKUCodes() == null || requestPO
						.getProductSKUCodes().size() < 1) && (requestPO
						.getProductSKUIds() == null || requestPO
						.getProductSKUIds().size() < 1))) {
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_DETAIL_DATA_ERROR_CODE001,
					"请求参数为空 ,必须包含productSKUCodes 或 productSKUIds");
			return responseObject;
		}

		ProductSkuViewService productSkuViewService = productSkuViewMap
				.get(requestObj.getVersion());
		ProductCountService productCountService = productCountMap
				.get(requestObj.getVersion());
		SupplierShopService supplierShopService = supplierShopMap
				.get(requestObj.getVersion());
		ProductInfoService productInfoService = productInfoMap.get(requestObj
				.getVersion());

		try {

			List<ProductSkuViewModel> skuViewModelList = null;

			// 1.如果skuCodeList 不为空则 通过skuCodeList查询，否则通过skuIdList
			if (requestPO.getProductSKUCodes() != null
					&& requestPO.getProductSKUCodes().size() > 0) {
				skuViewModelList = getProSkuDataBySkuCodes(
						requestPO.getProductSKUCodes(), productSkuViewService);
			} else {
				skuViewModelList = getProSkuDataBySkuIds(
						requestPO.getProductSKUIds(), productSkuViewService);
			}

			// 将sku根据productID分类 key--productId,value---对应的skuViewModeList
			Map<Long, List<ProductSkuViewModel>> mapProductIdAndSkuModel = groupSkuViewModel(skuViewModelList);
			List<String> proCodeList = getProductCodeListBySkuViewModelList(skuViewModelList);

			Set<Long> productIdSet = mapProductIdAndSkuModel.keySet();
			List<Long> productIdList = new ArrayList<Long>();
			for (Long productId : productIdSet) {
				productIdList.add(productId);
			}

			// 2.获取productMallViewModel 数据，优先从缓存中，其次是从数据库获取
			// List<ProductMallViewModel> productMallModelList =
			// fillProMallDataPart(productIdList,
			// productInfoService,requestObj.getClientType());
			List<ProductMallViewModel> productMallModelList = fillProMallDataPartByCodes(
					proCodeList, productInfoService, requestObj.getClientType());

			List<Long> shopIdList = new ArrayList<Long>();
			for (ProductMallViewModel mallModel : productMallModelList) {
				shopIdList.add(mallModel.getShopId());
			}

			// 3.获取skuCode 图片
			List<String> skuCodeList = getSkuCodeListBySkuModelList(skuViewModelList);
			Map<String, List<ProductImagePO>> mapSkuCodeAndImagePo = getImageListByCodeList(
					skuCodeList, 1L);

			// 4.统计详细
			// List<ProductCountModel> countModelList =
			// getProductCountFromCacheOrDB(productCountService,productIdList);
			List<ProductCountModel> countModelList = getProductCountFromCacheOrDB(
					productCountService, productMallModelList);

			// 5.供应商店铺
			List<SupplierShopModel> shopList = getSupplierFromCacheOrDB(
					shopIdList, supplierShopService);
			Map<Long, SupplierShopModel> mapProAndSupShopModel = mapProIdAndSupplierShopModel(
					productMallModelList, shopList);// 组合商品和提供商的map<ProductId,SupplierShopModel>

			// 6.组装数据
			List<ProductInfoPO<MallProductInfoPO>> prductInfoList = wrapeProductModelList(
					productMallModelList, mapProductIdAndSkuModel,
					mapSkuCodeAndImagePo, countModelList, mapProAndSupShopModel);

			responseObject = wrapResultSetByProInfoList(prductInfoList);

		} catch (Exception e) {
			logger.error("请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
					+ requestObj.getBusinessType() + "e=" + e.getMessage(), e);
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE004,
					"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
							+ requestObj.getBusinessType() + "e="
							+ e.getMessage());
		}

		return responseObject;
	}

	@Override
	public ResponseObject getMallProductInfoByCodes(RequestObject requestObj) {

		ResponseObject responseObject = null;

		RequestProductInfoPO requestPO = (RequestProductInfoPO) requestObj
				.getRequestParams();
		List<String> proCodesList = requestPO.getProductCodes();
		if (requestPO == null
				|| (proCodesList == null || proCodesList.size() < 1)) {
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_DETAIL_DATA_ERROR_CODE001,
					"请求参数为空 ,必须包含productCodes");
			return responseObject;
		}

		ProductSkuViewService productSkuViewService = productSkuViewMap
				.get(requestObj.getVersion());
		ProductCountService productCountService = productCountMap
				.get(requestObj.getVersion());
		SupplierShopService supplierShopService = supplierShopMap
				.get(requestObj.getVersion());
		ProductInfoService productInfoService = productInfoMap.get(requestObj
				.getVersion());

		try {

			List<Long> productIdList = new ArrayList<Long>();
			List<Long> shopIdList = new ArrayList<Long>();

			// 1.获取productMallViewModel 数据，优先从缓存中，其次是从数据库获取
			List<ProductMallViewModel> productMallModelList = fillProMallDataPartByCodes(
					proCodesList, productInfoService,
					requestObj.getClientType());

			// 2.sku图片
			Map<String, List<ProductImagePO>> skuCodeMapImagePo = new HashMap<String, List<ProductImagePO>>();

			// 3.获取sku集合, 将sku根据productID分类
			// key--productId,value---对应的skuViewModeList
			Map<Long, List<ProductSkuViewModel>> proIdMapSkuModelList = new HashMap<Long, List<ProductSkuViewModel>>();
			for (ProductMallViewModel mallViewModel : productMallModelList) {
				productIdList.add(mallViewModel.getProductId());
				shopIdList.add(mallViewModel.getShopId());
				List<ProductSkuViewModel> skuViewList = getProductSkuFromCacheOrDB(
						productSkuViewService, mallViewModel.getProductId(),
						mallViewModel.getProductCode());
				if (skuViewList != null) {
					proIdMapSkuModelList.put(mallViewModel.getProductId(),
							skuViewList);

					// 获取skuCode 图片
					List<String> skuCodeList = getSkuCodeListBySkuModelList(skuViewList);
					Map<String, List<ProductImagePO>> skuCodeMapImagePoTmp = getImageListByCodeList(
							skuCodeList, 1L);
					skuCodeMapImagePo.putAll(skuCodeMapImagePoTmp);
				}
			}

			// 4.统计详细
			List<ProductCountModel> countModelList = getProductCountFromCacheOrDB(
					productCountService, productMallModelList);

			// 5.供应商店铺
			List<SupplierShopModel> shopList = getSupplierFromCacheOrDB(
					shopIdList, supplierShopService);
			Map<Long, SupplierShopModel> mapProAndSupShopModel = mapProIdAndSupplierShopModel(
					productMallModelList, shopList);// 组合商品和提供商的map<ProductId,SupplierShopModel>

			// 6.组装数据
			List<ProductInfoPO<MallProductInfoPO>> prductInfoList = wrapeProductModelList(
					productMallModelList, proIdMapSkuModelList,
					skuCodeMapImagePo, countModelList, mapProAndSupShopModel);

			responseObject = wrapResultSetByProInfoList(prductInfoList);

		} catch (Exception e) {
			logger.error("请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
					+ requestObj.getBusinessType() + "e=" + e.getMessage(), e);
			responseObject = new ResponseObject(
					ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE004,
					"请求参数版本号或业务线错误:v=" + requestObj.getVersion() + " b="
							+ requestObj.getBusinessType() + "e="
							+ e.getMessage());
		}

		return responseObject;
	}

	/**
	 * 构造返回对象
	 * */
	private ResponseObject wrapResultSetByProInfoList(
			List<ProductInfoPO<MallProductInfoPO>> prductInfoList) {
		ResponseObject responseObject = null;
		if (prductInfoList != null && prductInfoList.size() > 0) {
			ResultSetModel<ProductInfoPO<MallProductInfoPO>> resultSet = new ResultSetModel<ProductInfoPO<MallProductInfoPO>>();
			resultSet.setItems(prductInfoList);
			resultSet.setTotalCount(prductInfoList.size());
			resultSet.setBeginRow(1);
			responseObject = new ResponseObject(resultSet);
		} else {
			responseObject = new ResponseObject("10104002",
					"获取商城产品信息失败;未能获得有效数据!");
		}
		return responseObject;
	}

	/**
	 * 通过productIdList 获取ProductCountModel对象
	 * 获取ProductCountModel,先从缓存中获取，如果缓存中有，则用缓存中的， 如果缓存中没有则使用数据库中
	 * 
	 * @throws Exception
	 * 
	 * */
	private List<ProductCountModel> getProductCountFromCacheOrDB(
			ProductCountService productCountService,
			List<ProductMallViewModel> productMallModelList) throws Exception {

		List<ProductCountModel> productCountList = new ArrayList<ProductCountModel>();
		List<ProductCountModel> queryResultList = null;

		List<Long> queryIdList = new ArrayList<Long>();

		for (ProductMallViewModel prodMallViewModel : productMallModelList) {

			ProductCountModel countModel = (ProductCountModel) this
					.getCachedClient().getObject(
							"product_count_"
									+ prodMallViewModel.getProductCode());
			if (countModel != null) {
				productCountList.add(countModel);
				// productMallModelList.remove(prodMallViewModel);
			} else {
				queryIdList.add(prodMallViewModel.getProductId());
			}
		}

		if (queryIdList.size() > 0) {
			queryResultList = productCountService
					.getProCountByProductIdList(queryIdList);
			if (queryResultList != null && queryResultList.size() > 0) {
				for (ProductCountModel resultShop : queryResultList) {
					for (ProductMallViewModel prodMallViewModel : productMallModelList) {
						if (resultShop.getProductId() != null
								&& resultShop.getProductId() == prodMallViewModel
										.getProductId()) {
							this.getCachedClient()
									.cacheObject(
											"product_count_"
													+ prodMallViewModel
															.getProductCode(),
											resultShop, cacheTime);
							productCountList.add(resultShop);
						}

					}

				}
			}

		}

		return productCountList;

	}

	/**
	 * 通过productCode 和productId 获取ProductMallViewModel对象
	 * 首先通过从各个缓存分片中查找各个部分的缓存，用各部分的缓存拼ProductMallViemModel
	 * 如果少一个部分的缓存不存在，就优先考虑通过productCode 来获取ProductMallViewModel 没有ProductCode
	 * 则通过productId获取，获取过对象
	 * 
	 * */
	private ProductMallViewModel getProMallViewByCacheOrDB(String productCode,
			Long productId) throws Exception {

		ProductMallViewModel resultModel = null;
		ProductOperateModel operateModel = null;
		ProductMallModel productMallModel = null;

		ProductModel productModel = (ProductModel) this.getCachedClient()
				.getObject("product_" + productCode);
		if (productModel != null) { // 拼接数据给返回给用户
			operateModel = (ProductOperateModel) this.getCachedClient()
					.getObject("operate_" + productModel.getProductCode());// 通过operate_
																			// +
																			// productId
																			// 来做唯一标志
			productMallModel = (ProductMallModel) this.getCachedClient()
					.getObject("product_mall_" + productModel.getProductCode());// 通过product_mall__
																				// +
																				// productId
																				// 来做唯一标志
		}

		// 如果 productModel、perateModel 、productMallModel
		// 三者有其一为空，需从表t_product_mall_view中查数据
		if (productModel == null || productMallModel == null
				|| operateModel == null) {

			// 先判断是否有productCode,如果有productCode就按productCode查，否则按productId查
			if (!StringUtils.isEmpty(productCode)) {
				resultModel = getMallProductMallViewByProductCode(productCode);
			} else {
				resultModel = getMallProductMallViewByProductId(productId);
			}

			if (resultModel != null) {
				productModel = createProductModelByMallView(resultModel);
				resultModel.setAttributeList(getAttributeById(productModel.getProductId()));
				productMallModel = createProductMallModelByMallView(resultModel);
				operateModel = createProductOperateModelByMallView(resultModel);
				productModel.setAttributeList(resultModel.getAttributeList());
				// 添加新的缓存
				this.getCachedClient().cacheObject("product_" + productCode,
						productModel, cacheTime);
				this.getCachedClient().cacheObject(
						"product_mall_" + productCode, productMallModel,
						cacheTime);
				this.getCachedClient().cacheObject("operate_" + productCode,
						operateModel, cacheTime);
			}

		} else {
			resultModel = createProMallViewByParts(productModel,
					productMallModel, operateModel);
		}

		return resultModel;

	}

	/**
	 * 获取供应商,先从缓存中获取，如果缓存中有，则用缓存中 ，如果缓存中没有则使用数据库中
	 * 
	 * @throws Exception
	 * */
	private List<SupplierShopModel> getSupplierFromCacheOrDB(
			List<Long> shopIdList, SupplierShopService supplierShopService)
			throws Exception {
		List<SupplierShopModel> supplierShopList = new ArrayList<SupplierShopModel>();
		List<SupplierShopModel> queryResultList = null;

		List<Long> queryIdList = new ArrayList<Long>();
		for (Long shopId : shopIdList) {
			SupplierShopModel shopModel = (SupplierShopModel) this
					.getCachedClient().getObject("supplier_shop_" + shopId);
			if (shopModel != null) {
				supplierShopList.add(shopModel);
			} else {
				queryIdList.add(shopId);
			}
		}

		if (queryIdList.size() > 0) {
			queryResultList = supplierShopService
					.getSupplierByIdList(queryIdList);
			if (queryResultList != null && queryResultList.size() > 0) {
				for (SupplierShopModel resultShop : queryResultList) {
					this.getCachedClient().cacheObject(
							"supplier_shop_" + resultShop.getId(), resultShop,
							cacheTime);
					supplierShopList.add(resultShop);
				}
			}

		}
		return supplierShopList;
	}

	private Long getProductShelves(String productCode) throws Exception {
		Long shelves = (Long) this.getCachedClient().getObject(
				"shelves_" + productCode);
		if (shelves != null) {
			return shelves;
		}

		shelves = getProductShelvesFiledByProductCode(productCode);
		if (shelves != null) {
			this.getCachedClient().cacheObject("shelves_" + productCode,
					shelves);// 放到缓存中
		}
		return shelves;
	}

	/**
	 * 先从缓存中读取各个模块的数据： 1、如过在缓存中各模块的数据都有，则用这些模块数据组装成新的模块对象
	 * 2、缓存中只要缺一个模块数据，则需要重新从数据库中读取，然后再分割成各个模块对象放到缓存中
	 * */
	private List<ProductMallViewModel> fillProMallDataPart(
			List<Long> productIdList, ProductInfoService service,
			String clientType) throws Exception {

		List<ProductMallViewModel> productMallModelList = new ArrayList<ProductMallViewModel>();

		List<ProductMallViewModel> queryViewModelList = null;
		List<Long> queryIdList = new ArrayList<Long>();// 放缓存中没有的数据ID,用户数据库查询

		ProductOperateModel operateModel = null;
		ProductMallModel productMallModel = null;
		ProductModel productModel = null;
		ProductMallViewModel partMallViewModel = null;

		// 先从缓存的各个分片中查询数据，拼装成需要的模型，如果有一个分片的缓存没有数据，则需要
		for (Long productId : productIdList) {

			productModel = (ProductModel) this.getCachedClient().getObject(
					"product_" + productId);
			if (productModel != null) { // 拼接数据给返回给用户
				operateModel = (ProductOperateModel) this.getCachedClient()
						.getObject("operate_" + productModel.getProductId());// 通过operate_
																				// +
																				// productId
																				// 来做唯一标志

				productMallModel = (ProductMallModel) this.getCachedClient()
						.getObject(
								"product_mall_" + productModel.getProductId());// 通过product_mall__
																				// +
																				// productId
																				// 来做唯一标志
			}

			if (productModel == null || productMallModel == null
					|| operateModel == null) {
				queryIdList.add(productId);// 需要从数据库总获取
			} else {
				partMallViewModel = createProMallViewByParts(productModel,
						productMallModel, operateModel);
				partMallViewModel
						.setProductShelves(getProductShelves(partMallViewModel
								.getProductCode()));// 设置上下架信息(ProductShelves)
				fillProMallDescData(partMallViewModel, service, clientType);
				productMallModelList.add(partMallViewModel);
			}

		}

		// 从数据库中查询，然后将查询结果重新分片放到缓存中
		if (queryIdList.size() > 0) {
			queryViewModelList = getProductMallViewByProductIdList(queryIdList,
					ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
			if (queryViewModelList != null && queryViewModelList.size() > 0) {

				for (ProductMallViewModel mallViewModel : queryViewModelList) {// 将数据库中查到的数据放到缓存中
					fillProMallDescData(mallViewModel, service, clientType);
					mallViewModel
							.setProductShelves(getProductShelves(mallViewModel
									.getProductCode()));// 设置上下架信息(ProductShelves)

					productModel = createProductModelByMallView(mallViewModel);
					productMallModel = createProductMallModelByMallView(mallViewModel);
					operateModel = createProductOperateModelByMallView(mallViewModel);
					// 添加新的缓存
					this.getCachedClient().cacheObject(
							"product_" + mallViewModel.getProductId(),
							productModel, cacheTime);
					this.getCachedClient().cacheObject(
							"product_mall_" + productMallModel.getProductId(),
							productMallModel, cacheTime);
					this.getCachedClient().cacheObject(
							"operate_" + operateModel.getProductId(),
							operateModel, cacheTime);

					productMallModelList.add(mallViewModel);
				}

			}
		}

		return productMallModelList;
	}

	/**
	 * 先从缓存中读取各个模块的数据： 1、如过在缓存中各模块的数据都有，则用这些模块数据组装成新的模块对象
	 * 2、缓存中只要缺一个模块数据，则需要重新从数据库中读取，然后再分割成各个模块对象放到缓存中
	 * */
	private List<ProductMallViewModel> fillProMallDataPartByCodes(
			List<String> productCodes, ProductInfoService service,
			String clientType) throws Exception {

		List<ProductMallViewModel> productMallModelList = new ArrayList<ProductMallViewModel>();

		List<ProductMallViewModel> queryViewModelList = null;
		List<String> queryCodeList = new ArrayList<String>();// 放缓存中没有的数据ID,用户数据库查询

		ProductOperateModel operateModel = null;
		ProductMallModel productMallModel = null;
		ProductModel productModel = null;
		ProductMallViewModel partMallViewModel = null;

		// 先从缓存的各个分片中查询数据，拼装成需要的模型，如果有一个分片的缓存没有数据，则需要
		for (String productCode : productCodes) {

			productModel = (ProductModel) this.getCachedClient().getObject(
					"product_" + productCode);
			if (productModel != null) { // 拼接数据给返回给用户
				operateModel = (ProductOperateModel) this.getCachedClient()
						.getObject("operate_" + productCode);// 通过operate_ +
																// productId
																// 来做唯一标志

				productMallModel = (ProductMallModel) this.getCachedClient()
						.getObject("product_mall_" + productCode);// 通过product_mall__
																	// +
																	// productId
																	// 来做唯一标志
			}

			if (productModel == null || productMallModel == null
					|| operateModel == null) {
				queryCodeList.add(productCode);// 需要从数据库总获取
			} else {
				partMallViewModel = createProMallViewByParts(productModel,
						productMallModel, operateModel);
				partMallViewModel
						.setProductShelves(getProductShelves(partMallViewModel
								.getProductCode()));// 设置上下架信息(ProductShelves)
				fillProMallDescData(partMallViewModel, service, clientType);
				productMallModelList.add(partMallViewModel);
			}

		}

		// 从数据库中查询，然后将查询结果重新分片放到缓存中
		if (queryCodeList.size() > 0) {
			queryViewModelList = getProductMallViewByProductCodeList(
					queryCodeList, ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
			if (queryViewModelList != null && queryViewModelList.size() > 0) {

				for (ProductMallViewModel mallViewModel : queryViewModelList) {// 将数据库中查到的数据放到缓存中
					fillProMallDescData(mallViewModel, service, clientType);
					mallViewModel
							.setProductShelves(getProductShelves(mallViewModel
									.getProductCode()));// 设置上下架信息(ProductShelves)

					productModel = createProductModelByMallView(mallViewModel);
					productMallModel = createProductMallModelByMallView(mallViewModel);
					operateModel = createProductOperateModelByMallView(mallViewModel);
					// 添加新的缓存
					this.getCachedClient().cacheObject(
							"product_" + mallViewModel.getProductCode(),
							productModel, cacheTime);
					this.getCachedClient().cacheObject(
							"product_mall_" + mallViewModel.getProductCode(),
							productMallModel, cacheTime);
					this.getCachedClient().cacheObject(
							"operate_" + mallViewModel.getProductCode(),
							operateModel, cacheTime);

					productMallModelList.add(mallViewModel);
				}

			}
		}

		return productMallModelList;
	}

	/**
	 * 设置ProductMallViewModel 描述信息 描述信息优先从缓存中获取，其次才是数据库， 描述信息的按终端类型来选择
	 * 
	 * @param model
	 *            ProductMallViewModel对象
	 * @param service
	 *            ProductInfoService
	 * @String clientType 终端类型
	 * */
	public void fillProMallDescData(ProductMallViewModel model,
			ProductInfoService service, String clientType) throws Exception {
		// 描述信息详情,
		ProductInfoModel productInfoModel = (ProductInfoModel) this
				.getCachedClient().getObject(
						"product_info_" + model.getProductCode());
		if (productInfoModel == null) {
			productInfoModel = service.getProInfoByProductId(model
					.getProductId());
			if (productInfoModel != null) {
				this.getCachedClient().cacheObject(
						"product_info_" + model.getProductCode(),
						productInfoModel, cacheTime);
			}
		}

		// 描述信息按终端来分类
		if (productInfoModel != null) {
			if (CommonConstant.CLIENT_TYPE_ENUM.PC.getKey().equals(clientType)) {// pc
																					// 端
				model.setIntegrityDesc(productInfoModel.getPcProductInfo());
			} else {// app 端
				model.setIntegrityDesc(productInfoModel.getAppProductInfo());
			}
		}

	}

	/**
	 * 通过 skuIds 集合获取ProductSkuViewModel 对象集合
	 * 1、先从缓存中获取对象，如果缓存中有，直接使用。如果缓存中找不到，执行第2步 2、如果缓存中没有，则从数据库中查数据，查到，使用该数据并放到缓存中
	 * */
	private List<ProductSkuViewModel> getProSkuDataBySkuIds(List<Long> skuIds,
			ProductSkuViewService productSkuViewService) throws Exception {

		List<ProductSkuViewModel> queryskuViewModelList = null;
		List<ProductSkuViewModel> skuViewModelList = new ArrayList<ProductSkuViewModel>();
		List<Long> querySkuIdList = new ArrayList<Long>();
		ProductSkuViewModel skuCacheModel = null;

		for (Long skuId : skuIds) {
			skuCacheModel = (ProductSkuViewModel) this.getCachedClient()
					.getObject("product_sku_view_" + skuId);// 通过operate_
															// 来做唯一标志
			if (skuCacheModel != null) {// 存在就使用缓存中的数据
				skuViewModelList.add(skuCacheModel);
			} else {// 不存在就记录到数据库中查询
				querySkuIdList.add(skuId);
			}
		}

		if (querySkuIdList.size() > 0) {// 需要多数据库中查询
			queryskuViewModelList = productSkuViewService
					.getProductSkuViewBySkuIdList(querySkuIdList);
			if (queryskuViewModelList != null
					&& queryskuViewModelList.size() > 0) {
				for (ProductSkuViewModel skuViewModel : queryskuViewModelList) {// 将数据库中查到的数据放到缓存中
					this.getCachedClient().cacheObject(
							"product_sku_view_"
									+ skuViewModel.getProductSkuId(),
							skuViewModel);
					skuViewModelList.add(skuViewModel);
				}
			}
		}

		return skuViewModelList;
	}

	/***
	 * 获取skuModelList,先从缓存中拿到商品和sku的对应关系模型proMapsku，如果存在，则通过proMap获取
	 * skuCodesList 集合，通过skuCodesList 获取skuList,如果缓存中不存在proMapSku 则需要从数据库获取数据
	 * 在将相应的对象存到缓存中
	 * 
	 * 
	 * */
	private List<ProductSkuViewModel> getProductSkuFromCacheOrDB(
			ProductSkuViewService productSkuViewService, Long productId,
			String productCode) throws Exception {
		List<ProductSkuViewModel> skuList = null;
		List<String> skuCodes = null;

		ProductMapSkuCodeModel proMapSkuModel = (ProductMapSkuCodeModel) this
				.getCachedClient().getObject(
						"product_map_sku_view_" + productCode);
		if (proMapSkuModel != null) {
			skuCodes = proMapSkuModel.getSkuCodeList();
			skuList = getProSkuDataBySkuCodes(skuCodes, productSkuViewService);
		} else {
			skuList = productSkuViewService
					.getProductSkuViewByProdId(productId);
			if (skuList != null && skuList.size() > 0) {
				skuCodes = new ArrayList<String>();

				for (ProductSkuViewModel skuModel : skuList) {
					this.getCachedClient().cacheObject(
							"product_sku_view_" + skuModel.getProductSkuCode(),
							skuModel, cacheTime);
					skuCodes.add(skuModel.getProductSkuCode());
				}
				proMapSkuModel = new ProductMapSkuCodeModel();
				proMapSkuModel.setProductCode(productCode);
				proMapSkuModel.setSkuCodeList(skuCodes);
				this.getCachedClient().cacheObject(
						"product_map_sku_view_" + productCode, proMapSkuModel,
						cacheTime);
			}
		}

		return skuList;
	}

	/**
	 * 通过 skuCodes 集合获取ProductSkuViewModel 对象集合
	 * 1、先从缓存中获取对象，如果缓存中有，直接使用。如果缓存中找不到，执行第2步 2、如果缓存中没有，则从数据库中查数据，查到，使用该数据并放到缓存中
	 * */
	private List<ProductSkuViewModel> getProSkuDataBySkuCodes(
			List<String> skuCodes, ProductSkuViewService productSkuViewService)
			throws Exception {

		List<ProductSkuViewModel> queryskuViewModelList = null;
		List<ProductSkuViewModel> skuViewModelList = new ArrayList<ProductSkuViewModel>();

		List<String> querySkuList = new ArrayList<String>();
		ProductSkuViewModel skuCacheModel = null;
		for (String skuCode : skuCodes) {
			skuCacheModel = (ProductSkuViewModel) this.getCachedClient()
					.getObject("product_sku_view_" + skuCode);// 通过operate_
			if (skuCacheModel != null) {// 存在就使用缓存中的数据
				skuViewModelList.add(skuCacheModel);
			} else {// 不存在就记录到数据库中查询
				querySkuList.add(skuCode);
			}
		}

		if (querySkuList.size() > 0) {// 需要多数据库中查询
			queryskuViewModelList = productSkuViewService
					.getProductSkuViewBySkuList(querySkuList);
			if (queryskuViewModelList != null
					&& queryskuViewModelList.size() > 0) {
				for (ProductSkuViewModel skuViewModel : queryskuViewModelList) {// 将数据库中查到的数据放到缓存中
					this.getCachedClient().cacheObject(
							"product_sku_view_"
									+ skuViewModel.getProductSkuCode(),
							skuViewModel, cacheTime);
					skuViewModelList.add(skuViewModel);
				}
			}
		}

		return skuViewModelList;
	}

	/**
	 * 组合店铺和供应商的Map关联 :key--productId ,value--SupplierShopModel
	 * */
	private Map<Long, SupplierShopModel> mapProIdAndSupplierShopModel(
			List<ProductMallViewModel> productMallModelList,
			List<SupplierShopModel> shopList) {

		Map<Long, SupplierShopModel> map = new HashMap<Long, SupplierShopModel>();

		for (ProductMallViewModel mallModel : productMallModelList) {
			for (SupplierShopModel shopModel : shopList) {

				if (mallModel.getShopId() != null
						&& mallModel.getShopId().equals(shopModel.getId())) {
					map.put(mallModel.getProductId(), shopModel);
				}
			}
		}

		return map;
	}

	/**
	 * codeList从图片服务器获取信息 然后封装成Map<code,List<ProductImagePO>>
	 * 
	 * @param codeList
	 *            可以使skuCode,productCode等
	 * @param ralatationType
	 *            关联类型 0：其它，1：商品sku，2：商品详情，3：品牌，4:品牌说明5：基础类型，
	 *            6：运营类型，7：属性，8：活动，9：活动类别，10：广告，11：CMS
	 * @return Map<String, List<ProductImagePO>>
	 * */
	private Map<String, List<ProductImagePO>> getImageListByCodeList(
			List<String> skuCodeList, Long relataionType) {

		List<ProductImagePO> imageList = null;
		Map<String, List<ProductImagePO>> map = new HashMap<String, List<ProductImagePO>>();// 按skuCode
																							// 组合
																							// List<ProductImagePO>
		ProductImagePO imagePO = null;

		if (skuCodeList == null || skuCodeList.size() < 1) {
			return null;
		}

		GetImagePathBatchRequestPO batchPo = new GetImagePathBatchRequestPO();

		batchPo.setRelationCodes(skuCodeList);
		batchPo.setRelationType(relataionType);

		RequestObject obj = new RequestObject();
		obj.setRequestParams(batchPo);

		ResponseObject response = imageSearch.getImagePathBatch(obj);
		if (response.getCode() == ResponseObject.CODE_SUCCESS_VALUE) {
			GetImageBatchResponsePO responsePo = (GetImageBatchResponsePO) response
					.getData();
			if (responsePo != null) {
				Map<String, List<GetImageResponsePO>> dataMap = responsePo
						.getImageMap();
				if (dataMap != null) {
					Set<String> keySet = dataMap.keySet();
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key = it.next();
						List<GetImageResponsePO> imagePOList = dataMap.get(key);
						imageList = new ArrayList<ProductImagePO>();
						for (GetImageResponsePO imageResponsePO : imagePOList) {
							imagePO = new ProductImagePO();
							imagePO.setImageIndex(imageResponsePO
									.getImageIndex());
							imagePO.setImageName(imageResponsePO.getImageName());
							imagePO.setImagePath(imageResponsePO.getImagePath());
							imagePO.setImageType(imageResponsePO.getImageType());
							imagePO.setImageVersion(imageResponsePO
									.getImageVersion());

							imageList.add(imagePO);
						}
						map.put(key, imageList);
					}
				}

			}

		}
		return map;
	}

	    
	/**
	 * 填充库存数据数据给 ProductSkuViewModel
	 * */
	public void fillData2SkuModel(List<ProductSkuViewModel> skuModelList,
			List<ProductStockModel> stockModelList) {
		if (skuModelList == null || stockModelList == null) {
			return;
		}
		for (ProductSkuViewModel productSkuViewModel : skuModelList) {
			for (ProductStockModel productStockModel : stockModelList) {
				if (productSkuViewModel.getProductSkuCode().equals(
						productStockModel.getSkuCode())) {
					fillData2SkuModel(productSkuViewModel, productStockModel);
				}
			}
		}

	}

	public void fillData2SkuModel(ProductSkuViewModel skuViewModel,
			ProductStockModel stockModel) {
		skuViewModel.setVirtualStock(stockModel.getVirtualStock());
		skuViewModel.setEntityStock(stockModel.getEntityStock());
		skuViewModel.setManualStock(stockModel.getManualStock());
	}

	private List<Long> getProIdBySkuModelList(
			List<ProductSkuViewModel> skuModelList) {
		List<Long> proIdList = null;
		if (skuModelList != null && skuModelList.size() > 0) {
			proIdList = new ArrayList<Long>();
			for (ProductSkuViewModel skuViewModel : skuModelList) {
				if (proIdList.contains(skuViewModel.getProductId())) {
					continue;
				}
				proIdList.add(skuViewModel.getProductId());
			}
		}
		return proIdList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teshehui.product.service.ProductService#getMallProductInfoList(com
	 * .teshehui.product.model.ProductListRequestModel )
	 */
	@Override
	public ResponseObject getMallProductInfoList(RequestProductListPO requestObj) {
		ResponseObject responseObject = new ResponseObject();
		ResultSetModel<ProductInfoPO<MallProductListInfoPO>> resultSet = new ResultSetModel<ProductInfoPO<MallProductListInfoPO>>();
		try {
			// -------php参数组成-----
			Map<String, String> params = new HashMap<String, String>();
			if (requestObj.getPage() != null && requestObj.getPage() > 0) {
				params.put("page", requestObj.getPage().toString());
			} else {
				params.put("page", "1");
			}
			if (requestObj.getPageSize() != null
					&& requestObj.getPageSize() > 0) {
				params.put("num_per_page", requestObj.getPageSize().toString());
			} else {
				params.put("num_per_page", "20");
			}
			if (!StringUtil.isEmpty(requestObj.getCategoryCode()))
				params.put("cate_id", requestObj.getCategoryCode());
			if (!StringUtil.isEmpty(requestObj.getCategoryLevel()))
				params.put("layer", requestObj.getCategoryCode());
			if (!StringUtil.isEmpty(requestObj.getCategoryLevel()))
				params.put("region_id", requestObj.getOrigin());
			if (!StringUtil.isEmpty(requestObj.getStoreCode()))
				params.put("store", requestObj.getStoreCode());
			if (!StringUtil.isEmpty(requestObj.getSort()))
				params.put("sort", requestObj.getSort());
			if (!StringUtil.isEmpty(requestObj.getOrderBy()))
				params.put("order", requestObj.getOrderBy());
			if (requestObj.getKeyword() != null
					&& requestObj.getKeyword().size() > 0)
				params.put("keyword", requestObj.getKeyword().get(0));
			// PHP接口暂时只支持单品牌查询，后续升级后将修改为多品牌并集查询
			if (requestObj.getBrandCode() != null)
				params.put("brand", requestObj.getBrandCode().get(0));
			if (!StringUtil.isEmpty(requestObj.getLowestPrice())
					|| !StringUtil.isEmpty(requestObj.getHighestPrice())) {
				String priceInterval = "";
				if (!StringUtil.isEmpty(requestObj.getLowestPrice())) {
					priceInterval += requestObj.getLowestPrice();
				} else {
					priceInterval += "0";
				}
				if (!StringUtil.isEmpty(requestObj.getHighestPrice())) {
					priceInterval += requestObj.getHighestPrice();
				} else {
					priceInterval += "1000000";
				}
				params.put("cate_id", priceInterval);
				params.put("", priceInterval);
			}
			String sourStr = HttpClientUtil.sendHttpRequest(
					APIConfig.mallProductSearchUrl, params);
			// System.out.println(sourStr);
			if (!StringUtil.isEmpty(sourStr)) {
				logger.debug("params=" + params + "   sourStr=" + sourStr);
				List<ProductInfoPO<MallProductListInfoPO>> productInfos = new ArrayList<ProductInfoPO<MallProductListInfoPO>>();
				Gson gson = new Gson();
				GoodList goods = gson.fromJson(sourStr,
						new TypeToken<GoodList>() {
						}.getType());
				// Object obj=JSONUtil.toObject(sourStr,null);
				// System.out.println(obj);
				// System.out.println(goods.getStatus());
				if (goods.status == 200) {
					for (int i = 0; i < goods.data.goods_list.size(); i++) {
						productInfos
								.add(conversionMallListData(goods.data.goods_list
										.get(i)));
					}
					resultSet.setTotalCount(productInfos.size());
					resultSet.setBeginRow(requestObj.getPage()
							* requestObj.getPageSize());
					resultSet.setItems(productInfos);
					responseObject.setData(resultSet);
					responseObject.setCode(ProductConstantUtil.HANDEL_SUCCESS);
				} else {
					logger.error("商城商品列表php接口返回错误   params=" + params
							+ "   sourStr=" + sourStr);
					responseObject
							.setErrorCode(ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE002);
					responseObject.setMsg(goods.error_msg);
					responseObject.setCode(ProductConstantUtil.HANDEL_FAIL);
				}
			} else {
				logger.error("商城商品列表php接口访问错误   params=" + params
						+ "   sourStr=" + sourStr);
				responseObject
						.setErrorCode(ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE003);
				responseObject.setMsg("商城商品列表php接口访问错误");
				responseObject.setCode(ProductConstantUtil.HANDEL_FAIL);
			}
		} catch (Exception e) {
			logger.error("获取商城商品列表错误", e);
			responseObject
					.setErrorCode(ProductConstantUtil.BIZ_MALL_LIST_DATA_ERROR_CODE004);
			responseObject.setMsg("获取商城商品列表错误" + e);
			responseObject.setCode(ProductConstantUtil.HANDEL_FAIL);
		}
		return responseObject;
	}

	/**
	 * 解析php返回数据mall list
	 * 
	 * @param jsonStr
	 * @return
	 */
	private ProductInfoPO<MallProductListInfoPO> conversionMallListData(
			GoodDatas goodsInfo) {
		ProductInfoPO<MallProductListInfoPO> productInfo = new ProductInfoPO<MallProductListInfoPO>();
		productInfo.setProductCode(goodsInfo.goods_id);
		productInfo.setProductId(Long.getLong(goodsInfo.goods_id));
		productInfo.setProductName(goodsInfo.goods_name);
		productInfo.setProductEnName("");
		productInfo.setStoreId(Long.getLong(goodsInfo.store_id));
		productInfo.setStoreCode(goodsInfo.store_id);
		productInfo.setStoreName(goodsInfo.store_name);
		productInfo.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		productInfo.setBrandCode("");
		productInfo.setBrandEnName("");
		productInfo.setBrandName(goodsInfo.brand);
		productInfo.setCategoryCode(goodsInfo.cate_id);
		productInfo.setCategoryName(goodsInfo.cate_name);
		// productInfo.setBuiltPlaceId(goodsInfo.region_id);
		// productInfo.setBuiltPlaceName(goodsInfo.region_name);
		productInfo.setCurrency("CNY");
		// productInfo.setActiveStartTime(activeStartTime);
		// productInfo.setActiveEndTime(activeEndTime);
		productInfo.setActivePrice(goodsInfo.price + "");
		// productInfo.setFixPrice(goodsInfo.cost_price);
		productInfo.setMarkerPrice(goodsInfo.marketing_price + "");
		productInfo.setPriceType(0);
		productInfo.setSalesPrice(goodsInfo.price + "");
		productInfo.setOnSale(1);
		productInfo.setSkuAttributeName1(goodsInfo.spec_name_1);
		productInfo.setSkuAttributeName2(goodsInfo.spec_name_2);
		// productInfo.setDiscount(discount);
		// 附加私有属性
		MallProductListInfoPO mallPro = new MallProductListInfoPO();
		mallPro.setAddTime(goodsInfo.add_time);
		mallPro.setClosed(goodsInfo.closed);
		mallPro.setDefaultImage(goodsInfo.default_image);
		mallPro.setIfShow(goodsInfo.if_show);
		mallPro.setMiddleGoodsImage(goodsInfo.middle_goods_image);
		mallPro.setPoints(goodsInfo.points);
		mallPro.setPraise_rate(goodsInfo.praise_rate);
		mallPro.setRecId(goodsInfo.rec_id);
		mallPro.setRecommended(goodsInfo.recommended);
		mallPro.setSales(goodsInfo.sales);
		mallPro.setSmallGoodsImage(goodsInfo.small_goods_image);
		mallPro.setSpec_qty(goodsInfo.spec_qty);
		mallPro.setStock(goodsInfo.stock);
		mallPro.setSubtotal(goodsInfo.subtotal);
		mallPro.setSubtotalPoints(goodsInfo.subtotal_points);
		mallPro.setThumbnailMiddle(goodsInfo.thumbnail_middle);
		mallPro.setThumbnailSmall(goodsInfo.thumbnail_small);
		mallPro.setType(goodsInfo.type);
		// -------
		
//		productInfo.setAttributeList(attributeList);
		List<ProductSKUPO> skuList = new ArrayList<ProductSKUPO>();
		ProductSKUPO sku = new ProductSKUPO();
		sku.setProductCode(goodsInfo.goods_id);
		sku.setCurrency("CNY");
		sku.setSkuId(goodsInfo.rec_id);
		// sku.setActiveStartTime(activeStartTime);
		// sku.setActiveEndTime(activeEndTime);
		sku.setActivePrice(goodsInfo.price + "");
		// sku.setFixPrice(goodsInfo.cost_price);
		sku.setSalesPrice(goodsInfo.price + "");
		sku.setPoints(goodsInfo.points);
		sku.setMarkerPrice(goodsInfo.marketing_price + "");
		sku.setQuantity(goodsInfo.quantity);
		sku.setPriceType(0);
		sku.setSkuAttributeValue1(goodsInfo.specification);
		skuList.add(sku);

		productInfo.setSkuList(skuList);
		return productInfo;
	}
	
	private List<AttributePO> getAttributeById(Long productId) {
		List<AttributePO> result = null;
		List<ProductAttributeModel> list = productAttributeDao.getAttributeByProductId(productId);
		if (null != list && list.size() > 0) {
			result=new ArrayList<AttributePO>();
			for (int i = 0; i < list.size(); i++) {
				AttributePO po= new AttributePO();
				po.setId(list.get(i).getId());
				po.setAttributeName(list.get(i).getAttribute_name());
				po.setAttributeValue(list.get(i).getAttribute_value());
				po.setAttributeLevel(list.get(i).getAttribute_level());
				result.add(po);
			}
		}
		return result;
	}
	

	 

	/**
	 * 
	 * @param mapProductIdAndSkuModel
	 * @return
	 */
	private List<ProductSKUPO> getProductSKUPOListFromMap(
			ProductMallViewModel mallModel,
			List<ProductSkuViewModel> mapProductIdAndSkuList,
			Map<String, List<ProductImagePO>> mapSkuCodeAndImagePo) {
		List<ProductSKUPO> productSKUPoList = new ArrayList<ProductSKUPO>();
		ProductSKUPO skuPo = null;

		for (ProductSkuViewModel model : mapProductIdAndSkuList) {
			skuPo = new ProductSKUPO();
			fillProSkuData(skuPo, model, mallModel.getProductCode());// 填充数据
			skuPo.setImageOPList(mapSkuCodeAndImagePo.get(model
					.getProductSkuCode()));
			productSKUPoList.add(skuPo);
		}
		return productSKUPoList;
	}

	/**
	 * 将List<ProductSkuViewModel> 按照productId去分类
	 * 
	 * @param skuViewModelList
	 * @return Map<key,value>
	 *         key--->productId,value---->对应的List<ProductSkuViewModel>
	 */
	private Map<Long, List<ProductSkuViewModel>> groupSkuViewModel(
			List<ProductSkuViewModel> skuViewModelList) {

		Map<Long, List<ProductSkuViewModel>> map = new HashMap<Long, List<ProductSkuViewModel>>();

		List<ProductSkuViewModel> skuList = null;
		for (ProductSkuViewModel skuViewModel : skuViewModelList) {
			Long key = skuViewModel.getProductId();
			if (map.containsKey(key)) {
				skuList = map.get(key);
				skuList.add(skuViewModel);
			} else {
				skuList = new ArrayList<ProductSkuViewModel>();
				skuList.add(skuViewModel);
			}
			map.put(key, skuList);
		}
		return map;
	}

	/**
	 * 将List<ProductSkuViewModel> 按照productCode去分类
	 * 
	 * @param skuViewModelList
	 * @return Map<key,value>
	 *         key--->productCode,value---->对应的List<ProductSkuViewModel>
	 */
	private Map<String, List<ProductSkuViewModel>> mapProCodeAndSkuViewList(
			List<ProductSkuViewModel> skuViewModelList) {

		Map<String, List<ProductSkuViewModel>> map = new HashMap<String, List<ProductSkuViewModel>>();

		List<ProductSkuViewModel> skuList = null;
		for (ProductSkuViewModel skuViewModel : skuViewModelList) {
			String skuCode = skuViewModel.getProductSkuCode();
			if (StringUtils.isEmpty(skuCode)) {
				skuCode.substring(0, 12);// skuCode 截取前12位是productCode
				if (map.containsKey(skuCode)) {
					skuList = map.get(skuCode);
					skuList.add(skuViewModel);
				} else {
					skuList = new ArrayList<ProductSkuViewModel>();
					skuList.add(skuViewModel);
				}
				map.put(skuCode, skuList);
			}
		}
		return map;
	}

	/**
	 * 通过productSkuViewModel List 获取productCodes list 结合
	 * */
	private List<String> getProductCodeListBySkuViewModelList(
			List<ProductSkuViewModel> skuViewModelList) {

		Set<String> set = new HashSet<String>();

		for (ProductSkuViewModel skuViewModel : skuViewModelList) {
			String skuCode = skuViewModel.getProductSkuCode();
			if (!StringUtils.isEmpty(skuCode)) {
				String productCode = skuCode.substring(0, 12);// skuCode
																// 截取前12位是productCode
				set.add(productCode);
			}
		}
		String[] strs = set.toArray(new String[set.size()]);
		return Arrays.asList(strs);
	}

  

	/*******************************************************************/
	/*************************** 以下是工具类 ********************************/

	/**
	 * 将产品模型List转换PO
	 * */
	public List<ProductMallPO> mallModelList2PO(
			List<ProductMallViewModel> modelList) {
		List<ProductMallPO> poList = null;
		if (modelList != null) {
			poList = new ArrayList<ProductMallPO>();
			ProductMallPO po = null;
			for (ProductMallViewModel model : modelList) {
				po = mallModel2PO(model);
				if (po != null) {
					poList.add(po);
				}
			}
		}
		return poList;
	}

	/**
	 * 将产品模型转换PO
	 * */
	public ProductMallPO mallModel2PO(ProductMallViewModel model) {
		ProductMallPO PO = null;
		if (model != null) {
			PO = new ProductMallPO();
			PO.setProductId(model.getProductId());
			PO.setProductName(model.getProductName());
			PO.setProductCode(model.getProductCode());
			PO.setProductDesc(model.getProductDesc());

			PO.setCreateTime(model.getCreateTime());
			PO.setCreateBy(model.getCreateBy());
			PO.setCreateUserName(model.getCreateUserName());
			PO.setUpdateTime(model.getUpdateTime());
			PO.setUpdateBy(model.getUpdateBy());
			PO.setUpdateUserName(model.getUpdateUserName());

			PO.setProductShelves(model.getProductShelves() + "");
			PO.setFirstShelves(model.getFirstShelves());
			PO.setFirstShelvesName(model.getFirstShelvesName());
			PO.setFirstShelvesTime(model.getFirstShelvesTime());
			PO.setLastShelves(model.getLastShelves());
			PO.setLastShelvesTime(model.getLastShelvesTime());
			PO.setLastShelvesName(model.getLastShelvesName());
			PO.setDownShelves(model.getLastShelves());
			PO.setDownShelvesTime(model.getDownShelvesTime());
			PO.setDownShelvesName(model.getDownShelvesName());
			PO.setCostPrice(model.getCostPrice());
			PO.setTshPrice(model.getTshPrice());
			PO.setTb(model.getTb());
			PO.setMarketPrice(model.getMarketPrice());
			PO.setBrandCode(model.getBrandCode());
			PO.setBrandName(model.getBrandName());
			PO.setCatCode(model.getCatCode());
			PO.setCatName(model.getCatName());
			PO.setType(model.getType());
			PO.setStatus(model.getStatus());
			PO.setMainPic(model.getMainPic());
			PO.setSupplierId(model.getSupplierId());
			PO.setStopId(model.getShopId());
			PO.setIntegrity(model.getIntegrity());
			PO.setIntegrityDesc(model.getIntegrityDesc());
			PO.setProductShortName(model.getProductShortName());
			PO.setProductPrefix(model.getProductPrefix());
			PO.setProductSuffix(model.getProductSuffix());
			PO.setProductKeyword(model.getProductKeyword());
			PO.setProductDownShow(model.getProductDownShow());
			PO.setProductAutoUp(model.getProductAutoUp());
			PO.setProductSizeCompare(model.getProductSizeCompare());
			PO.setProductCatCode(model.getProductCatCode());
			PO.setProductCatName(model.getProductCatName());
			PO.setProductLevel(model.getProductLevel());
			PO.setProductPriceRate(model.getProductPriceRate());

			PO.setProductWeight(model.getProductWeight());
			PO.setProductLong(model.getProductLong());
			PO.setProductWidth(model.getProductWeight());
			PO.setProductHeight(model.getProductHeight());
			PO.setProductProducer(model.getProductProducer());
			PO.setProductDensity(model.getProductDensity());

			PO.setColumn3(model.getColumn3());
			PO.setColumn4(model.getColumn4());

		}
		return PO;
	}

	/**
	 * 将sku模型List转换PO
	 * */
	public List<ProductMallSkuPO> skuModelList2PO(
			List<ProductSkuViewModel> modelList) {
		List<ProductMallSkuPO> poList = null;
		if (modelList != null) {
			poList = new ArrayList<ProductMallSkuPO>();
			ProductMallSkuPO po = null;
			for (ProductSkuViewModel model : modelList) {
				po = skuModel2PO(model);
				if (po != null) {
					poList.add(po);
				}
			}
		}
		return poList;
	}

	/**
	 * 将sku模型转换PO
	 * */
	public ProductMallSkuPO skuModel2PO(ProductSkuViewModel model) {
		ProductMallSkuPO PO = null;
		if (model != null) {
			PO = new ProductMallSkuPO();
			PO.setProductSkuId(model.getProductSkuId());
			PO.setProductId(model.getProductId());
			PO.setProductSkuCode(model.getProductSkuCode());

			PO.setAttrColorName(model.getAttrColorName());
			PO.setAttrColorValue(model.getAttrColorValue());

			PO.setAttrSpecName(model.getAttrSpecName());
			PO.setAttrSpecValue(model.getAttrSpecValue());

			PO.setMainSku(model.getMainSku());
			PO.setIfShow(model.getIfShow());

			PO.setMarketPrice(model.getMarketPrice());
			PO.setCostPrice(model.getCostPrice());
			PO.setTshPrice(model.getTshPrice());
			PO.setTb(model.getTb());
		}
		return PO;
	}

	/**
	 * 获取ProductSkuViewModel 集合的ID 集合
	 * */
	private List<Long> getSkuIdListBySkuModelList(
			List<ProductSkuViewModel> skuModelList) {

		List<Long> skuIdList = null;
		if (skuModelList != null && skuModelList.size() > 0) {
			skuIdList = new ArrayList<Long>();
			for (ProductSkuViewModel productSkuViewModel : skuModelList) {
				if (skuIdList.contains(productSkuViewModel.getProductSkuId())) {
					continue;
				}
				skuIdList.add(productSkuViewModel.getProductSkuId());
			}
		}
		return skuIdList;
	}

	/**
	 * 获取ProductSkuViewModel 集合的skuCode 集合
	 * */
	private List<String> getSkuCodeListBySkuModelList(
			List<ProductSkuViewModel> skuModelList) {

		List<String> skuCodeList = null;
		if (skuModelList != null && skuModelList.size() > 0) {
			skuCodeList = new ArrayList<String>();
			for (ProductSkuViewModel productSkuViewModel : skuModelList) {
				if (skuCodeList.contains(productSkuViewModel
						.getProductSkuCode())) {
					continue;
				}
				skuCodeList.add(productSkuViewModel.getProductSkuCode());
			}
		}
		return skuCodeList;
	}

	/**
	 * 通过ProductModel,
	 * ProductMallModel,ProductOperateModel,构建ProductMallViewModel
	 * */
	private ProductMallViewModel createProMallViewByParts(
			ProductModel productModel, ProductMallModel productMallModel,
			ProductOperateModel operateModel) {
		ProductMallViewModel productMallView = null;
		if (productModel != null && productMallModel != null
				&& operateModel != null) {
			productMallView = new ProductMallViewModel();

			productMallView.setProductId(productModel.getProductId());
			productMallView.setProductName(productModel.getProductName());
			productMallView.setProductCode(productModel.getProductCode());
			productMallView.setProductDesc(productModel.getProductDesc());
			productMallView.setIntegrityDesc(productModel.getIntegrityDesc());
			productMallView.setProductShelves(productModel.getProductShelves());
			productMallView.setCreateTime(productModel.getCreateTime());
			productMallView.setCreateBy(productModel.getCreateTime());
			productMallView.setCreateUserName(productModel.getCreateUserName());
			productMallView.setUpdateTime(productModel.getUpdateTime());
			productMallView.setUpdateBy(productModel.getUpdateBy());
			productMallView.setUpdateUserName(productModel.getUpdateUserName());
			productMallView.setFirstShelves(productModel.getFirstShelves());
			productMallView.setFirstShelvesName(productModel
					.getFirstShelvesName());
			productMallView.setFirstShelvesTime(productModel
					.getFirstShelvesTime());
			productMallView.setLastShelves(productModel.getLastShelves());
			productMallView.setLastShelvesTime(productModel
					.getLastShelvesTime());
			productMallView.setLastShelvesName(productModel
					.getLastShelvesName());
			productMallView.setDownShelves(productModel.getDownShelves());
			productMallView.setDownShelvesTime(productModel
					.getDownShelvesTime());
			productMallView.setDownShelvesName(productModel
					.getDownShelvesName());
			productMallView.setCostPrice(productModel.getCostPrice());
			productMallView.setTshPrice(productModel.getTshPrice());
			productMallView.setTb(productModel.getTb());
			productMallView.setMarketPrice(productModel.getMarketPrice());
			productMallView.setBrandCode(productModel.getBrandCode());
			productMallView.setBrandName(productModel.getBrandName());
			productMallView.setCatCode(productModel.getCatCode());
			productMallView.setCatName(productModel.getCatName());
			productMallView.setType(productModel.getType());
			productMallView.setStatus(productModel.getStatus());
			productMallView.setMainPic(productModel.getMainPic());
			productMallView.setSupplierId(productModel.getSupplierId());
			productMallView.setShopId(productModel.getStopId());
			productMallView.setIntegrity(productModel.getIntegrity());
			productMallView.setIntegrityDesc(productModel.getIntegrityDesc());

			productMallView.setProductWeight(productMallModel
					.getProductWeight());
			productMallView.setProductLong(productMallModel.getProductLong());
			productMallView
					.setProductWidth(productMallModel.getProductWeight());
			productMallView.setProductHeight(productMallModel
					.getProductHeight());
			productMallView.setProductProducer(productMallModel
					.getProductProducer());
			productMallView.setProductDensity(productMallModel
					.getProductDensity());
			productMallView.setProductAtrNumber(productMallModel
					.getProductAtrNumber());
			productMallView.setColumn3(productMallModel.getColumn3());
			productMallView.setColumn4(productMallModel.getColumn4());

			productMallView.setProductShortName(operateModel
					.getProductShortName());
			productMallView.setProductPrefix(operateModel.getProductPrefix());
			productMallView.setProductSuffix(operateModel.getProductSuffix());
			productMallView.setProductKeyword(operateModel.getProductKeyword());
			productMallView.setProductDownShow(operateModel
					.getProductDownShow());
			productMallView.setProductAutoUp(operateModel.getProductAutoUp());
			productMallView.setProductSizeCompare(operateModel
					.getProductSizeCompare());
			productMallView.setProductCatCode(operateModel.getProductCatCode());
			productMallView.setProductCatName(operateModel.getProductCatCode());
			productMallView.setProductLevel(operateModel.getProductLevel());
			productMallView.setProductPriceRate(operateModel
					.getProductPriceRate());

			productMallView.setProductVideoUrl(operateModel
					.getProductVideoUrl());
			productMallView.setProductVideoThumbnail(operateModel
					.getProductVideoThumbnail());
			productMallView.setIsSupportExpensive(operateModel
					.getIsSupportExpensive());
			productMallView.setUserMaxNum(operateModel.getUserMaxNum());
			productMallView.setUserMaxType(operateModel.getUserMaxType());
			productMallView.setZeroMaxCount(operateModel.getZeroMaxCount());
			productMallView.setAttributeList(productModel.getAttributeList());
		}

		return productMallView;
	}

	private ProductOperateModel createProductOperateModelByMallView(
			ProductMallViewModel viewModel) {
		ProductOperateModel productOperateModel = null;
		if (viewModel != null) {
			productOperateModel = new ProductOperateModel();
			productOperateModel.setProductId(viewModel.getProductId());
			productOperateModel.setProductShortName(viewModel
					.getProductShortName());
			productOperateModel.setProductPrefix(viewModel.getProductPrefix());
			productOperateModel.setProductSuffix(viewModel.getProductSuffix());
			productOperateModel
					.setProductKeyword(viewModel.getProductKeyword());
			productOperateModel.setProductDownShow(viewModel
					.getProductDownShow());
			productOperateModel.setProductAutoUp(viewModel.getProductAutoUp());
			productOperateModel.setProductSizeCompare(viewModel
					.getProductSizeCompare());
			productOperateModel
					.setProductCatCode(viewModel.getProductCatCode());
			productOperateModel
					.setProductCatName(viewModel.getProductCatCode());
			productOperateModel.setProductLevel(viewModel.getProductLevel());
			productOperateModel.setProductPriceRate(viewModel
					.getProductPriceRate());

			productOperateModel.setProductVideoUrl(viewModel
					.getProductVideoUrl());
			productOperateModel.setProductVideoThumbnail(viewModel
					.getProductVideoThumbnail());
			productOperateModel.setIsSupportExpensive(viewModel
					.getIsSupportExpensive());
			
			productOperateModel.setUserMaxNum(viewModel.getUserMaxNum());
			productOperateModel.setUserMaxType(viewModel.getUserMaxType());
			productOperateModel.setZeroMaxCount(viewModel.getZeroMaxCount());
		}
		return productOperateModel;

	}

	/***
	 * 通过ProductMallViewModel 拼装ProductModel
	 * */
	private ProductMallModel createProductMallModelByMallView(
			ProductMallViewModel viewModel) {
		ProductMallModel productMallModel = null;
		if (viewModel != null) {
			productMallModel = new ProductMallModel();

			productMallModel.setProductId(viewModel.getProductId());
			productMallModel.setProductWeight(viewModel.getProductWeight());
			productMallModel.setProductLong(viewModel.getProductLong());
			productMallModel.setProductWidth(viewModel.getProductWeight());
			productMallModel.setProductHeight(viewModel.getProductHeight());
			productMallModel.setProductProducer(viewModel.getProductProducer());
			productMallModel.setProductDensity(viewModel.getProductDensity());
			productMallModel.setProductAtrNumber(viewModel
					.getProductAtrNumber());

			productMallModel.setColumn3(viewModel.getColumn3());
			productMallModel.setColumn4(viewModel.getColumn4());

		}
		return productMallModel;
	}

	/***
	 * 通过ProductMallViewModel 拼装ProductModel
	 * */
	private ProductModel createProductModelByMallView(
			ProductMallViewModel resultModel) {
		ProductModel productMall = null;
		if (resultModel != null) {
			productMall = new ProductModel();
			productMall.setProductId(resultModel.getProductId());
			productMall.setProductName(resultModel.getProductName());
			productMall.setProductCode(resultModel.getProductCode());
			productMall.setProductDesc(resultModel.getProductDesc());
			productMall.setProductShelves(resultModel.getProductShelves());
			productMall.setCreateTime(resultModel.getCreateTime());
			productMall.setCreateBy(resultModel.getCreateTime());
			productMall.setCreateUserName(resultModel.getCreateUserName());
			productMall.setUpdateTime(resultModel.getUpdateTime());
			productMall.setUpdateBy(resultModel.getUpdateBy());
			productMall.setUpdateUserName(resultModel.getUpdateUserName());
			productMall.setFirstShelves(resultModel.getFirstShelves());
			productMall.setFirstShelvesName(resultModel.getFirstShelvesName());
			productMall.setFirstShelvesTime(resultModel.getFirstShelvesTime());
			productMall.setLastShelves(resultModel.getLastShelves());
			productMall.setLastShelvesTime(resultModel.getLastShelvesTime());
			productMall.setLastShelvesName(resultModel.getLastShelvesName());
			productMall.setDownShelves(resultModel.getDownShelves());
			productMall.setDownShelvesTime(resultModel.getDownShelvesTime());
			productMall.setDownShelvesName(resultModel.getDownShelvesName());
			productMall.setCostPrice(resultModel.getCostPrice());
			productMall.setTshPrice(resultModel.getTshPrice());
			productMall.setTb(resultModel.getTb());
			productMall.setMarketPrice(resultModel.getMarketPrice());
			productMall.setBrandCode(resultModel.getBrandCode());
			productMall.setBrandName(resultModel.getBrandName());
			productMall.setCatCode(resultModel.getCatCode());
			productMall.setCatName(resultModel.getCatName());
			productMall.setType(resultModel.getType());
			productMall.setStatus(resultModel.getStatus());
			productMall.setMainPic(resultModel.getMainPic());
			productMall.setSupplierId(resultModel.getSupplierId());
			productMall.setStopId(resultModel.getShopId());
			productMall.setIntegrity(resultModel.getIntegrity());
			productMall.setIntegrityDesc(resultModel.getIntegrityDesc());

		}
		return productMall;

	}

	/**
	 * 将ProductMallModel 对象转换成 ProductInfoPO<MallProductInfoPO>
	 * 
	 * @param mallView
	 *            ProductMallViewModel商品模型
	 * @param countModel
	 *            操作统计
	 * @param skuViewList
	 *            sku集合
	 * @param skuImageMap
	 *            skuCode对应的图片集合
	 * @param shopModel
	 *            店铺供应商
	 * */
	private ProductInfoPO<MallProductInfoPO> mallModel2ProudctInfoPO(
			ProductMallViewModel mallView, ProductCountModel countModel,
			List<ProductSkuViewModel> skuViewList,
			Map<String, List<ProductImagePO>> skuImageMap,
			SupplierShopModel shopModel) {

		if (mallView == null) {
			return null;
		}

		ProductInfoPO<MallProductInfoPO> productInfo = new ProductInfoPO<MallProductInfoPO>();
		fillProductInfoData(productInfo, mallView);// 填充数据

		// 商品价格
		ProductSkuViewModel mainSkuViewModel = getProMainSkuViewModelBySkuModelList(skuViewList); // 获取主sku,没有主sku返回第一条数据
		fillProductInfoPriceData(productInfo, mainSkuViewModel);// 通过主sku填充价格信息

		// 商品图片
		mainSkuViewModel = getProMainSkuViewModelBySkuList(skuViewList);// 获取主sku,没有主sku,返回空
		if (mainSkuViewModel != null && skuImageMap != null) {
			if (skuImageMap != null) {
				productInfo.setImageList(skuImageMap.get(mainSkuViewModel
						.getProductSkuCode()));// 用主sku图片设置商品图片
			}
		}

		// 店铺信息
		if (shopModel != null) {
			fillProInfoShopData(productInfo, shopModel);// 填充店铺信息
		}

		productInfo.setProductShelves(mallView.getProductShelves() + "");
		productInfo.setBuiltPlaceId(mallView.getProductProducer());
		productInfo.setBuiltPlaceName(mallView.getProductProducer());

		productInfo.setCategoryCode(mallView.getProductCatCode());
		productInfo.setCategoryName(mallView.getProductCatName());

		// 附加私有属性
		MallProductInfoPO mallPro = new MallProductInfoPO();
		mallPro.setDefaultImage(mallView.getMainPic());
		mallPro.setPriceRate(mallView.getProductPriceRate() + "");

		// 2015-07-15
		mallPro.setProductVideoUrl(mallView.getProductVideoUrl());
		mallPro.setProductVideoThumbnail(mallView.getProductVideoThumbnail());
		mallPro.setIsSupportExpensive(StringUtil.toString(mallView
				.getIsSupportExpensive()));

		mallPro.setProductWeight(mallView.getProductWeight());
		mallPro.setProductWidth(mallView.getProductWidth());
		mallPro.setProductLong(mallView.getProductLong());
		;
		mallPro.setProductHeight(mallView.getProductHeight());
		mallPro.setProductDensity(mallView.getProductDensity());
		mallPro.setProductAtrNumber(mallView.getProductAtrNumber());
		mallPro.setColumn3(mallView.getColumn3());
		mallPro.setColumn4(mallView.getColumn4());

		// 点击量、浏览量、统计等信息
		if (countModel != null) {
			fillMallProInfoCountData(mallPro, countModel);// 填充点赞，浏览量，加入购物车，下单等数据
		}
		productInfo.setDetailDataBean(mallPro);

		List<ProductSKUPO> skuList = new ArrayList<ProductSKUPO>();
		if (skuViewList != null && skuViewList.size() > 0) {
			productInfo.setSkuAttributeName1(skuViewList.get(0)
					.getAttrColorName());
			productInfo.setSkuAttributeName2(skuViewList.get(0)
					.getAttrSpecName());

			for (int i = 0; i < skuViewList.size(); i++) {
				ProductSKUPO sku = new ProductSKUPO();
				ProductSkuViewModel mallSkuView = skuViewList.get(i);

				fillProSkuData(sku, mallSkuView, mallView.getProductCode());// 填充数据
				if (skuImageMap != null) {
					sku.setImageOPList(skuImageMap.get(mallSkuView
							.getProductSkuCode()));
				}
				skuList.add(sku);
			}
		}
		productInfo.setSkuList(skuList);
		return productInfo;
	}

	/**
	 * 组装productInfo<MallProductInfoPO> 对象
	 * */
	private List<ProductInfoPO<MallProductInfoPO>> wrapeProductModelList(
			List<ProductMallViewModel> productMallModelList,
			Map<Long, List<ProductSkuViewModel>> mapProductIdAndSkuModel,
			Map<String, List<ProductImagePO>> mapSkuCodeAndImagePo,
			List<ProductCountModel> countModelList,
			Map<Long, SupplierShopModel> mapProAndSupShopModel)
			throws Exception {

		if (productMallModelList == null || productMallModelList.size() == 0) {
			return null;
		}

		List<ProductInfoPO<MallProductInfoPO>> prductInfoPoList = new ArrayList<ProductInfoPO<MallProductInfoPO>>();
		ProductInfoPO<MallProductInfoPO> productInfoPo = null;

		// 遍历大横表
		for (ProductMallViewModel model : productMallModelList) {

			productInfoPo = new ProductInfoPO<MallProductInfoPO>();
			fillProductInfoData(productInfoPo, model);

			// 店铺信息
			SupplierShopModel shopModel = mapProAndSupShopModel.get(model
					.getProductId());
			if (shopModel != null) {
				fillProInfoShopData(productInfoPo, shopModel);// 填充店铺信息
			}

			// 颜色,规格
			List<ProductSkuViewModel> proSkuList = mapProductIdAndSkuModel
					.get(model.getProductId());
			if (proSkuList != null && proSkuList.size() > 0) {
				productInfoPo.setSkuAttributeName1(proSkuList.get(0)
						.getAttrColorName());
				productInfoPo.setSkuAttributeName2(proSkuList.get(0)
						.getAttrSpecName());
			}

			// 商品价格
			ProductSkuViewModel mainSkuViewModel = getProMainSkuViewModelBySkuModelList(proSkuList); // 获取主sku,主sku不在则返回第一个sku
			fillProductInfoPriceData(productInfoPo, mainSkuViewModel);// 通过主sku填充价格信息

			// 商品图片
			mainSkuViewModel = getProMainSkuViewModelBySkuList(proSkuList);// 获取主sku，如果主skuCode不存在，则返回空
			if (mainSkuViewModel != null && mapSkuCodeAndImagePo != null) {
				productInfoPo.setImageList(mapSkuCodeAndImagePo
						.get(mainSkuViewModel.getProductSkuCode()));
			}

			// 商品sku信息
			List<ProductSKUPO> productSKUPOList = getProductSKUPOListFromMap(
					model, proSkuList, mapSkuCodeAndImagePo);
			productInfoPo.setSkuList(productSKUPOList);

			// 附加私有属性
			MallProductInfoPO mallPro = new MallProductInfoPO();
			mallPro.setDefaultImage(model.getMainPic());
			mallPro.setPriceRate(model.getProductPriceRate() + "");

			// 2015-07-20新增
			mallPro.setProductVideoUrl(model.getProductVideoUrl());
			mallPro.setProductVideoThumbnail(model.getProductVideoThumbnail());
			mallPro.setIsSupportExpensive(StringUtil.toString(model
					.getIsSupportExpensive()));

			mallPro.setProductWeight(model.getProductWeight());
			mallPro.setProductWidth(model.getProductWidth());
			mallPro.setProductLong(model.getProductLong());
			;
			mallPro.setProductHeight(model.getProductHeight());
			mallPro.setProductDensity(model.getProductDensity());
			mallPro.setProductAtrNumber(model.getProductAtrNumber());
			mallPro.setColumn3(model.getColumn3());
			mallPro.setColumn4(model.getColumn4());

			// 点赞、浏览信息
			if (countModelList != null) {
				for (ProductCountModel countModel : countModelList) {
					if (model.getProductId().equals(countModel.getProductId())) {
						fillMallProInfoCountData(mallPro, countModel);// 填充点赞，浏览量，加入购物车，下单等数据
					}
				}
			}

			productInfoPo.setDetailDataBean(mallPro);
			prductInfoPoList.add(productInfoPo);
		}
		return prductInfoPoList;
	}

	/**
	 * 通过ProductMallViewModel 来填充 ProductInfoPO<MallProductInfoPO> 数据
	 * */
	private void fillProductInfoData(
			ProductInfoPO<MallProductInfoPO> productInfoPo,
			ProductMallViewModel model) {
		productInfoPo.setBrandCode(model.getBrandCode());
		productInfoPo.setBrandName(model.getBrandName());
		productInfoPo.setCategoryCode(model.getCatCode());
		productInfoPo.setCategoryName(model.getCatName());
		productInfoPo.setProductId(model.getProductId());
		productInfoPo.setProductCode(model.getProductCode());
		productInfoPo.setProductName(model.getProductName());
		productInfoPo.setProductEnName("");
		productInfoPo.setProductShelves(model.getProductShelves() + "");
		// 
		// productInfoPo.setStoreCode(model.getStoreCode);
		// TODO
		// productInfoPo.setStoreName(model.getStoreName());
		productInfoPo.setBusinessType(model.getType());
		productInfoPo.setBrandCode(model.getBrandCode());
		productInfoPo.setBrandEnName("");
		productInfoPo.setBuiltPlaceId(model.getProductProducer());
		productInfoPo.setBuiltPlaceName(model.getProductProducer());
		productInfoPo.setCurrency("CNY");

		productInfoPo.setProductDesc(model.getProductDesc());
		DecimalFormat df=new DecimalFormat("0.00"); 
		// TODO 价格
		// productInfoPo.setActivePrice();
		// productInfoPo.setFixPrice(model.getCostPrice()+"");
		productInfoPo.setMarkerPrice(df.format(model.getMarketPrice()) + "");
		productInfoPo.setPriceType(0);
		productInfoPo.setSalesPrice(df.format(model.getTshPrice()) + "");
		productInfoPo.setTb(model.getTb());

		productInfoPo.setProductPrefix(model.getProductPrefix());
		productInfoPo.setProductSuffix(model.getProductSuffix());
		productInfoPo.setProductShortName(model.getProductShortName());
		
		productInfoPo.setProductDesc(model.getProductDesc());
		productInfoPo.setZeroMaxCount(model.getZeroMaxCount());
		productInfoPo.setUserMaxNum(model.getUserMaxNum());
		productInfoPo.setUserMaxType(model.getUserMaxType());
		productInfoPo.setAttributeList(model.getAttributeList());
		productInfoPo.setIntegrityDesc(model.getIntegrityDesc());
		// 计算折扣，销售价/市场价*10 ,保留一位小数
		String discount = calCuDiscount(model.getTshPrice(),
				model.getMarketPrice());
		productInfoPo.setDiscount(discount);
	}

	/**
	 * 计算折扣，销售价/市场价*10 ,保留一位小数
	 * */
	public String calCuDiscount(double salePrice, double marketPrice) {

		if (0 == marketPrice) {
			return "0.0";
		} else {

			DecimalFormat df = new DecimalFormat("##0.0");
			BigDecimal saleDec = new BigDecimal(Double.toString(salePrice));
			BigDecimal marketDec = new BigDecimal(Double.toString(marketPrice));

			BigDecimal result = saleDec.divide(marketDec, 2,
					BigDecimal.ROUND_CEILING);// 保留两位小数，向上取整
			BigDecimal resultDiscount = result.multiply(new BigDecimal("10"));
			return df.format(resultDiscount);
		}
	}

	/**
	 * 获取商品的主skuViewModel,如果没有主sku, 则返回第一skuModel
	 * 
	 * @param skuViewModelList
	 *            商品的所有sku集合
	 * @return ProductSkuViewModel 商品的主sku
	 * */
	public ProductSkuViewModel getProMainSkuViewModelBySkuModelList(
			List<ProductSkuViewModel> skuViewModelList) {
		int flag = 0;
		if (skuViewModelList != null && skuViewModelList.size() > 0) {
			for (ProductSkuViewModel productSkuViewModel : skuViewModelList) {
				if (MAIN_SKU_LABEL.equals(productSkuViewModel.getMainSku())) {
					flag = 1;
					return productSkuViewModel;
				}
			}
			if (flag != 1) {
				return skuViewModelList.get(0);
			}
		}
		return null;
	}

	/**
	 * 获取product 主skuModel 没有主sku则返回空
	 * 
	 * @param skuViewModelList
	 *            商品的所有sku集合
	 * @return ProductSkuViewModel 商品的主sku
	 * */
	public ProductSkuViewModel getProMainSkuViewModelBySkuList(
			List<ProductSkuViewModel> skuViewModelList) {
		if (skuViewModelList != null && skuViewModelList.size() > 0) {
			for (ProductSkuViewModel productSkuViewModel : skuViewModelList) {
				if (MAIN_SKU_LABEL.equals(productSkuViewModel.getMainSku())) {
					return productSkuViewModel;
				}
			}
		}
		return null;
	}

	/**
	 * 通过主sku设置ProductInfoPO价格信息
	 * */
	private void fillProductInfoPriceData(
			ProductInfoPO<MallProductInfoPO> productInfo,
			ProductSkuViewModel mainSkuViewModel) {
		if (mainSkuViewModel != null) {
			DecimalFormat df=new DecimalFormat("0.00"); 
			productInfo.setMarkerPrice(df.format(mainSkuViewModel
					.getMarketPrice()) + "");
			productInfo.setPriceType(0);
			productInfo.setSalesPrice(df.format(mainSkuViewModel.getTshPrice())
					+ "");
			productInfo.setTb(mainSkuViewModel.getTb());

			// 计算折扣，销售价/市场价*10 ,保留一位小数
			String discount = calCuDiscount(mainSkuViewModel.getTshPrice(),
					mainSkuViewModel.getMarketPrice());
			productInfo.setDiscount(discount);

		}
	}

	/**
	 * 用ProductCountModel 填充 MallProductInfoPO的 点赞，浏览量，加入购物车，下单等数据
	 * */
	private void fillMallProInfoCountData(MallProductInfoPO mallPro,
			ProductCountModel countModel) {
		mallPro.setComments(countModel.getComments() == null ? 0 : countModel
				.getComments().intValue());
		mallPro.setSales(countModel.getSales() == null ? 0 : countModel
				.getSales().intValue());
		mallPro.setViews(countModel.getViews() == null ? 0 : countModel
				.getViews().intValue());
		mallPro.setCarts(countModel.getCarts() == null ? 0 : countModel
				.getCarts().intValue());
		mallPro.setCollects(countModel.getCollects() == null ? 0 : countModel
				.getCollects().intValue());
		mallPro.setOrders(countModel.getOrders() == null ? 0 : countModel
				.getOrders().intValue());
		mallPro.setApplaudCount(countModel.getApplaudCount() == null ? 0
				: countModel.getApplaudCount().intValue());

		mallPro.setFavorCount(countModel.getFavorCount() == null ? 0
				: countModel.getFavorCount());
	}

	/**
	 * 用SupplierShopModel 填充 MallProductInfoPO的 店铺信息：店铺id，店铺name，客服电话等
	 * */
	private void fillProInfoShopData(
			ProductInfoPO<MallProductInfoPO> productInfo,
			SupplierShopModel shopModel) {
		productInfo.setStoreId(shopModel.getId());
		productInfo.setStoreName(shopModel.getName());
		productInfo
				.setCustomerServicePhone(shopModel.getCustomerServicePhone());// 客户电话
	}

	/**
	 * 组装ProductSKUPO 对象数据 用ProductSkuViewModel 填充 ProductSKUPO的
	 * */
	private void fillProSkuData(ProductSKUPO sku,
			ProductSkuViewModel mallSkuView, String productCode) {
		DecimalFormat df=new DecimalFormat("0.00");  
		sku.setProductCode(productCode);
		sku.setCurrency("CNY");
		sku.setSkuId(Integer.valueOf(mallSkuView.getProductSkuId() + ""));
		sku.setSkuCode(mallSkuView.getProductSkuCode());
		sku.setMainSku(mallSkuView.getMainSku());
		// sku.setActivePrice(mallSkuPO.getP);
		sku.setFixPrice(df.format(mallSkuView.getCostPrice()) + "");
		sku.setSalesPrice(df.format(mallSkuView.getTshPrice()) + "");
		sku.setPoints(mallSkuView.getTb());// 特币
		sku.setMarkerPrice(df.format(mallSkuView.getMarketPrice()) + "");

		String discount = calCuDiscount(mallSkuView.getTshPrice(),
				mallSkuView.getMarketPrice());
		sku.setDiscount(discount);

		sku.setPriceType(0);
		// sku.setSkuAttributeValue1(mallSkuView.getAttrColorValue());
		// sku.setSkuAttributeValue2(mallSkuView.getAttrSpecValue());
		sku.setSkuAttributeValue1(mallSkuView.getColorValueAlias() != null ? mallSkuView
				.getColorValueAlias() : mallSkuView.getAttrColorValue());
		sku.setSkuAttributeValue2(mallSkuView.getSpecValueAlias() != null ? mallSkuView
				.getSpecValueAlias() : mallSkuView.getAttrSpecValue());

	}
 

	@Override
	public Long getProductShelvesFiledByProductCode(String productCode)
			throws Exception {
		Long shelves = productMallViewDao
				.getProductShelvesFiledByProductCode(productCode);
		return shelves;
	}


}
