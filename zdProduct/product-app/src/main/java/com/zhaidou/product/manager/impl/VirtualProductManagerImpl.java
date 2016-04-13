package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.VirtualProductManager;
import com.zhaidou.product.model.VirtualProductModel;
import com.zhaidou.product.po.VirtualProductPO;
import com.zhaidou.product.service.VirtualProductService;

@Service("virtualProductManager")
public class VirtualProductManagerImpl implements VirtualProductManager {

	private Logger logger = Logger.getLogger(VirtualProductManagerImpl.class);

	@Resource
	private VirtualProductService virtualProductService;

	/**
	 * 通过RequestObject.requestParams=list[skuCode]获取商品list
	 * 
	 * @param requestObj
	 *            RequestObject.requestParams
	 * @return ResponseObject.data=list[VirtualProduct]
	 * */
	@Override
	public ResponseObject getVirtualProductByCodes(RequestObject requestObj) {
		ResponseObject result = null;
		VirtualProductModel virtualProduct = null;
		List<String> virtualProductCodeList = (List<String>) requestObj
				.getRequestParams();
		List<VirtualProductModel> virtualProductModelList = new ArrayList<VirtualProductModel>();
		Map<String, Object> virtualProductPOMap = new HashMap<String, Object>();

		for (String code : virtualProductCodeList) {
			virtualProductPOMap.put(code, null);
		}

		VirtualProductPO virtualProductPO = null;
		String requestPamas = "";
		try {
			requestPamas = JSON.toJSONString(virtualProductCodeList);
		} catch (Exception e) {
		}
		try {

			logger.debug("查询商品是否支持贵就赔请求参数:" + requestPamas);
			if (virtualProductCodeList != null
					&& virtualProductCodeList.size() > 0) {
				virtualProductModelList = virtualProductService
						.getVirtualProductBySkuCodes(virtualProductCodeList);

				if (virtualProductModelList != null
						&& virtualProductModelList.size() > 0) {
					for (VirtualProductModel vp : virtualProductModelList) {
						virtualProductPO = new VirtualProductPO();
						virtualProductPO = (VirtualProductPO) virtualProductService
								.copyModeltoPo(vp, virtualProductPO);
						virtualProductPOMap.put(virtualProductPO.getSkuCode(),
								virtualProductPO);
					}
				}

				try {
					logger.debug("根据codes查询虚拟商品返回参数:"
							+ JSON.toJSONString(virtualProductPOMap));

				} catch (Exception e) {
				}
				return new ResponseObject(ResponseObject.CODE_SUCCESS_VALUE,
						null, "请求成功!", virtualProductPOMap);
			} else {
				return new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
						"28801011", "请求失败,请求参数为空！", null);
			}

		}

		catch (Exception e) {
			logger.error(e);
			result = new ResponseObject(ResponseObject.CODE_FAILURE_VALUE,
					"48801011", "请求异常:" + e.getMessage(), null);
		}

		return result;
	}

}
