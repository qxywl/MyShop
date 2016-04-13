package com.zhaidou.product.category.service;

import java.util.List;

import com.zhaidou.common.util.Response;
import com.zhaidou.product.category.model.FilterMountPO;

/**
 * 
 * @Title: FilterMountService.java 
 *
 * @Package com.teshehui.product.category.service 
 *
 * @Description:   运营分类属性项挂载Service
 *
 * @author lvshuding 
 *
 * @date 2015年4月8日 下午2:38:29 
 *
 * @version V1.0
 */
public interface FilterMountService {

	/**
	 * @Description: 批量插入
	 * @param fmList
	 * return Response
	 */
	public Response insertBatch(List<FilterMountPO> fmList);
	
	/**
	 * @Description: 批量删除[软删除]
	 * @param fmList 
	 * return Response
	 */
	public Response deleteBatch(List<FilterMountPO> fmList);
	
	/**
	 * @Description:  根据运营分类ID删除筛选项列表
	 * @param po
	 * return Response
	 */
	public Response deleteByCategoryId(FilterMountPO po);
	
	/**
	 * @Description:  修改
	 * @param fmList  新增的对象
	 * @param delIds  需要删除的ID列表
	 * @param updateOrders  需要修改的排序值
	 * @param updateUser   修改人
	 * return Response
	 */
	public Response update(List<FilterMountPO> fmList,List<FilterMountPO> dltList,String delIds,String updateOrders,String updateUser);
	 
	/**
	 * @Description: 根据运营分类加载
	 * @param po
	 * return List<FilterMountPO>
	 */
	public List<FilterMountPO> queryListByCategoryId(FilterMountPO po);
}
