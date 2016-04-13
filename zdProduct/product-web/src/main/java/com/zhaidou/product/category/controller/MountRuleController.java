package com.zhaidou.product.category.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.Response;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.service.ProductBrandService;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.MountRulePO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.MountRuleService;
import com.zhaidou.product.category.util.Utils;

/**
 * 
 * @Title: MountRuleController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   运营分类自动挂载规则Controller
 *
 * @author lvshuding 
 *
 * @date 2015年4月9日 上午10:42:59 
 *
 * @version V1.0
 */
@Controller("mountRuleController")
@RequestMapping("/mount-rule")
public class MountRuleController extends BaseController{

	private final static String CURRENT_CATE_ID="currentCateId";
	
	
	@Autowired
	private MountRuleService mountRuleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	
	@RequestMapping("/main")
	public String main(HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr,Model model){
		/*
		 * 校验用户登录
		 * 
		 * 
		 */
		String catId = req.getParameter(CURRENT_CATE_ID);
		if(StringUtils.isBlank(catId)){
			return AjaxObject.newErrorForward("运营分类ID为空", "sale-category/main").toString();
		}
		model.addAttribute(CURRENT_CATE_ID, catId);
		
		MountRulePO po = new MountRulePO();
		po.setCategoryId(Long.parseLong(catId));
		po = mountRuleService.queryOne(po);
		if(po == null){
			model.addAttribute("addOper",true);
			po = new MountRulePO();
		}else{
			model.addAttribute("addOper",false);
		}
		
		if(StringUtils.isNotBlank(po.getCategoryCodeList())){
			String[] codes = po.getCategoryCodeList().split(";");
			List<CategoryPO> cList = new ArrayList<CategoryPO>();
			CategoryPO tmp;
			for(String c:codes){
				tmp = new CategoryPO();
				tmp.setCategoryCode(c);
				try{
					tmp = categoryService.queryOne(tmp, Category_Type.BASE_CATEGORY);
					if(tmp!=null){
						cList.add(tmp);
					}
				}catch(Exception e){
					e.printStackTrace();
					attr.addAttribute(CURRENT_CATE_ID, catId);
					return AjaxObject.newErrorForward("后台服务出现c异常", "sale-category/main").toString();
				}
			}
			if(cList.size()>0){
				po.setCateList(cList);
			}
		}
		
		if(StringUtils.isNotBlank(po.getBrandCodeList())){
			String[] codes = po.getBrandCodeList().split(";");
			List<ProductBrandModel> bList = new ArrayList<ProductBrandModel>();
			ProductBrandModel tmp;
			for(String c:codes){
				try{
					tmp = productBrandService.getEnableBrandByCode(c);
					if(tmp!=null){
						bList.add(tmp);
					}
				}catch(Exception e){
					e.printStackTrace();
					attr.addAttribute(CURRENT_CATE_ID, catId);
					return AjaxObject.newErrorForward("后台服务出现b异常", "sale-category/main").toString();
				}
			}
			if(bList.size()>0){
				po.setBrandList(bList);
			}
		}
		
		model.addAttribute("mountRulePo", po);
		
		return "category/mount_rule";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String toAdd(MountRulePO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr){
		/*
		 * 校验用户登录
		 * 
		 * 
		 */
		String userName = Utils.getUserNameFromRequest(req);
		
		//这里要获取登录用户的用户名
		po.setCreateUser(userName);
		po.setCreateDate(new Date(System.currentTimeMillis()));
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		po.setDeleteFlag(0);
		Response result = mountRuleService.insert(po);
		
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main?"+CURRENT_CATE_ID+"="+po.getCategoryId()).toString();
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String toDelete(MountRulePO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr){
		
		if(po.getId() == null){
			return AjaxObject.newErrorForward("", "sale-category/main?"+CURRENT_CATE_ID+"="+po.getCategoryId()).toString();
		}
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		
		po.setDeleteFlag(1);
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Long cateId = po.getCategoryId();
		po.setCategoryId(null);
		
		Response result = mountRuleService.delete(po);
				
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main?"+CURRENT_CATE_ID+"="+cateId).toString();
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String toUpdate(MountRulePO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr){
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = mountRuleService.update(po);
		
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main?"+CURRENT_CATE_ID+"="+po.getCategoryId()).toString();
	}


}
