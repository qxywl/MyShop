package com.zhaidou.product.category.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.MountProductLog;
import com.zhaidou.product.category.service.MountProductLogService;
import com.zhaidou.product.category.service.MountProductOptType;
import com.zhaidou.product.category.service.MountType;

/**
 * @Title: SaleCategoryLogController.java
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
@Controller("mountProductLogController")
@RequestMapping("/mount-product/log")
public class MountProductLogController extends BaseController {

	private static Logger logger = Logger.getLogger(MountProductLogController.class);
	private final static Integer limitRows = 3000; // 限制导出三千条

	private final static String LOG_LIST = "category/mountproductlog/list";
	private final static String LOG_MAIN = "category/mountproductlog/list";
	/** 导出字段和表名 */
//	private static String[] strHeaders = { "挂载ID", "运营分类ID", "运营分类编码", "产品ID", "产品编号", "挂载类型", "排序值", "备注", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
//	private static String[] strField = { "relationId", "categoryId", "categoryCode", "productId", "productCode", "mountType", "orderValue", "optType", "description", "operateUser", "operateTime", "logDate" };

	@Autowired
	private MountProductLogService mountProductLogService;

	@RequestMapping("/main")
	public String toMain(Model model, Page page) {
		/*
		 * 校验用户登录
		 */

		page.setPageNum(1);
		List<MountProductLog> logList = mountProductLogService.getList(null, page);
		Map<Integer, String> optTypeMap = this.getMountProductOptType();
		Map<Integer, String> mountTypeMap = this.getMountType();
		model.addAttribute("optTypeMap", optTypeMap);
		model.addAttribute("mountTypeMap", mountTypeMap);
		
		model.addAttribute("list", logList);
		model.addAttribute("page", page);

		return LOG_MAIN;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String logList(Model model, Page page, MountProductLog mountProductLog) {
		List<MountProductLog> logList = mountProductLogService.getList(mountProductLog, page);
		Map<Integer, String> optTypeMap = this.getMountProductOptType();
		Map<Integer, String> mountTypeMap = this.getMountType();
		model.addAttribute("optTypeMap", optTypeMap);
		model.addAttribute("mountTypeMap", mountTypeMap);

		model.addAttribute("list", logList);
		model.addAttribute("page", page);
		model.addAttribute("mountProductLog", mountProductLog);
		return LOG_LIST;
	}

	/**
	 * 导出
	 * */
	@RequestMapping(value = "/export", method = { RequestMethod.GET })
	@ResponseBody
	public String exportLog(Page page, @ModelAttribute MountProductLog mountProductLog, HttpServletResponse resp, HttpServletRequest req) {

		String strReturn = null;
		AjaxObject returnAjax = null;
		page.setNumPerPage(limitRows);
//		String fileType = ".xlsx";
//		String sheetName = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
		String fileName = "产品挂载日志" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
		String tplPath = req.getSession().getServletContext().getRealPath("/resources/mount_product_log_template.xlsx") ;

//		List<String> headerList = Arrays.asList(strHeaders);
//		List<String> fieldList = Arrays.asList(strField);

		Workbook book = null;
		OutputStream os = null;
		try {

//			List<MountProductLog> logList = mountProductLogService.getList(mountProductLog, page);
			book = mountProductLogService.exportExcel(mountProductLog, page, tplPath);
//			book = new ExcelIO<MountProductLog>().export(fileType, sheetName, headerList, fieldList, logList);
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

	private Map<Integer, String> getMountProductOptType() {
		Map<Integer, String> mountProductOptTypeMap = new HashMap<Integer, String>();
		mountProductOptTypeMap.put(MountProductOptType.UPDATE.getValue(), "更新");
		mountProductOptTypeMap.put(MountProductOptType.DELETE.getValue(), "删除");
		return mountProductOptTypeMap;
	}

	private Map<Integer, String> getMountType() {
		Map<Integer, String> mountTypeMap = new HashMap<Integer, String>();
		mountTypeMap.put(MountType.AUTO.getValue(), "自动");
		mountTypeMap.put(MountType.MANUAL.getValue(), "手动");
		return mountTypeMap;
	}
}
