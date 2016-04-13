/*
 * 文 件 名:  VirtualProductService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.VirtualProductModel;
import com.zhaidou.product.po.VirtualProductPO;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-10-01]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface VirtualProductService
{
    public void addVirtualProduct(VirtualProductModel virtualProductModel)throws Exception;

    public void updateVirtualProductById(VirtualProductModel virtualProductModel)throws Exception;

    public VirtualProductModel getVirtualProductById(VirtualProductModel virtualProductModel)throws Exception;

    public long getVirtualProductCount(VirtualProductModel virtualProductModel)throws Exception;

    public List<VirtualProductModel> getVirtualProduct(VirtualProductModel virtualProductModel, Page page)throws Exception;

    public void deleteById(VirtualProductModel virtualProductModel)throws Exception;
    
    public void deleteByIds(List<Long> ids)throws Exception;

	public List<VirtualProductModel> getVirtualProductBySkuCodes(
			List<String> virtualProductCodeList)throws Exception;

	public <T> T copyModeltoPo(T vpModel,
			T virtualProductPO) throws Exception ;

}
