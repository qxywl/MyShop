/*
 * 文 件 名:  ProductPriceLadderManager.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-27
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductPriceLadderModel;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductPriceLadderManager
{
    public void addProductPriceLadder(ProductPriceLadderModel productPriceLadderModel) throws Exception;

    public void updateProductPriceLadder(ProductPriceLadderModel productPriceLadderModel, Map<String,Object> user);

    public ProductPriceLadderModel getProductPriceLadderById(String id);

    public Map<String,Object> getProductPriceLadder(ProductPriceLadderModel productPriceLadderModel,Page page);

    public void deleteById(String id);
    
    public void deleteByIds(List<Long> ids);

	public void addProductPriceLadder(
			List<ProductPriceLadderModel> productOperateList, Map<String,Object> user) throws Exception;

}
