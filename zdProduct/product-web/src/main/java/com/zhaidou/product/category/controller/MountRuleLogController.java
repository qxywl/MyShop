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
import com.zhaidou.product.category.model.MountRuleLog;
import com.zhaidou.product.category.service.MountRuleLogService;
import com.zhaidou.product.category.service.MountRuleOptType;

/**
 * @Title: MountRuleLogController.java
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
@Controller("mountRuleLogController")
@RequestMapping("/mount-rule/log")
public class MountRuleLogController extends BaseController {

	private static Logger logger = Logger.getLogger(MountRuleLogController.class);
	private final static Integer limitRows = 3000; // 限制导出三千条

	private final static String LOG_LIST = "category/mountrulelog/list";
	private final static String LOG_MAIN = "category/mountrulelog/list";
	/** 导出字段和表名 */
//	private static String[] strHeaders = { "挂载规则ID", "分类ID", "分类编码", "产品名", "产品前缀", "产品后缀", "查询关键字", "品牌编码组", "分类编码组", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
//	private static String[] strField = { "mountId", "categoryId", "categoryCode", "productName", "productPrefix", "productSuffix", "searchTag", "brandCodes", "categoryCodes", "optType", "operateUser", "operateTime", "logDate" };

	@Autowired
	private MountRuleLogService mountRuleLogService;

	@RequestMapping("/main")
	public String toMain(Model model, Page page) {
		/*
		 * 校验用户登录
		 */

		page.setPageNum(1);
		List<MountRuleLog> logList = mountRuleLogService.getList(null, page);
		Map<Integer, String> optTypeMap = this.getOptType();

		model.addAttribute("list", logList);
		model.addAttribute("page", page);
		model.addAttribute("optTypeMap", optTypeMap);

		return LOG_MAIN;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String logList(Model model, Page page, MountRuleLog mountRuleLog) {
		List<MountRuleLog> logList = mountRuleLogService.getList(mountRuleLog, page);
		Map<Integer, String> optTypeMap = this.getOptType();

		model.addAttribute("list", logList);
		model.addAttribute("page", page);
		model.addAttribute("mountRuleLog", mountRuleLog);
		model.addAttribute("optTypeMap", optTypeMap);

		return LOG_LIST;
	}

	/**
	 * 导出
	 * */
	@RequestMapping(value = "/export", method = { RequestMethod.GET })
	@ResponseBody
	public String exportLog(Page page, @ModelAttribute MountRuleLog mountRuleLog, HttpServletResponse resp, HttpServletRequest req) {

		String strReturn = null;
		AjaxObject returnAjax = null;
		page.setNumPerPage(limitRows);
//		String fileType = ".xlsx";
//		String sheetName = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
		String fileName = "产品挂载规则日志" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
		String tplPath = req.getSession().getServletContext().getRealPath("/resources/mount_rule_log_template.xlsx") ;
		
//		List<String> headerList = Arrays.asList(strHeaders);
//		List<String> fieldList = Arrays.asList(strField);

		Workbook book = null;
		OutputStream os = null;
		try {

//			List<MountRuleLog> logList = mountRuleLogService.getList(mountRuleLog, page);
			book = mountRuleLogService.exportExcel(mountRuleLog, page, tplPath);
//			book = new ExcelIO<MountRuleLog>().export(fileType, sheetName, headerList, fieldList, logList);
			resp.reset();
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + toUtf8String(fileName, req) + "\"");
			resp.setContentType("application/vnd.ms-excel; charset=utf-8");
			os = resp.getOutputStream();
			book.write(os);
			os.flush();
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
	 * */
	private static String toUtf8String(String fileName, HttpServletRequest req) {
		// return new String(fileName.getBytes("GBK"), "ISO8859-1");
		try {
			if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				return URLEncoder.encode(fileName, "UTF-8");
			} else {
				return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	private Map<Integer, String> getOptType() {
		Map<Integer, String> mountRuleOptTypeMap = new HashMap<Integer, String>();
		mountRuleOptTypeMap.put(MountRuleOptType.APPEND.getValue(), "添加");
		mountRuleOptTypeMap.put(MountRuleOptType.UPDATE.getValue(), "修改");
		mountRuleOptTypeMap.put(MountRuleOptType.DELETE.getValue(), "删除");
		return mountRuleOptTypeMap;
	}
}
