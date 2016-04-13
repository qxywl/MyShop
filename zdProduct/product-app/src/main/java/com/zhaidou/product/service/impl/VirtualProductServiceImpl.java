/*
 * 文 件 名:  VirtualProductServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.VirtualProductDao;
import com.zhaidou.product.model.VirtualProductModel;
import com.zhaidou.product.po.VirtualProductPO;
import com.zhaidou.product.service.VirtualProductService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author mingbao
 * @version [版本号, 2015-10-01]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("virtualProductService")
public class VirtualProductServiceImpl implements VirtualProductService {
	private static final Log logger = LogFactory
			.getLog(VirtualProductServiceImpl.class);

	@Resource
	private VirtualProductDao virtualProductDao;

	public void addVirtualProduct(VirtualProductModel virtualProductModel)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--addVirtualProduct Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		virtualProductDao.insert(virtualProductModel);
	}

	public void updateVirtualProductById(VirtualProductModel virtualProductModel)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--addVirtualProduct  Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		virtualProductDao.update(virtualProductModel);
	}

	public VirtualProductModel getVirtualProductById(
			VirtualProductModel virtualProductModel) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProductById Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		VirtualProductModel result = virtualProductDao
				.queryOne(virtualProductModel);
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProductById result-->"
					+ JSON.toJSONString(result));
		}
		return result;
	}

	public long getVirtualProductCount(VirtualProductModel virtualProductModel)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProductCount Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		long result = virtualProductDao.countListPage(virtualProductModel);
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProductCount result-->"
					+ JSON.toJSONString(result));
		}
		return result;
	}

	public List<VirtualProductModel> getVirtualProduct(
			VirtualProductModel virtualProductModel, Page page)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProduct Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		long count = virtualProductDao.countListPage(virtualProductModel);
		List<VirtualProductModel> result = null;
		if (count > 0) {
			page.setTotalCount(count);
			result = virtualProductDao.queryListPage(virtualProductModel,
					page.getPageNum(), page.getNumPerPage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--getVirtualProduct result-->"
					+ JSON.toJSONString(result));
		}
		return result;
	}

	public void deleteById(VirtualProductModel virtualProductModel)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--deleteById Params-->"
					+ JSON.toJSONString(virtualProductModel));
		}
		virtualProductDao.delete(virtualProductModel);
	}

	@Override
	public void deleteByIds(List<Long> ids) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("--deleteByIds Params-->" + JSON.toJSONString(ids));
		}
		virtualProductDao.deleteByIds(ids);

	}

	@Override
	public List<VirtualProductModel> getVirtualProductBySkuCodes(
			List<String> virtualProductCodeList) throws Exception {
		List<VirtualProductModel> list = null;
		list = virtualProductDao.queryVirtualProductBySkuCodes(
				virtualProductCodeList, 1, 99999);
		return list;
	}

	@Override
	public <T> T copyModeltoPo(T vpModel, T virtualProductPO) throws Exception {

		Class<?> model = vpModel.getClass();
		Class<?> po = virtualProductPO.getClass();

		Field[] modelFields = model.getDeclaredFields();
		Field[] poFields = po.getDeclaredFields();
		List<Field> copyFields = new ArrayList<Field>();

		for (Field modelFiels : modelFields) {

			for (Field poField : poFields) {
				if (modelFiels.getName().equals(poField.getName())) {
					copyFields.add(poField);
					break;
				}
			}

		}

		for (Field field : copyFields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			// 获取成员变量的名字
			String name = field.getName(); // 获取成员变量的名字，此处为id，name,age
			// System.out.println(name);
			try {
				// 获取get和set方法的名字
				String firstLetter = name.substring(0, 1).toUpperCase(); // 将属性的首字母转换为大写
				String getMethodName = "get" + firstLetter + name.substring(1);
				String setMethodName = "set" + firstLetter + name.substring(1);
				// System.out.println(getMethodName + "," + setMethodName);

				// 获取方法对象
				Method getMethod = model.getMethod(getMethodName,
						new Class[] {});
				Method setMethod = po.getMethod(setMethodName,
						new Class[] { field.getType() });// 注意set方法需要传入参数类型

				// 调用get方法获取旧的对象的值
				Object value = getMethod.invoke(vpModel, new Object[] {});
				// 调用set方法将这个值复制到新的对象中去

				setMethod.invoke(virtualProductPO, new Object[] { value });
			} catch (Exception e) {
				logger.error(e);
			}

		}
		return virtualProductPO;

	}

}
