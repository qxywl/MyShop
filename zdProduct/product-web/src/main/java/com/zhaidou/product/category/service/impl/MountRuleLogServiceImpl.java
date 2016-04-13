/*
 * 文 件 名:  SalecategoryProductMountRuleLogServiceImpl.java
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.MountRuleLogDao;
import com.zhaidou.product.category.model.MountRuleLog;
import com.zhaidou.product.category.service.MountRuleLogService;
import com.zhaidou.product.category.service.MountRuleOptType;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("mountRuleLogService")
public class MountRuleLogServiceImpl implements MountRuleLogService {
	private static final Log logger = LogFactory.getLog(MountRuleLogServiceImpl.class);
	/** 导出字段和表名 */
	private static String[] strHeaders = { "挂载规则ID", "分类ID", "分类编码", "产品名", "产品前缀", "产品后缀", "查询关键字", "品牌编码组", "分类编码组", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
	private static String[] strField = { "mountId", "categoryId", "categoryCode", "productName", "productPrefix", "productSuffix", "searchTag", "brandCodes", "categoryCodes", "optType", "operateUser", "operateTime", "logDate" };

	@Resource
	private MountRuleLogDao mountRuleLogDao;

	@Override
	public void insert(MountRuleLog mountRuleLog) throws DaoException {
		try {
			mountRuleLog.setLogDate(new Date());
			mountRuleLogDao.insert(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("插入挂载规则日志失败");
		}
	}

	@Override
	public void update(MountRuleLog mountRuleLog) {
		try {
			mountRuleLogDao.update(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("修改挂载规则日志失败");
		}
	}

	@Override
	public MountRuleLog getById(MountRuleLog mountRuleLog) {
		MountRuleLog result;
		try {
			result = mountRuleLogDao.queryOne(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询挂载规则日志失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public long getCount(MountRuleLog mountRuleLog) {
		long result = 0L;
		try {
			result = mountRuleLogDao.countListPage(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询挂载规则日志条数失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public List<MountRuleLog> getList(MountRuleLog mountRuleLog, Page page) {
		List<MountRuleLog> result = null;
		try {
			long count = mountRuleLogDao.countListPage(mountRuleLog);
			if (count > 0) {
				page.setTotalCount(count);
				result = mountRuleLogDao.queryListPage(mountRuleLog, page.getPageNum(), page
						.getNumPerPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("获取挂载规则日志列表失败");
		}
		return result;
	}

	@Override
	public void deleteById(MountRuleLog mountRuleLog) {
		try {
			mountRuleLogDao.delete(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("删除挂载规则日志失败");
		}
	}

	@Override
	public Workbook exportExcel(MountRuleLog mountRuleLog, Page page, String tplPath) {

		List<MountRuleLog> logList = this.getList(mountRuleLog, page);
//		List<String> headerList = Arrays.asList(strHeaders);
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
		//设置列宽
//		sheet.setColumnWidth(7, 200);
//		sheet.setColumnWidth(8, 200);
//		sheet.setColumnWidth(11, 1000);
//		sheet.setColumnWidth(12, 250);
		
		// 构建表体数据
		if (logList != null && logList.size() > 0) {
			// 2.填写数据
			int j = 1;
			JSONObject json = null;
			for (MountRuleLog log : logList) {

				Row row = sheet.createRow(j); // 创建行
				json = (JSONObject) JSON.toJSON(log);
				for (int i = 0; i < fieldList.size(); i++) {
					String key = fieldList.get(i);
					Object value = json.get(key);
					Cell cell0 = row.createCell(i);
					value = value == null ? "" : value;
					
					if(key == "optType"){
						if(value == MountRuleOptType.APPEND.getValue()){
							value = "添加";
						}else if(value == MountRuleOptType.UPDATE.getValue()){
							value = "修改";
						}else if(value == MountRuleOptType.DELETE.getValue()){
							value = "删除";
						}else{
							value= "未知";
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
