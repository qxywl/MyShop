package com.zhaidou.product.category.controller;

import com.mongodb.DBObject;
import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.BaseCategoryOperateLog;
import com.zhaidou.product.category.service.BaseCategoryLogService;
import com.zhaidou.product.category.service.CategoryOptType;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: BaseCategoryController.java
 *
 * @Package com.teshehui.product.category.controller
 *
 * @Description: 基础分类Controller
 *
 * @author lvshuding
 *
 * @date 2015年3月25日 上午10:27:29
 *
 * @version V1.0
 */
@Controller("baseCategoryLogController")
@RequestMapping("/category/log")
public class BaseCategoryLogController extends BaseController {

	private static Logger logger = Logger.getLogger(BaseCategoryLogController.class);
	private final static Integer limitRows = 3000; // 限制导出三千条

	private final static String LOG_LIST = "category/log/list";
	private final static String LOG_MAIN = "category/log/list";
	private final static String LOG_UPLOAD = "category/log/upload"; // 详情页面
	private final static String LOG_ADD = "category/log/add";
	private final static String LOG_UPDATE = "category/log/update";
	/** 导出字段和表名 */
	private static String[] strHeaders = { "ID", "基础分类ID", "基础分类名称", "基础分类编码", "父分类ID", "父分类编码", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
	private static String[] strField = { "id", "categoryId", "categoryName", "categoryCode", "parentId", "parentCode", "operateType", "operateUser", "operateTime", "logDate" };

	@Autowired
	private BaseCategoryLogService baseCategoryLogService;

	@RequestMapping("/main")
	public String toMain(Model model, Page page) {
		/*
		 * 校验用户登录
		 */

		page.setPageNum(1);
		List<BaseCategoryOperateLog> logList = baseCategoryLogService
				.getCategoryOperateLog(null, page);

		// model.addAllAttributes(map);
		model.addAttribute("list", logList);
		model.addAttribute("page", page);
		// model.addAttribute("orgTreeStr",Utils.buildJsonFormTree(poList,req.getParameter(CURRENT_CATE_ID)));

		// model.addAttribute(MSG_NAME, req.getParameter(MSG_NAME));

		return LOG_MAIN;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String logList(Model model, Page page, BaseCategoryOperateLog baseCategoryOperateLog) {
		List<BaseCategoryOperateLog> logList = baseCategoryLogService
				.getCategoryOperateLog(baseCategoryOperateLog, page);

		model.addAttribute("list", logList);
		model.addAttribute("page", page);
		model.addAttribute("baseCategoryOperateLog", baseCategoryOperateLog);
		return LOG_LIST;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return LOG_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@ModelAttribute BaseCategoryOperateLog baseCategoryOperateLog) {
		baseCategoryOperateLog.setOperateType(CategoryOptType.APPEND.getValue());
		baseCategoryOperateLog.setLogDate(new Date());
		// baseCategoryOperateLog.setOperateUser("test");
		// baseCategoryOperateLog.setOperateTime(new Date());
		String strReturn;
		try{
			baseCategoryLogService.insertCategoryOperateLog(baseCategoryOperateLog);
			strReturn = AjaxObject.newOk("插入成功").setNavTabId("brand").toString();
		}catch(Exception e){
			logger.info("插入日志信息异常==" + e.getMessage());
			strReturn = AjaxObject.newError("插入日志异常信息==" + e.getMessage()).toString();
		}
		return strReturn;
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String toUpdate(@PathVariable Long id, Model model) {
		BaseCategoryOperateLog baseCategoryOperateLog = new BaseCategoryOperateLog();
		baseCategoryOperateLog.setId(id);
		try {
			baseCategoryOperateLog = baseCategoryLogService
					.getCategoryOperateLogById(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取日志信息操作异常信息==" + e.getMessage());
		}
		model.addAttribute("baseCategoryOperateLog", baseCategoryOperateLog);
		return LOG_UPDATE;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute BaseCategoryOperateLog baseCategoryOperateLog) {
		String strReturn = null;
		try {
			baseCategoryLogService.updateCategoryOperateLog(baseCategoryOperateLog);
			strReturn = AjaxObject.newOk("更新成功").setNavTabId("brand").toString();
		} catch (Exception e) {
			logger.info("更新日志异常信息==" + e.getMessage());
			strReturn = AjaxObject.newError("更新日志异常信息==" + e.getMessage()).toString();
		}
		return strReturn;
	}

	/**
	 * 日志记录删除
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public String delete(@PathVariable Long id) {
		
		String strReturn = null;
		AjaxObject returnAjax = null;
		BaseCategoryOperateLog baseCategoryOperateLog = new BaseCategoryOperateLog();
		baseCategoryOperateLog.setId(id);
		baseCategoryOperateLog.setOperateType(CategoryOptType.DELETE.getValue());
		try {
			
			baseCategoryLogService.deleteById(baseCategoryOperateLog);
			returnAjax = AjaxObject.newOk("删除成功");
			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl(LOG_LIST);

		} catch (Exception e) {
			logger.info("更新品牌异常信息==" + e.getMessage());
			returnAjax = AjaxObject.newError("删除品牌异常信息==" + e.getMessage());
			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl(LOG_LIST);
		}

		strReturn = returnAjax.toString();
		return strReturn;
	}

	/**
	 * 日志记录批量删除
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	@ResponseBody
	public String batchDelete(@RequestParam(value="ids",required=true) List<Long> ids ) {
		
		String strReturn = null;
		AjaxObject returnAjax = null;
		try {
			
			baseCategoryLogService.deleteByIds(ids);
			returnAjax = AjaxObject.newOk("删除成功");
			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl(LOG_LIST);

		} catch (Exception e) {
			logger.info("删除品牌异常信息==" + e.getMessage());
			returnAjax = AjaxObject.newError("删除品牌异常信息==" + e.getMessage());
			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
			returnAjax.setForwardUrl(LOG_LIST);
		}

		strReturn = returnAjax.toString();
		return strReturn;
	}
	
	/**
	 * 导出
	 * */
	@RequestMapping(value = "/export", method = { RequestMethod.GET })
	@ResponseBody
	public String exportLog(Page page, HttpServletResponse resp, HttpServletRequest req) {

		BaseCategoryOperateLog baseCategoryOperateLog = new BaseCategoryOperateLog();
		String strReturn = null;
		AjaxObject returnAjax = null;
		page.setNumPerPage(limitRows);
//		String fileType = ".xlsx";
//		String sheetName = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();

//		List<String> headerList = Arrays.asList(strHeaders);
//		List<String> fieldList = Arrays.asList(strField);

		page.setNumPerPage(limitRows);
		String fileName = "基础分类操作日志" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
		String tplPath = req.getSession().getServletContext().getRealPath("/resources/base_category_log_template.xlsx") ;
		Workbook book = null;
		OutputStream os = null;
		try {

//			List<BaseCategoryOperateLog> logList = baseCategoryLogService
//					.getCategoryOperateLog(baseCategoryOperateLog, page);
			book = baseCategoryLogService.exportExcel(baseCategoryOperateLog, page, tplPath);
//			book = new ExcelIO<BaseCategoryOperateLog>().export(fileType, sheetName, headerList, fieldList, logList);
			resp.reset();
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + toUtf8String(fileName, req) + "\"");
			resp.setContentType("application/vnd.ms-excel; charset=utf-8");
			os = resp.getOutputStream();
			book.write(os);
			returnAjax = AjaxObject.newOk("导出成功");
		} catch (Exception e) {

			logger.info("导出日志异常信息==" + e.getMessage());
			returnAjax = AjaxObject.newError("导出日志异常信息==" + e.getMessage());

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error("关闭输出流出错", e);
				}
			}
		}

		strReturn = returnAjax.toString();
		return strReturn;
	}

	/**
	 * 转至上传页面
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String toUpload() {
		return LOG_UPLOAD;
	}

	/**
	 * 导入名单
	 * */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/import_excel", method = { RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	public String uploadExcel(MultipartHttpServletRequest request, HttpServletResponse response) {

		AjaxObject returnAjax = null;
		List<DBObject> brandList = null;

		MultipartFile mf = request.getFile("myFile");
		String filename = mf.getOriginalFilename();

		byte[] buf = new byte[0];
		logger.info(filename + "==" + buf.length);
		try {
			buf = mf.getBytes();
			if (buf == null) {
				returnAjax = AjaxObject.newError("文件为空");
			} else {
				List<String> headerList = Arrays.asList(strHeaders);
				List<String> fieldList = Arrays.asList(strField);
				baseCategoryLogService.batchInsert(filename, buf, headerList, fieldList);
				returnAjax = AjaxObject.newOk("导入成功");
			}
		} catch (IOException e) {
			logger.error("读取上传文件出错", e);
			returnAjax = AjaxObject.newError("导入失败：io流错误");
		} catch (Exception e) {
			logger.error("读取上传文件出错", e);
			returnAjax = AjaxObject.newError("导入失败：系统错误");
		}
		String ss = returnAjax.toString();
		logger.info("returnAjax ============" + ss);
		return returnAjax.toString();
	}

	/**
	 * DBObject 转换成ProductBrandModel
	 * */
	public List<BaseCategoryOperateLog> dbObj2BrandModel(List<DBObject> dbObjList) {
		List<BaseCategoryOperateLog> brandList = new ArrayList<BaseCategoryOperateLog>();
		for (DBObject dbObj : dbObjList) {
			Set<String> keySet = dbObj.keySet();
			Iterator<String> it = keySet.iterator();
			BaseCategoryOperateLog brand = new BaseCategoryOperateLog();
			while (it.hasNext()) {
				String key = it.next();
				Object value = dbObj.get(key);
				ReflectUtil.setVal(brand, key, value);
			}
			brandList.add(brand);
		}
		return brandList;
	}
	
	/**
	 * 乱码解决
	 * @throws UnsupportedEncodingException 
	 * */
	private static String toUtf8String(String fileName, HttpServletRequest req) throws UnsupportedEncodingException {
		//return new String(fileName.getBytes("GBK"), "ISO8859-1");
		if(req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			return URLEncoder.encode(fileName, "UTF-8");
		}else{
			return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		}
	}
}
