package com.zhaidou.product.category.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.Response;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.ProductAttrModel;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.attributies.service.ProductAttrService;
import com.zhaidou.product.attributies.service.ProductAttrValueService;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.FilterMountPO;
import com.zhaidou.product.category.model.FilterMountShowPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.FilterMountService;
import com.zhaidou.product.category.util.Constants;
import com.zhaidou.product.category.util.Utils;

/**
 * 
 * @Title: FilterMountController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   运营分类筛选项挂载Controller
 *
 * @author lvshuding 
 *
 * @date 2015年4月8日 上午9:27:23 
 *
 * @version V1.0
 */
@Controller("filterMountController")
@RequestMapping("/filter-mount")
public class FilterMountController extends BaseController{

	private final static String CURRENT_CATE_ID="currentCateId";
	
	@Autowired
	private ProductAttrGroupService productAttrGroupService;
	
	@Autowired
	private ProductAttrService productAttrService;
	
	@Autowired
	private ProductAttrValueService productAttrValueService;
	
	@Autowired
	private FilterMountService filterMountService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * 颜色筛选项启用标识名称
	 */
	private final static String COLOR_SHOW_FLAG="colorShowFlag";
	
	/*
	 * 尺码筛选项启用标识名称
	 */
	private final static String SIZE_SHOW_FLAG="sizeShowFlag";
	
	/*
	 * 是否是一级分类标识名称
	 */
	private final static String FIRST_LEVEL_FLAG="isLevel1";
	
	
	@RequestMapping("/main")
	public String main(HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		String curCateId = req.getParameter(CURRENT_CATE_ID);
		model.addAttribute(CURRENT_CATE_ID, curCateId);
		
		boolean isLevel1=false;
		String curLevel = req.getParameter("currentCateLevel");
		if(curLevel != null && "0".equals(curLevel)){
			isLevel1=true;
		}
		
		boolean colorShow = false;
		boolean sizeShow = false;
		
		// 1.根据分类id加载所有筛选项数据
		FilterMountPO po = new FilterMountPO();
		po.setCategoryId(Long.parseLong(curCateId));
		List<FilterMountPO> fmList = null;
		List<FilterMountShowPO> sList = new ArrayList<FilterMountShowPO>();
		
		Map<String,Integer> orderMap = new HashMap<String, Integer>();
		
		try{
			fmList = filterMountService.queryListByCategoryId(po);
			if(fmList == null || fmList.size()==0){
				model.addAttribute("addOper", true);
			}else{
				model.addAttribute("addOper", false);
				// key:categoryCode value: <attrCode,attrValCode>>
				Map<String, Map<String, List<String>>> tmpMap = new HashMap<String, Map<String, List<String>>>();
				for(FilterMountPO p:fmList){
					if(isLevel1 && p.getAttrId() == null){
						if(Constants.COLOR_SHOW_FLAG.equalsIgnoreCase(p.getAttrCode())){
							colorShow = true;
							continue;
						}
						if(Constants.SIZE_SHOW_FLAG.equalsIgnoreCase(p.getAttrCode())){
							sizeShow = true;
							continue;
						}
					}
					if(!tmpMap.containsKey(p.getCategoryCode())){
						tmpMap.put(p.getCategoryCode(), new HashMap<String,List<String>>());
					}
					if(!tmpMap.get(p.getCategoryCode()).containsKey(p.getAttrCode())){
						tmpMap.get(p.getCategoryCode()).put(p.getAttrCode(),new ArrayList<String>());
					}
					tmpMap.get(p.getCategoryCode()).get(p.getAttrCode()).add(p.getAttrValCode());
					
					orderMap.put(p.getCategoryCode()+"_"+p.getAttrCode(), p.getOrderNumber());
				}
				ProductAttrModel tmpPa;
				ProductAttrValueModel tmpAv;
				FilterMountShowPO to;
				boolean attHas=true;
				for(String k:tmpMap.keySet()){
					for(String c:tmpMap.get(k).keySet()){
						to = new FilterMountShowPO();
						to.setId(k+"_"+c);
						tmpPa = productAttrService.getProductAttrByCode(c);
						if(tmpPa == null){
							attHas = false;
							break;
						}
						to.setAttName(tmpPa.getAttrName());
						for(String ac:tmpMap.get(k).get(c)){
							tmpAv = productAttrValueService.getProductAttrValueByCode(ac);
							if(tmpAv == null){
								continue;
							}
							to.getAttrValNameList().add(tmpAv.getAttrValue());
						}
						to.setOrderNumber(orderMap.get(k+"_"+c));
						sList.add(to);
					}
					if(!attHas){
						continue;
					}
				}
			}
			
			model.addAttribute("fmShowList", sList);
			model.addAttribute(COLOR_SHOW_FLAG, colorShow);
			model.addAttribute(SIZE_SHOW_FLAG, sizeShow);
			model.addAttribute(FIRST_LEVEL_FLAG, isLevel1);
			
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台服务出错", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		//2. 加载所有属性组
		Page page = new Page();
		page.setPageNum(1);
		page.setNumPerPage(100000);
		try{
			List<ProductAttrGroupModel> groupList = productAttrGroupService.getAttrGroupList(page);
			if(groupList == null){
				groupList = new ArrayList<ProductAttrGroupModel>();
			}
			model.addAttribute("groupList", groupList);
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
		}
		
		return "category/filter_mount";
	}
	
	/**
	 * @Description: 查询分组列表 [AJAX请求]
	 * @param group  如果groupName有值，根据名称查询，否则，加载所有
	 * @param req
	 * @param rsp
	 * return String
	 */
	@RequestMapping(value="/queryGroups",method={ RequestMethod.GET,RequestMethod.POST }, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String ajaxQueryGroup(ProductAttrGroupModel group,HttpServletRequest req,HttpServletResponse rsp){
		String returnStr = "[]";
		try{
			List<ProductAttrGroupModel> groupList = null;
			if(StringUtils.isBlank(group.getGroupName())){
				Page page = new Page();
				page.setPageNum(1);
				page.setNumPerPage(100000);
				groupList = productAttrGroupService.getAttrGroupList(page);
			}else{
				groupList = productAttrGroupService.queryAttrGroupListByName(group.getGroupName());
			}
			if(groupList != null){
				returnStr = JSONArray.fromObject(groupList).toString();
			}
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
		}
		
		return returnStr;
	}
	
	/**
	 * @Description:  根据属性组编号查询属性项列表 [AJAX请求]
	 * @param group
	 * @param req
	 * @param rsp
	 * return String
	 */
	@RequestMapping(value="/queryAttrs",method={ RequestMethod.GET,RequestMethod.POST }, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String ajaxQueryAttrs(ProductAttrGroupModel group,HttpServletRequest req,HttpServletResponse rsp){
		
		String returnStr = "[]";
		Page page = new Page();
		page.setPageNum(1);
		page.setNumPerPage(100000);
		try{
			List<ProductAttrModel> attrList = productAttrService.queryAttrListPageByGroupCode(group.getGroupCode(), page);
			if(attrList != null){
				returnStr = JSONArray.fromObject(attrList).toString();
			}
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
		}
		return returnStr;
	}
	
	/**
	 * @Description:  根据属性项编号查询属性值列表 [AJAX请求]
	 * @param group
	 * @param req
	 * @param rsp
	 * return String
	 */
	@RequestMapping(value="/queryAttrVals",method={ RequestMethod.GET,RequestMethod.POST }, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String ajaxQueryAttrVals(ProductAttrModel group,HttpServletRequest req,HttpServletResponse rsp){
		
		String returnStr = "[]";
		Page page = new Page();
		page.setPageNum(1);
		page.setNumPerPage(100000);
		try{
			List<ProductAttrValueModel> attrList = productAttrValueService.getProductAttrValueListPageByAttrCode(group.getAttrCode(), page);
			if(attrList != null){
				returnStr = JSONArray.fromObject(attrList).toString();
			}
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
		}
		return returnStr;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String toAdd(HttpServletRequest req,HttpServletResponse rsp){
		/*
		 * 校验用户登录
		 * 
		 * 
		 */
		String curCateId = req.getParameter(CURRENT_CATE_ID);
		CategoryPO catePo = new CategoryPO();
		catePo.setId(Long.parseLong(curCateId));
		catePo = categoryService.queryOne(catePo, Category_Type.SALE_CATEGORY);
		if(catePo == null){
			return AjaxObject.newErrorForward("运营分类已被删除或不存在", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		//格式：属性项Code1;属性值Code11,属性值Code12;排序值1#属性项Code2;属性值Code21,属性值Code22;排序值
		String newAddFilterData = req.getParameter("newAddFilterData");
		if(StringUtils.isEmpty(newAddFilterData)){
			return AjaxObject.newErrorForward("没有可保存的数据", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		
		String colorShowFlag = req.getParameter(COLOR_SHOW_FLAG);
		String sizeShowFlag = req.getParameter(SIZE_SHOW_FLAG);
		
		/*
		 * 
		 * 这里要获取登录用户的用户名
		 * 
		 * 
		 */
		String operUser = Utils.getUserNameFromRequest(req);
		
		String msg="";
		Response result = null;
		try{
			List<FilterMountPO> fList = this.buildNewFMList(newAddFilterData, catePo,operUser);
			//颜色
			if(StringUtils.isNotEmpty(colorShowFlag) && "1".equals(colorShowFlag)){
				fList.add(this.buildSpecialPO(catePo, Constants.COLOR_SHOW_FLAG, operUser));
			}
			//尺码
			if(StringUtils.isNotEmpty(sizeShowFlag) && "1".equals(sizeShowFlag)){
				fList.add(this.buildSpecialPO(catePo, Constants.SIZE_SHOW_FLAG, operUser));
			}
			result = filterMountService.insertBatch(fList);
			msg = result.getMsg();
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
			msg = "保存数据失败";
		}
		return AjaxObject.newOkForward(msg, "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		
	}
	
	private FilterMountPO buildSpecialPO(CategoryPO catePo,String attrCode,String operUser){
		FilterMountPO tmpPo = new FilterMountPO();
		tmpPo.setCategoryId(catePo.getId());
		tmpPo.setCategoryCode(catePo.getCategoryCode());
		tmpPo.setAttrCode(attrCode);
		tmpPo.setDeleteFlag(0);
		tmpPo.setCreateDate(new Date(System.currentTimeMillis()));
		tmpPo.setCreateUser(operUser);
		tmpPo.setUpdateDate(tmpPo.getCreateDate());
		tmpPo.setUpdateUser(operUser);
		return tmpPo;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String toDelete(FilterMountPO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr){
		/*
		 * 校验用户登录
		 * 
		 * 
		 */
		//这里要获取登录用户的用户名
		String operUser = Utils.getUserNameFromRequest(req);
		
		po.setUpdateUser(operUser);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		po.setDeleteFlag(1);
			
		Response result = filterMountService.deleteByCategoryId(po);
		
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main?"+CURRENT_CATE_ID+"="+po.getCategoryId()).toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String toUpdate(HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr){

		String curCateId = req.getParameter(CURRENT_CATE_ID);
		CategoryPO catePo = new CategoryPO();
		catePo.setId(Long.parseLong(curCateId));
		catePo = categoryService.queryOne(catePo, Category_Type.SALE_CATEGORY);
		if(catePo == null){
			return AjaxObject.newErrorForward("运营分类已被删除或不存在", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		
		//格式：属性项Code1;属性值Code11,属性值Code12;排序值1#属性项Code2;属性值Code21,属性值Code22;排序值
		String newAddFilterData = req.getParameter("newAddFilterData");
//		if(StringUtils.isEmpty(newAddFilterData)){
//			return AjaxObject.newForward("没有可保存的数据", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
//		}
			
		/*
		 * 
		 * 这里要获取登录用户的用户名
		 * 
		 * 
		 */
		String operUser = Utils.getUserNameFromRequest(req);
		
		//需要新增的
		List<FilterMountPO> fmList = this.buildNewFMList(newAddFilterData, catePo,operUser);
		
		//需要删除的
		String delIds = req.getParameter("oldDelIdsData");
		if(StringUtils.isNotEmpty(delIds) && ";".equals(delIds)){
			delIds = null;
		}
		
		//需要修改的
		String orderVals = req.getParameter("oldOrderData");
		
		
		boolean isLevel1=false;
		String curLevel = req.getParameter(FIRST_LEVEL_FLAG);
		if(StringUtils.isNotEmpty(curLevel)){
			isLevel1=Boolean.parseBoolean(curLevel);
		}
		
		boolean colorShow = false;
		boolean sizeShow = false;
		Long deleteColorShowId=null;
		Long deleteSizeShowId=null;
		
		String colorShowFlag = req.getParameter(COLOR_SHOW_FLAG);
		if(StringUtils.isNotEmpty(colorShowFlag) && "1".equals(colorShowFlag)){
			colorShow = true;
		}
		String sizeShowFlag = req.getParameter(SIZE_SHOW_FLAG);
		if(StringUtils.isNotEmpty(sizeShowFlag) && "1".equals(sizeShowFlag)){
			sizeShow = true;
		}
		
		List<FilterMountPO> tmpList = null;
		try{
			FilterMountPO po = new FilterMountPO();
			po.setCategoryId(Long.parseLong(curCateId));
			tmpList = filterMountService.queryListByCategoryId(po);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台服务出错", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		for(FilterMountPO p:tmpList){
			if(isLevel1 && p.getAttrId() == null){
				if(Constants.COLOR_SHOW_FLAG.equalsIgnoreCase(p.getAttrCode())){
					deleteColorShowId = p.getId();
					continue;
				}
				if(Constants.SIZE_SHOW_FLAG.equalsIgnoreCase(p.getAttrCode())){
					deleteSizeShowId = p.getId();
					continue;
				}
			}
		}
		List<FilterMountPO> dltList = new ArrayList<FilterMountPO>();
		if(!colorShow && deleteColorShowId!=null){
			FilterMountPO po1 = new FilterMountPO();
			po1.setId(deleteColorShowId);
			po1.setUpdateDate(new Date(System.currentTimeMillis()));
			po1.setUpdateUser(operUser);
			dltList.add(po1);
		}else if(colorShow && deleteColorShowId==null){
			fmList.add(this.buildSpecialPO(catePo, Constants.COLOR_SHOW_FLAG, operUser));
		}
		if(!sizeShow && deleteSizeShowId!=null){
			FilterMountPO po1 = new FilterMountPO();
			po1.setId(deleteSizeShowId);
			po1.setUpdateDate(new Date(System.currentTimeMillis()));
			po1.setUpdateUser(operUser);
			dltList.add(po1);
		}else if(sizeShow && deleteSizeShowId==null){
			fmList.add(this.buildSpecialPO(catePo, Constants.SIZE_SHOW_FLAG, operUser));
		}
		if("".equals(delIds)){
			delIds = null;
		}
		
		if(StringUtils.isEmpty(newAddFilterData) && dltList.size()==0 && StringUtils.isEmpty(delIds) && StringUtils.isEmpty(orderVals)){
			return AjaxObject.newErrorForward("没有可保存的数据", "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
		}
		
		String msg="";
		Response result = null;
		try{
			result = filterMountService.update(fmList,dltList,delIds,orderVals,operUser);
			msg = result.getMsg();
		}catch(Exception e){
			//此处暂时不写日志
			e.printStackTrace();
			msg = "保存数据失败";
		}
		return AjaxObject.newOkForward(msg, "sale-category/main?"+CURRENT_CATE_ID+"="+curCateId).toString();
	}
	
	private List<FilterMountPO> buildNewFMList(String newFMDataStr,CategoryPO catePo,String operUser){
		if(StringUtils.isEmpty(newFMDataStr)){
			return new ArrayList<FilterMountPO>();
		}
		//批量插入
		FilterMountPO po = null; 
		List<FilterMountPO> fmList = new ArrayList<FilterMountPO>();
		String[] fms = newFMDataStr.split("#");
		String[] tmps,tmp2;
		ProductAttrModel tmpPa;
		ProductAttrValueModel tmpAv;
		for(String fm:fms){
			tmps = fm.split(";");
			try {
				tmpPa = productAttrService.getProductAttrByCode(tmps[0]);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if(tmpPa == null){
				continue;
			}
			tmp2 = tmps[1].split(",");
			for(String t:tmp2){
				try {
					tmpAv = productAttrValueService.getProductAttrValueByCode(t);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				if(tmpAv == null){
					continue;
				}
				po = new FilterMountPO();
				po.setCategoryId(catePo.getId());
				po.setCategoryCode(catePo.getCategoryCode());
				po.setAttrCode(tmps[0]);
				po.setAttrId(tmpPa.getAttrId().intValue());
				po.setAttrValCode(t);
				po.setAttrValId(tmpAv.getAttrValueId().intValue());
				po.setOrderNumber(Integer.parseInt(tmps[2]));
				po.setDeleteFlag(0);
				
				po.setCreateUser(operUser);
				po.setCreateDate(new Date(System.currentTimeMillis()));
				po.setUpdateUser(po.getCreateUser());
				po.setUpdateDate(po.getCreateDate());
				fmList.add(po);
			}
		}
		return fmList;
	}

	public void setProductAttrGroupService(
			ProductAttrGroupService productAttrGroupService) {
		this.productAttrGroupService = productAttrGroupService;
	}

	public void setProductAttrService(ProductAttrService productAttrService) {
		this.productAttrService = productAttrService;
	}

	public void setProductAttrValueService(
			ProductAttrValueService productAttrValueService) {
		this.productAttrValueService = productAttrValueService;
	}

	public void setFilterMountService(FilterMountService filterMountService) {
		this.filterMountService = filterMountService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
