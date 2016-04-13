/*
 * 文 件 名:  SalecategoryProductRelationService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.model.MountProductModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MountProductService {

	public void update(MountProductModel salecategoryProductRelationModel);

	public void updateOrBatchInsert(List<MountProductModel> relationList, String categoryCode) throws ZhaidouRuntimeException;
	
	/**
	 * @Description:  根据运营分类ID加载其下所有opt_type=1的商品
	 * @param model
	 * @throws Exception
	 * @return List<MountProductModel>
	 */
	public List<MountProductModel> selectOpt1ByCategoryId(MountProductModel model) throws Exception;

}
