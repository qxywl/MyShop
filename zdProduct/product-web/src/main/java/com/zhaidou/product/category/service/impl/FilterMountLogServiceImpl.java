/*
 * 文 件 名:  SalecategoryFilterRelationLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-21
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.FilterMountLogDao;
import com.zhaidou.product.category.model.FilterMountLog;
import com.zhaidou.product.category.model.FilterMountPO;
import com.zhaidou.product.category.service.FilterMountLogService;
import com.zhaidou.product.category.service.FilterMountOptType;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("filterMountLogService")
public class FilterMountLogServiceImpl implements FilterMountLogService {
	private static final Log logger = LogFactory.getLog(FilterMountLogServiceImpl.class);
	/** 导出字段和表名 */
	private static String[] strHeaders = { "筛选项ID","分类号","分类编码","属性项ID","属性项编码","属性值ID","属性值编码","排序值","操作类型","操作人","操作时间","日志记录时间" };
	private static String[] strField = { "relationId", "categoryId", "categoryCode", "attrId", "attrCode", "attrvalueId", "attrvalueCode", "orderNumber", "optType", "operateUser", "operateTime", "logDate" };

	@Resource
	private FilterMountLogDao filterMountLogDao;

	public void insert(FilterMountLog filterMountLog) throws ZhaidouRuntimeException {
		try {
			filterMountLogDao.insert(filterMountLog);
		} catch (Exception e) {
			logger.error("{筛选项挂载日志}->[插入]失败", e);
			throw new ZhaidouRuntimeException("{筛选项挂载日志}->[插入]失败", e);
		}
	}

	public void update(FilterMountLog filterMountLog) throws DaoException {
		try {
			filterMountLogDao.update(filterMountLog);
		} catch (Exception e) {
			logger.error("【筛选项挂载日志】-> [修改]失败", e);
			throw new DaoException("【筛选项挂载日志】-> [修改]失败", e);
		}
	}

	public FilterMountLog getById(FilterMountLog filterMountLog) throws DaoException {
		FilterMountLog result;
		try {
			result = filterMountLogDao.queryOne(filterMountLog);
		} catch (Exception e) {
			logger.error("根据ID获取【筛选项挂载日志】失败", e);
			throw new DaoException("根据ID获取【筛选项挂载日志】失败", e);
		}
		return result;
	}

	public long getCount(FilterMountLog filterMountLog) throws DaoException {
		long result;
		try {
			result = filterMountLogDao.countListPage(filterMountLog);
		} catch (Exception e) {
			logger.error("{筛选项挂载日志}->[条数获取]失败", e);
			throw new DaoException("{筛选项挂载日志}->[条数获取]失败", e);
		}
		return result;
	}

	public List<FilterMountLog> getList(FilterMountLog filterMountLog, Page page) throws DaoException {
		List<FilterMountLog> result;
		try {
			long count = filterMountLogDao.countListPage(filterMountLog);
			result = null;
			if (count > 0) {
				page.setTotalCount(count);
				result = filterMountLogDao.queryListPage(filterMountLog, page.getPageNum(), page
						.getNumPerPage());
			}
		} catch (Exception e) {
			logger.error("【筛选项挂载日志】->[列表获取]失败", e);
			throw new DaoException("【筛选项挂载日志】->[列表获取]失败", e);
		}
		return result;
	}

	public void deleteById(FilterMountLog filterMountLog) throws DaoException {
		try {
			filterMountLogDao.delete(filterMountLog);
		} catch (Exception e) {
			logger.error("【筛选项挂载日志】->[删除]失败", e);
			throw new DaoException("【筛选项挂载日志】->[列表]失败", e);
		}
	}

	@Override
	public void insertBatch(List<FilterMountPO> fmList, Integer optType) {
		for (FilterMountPO po : fmList) {
			FilterMountLog fmLog = new FilterMountLog();
			fmLog.setRelationId(po.getId());
			fmLog.setCategoryId(po.getCategoryId());
			fmLog.setCategoryCode(po.getCategoryCode());
			fmLog.setAttrId(po.getAttrId());
			fmLog.setAttrCode(po.getAttrCode());
			fmLog.setAttrvalueId(po.getAttrValId());
			fmLog.setAttrvalueCode(po.getAttrValCode());
			fmLog.setOptType(optType);
			fmLog.setOrderNumber(po.getOrderNumber());
			fmLog.setOperateTime(po.getUpdateDate());
			fmLog.setOperateUser(po.getUpdateUser());
			fmLog.setLogDate(new Date());
			this.insert(fmLog);
		}
	}

	@Override
	public Workbook exportExcel(FilterMountLog filterMountLog, Page page, String tplPath) {
		List<FilterMountLog> logList = this.getList(filterMountLog, page);
		// List<String> headerList = Arrays.asList(strHeaders);
		List<String> fieldList = Arrays.asList(strField);
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(tplPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("挂载规则日志模板文件不存在: " + tplPath, e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("挂载规则日志模板文件有问题:" + tplPath, e);
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
			for (FilterMountLog log : logList) {

				Row row = sheet.createRow(j); // 创建行
				json = (JSONObject) JSON.toJSON(log);
				for (int i = 0; i < fieldList.size(); i++) {
					String key = fieldList.get(i);
					Object value = json.get(key);
					Cell cell0 = row.createCell(i);
					value = value == null ? "" : value;

					if (key == "optType") {
						if (value == FilterMountOptType.APPEND.getValue()) {
							value = "添加";
						} else if (value == FilterMountOptType.UPDATE.getValue()) {
							value = "修改";
						} else if (value == FilterMountOptType.DELETE.getValue()) {
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
