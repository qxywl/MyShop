/*
 * 文 件 名:  SalecategoryProductMountRuleLogService.java
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

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.MountRuleLog;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MountRuleLogService
{
    public void insert(MountRuleLog mountRuleLog);

    public void update(MountRuleLog mountRuleLog);

    public MountRuleLog getById(MountRuleLog mountRuleLog);

    public long getCount(MountRuleLog mountRuleLog);

    public List<MountRuleLog> getList(MountRuleLog mountRuleLog, Page page);

    public void deleteById(MountRuleLog mountRuleLog);

	public Workbook exportExcel(MountRuleLog mountRuleLog, Page page, String tplPath);

}
