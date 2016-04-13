/*
 * 文 件 名:  SalecategoryProductRelationLogService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.MountProductLog;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MountProductLogService {
	public void insert(MountProductLog mountProductLog) throws ZhaidouRuntimeException;

	public void update(MountProductLog mountProductLog) throws ZhaidouRuntimeException;

	public MountProductLog getById(MountProductLog mountProductLog) throws ZhaidouRuntimeException;

	public long getCount(MountProductLog mountProductLog) throws ZhaidouRuntimeException;

	public List<MountProductLog> getList(MountProductLog mountProductLog, Page page) throws ZhaidouRuntimeException;

	public void deleteById(MountProductLog mountProductLog) throws ZhaidouRuntimeException;

	public Workbook exportExcel(MountProductLog mountProductLog, Page page, String tplPath);

	public boolean batchInsert(List<MountProductLog> logList) throws ZhaidouRuntimeException;

}
