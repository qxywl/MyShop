package com.zhaidou.product.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.Constants;
import com.zhaidou.common.util.DateUtil;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.framework.util.string.StringUtil;
import com.zhaidou.product.Util.UserHelper;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.info.model.ProductJobTimeModel;
import com.zhaidou.product.info.service.ProductJobTimeService;
import com.zhaidou.product.info.util.CSVUtil;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.model.ProductStockPO;
import com.zhaidou.product.stock.po.request.GetStockListPageRequestPo;
import com.zhaidou.product.stock.po.request.ImportStockRequestPo;
import com.zhaidou.product.stock.po.response.GetStockListPageResponsePo;
import com.zhaidou.product.stock.po.response.ImportStockResponsePo;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value="info/stock")
public class ProductStockController{
    
	private static Logger logger = Logger.getLogger(ProductStockController.class);    
	
    private static String STOCKLIST = "info/stock/stock_list";
    
    private static String TOEXPORTEXCEL = "info/stock/export";

    private static String TOIMPORTEXCEL = "info/stock/importStock";
    
    @SuppressWarnings("unused")
	private static String xmlConfig = "/config/excel/emptyloopbreak.xml";
    
    /**导入新增*/
	String [] addUploadTitle ={"sku编码","商品编号","品牌编号","分类编号","对接库存数"};
	String [] addUploadField ={"skuCode","productCode","brandCode","categoryCode","virtualStock"};

	 /**导入修改*/
	String [] changeUploadTitle ={"sku编码","库存类型","库存数","库存百分比"};
	String [] changeUploadField ={"skuCode","stockType","virtualStock","stockPercent"};
	

	@Resource
	private ProductJobTimeService productJobTimeService;
	
    @Resource
    private ProductStockManager productStockManager;
    

    @RequestMapping(value="/list",method={RequestMethod.GET, RequestMethod.POST })
    public String getPageList(ProductStockPO productStockPo,Page page,Map<String,Object> map){
        
    	RequestObject requestObj = new RequestObject();
    	GetStockListPageRequestPo requestPo = new GetStockListPageRequestPo();
    	requestPo.setNumPerPage( page.getNumPerPage() );
    	requestPo.setPageNum( page.getPlainPageNum() );
    	if(page.getTotalCount() != 0)
    	{
    		requestPo.setTotalCount( page.getTotalCount() );
    	}
    	
    	requestPo.setPo( productStockPo );
    	requestObj.setRequestParams( requestPo );
    	
        try
        {
			ResponseObject responseObj = productStockManager.getStockListPage( requestObj );
			if(responseObj.getCode() != 0)
			{
				throw new Exception("调用库存【getStockListPage】接口失败");
			}
			
			GetStockListPageResponsePo responsePo = ( GetStockListPageResponsePo )responseObj.getData();
			List<ProductStockPO> list = responsePo.getPos();
			page.setTotalCount( responsePo.getTotalCount() );
			map.put("list", list);
			map.put( "page", page );
			map.put( "productStockPo", productStockPo );
		} 
        catch( Exception e )
        {
			e.printStackTrace();
			logger.error( e.getMessage(), e);
		}
        return STOCKLIST;
    }
    
    @RequestMapping(value="/to_exportExcel",method={RequestMethod.GET, RequestMethod.POST })
    public String to_exportExcel( ProductStockPO productStockPo,Map<String,Object> map,HttpServletRequest request,Integer totalCount)
    {
    	
    	map.put( "productStockPo", productStockPo );
    	map.put( "totalCount", totalCount%1000 > 0 ? (totalCount/1000+1) : totalCount/1000 );
    	return TOEXPORTEXCEL;
    }
    
    /**
     * 根据excel模版导出供应商详情列表 config/excel/supplierTemplate.xls 导出 TODO Add comments
     * here.
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/exportExcel", method = { RequestMethod.POST, RequestMethod.GET })
    public String exportProductExcel(HttpServletResponse response, ProductStockPO productStockPo, HttpServletRequest req,Integer pageNum) 
    {
        List<ProductStockPO> list = null;
        try {
        	
        	productStockPo.setBrandCode( productStockPo.getBrandCode().trim() );
        	productStockPo.setProductCode( productStockPo.getProductCode().trim() );
        	productStockPo.setCategoryCode( productStockPo.getCategoryCode().trim() );
        	productStockPo.setSkuCode( productStockPo.getSkuCode().trim() );
        	productStockPo.setProductName( productStockPo.getProductName().trim() );
        	productStockPo.setBrandName( productStockPo.getBrandName().trim() );
        	productStockPo.setCategoryName( productStockPo.getCategoryName().trim() );
        	
        	RequestObject requestObj = new RequestObject();
        	GetStockListPageRequestPo requestPo = new GetStockListPageRequestPo();
        	requestPo.setNumPerPage(1000);
        	requestPo.setPageNum(pageNum );
        	requestPo.setTotalCount( 100L );
        	requestPo.setPo( productStockPo );
        	requestObj.setRequestParams( requestPo );
        	ResponseObject responseObj = productStockManager.getStockListPage( requestObj );
			if(responseObj.getCode() != 0)
			{
				throw new Exception("调用库存【getStockListPage】接口失败");
			}
			
			GetStockListPageResponsePo responsePo = ( GetStockListPageResponsePo )responseObj.getData();
			list = responsePo.getPos();
            CSVUtil.exportCSV( ProductStockPO.class, list, response );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return null;
    }
    
   
    @RequestMapping(value = "/to_importStock", method = { RequestMethod.POST, RequestMethod.GET })
    public String to_importStock()
    {
    	return TOIMPORTEXCEL;
    }
    
    /**
     * 
     * @return
     */
    @SuppressWarnings( "unused" )
	@RequestMapping(value = "/importExcel", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String importStock(Integer type,HttpServletRequest request,
    		HttpServletResponse resp,@RequestParam  MultipartFile file)
    {
    	AjaxObject returnAjax = null;
   	 
        String filename = file.getOriginalFilename();
        byte[] buf = null;
       
        try
        {
			
        	buf = file.getBytes();
			logger.info(filename + "==" + buf.length);
			if (buf == null) 
			{
	             returnAjax = AjaxObject.newError("文件为空");
	        }
			else
			{	 RequestObject reqObj =new RequestObject();
				 ImportStockRequestPo respPo = new ImportStockRequestPo();
				 
				 @SuppressWarnings( "unchecked" )
				 Map<String,String> user =  (Map<String, String>) request.getSession().getAttribute("user");
				 List<ProductStockPO> pos = null;
				 if(type.equals( 1 ))
				 {
					 pos = importStockData(filename, buf,Arrays.asList(addUploadTitle),Arrays.asList(addUploadField),user);
					 
					 checkMustDateForAddStock(pos);
					 
					 returnAjax = importAddStock(pos,type);
				 }
				 else if(type.equals( 2 ))
				 {
					 pos = importStockData(filename, buf,Arrays.asList(changeUploadTitle),Arrays.asList(changeUploadField),user);
					 
					 reserverChangeType(pos);
					 
					 returnAjax = importChangeStock(pos,type);
				 }
	        }
		 }catch (IOException e) {
			logger.error("读取上传文件出错",e);
			returnAjax = AjaxObject.newError("导入失败："+e.getMessage());
		 }catch (ZhaidouRuntimeException e) {
			 returnAjax = AjaxObject.newError(e.getMessage());
	         logger.error("导入品牌异常信息=="+e.getMessage());
	     }catch (Exception e) {
			logger.error("读取上传文件出错",e);
        	returnAjax = AjaxObject.newError("导入失败：系统错误");
		 }
        String ss = returnAjax.toString();
        return returnAjax.toString();
    }

    
    /**
     * 手动出发同步erp增量库存
     * @return
     */
	@RequestMapping(value="/syncPurchaseStock",method={RequestMethod.GET, RequestMethod.POST })
    public void SyncPurchaseStock(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	String msg = null;
    	JSONObject jsonObject = new JSONObject();
    	try {
			ProductJobTimeModel newProductJobTimeModel = new ProductJobTimeModel();
			DateTime date = new DateTime();
			String datetimestr = "yyyy-MM-dd'T'HH:mm:ss";
			String dateStart = null;
			String dateEnd = null;
			Long userId = null;
			logger.info("product-web从erp获取采购入库数据");

			//获取JOB时间记录数据
			ProductJobTimeModel productJobTimeModel = new ProductJobTimeModel();
			productJobTimeModel.setType(Constants.ERP_JOB_TYPE.PURCHASESTOCK);
			productJobTimeModel = productJobTimeService.queryOne(productJobTimeModel);
			if(productJobTimeModel != null){
				newProductJobTimeModel.setStartTime(productJobTimeModel.getEndTime());
				dateStart = DateUtil.formatErpDateTime(productJobTimeModel.getEndTime()); 
				dateEnd = date.toString(datetimestr);
			}else{
				newProductJobTimeModel.setStartTime(new Date());
				//当前时间一小时内
				dateStart = date.minusHours(1).toString(datetimestr);
				dateEnd = date.toString(datetimestr);
			}
			if(!StringUtil.isEmpty(UserHelper.getUserId(request))){
				userId = Long.valueOf(UserHelper.getUserId(request));
			}
			//准备ERP-JOB记录数据
			newProductJobTimeModel.setEndTime(new Date());
			newProductJobTimeModel.setType(Constants.ERP_JOB_TYPE.PURCHASESTOCK);
			newProductJobTimeModel.setCreateBy(userId);
			newProductJobTimeModel.setUpdateBy(0l);
			msg = productJobTimeService.addPurchaseStockAndJobTime(newProductJobTimeModel, dateStart, dateEnd, userId);
		} catch (Exception e) {
			logger.error("同步ERP错误" , e);
			msg = "同步ERP错误";
		}
    	jsonObject.put("msg", msg);
    	response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonObject.toString());//返回json对象
		response.getWriter().flush();
		response.getWriter().close(); 
    }
    
    
    
    private AjaxObject importChangeStock( List<ProductStockPO> pos, Integer type )
    {
    	
    	AjaxObject returnAjax = null;
    	
    	if(pos == null || pos.size() == 0)
		{
			returnAjax = AjaxObject.newError("导入的文件为空");
		}
		else
		{
			
			ImportStockRequestPo resqPo = new ImportStockRequestPo();
			RequestObject reqObj =  new RequestObject();
			/*
			 * 调用库存的接口
			 */
			resqPo.setPos( pos );
			resqPo.setType( type );
			reqObj.setRequestParams( resqPo );
			ResponseObject responseObj = productStockManager.importStock( reqObj );
       	 
			//判断响应
			if(responseObj.getCode() == 0)
			{
       		 	returnAjax = AjaxObject.newOk("ok");
			}
			else
       	 	{
				if(responseObj.getErrorCode().equals( "20600005" ))
        		{
        			ImportStockResponsePo responsePo = ( ImportStockResponsePo )responseObj.getData();
        			StringBuffer sb = new StringBuffer();
        			
        			if(responsePo != null && responsePo.getSkuList() != null && responsePo.getSkuList().size() > 0)
        			{
        				sb.append( "以下sku对应的库存不存在，如要新增请下载新增库存模板再单独导入" + responsePo.getSkuList().toString() );
        			}
        			if( responsePo != null && responsePo.getStockNotEnoughSkuList() != null && responsePo.getStockNotEnoughSkuList().size() > 0 )
        			{
        				sb.append( "以下sku对应的库存的库存数不足，请核定后再单独导入" + responsePo.getStockNotEnoughSkuList().toString() );
        			}
        			returnAjax =  AjaxObject.newError( sb.toString() );
        		}
        		if(responseObj.getErrorCode().equals( "20600003" ))
        		{
        			throw new ZhaidouRuntimeException("库存系统繁忙，请稍候再试");
        		}
       	 	}
		 }
    	return returnAjax;
	}

	private AjaxObject importAddStock( List<ProductStockPO> pos, Integer type ) throws Exception
    {
    	
    	AjaxObject returnAjax = null;
    	
    	if(pos == null || pos.size() == 0)
		{
			returnAjax = AjaxObject.newError("导入的文件为空");
		}
		else
		{
			
			ImportStockRequestPo resqPo = new ImportStockRequestPo();
			RequestObject reqObj =  new RequestObject();
			
			/*
			 * 调用库存的接口
			 */
			resqPo.setPos( pos );
			resqPo.setType( type );
			reqObj.setRequestParams( resqPo );
        	ResponseObject responseObj = productStockManager.importStock( reqObj );
        	 
        	//判断响应
        	if(responseObj.getCode() == 0)
        	{
        	 returnAjax = AjaxObject.newOk("ok");
        	}
        	else
        	{
        		if(responseObj.getErrorCode().equals( "20600005" ))
        		{
        			ImportStockResponsePo responsePo = ( ImportStockResponsePo )responseObj.getData();
        			if(responsePo != null && responsePo.getSkuList() != null && responsePo.getSkuList().size() > 0)
        			{
        				returnAjax = AjaxObject.newError( "以下sku对应的库存已经存在，如要修改请下载修改库存模板再导入" + responsePo.getSkuList().toString());
        			}
        		}
        		else if(responseObj.getErrorCode().equals( "20600003" ))
        		{
        			throw new ZhaidouRuntimeException("库存系统繁忙，请稍候再试");
        		}
        	}
		}
    	 
    	return returnAjax;
	}

	private void checkMustDateForAddStock( List<ProductStockPO> pos ) {

    	ProductStockPO po = null;
		for(int i = 0 ; i < pos.size() ; i++)
		{
			po = pos.get( i );
			
			//校验必填项
			if(StringUtil.isEmpty( po.getBrandCode() ) || StringUtil.isEmpty( po.getSkuCode() ) || StringUtil.isEmpty( po.getCategoryCode() )
					|| StringUtil.isEmpty( po.getProductCode() ) || null == po.getVirtualStock())
			{
				throw new ZhaidouRuntimeException("导入失败，第" + (i+1) + "行有必填数据为空，请填写后再上传");
			}
			
			if(po.getSkuCode().indexOf( po.getProductCode() ) != 0)
			{
				throw new ZhaidouRuntimeException("导入失败，第" + (i+1) + "行skuCode对应的productCode异常，请校验后再上传");
			}
			
			po.setChangeOption( 1 );
			pos.set( i, po );
		}
		
	}

	/**
     * 校验必填项 并且对全量和增量变更进行数据处理
     */
	private void reserverChangeType( List<ProductStockPO> pos ) 
	{

		ProductStockPO po = null;
		for(int i = 0 ; i < pos.size() ; i++)
		{
			po = pos.get( i );
			
//			haveEmptyValue(po,i);//校验必填项
//			
//			if(po.getProductEname().equals( "=" ))//全量变更
//			{
				po.setChangeOption( 2 );
				
//			}
//			else if(po.getProductEname().equals( "-" ) || po.getProductEname().equals( "+" ))//增量变更
//			{
//				po.setChangeOption( 1 );
//				if(po.getProductEname().equals( "-" ))
//				{
//					po.setVirtualStock( 0 - po.getVirtualStock() );
//				}
//			}
		}
		
	}

	

	@SuppressWarnings("unused")
	private boolean haveEmptyValue( ProductStockPO po,int i ) {

		if(StringUtil.isEmpty( po.getSkuCode() ))
		{
			throw new ZhaidouRuntimeException("第" + (i+1) + "行skuCode为空，请填写后再上传");
		}
		
		if(null == po.getVirtualStock())
		{
			throw new ZhaidouRuntimeException("第" + (i+1) + "行库存变更数为空，请填写后再上传");
		}
		
		if(StringUtil.isEmpty( po.getProductEname()))
		{
			throw new ZhaidouRuntimeException("第" + (i+1) + "行库存变更类型为空，请填写后再上传");
		}
		return true;
	}

	private List<ProductStockPO> importStockData( String filename, byte[] buf, List<String> titleList, List<String>  fieldList, Map<String,String> user ) throws Exception{
		
		List<DBObject> dbList = null; 
    	List<ProductStockPO> pos = null;
    	
		dbList = ExcelUtil.readExcel(filename, new ByteArrayInputStream(buf),titleList,fieldList); //读取excel表格
    	
		pos = DBList2StockList(dbList,user); //处理 DBObject转成ProductBrandModelz
		
//		checkMustDateForAddStock(pos);
		
		return pos;
	}

	private List<ProductStockPO> DBList2StockList( List<DBObject> dbList, Map<String,String> user ) 
	{
		
		List<ProductStockPO> pos = new ArrayList<ProductStockPO>();
		
		for(DBObject dbObj : dbList)
		{
			 
			ProductStockPO po = null;
	    	if(dbObj != null){
	    		po = new ProductStockPO();
	    		Set<String> keySet = dbObj.keySet();
	    		Iterator<String> it =  keySet.iterator();
	    		 
	    		while(it.hasNext())
	    		{
	    			String key = it.next();
	    			//对brandId ,brandStatus ,storeCertificationType 做处理，从String 转成 Integer 类型,因为反射不能将Object 类型赋值给Integer类型
	    			Object value = null;
	    			if("virtualStock".equals(key)){
	    				Object tmpValue = dbObj.get(key);
	    				if(tmpValue != null){
	    					Double intVal = Double.valueOf(tmpValue.toString());
	    					ReflectUtil.setVal(po, key, intVal);
	    				}
	    			}
	    			else if("stockType".equals(key)){
                        Object tmpValue = dbObj.get(key);
                        if(tmpValue != null){
                            Integer intVal = Integer.parseInt(tmpValue.toString());
                            ReflectUtil.setVal(po, key, intVal);
                        }
                    }
	    			else if("stockPercent".equals(key)){
                        Object tmpValue = dbObj.get(key);
                        if(tmpValue != null){
                            Integer intVal = Integer.parseInt(tmpValue.toString());
                            ReflectUtil.setVal(po, key, intVal);
                        }
                    }
	    			else
	    			{
	    				value = dbObj.get(key);
	    				ReflectUtil.setVal(po, key, value);
	    			}
	    		}
	    		if(user != null)
	    		{
	    			po.setChangeUserName( user.get( "userName" ) );
	    			po.setChangeUserCode( user.get( "userCode" ) );
	    		}
	    		pos.add( po );
		    }
		}
		
		return pos;
	}

	
}
