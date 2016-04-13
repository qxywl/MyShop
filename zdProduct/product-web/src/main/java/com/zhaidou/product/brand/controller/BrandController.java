package com.zhaidou.product.brand.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.DBObject;
import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.product.Util.StringUtil;
import com.zhaidou.product.Util.UserHelper;
import com.zhaidou.product.brand.enumration.BrandStatusEnum;
import com.zhaidou.product.brand.enumration.StoreCertificateEnum;
import com.zhaidou.product.brand.model.CountryModel;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.model.ProductBrandPO;
import com.zhaidou.product.brand.service.CountryService;
import com.zhaidou.product.brand.service.ProductBrandService;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.brand.util.ImageUtil;



@Controller
@RequestMapping("/brand")
public class BrandController {
	
	private static Logger logger = Logger.getLogger(BrandController.class);    

	/**页面参数*/
	private static String BRAND_LIST = "brand/brand_list"; //列表页面
	private static String BRAND_ADD = "brand/brand_add"; //添加页面 
	private static String BRAND_UPDATE = "brand/brand_update"; //更新页面 
	private static String BRAND_DETAIL = "brand/brand_detail"; //详情页面 
	private static String BRAND_IMPORT = "brand/brand_import"; //上传页面
	private static String BRAND_IMAGE_UPLOAD = "brand/brand_image_upload"; //上传页面
	
	/**导出字段和表名*/
	private static String [] strExportTitle ={"品牌id","品牌编号","品牌名称","品牌别名","品牌说明","品牌故事","品牌国家","状态","品牌旗舰店铺编号","旗舰店认证状态","最后修改时间","最后修改者"};
	private static String [] strExportField ={"brandId","brandCode","brandName","brandEname","brandInfo","brandStory","brandCountryPO","brandStatusPO","flagshipStoreCode","storeCertificationTypePO","updateTimes","updateUser"};
	
	/**导入*/
	String [] strUploadTitle ={"品牌名称","品牌别名或英文名","品牌旗舰店铺编号","品牌说明","品牌故事","品牌所属国家"};
	String [] strUploadField ={"brandName","brandEname","flagshipStoreCode","brandInfo","brandStory","brandCountry"};
	
	
    @Resource
    private ProductBrandService productBrandService;
   
    @Resource
    private CountryService countryService;
    
    @Resource
    private ImagePathManager imagePathManager;
	    
    
    /**
     * 获取数据分页列表
     * @param page 分页信息
     * @param productBrandModel 品牌模型查询条件
     * @param map 返回数据
     * @return String 返回JSON字符格式结果
     * */
    @RequestMapping(value="/list",method = { RequestMethod.GET, RequestMethod.POST })
    public String List(Page page,ProductBrandModel productBrandModel,Map<String,Object> map,HttpServletRequest request){
       
    	List<ProductBrandModel> brandList = null;
        try {
        	brandList = productBrandService.getProductBrandListPage(productBrandModel, page);
        } catch (Exception e) {
        	logger.info("获取品牌分页列表异常=="+e.getMessage());
        }
        map.put("productBrandModel",productBrandModel);
        map.put("brandList", brandList);
        map.put("page", page);
        return BRAND_LIST;
    }
    
    
    /**
     * 转至添加页面
     * @param  map 用于存放国家结合列表
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/to_add",method = { RequestMethod.GET, RequestMethod.POST })
    public String toAddProductBrand(Map<String,Object> map){
    	List<CountryModel> countryList = null;
    	CountryModel countryModel = new CountryModel();
    	try {
			countryList = countryService.getConditionList(countryModel);
			
		} catch (Exception e) {
			logger.info("获取国家列表异常"+e.getMessage());
		}
    	map.put("countryList", countryList);
    	return BRAND_ADD;
    }
    
    
    /**
     * 转至更新页面
     * @param id 品牌id
     * @param map 用户存储页面需要展示数据
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/to_update/{id}",method = { RequestMethod.GET, RequestMethod.POST })
    public String toUpdateProductBrand(@PathVariable Long id,Map<String,Object> map){
    	ProductBrandModel productBrandModel =null;
    	List<CountryModel> countryList = null;
    	CountryModel countryModel = new CountryModel();
	    try {
	    	 productBrandModel = productBrandService.getProductBrandById(id);
	    	 countryList = countryService.getConditionList(countryModel);
	    	 
	    } catch (Exception e) {
	         logger.info("获取品牌信息操作异常信息=="+e.getMessage(),e);
	    }
	    map.put("brandModel", productBrandModel);
	    map.put("countryList", countryList);
	    return BRAND_UPDATE;
    }
    
    
    /**
     * 转至详情
     * @param id 品牌id
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/to_detail/{id}",method = { RequestMethod.GET, RequestMethod.POST })
    public String toDetailroductBrand(@PathVariable Long id,Map<String,Object> map){
    	ProductBrandModel productBrandModel =null;
	    try {
	    	 productBrandModel = productBrandService.getProductBrandById(id);
	    } catch (Exception e) {
	         logger.info("获取品牌信息操作异常信息=="+e.getMessage(),e);
	    }
	    map.put("brandModel", productBrandModel);
	    return BRAND_DETAIL;
    }
    
    
    /**
     * 转至上传页面
     */
    @RequestMapping(value="/to_import",method = { RequestMethod.GET, RequestMethod.POST })
    public String toUpload(){
    	return BRAND_IMPORT;
    }
    
    
    /**
     * 转至图片上传页面
     */
    @RequestMapping(value="/to_image_upload",method = { RequestMethod.GET, RequestMethod.POST })
    public String toImageUpload(){
    	return BRAND_IMAGE_UPLOAD;
    }
    
    
    /**
     * 添加操作
     * @param productBrandModel 
     * @param resp
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/add",method={ RequestMethod.POST,RequestMethod.GET } )
    @ResponseBody
    public String addProductBrand(ProductBrandModel productBrandModel,HttpServletRequest request,
    		HttpServletResponse resp, @RequestParam  MultipartFile file){
        String strReturn = null;
        AjaxObject returnAjax = null;
        try {
        	productBrandModel.setCreator(UserHelper.getUserName(request));
        	productBrandModel.setUpdateUser(UserHelper.getUserName(request));
        	productBrandService.addProductBrand(productBrandModel,file);
        	returnAjax = AjaxObject.newOk("添加成功");
        	returnAjax.setNavTabId("brand");
        	strReturn = returnAjax.toString();
        }catch (ZhaidouRuntimeException e) {
        	strReturn = AjaxObject.newError(e.getMessage()).toString();
        	logger.error("更新品牌异常信息=="+e.getMessage(),e);
        }catch (Exception e) {
        	strReturn = AjaxObject.newError("添加品牌服务器异常").toString();
        	logger.error("更新品牌异常信息=="+e.getMessage(),e);
        }
        return strReturn;
    }
    
    
    /**
     * 更新操作
     * @param productBrandModel
     * @param resp
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/update",method={ RequestMethod.POST })
    @ResponseBody
    public String updateProductBrand(ProductBrandModel productBrandModel,HttpServletRequest request,
    		HttpServletResponse resp,@RequestParam  MultipartFile file){
        
        productBrandModel.setUpdateTime(new Date().getTime());
        productBrandModel.setUpdateUser("updateUser");
        AjaxObject returnAjax = null;
        try {
        	productBrandModel.setUpdateUser(UserHelper.getUserName(request));
        	String upStr = productBrandService.updateProductBrand(productBrandModel,UserHelper.getUserMap(request),1L,"更新品牌",file);
        	returnAjax = AjaxObject.newOk(upStr);
        	returnAjax.setNavTabId("brand");
        } catch (ZhaidouRuntimeException e) {
        	returnAjax = AjaxObject.newError("更新品牌失败，失败信息=" +e.getMessage());
        	logger.error("更新品牌异常信息=="+e.getMessage(),e);
        }catch (Exception e) {
        	logger.error("更新品牌异常信息=="+e.getMessage(),e);
        	returnAjax = AjaxObject.newError("更新品牌失败,失败信息="+e.getMessage());
        }
       
        return returnAjax.toString();
    }
    
    
    /**
     * 废弃操作，在页面上显示删除操作，其实只是更改状态
     * @param id
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/abandon/{id}",method={  RequestMethod.POST })
    @ResponseBody
    public String deleteUpdateProductBrand(@PathVariable Long id,HttpServletRequest request,HttpServletResponse resp){
       
    	int brandStatus;
        String strReturn = null;
        AjaxObject returnAjax = null;
        ProductBrandModel productBrandModel = null;
       
        try {
        	productBrandModel = productBrandService.getProductBrandById(id);
        	if(productBrandModel !=null){
        	/*	
        		//判断是否被商品关联
        		boolean relBool = productBrandService.checkBrandRelProduct(productBrandModel.getBrandCode());
        	
        		if(relBool){
        			returnAjax = AjaxObject.newError("已经有产品关联品牌，不允许删除!");
        			return returnAjax.toString();
        		}*/
        		
        		brandStatus = productBrandModel.getBrandStatus();
        		if(brandStatus == BrandStatusEnum.disable.getInt()){   //禁用状态的才可以废弃
        			productBrandModel.setBrandStatus(BrandStatusEnum.delete.getInt());
            		productBrandModel.setUpdateTime(new Date().getTime());
            		productBrandService.updateProductBrand(productBrandModel,UserHelper.getUserMap(request),2L,"删除品牌",null);
            		returnAjax = AjaxObject.newOk("删除成功");
            		returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
            		returnAjax.setForwardUrl("brand/list");
            		
        		}else{
        			returnAjax = AjaxObject.newError("该状态不允许删除！");
        			returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
            		returnAjax.setForwardUrl("brand/list");
        		}
        		
        	}
        	
        } catch (Exception e) {
        	logger.error("删除品牌异常信息=="+e.getMessage(),e);
        	returnAjax = AjaxObject.newError("删除品牌服务异常");
        	returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
    		returnAjax.setForwardUrl("brand/list");
        }
        
    	strReturn = returnAjax.toString();
        return strReturn;
    }
    
    
    /**
     * 批量废弃操作
     * @param ids  id字符串
     * @return String 返回JSON字符格式结果
     */
    @RequestMapping(value="/batchAbandon",method={  RequestMethod.POST })
    @ResponseBody
    public String batchDeleteProductBrand(String ids,HttpServletResponse resp){
       
        String strReturn = null;
        AjaxObject returnAjax = null;
        try {
        	returnAjax = AjaxObject.newOk("删除成功");
        	returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
        	returnAjax.setForwardUrl("brand/list");
        } catch (Exception e) {
        	logger.error("删除品牌异常信息=="+e.getMessage(),e);
        	returnAjax = AjaxObject.newError("删除品牌服务异常");
        }
        
    	strReturn = returnAjax.toString();
        return strReturn;
    }
    
    
    /**
     * 删除操作
     * @return String 
     */
    @RequestMapping(value="/delete/{id}",method={  RequestMethod.POST })
    @ResponseBody
    public String deleteProductBrand(@PathVariable Integer id,HttpServletResponse resp){
       
        String strReturn = null;
        AjaxObject returnAjax = null;
        try {
        	productBrandService.deleteById(id);
        	returnAjax = AjaxObject.newOk("删除成功");
        	returnAjax.setCallbackType(AjaxObject.CALLBACK_TYPE_FORWARD);
        	returnAjax.setForwardUrl("brand/list");
        } catch (Exception e) {
        	logger.error("删除品牌异常信息:"+e.getMessage(),e);
        	returnAjax = AjaxObject.newError("删除品牌异常");
        }
        
    	strReturn = returnAjax.toString();
        return strReturn;
    }
    
    
    /**
     * 下载模板
     * @return page 分页信息
     * @return String 返回JSON字符格式结果
     * */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/downTemplate",method={  RequestMethod.GET})
    @ResponseBody
    public String downTemplate(Page page,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<ProductBrandModel> brandList = null;
       
        String fileType=".xlsx";
        String fileName = "品牌导入表单模板.xlsx";
        String sheetName = "品牌表单";
    	
    	List<String> titleList = Arrays.asList(strUploadTitle);
    	List<String> fieldList = Arrays.asList(strUploadField);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
        	
    		 brandList = getTmplateBrand();
        	 book = new ExcelUtil().writeExcel(fileType,sheetName, titleList, fieldList, brandList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtil.toUtf8String(fileName) + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
        } catch (Exception e) {
        	
        	logger.info("export品牌异常信息=="+e.getMessage(),e);
        	returnAjax = AjaxObject.newError("export品牌异常信息=="+e.getMessage());
        	
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	
    	strReturn = returnAjax.toString();
        return strReturn;
    }
    

	/**
     * 导出所有商品
     * @return page 分页信息
     * @return String 返回JSON字符格式结果
     * */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/export",method={  RequestMethod.GET})
    @ResponseBody
    public String exportProductBrand(Page page,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<ProductBrandPO> brandPOList = null;
       
        String fileType=".xlsx";
        String fileName = "品牌表单.xlsx";
        String sheetName = "品牌表单";
    	
    	List<String> titleList = Arrays.asList(strExportTitle);
    	List<String> fieldList = Arrays.asList(strExportField);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
        	
    		 brandPOList = productBrandService.exportBrand();
        	 book = new ExcelUtil().writeExcel(fileType,sheetName, titleList, fieldList, brandPOList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtil.toUtf8String(fileName) + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
        } catch (Exception e) {
        	logger.error("export品牌异常信息==",  e);
        	returnAjax = AjaxObject.newError("export品牌异常!"+e.getMessage());
        	
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	
    	strReturn = returnAjax.toString();
        return strReturn;
    }
    
    
    /**
     * 导入名单
     * */
    @SuppressWarnings("unused")
	@ResponseBody
    @RequestMapping(value="/importBrand",produces = {"application/json;charset=UTF-8"})
    public String importBrand(MultipartHttpServletRequest request, HttpServletResponse response,
    		@RequestParam("file")   MultipartFile file){
    	
    	 AjaxObject returnAjax = null;
    	 List<DBObject> brandList = null;
    	 
         String filename = file.getOriginalFilename();
         byte[] buf = null;
        
         try {
			buf = file.getBytes();
			logger.info(filename + "==" + buf.length);
			if (buf == null) {
	             returnAjax = AjaxObject.newError("文件为空");
	        }else{
	        	 String operator = UserHelper.getUserName(request);
	        	 String impStr = productBrandService.importdBrand(filename, buf,Arrays.asList(strUploadTitle),Arrays.asList(strUploadField),operator);
            	 returnAjax = AjaxObject.newOk(impStr);
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
    
    
    @ResponseBody
    @RequestMapping(value="/uploadImage",method={ RequestMethod.POST },produces = {"application/json;charset=UTF-8"})
    public String uploadImage(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam("file")   MultipartFile file){
    	 AjaxObject returnAjax = null;
    	 String image = "image"; //图片文件
    	
    	 //创建图片文件的根目录
//    	 String path=request.getSession().getServletContext().getRealPath("/");
    	
    	 String path = "/data/img_tmp/brand/";
//    	 String imagePath = path + image;
	     String filename = file.getOriginalFilename();
	    
	     //解析文件名,组装文件名
	     String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	    
	     if(!ImageUtil.checkImage(fileExt)){
	    	 returnAjax = AjaxObject.newError("请选择图片上传");
	    	 return returnAjax.toString();
	     }
	     
	     Long dateLong = new Date().getTime();
	     filename = dateLong + "." + fileExt;
		 try {
			 
			byte bytes[] = file.getBytes();
			ImageUtil.write(bytes, path, filename);
			
		/*	RequestObject requestObjest = new RequestObject();
            UploadImageRequestPO requestPo = new UploadImageRequestPO();
            requestPo.setImages(listImg);
            requestPo.setRelationCode(productCode);
            requestPo.setRelationSubCode(skuCode);
            requestPo.setTmpPath("product");
            requestPo.setRelationType(1);
            requestObjest.setRequestParams(requestPo);
            responseObject = imagePathManager.uploadImage(requestObjest);*/
			
			
			
		 } catch (IOException e) {
		    returnAjax = AjaxObject.newError("图片上传失败");
			logger.error("读取上传文件出错",e);
			return returnAjax.toString();
		 }

    	 returnAjax = AjaxObject.newOk("图片上传成功");
//    	 returnAjax.setMessage(contextPath+ "/" + image + "/" +filename);
    	 return returnAjax.toString(); 
    }
    

    /**
     * DBObject对象 转换成 ProductBrandModel对象
     * */
    public List<ProductBrandModel> dbObj2BrandModel(List<DBObject> dbObjList){
    	List<ProductBrandModel> brandList = new ArrayList<ProductBrandModel>();
    	for(DBObject dbObj:dbObjList){
    		Set<String> keySet = dbObj.keySet();
    		Iterator<String> it =  keySet.iterator();
    		ProductBrandModel brand = new ProductBrandModel();
    		while(it.hasNext()){
    			String key = it.next();
    			Object value =dbObj.get(key);
    			ReflectUtil.setVal(brand, key, value);
    		}
    		brandList.add(brand);
    	} 
    	return brandList;
    }
    
    
    /**
     * 模板数据，用于模板下载数据填充
     * */
    private List<ProductBrandModel> getTmplateBrand() {
    	List<ProductBrandModel>  list = new ArrayList<ProductBrandModel>();
    	ProductBrandModel brandModel = new ProductBrandModel();
    	brandModel.setBrandName("三星手机");
    	brandModel.setBrandEname("Galaxy");
    	brandModel.setBrandInfo("高配置");
    	brandModel.setBrandStory("神奇的功能");
    	brandModel.setBrandCountry("韩国");
    	brandModel.setBrandLogo("http://localhost:8080/product/logo.jpg");
    	brandModel.setBrandStatus(BrandStatusEnum.enable.getInt());
    	brandModel.setFlagshipStoreCode("FSC123");
    	brandModel.setStoreCertificationType(StoreCertificateEnum.uncertified.getInt());
    	list.add(brandModel);
		return list;
	}
    
    
    
    /************************************************************************************************/
    /***************************                测试                                          *********************************/
    /************************************************************************************************/
   
    /**
     * 转至添加页面
     */
    @RequestMapping(value="/to_test",method = { RequestMethod.GET, RequestMethod.POST })
    public String toTestBrand(Page page,ProductBrandModel productBrandModel,Map<String,Object> map){
    
        return "brand/brand_image_show";
    }
    
    
    /**
     * 添加操作
     * @return String
     */
    @RequestMapping(value="/test",method={ RequestMethod.POST } )
    @ResponseBody
    public String test(ProductBrandModel productBrandModel,HttpServletResponse resp){
        
        String strReturn = null;
        try {
        	strReturn = AjaxObject.newOk("更新成功").toString();
        } catch (Exception e) {
        	logger.info("更新品牌异常信息=="+e.getMessage());
        	strReturn = AjaxObject.newError("更新品牌异常信息=="+e.getMessage()).toString();
        }
       
        return strReturn;
    }
    

}
