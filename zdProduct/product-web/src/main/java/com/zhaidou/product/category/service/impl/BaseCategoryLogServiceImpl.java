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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExcelIO;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.BaseCategoryOperateLogDao;
import com.zhaidou.product.category.model.BaseCategoryOperateLog;
import com.zhaidou.product.category.service.BaseCategoryLogService;
import com.zhaidou.product.category.service.CategoryOptType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("baseCategoryLogService")
public class BaseCategoryLogServiceImpl implements BaseCategoryLogService {
	private static final Log logger = LogFactory.getLog(BaseCategoryLogServiceImpl.class);
	// private static final Integer limitRow = 3000;
	/** 导出字段和表名 */
	private static String[] strHeaders = { "ID", "基础分类ID", "基础分类名称", "基础分类编码", "父分类ID", "父分类编码", "操作类型", "最后操作人", "最后操作时间", "日志记录时间" };
	private static String[] strField = { "id", "categoryId", "categoryName", "categoryCode", "parentId", "parentCode", "operateType", "operateUser", "operateTime", "logDate" };

	
	@Autowired
	private BaseCategoryOperateLogDao baseCategoryOperateLogDao;

	@Override
	public void insertCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}

		try {
			baseCategoryOperateLogDao.insert(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志写入数据库操作失败：", e);
			// throw new DaoException("日志写入数据库操作失败"); // 非重要操作，即使操作失败也不回滚
		}
		// throw new DaoException("日志写入数据库操作失败"); // 非重要操作，即使操作失败也不回滚
	}

	/**
	 * List<DBObject> 转换成List<BaseCategoryOperateLog>
	 * */
	private List<BaseCategoryOperateLog> DBList2ModelList(List<DBObject> dbObjList) {
		List<BaseCategoryOperateLog> list = new ArrayList<BaseCategoryOperateLog>();
		BaseCategoryOperateLog po = null;
		for (DBObject dbObj : dbObjList) {
			po = DB2Model(dbObj);
			if (po != null) {
				list.add(po);
			}
		}
		return list;
	}

	/**
	 * DBObject 转换 BaseCategoryOperateLog
	 * */
	private BaseCategoryOperateLog DB2Model(DBObject dbObj) {
		BaseCategoryOperateLog po = null;
		if (dbObj != null) {
			po = new BaseCategoryOperateLog();
			Set<String> keySet = dbObj.keySet();
			Iterator<String> it = keySet.iterator();

			while (it.hasNext()) {
				String key = it.next();
				// 对brandId ,brandStatus ,storeCertificationType 做处理，从String 转成
				// Integer 类型
				Object value = null;
				if ("brandId".equals(key) || "brandStatus".equals(key) || "storeCertificationType"
						.equals(key)) {
					Object tmpValue = dbObj.get(key);
					if (tmpValue != null) {
						Integer intVal = Integer.valueOf(tmpValue.toString());
						ReflectUtil.setVal(po, key, intVal);
					}
				} else {
					value = dbObj.get(key);
					ReflectUtil.setVal(po, key, value);
				}
			}
		}
		return po;
	}

	@Override
	@Transactional
	public void batchInsert(String filename, byte[] buf, List<String> headerList, List<String> fieldList) throws Exception {
		List<DBObject> dbList = null;
		List<BaseCategoryOperateLog> poList = null;

		dbList = ExcelIO.read(filename, new ByteArrayInputStream(buf), headerList, fieldList);

		poList = DBList2ModelList(dbList);

		if (poList != null) {
			for (BaseCategoryOperateLog baseCategoryOperateLog : poList) {// 批量插入数据
				this.insertCategoryOperateLog(baseCategoryOperateLog);
			}
		}

	}

	@Override
	public void updateCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}
		try {
			baseCategoryOperateLogDao.update(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志修改操作失败：", e);
			throw new DaoException("日志修改操作失败");
		}
	}

	@Override
	public BaseCategoryOperateLog getCategoryOperateLogById(BaseCategoryOperateLog baseCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}
		BaseCategoryOperateLog result;
		try {
			result = baseCategoryOperateLogDao.queryOne(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志查询操作失败：", e);
			throw new DaoException("日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public long getCategoryOperateLogCount(BaseCategoryOperateLog baseCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}
		long result;
		try {
			result = baseCategoryOperateLogDao.countListPage(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志查询操作失败：", e);
			throw new DaoException("日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;
	}

	@Override
	public List<BaseCategoryOperateLog> getCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog, Page page) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}
		long count;
		List<BaseCategoryOperateLog> result = null;

		try {
			count = baseCategoryOperateLogDao.countListPage(baseCategoryOperateLog);

			if (count > 0) {
				page.setTotalCount(count);
				result = baseCategoryOperateLogDao.queryListPage(baseCategoryOperateLog, page
						.getPageNum(), page.getNumPerPage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志读取操作失败：", e);
			throw new DaoException("日志查询操作失败");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("--result-->" + result);
		}
		return result;

	}

	@Override
	public void deleteById(BaseCategoryOperateLog baseCategoryOperateLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLog);
		}
		try {
			baseCategoryOperateLogDao.delete(baseCategoryOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志删除操作失败：", e);
			throw new DaoException("日志删除操作失败");
		}
	}
	
	@Override
	public void inserts(List<BaseCategoryOperateLog> baseCategoryOperateLogs) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + baseCategoryOperateLogs);
		}
		try {
			baseCategoryOperateLogDao.inserts(baseCategoryOperateLogs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("批量插入日志操作失败：", e);
			throw new DaoException("批量插入日志操作失败");
		}
	}
	
	@Override
	public void deleteByIds(List<Long> ids) {
		if (logger.isDebugEnabled()) {
			logger.debug("--Params-->" + ids);
		}
		try {
			baseCategoryOperateLogDao.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("批量删除日志操作失败：", e);
			throw new DaoException("批量日志删除操作失败");
		}
	}


	@Override
	public Workbook exportExcel(BaseCategoryOperateLog baseCategoryOperateLog, Page page, String tplPath) {
		// TODO Auto-generated method stub
		
		List<BaseCategoryOperateLog> logList = this.getCategoryOperateLog(baseCategoryOperateLog, page);
//		List<FilterMountLog> logList = this.getList(filterMountLog, page);
		// List<String> headerList = Arrays.asList(strHeaders);
		List<String> fieldList = Arrays.asList(strField);
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(tplPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("基础分类操作日志模板文件不存在: " + tplPath, e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("基础分类操作日志模板文件有问题:" + tplPath, e);
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
			for (BaseCategoryOperateLog log : logList) {

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
