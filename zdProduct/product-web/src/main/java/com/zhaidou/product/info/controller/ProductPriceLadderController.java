package com.zhaidou.product.info.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.manager.ProductPriceLadderManager;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductOperateModel;
import com.zhaidou.product.info.model.ProductPriceLadderModel;
import com.zhaidou.product.info.service.ProductOperateService;
import com.zhaidou.product.info.util.ExcelUtil;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value = "/info/price/")
public class ProductPriceLadderController {

	private static final Log logger = LogFactory
			.getLog(ProductPriceLadderController.class);

	@Resource
	ProductPriceLadderManager productPriceLadderManager;

	@Resource
	private ProductManager productManager;

	@Resource
	private ProductOperateService productOperateService;

	/**
	 * 转至商品列表页面
	 * 
	 * @param page
	 * @param productModel
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String productLadderList(Page page,
			ProductPriceLadderModel productPriceLadderModel,
			HttpServletRequest req) {
		Object list = null;
		Object pageResult = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map = productPriceLadderManager.getProductPriceLadder(
					productPriceLadderModel, page);
			list = map.get("list");
			pageResult = map.get("page");

		} catch (Exception e) {
			logger.error("productLadderList", e);
			req.setAttribute("errorMsg", e.getMessage());
			return InfoUtil.ERROR_500JSP;
		}

		req.setAttribute("list", list);
		req.setAttribute("page", pageResult);

		return "info/price/list";
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String delete(Map<String, Object> map, @PathVariable String id) {

		String strReturn = null;
		try {

			productPriceLadderManager.deleteById(id);

			AjaxObject returnAjax = null;

			returnAjax = AjaxObject.newOk("删除成功");

			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl("info/price/list");
			strReturn = returnAjax.toString();
		} catch (Exception e) {
			logger.error("删除异常", e);
			strReturn = AjaxObject.newError("删除失败==" + e.getMessage())
					.toString();
		}

		return strReturn;

	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String deleteByids(Map<String, Object> map, Long[] ids) {

		String strReturn = null;
		try {
			List<Long> idList = Arrays.asList(ids);

			productPriceLadderManager.deleteByIds(idList);

			AjaxObject returnAjax = null;

			returnAjax = AjaxObject.newOk("删除成功" + JSONUtil.toString(idList));
			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl("info/price/list");
			strReturn = returnAjax.toString();
		} catch (Exception e) {
			logger.error("删除异常", e);
			strReturn = AjaxObject.newError("删除失败:" + e.getMessage())
					.toString();
		}

		return strReturn;

	}

	/**
	 * 转至添加页面
	 */
	@RequestMapping(value = "/to_add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toAdd(Map<String, Object> map) {

		return "info/price/add";
	}

	/**
	 * 添加操作
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public String Add(ProductPriceLadderModel productPriceLadderModel,
			HttpServletResponse resp, HttpServletRequest req) {
		Map<String,Object> user = (Map<String,Object>)req.getSession().getAttribute("user");
	/*	if(user==null)
		{
		 user=new HashMap<String, Object>(); //仅仅是测试
		 user.put("userId", "15555");//仅仅是测试
		 user.put("userName", "userName");//仅仅是测试
		}*/
		String strReturn = null;
		try {
			productPriceLadderModel.setProductName(req.getParameter(
					"productModel.productName").toString());
			productPriceLadderModel
					.setProductCode(req
							.getParameter("productModel.productCode")
							.toString().trim());
			productPriceLadderModel.setProductId(Long.parseLong(req
					.getParameter("productModel.productId")));

			productPriceLadderModel.setSupplierId(Long.parseLong(req
					.getParameter("productModel.supplierId")));
			productPriceLadderModel.setPriceLevel1(Long.parseLong(req
					.getParameter("productModel.markUpRate")));

			if (checkPriceLadder(productPriceLadderModel)) {

				return AjaxObject.newError(
						"加价率大小关系错误:一级加价率>=二级加价率>=三级加价率>=四级加价率").toString();
			}

			productPriceLadderModel.setCreateBy(Long.parseLong(user.get(
					"userId").toString()));
			productPriceLadderModel.setCreateUserName(user.get("userName")
					.toString());

			productPriceLadderManager
					.addProductPriceLadder(productPriceLadderModel);

			strReturn = AjaxObject.newOk("添加成功").toString();
		} catch (DuplicateKeyException e1) {
			logger.error("添加异常", e1);
			return AjaxObject.newError("添加异常信息:不能添加重复的产品！").toString();
		} catch (Exception e) {
			logger.error("添加异常", e);
			if (e != null
					&& e.getCause() != null
					&& e.getCause().toString() != null
					&& e.getCause()
							.toString()
							.startsWith(
									"org.springframework.dao.DuplicateKeyException")) {
				return AjaxObject.newError("添加异常信息:不能添加重复的产品！").toString();

			} else {
				return AjaxObject.newError("添加异常信息:" + e.getMessage())
						.toString();
			}

		}

		return strReturn;
	}

	private boolean checkPriceLadder(
			ProductPriceLadderModel productPriceLadderModel) {
		Long[] prices = new Long[4];
		prices[0] = productPriceLadderModel.getPriceLevel1();
		prices[1] = productPriceLadderModel.getPriceLevel2();
		prices[2] = productPriceLadderModel.getPriceLevel3();
		prices[3] = productPriceLadderModel.getPriceLevel4();

		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				// logger.debug(prices[i]+" "+prices[j]);
				if (prices[i] < prices[j]) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 转到修改地址页面
	 * 
	 * @author mingbao.wu
	 * @param id
	 * @param productAttrModel
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/to_update/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toUpdate(@PathVariable String id, Map<String, Object> map) {

		ProductPriceLadderModel ProductPriceLadderModel = null;
		try {
			ProductPriceLadderModel = productPriceLadderManager
					.getProductPriceLadderById(id);
		} catch (Exception e) {
			logger.error(e);
			map.put("errorMsg", e.getMessage());
			return InfoUtil.ERROR_500JSP;
		}

		map.put("ProductPriceLadderModel", ProductPriceLadderModel);

		return "info/price/update";
	}

	/**
	 * 更新地址
	 * 
	 * @param addressModel
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ResponseBody
	public String update(ProductPriceLadderModel productPriceLadderModel,
			HttpServletRequest req) {

		Map<String, Object> user = (Map<String, Object>) req.getSession()
				.getAttribute("user");
		// if(user==null)
		// {
		// user=new HashMap<String, Object>(); //仅仅是测试
		// user.put("userId", "15555");//仅仅是测试
		// user.put("userName", "userName");//仅仅是测试
		// }
		String returnStr = null;
		try {

			if (checkPriceLadder(productPriceLadderModel)) {

				return AjaxObject.newError(
						"加价率大小关系错误:一级加价率>=二级加价率>=三级加价率>=四级加价率").toString();
			}

			productPriceLadderModel.setUpdateTime(new Date());
			productPriceLadderModel.setUpdateUserName(user.get("userName")
					.toString());
			productPriceLadderModel.setUpdateBy(Long.parseLong(user.get(
					"userId").toString()));
			// 更新两张表// 另外的表是product_operate// 一级价格调整率 加价率
			productPriceLadderManager.updateProductPriceLadder(
					productPriceLadderModel, user);

			returnStr = AjaxObject.newOk("更新成功").toString();
		} catch (Exception e) {
			returnStr = AjaxObject.newError("更新失败=" + e.getMessage())
					.toString();
			logger.error("更新异常", e);
		}
		return returnStr;
	}

	@RequestMapping(value = "/selectback", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String selectBack(ProductModel productModel, Page page,
			Map<String, Object> map) {
		List<ProductModel> productList = null;
		List<ProductOperateModel> productOperateList = null;
		Double markUpRate;
		ProductOperateModel productOperateModel = null;
		try {

			productList = productManager.getProductListAndMarkUpRate(page,
					productModel);

		} catch (Exception e) {
			logger.error(e);
			map.put("errorMsg", e.getMessage());
			return InfoUtil.ERROR_500JSP;
		}

		map.put("productList", productList);
		map.put("page", page);
		map.put("target", "dialog");
		return "info/price/select_product_list";
	}

	/**
	 * 转至添加页面
	 */
	@RequestMapping(value = "/to_add_excel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String toAddExcel(Map<String, Object> map) {

		return "info/price/add_excel";
	}

	/**
	 * 添加操作
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/add_excel", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String AddExcel(@RequestParam("file") MultipartFile file,
			HttpServletRequest req) {
		Map<String, Object> user = (Map<String, Object>) req.getSession()
				.getAttribute("user");
		// if(user==null)
		// {
		// user=new HashMap<String, Object>(); //仅仅是测试
		// user.put("userId", "15555");//仅仅是测试
		// user.put("userName", "userName");//仅仅是测试
		// }
		String strReturn = AjaxObject.newError("添加或者修改异常!").toString();
		List<ProductModel> productList = null;

		Page page = new Page();
		page.setNumPerPage(9999999);
		page.setPageNum(1);
		ProductModel productModel = new ProductModel();
		try {
			List<ProductPriceLadderModel> excelModelList = new ArrayList<ProductPriceLadderModel>();

			uploadProductAddExcel(file,
					"/templates/productPriceLadderList.xml", excelModelList);

			List<ProductPriceLadderModel> priceLadderList = new ArrayList<ProductPriceLadderModel>();

			productList = productManager.getProductList(page, productModel);
			String missProduct = "";
			for (ProductPriceLadderModel priceLadder : excelModelList) {

				if (priceLadder != null
						&& priceLadder.getProductCode() != null
						&& (priceLadder.getPriceLevel1() == null
								|| priceLadder.getPriceLevel2() == null
								|| priceLadder.getPriceLevel3() == null || priceLadder
								.getPriceLevel4() == null)
						&& !priceLadder.getProductCode().equals("商品编码")) {

					return AjaxObject.newError(
							"产品编码为" + priceLadder.getProductCode()
									+ "的加价率错误，请确认加价率都是整数!").toString();

				}

				if (priceLadder != null && priceLadder.getPriceLevel1() != null
						&& !priceLadder.getPriceLevel1().equals("")) {

					if (checkPriceLadder(priceLadder)) {

						return AjaxObject
								.newError(
										"产品编码为"
												+ priceLadder.getProductCode()
												+ "的加价率大小关系错误:一级加价率>=二级加价率>=三级加价率>=四级加价率")
								.toString();
					}

					for (ProductModel product : productList) {
						if (priceLadder != null
								&& product != null
								&& priceLadder.getProductCode().equals(
										product.getProductCode())) {
							priceLadder.setProductId(product.getProductId());
							priceLadder
									.setProductName(product.getProductName());
							priceLadder.setSupplierId(product.getSupplierId());
							priceLadder.setCreateBy(Long.parseLong(user.get(
									"userId").toString()));
							priceLadder.setCreateUserName(user.get("userName")
									.toString());
							break;
						}
					}

					// 找到对应的产品才修改或者添加 加价率
					if (priceLadder != null
							&& priceLadder.getProductId() != null
							&& priceLadder.getProductCode() != null
							&& priceLadder.getProductName() != null) {
						priceLadderList.add(priceLadder);
					} else {
						missProduct = priceLadder.getProductCode() + ";";
					}
				}

			}
			if (priceLadderList != null && priceLadderList.size() > 0) {

				productPriceLadderManager.addProductPriceLadder(
						priceLadderList, user);
			}
			if (!missProduct.equals("")) {
				strReturn = AjaxObject.newOk(
						"添加或者修改" + priceLadderList.size() + "个商品加价率成功")
						.toString();
			} else {
				strReturn = AjaxObject.newOk(
						"添加或者修改" + priceLadderList.size() + "个商品加价率成功。"
								+ "以下产品不存在，所有不能新增或者修改加价率:" + missProduct)
						.toString();
			}
		} catch (DuplicateKeyException e1) {
			logger.error("添加或者修改异常", e1);
			return AjaxObject.newError("添加或者修改异常信息:不能添加重复的产品！").toString();
		} catch (Exception e) {
			logger.error("添加或者修改异常", e);
			if (e != null
					&& e.getCause() != null
					&& e.getCause().toString() != null
					&& e.getCause()
							.toString()
							.startsWith(
									"org.springframework.dao.DuplicateKeyException")) {
				return AjaxObject.newError("添加或者修改异常信息:不能添加或者修改重复的产品！")
						.toString();

			} else {
				return AjaxObject.newError("添加或者修改异常信息:" + e.getMessage())
						.toString();
			}
		}
		return strReturn;
	}

	/**
	 * 通过xml定义excel格式，解析用户上传的excel文件，返回list<model> <dependency>
	 * <groupId>net.sf.jxls</groupId> <artifactId>jxls-reader</artifactId>
	 * <version>1.0.6</version> </dependency>
	 * 
	 * @param file
	 * @param xmlTemmplatePath
	 *            xmlTemmplatePath
	 *            将xml模版文件(taobao.xml)放在src/main/resources/templates
	 *            那么xmlTemmplatePath="/templates/taobao.xml"
	 * @param excelModelList
	 *            List<T> excelModelList 示例 List<TaoBaoExcelModel>
	 *            excelModelList = new ArrayList<TaoBaoExcelModel>(); <loop
	 *            startRow="1" endRow="1" items="list" var="taoBaoExcelModel"
	 *            varType="com.teshehui.product.info.model.TaoBaoExcelModel">
	 * @return
	 */
	public <T> List<T> uploadProductAddExcel(MultipartFile file,
			String xmlTemmplatePath, List<T> list) throws Exception {

		Map<String, Object> beans = new HashMap<String, Object>();
		InputStream inputXLS = null;
		XLSReader reader;
		// 读取excel
		try {
			// 读取用户导入的Excel 生成一个taobaoExcelModel list
			beans.put("list", list);
			InputStream inputXML = new BufferedInputStream(getClass()
					.getResourceAsStream(xmlTemmplatePath));
			reader = ReaderBuilder.buildFromXML(inputXML);
			ReaderConfig.getInstance().setSkipErrors(true);
			inputXLS = file.getInputStream();
			reader.read(inputXLS, beans);

		} catch (Exception e) {
			logger.error(e);
			throw new ZhaidouRuntimeException(e);
		}
		return list;
	}

	/**
	 * 在售商品导出
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/export_product", method = { RequestMethod.GET })
	@ResponseBody
	public String exportProductExcel(HttpServletResponse response,
			ProductPriceLadderModel productPriceLadderModel, Page page,
			HttpServletRequest req) {

		page.setPageNum(1);
		page.setNumPerPage(5000); // 最多导出1000条 根据配置文件
		Map<String, Object> map = productPriceLadderManager
				.getProductPriceLadder(productPriceLadderModel, page);

		exportProductExcel(response,
				(List<ProductPriceLadderModel>) map.get("list"),
				"config/excel/priceLadderExport.xls", req,
				"productPriceLadderList.xls");

		return null;
	}

	/**
	 * 通过excel模版 ,modelList,response,require,导出数据
	 * 
	 * @param response
	 * @param list
	 * @param templateFileName
	 * @param req
	 * @param filename
	 * @return
	 */
	public String exportProductExcel(HttpServletResponse response,
			List<?> list, String templateFileName, HttpServletRequest req,
			String filename) {

		String resultFileName = "config/excel/" + new Random(10000).toString()
				+ filename;
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.createExcel(templateFileName, list, resultFileName);

		try {
			XLSTransformer transformer = new XLSTransformer();
			Workbook wb = null;
			ServletOutputStream outputStream = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment; filename="
					+ filename);// export file name

			URL url = this.getClass().getClassLoader().getResource("");

			// String srcFilePath = url.getPath() + templateFileName;
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("list", list);
			String destFilePath = url.getPath() + resultFileName;

			try {
				wb = transformer.transformXLS(
						new FileInputStream(destFilePath), beanParams);
			} catch (ParsePropertyException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}

			try {
				wb.write(outputStream);
				outputStream.flush();
				outputStream.close();
			} finally {
				outputStream.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
