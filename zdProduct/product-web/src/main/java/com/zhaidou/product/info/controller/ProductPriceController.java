package com.zhaidou.product.info.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.common.util.ImportExecl;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.info.manager.ProductCategoryManager;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.manager.ProductPriceListManager;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductPriceListModel;
import com.zhaidou.product.info.model.ProductPriceLogModel;
import com.zhaidou.product.info.model.ProductPriceModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.model.ProductStockModel;
import com.zhaidou.product.info.util.ExcelUtil;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value="/info/productPrice/")
public class ProductPriceController {
	
	    private static final Log logger = LogFactory.getLog(ProductPriceController.class);

	
	    public static String PRODUCTPRICELIST="info/productPrice/product_list";
	    public static String PRODUCTPRICELOGLIST="info/productPrice/productLog_list";
	    public static String PRODUCTPRICEIMPORT="info/productPrice/product_import";
	    public static String PRODUCTPRICEEXAMINE="info/productPrice/product_examine";
	    @Resource
	    private ProductCategoryManager productCategoryManager;
	    
	    
	    @Resource
	    private ProductPriceListManager productPriceListManager;
	    @Resource
	    private ProductManager productManager;
	    
	    /**
	     *获取 登录用户的信息 
	     * */
	    private Map<String,String> getUserMap(HttpServletRequest request){
	        Map<String,String> userMap = (Map<String,String>)request.getSession().getAttribute("user");
	        if(userMap==null){
	            userMap = new HashMap<String, String>();
	            userMap.put("userName", "wanghang");
	            userMap.put("userId", "1");
	        }
	        return userMap;
	    }
	    
	    /**
	     * 转至价格列表页面
	     *
	     * @param page
	     * @param productModel
	     * @param map
	     * @return
	     */
	    @RequestMapping(value="/list",method = { RequestMethod.GET, RequestMethod.POST })
	    public String productList(Page page,ProductPriceListModel productPriceListModel, HttpServletRequest req){
	        
	        // 按时间查询 设置起始时间
	        String StrDate = "";
	        if (productPriceListModel.getCreateTimes() != null && !"".equals(productPriceListModel.getCreateTimes())) {
	            StrDate = productPriceListModel.getCreateTimes();
	            productPriceListModel.setCreateTime(DatetimeUtil.stringToDate(StrDate + " 00:00:01").getTime() / 1000);
	            productPriceListModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(StrDate + " 23:59:59").getTime() / 1000));
	        }
	    	try{
    	        Map<String,Object> map = productPriceListManager.getProductPriceList(productPriceListModel,page);
    	        List<CategoryPO> listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
    	        req.setAttribute("listCategory",listCategory);
    	        req.setAttribute("productPriceList", map.get("productPriceList"));
    	        req.setAttribute("page", map.get("page"));
	    	}catch(Exception e){
	    	    logger.error("",e);
	            req.setAttribute("errorMsg", e.getMessage());
	            return InfoUtil.ERROR_500JSP;
	    	}
	        return PRODUCTPRICELIST;
	    }
	    
	    /**
	     * 转至价格审核列表页面
	     *
	     * @param page
	     * @param productModel
	     * @param map
	     * @return
	     */
	    @RequestMapping(value="/examinelist",method = { RequestMethod.GET, RequestMethod.POST })
	    public String productexamineList(Page page,ProductPriceListModel productPriceListModel, HttpServletRequest req){
            
            String startTime1 = req.getParameter("startTime1");
            String endTime1 = req.getParameter("endTime1");
            String startTime2 = req.getParameter("startTime2");
            String endTime2 = req.getParameter("endTime2");
            if(startTime1!=null && !"".equals(startTime1)){
                productPriceListModel.setCreateTime(DatetimeUtil.stringToDate(startTime1+" 00:00:01").getTime()/1000);
                req.setAttribute("startTime1", startTime1);
            }
            if(endTime1!=null && !"".equals(endTime1)){
                productPriceListModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime1+" 23:59:59").getTime()/1000));
                req.setAttribute("endTime1", endTime1);
            }
            if(startTime2!=null && !"".equals(startTime2)){
                productPriceListModel.setUpdateTime(DatetimeUtil.stringToDate(startTime2+" 00:00:01").getTime()/1000);
                req.setAttribute("startTime2", startTime2);
            }
            if(endTime2!=null && !"".equals(endTime2)){
                productPriceListModel.setEndTime2(Long.toString(DatetimeUtil.stringToDate(endTime2+" 23:59:59").getTime()/1000));
                req.setAttribute("endTime2", endTime2);
            }
	    	try{
    	        Map<String,Object> map = productPriceListManager.getProductPriceList(productPriceListModel,page);
    	        List<CategoryPO> listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
    	        req.setAttribute("listCategory",listCategory);
    	        req.setAttribute("productPriceList", map.get("productPriceList"));
    	        req.setAttribute("page", map.get("page"));
	    	}catch(Exception e){
	    	    logger.error("",e);
                req.setAttribute("errorMsg", e.getMessage());
                return InfoUtil.ERROR_500JSP;
	    	}
	        return PRODUCTPRICEEXAMINE;
	    }
	    
	    
	    /**
	     * 转至价格日志列表页面
	     *
	     * @param page
	     * @param productModel
	     * @param map
	     * @return
	     */
	    @RequestMapping(value="/logList",method = { RequestMethod.GET, RequestMethod.POST })
	    public String productLogList(Page page,ProductPriceLogModel productPriceLogModel, HttpServletRequest req){
	        
	        String createPriceLogStartTime = req.getParameter("createPriceLogStartTime");
            String createPriceLogEndTime = req.getParameter("createPriceLogEndTime");
            String updatePriceLogStartTime = req.getParameter("updatePriceLogStartTime");
            String updatePriceLogEndTime = req.getParameter("updatePriceLogEndTime");
            if(createPriceLogStartTime!=null && !"".equals(createPriceLogStartTime)){
                productPriceLogModel.setCreateTime(DatetimeUtil.stringToDate(createPriceLogStartTime+" 00:00:01").getTime()/1000);
                req.setAttribute("createPriceLogStartTime", createPriceLogStartTime);
            }
            if(createPriceLogEndTime!=null && !"".equals(createPriceLogEndTime)){
                productPriceLogModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(createPriceLogEndTime+" 23:59:59").getTime()/1000));
                req.setAttribute("createPriceLogEndTime", createPriceLogEndTime);
            }
            if(updatePriceLogStartTime!=null && !"".equals(updatePriceLogStartTime)){
                productPriceLogModel.setUpdateTime(DatetimeUtil.stringToDate(updatePriceLogStartTime+" 00:00:01").getTime()/1000);
                req.setAttribute("updatePriceLogStartTime", updatePriceLogStartTime);
            }
            if(updatePriceLogEndTime!=null && !"".equals(updatePriceLogEndTime)){
                productPriceLogModel.setEndTime2(Long.toString(DatetimeUtil.stringToDate(updatePriceLogEndTime+" 23:59:59").getTime()/1000));
                req.setAttribute("updatePriceLogEndTime", updatePriceLogEndTime);
            }
	    	try{
    	        Map<String,Object> map = productPriceListManager.getProductPriceLogList(productPriceLogModel, page);
    	        req.setAttribute("productPriceLogList", map.get("productPriceLogList"));
    	        req.setAttribute("page", map.get("page"));
	    	}catch(Exception e){
	    	    logger.error("",e);
                req.setAttribute("errorMsg", e.getMessage());
                return InfoUtil.ERROR_500JSP;
	    	}
	        return PRODUCTPRICELOGLIST;
	    }
	    
	    
	    /**
	     * 审核操作
	     */
	    @RequestMapping(value="/update",method={ RequestMethod.GET, RequestMethod.POST })
	    @ResponseBody
	    public String update(Long[] ids,HttpServletRequest req){
	    	try {
	    	    for(Long id:ids){
	    	        ProductPriceListModel productPriceListModel=new ProductPriceListModel();
	    	        productPriceListModel.setProductPriceList(id);
	    	        productPriceListModel = productPriceListManager.getProductPriceListById(productPriceListModel);
	    	        if(productPriceListModel!=null && productPriceListModel.getStatus()!=1){
	    	            return AjaxObject.newError( productPriceListModel.getProductSkuCode()+"该数据已审核或已处理,请刷新后再查看").toString();
	    	        }
	    	        if(    productPriceListModel.getTshPrice()!=null 
	                        && productPriceListModel.getMarketPrice()!=null && productPriceListModel.getMarketPrice()>0){
	                    
	                }else{
	                    return AjaxObject.newError( productPriceListModel.getProductSkuCode()+"该数据价格不完整,请确认!").toString();
	                }
	    	    }
	    	    
	    		Map<String,String> map =getUserMap(req);
	    		ProductPriceListModel productPriceListModel =new ProductPriceListModel();
	    		productPriceListModel.setUpdateBy(Long.parseLong(map.get("userId")));
	    		productPriceListModel.setUpdateUserName(map.get("userName"));
	    		productPriceListModel.setStatus(2);
	    		productPriceListModel.setUpdateTime(new Date().getTime()/1000);
	    		
	    		
				productPriceListManager.updateProductPriceList(ids,productPriceListModel);
			} catch (Exception e) {
				logger.error("",e);
				return  AjaxObject.newError( "审核失败,系统错误！").toString();
			}
	    	return  AjaxObject.newOkCallbackType().toString();
	    }
	    
	    
	    /**
	     * 审核操作
	     */
	    @RequestMapping(value="/updateNo",method={ RequestMethod.GET, RequestMethod.POST })
	    @ResponseBody
	    public String updateNo(Long[] ids,String reason,HttpServletRequest req){
	    	try {
	    	    if(ids!=null){
	    	        for(Long id:ids){
	                    ProductPriceListModel productPriceListModel=new ProductPriceListModel();
	                    productPriceListModel.setProductPriceList(id);
	                    productPriceListModel = productPriceListManager.getProductPriceListById(productPriceListModel);
	                    if(productPriceListModel!=null && productPriceListModel.getStatus()!=1){
	                        return AjaxObject.newError( productPriceListModel.getProductSkuCode()+"该数据已审核或已处理,请刷新后再查看").toString();
	                    }
	                }
	    	    }else{
	    	        return  AjaxObject.newError( "操作有误或浏览器不兼容,请重试或替换浏览器").toString();
	    	    }
	    	    
	    		Map<String,String> map =getUserMap(req);
	    		ProductPriceListModel productPriceListModel =new ProductPriceListModel();
	    		productPriceListModel.setUpdateBy(Long.parseLong(map.get("userId")));
	    		productPriceListModel.setUpdateUserName(map.get("userName"));
	    		productPriceListModel.setStatus(8);
	    		productPriceListModel.setReason(reason);
	    		productPriceListModel.setUpdateTime(new Date().getTime()/1000);
				productPriceListManager.updateProductPriceList(ids,productPriceListModel);
			} catch (Exception e) {
				logger.error("",e);
				return  AjaxObject.newError( "审核失败,系统错误！").toString();
			}
	    	return  AjaxObject.newOkCallbackType().toString();
	    }
	    
	    
	    /**
	     * 转至 导入页面 
	     *
	     * @return
	     */
	    @RequestMapping(value="/to_upload_excel",method={RequestMethod.POST,RequestMethod.GET})
	    public String toUploadExcel(){
	        return PRODUCTPRICEIMPORT;
	    }
	    
	    /**
	     * 导入操作
	     */
	    @RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
	    @ResponseBody
	    public String uploadExcel(@RequestParam("file")   MultipartFile file,HttpServletRequest req){
	    	ImportExecl poi = new ImportExecl();
	    	List<List<String>> list = null;
	    	try {
	    		list = poi.read(file.getOriginalFilename(),file.getInputStream());
				if (list != null) {
					Map<String,String> map =getUserMap(req);
					ProductPriceListModel productPriceListModel =new ProductPriceListModel();
					productPriceListModel.setCreateBy(Long.parseLong(map.get("userId")));
		    		productPriceListModel.setCreateUserName(map.get("userName"));
					productPriceListManager.addProductPriceList(list,productPriceListModel);
				}else{
				    return AjaxObject.newError("导入失败！文件内容不符合规范").toString();
				}
			} catch (Exception e) {
				logger.error("",e);
				return AjaxObject.newError("导入失败！"+e.getMessage()).toString();
			}
	    	
	    	return AjaxObject.newOk("OK").toString();
	    }
	    
	    /**
	     * 导出
	     * @param request
	     * @return
	     */
	    @RequestMapping(value="/exportExcel",method={ RequestMethod.GET, RequestMethod.POST })
	    public String  exportExcel(ProductPriceLogModel productPriceLogModel,HttpServletResponse response){
	    	response.setContentType("application/binary;charset=ISO8859_1");  
	        try  
	        {  
	        	Page page=new Page();
	        	page.setNumPerPage(5000);
				Map<String,Object> map = productPriceListManager.getProductPriceLogList(productPriceLogModel ,page);
				List<ProductPriceLogModel> list=(List<ProductPriceLogModel>) map.get("productPriceLogList");
	            ServletOutputStream outputStream = response.getOutputStream();  
	            String fileName = new String(("productLog").getBytes(), "ISO8859_1");  
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
	           
	            String[] titles = { "skucode", "成本价", "售价", "特币", "市场价", "创建人", "创建时间","原因" };  
	            exportExcel(titles, outputStream,list);  
	        }  
	        catch (Exception e)  
	        {  
	        	logger.error("",e);
				return AjaxObject.newError("false").toString();
	        }  
	        return null;  
	    }
	    
	    
	    public void exportExcel(String[] titles, ServletOutputStream outputStream,List<ProductPriceLogModel> list)  throws  Exception
	    {  
	        // 创建一个workbook 对应一个excel应用文件  
	        XSSFWorkbook workBook = new XSSFWorkbook();  
	        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
	        XSSFSheet sheet = workBook.createSheet("导出excel例子");  
	        ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
	        XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
	        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
	        // 构建表头  
	        XSSFRow headRow = sheet.createRow(0);  
	        XSSFCell cell = null;  
	        for (int i = 0; i < titles.length; i++)  
	        {  
	            cell = headRow.createCell(i);  
	            cell.setCellStyle(headStyle);  
	            cell.setCellValue(titles[i]);  
	        }  
	        // 构建表体数据  
	        if (list != null && list.size() > 0)  
	        {  
	            for (int j = 0; j < list.size(); j++)  
	            {  
	                XSSFRow bodyRow = sheet.createRow(j + 1);  
	                ProductPriceLogModel productPriceLogModel = list.get(j);  
	                cell = bodyRow.createCell(0);  
	                cell.setCellStyle(bodyStyle);
	                cell.setCellValue(productPriceLogModel.getProductSkuCode());  
	                cell = bodyRow.createCell(1);  
	                cell.setCellStyle(bodyStyle);  
	                if(productPriceLogModel.getCostPrice()!=null){
	                    cell.setCellValue(productPriceLogModel.getCostPrice()); 
	                }else{
	                    cell.setCellValue("");
	                }
	                cell = bodyRow.createCell(2);  
	                cell.setCellStyle(bodyStyle);  
	                if(productPriceLogModel.getTshPrice()!=null){
                        cell.setCellValue(productPriceLogModel.getTshPrice()); 
                    }else{
                        cell.setCellValue("");
                    }
	                cell = bodyRow.createCell(3);  
	                cell.setCellStyle(bodyStyle); 
	                if(productPriceLogModel.getTb()!=null){
                        cell.setCellValue(productPriceLogModel.getTb()); 
                    }else{
                        cell.setCellValue("");
                    }
	                cell = bodyRow.createCell(4);  
	                cell.setCellStyle(bodyStyle); 
	                if(productPriceLogModel.getMarketPrice()!=null){
                        cell.setCellValue(productPriceLogModel.getMarketPrice()); 
                    }else{
                        cell.setCellValue("");
                    }
	                cell = bodyRow.createCell(5);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(productPriceLogModel.getCreateUserName());  
	                cell = bodyRow.createCell(6);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(productPriceLogModel.getCreateTimes());  
	                cell = bodyRow.createCell(7);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(productPriceLogModel.getReason());    
	            }  
	        }  
	        try  
	        {  
	            workBook.write(outputStream);  
	            outputStream.flush();  
	            outputStream.close();  
	        } finally  
	        {  
	            outputStream.close();  
	        }  
	  
	    }  
	    
	    /**
	     * 商品审核预览
	     *
	     * @return
	     */
	    @RequestMapping(value="/to_look/{id}",method={RequestMethod.POST,RequestMethod.GET})
	    public String getProductLook(@PathVariable String id,HttpServletRequest req){
	        
	        try {
                ProductPriceListModel productPriceListModel = new ProductPriceListModel();
                productPriceListModel.setProductPriceList(Long.parseLong(id));
                productPriceListModel = productPriceListManager.getProductPriceListById(productPriceListModel);
                
                ProductModel productModel = new ProductModel();
                if(productPriceListModel!=null){
                    String productCode = productPriceListModel.getProductSkuCode().substring(0, 12);
                    
                    productModel = productManager.getProductByCode(productCode);
                    
                    Map<String, Object> map = null;
                    if(productModel!=null){
                        map = productManager.getProductInfo(productModel);
                    }
                    
                    if(map!=null){
                        productModel = (ProductModel)map.get("productModel");
                    }
                    
                    List<ProductSkuModel> listSku = new ArrayList<ProductSkuModel>();
                    if(productModel.getProductSkuList()!=null && productModel.getProductSkuList().size()>0){
                        for(ProductSkuModel sku : productModel.getProductSkuList()){
                            if(sku.getProductSkuCode().equals(productPriceListModel.getProductSkuCode())){
                                ProductSkuModel skuModel = new ProductSkuModel();
                                skuModel.setAttrColorName(sku.getAttrColorName());
                                skuModel.setAttrColorValue(sku.getAttrColorValue());
                                skuModel.setColorValueAlias(sku.getColorValueAlias());
                                skuModel.setAttrSpecName(sku.getAttrSpecName());
                                skuModel.setAttrSpecValue(sku.getAttrSpecValue());
                                skuModel.setSpecValueAlias(sku.getSpecValueAlias());
                                
                                ProductPriceModel productPriceModel = new ProductPriceModel();
                                productPriceModel.setCostPrice(productPriceListModel.getCostPrice());
                                productPriceModel.setMarketPrice(productPriceListModel.getMarketPrice());
                                productPriceModel.setTshPrice(productPriceListModel.getTshPrice());
                                productPriceModel.setTb(productPriceListModel.getTb());
                                skuModel.setProductPriceModel(productPriceModel);
                                
                                ProductStockModel productStockModel = new ProductStockModel();
                                productStockModel.setVirtualStock(sku.getProductStockModel().getVirtualStock());
                                skuModel.setProductStockModel(productStockModel);
                                
                                skuModel.setProductImagerList(sku.getProductImagerList());
                                
                                listSku.add(skuModel);
                            }
                        }
                    }
                    
                    if(listSku.size()>0){
                        productModel.setProductSkuList(listSku);
                    }
                    
                }
                
                String yanse="";
                String chima="";
                String ycDate="";
                String ys="";
                String cm="";
                Map<String,String> yanseMap= new HashMap<String, String>();
                Map<String,String> chimaMap= new HashMap<String, String>();
                List<ProductSkuModel> skuList = (List<ProductSkuModel>)productModel.getProductSkuList();
                if(null!=skuList&&skuList.size()>0){
                   for (int j = 0; j < skuList.size(); j++) {
                       ProductSkuModel  pp = skuList.get(j);
                       ys=pp.getAttrColorName();
                       cm=pp.getAttrSpecName();
                       yanseMap.put(pp.getColorValueAlias(),pp.getColorValueAlias());
                       chimaMap.put(pp.getSpecValueAlias(), pp.getSpecValueAlias()); 
                       ycDate=ycDate+"'"+pp.getColorValueAlias()+";"+pp.getSpecValueAlias()+"':{price:"+pp.getProductPriceModel().getTshPrice()+",count:"+pp.getProductStockModel().getVirtualStock()+",tb:"+pp.getProductPriceModel().getTb()+"},";
                   }
                   
                   for(String dataKey : yanseMap.keySet())   {  
                       yanse=yanse+"'"+dataKey+"',";
                   }
                   
                   for(String dataKey : chimaMap.keySet())   {  
                       chima=chima+"'"+dataKey+"',";
                   }
                   if(yanse.length()>0){
                       yanse=yanse.substring(0, yanse.length()-1);
                       chima=chima.substring(0, chima.length()-1);
                       ycDate=ycDate.substring(0, ycDate.length()-1);
                   }
                }
                req.setAttribute("imglist", skuList.get(0).getProductImagerList());
                
                req.setAttribute("productModel", productModel);
                req.setAttribute("ys", ys);
                req.setAttribute("cm", cm);
                req.setAttribute("yanse", yanse);
                req.setAttribute("chima", chima);
                req.setAttribute("keys", "[["+yanse+"],["+chima+"]]");
                req.setAttribute("yanseMap", yanseMap);
                req.setAttribute("chimaMap", chimaMap);
                req.setAttribute("ycDate","{"+ycDate+"}");
            } catch (Exception e) {
                logger.error("",e);
                return InfoUtil.ERROR_500JSP;
            } 
	        
	        return "info/product/view_product";
	    }
	    
	    @Value("#{propertyConfigurerForProject2['price_examine_export_num']}")
	    private Integer priceExamineExportNum;

	    /**
	     * 商品 导出
	     * 
	     * @param req
	     * @return
	     */
	    @RequestMapping(value = "/examine/export", method = { RequestMethod.POST,RequestMethod.GET })
	    @ResponseBody
	    public String exportExamineExcel(HttpServletResponse response,
	            ProductPriceListModel productPriceListModel, Page page,
	            HttpServletRequest req,String exportType) {
	        
	        String startTime1 = req.getParameter("startTime1");
            String endTime1 = req.getParameter("endTime1");
            String startTime2 = req.getParameter("startTime2");
            String endTime2 = req.getParameter("endTime2");
            if(startTime1!=null && !"".equals(startTime1)){
                productPriceListModel.setCreateTime(DatetimeUtil.stringToDate(startTime1+" 00:00:01").getTime()/1000);
                req.setAttribute("startTime1", startTime1);
            }
            if(endTime1!=null && !"".equals(endTime1)){
                productPriceListModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime1+" 23:59:59").getTime()/1000));
                req.setAttribute("endTime1", endTime1);
            }
            if(startTime2!=null && !"".equals(startTime2)){
                productPriceListModel.setUpdateTime(DatetimeUtil.stringToDate(startTime2+" 00:00:01").getTime()/1000);
                req.setAttribute("startTime2", startTime2);
            }
            if(endTime2!=null && !"".equals(endTime2)){
                productPriceListModel.setEndTime2(Long.toString(DatetimeUtil.stringToDate(endTime2+" 23:59:59").getTime()/1000));
                req.setAttribute("endTime2", endTime2);
            }
            try{
                
                Integer pageNum = 1;
                page.setPageNum(pageNum);
                page.setNumPerPage(priceExamineExportNum);
                
                Map<String,Object> map = productPriceListManager.getProductPriceList(productPriceListModel,page);
	        
                List<ProductPriceListModel> listInfoAuth1 = (List<ProductPriceListModel>)map.get("productPriceList");
                if(listInfoAuth1!=null && listInfoAuth1.size()>0){
                    for(ProductPriceListModel priceModel :listInfoAuth1){
                        if(priceModel.getOldTshPrice()!=null && priceModel.getOldTshPrice()>0 && 
                                priceModel.getTshPrice()!=null && priceModel.getTshPrice()>0){
                            Double floatPrice = priceModel.getTshPrice()/priceModel.getOldTshPrice();
                            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.##");
                            priceModel.setFloatPrice(Double.parseDouble(df.format(floatPrice)));
                        }
                    }
                }
                page = (Page)map.get("page");
	        
	         // 查询所有的一级目录
	            List<CategoryPO> categoryPO1List = new ArrayList<CategoryPO>();
	            categoryPO1List = productCategoryManager.getAllByLevel(1);
	            
	            // 查询所有的二级目录        
	            List<CategoryPO> categoryPO2List;
	            
	                categoryPO2List = productCategoryManager.getAllByLevel(2);

	            String categoryPO1 = null;
	            String categoryPO2 = null;
	            String categoryPO3 = null;

	            for (ProductPriceListModel p : listInfoAuth1) {
	                
	                categoryPO3 = p.getCatCode();
	                if(categoryPO3==null){
	                    continue;
	                }
	                categoryPO1 = categoryPO3.substring(0, 2);
	                categoryPO2 = categoryPO3.substring(0, 4);

	                for (CategoryPO c2 : categoryPO2List) // 插入二级目录的name和code
	                {
	                    if (categoryPO2.equals(c2.getCategoryCode())) {
	                        p.setCatCode2(c2.getCategoryCode());
	                        p.setCatName2(c2.getCategoryName());
	                        break;
	                    }

	                }
	                for (CategoryPO c1 : categoryPO1List)// 插入一级目录的name和code
	                {
	                    if (categoryPO1.equals(c1.getCategoryCode())) {
	                        p.setCatCode1(c1.getCategoryCode());
	                        p.setCatName1(c1.getCategoryName());
	                        break;
	                    }
	                }
	            }
	            exportProductExcel(response, listInfoAuth1,"config/excel/priceExamine.xls", req,"priceExamine.xls");
	        } catch (Exception e) {
	            logger.error("",e);
	            req.setAttribute("errorMsg", e.getMessage());
	            return InfoUtil.ERROR_500JSP;
	        }
	        return null;
	    }
	    
	    /**
	     * 根据excel模版 ,modelList,response,require,目标文件后缀名 导出excel报表
	     * 
	     * @param response
	     * @param list
	     * @param templateFileName
	     * @param req
	     * @param filename
	     * @return
	     */
	    public  String exportProductExcel(HttpServletResponse response,
	            List<?> list, String templateFileName, HttpServletRequest req,
	            String filename) {

	        String resultFileName = "config/excel/" + new Random(10000).toString()
	                + filename;
	        ExcelUtil excelUtil = new ExcelUtil();
	        excelUtil.createExcel(templateFileName, list, resultFileName);

	        try {
	            XLSTransformer transformer = new XLSTransformer();
	            Workbook wb = null;
	            ServletOutputStream outputStream = response.getOutputStream();
	            response.setHeader("Content-disposition", "attachment; filename="
	                    + filename);// 组装附件名称和格式

	            URL url = this.getClass().getClassLoader().getResource("");
	            // 得到模板文件路徑
	            // String srcFilePath = url.getPath() + templateFileName;
	            Map<String, Object> beanParams = new HashMap<String, Object>();
	            beanParams.put("list", list);
	            String destFilePath = url.getPath() + resultFileName;

	            try {
	                wb = transformer.transformXLS(
	                        new FileInputStream(destFilePath), beanParams);
	            } catch (ParsePropertyException e) {
	                logger.error("",e);
	            } catch (InvalidFormatException e) {
	                logger.error("",e);
	            }

	            try {
	                wb.write(outputStream);
	                outputStream.flush();
	                outputStream.close();
	            } finally {
	                outputStream.close();
	            }

	        } catch (IOException e) {
	            logger.error("",e);
	        }
	        return null;
	    }
	    
	    @RequestMapping(value = "/to_updatePrice/{id}", method = {RequestMethod.GET })
	    public String toUpdatePrice(@PathVariable Long id,HttpServletRequest req){
	        String priceRate = req.getParameter("priceRate");
	        
	        
	        ProductPriceListModel productPriceListModel = new ProductPriceListModel();
	        productPriceListModel.setProductPriceList(id);
	        try {
	            productPriceListModel = productPriceListManager.getProductPriceListById(productPriceListModel);
            } catch (Exception e) {
                logger.error("",e);
                return InfoUtil.ERROR_500JSP;
            }
	        if(priceRate!=null){
                productPriceListModel.setPriceRate(Double.parseDouble(priceRate));
            }
	        req.setAttribute("productPriceListModel", productPriceListModel);
	        req.getSession().setAttribute(""+productPriceListModel.getProductPriceList(), productPriceListModel);
	        return "info/productPrice/update_price";
	    }
	    
	    @RequestMapping(value = "/updatePrice", method = { RequestMethod.POST })
	    @ResponseBody
        public String updatePrice(ProductPriceListModel productPriceListModel,HttpServletRequest req){
	        
	        if(productPriceListModel.getCostPrice()==null){
	            return "供货价不能为空,请确认!";
	        }
	        if(productPriceListModel.getMarketPrice()==null){
                return "市场价不能为空,请确认!";
            }
	        if(productPriceListModel.getTshPrice()==null){
                return "销售价不能为空,请确认!";
            }
	        if(productPriceListModel.getTb()==null){
                return "特币不能为空,请确认!";
            }
	        
	        Map<String,String> map = getUserMap(req);
	        String result = "";
            if(map!=null){
                String priceRate = req.getParameter("oldPriceRate");
                ProductPriceListModel oldPriceModel = null;
                try {
                    oldPriceModel = productPriceListManager.getProductPriceListById(productPriceListModel);
                } catch (Exception e) {
                    logger.error("",e);
                    return "系统错误,请稍后再试!";
                }
                long userId = Long.parseLong(map.get("userId").toString());
                String userName = map.get("userName").toString();
                long time = new Date().getTime()/1000; 
                oldPriceModel.setUpdateBy(userId);
                oldPriceModel.setUpdateTime(time);
                oldPriceModel.setUpdateUserName(userName);
                oldPriceModel.setPriceRate(Double.parseDouble(priceRate));
                try {
                    productPriceListManager.updateProductPriceList(productPriceListModel, oldPriceModel);
//                    result = AjaxObject.newOk("修改成功").toString();
                } catch (Exception e) {
                    logger.error("加价率修改失败",e);
//                    result = AjaxObject.newError("修改失败").toString();
                    result="修改失败";
                }
            }else{
//                result = AjaxObject.newError("登录超时,请重新登录！").toString();
                result="登录超时,请重新登录！";
            }
            
            return result;
        }
}
