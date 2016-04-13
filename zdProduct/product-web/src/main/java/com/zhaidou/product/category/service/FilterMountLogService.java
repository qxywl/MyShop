/*
 * 文 件 名:  SalecategoryFilterRelationLogService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-21
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.FilterMountLog;
import com.zhaidou.product.category.model.FilterMountPO;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FilterMountLogService
{
    public void insert(FilterMountLog filterMountLog)throws ZhaidouRuntimeException;

    public void update(FilterMountLog filterMountLog)throws ZhaidouRuntimeException;

    public FilterMountLog getById(FilterMountLog filterMountLog)throws ZhaidouRuntimeException;

    public long getCount(FilterMountLog filterMountLog)throws ZhaidouRuntimeException;

    public List<FilterMountLog> getList(FilterMountLog filterMountLog, Page page)throws ZhaidouRuntimeException;

    public void deleteById(FilterMountLog filterMountLog)throws ZhaidouRuntimeException;

	public void insertBatch(List<FilterMountPO> fmList, Integer optType)throws ZhaidouRuntimeException;

	public Workbook exportExcel(FilterMountLog filterMountLog, Page page, String tplPath);

}
