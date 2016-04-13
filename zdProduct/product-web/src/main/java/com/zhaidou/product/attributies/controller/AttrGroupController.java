package com.zhaidou.product.attributies.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.numberrule.NumberingRuleUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.framework.util.string.StringUtil;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.ProductAttrModel;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.attributies.service.ProductAttrService;
import com.zhaidou.product.attributies.service.ProductAttrValueService;
import com.zhaidou.product.attributies.service.RelationCateGroupService;
import com.zhaidou.product.attributies.service.RelationGroupAttrService;
import com.zhaidou.product.attributies.util.Utils;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;

/**
 * 
 * @author hu.tongqing
 *
 */
@Controller
@RequestMapping("/attrgroup")
public class AttrGroupController
{
	private static Logger logger = Logger.getLogger(AttrGroupController.class);    

	
	/**页面参数*/
	private static String ATTRGROUP_LIST = "attrgroup/attrgroup_list"; //列表页面
	private static String ATTRGROUP_ADD = "attrgroup/attrgroup_add"; //添加页面 
	private static String ATTRGROUP_UPDATE = "attrgroup/attrgroup_update"; //更新页面 
	private static String ATTRGROUP_RELATIONATTR = "attrgroup/attrgroup_relationattr_list"; //已关联属性页面 
	private static String ATTRGROUP_NOBUND_RELATIONATTR = "attrgroup/attrgroup_nobundattr_list"; //未关联属性页面 
	private static String ATTRVALUE_LIST = "attrgroup/attr_attrvaluelist"; //更新页面 
	@Resource
	private ProductAttrGroupService productAttrGroupService;
	@Resource
	private ProductAttrService productAttrService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private RelationGroupAttrService relationGroupAttrService ;
	@Resource
	private RelationCateGroupService relationCateGroupService;
	@Resource
	private ProductAttrValueService valueService;
	 /**
     * 获取数据分页列表
     * */
    @RequestMapping(value="/list",method = { RequestMethod.GET, RequestMethod.POST })
    public String List(Page page,ProductAttrGroupModel productAttrGroupModel,Map<String,Object> map)
    {
    	
    	
        List<ProductAttrGroupModel> attrGroupList = null;
        
        if(productAttrGroupModel.getCreateTimes()!=null && !"".equals(productAttrGroupModel.getCreateTimes())){
            productAttrGroupModel.setCreateTime(DatetimeUtil.stringToDate(productAttrGroupModel.getCreateTimes() + " 00:00:01").getTime());
        }
        if(productAttrGroupModel.getEndTime1()!=null && !"".equals(productAttrGroupModel.getEndTime1())){
            map.put("endTime1",productAttrGroupModel.getEndTime1());
            productAttrGroupModel.setEndTime1(String.valueOf(DatetimeUtil.stringToDate(productAttrGroupModel.getEndTime1() + " 23:59:59").getTime()));
        }
        if(productAttrGroupModel.getEndTime2()!=null && !"".equals(productAttrGroupModel.getEndTime2())){
            map.put("endTime2",productAttrGroupModel.getEndTime2());
            productAttrGroupModel.setEndTime2(String.valueOf(DatetimeUtil.stringToDate(productAttrGroupModel.getEndTime2() + " 23:59:59").getTime()));
        }
        if(productAttrGroupModel.getUpdateTimes()!=null && !"".equals(productAttrGroupModel.getUpdateTimes())){
            productAttrGroupModel.setUpdateTime(DatetimeUtil.stringToDate(productAttrGroupModel.getUpdateTimes() + " 00:00:01").getTime());
        }
        
        try
        {
        	attrGroupList = productAttrGroupService.getProductAttrGroupListPage(productAttrGroupModel, page);
        } 
        catch (Exception e)
        {
        	logger.info("获取属性组分页列表异常=="+e.getMessage());
        }
        
        map.put("attrGroupList", attrGroupList);
        map.put("page", page);
        map.put("productAttrGroupModel", productAttrGroupModel);
        return ATTRGROUP_LIST;
    }
    
    
    /**
     * 转至添加页面
     */
    @RequestMapping(value="/to_add",method = { RequestMethod.GET, RequestMethod.POST })
    public String toAddProductAttrGroup()
    {
    	//List<CategoryPO> groupCateList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
    	
    	//map.put("groupCateList", groupCateList);
        
    	return ATTRGROUP_ADD;
    }
    
    /**
     * 添加操作
     * @return String
     */
    @RequestMapping(value="/add",method={ RequestMethod.POST })
    @ResponseBody
    public String addProductAttrGroup(HttpServletRequest req,ProductAttrGroupModel productAttrGroupModel,Long groupCateId,HttpServletResponse resp){
        
    	@SuppressWarnings("unchecked")
		Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    	
    	//从后台补充其他参数
    	productAttrGroupModel.setCreator(user.get("userName"));
    	productAttrGroupModel.setCreateTime(new Date().getTime());
    	productAttrGroupModel.setUpdateUser(user.get("userName"));
    	productAttrGroupModel.setUpdateTime(new Date().getTime());
        String strReturn = null;
        try {
        	Long maxId = productAttrGroupService.getMaxId();
        	productAttrGroupModel.setGroupCode(NumberingRuleUtil.newBaseCode(ProductAttrGroupModel.PRE_ATTR_CODE, ""+maxId,"", false));
        	productAttrGroupService.addProductAttrGroup(productAttrGroupModel);
        	strReturn = AjaxObject.newOk("添加成功").toString();
        } catch (Exception e) {
        	logger.info("添加属性组异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("添加属性组异常信息=="+e.getMessage()).toString();
        }
       
        return strReturn;
    }
    
    /**
     * 转至更新页面
     */
    @RequestMapping(value="/to_update/{id}",method = { RequestMethod.GET, RequestMethod.POST })
    public String toUpdateProductBrand(@PathVariable Long id,Map<String,Object> map)
    {
    	ProductAttrGroupModel productAttrGroupModel =null;
	   
    	try 
	    {
    		productAttrGroupModel = productAttrGroupService.getProductAttrGroupById(id);
	    } 
    	catch (Exception e) 
    	{
	         e.printStackTrace();
	         logger.info("获取属性组操作异常信息=="+e.getMessage());
	    }
    	
	    map.put("attrGroupModel", productAttrGroupModel);
	    
	    return ATTRGROUP_UPDATE;
    }
    
    /**
     * 更新操作
     * @return String
     */
    @RequestMapping(value="/update",method={  RequestMethod.POST })
    @ResponseBody
    public String updateProductAttrGroup(HttpServletRequest req,ProductAttrGroupModel productAttrGroupModel,HttpServletResponse resp)
    {
        
    	@SuppressWarnings("unchecked")
		Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    	
    	productAttrGroupModel.setUpdateTime(new Date().getTime());
    	productAttrGroupModel.setUpdateUser(user.get("userName"));
    	productAttrGroupModel.setCreateBy(Long.parseLong(user.get("userId")));
        String strReturn = null;
        
        try 
        {
        	productAttrGroupService.updateProductAttrGroup(productAttrGroupModel);
        	strReturn = AjaxObject.newOk("更新成功").toString();
        } 
        catch (Exception e)
        {
        	logger.info("更新属性组异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("更新属性组异常信息=="+e.getMessage()).toString();
        }
       
        return strReturn;
    }
    
    /**
     * 删除操作
     * @return String
     */
	@RequestMapping(value="/delete/{id}",method={ RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String deleteProductAttrGroup(@PathVariable Long id,Map<String,Object> map)
    {
        
        String strReturn = null;
        try 
        {
        	productAttrGroupService.deleteById(id);
        	AjaxObject returnAjax = AjaxObject.newOkCallbackType("删除成功");
//        	returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
//    		returnAjax.setForwardUrl("${contextPath}/attrgroup/list");
    		strReturn = returnAjax.toString();
        } 
        catch (Exception e)
        {
        	logger.info("删除属性组异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("更新属性组异常信息=="+e.getMessage()).toString();
        }
        
        return strReturn;
    }
    
    /**
     * 到已关联属性页面
     * @return
     */
    @RequestMapping(value="/to_relationattr/{id}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String to_relationattr(@PathVariable Long id,Page page,ProductAttrModel productAttrModel, Map<String,Object> map)
    {
    	List<ProductAttrModel> productAttrModelList = null;
    	try 
    	{
    		productAttrModel.setAttrId(id);
			productAttrModelList = productAttrService.queryGroupRelationAttrList(productAttrModel, page);
		} 
    	catch (Exception e) 
    	{
			logger.info("获取属性项列表异常信息=="+e.getMessage());
			e.printStackTrace();
		}
    	map.put("productAttrModelList", productAttrModelList);
    	map.put("attrGroupId", id);
    	map.put("productAttrModel", productAttrModel);
    	map.put("target", "dialog");
    	map.put("onchange", "dialogPageBreak({numPerPage:this.value})");
    	return ATTRGROUP_RELATIONATTR;
    }
    
    @RequestMapping(value="/to_nobundList/{groupId}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String to_notBundAttrList(@PathVariable Long groupId,Page page,ProductAttrModel productAttrModel, Map<String,Object> map)
    {
    	List<ProductAttrModel> productAttrModelList = null;
    	try 
    	{
    		productAttrModel.setAttrId(groupId);;
			productAttrModelList = productAttrService.queryGroupRelationAttrNotBundList(productAttrModel, page);
		} 
    	catch (Exception e) 
    	{
			logger.info("获取属性组未关联的属性项错误信息=="+e.getMessage());
			e.printStackTrace();
		}
    	map.put("productAttrModelList", productAttrModelList);
    	map.put("attrGroupId", groupId);
    	map.put("productAttrModel", productAttrModel);
    	map.put("target", "dialog");
    	map.put("onchange", "dialogPageBreak({numPerPage:this.value})");
    	return ATTRGROUP_NOBUND_RELATIONATTR;
    }
    
    /**
     * 增加属性组与属性项关联
     * @param groupId
     * @param attrIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addRelation/{groupId}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String addRelation(@PathVariable Long groupId, String addIds)
    {
		String returnStr = null;
    	try 
    	{
			
			String[] ids = addIds.split(",");
			
			relationGroupAttrService.addRelationGroupAttr(groupId, ids);
			AjaxObject returnAjax = AjaxObject.newOk("关联成功");
    		returnStr = returnAjax.toString();
		}
    	catch (Exception e) 
    	{
    		returnStr = AjaxObject.newError("删除失败").toString();
			e.printStackTrace();
		}
    	
    	return returnStr;
    }
    
    /**
     * 取消属性组跟属性项的关联
     * @param groupId
     * @param attrIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delRelation/{groupId}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String deleteRelation(@PathVariable Long groupId, String attrIds)
    {
    	
    	if(StringUtil.isEmpty( attrIds ))
    	{
    		return AjaxObject.newError("请选择属性项").toString();
    	}
    	
    	String returnStr = null;
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	try 
    	{
    		map.put("groupId", groupId);
			String[] ids = attrIds.split(",");
			List<String> list = Arrays.asList(ids);
			map.put("attrIds", list);
			relationGroupAttrService.deleteByAttrIdAndGroupId(map);
			AjaxObject returnAjax = AjaxObject.newOk("取消关联成功");
    		returnStr = returnAjax.toString();
		}
    	catch (Exception e) 
    	{
    		returnStr = AjaxObject.newError("删除失败").toString();
			e.printStackTrace();
		}
    	
    	return returnStr;
    }
    
    @RequestMapping(value="/to_relationattrPannel/{groupId}",method={RequestMethod.POST,RequestMethod.GET} )
    public String to_relationattrPannel(@PathVariable Long groupId,Integer currentIndex,String flag,String flagt,Page page,ProductAttrModel productAttrModel,Map<String,Object> map)
    {
    	//查询的属性列表
    	List<ProductAttrModel> productAttrModelList = null;
    	
    	
    	try 
    	{
    		productAttrModel.setAttrId(groupId);
    		

			if(currentIndex == null || currentIndex == 0)
	    	{
				productAttrModelList = productAttrService.queryGroupRelationAttrList(productAttrModel, page);
	    		currentIndex = 0;
	    	}
	    	else if(currentIndex == 1)
	    	{
	    		productAttrModelList = productAttrService.queryGroupRelationAttrNotBundList(productAttrModel, page);
	    	}
	    	else
	    	{
	    		
	    	}
		} 
    	catch (Exception e) 
    	{
			logger.info("获取属性项列表异常信息=="+e.getMessage());
			e.printStackTrace();
		}
    	
    	map.put("productAttrModelList", productAttrModelList);
    	map.put("groupId", groupId);
    	map.put("currentIndex", currentIndex);
    	
    	
    	map.put("target", "dialog");
    	map.put("onchange", "dialogPageBreak({numPerPage:this.value})");
//    	map.put("action", "attrgroup/to_relationattrPannel/"+ groupId);
    	return "attrgroup/attrgroup_relationattr_dialog";
    }
    
    
    @RequestMapping(value="/to_listAttrValue/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String toListAttrValue(@PathVariable Long id , Map<String,Object> map)
    {
    	List<ProductAttrValueModel> valueList = new ArrayList<ProductAttrValueModel>();
    	ProductAttrModel attrModel = null;
    	try 
    	{
    		attrModel = productAttrService.getProductAttrById(id);
    		Page page = new Page();
    		page.setNumPerPage(1000);
			valueList = valueService.getProductAttrValueListPageByAttrCode(attrModel.getAttrCode(), page);
		} 
    	catch (Exception e) 
    	{
			logger.error("查询属性值列表异常=" + e.getMessage());
			e.printStackTrace();
		}
    	//map.put("page", page);
    	map.put("valueList", valueList);
    	map.put("attrId", id);
    	map.put("isDesign", attrModel.getAttrDesign());
    	return ATTRVALUE_LIST;
    }
    /*********************关联分类***********************/
    /**
     * 到已关联属性页面
     * @return
     */
    @RequestMapping(value="/to_relationcate/{id}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String to_cateTree(@PathVariable Long id, Map<String,Object> map)
    {
    	List<CategoryPO> categoryModelList = null;
    	try 
    	{
    		categoryModelList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
    		
    		List<Long> ids = getRelationGroupCate(id);
    		
    		map.put("orgTreeStr", Utils.buildJsonFormTree(categoryModelList, ids));
    		

		} 
    	catch (Exception e) 
    	{
			logger.info("获取属性项列表异常信息=="+e.getMessage());
			e.printStackTrace();
		}
    	map.put("attrGroupId", id);
    	
    	return "attrgroup/attrgroup_catetree";
    }
    
    /**
     * 提交属性组关联的分类树
     * @param id
     * @param checkIdStr
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/saveGroupcate/{id}",method = {RequestMethod.POST,RequestMethod.GET} )
    public String saveGroupcate(@PathVariable Long id,String checkIdStr, Map<String,Object> map)
    {
    	String returnStr = null;
    	List<CategoryPO> categoryModelList = null;
    	try 
    	{
    		List<Long> checkIds = getReceivedRelationCateId(checkIdStr);
    		List<Long> preIds = getRelationGroupCate(id);
    		
    		List<Long> addCateIds = getSubList(checkIds, preIds);
    		List<Long> deleteCateIds = getSubList(preIds,checkIds);
    		
    		relationCateGroupService.saveRelationCate(id,addCateIds,deleteCateIds);
    		
    		map.put("attrgroupId", id);
    		
    		categoryModelList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
    		
    		List<Long> ids = getRelationGroupCate(id);
    		
    		returnStr = Utils.buildJsonFormTree(categoryModelList, ids);
    		
		}
    	catch (Exception e) 
    	{
    		AjaxObject ajaxObj = AjaxObject.newError("提交失败");
			logger.info("失败="+e.getMessage());
			returnStr = ajaxObj.toString();
		}
    	
    	return returnStr;
    }


    /**
     *  得到A集合减B集合后的结果
     */
	private List<Long> getSubList(List<Long> checkIds, List<Long> preIds) {
		List<Long> result = new ArrayList<Long>();
		if(checkIds != null)
		{
			for(int i = 0 ; i < checkIds.size() ; i++)
			{
				if(!preIds.contains(checkIds.get(i)))
				{
					result.add(checkIds.get(i));
				}
			}
		}
		return result;
	}


    /**
     * 解析页面传来的选中的分类id
     * @param checkIdStr
     * @return 
     */
	private List<Long> getReceivedRelationCateId(String checkIdStr) {
		
		List<Long> receiveIds = new ArrayList<Long>();

		if(checkIdStr.equals("nodate"))
		{
			return receiveIds;
		}
		String[] checkIds = null;
    	if(checkIdStr != null && checkIdStr.length() > 0)
    	{
    		checkIds = checkIdStr.split(",");
    	}
    	for(String receiveId : checkIds)
    	{
    		receiveIds.add(Long.parseLong(receiveId));
    	}
		return receiveIds;
	}


	/**
	 * 获取上一次所有关联的分类ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private List<Long> getRelationGroupCate(Long id) throws Exception 
	{
		List<CategoryPO> pos = relationCateGroupService.getGroupRelateCateByGroupId(id);
		List<Long> ids = new ArrayList<Long>();
		for(CategoryPO po : pos)
		{
			ids.add(po.getId());
		}
		
		return ids;
	}
}
