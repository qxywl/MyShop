package com.zhaidou.product.category.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.common.util.Response;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.category.dao.BaseCategoryDao;
import com.zhaidou.product.category.dao.SaleCategoryDao;
import com.zhaidou.product.category.model.BaseCategoryOperateLog;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.SaleCategoryOperateLog;
import com.zhaidou.product.category.service.BaseCategoryLogService;
import com.zhaidou.product.category.service.CategoryOptType;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.SaleCategoryLogService;
import com.zhaidou.product.category.util.Constants;
import com.zhaidou.product.info.model.ProductOtherJobModel;
import com.zhaidou.product.info.model.ProductSalecategoryRelationModel;
import com.zhaidou.product.info.service.ProductOtherJobService;
import com.zhaidou.product.info.service.ProductSalecategoryRelationService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	private static Logger LOG = Logger.getLogger(CategoryServiceImpl.class);

	@Autowired
	private BaseCategoryDao baseCategoryDao;// 基础分类

	@Autowired
	private SaleCategoryDao saleCategoryDao;// 运营分类

	@Autowired
	private BaseCategoryLogService baseCategoryLogService; // 基础日志操作

	@Autowired
	private SaleCategoryLogService saleCategoryLogService; // 运营日志操作
	
	@Autowired
	private ProductOtherJobService productOtherJobService; //缓存调度Service

	@Resource(name="productSalecategoryRelationService")
	private ProductSalecategoryRelationService psRelService;
	
	@Override
	public Response insert(CategoryPO po, Category_Type type) {
		int count = 0;
		boolean result = true;
		Response resp = new Response();
		CategoryPO tmpPo = new CategoryPO();
		try {
			tmpPo.setId(po.getParentId());
//			po.setCategoryCode(this.buildCurrentCode(tmpPo, type));
			this.buildOtherInfo(tmpPo, type);
			po.setParentCode(tmpPo.getCategoryCode());
			switch (type) {
			case BASE_CATEGORY:
				if (StringUtils.isEmpty(po.getColorName())) {
					po.setColorName(tmpPo.getColorName());
				}
				if (StringUtils.isEmpty(po.getSizeName())) {
					po.setSizeName(tmpPo.getSizeName());
				}
				count = baseCategoryDao.insert(po);
				break;
			case SALE_CATEGORY://只在一级分类上设置颜色、尺码
				if (StringUtils.isEmpty(po.getColorName())) {
					po.setColorName(Constants.COLOR_NAME_DEFAULT);
				}
				if (StringUtils.isEmpty(po.getSizeName())) {
					po.setSizeName(Constants.SIZE_NAME_DEFAULT);
				}
				count = saleCategoryDao.insert(po);
				break;
			}
			if (count > 0) {
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("分类数据保存成功");
			} else {
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("分类数据保存到数据库未知异常");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("插入分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("分类数据保存到数据库出错");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("插入分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg(e.getMessage());
			result = false;
		}
		// 写日志表
		if (result) {

			/*
			 * 
			 * 写操作日志表 数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 */

			writeCategoryOperateLog(po, CategoryOptType.APPEND, type);

			StringBuilder sb = new StringBuilder();
			sb.append("【添加分类】#").append("分类类型[").append(type.name()).append("]@").append("分类名称[")
					.append(po.getCategoryName()).append("]@").append("分类编码[")
					.append(po.getCategoryCode()).append("]@").append("父分类ID[")
					.append(po.getParentId() == null ? "" : po.getParentId()).append("]@")
					.append("父分类编号[").append(po.getParentCode() == null ? "" : po.getParentCode())
					.append("]@").append("添加人[").append(po.getCreateUser()).append("]@")
					.append("添加时间[")
					.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getCreateDate()));
			LOG.info(sb.toString());
		}
		return resp;
	}

	@Override
	public Response update(CategoryPO po, Category_Type type) {
		assert po != null : "分类对象为空";
		int count = 0;
		boolean result = true;
		Response resp = new Response();
		try {
			if(StringUtils.isEmpty(po.getColorName())){
				po.setColorName(null);
			}
			if(StringUtils.isEmpty(po.getSizeName())){
				po.setSizeName(null);
			}
			switch (type) {
			case BASE_CATEGORY:
				count = baseCategoryDao.update(po);
				break;
			case SALE_CATEGORY:
				count = saleCategoryDao.update(po);
				break;
			}
			if (count > 0) {
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("分类数据更新成功");
			} else {
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("没有更新到分类数据");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("修改分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("Constants.修改分类操作失败");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("修改分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("修改分类操作失败");
			result = false;
		}

		// 写日志表
		if (result) {

			try {
				po = this.queryOne(po, type);
			} catch (DaoException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (po == null) {
				return resp;
			}

			/**
			 * 删除缓存
			 */
			this.removeCache(po, type,1);

			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！1
			 */
			writeCategoryOperateLog(po, CategoryOptType.UPDATE, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【修改分类】#").append("分类类型[").append(type.name()).append("]@").append("分类Id[")
					.append(po.getId()).append("]@").append("分类名称[").append(po.getCategoryName())
					.append("]@").append("分类编码[").append(po.getCategoryCode()).append("]@")
					.append("父分类ID[").append(po.getParentId() == null ? "" : po.getParentId())
					.append("]@").append("父分类编号[")
					.append(po.getParentCode() == null ? "" : po.getParentCode()).append("]@")
					.append("修改人[").append(po.getUpdateUser()).append("]@").append("修改时间[")
					.append(sdf.format(po.getUpdateDate()));
			LOG.info(sb.toString());
		}
		return resp;
	}

	
	@Override
	public Response show(CategoryPO po, Category_Type type) {
		int count = 0;
		boolean result = true;
		Response resp = new Response();
		try {
			if(StringUtils.isEmpty(po.getColorName())){
				po.setColorName(null);
			}
			if(StringUtils.isEmpty(po.getSizeName())){
				po.setSizeName(null);
			}
			switch (type) {
			case BASE_CATEGORY:
				count = baseCategoryDao.update(po);
				break;
			case SALE_CATEGORY:
				count = saleCategoryDao.update(po);
				break;
			}
			if (count > 0) {
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("分类数据显示成功");
			} else {
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("没有显示到分类信息");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("显示分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("Constants.显示分类操作失败");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("显示分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("显示分类操作失败");
			result = false;
		}

		// 写日志表
		if (result) {

			try {
				po = this.queryOne(po, type);
			} catch (DaoException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (po == null) {
				return resp;
			}

			/**
			 * 删除缓存
			 */
			this.removeCache(po, type,1);

			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！1
			 */
			writeCategoryOperateLog(po, CategoryOptType.SHOW, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【显示分类】#").append("分类类型[").append(type.name()).append("]@").append("分类Id[")
					.append(po.getId()).append("]@").append("分类名称[").append(po.getCategoryName())
					.append("]@").append("分类编码[").append(po.getCategoryCode()).append("]@")
					.append("父分类ID[").append(po.getParentId() == null ? "" : po.getParentId())
					.append("]@").append("父分类编号[")
					.append(po.getParentCode() == null ? "" : po.getParentCode()).append("]@")
					.append("操作人[").append(po.getUpdateUser()).append("]@").append("操作时间[")
					.append(sdf.format(po.getUpdateDate()));
			LOG.info(sb.toString());
			
			List<CategoryPO> sonList = this.selectByParentCode(po, type);
			if (sonList != null && sonList.size() > 0) {
				// 显示所有子分类
				this.showAllSons(po, type);
			}

		}
		return resp;
	
	}
	
	
	@Override
	public Response hidden(CategoryPO po, Category_Type type) {
		
		int count = 0;
		boolean result = true;
		Response resp = new Response();
		try {
			if(StringUtils.isEmpty(po.getColorName())){
				po.setColorName(null);
			}
			if(StringUtils.isEmpty(po.getSizeName())){
				po.setSizeName(null);
			}
			switch (type) {
			case BASE_CATEGORY:
				count = baseCategoryDao.update(po);
				break;
			case SALE_CATEGORY:
				count = saleCategoryDao.update(po);
				break;
			}
			if (count > 0) {
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("分类数据隐藏成功");
			} else {
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("没有隐藏到分类信息");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("隐藏分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("Constants.隐藏分类操作失败");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("隐藏分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("隐藏分类操作失败");
			result = false;
		}

		// 写日志表
		if (result) {

			try {
				po = this.queryOne(po, type);
			} catch (DaoException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (po == null) {
				return resp;
			}

			/**
			 * 删除缓存
			 */
			this.removeCache(po, type,1);

			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！1
			 */
			writeCategoryOperateLog(po, CategoryOptType.HIDDEN, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【隐藏分类】#").append("分类类型[").append(type.name()).append("]@").append("分类Id[")
					.append(po.getId()).append("]@").append("分类名称[").append(po.getCategoryName())
					.append("]@").append("分类编码[").append(po.getCategoryCode()).append("]@")
					.append("父分类ID[").append(po.getParentId() == null ? "" : po.getParentId())
					.append("]@").append("父分类编号[")
					.append(po.getParentCode() == null ? "" : po.getParentCode()).append("]@")
					.append("操作人[").append(po.getUpdateUser()).append("]@").append("操作时间[")
					.append(sdf.format(po.getUpdateDate()));
			LOG.info(sb.toString());
			
			List<CategoryPO> sonList = this.selectByParentCode(po, type);
			if (sonList != null && sonList.size() > 0) {
				// 隐藏所有子分类
				this.hiddenAllSons(po, type);
			}

		}
		return resp;
	}
	
	
	
	
	/**
	 * @Description:  删除缓存
	 * @param po
	 * @param type 
	 * @param operType  操作类型，1,修改，2删除
	 * return void
	 */
	private void removeCache(CategoryPO po, Category_Type type,long operType) {
		if (type == Category_Type.BASE_CATEGORY) {
			try {
				ProductOtherJobModel m = new ProductOtherJobModel();
				m.setProductOtherId(po.getId());
				m.setOtherCode(po.getCategoryCode());
				m.setType(2l);
				m.setOperate(operType);
				m.setCreateTime(po.getUpdateDate().getTime());
				m.setCreateUserName(po.getUpdateUser());
				m.setReason(operType==1?"修改基础分类":"删除基础分类");
				productOtherJobService.addProductOtherJob(m);
				LOG.info("将数据添加进调度数据表");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("将数据添加进调度数据表，操作失败：", e);
			}
		}
	}

	@Override
	public Response delete(CategoryPO po, Category_Type type) {
		int count = 0;
		boolean result = true;
		Response resp = new Response();
		try {
			switch (type) {
			case BASE_CATEGORY:
				count = baseCategoryDao.update(po);
				break;
			case SALE_CATEGORY:
				count = saleCategoryDao.update(po);
				break;
			}
			if (count > 0) {
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("分类数据删除成功");
			} else {
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("没有删除到分类数据");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("删除分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("删除分类操作失败");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("删除分类[" + type.name() + "]操作失败：", e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("删除分类操作失败");
			result = false;
		}

		// 写日志表
		if (result) {

			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！！！！
			 */
			try {
				po = this.queryOne(po, type);
			} catch (DaoException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (po == null) {
				return resp;
			}

			/**
			 * 删除缓存
			 */
			this.removeCache(po, type,2);

			writeCategoryOperateLog(po, CategoryOptType.DELETE, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【删除分类】#").append("分类类型[").append(type.name()).append("]@").append("分类ID[")
					.append(po.getId()).append("]@").append("分类名称[").append(po.getCategoryName())
					.append("]@").append("分类编码[").append(po.getCategoryCode()).append("]@")
					.append("父分类ID[").append(po.getParentId() == null ? "" : po.getParentId())
					.append("]@").append("父分类编号[")
					.append(po.getParentCode() == null ? "" : po.getParentCode()).append("]@")
					.append("删除人[").append(po.getUpdateUser()).append("]@").append("删除时间[")
					.append(sdf.format(po.getUpdateDate()));

			LOG.info(sb.toString());

			List<CategoryPO> sonList = this.selectByParentCode(po, type);
			if (sonList != null && sonList.size() > 0) {
				// 删除所有子分类
				this.deleteAllSons(po, type);
			}

		}
		return resp;
	}

	/**
	 * @Description: 父分类编号，查询其下所有的子分类
	 * @param po
	 * @param type
	 *            return List<CategoryPO>
	 */
	private List<CategoryPO> selectByParentCode(CategoryPO po, Category_Type type) {
		List<CategoryPO> tmpList = null;
		try {
			switch (type) {
			case BASE_CATEGORY:
				tmpList = baseCategoryDao.selectByParentCode(po);
				break;
			case SALE_CATEGORY:
				tmpList = saleCategoryDao.selectByParentCode(po);
				break;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]查询所有子分类[" + type.name() + "]操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]查询所有子分类[" + type.name() + "]操作失败：", e);
		}
		return tmpList;
	}

	
	private void showAllSons(CategoryPO po, Category_Type type) {
		try {
			switch (type) {
			case BASE_CATEGORY:
//				baseCategoryDao.deleteByParenCode(po);
				break;
			case SALE_CATEGORY:
				saleCategoryDao.showByParenCode(po);
				break;
			}
			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！！！！
			 */
			writeCategoryOperateLog(po, CategoryOptType.SHOW, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【根据父分类编号显示所有子分类】#").append("分类类型[").append(type.name()).append("]@")
					.append("父分类编号[").append(po.getCategoryCode()).append("]@").append("显示人[")
					.append(po.getUpdateUser()).append("]@").append("显示时间[")
					.append(sdf.format(po.getUpdateDate()));

			LOG.info(sb.toString());
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]显示分类[" + type.name() + "]操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]显示分类[" + type.name() + "]操作失败：", e);
		}
	}
	
	private void hiddenAllSons(CategoryPO po, Category_Type type) {
		try {
			switch (type) {
			case BASE_CATEGORY:
//				baseCategoryDao.deleteByParenCode(po);
				break;
			case SALE_CATEGORY:
				saleCategoryDao.hiddenByParenCode(po);
				break;
			}
			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！！！！
			 */
			writeCategoryOperateLog(po, CategoryOptType.HIDDEN, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【根据父分类编号隐藏所有子分类】#").append("分类类型[").append(type.name()).append("]@")
					.append("父分类编号[").append(po.getCategoryCode()).append("]@").append("隐藏人[")
					.append(po.getUpdateUser()).append("]@").append("隐藏时间[")
					.append(sdf.format(po.getUpdateDate()));

			LOG.info(sb.toString());
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]隐藏分类[" + type.name() + "]操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]隐藏分类[" + type.name() + "]操作失败：", e);
		}
	}
	
	
	private void deleteAllSons(CategoryPO po, Category_Type type) {
		try {
			switch (type) {
			case BASE_CATEGORY:
				baseCategoryDao.deleteByParenCode(po);
				break;
			case SALE_CATEGORY:
				saleCategoryDao.deleteByParenCode(po);
				break;
			}
			/*
			 * 
			 * 写操作日志表 数据库表 ！！！！！！！！！！！！！！！！！！！！！！！！！！！
			 */
			writeCategoryOperateLog(po, CategoryOptType.DEL_CHILD, type);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuilder sb = new StringBuilder();
			sb.append("【根据父分类编号删除所有子分类】#").append("分类类型[").append(type.name()).append("]@")
					.append("父分类编号[").append(po.getCategoryCode()).append("]@").append("删除人[")
					.append(po.getUpdateUser()).append("]@").append("删除时间[")
					.append(sdf.format(po.getUpdateDate()));

			LOG.info(sb.toString());
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]删除分类[" + type.name() + "]操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据父分类[parentCode:" + po.getCategoryCode() + "]删除分类[" + type.name() + "]操作失败：", e);
		}
	}
	
	@Override
	public Response showBatch(CategoryPO po, String idsStr, Category_Type type) {
		Response rsp = null;
		String[] ids = idsStr.split(",");
		po.setShowFlag(1);
		for (String id : ids) {
			po.setId(Long.valueOf(id));
			rsp = this.show(po, type);
			if (Constants.ERROR_CODE.equals(rsp.getCode())) {
				throw new RuntimeException("显示分类失败[" + type.name() + "]");// 这里是为了事务回滚
			}
		}
		rsp = new Response();
		rsp.setCode(Constants.SUCCESS_CODE);
		rsp.setMsg("显示分类操作成功");
		return rsp;
	}
	

	@Override
	public Response hiddenBatch(CategoryPO po, String idsStr, Category_Type type) {
		Response rsp = null;
		String[] ids = idsStr.split(",");
		po.setShowFlag(0);
		for (String id : ids) {
			po.setId(Long.valueOf(id));
			rsp = this.hidden(po, type);
			if (Constants.ERROR_CODE.equals(rsp.getCode())) {
				throw new RuntimeException("隐藏分类失败[" + type.name() + "]");// 这里是为了事务回滚
			}
		}
		rsp = new Response();
		rsp.setCode(Constants.SUCCESS_CODE);
		rsp.setMsg("隐藏分类操作成功");
		return rsp;
	}
	
	@Override
	public Response deleteBatch(CategoryPO po, String idsStr, Category_Type type) {
		Response rsp = null;
		String[] ids = idsStr.split(",");
		po.setDeleteFlag(1);
		for (String id : ids) {
			po.setId(Long.valueOf(id));
			rsp = this.delete(po, type);
			if (Constants.ERROR_CODE.equals(rsp.getCode())) {
				throw new RuntimeException("删除分类失败[" + type.name() + "]");// 这里是为了事务回滚
			}
		}
		rsp = new Response();
		rsp.setCode(Constants.SUCCESS_CODE);
		rsp.setMsg("删除分类操作成功");
		return rsp;
	}

	@Override
	public List<CategoryPO> queryAll(Category_Type type) {
		List<CategoryPO> poList = null;
		try {
			switch (type) {
			case BASE_CATEGORY:
				poList = baseCategoryDao.queryAll();
				break;
			case SALE_CATEGORY:
				poList = saleCategoryDao.queryAll();
				break;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("加载所有分类[" + type.name() + "]操作失败：", e);
		}
		return poList;
	}

	

	@Override
	public CategoryPO queryOne(CategoryPO po, Category_Type type) {
		CategoryPO retPo = null;
		try {
			switch (type) {
			case BASE_CATEGORY:
				retPo = baseCategoryDao.queryOne(po);
				break;
			case SALE_CATEGORY:
				retPo = saleCategoryDao.queryOne(po);
				break;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据分类ID加载分类[" + type.name() + "]操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据分类ID加载分类[" + type.name() + "]操作失败：", e);
		}
		return retPo;
	}

	@Override
	public Response queryDirectSonListByParentCode(CategoryPO parent, Category_Type type) {
		Response rsp = new Response();
		rsp.setCode(Constants.SUCCESS_CODE);
		List<CategoryPO> modelList = null;
		try {
			switch (type) {
			case BASE_CATEGORY:
				modelList = baseCategoryDao.selectSonsByParentCode(parent);
				break;
			case SALE_CATEGORY:
				modelList = saleCategoryDao.selectSonsByParentCode(parent);
				break;
			}
			rsp.setStatus(true);
			rsp.setModel(modelList);
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据根据父分类编号查询直接子分类列表[" + type.name() + "]操作失败：", e);
			rsp.setCode(Constants.ERROR_CODE);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据分根据父分类编号查询直接子分类列表[" + type.name() + "]操作失败：", e);
			rsp.setCode(Constants.ERROR_CODE);
		}
		return rsp;
	}

	/**
	 * @Description: 构建分类编码 格式 ：每一级分类两位,不足前边补0
	 * @param po
	 * @param type
	 *            return String
	 */
	@Override
	public String buildCurrentCode(CategoryPO po, Category_Type type) throws Exception {
		StringBuilder sb = new StringBuilder();
		CategoryPO tmp = null;
		long tot = 0;
		switch (type) {
		case BASE_CATEGORY:
			tot = baseCategoryDao.countListPage(po);
			tot=tot+10;
			if (po.getId() != null) {
				tmp = baseCategoryDao.queryOne(po);
			}
			break;
		case SALE_CATEGORY:
			tot = saleCategoryDao.countListPage(po);
			if (po.getId() != null) {
				tmp = saleCategoryDao.queryOne(po);
			}
			break;
		}
		if (tmp == null) {
			tmp = new CategoryPO();
		}
		if (StringUtils.isNotBlank(tmp.getCategoryCode())) {
			sb.append(tmp.getCategoryCode());
		}
		tot += 1;
		if (tot > 99) {
			throw new Exception("分类编号已经用完");
		}
		if (tot < 10) {
			sb.append("0");
		}
		sb.append(String.valueOf(tot));
		return sb.toString();
	}
	
	/**
	 * @Description: 设置颜色、尺码的值,父分类编码
	 * @param po
	 * @param type
	 * @throws Exception
	 * return void
	 */
	private  void  buildOtherInfo(CategoryPO po, Category_Type type) throws Exception {
		CategoryPO tmp = null;
		switch (type) {
		case BASE_CATEGORY:
			if (po.getId() != null) {
				tmp = baseCategoryDao.queryOne(po);
			}
			if (tmp != null) {
				po.setColorName(tmp.getColorName());
				po.setSizeName(tmp.getSizeName());
			} else {
				po.setColorName(Constants.COLOR_NAME_DEFAULT);
				po.setSizeName(Constants.SIZE_NAME_DEFAULT);
			}
			break;
		case SALE_CATEGORY:
			if (po.getId() != null) {
				tmp = saleCategoryDao.queryOne(po);
			}
			break;
		}
		if (tmp == null) {
			tmp = new CategoryPO();
		}
		if (StringUtils.isNotBlank(tmp.getCategoryCode())) {
			po.setCategoryCode(tmp.getCategoryCode());
		}
	}


	@Override
	public Response queryBaseCategoryColorAndSizeByCode(String code) {
		Response rsp = new Response();
		if (StringUtils.isBlank(code)) {
			rsp.setCode(Constants.ERROR_CODE);
			rsp.setMsg("传入的参数 code 为空");
			return rsp;
		}
		rsp.setCode(Constants.SUCCESS_CODE);
		CategoryPO tmp = new CategoryPO();
		tmp.setCategoryCode(code);
		try {
			rsp.setStatus(true);
			rsp.setModel(baseCategoryDao.queryOne(tmp));
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据根据基础分类编号查询颜色、尺码展示名称操作失败：", e);
			rsp.setCode(Constants.ERROR_CODE);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据根据基础分类编号查询颜色、尺码展示名称操作失败：", e);
			rsp.setCode(Constants.ERROR_CODE);
		}
		return rsp;
	}
	
	@Override
	public CategoryPO selectByParentIdAndCategoryName(CategoryPO po,Category_Type type) {
		try {
			switch (type) {
			case BASE_CATEGORY:
				return baseCategoryDao.selectByParentIdAndCategoryName(po);
			case SALE_CATEGORY:
				return saleCategoryDao.selectByParentIdAndCategoryName(po);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			LOG.error("根据根据基础分类编号查询颜色、尺码展示名称操作失败：", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据根据基础分类编号查询颜色、尺码展示名称操作失败：", e);
		}
		return null;
	}

	public void setBaseCategoryDao(BaseCategoryDao baseCategoryDao) {
		this.baseCategoryDao = baseCategoryDao;
	}

	public void setSaleCategoryDao(SaleCategoryDao saleCategoryDao) {
		this.saleCategoryDao = saleCategoryDao;
	}

	/**
	 * @Description: 基础分类日志插入
	 * @param po
	 * @param operateType
	 */
	private void writeBaseCategoryOperateLog(CategoryPO po, CategoryOptType operateType) {
		BaseCategoryOperateLog baseCategoryOperateLog = new BaseCategoryOperateLog();
		baseCategoryOperateLog.setCategoryId(po.getId());
		baseCategoryOperateLog.setCategoryName(po.getCategoryName());
		baseCategoryOperateLog.setCategoryCode(po.getCategoryCode());
		baseCategoryOperateLog.setParentId(po.getParentId());
		baseCategoryOperateLog.setParentCode(po.getParentCode());
		baseCategoryOperateLog.setOperateType(operateType.getValue());
		baseCategoryOperateLog.setOperateTime(po.getUpdateDate());
		baseCategoryOperateLog.setOperateUser(po.getUpdateUser());
		baseCategoryOperateLog.setLogDate(new Timestamp(System.currentTimeMillis()));

		baseCategoryLogService.insertCategoryOperateLog(baseCategoryOperateLog);
	}

	/**
	 * @Description 运营分类日志插入
	 * @param po
	 * @param operateType
	 */
	private void writeSaleCategoryOperateLog(CategoryPO po, CategoryOptType operateType) {
		SaleCategoryOperateLog saleCategoryOperateLog = new SaleCategoryOperateLog();
		saleCategoryOperateLog.setCategoryId(po.getId());
		saleCategoryOperateLog.setCategoryName(po.getCategoryName());
		saleCategoryOperateLog.setCategoryCode(po.getCategoryCode());
		saleCategoryOperateLog.setParentId(po.getParentId());
		saleCategoryOperateLog.setParentCode(po.getParentCode());
		saleCategoryOperateLog.setOperateType(operateType.getValue());
		saleCategoryOperateLog.setOperateTime(new Timestamp(po.getUpdateDate().getTime()));
		saleCategoryOperateLog.setOperateUser(po.getUpdateUser());
		saleCategoryOperateLog.setLogDate(new Timestamp(System.currentTimeMillis()));

		saleCategoryLogService.insert(saleCategoryOperateLog);
	}

	private void writeCategoryOperateLog(CategoryPO po, CategoryOptType operateType, Category_Type categoryType) {
		switch (categoryType) {
		case BASE_CATEGORY:
			writeBaseCategoryOperateLog(po, operateType);
			break;
		case SALE_CATEGORY:
			writeSaleCategoryOperateLog(po, operateType);
			break;
		}
	}

	@Override
	public Map<String, Long> getListByCategoryCodes(List<String> categoryCodeList) {
		if (LOG.isDebugEnabled())
        {
			LOG.debug("--Params-->" + categoryCodeList.toString());
        }
		
		List<CategoryPO> categoryList = this.saleCategoryDao.getListByCategoryCodes(categoryCodeList);
		if (CollectionUtils.isNotEmpty(categoryList)) {
			Map<String, Long> map = new HashMap<String, Long>();
			for (CategoryPO item : categoryList) {
				map.put(item.getCategoryCode(), item.getId());
			}
			return map;
		}
		return null;
		
	}
	
	@Override
	public boolean checkExistLevel1ByIds(String ids, Category_Type type) {
		CategoryPO tmpPo;
		String[] tmpIds = ids.split(",");
		try{
			for(String tId:tmpIds){
				tmpPo = new CategoryPO();
				tmpPo.setId(Long.parseLong(tId));
				tmpPo = this.queryOne(tmpPo, type);
				if(tmpPo != null && tmpPo.getParentId() == null){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("根据分类ID查询分类出错",e);
		}
		return false;
	}

	/**
	 * productCode: 010102 
	 */
	@Override
	public List<String> getListByProductCode(String productCode) {
		ProductSalecategoryRelationModel psRel = psRelService.getByProductCode(productCode);
		if(psRel == null){
			return null;
		}
		String categoryCodes = psRel.getCategoryCodes();
		categoryCodes = categoryCodes.substring(1, categoryCodes.length()-1);
		List<String> tmpList = Arrays.asList(categoryCodes.trim().split(";"));
		List<String> categoryCodeList = new ArrayList<String>();
		for(String categoryCode: tmpList){
			while(categoryCode.length() >= 2){
				categoryCodeList.add(categoryCode);
				categoryCode = categoryCode.substring(0, categoryCode.length() - 2);
			}
		}
		//返回根据category_code降序排好序的列表
		/*
		11	求租房		03010103	7	030101
		7	租房			030101		6	0301
		6	深圳房产		0301		5	03
		5	房产			03			
		26	sdfsdfs		0104		1	01
		1	level1-1	01		
		 */
		List<CategoryPO> categoryList = this.saleCategoryDao.getCategoryListByCategoryCodes(categoryCodeList);
		List<String> categoryNamesList = new ArrayList<String>();
		String categoryNames = "";
		String conStr = " -> ";
		for(CategoryPO po: categoryList){
			categoryNames = po.getCategoryName() + conStr + categoryNames;
			if(po.getParentId() == null){
				categoryNamesList.add(categoryNames.substring(0,categoryNames.length()-conStr.length()));
				categoryNames = "";
			}
		}
		
		return categoryNamesList;
		
	}


	/**
	 * 判断该运营分类下是否为叶子分类节点
	 */
	@Override
	public boolean isLeafNode(Long categoryId) throws ZhaidouRuntimeException{
		if(categoryId == null)
			throw new ZhaidouRuntimeException("运营分类ID 为空");
		//通过categoryId 查询 分类+及子节点
		//若返回	0 则代表该分类不存在， 1代表该分类没有子节点， 大于1表示子节点存在
		try{
			Long count = this.saleCategoryDao.countCategoryAndSubList(categoryId);
			if(count > 1)
				return false;
			else if(count == 1){
				return true;
			}else{
				throw new ZhaidouRuntimeException("运营分类ID 为" + categoryId + "的分类不存在，请选择末级分类");
			}
		}catch(Exception e){
			throw new ZhaidouRuntimeException("查询运营分类ID为" + categoryId + "失败", e);
		}
		
	}
	
	
	/**
	 * 判断该运营分类下是否为叶子分类节点
	 */
	@Override
	public boolean isLeafNode(String categoryCode) throws ZhaidouRuntimeException{
		if(categoryCode == null){
			throw new ZhaidouRuntimeException("运营分类categoryCode 为空");

		}
		//通过categoryCode 查询 分类+及子节点
		//若返回	0 则代表该分类不存在， 1代表该分类没有子节点， 大于1表示子节点存在
		try{
			Long count = this.saleCategoryDao.countCategoryAndSubList(categoryCode);
			if(count == 1)
				return true;
			else{
				return false;
			}
		}catch(Exception e){
			throw new ZhaidouRuntimeException("查询运营分类categoryCode为" + categoryCode + "失败", e);
		}
		
	}
	
	
	@Override
	public List<String> queryCodeListByIds(String ids) throws ZhaidouRuntimeException, NumberFormatException {
		String[] tmpIds = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for(int i = 0; i < tmpIds.length; i++){
			idList.add(Integer.valueOf(tmpIds[i]));
		}
		
		try{
			return this.saleCategoryDao.queryCodeListByIds(idList);
		}catch(DaoException e){
			throw new ZhaidouRuntimeException(e.getMessage(), e);
		}
		
	}

	@Override
	public List<String> queryBaseCodeListByIds(String ids) throws ZhaidouRuntimeException, NumberFormatException {
		String[] tmpIds = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for(int i = 0; i < tmpIds.length; i++){
			idList.add(Integer.valueOf(tmpIds[i]));
		}
		
		try{
			return this.baseCategoryDao.queryCodeListByIds(idList);
		}catch(DaoException e){
			throw new ZhaidouRuntimeException(e.getMessage(), e);
		}
		
	}
	
	
	@Override
    public List<CategoryPO> selectAllByLevel(Integer level,Category_Type type) throws Exception {
		
		if(level != null){
    		level = level * 2; 
    	}
		switch (type) {
		case BASE_CATEGORY:
			return baseCategoryDao.selectAllByLevel(level);
		case SALE_CATEGORY:
			return saleCategoryDao.selectAllByLevel(level);
		}
    	return null;
    }
	
	
	@Override
	public List<CategoryPO> selectParentAndSonByCode(String categoryCode,
			Category_Type type) throws Exception {
		switch (type) {
		case BASE_CATEGORY:
			return baseCategoryDao.selectParentAndSonByCode(categoryCode);
		case SALE_CATEGORY:
			return saleCategoryDao.selectParentAndSonByCode(categoryCode);
		}
    	return null;
	}
	
	@Override
	public Workbook writeExcel(List<String> titleList,
			List<CategoryPO> catePOList) {
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		
		XSSFSheet sheet = workBook.createSheet("2015");// 创建一个Excel的Sheet
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		
		 //1.创建的表头
        Row sysRow = sheet.createRow(0);
        sysRow.setRowStyle(headStyle);
        for (int i = 0; i < titleList.size(); i++) {
            Cell cell = sysRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titleList.get(i));
        }
        
        int j = 1;
        Map<String ,CategoryPO> level1 =  new HashMap<String,CategoryPO>();
        Map<String ,CategoryPO> level2 =  new HashMap<String,CategoryPO>();
        Map<String ,CategoryPO> level3 =  new HashMap<String,CategoryPO>();
        Map<String ,CategoryPO> noRight =  new HashMap<String,CategoryPO>();
        for (CategoryPO cate : catePOList){
        	if(cate.getCategoryCode().length() == 2){
        		level1.put(cate.getCategoryCode(),cate);
        		continue;
        	}
        	if(cate.getCategoryCode().length() == 4){
        		level2.put(cate.getCategoryCode(),cate);
        		continue;
        	}
        	if(cate.getCategoryCode().length() == 6){
        		level3.put(cate.getCategoryCode(),cate);
        		continue;
        	}
        	noRight.put(cate.getCategoryCode(), cate);
        }
        
        LOG.info("不合格的运营分类列表：" +noRight);
        
        
        //对level3排序
        List<Entry<String,CategoryPO>> level3List = new ArrayList<Map.Entry<String,CategoryPO>>(level3.entrySet());
        Collections.sort(level3List, new Comparator<Map.Entry<String,CategoryPO>>(){ 
        	   public int compare(Map.Entry<String,CategoryPO> mapping1,Map.Entry<String,CategoryPO> mapping2){ 
        	    return mapping1.getKey().compareTo(mapping2.getKey()); 
        	   }
        });
        
        
        
        /*
         * 2.填写数据
         * 表格数据格式：一级分类code,一级分类name,二级分类code,二级分类name,三级分类code,三级分类name
         * */
        for (Entry<String, CategoryPO> cate : level3List) {
        	Row sysRowJ = sheet.createRow(j); //创建行
        	
        	try {
				
				CategoryPO cate1 = level1.get(cate.getKey().substring(0, 2));
				if(cate1 != null){
					Cell cell0 = sysRowJ.createCell(0);
					cell0.setCellValue(cate1.getCategoryCode());
					Cell cell1 = sysRowJ.createCell(1);
					cell1.setCellValue(cate1.getCategoryName());
				}
				
				CategoryPO cate2 = level2.get(cate.getKey().substring(0, 4));
				if(cate2 != null){
					Cell cell2 = sysRowJ.createCell(2);
					cell2.setCellValue(cate2.getCategoryCode());
					Cell cell3 = sysRowJ.createCell(3);
					cell3.setCellValue(cate2.getCategoryName());
				}
				
				Cell cell4 = sysRowJ.createCell(4);
				cell4.setCellValue(cate.getValue().getCategoryCode());
				Cell cell5 = sysRowJ.createCell(5);
				cell5.setCellValue(cate.getValue().getCategoryName());
				j++;  // 控制行数
			} catch (Exception e) {
				LOG.error("导出运营分类出错，错误信息=="+e.getMessage(), e);
			}
        }
       
        return workBook;
	}

	
}
