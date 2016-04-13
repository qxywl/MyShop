/*
 * 文 件 名:  BasecategoryOperateLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-03-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.SaleCategoryOperateLogDao;
import com.zhaidou.product.category.model.SaleCategoryOperateLog;
import com.zhaidou.product.category.service.CategoryOptType;
import com.zhaidou.product.category.service.SaleCategoryLogService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("saleCategoryLogService")
public class SaleCategoryLogServiceImpl implements SaleCategoryLogService {
	private static final Log logger = LogFactory.getLog(SaleCategoryLogServiceImpl.class);
	private static String[] strHeaders = { "ID", "运营分类ID", "运营分类名称", "运营分类编码", "父分类ID", "父分类编码", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
	private static String[] strField = { "id", "categoryId", "categoryName", "categoryCode", "parentId", "parentCode", "operateType", "operateUser", "operateTime", "logDate" };

	// private static final Integer limitRow = 3000;
	@Autowired
	private SaleCategoryOperateLogDao saleCategoryOperateLogDao;

	@Override
	public void insert(SaleCategoryOperateLog saleCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + saleCategoryOperateLog);
		}

		try {
			saleCategoryOperateLogDao.insert(saleCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("运营分类日志写入数据库操作失败：", e);
			// throw new DaoException("日志写入数据库操作失败"); // 非重要操作，即使操作失败也不回滚
		}
	}

	@Override
	public SaleCategoryOperateLog getById(SaleCategoryOperateLog saleCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + saleCategoryOperateLog);
		}
		SaleCategoryOperateLog result;
		try {
			result = saleCategoryOperateLogDao.queryOne(saleCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("运营分类日志查询操作失败：", e);
			throw new DaoException("运营分类日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public long getCount(SaleCategoryOperateLog saleCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + saleCategoryOperateLog);
		}
		long result;
		try {
			result = saleCategoryOperateLogDao.countListPage(saleCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("运营分类日志查询操作失败：", e);
			throw new DaoException("运营分类日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public List<SaleCategoryOperateLog> getPage(SaleCategoryOperateLog saleCategoryOperateLog, Page page) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + saleCategoryOperateLog);
		}
		long count;
		List<SaleCategoryOperateLog> result = null;

		try {
			count = saleCategoryOperateLogDao.countListPage(saleCategoryOperateLog);

			if (count > 0) {
				page.setTotalCount(count);
				result = saleCategoryOperateLogDao.queryListPage(saleCategoryOperateLog, page
						.getPageNum(), page.getNumPerPage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("运营分类日志读取操作失败：", e);
			throw new DaoException("运营分类日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;

	}

	@Override
	public void deleteById(SaleCategoryOperateLog saleCategoryOperateLog) {
		
	}


	@Override
	public Workbook exportExcel(SaleCategoryOperateLog saleCategoryOperateLog, Page page, String tplPath) {
		List<SaleCategoryOperateLog> logList = this.getPage(saleCategoryOperateLog, page);
		// List<String> headerList = Arrays.asList(strHeaders);
		List<String> fieldList = Arrays.asList(strField);
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(tplPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("运营分类操作日志模板文件不存在: " + tplPath, e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("运营分类操作日志模板文件有问题:" + tplPath, e);
			return null;
		}
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		// XSSFSheet sheet = workBook.createSheet("2015");
		XSSFSheet sheet = workBook.getSheetAt(0); // 读取第一个工作簿
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle defaultStyle = exportUtil.getTextStyle();
		XSSFCellStyle dateStyle = exportUtil.getDateStyle();
		XSSFCellStyle numbericStyle = exportUtil.getNumbericStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		for (int i = 0; i < strHeaders.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(strHeaders[i]);
		}
		// 设置列宽
		// sheet.setColumnWidth(7, 200);
		// sheet.setColumnWidth(8, 200);
		// sheet.setColumnWidth(11, 1000);
		// sheet.setColumnWidth(12, 250);

		// 构建表体数据
		if (logList != null && logList.size() > 0) {
			// 2.填写数据
			int j = 1;
			JSONObject json = null;
			for (SaleCategoryOperateLog log : logList) {

				Row row = sheet.createRow(j); // 创建行
				json = (JSONObject) JSON.toJSON(log);
				for (int i = 0; i < fieldList.size(); i++) {
					String key = fieldList.get(i);
					Object value = json.get(key);
					Cell cell0 = row.createCell(i);
					value = value == null ? "" : value;

//					操作类型：1.插入		2:修改	3:删除
					if (key == "operateType") {
						if (value == CategoryOptType.APPEND.getValue()) {
							value = "添加";
						} else if (value == CategoryOptType.UPDATE.getValue()) {
							value = "修改";
						} else if (value == CategoryOptType.DELETE.getValue()) {
							value = "删除";
						}else if (value == CategoryOptType.DEL_CHILD.getValue()){
							value = "删除子类";
						}else{
							value = "未知";
						}
					}

					if (value instanceof Number) {
						cell0.setCellStyle(numbericStyle);
						cell0.setCellValue(Double.parseDouble(value.toString()));
					} else if (value instanceof Date) {
						cell0.setCellStyle(dateStyle);
						cell0.setCellValue((Date) value);
					} else {
						// 默认是文本格式
						cell0.setCellStyle(defaultStyle);
						cell0.setCellValue(value.toString());
					}
				}
				j++; // 控制行数
			}
		}
		return workBook;
	}
}
