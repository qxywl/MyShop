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
import com.zhaidou.product.category.model.SaleCategoryOperateLog;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SaleCategoryLogService
{
    public void insert(SaleCategoryOperateLog saleCategoryOperateLog);

    public SaleCategoryOperateLog getById(SaleCategoryOperateLog saleCategoryOperateLog);

    public long getCount(SaleCategoryOperateLog saleCategoryOperateLog);

    public List<SaleCategoryOperateLog> getPage(SaleCategoryOperateLog saleCategoryOperateLog, Page page);

    public void deleteById(SaleCategoryOperateLog saleCategoryOperateLog);

	public Workbook exportExcel(SaleCategoryOperateLog saleCategoryOperateLog, Page page, String tplPath);
}
