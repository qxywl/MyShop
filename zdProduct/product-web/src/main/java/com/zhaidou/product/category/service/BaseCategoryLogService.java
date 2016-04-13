/*
 * 文 件 名:  BasecategoryOperateLogService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-03-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.BaseCategoryOperateLog;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface BaseCategoryLogService
{
    public void insertCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog);

    public void updateCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog);

    public BaseCategoryOperateLog getCategoryOperateLogById(BaseCategoryOperateLog baseCategoryOperateLog);

    public long getCategoryOperateLogCount(BaseCategoryOperateLog baseCategoryOperateLog);

    public List<BaseCategoryOperateLog> getCategoryOperateLog(BaseCategoryOperateLog baseCategoryOperateLog, Page page);

    public void deleteById(BaseCategoryOperateLog baseCategoryOperateLog);

	public void batchInsert(String filename, byte[] buf, List<String> headerList, List<String> fieldList) throws Exception;

	public void inserts(List<BaseCategoryOperateLog> baseCategoryOperateLogs);

	public void deleteByIds(List<Long> ids);

	public Workbook exportExcel(BaseCategoryOperateLog baseCategoryOperateLog, Page page, String tplPath);

}
