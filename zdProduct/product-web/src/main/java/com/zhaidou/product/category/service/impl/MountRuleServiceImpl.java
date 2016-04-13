package com.zhaidou.product.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.common.util.Response;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.MountRuleDao;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.MountRuleLog;
import com.zhaidou.product.category.model.MountRulePO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.MountRuleLogService;
import com.zhaidou.product.category.service.MountRuleOptType;
import com.zhaidou.product.category.service.MountRuleService;
import com.zhaidou.product.category.util.Constants;

@Service("mountRuleService")
public class MountRuleServiceImpl implements MountRuleService {

	private static Logger LOG = Logger.getLogger(MountRuleServiceImpl.class);
	
	@Autowired
	private MountRuleDao mountRuleDao;
	
	@Autowired
	private CategoryService categoryService;
	
	@Resource(name="mountRuleLogService")
	private MountRuleLogService mountRuleLogService;
	
	@Override
	public Response insert(MountRulePO po) {
		Response resp = new Response();
		
		int count = 0;
		boolean result = true;
		try{
			CategoryPO cate = new CategoryPO();
			cate.setId(po.getCategoryId());
			cate = categoryService.queryOne(cate, Category_Type.SALE_CATEGORY);
			if(cate !=null){
				po.setCategoryCode(cate.getCategoryCode());
			}
			
			if(StringUtils.isNotEmpty(po.getBrandCodeList()) && po.getBrandCodeList().endsWith(";")){
				po.setBrandCodeList(po.getBrandCodeList().substring(0,po.getBrandCodeList().length()-1));
			}
			
			//替换分类ID列表  --> 分类编号列表
			po.setCategoryCodeList(this.replaceCateCodeList(po.getCategoryCodeList()));
			
			//封装表达式
			po.setMountRule(this.buildRuleExpress(po));
			
			count = mountRuleDao.insert(po);
			if(count > 0){
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("挂载规则数据保存成功");
			}else{
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("挂载规则数据保存到数据库未知异常");
			}
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("插入挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("挂载规则数据保存到数据库出错");
			result = false;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("插入挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg(e.getMessage());
			result = false;
		}
		
		
		//写日志表
		if(result){
			
			/*
			 * 
			 * 写操作日志表    数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			
			recordMountRuleLog(po,MountRuleOptType.APPEND);
			StringBuilder sb = new StringBuilder();
			sb.append("【添加规则】").append("]@")
			.append("ID[").append(po.getId()).append("]@")
			.append("分类ID[").append(po.getCategoryId()).append("]@")
			.append("分类编码[").append(po.getCategoryCode()).append("]@")
			.append("productName[").append(po.getProductName()).append("]@")
			.append("productPrefix[").append(po.getProductPrefix()).append("]@")
			.append("productSuffix[").append(po.getProductSuffix()).append("]@")
			.append("searchTags[").append(po.getProductSearchTags()).append("]@")
			.append("brandCodes[").append(po.getBrandCodeList()).append("]@")
			.append("categoryCodes[").append(po.getCategoryCodeList()).append("]@")
			.append("添加人[").append(po.getCreateUser()).append("]@")
			.append("添加时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getCreateDate()));
			LOG.info(sb.toString());
		}
				
		return resp;
	}
	
	/**
	 * @Description:  把分类ID列表替换成分类编号列表
	 * @param src
	 * return String
	 */
	private String replaceCateCodeList(String src){
		if(StringUtils.isEmpty(src)){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String[] codes = src.split(";");
		CategoryPO tmp;
		for(String c:codes){
			tmp = new CategoryPO();
			tmp.setId(Long.parseLong(c));
			try{
				tmp = categoryService.queryOne(tmp, Category_Type.BASE_CATEGORY);
				if(tmp!=null){
					sb.append(tmp.getCategoryCode());
				}else{
					sb.append(c);
				}
				sb.append(";");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(sb.length()==0){
			return null;
		}
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * @Description:  构造表达式【这里只是暂时连接，索引结构修之后，替换为索引字段名称！！！！！】
	 * @param po
	 * return String
	 */
	private String buildRuleExpress(MountRulePO po){
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotEmpty(po.getProductName())){
			sb.append("productName:").append(po.getProductName());
			sb.append(" AND ");
		}
		if(StringUtils.isNotEmpty(po.getProductPrefix())){
			sb.append("productPrefix:").append(po.getProductPrefix());
			sb.append(" AND ");
		}
		if(StringUtils.isNotEmpty(po.getProductSuffix())){
			sb.append("productSuffix:").append(po.getProductSuffix());
			sb.append(" AND ");
			
		}
		if(StringUtils.isNotEmpty(po.getProductSearchTags())){
			sb.append("searchTags:").append(po.getProductSearchTags());
			sb.append(" AND ");
		}
		if(StringUtils.isNotEmpty(po.getBrandCodeList())){
			String[] tmp = po.getBrandCodeList().split(";");
			StringBuilder bStr = new StringBuilder(" ( ");
			for(int j=0;j<tmp.length;j++){
				bStr.append("brandCode:").append(tmp[j]);
				if(j < tmp.length-1){
					bStr.append(" OR ");
				}
			}
			bStr.append(" ) ");
			sb.append(bStr).append(" AND ");
		}
		
		if(StringUtils.isNotEmpty(po.getCategoryCodeList())){
			String[] tmp = po.getCategoryCodeList().split(";");
			StringBuilder bStr = new StringBuilder(" ( ");
			for(int j=0;j<tmp.length;j++){
				bStr.append("categoryCode:").append(tmp[j]);
				if(j < tmp.length-1){
					bStr.append(" OR ");
				}
			}
			bStr.append(" ) ");
			sb.append(bStr);
		}
		
		return sb.toString();
	}

	@Override
	public Response delete(MountRulePO po) {
		Response resp = new Response();
		
		int count = 0;
		boolean result = true;
		MountRulePO	tmpPo = null;
		try{
			tmpPo = this.queryOne(po);
			count = mountRuleDao.update(po);
			if(count > 0){
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("挂载规则数据删除成功");
			}else{
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("挂载规则数据删除出现未知异常");
			}
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("插入挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("挂载规则数据删除出错");
			result = false;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("删除挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg(e.getMessage());
			result = false;
		}
		
		
		//写日志表
		if(result){
			
			/*
			 * 
			 * 写操作日志表    数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			if(tmpPo != null){
				recordMountRuleLog(tmpPo,MountRuleOptType.DELETE);
			}else{
				recordMountRuleLog(po,MountRuleOptType.DELETE);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("【删除规则】").append("]@")
			.append("ID[").append(po.getId()).append("]@")
			.append("修改人[").append(po.getUpdateUser()).append("]@")
			.append("修改时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getUpdateDate()));
			LOG.info(sb.toString());
		}
		
		return resp;
	}

	@Override
	public Response update(MountRulePO po) {
		Response resp = new Response();
		
		int count = 0;
		boolean result = true;
		try{
			
			//剔除结尾的;号
			if(StringUtils.isNotEmpty(po.getBrandCodeList()) ){
				if(po.getBrandCodeList().endsWith(";")){
					po.setBrandCodeList(po.getBrandCodeList().substring(0,po.getBrandCodeList().length()-1));
				}
			}else{
				po.setBrandCodeList(null);
			}
			
			//替换分类ID列表  --> 分类编号列表
			po.setCategoryCodeList(this.replaceCateCodeList(po.getCategoryCodeList()));
			
			//封装表达式
			po.setMountRule(this.buildRuleExpress(po));
			
			count = mountRuleDao.update(po);
			if(count > 0){
				resp.setCode(Constants.SUCCESS_CODE);
				resp.setMsg("修改挂载规则数据成功");
			}else{
				resp.setCode(Constants.ERROR_CODE);
				resp.setMsg("修改挂载规则出现未知异常");
			}
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("修改挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg("修改挂载规则出错");
			result = false;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("修改挂载规则操作失败：",e);
			resp.setCode(Constants.ERROR_CODE);
			resp.setMsg(e.getMessage());
			result = false;
		}
		
		
		//写日志表
		if(result){
			try {
				MountRulePO tmpPo = this.queryOne(po);
				if(tmpPo != null){
					//回传po对象到controller，需要用到categoryId
					po.setCategoryCode(tmpPo.getCategoryCode());
					po.setCategoryId(tmpPo.getCategoryId());
				}
			} catch (DaoException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (po == null) {
				return resp;
			}
			/*
			 * 
			 * 写操作日志表    数据库表 !!!!!!!!!!!!!!!!!!!!!!!!
			 * 
			 */
			
			recordMountRuleLog(po,MountRuleOptType.UPDATE);
			StringBuilder sb = new StringBuilder();
			sb.append("【修改规则】").append("]@")
			.append("分类ID[").append(po.getCategoryId()).append("]@")
			.append("分类编码[").append(po.getCategoryCode()).append("]@")
			.append("productName[").append(po.getProductName()).append("]@")
			.append("productPrefix[").append(po.getProductPrefix()).append("]@")
			.append("productSuffix[").append(po.getProductSuffix()).append("]@")
			.append("searchTags[").append(po.getProductSearchTags()).append("]@")
			.append("brandCodes[").append(po.getBrandCodeList()).append("]@")
			.append("categoryCodes[").append(po.getCategoryCodeList()).append("]@")
			.append("修改人[").append(po.getUpdateUser()).append("]@")
			.append("修改时间[").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(po.getUpdateDate()));
			LOG.info(sb.toString());
		}
				
		return resp;
	}

	@Override
	public MountRulePO queryOne(MountRulePO po) {
		MountRulePO retPo = null;
		try{
			retPo = mountRuleDao.queryOne(po);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("查询挂载规则操作失败：",e);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("查询挂载规则操作失败：",e);
		}
		return retPo;
	}

	@Override
	public Long totCountByEffective() throws Exception{
		try{
			return mountRuleDao.totCountByEffective();
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("查询有效挂载规则总量操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("查询有效挂载规则总量操作失败：",e);
			throw e;
		}
	}

	@Override
	public List<MountRulePO> queryPage(Page page) throws Exception{
		try{
			return mountRuleDao.queryPage(page);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("分页查询挂载规则操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("分页查询挂载规则操作失败：",e);
			throw e;
		}
	}

	@Override
	public List<MountRulePO> queryDeleteRuleByDelDate(String date) throws Exception {
		try{
			return mountRuleDao.queryDeleteRuleByDelDate(date);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("根据日期分页查询已删除挂载规则操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("根据日期分页查询已删除挂载规则操作失败：",e);
			throw e;
		}
	}

	public void setMountRuleDao(MountRuleDao mountRuleDao) {
		this.mountRuleDao = mountRuleDao;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private void recordMountRuleLog(MountRulePO po, MountRuleOptType mountRuleOptType) {
		MountRuleLog mountRuleLog = new MountRuleLog();
		mountRuleLog.setMountId(po.getId());
		mountRuleLog.setCategoryId(po.getCategoryId());
		mountRuleLog.setCategoryCode(po.getCategoryCode());
		mountRuleLog.setProductName(po.getProductName());
		mountRuleLog.setProductPrefix(po.getProductPrefix());
		mountRuleLog.setProductSuffix(po.getProductSuffix());
		mountRuleLog.setSearchTag(po.getProductSearchTags());
		mountRuleLog.setBrandCodes(po.getBrandCodeList());
		mountRuleLog.setCategoryCodes(po.getCategoryCodeList());
		mountRuleLog.setOptType(mountRuleOptType.getValue());
		mountRuleLog.setOperateTime(po.getUpdateDate());
		mountRuleLog.setOperateUser(po.getUpdateUser());
		try {
			this.mountRuleLogService.insert(mountRuleLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

