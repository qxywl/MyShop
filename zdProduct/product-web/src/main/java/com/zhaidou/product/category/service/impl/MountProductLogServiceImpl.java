/*
 * 文 件 名:  SalecategoryProductRelationLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
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

import javax.annotation.Resource;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.MountProductLogDao;
import com.zhaidou.product.category.model.MountProductLog;
import com.zhaidou.product.category.service.MountProductLogService;
import com.zhaidou.product.category.service.MountProductOptType;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("mountProductLogService")
public class MountProductLogServiceImpl implements MountProductLogService {
	private static final Log logger = LogFactory.getLog(MountProductLogServiceImpl.class);
	private static String[] strHeaders = { "挂载ID", "运营分类ID", "运营分类编码", "产品ID", "产品编号", "挂载类型", "排序值", "备注", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
	private static String[] strField = { "relationId", "categoryId", "categoryCode", "productId", "productCode", "mountType", "orderValue", "optType", "description", "operateUser", "operateTime", "logDate" };

	@Resource
	private MountProductLogDao mountProductLogDao;

	public void insert(MountProductLog mountProductLog) throws ZhaidouRuntimeException {
		try {
			mountProductLogDao.insert(mountProductLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("插入产品挂载日志失败", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean batchInsert(List<MountProductLog> logList) throws ZhaidouRuntimeException {
		try {
			mountProductLogDao.batchInsert(logList);
		} catch (Exception e) {
			// throw new TeshehuiRuntimeException("产品挂载日志批量插入失败",e);
			logger.error("产品挂载日志批量插入失败",e);
			return false;
		}
		return true;
	}

	public void update(MountProductLog mountProductLog) throws ZhaidouRuntimeException {
		try {
			mountProductLogDao.update(mountProductLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	public MountProductLog getById(MountProductLog mountProductLog) throws ZhaidouRuntimeException {
		MountProductLog result = null;
		try {
			result = mountProductLogDao.queryOne(mountProductLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public long getCount(MountProductLog mountProductLog) throws ZhaidouRuntimeException {
		long result = 0;
		try {
			result = mountProductLogDao.countListPage(mountProductLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<MountProductLog> getList(MountProductLog mountProductLog, Page page) throws ZhaidouRuntimeException {
		long count;
		List<MountProductLog> result = null;
		try {
			count = mountProductLogDao.countListPage(mountProductLog);
			if (count > 0) {
				page.setTotalCount(count);
				result = mountProductLogDao.queryListPage(mountProductLog, page.getPageNum(), page
						.getNumPerPage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void deleteById(MountProductLog mountProductLog) throws ZhaidouRuntimeException {
		try {
			mountProductLogDao.delete(mountProductLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Workbook exportExcel(MountProductLog mountProductLog, Page page, String tplPath) {

		List<MountProductLog> logList = this.getList(mountProductLog, page);
		// List<String> headerList = Arrays.asList(strHeaders);
		List<String> fieldList = Arrays.asList(strField);
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(tplPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("商品挂载日志模板文件不存在: " + tplPath, e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("商品挂载日志模板文件有问题:" + tplPath, e);
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
			for (MountProductLog log : logList) {

				Row row = sheet.createRow(j); // 创建行
				json = (JSONObject) JSON.toJSON(log);
				for (int i = 0; i < fieldList.size(); i++) {
					String key = fieldList.get(i);
					Object value = json.get(key);
					Cell cell0 = row.createCell(i);
					value = value == null ? "" : value;

					if (key == "optType") {
						if (value == MountProductOptType.UPDATE.getValue()) {
							value = "更新";
						} else if (value == MountProductOptType.DELETE.getValue()) {
							value = "删除";
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
