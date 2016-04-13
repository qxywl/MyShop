package com.zhaidou.product.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.Response;
import com.zhaidou.product.category.dao.FilterMountDao;
import com.zhaidou.product.category.model.FilterMountPO;
import com.zhaidou.product.category.service.FilterMountLogService;
import com.zhaidou.product.category.service.FilterMountOptType;
import com.zhaidou.product.category.service.FilterMountService;
import com.zhaidou.product.category.util.Constants;

@Service("filterMountService")
public class FilterMountServiceImpl implements FilterMountService {
	
	private static Logger LOG = Logger.getLogger(FilterMountServiceImpl.class);
	
	@Resource
	private FilterMountLogService filterMountLogService; 

	@Autowired
	private FilterMountDao filterMountDao;
	
	@Override
	public Response insertBatch(List<FilterMountPO> fmList) {
		Response resp = new Response();
		for(FilterMountPO po:fmList){
			if(!this.insert(po)){
				throw new RuntimeException("insert筛选项数据失败");// 这里是为了事务回滚
			}
		}
		resp.setCode(Constants.SUCCESS_CODE);
		resp.setMsg("数据保存成功");
		
		/*
		 * 
		 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
		 * 
		 */
		filterMountLogService.insertBatch(fmList, FilterMountOptType.APPEND.getValue());
		
		for(FilterMountPO po:fmList){

			StringBuilder sb = new StringBuilder();
			sb.append("【挂载筛选项】#")
				.append("Id[").append(po.getId()).append("]@")
				.append("分类Id[").append(po.getCategoryId()).append("]@")
				.append("分类编码[").append(po.getCategoryCode()).append("]@")
				.append("属性项ID[").append(po.getAttrId() == null ? "" : po.getAttrId()).append("]@")
				.append("属性项编号[").append(po.getAttrCode() == null ? "" : po.getAttrCode()).append("]@")
				.append("属性值ID[").append(po.getAttrValId() == null ? "" : po.getAttrValId()).append("]@")
				.append("属性值编号[").append(po.getAttrValCode() == null ? "" : po.getAttrValCode()).append("]@")
				.append("属性项排序值[").append(po.getOrderNumber() == null ? "" : po.getOrderNumber()).append("]@")
				.append("添加人[").append(po.getCreateUser()).append("]@")
				.append("添加时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getCreateDate())).append("]");
			LOG.info(sb.toString());
		}
		
		return resp;
	}
	
	@Override
	public Response deleteBatch(List<FilterMountPO> fmList) {
		Response resp = new Response();
		for(FilterMountPO po:fmList){
			if(!this.delete(po)){
				throw new RuntimeException("删除筛选项数据失败");// 这里是为了事务回滚
			}
		}
		resp.setCode(Constants.SUCCESS_CODE);
		resp.setMsg("数据删除成功");
		
		/*
		 * 
		 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
		 * 
		 */
		filterMountLogService.insertBatch(fmList, FilterMountOptType.DELETE.getValue());
		for(FilterMountPO po:fmList){

			StringBuilder sb = new StringBuilder();
			sb.append("【删除筛选项】#").append("Id[").append(po.getId()).append("]@")
				.append("categoryId[").append(po.getCategoryId()).append("]@")
				.append("删除人[").append(po.getUpdateUser()).append("]@")
				.append("删除时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getUpdateDate())).append("]");
			LOG.info(sb.toString());
		}
		
		return resp;
	}

	@Override
	public Response deleteByCategoryId(FilterMountPO po) {
		int count = 0;
		Response resp = new Response();
		resp.setCode(Constants.SUCCESS_CODE);
		resp.setMsg("删除数据成功");
		try{
			count = filterMountDao.deleteByCategoryId(po);
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("删除筛选项操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("删除数据出错");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("删除筛选项操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("删除数据出错");
		}
		LOG.info("根据运营分类ID删除筛选项数据结果条数："+count);
		
		/*
		 * 
		 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
		 * 
		 */
		List<FilterMountPO> fmList = new ArrayList<FilterMountPO>();
		fmList.add(po);
		filterMountLogService.insertBatch(fmList, FilterMountOptType.DELETE.getValue());
		if(count>0){
			StringBuilder sb = new StringBuilder();
			sb.append("【删除筛选项】#")
				.append("categoryId[").append(po.getCategoryId()).append("]@")
				.append("删除人[").append(po.getUpdateUser()).append("]@")
				.append("删除时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getUpdateDate())).append("]");
			LOG.info(sb.toString());
		}
		
		return resp;
	}
	
	@Override
	public Response update(List<FilterMountPO> fmList,List<FilterMountPO> dltList,String delIds,String updOrders,String updateUser) {
		Response resp = new Response();
		
		// 1.插入新数据
		if(fmList.size()>0){
			resp = this.insertBatch(fmList);
		}
		if(Constants.ERROR_CODE.equals(resp.getClass())){
			throw new RuntimeException("插入筛选项数据失败");// 这里是为了事务回滚
		}
		
		// 2.需要删除的
		if(StringUtils.isNotEmpty(delIds)){
			delIds = delIds.substring(1,delIds.length());
			String[] ids = delIds.split(";");
			FilterMountPO dPo;
			String[] tmpId;
			List<FilterMountPO> delList = new ArrayList<FilterMountPO>();
			try{
				for(String id:ids){
					tmpId = id.split("_");
					dPo = new FilterMountPO();
					dPo.setCategoryCode(tmpId[0]);
					dPo.setAttrCode(tmpId[1]);
					dPo.setUpdateUser(updateUser);
					dPo.setUpdateDate(new Date(System.currentTimeMillis()));
					
					filterMountDao.deleteByCateCodeAndAttrCode(dPo);
					delList.add(dPo);
				}
			} catch (DaoException e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			}
			
			/*
			 * 
			 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			filterMountLogService.insertBatch(delList, FilterMountOptType.DELETE.getValue());
			for(FilterMountPO p:delList){
				StringBuilder sb = new StringBuilder();
				sb.append("【删除筛选项】#")
					.append("categoryCode[").append(p.getCategoryCode()).append("]@")
					.append("attrCode[").append(p.getAttrCode()).append("]@")
					.append("删除人[").append(p.getUpdateUser()).append("]@")
					.append("删除时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getUpdateDate())).append("]");
				LOG.info(sb.toString());
			}
		}
				
		// 3.需要修改的
		if(StringUtils.isNotEmpty(updOrders)){
			String[] ords = updOrders.split(";");
			FilterMountPO dPo;
			String[] tmpId,tmp;
			List<FilterMountPO> updList = new ArrayList<FilterMountPO>();
			try{
				for(String o:ords){
					tmp= o.split(":");
					tmpId = tmp[0].split("_");
					dPo = new FilterMountPO();
					dPo.setCategoryCode(tmpId[0]);
					dPo.setAttrCode(tmpId[1]);
					if(StringUtils.isNotEmpty(tmp[1])){
						dPo.setOrderNumber(Integer.parseInt(tmp[1]));
					}
					dPo.setUpdateUser(updateUser);
					dPo.setUpdateDate(new Date(System.currentTimeMillis()));
					
					filterMountDao.updateOrderByCateCodeAndAttrCode(dPo);
					
					updList.add(dPo);
				}
			} catch (DaoException e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			}
			
			/*
			 * 
			 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			filterMountLogService.insertBatch(updList, FilterMountOptType.UPDATE.getValue());
			for(FilterMountPO p:updList){
				StringBuilder sb = new StringBuilder();
				sb.append("【修改筛选项】#")
					.append("categoryCode[").append(p.getCategoryCode()).append("]@")
					.append("attrCode[").append(p.getAttrCode()).append("]@")
					.append("orderVal[").append(p.getOrderNumber()).append("]@")
					.append("修改人[").append(p.getUpdateUser()).append("]@")
					.append("修改时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getUpdateDate())).append("]");
				LOG.info(sb.toString());
			}
		}
		
		// 4. 需要删除的颜色、尺码筛选项
		if(dltList.size()>0){
			try{
				for(FilterMountPO po:dltList){
					filterMountDao.delete(po);
				}
			} catch (DaoException e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("删除筛选项操作失败：", e);
				throw new RuntimeException("删除筛选项数据失败",e);// 这里是为了事务回滚
			}
			
			/*
			 * 
			 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			filterMountLogService.insertBatch(dltList, FilterMountOptType.UPDATE.getValue());
			for(FilterMountPO p:dltList){
				StringBuilder sb = new StringBuilder();
				sb.append("【删除筛选项】#")
				.append("id[").append(p.getId()).append("]@")
				.append("categoryCode[").append(p.getCategoryCode()).append("]@")
				.append("attrCode[").append(p.getAttrCode()).append("]@")
				.append("删除人[").append(p.getUpdateUser()).append("]@")
				.append("删除时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getUpdateDate())).append("]");
			LOG.info(sb.toString());
			}
		}
		
		resp.setCode(Constants.SUCCESS_CODE);
		resp.setStatus(true);
		resp.setMsg("数据修改成功");
		return resp;
	}

	@Override
	public List<FilterMountPO> queryListByCategoryId(FilterMountPO po) {
		try{
			return filterMountDao.queryListByCategoryId(po);
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据运营分类ID查询筛选项操作失败：", e);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据运营分类ID查询筛选项操作失败：", e);
			return null;
		}
	}

	private boolean insert(FilterMountPO po){
		int count = 0;
		try{
			count = filterMountDao.insert(po);
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("插入筛选项操作失败：", e);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("插入筛选项操作失败：", e);
			return false;
		}
		return count<1?false:true;
	}
	
	private boolean delete(FilterMountPO po){
		int count = 0;
		try{
			count = filterMountDao.delete(po);
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("删除筛选项操作失败：", e);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("删除筛选项操作失败：", e);
			return false;
		}
		LOG.info("删除筛选项操作结果："+count);
		return true;
	}
	
	public void setFilterMountDao(FilterMountDao filterMountDao) {
		this.filterMountDao = filterMountDao;
	}

}
