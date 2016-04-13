package com.zhaidou.product.info.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.util.Constants;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.Util.StringUtil;
import com.zhaidou.product.Util.UserHelper;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;
import com.zhaidou.product.info.service.ProductPurchaseStockService;

@Controller
@RequestMapping(value="info/purchaseStock")
public class ProductPurchaseStockController {
	
	 private static Logger logger = Logger.getLogger(ProductPurchaseStockController.class);
	 private static String list = "info/erpPurchase/list"; //采购单列表
	 
	 /**导出字段和表名*/
	 private static String [] strExportTitle ={"商品代码","规格代码","公共库数量"};
	 private static String [] strExportField ={"spdm","skudm","publicStock"};
	
	 @Resource
	 private ProductPurchaseStockService productPurchaseStockService;
	
	/**
	 * 获取采购库列表
	 * @param page
	 * @param productPurchaseStockModel
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getPurchaseList(Page page, ProductPurchaseStockModel productPurchaseStockModel, Map<String, Object> map, HttpServletRequest request){
		List<ProductPurchaseStockModel> purchaseStockList = null;
		try {
			purchaseStockList = productPurchaseStockService.purchaseStockList(productPurchaseStockModel, page);
			map.put("productPurchaseStockModel", productPurchaseStockModel);
		    map.put("purchaseStockList", purchaseStockList);
		    map.put("page", page);
		} catch (Exception e) {
			logger.info("获取采购库列表异常："+ e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出excel校验
	 * @param page
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkExport")
	public void checkExport(HttpServletRequest request, HttpServletResponse response)throws Exception{
		JSONObject jsonObject = new JSONObject();
		List<ProductPurchaseStockModel> erpStockList = null;
		erpStockList = productPurchaseStockService.getpurchaseStockListByStatus();
		if(erpStockList == null || erpStockList.size() <= 0){
			jsonObject.put("result", false);
			response.getWriter().write(jsonObject.toString());//返回json对象
		}else{
			jsonObject.put("result", true);
			response.getWriter().write(jsonObject.toString());//返回json对象
		}
		response.getWriter().flush();
		response.getWriter().close(); 
	}
	
	/**
     * 导出所有商品
     * @return page 分页信息
     * @return String 返回JSON字符格式结果
     * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/export",method={RequestMethod.GET, RequestMethod.POST})
    public void exportErpStock(Page page,HttpServletResponse resp, HttpServletRequest request)throws IOException{
    	
        List<ProductPurchaseStockModel> erpStockList = null;
       
        String fileType=".xlsx";
        String fileName = "erp库存信息.xlsx";
        String sheetName = "erp库存列表";
    	
    	List<String> titleList = Arrays.asList(strExportTitle);
    	List<String> fieldList = Arrays.asList(strExportField);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
        	//获取需要导出excel数据
    		 erpStockList = productPurchaseStockService.getpurchaseStockListByStatus();
    			 book = new ExcelUtil().writeExcel(fileType,sheetName, titleList, fieldList, erpStockList);
                 resp.reset();
                 resp.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtil.toUtf8String(fileName) + "\"");
                 resp.setContentType("application/vnd.ms-excel; charset=utf-8");
                 os = resp.getOutputStream();
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("assignStatus", Constants.STATUS_YES);
                 map.put("assignBy", UserHelper.getUserName(request));
                 //修改导出excel数据状态
                 productPurchaseStockService.updateAssignStatusAndAssignBy(map);
                 book.write(os);
        } catch (Exception e) {
        	logger.error("export库存异常信息==",  e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	resp.flushBuffer();
    }
	
}
