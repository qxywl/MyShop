package com.zhaidou.product.attributies.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.numberrule.NumberingRuleUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.model.ProductAttrModel;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.attributies.service.ProductAttrService;
import com.zhaidou.product.attributies.service.ProductAttrValueService;
import com.zhaidou.product.attributies.service.RelationGroupAttrService;

/**
 * 
 * @author hu.tongqing
 *
 */
@Controller
@RequestMapping("/attr")
public class AttrController 
{
	
	private static Logger logger = Logger.getLogger(AttrGroupController.class);    
	
	
	
	/**页面参数*/
	private static String ATTR_LIST = "attr/attr_list"; //列表页面
	private static String ATTR_ADD = "attr/attr_add"; //添加页面 
	private static String ATTR_UPDATE = "attr/attr_update"; //更新页面 
	private static String ATTR_UPDATEVALUE = "attr/attr_attrvalueupdate"; //更新页面 
	private static String ATTR_ADDATTRVALUE = "attr/attr_addattrvalue"; //更新页面 
	private static String ATTRVALUE_LIST = "attr/attr_attrvaluelist"; //更新页面 
	
	@Resource
	private ProductAttrService productAttrService;
	@Resource
	private ProductAttrGroupService attrGroupService; 
	@Resource
	private RelationGroupAttrService relationGroupAttrService;
	@Resource
	private ProductAttrValueService valueService;
	 /**
     * 获取数据分页列表
     * */
    @RequestMapping(value="/list",method = { RequestMethod.GET, RequestMethod.POST })
    public String List(Page page,ProductAttrModel productAttrModel,Map<String,Object> map)
    {
        List<ProductAttrModel> attrList = null;
       if(productAttrModel.getCreateTimes()!=null && !"".equals(productAttrModel.getCreateTimes())){
           productAttrModel.setCreateTime(DatetimeUtil.stringToDate(productAttrModel.getCreateTimes() + " 00:00:01").getTime());
       }
       if(productAttrModel.getEndTime1()!=null && !"".equals(productAttrModel.getEndTime1())){
           map.put("endTime1",productAttrModel.getEndTime1());
           productAttrModel.setEndTime1(String.valueOf(DatetimeUtil.stringToDate(productAttrModel.getEndTime1() + " 23:59:59").getTime()));
       }
       if(productAttrModel.getEndTime2()!=null && !"".equals(productAttrModel.getEndTime2())){
           map.put("endTime2",productAttrModel.getEndTime2());
           productAttrModel.setEndTime2(String.valueOf(DatetimeUtil.stringToDate(productAttrModel.getEndTime2() + " 23:59:59").getTime()));
       }
       if(productAttrModel.getUpdateTimes()!=null && !"".equals(productAttrModel.getUpdateTimes())){
           productAttrModel.setUpdateTime(DatetimeUtil.stringToDate(productAttrModel.getUpdateTimes() + " 00:00:01").getTime());
       }
        try
        {
        	attrList = productAttrService.getProductAttrListPage(productAttrModel, page);
        } 
        catch (Exception e)
        {
        	logger.info("获取属性项分页列表异常=="+e.getMessage());
        }
        
        map.put("attrList", attrList);
        map.put("page", page);
        map.put("productAttrModel", productAttrModel);
        return ATTR_LIST;
    }
    
    /**
     * 转至添加页面
     */
    @RequestMapping(value="/to_add",method = { RequestMethod.GET, RequestMethod.POST })
    public String toAddProductAttrGroup()
    {
    	
        return ATTR_ADD;
    }
    
    /**
     * 添加操作
     * @return String
     */
    @RequestMapping(value="/add",method={ RequestMethod.POST })
    @ResponseBody
    public String addProductAttr(ProductAttrModel productAttrModel,HttpServletResponse resp,HttpServletRequest req)
    {
    	@SuppressWarnings("unchecked")
		Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    	productAttrModel.setCreator(user.get("userName"));
    	productAttrModel.setCreateTime(new Date().getTime());
    	productAttrModel.setUpdateUser(user.get("userName"));
    	productAttrModel.setUpdateTime(new Date().getTime());
    	productAttrModel.setAttrStatus(1);
    	productAttrModel.setCreateBy(Long.parseLong(user.get("userId")));
    	if(productAttrModel.getAttrEname() == null || "".equals(productAttrModel.getAttrEname()))
    	{
    		productAttrModel.setAttrEname(productAttrModel.getAttrName());
    	}
        String strReturn = null;
        try 
        {
        	productAttrModel.setAttrCode(NumberingRuleUtil.newBaseCode(ProductAttrModel.PRE_ATTR_CODE,productAttrService.getMaxId()+"","",false));
        	//保存属性
        	productAttrService.addProductAttr(productAttrModel);
        	strReturn = AjaxObject.newOk("添加成功").toString();
        }
        catch (Exception e) 
        {
        	logger.info("添加属性组异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("添加属性组异常信息=="+e.getMessage()).toString();
        }
       
        return strReturn;
    }
    
    /**
     * 删除操作
     * @return String
     */
	@RequestMapping(value="/delete",method={ RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String deleteProductAttr(Map<String,Object> map,String ids)
    {
        
        String strReturn = null;
        try 
        {
        	String[] attrIds = ids.split(",");
        	Boolean flag = productAttrService.deleteByAttrIds(attrIds);
        	AjaxObject returnAjax = null;
        	if(flag)
        	{
        		returnAjax = AjaxObject.newOk("删除成功");
        	}
        	else
        	{
        		returnAjax = AjaxObject.newOk("部分属性因挂载了属性值而删除失败");
        	}
        	returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
    		returnAjax.setForwardUrl("attr/list");
    		strReturn = returnAjax.toString();
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        	logger.info("删除属性异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("更新属性异常信息=="+e.getMessage()).toString();
        }
        
        return strReturn;
    }
    
    
    /**
     * 转至修改页面
     */
    @RequestMapping(value="/to_update/{id}",method = { RequestMethod.GET, RequestMethod.POST })
    public String toUpdateProductAttr(@PathVariable Long id,ProductAttrModel productAttrModel,Map<String,Object> map)
    {
    	
    	try 
    	{
    		productAttrModel = productAttrService.getProductAttrById(id);
    	} 
    	catch (Exception e) 
    	{
    		logger.info("获取属性项分页列表异常=="+e.getMessage());
    	}
    	map.put("attrModel", productAttrModel);
    	
    	return ATTR_UPDATE;
    }
    /**
     * 转至修改页面
     */
    @RequestMapping(value="/to_updateattrvalue/{id}",method = { RequestMethod.GET, RequestMethod.POST })
    public String to_updateattrvalue(@PathVariable Long id,Integer attrId,ProductAttrValueModel productAttrValueModel,Map<String,Object> map)
    {
    	
    	try 
    	{
    		productAttrValueModel = valueService.getProductAttrValueById(id);
    	} 
    	catch (Exception e) 
    	{
    		logger.info("获取属性项分页列表异常=="+e.getMessage());
    	}
    	map.put("attrValueModel", productAttrValueModel);
    	map.put("attrId", attrId);
    	return ATTR_UPDATEVALUE;
    }
    
    /**
     * 修改属性
     */
    @RequestMapping(value="/updateattr",method = { RequestMethod.POST })
    @ResponseBody
    public String updateProductAttr(ProductAttrModel productAttrModel,HttpServletRequest req)
    {
    	 String returnStr = null;
    	 try 
    	 {
    		 @SuppressWarnings("unchecked")
			Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    		 productAttrModel.setUpdateTime(new Date().getTime());
    		 productAttrModel.setUpdateUser(user.get("userName"));
    		 productAttrModel.setUpdateBy(Long.parseLong(user.get("userId")));
			
    		 productAttrService.updateProductAttr(productAttrModel);
			returnStr = AjaxObject.newOk("更新成功").toString();
    	 } 
    	 catch (Exception e) 
    	 {
    		 returnStr = AjaxObject.newError("更新失败=" + e.getMessage()).toString();
			 logger.info("修改属性操作异常=="+e.getMessage());
		}
    	 return returnStr;
    }
    /**
     * 修改属性值 
     */
    @RequestMapping(value="/updateattrvalue",method = { RequestMethod.POST })
    @ResponseBody
    public String updateattrvalue(ProductAttrValueModel productValueAttrModel,HttpServletRequest req)
    {
    	String returnStr = null;
    	try 
    	{
    		@SuppressWarnings("unchecked")
			Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    		productValueAttrModel.setUpdateTime(new Date().getTime());
    		productValueAttrModel.setUpdateUser(user.get("userName"));
    		valueService.updateProductAttrValue(productValueAttrModel);
    		AjaxObject ajaxObj = AjaxObject.newOk("更新成功");
    		ajaxObj.setCallbackType(AjaxObject.CALLBACK_TYPE_CLOSE_CURRENT);
    		returnStr = ajaxObj.toString();
    	} 
    	catch (Exception e) 
    	{
    		returnStr = AjaxObject.newError("更新失败=" + e.getMessage()).toString();
    		logger.info("修改属性操作异常=="+e.getMessage());
    	}
    	return returnStr;
    }
    
    @RequestMapping(value="/to_addAttrValue/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String toAddAttrValue(@PathVariable Long id,Map<String,Object> map)
    {
    	try 
    	{
			ProductAttrModel attrModel = productAttrService.getProductAttrById(id);
			map.put("isDesign", attrModel.getAttrDesign());
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	map.put("attrId", id);
    	return ATTR_ADDATTRVALUE;
    }
    
    
    /**
     * 添加属性值
     * @param id
     * @param list
     * @return
     */
    @ResponseBody 
    @RequestMapping(value="/addAttrValue/{id}",method = {RequestMethod.POST})
    public String addAttrValue(@PathVariable Long id,ProductAttrModel list,HttpServletRequest req)
    {
    	
    	String returnStr = null;
    	try {
    		ProductAttrModel attrModel = productAttrService.getProductAttrById(id);
    		List<ProductAttrValueModel> valueList = list.getAttrValueList();
    		List<ProductAttrValueModel> addList  = new ArrayList<ProductAttrValueModel>();
    		
    		@SuppressWarnings("unchecked")
			Map<String,String> user =  (Map<String, String>) req.getSession().getAttribute("user");
    		
    		Long maxId = valueService.getMaxId();
    		ProductAttrValueModel valueModel = null;
    		for(int i = 0 ; i < valueList.size() ; i++)
    		{
    			valueModel = valueList.get(i);
    			if(!StringUtils.isEmpty(valueModel.getAttrValue())){ //前端传过来的valueList 有空值，所以要过滤
    				valueModel.setAttrCode(attrModel.getAttrCode());
        			valueModel.setAttrId(attrModel.getAttrId());
        			valueModel.setCreator(user.get("userName"));
        			valueModel.setUpdateUser(user.get("userName"));
        			valueModel.setUpdateTime(new Date().getTime());
        			valueModel.setCreateTime(new Date().getTime());
        			valueModel.setAttrValueCode(NumberingRuleUtil.newBaseCode(ProductAttrValueModel.PRE_ATTRVALUE_CODE,"" + maxId, "",false));
        			valueModel.setAttrValueStatus(1);
//        			valueList.set(i, valueModel);
        			addList.add(valueModel);
        			maxId++;
    			}
    			
    			
    		}
    		
    		valueService.addProductAttrValue(addList);
    		
    		AjaxObject ajaxObj = AjaxObject.newOk("添加成功");
    		returnStr = ajaxObj.toString();
		} catch (Exception e) {
			logger.info("添加属性值失败");
			returnStr = AjaxObject.newError("添加属性值失败").toString();
			logger.error( "添加属性值失败" + e.getMessage() );
		}
    	return returnStr;
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
    	
    /**
     * 批量删除属性值
     * @param attrValueIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteAttrValue",method={RequestMethod.POST})
    public String deleteAttrValue(String attrValueIds)
    {
    	String returnStr = null;
    	try 
    	{
			
			String[] ids = attrValueIds.split(",");
			List<Long> list = new ArrayList<Long>();
			
			for(int index = 0 ; index < ids.length ; index++)
			{
				list.add(Long.valueOf(ids[index]));
			}
			
			valueService.deleteByAttrValueIds(list);
			AjaxObject returnAjax = AjaxObject.newOk("删除成功");
    		
    		returnStr = returnAjax.toString();
		}
    	catch (Exception e) 
    	{
    		returnStr = AjaxObject.newError("删除失败").toString();
			e.printStackTrace();
		}
    	
    	
    	return returnStr;
    }
    
    
    @RequestMapping(value = "/ajaxValidateFieldUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String ajaxValidateFieldAttr(String fieldId, String fieldValue) {        
        Boolean isExistInSupplier=Boolean.FALSE;
        //Boolean isExistInSelfRegisteredSupplier=Boolean.FALSE;
        
        try 
        {
			isExistInSupplier= productAttrService.isExistThisName(fieldValue.trim());
		}
        catch( Exception e ) 
        {
        	logger.error( "ajax校验属性名称唯一性失败" );
			e.printStackTrace();
		}
     
        
        if (!isExistInSupplier) {           
          
            logger.debug("[\"" + fieldId + "\",true,\"此名称可以使用\"]");
            return "[\"" + fieldId + "\",true,\"此名称可以使用\"]";
           
        } else {           
            logger.debug("[\""+fieldId+"\",false,\"此名称已被其他人使用\"]");
            return "[\""+fieldId+"\",false,\"此名称已被其他人使用\"]";

        }

    }
}
