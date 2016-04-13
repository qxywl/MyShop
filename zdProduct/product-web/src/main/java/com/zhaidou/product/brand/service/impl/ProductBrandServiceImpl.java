/*
 * 文 件 名:  ProductBrandServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.brand.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.service.impl.BaseServiceImpl;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.imageservice.model.request.GetImagePathBatchRequestPO;
import com.zhaidou.imageservice.model.request.GetImagePathRequestPO;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.GetImageBatchResponsePO;
import com.zhaidou.imageservice.model.response.GetImageResponsePO;
import com.zhaidou.product.brand.dao.ProductBrandDao;
import com.zhaidou.product.brand.enumration.BrandStatusEnum;
import com.zhaidou.product.brand.enumration.StoreCertificateEnum;
import com.zhaidou.product.brand.model.CountryModel;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.model.ProductBrandPO;
import com.zhaidou.product.brand.service.CountryService;
import com.zhaidou.product.brand.service.ProductBrandService;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.brand.util.ImageUtil;
import com.zhaidou.product.info.model.ProductOtherJobModel;
import com.zhaidou.product.info.service.ProductOtherJobService;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productBrandService")
@Transactional()
public class ProductBrandServiceImpl extends BaseServiceImpl implements ProductBrandService
{
    private static final Log logger = LogFactory.getLog(ProductBrandServiceImpl.class);

    @Resource
    private ProductBrandDao  productBrandDao;
    @Resource
    private CountryService  countryService;
    @Resource
    private ProductOtherJobService productOtherJobService;
    @Resource
    private ImagePathManager imagePathManager;
    @Resource
    private ImageSearchManager imageSearchManager;
    
	/*@Resource
    private JmsMessageSender jmsMessageSender;*/

    @Value("#{propertyConfigurerForProject2['cache_pc_time']}")
    private Integer cachePcTime;
    @Value("#{propertyConfigurerForProject2['cache_android_time']}")
    private Integer cacheAndroidTime;
    @Value("#{propertyConfigurerForProject2['cache_ios_time']}")
    private Integer cacheIosTime;
    @Value("#{propertyConfigurerForProject2['cache_wap_time']}")
    private Integer cacheWapTime;
    
    @Value("#{propertyConfigurerForProject2['img_brand_dir']}")
    private String img_brand_dir ;
    
    public void addProductBrand(ProductBrandModel productBrandModel, MultipartFile file) throws ZhaidouRuntimeException,Exception
    {
    	if(StringUtils.isEmpty(productBrandModel.getBrandName())){
			 throw new ZhaidouRuntimeException("品牌名称["+productBrandModel.getBrandName()+"]不能为空");
		 }
		 if(checkExistByName(productBrandModel.getBrandName())){
			 throw new ZhaidouRuntimeException("品牌名称["+productBrandModel.getBrandName()+"]已经存在");
		 }
	    	
	     insertBrand(productBrandModel,file);
     
    }

    
    public String updateProductBrand(ProductBrandModel productBrandModel,Map<String,String> userMap,Long operateType,String reason,MultipartFile file)throws Exception
    {
        String returnStr = null;
        
        //如果名称改变了，需要判断是否已经存在
        if(productBrandModel.getBrandOldName() != null){
        	 if(!(productBrandModel.getBrandOldName().equals(productBrandModel.getBrandName()))){
             	if(checkExistByName(productBrandModel.getBrandName())){//判断是否已经存在
             		returnStr = "品牌名称["+productBrandModel.getBrandName()+"]已经存在";
//             		return returnStr;
             		throw new ZhaidouRuntimeException(returnStr);
             	}
             }
        }
       
        productBrandModel.setUpdateTime(new Date().getTime());
        
        uploadImageToImgServer(file,productBrandModel.getBrandCode());//上传图片到图片服务器
        productBrandDao.update(productBrandModel);
        
        //插入任务表
        ProductOtherJobModel productOtherJobModel = new ProductOtherJobModel();
        productOtherJobModel.setOtherCode(productBrandModel.getBrandCode());
        productOtherJobModel.setType(1L);
        productOtherJobModel.setStatus(3L);
        productOtherJobModel.setReason(reason);
        productOtherJobModel.setOperate(operateType);
        
        
        if(userMap != null){
        	String userId = userMap.get("userId");
        	if(!StringUtils.isEmpty(userId)){
        		productOtherJobModel.setCreateBy(Long.valueOf(userId));
        	}
        	productOtherJobModel.setCreateUserName(userMap.get("userName"));
        }
        
        productOtherJobModel.setCreateTime(System.currentTimeMillis());
        
        productOtherJobService.addProductOtherJob(productOtherJobModel);
        
        ProductBrandModel brandModel = getProductBrandById(productBrandModel.getBrandId());
        //清除原来的缓存
        this.getCachedClient().releaseObject("brand_"+productBrandModel.getBrandCode());
       
        //添加新的缓存
        this.getCachedClient().cacheObject("brand_"+brandModel.getBrandCode(), brandModel, cachePcTime);
        returnStr = "操作成功！";
        return returnStr;
    }

    
    /**
     * 上传图片得到图片服务器
     * @param file 图片文件
     * @param brandCode 品牌code
     * @throws IOException 
     * */
    private void uploadImageToImgServer(MultipartFile file,String brandCode) throws IOException {
    	
    	if(file != null && !file.isEmpty()){
	        String filename = file.getOriginalFilename();
	   	    //解析文件名,组装文件名
	   	    String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	   	    Long dateLong = new Date().getTime();
	   	    filename = dateLong + "." + fileExt;
	   		 
	   	    byte bytes[] = file.getBytes();
	   		ImageUtil.write(bytes, img_brand_dir, filename);
	   		 
	   		List<Image> listImg = new ArrayList<Image>();
	   	    
	   		Image image = new Image();
	   	    image.setImageIndex(1);
	   	    image.setImageName(filename);
	   	    image.setImageVersion(null);
	   	    image.setOldPathName(null);
	   	    listImg.add(image);
	   	     
	   		RequestObject requestObjest = new RequestObject();
	   		
	          UploadImageRequestPO requestPo = new UploadImageRequestPO();
	          requestPo.setImages(listImg);
	          requestPo.setRelationCode(brandCode);
	          requestPo.setRelationSubCode(null);
	            
	          requestPo.setTmpPath("brand");
	          requestPo.setRelationType(3);
	          requestObjest.setRequestParams(requestPo);
	          ResponseObject responseObject = imagePathManager.uploadImage(requestObjest);
	          logger.info("图片上传信息 =="+(JSONObject.toJSONString(responseObject)));
	          /*String imagePath = null;
	          if(responseObject.getCode()==0){
	              UploadImageResponsePO responsePo = (UploadImageResponsePO)responseObject.getData();
	              listImg = responsePo.getImages();
	              //拼接图片路径
	              imagePath = responsePo.getImagePath()+"/"+listImg.get(0).getImageName()+responsePo.getImageType();
	          }else{
	        	  logger.error("添加图片上传失败,失败原因:" + responseObject.getMsg());
	          }
	          productBrandModel.setBrandLogo(imagePath);*/
	          
      	}
		
	}


	@SuppressWarnings("unchecked")
	public ProductBrandModel getProductBrandById(Long id)throws Exception
    {
   
        ProductBrandModel  productBrandModel =new ProductBrandModel();     
        productBrandModel.setBrandId(id);
        ProductBrandModel result = productBrandDao.queryOne(productBrandModel);
        
        RequestObject reqObj = new RequestObject();
        GetImagePathRequestPO request = new GetImagePathRequestPO();
		request.setRelationCode(result.getBrandCode());
		request.setRelationType(3L);
		reqObj.setRequestParams(request);
		ResponseObject respObj = null;
		List<GetImageResponsePO> response = null;
		
		respObj = imageSearchManager.getImagePath(reqObj);
		logger.info("获取图片信息 =="+(JSONObject.toJSONString(respObj)));
		if(respObj.getCode() == 0)
		{
			response = (List<GetImageResponsePO>) respObj.getData();
			if(response != null && response.size() > 0)
			{
				//加上一个时间随机数，在页面显示时控制缓存
				String imagePath = response.get(0).getImagePath() + "/" + response.get(0).getImageName() + response.get(0).getImageType()+"?date=" + System.currentTimeMillis();
				result.setBrandLogo(imagePath);
			}
		}
        
        if(result != null){
        	long2DateStringFormat(result);
        }
        
        return result;
    }

    
    public long getProductBrandCount(ProductBrandModel productBrandModel)throws Exception
    {
        long result = productBrandDao.countListPage(productBrandModel);
        return result;
    }

    
    public List<ProductBrandModel> getProductBrandListPage(ProductBrandModel productBrandModel, Page page)throws Exception
    {
       
        //设置查询品牌的启用和禁用状态
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(BrandStatusEnum.enable.getInt());
        statusList.add(BrandStatusEnum.disable.getInt());
    	productBrandModel.setStatusList(statusList);
    	
        long count = productBrandDao.countListPage(productBrandModel);
        List<ProductBrandModel> result=null;
        if (count > 0) {
        	page.setTotalCount(count);//设置分页信息
        	result= productBrandDao.queryListPage(productBrandModel, page.getPageNum(), page.getNumPerPage());
        	long2DateList(result);
        	setCountryShowByName(result);
        	wrapBrandPicture(result);
        }
        
        return result;
    }

    /**
     * 调用图片获取服务器接口，查询品牌log图片
     * @param result
     */
    @SuppressWarnings("unchecked")
	private void wrapBrandPicture(List<ProductBrandModel> result) 
    {
		RequestObject reqObj = new RequestObject();
		List<GetImageResponsePO> response = null;
		ProductBrandModel brandModel = null;
		
		//获取品牌编号列表
		List<String> productBrandCodeList = getProductBrandCodeList(result);
		
		/*
		 * 封装调用图片获取服务器接口参数
		 */
		GetImagePathBatchRequestPO batchRequestPo = new GetImagePathBatchRequestPO();
		batchRequestPo.setRelationCodes(productBrandCodeList);
		batchRequestPo.setRelationType(3L);
		reqObj.setRequestParams(batchRequestPo);
		
		ResponseObject respObj = new ResponseObject();
		try {
			respObj = imageSearchManager.getImagePathBatch(reqObj);
			logger.info("获取图片信息 =="+(JSONObject.toJSONString(respObj)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("品牌列表，调用图片获取服务器接口失败,图片信息respObj="+JSONObject.toJSONString(respObj));
		}
		GetImageBatchResponsePO batchResponse = new GetImageBatchResponsePO();
		try {
			batchResponse = (GetImageBatchResponsePO) respObj.getData();
		} catch( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,List<GetImageResponsePO>> map = batchResponse.getImageMap() ;
		if(respObj.getCode() == -1 || map == null || map.size() == 0)
		{
			return;
		}
		
		/*
		 * 遍历结果
		 */
		for(int i = 0 ; i < result.size() ; i++)
		{
			brandModel = result.get(i);
				
			response = (List<GetImageResponsePO>) map.get(brandModel.getBrandCode());
			if(response != null && response.size() > 0)
			{
				//加上一个时间随机数，在页面显示时控制缓存
				String imagePath = response.get(0).getImagePath() + "/" + response.get(0).getImageName() + response.get(0).getImageType()+"?date=" + System.currentTimeMillis();
				brandModel.setBrandLogo(imagePath);
				result.set(i, brandModel);
			}
		}
		
	}

    /**
     * 获取列表中的ProductBrandCode列表，用于图片获取
     * @param result
     * @return
     */
	private List<String> getProductBrandCodeList(List<ProductBrandModel> result) {
		List<String> productBrandCodeList = new ArrayList<String>();
		if(result == null )
		{
			return null;
		}
		else
		{
			for(ProductBrandModel model : result)
			{
				productBrandCodeList.add(model.getBrandCode());
			}
		}
		return productBrandCodeList;
	}


	public List<ProductBrandModel> getEnableProductBrandListPage(ProductBrandModel productBrandModel, Page page)throws Exception
    {
    	productBrandModel.setBrandStatus(BrandStatusEnum.enable.getInt());
    	
        long count = productBrandDao.countListPage(productBrandModel);
        List<ProductBrandModel> result=null;
        if (count > 0) {
        	page.setTotalCount(count);//设置分页信息
        	result= productBrandDao.queryListPage(productBrandModel, page.getPageNum(), page.getNumPerPage());
        }
        
        return result;
    }


	public List<ProductBrandModel> getBrandAllList() throws Exception{
    	List<ProductBrandModel> list= null;
    	list = productBrandDao.getBrandAllList();
    	long2DateList(list);
    	return list;
    }
    
    
    public void deleteById(Integer id)throws Exception
    {
        productBrandDao.delete(id);
    }

   
    
    @Transactional
    public String importdBrand(String filename, byte[] buf ,List<String> titleList,List<String> fieldList,String operator)throws ZhaidouRuntimeException, Exception{
    	List<DBObject> dbList = null; 
    	List<ProductBrandModel> brandList = null;
    	dbList = ExcelUtil.readExcel(filename, new ByteArrayInputStream(buf),titleList,fieldList); //读取excel表格
    	
    	brandList = DBList2BrandList(dbList); //处理 DBObject转成ProductBrandModelz
    	setBrandCountryAbbr(brandList,operator);//设置国家地域编号
    	
    	StringBuffer existStr = new StringBuffer("已存在品牌"); //BrandName已经存在的记录
    	StringBuffer nameNullStr =  new StringBuffer("品牌名称为空");//BrandName品牌名称为空记录
    	StringBuffer retStr = new StringBuffer("导入成功:");
    	int existInt = 0; //标记导入的表单中 , 数据库已经存在该表单记录的个数
    	int nameNullInt = 0; //记录导入的表单，品牌名称为空的记录
    	
    	if(brandList !=null){// TODO 单个插入要改成多个插入
    		for (ProductBrandModel productBrandModel : brandList) {//批量插入数据 
    			
    			if(StringUtils.isEmpty(productBrandModel.getBrandName())){
    				nameNullStr.append("["+productBrandModel.toString()+"]");
    				nameNullInt ++;
    			}else if(checkExistByName(productBrandModel.getBrandName())){
    				existStr.append("["+productBrandModel.getBrandName()+"]");
    				existInt ++;
    			}else{
    				insertBrand(productBrandModel,null);
    			}
			}
    	}
    	
    	if(nameNullInt>0){
    		retStr.append(",").append(nameNullStr);
    		logger.error(nameNullStr.toString());
    	}
    	if(existInt>0){
    		retStr.append(",").append(existStr);
    		logger.error(retStr.toString());
    	}
    	
    	return retStr.toString();
    }
    

	@Override
	public List<ProductBrandPO> exportBrand() throws Exception {
		List<ProductBrandPO> brandPOList = null; 
		List<ProductBrandModel>  brandList = productBrandDao.getBrandAllList();
		List<CountryModel> countryList = countryService.getCountryAllList();
		brandPOList = formatBrandExport(brandList,countryList);
		
		return brandPOList;
	}
    
    
    @Override
	public ProductBrandModel getBrandByCode(String brandCode) throws Exception {
    	ProductBrandModel ProductBrandModel = null;
    	ProductBrandModel = productBrandDao.getBrandByCode(brandCode);
    	long2DateStringFormat(ProductBrandModel);
		return ProductBrandModel;
	}
    
	@Override
	public ProductBrandModel getEnableBrandByCode(String brandCode)
			throws Exception {
		ProductBrandModel ProductBrandModel = null;
    	ProductBrandModel = productBrandDao.getEnableBrandByCode(brandCode);
    	long2DateStringFormat(ProductBrandModel);
		return ProductBrandModel;
	}
    
    
    @Override
   	public ProductBrandModel getBrandByName(String brandName) throws Exception {
       	ProductBrandModel ProductBrandModel = null;
       	ProductBrandModel = productBrandDao.getBrandByName(brandName);
       	long2DateStringFormat(ProductBrandModel);
   		return ProductBrandModel;
   	}
    
    
	@Override
	public ProductBrandModel getEnableBrandByName(String brandName)
			throws Exception {
	 	ProductBrandModel ProductBrandModel = null;
       	ProductBrandModel = productBrandDao.getEnableBrandByName(brandName);
       	long2DateStringFormat(ProductBrandModel);
   		return ProductBrandModel;
	}
    
    
    @Override
    public List<ProductBrandPO> formatBrandExport(List<ProductBrandModel> brandList,List<CountryModel> countryList) throws Exception{
    	
    	List<ProductBrandPO> brandPOList = new ArrayList<ProductBrandPO>();
    	
    	ProductBrandPO brandPO = null;
    	if(!StringUtils.isEmpty(brandList)){
    		for (ProductBrandModel brand : brandList) {
    			brandPO = new ProductBrandPO();
    			brandPO.setBrandId(brand.getBrandId());
    			brandPO.setBrandCode(brand.getBrandCode());
    			brandPO.setBrandName(brand.getBrandName());
    			brandPO.setBrandEname(brand.getBrandEname());
    			brandPO.setBrandInfo(brand.getBrandInfo());
    			brandPO.setBrandStory(brand.getBrandStory());
    			brandPO.setUpdateUser(brand.getUpdateUser());
    			//设置国家显示信息
    			for (CountryModel countryModel : countryList) {
    				
    				if(!StringUtils.isEmpty(brand.getBrandCountry())){
    					if(brand.getBrandCountry().equals(countryModel.getCountryAbbr())){//如果相等则设置国家名称
        					brandPO.setBrandCountryPO(countryModel.getCountryName());
        				}
    				}
    				
				}
    			
    			//转换状态显示文字信息
    			if(BrandStatusEnum.enable.getInt() == brand.getBrandStatus()){
    				brandPO.setBrandStatusPO(BrandStatusEnum.enable.getDesc());
    			}else if(BrandStatusEnum.disable.getInt() == brand.getBrandStatus()){
    				brandPO.setBrandStatusPO(BrandStatusEnum.disable.getDesc());
    			}else if(BrandStatusEnum.delete.getInt() == brand.getBrandStatus()){
    				brandPO.setBrandStatusPO(BrandStatusEnum.delete.getDesc());
    			}
    			
    			//旗舰店认证状态
    			if(StoreCertificateEnum.certified.getInt() == brand.getStoreCertificationType()){
    				brandPO.setStoreCertificationTypePO(StoreCertificateEnum.certified.getDesc());
    			}else if(StoreCertificateEnum.uncertified.getInt() == brand.getStoreCertificationType()){
    				brandPO.setStoreCertificationTypePO(StoreCertificateEnum.uncertified.getDesc());
    			}
    			brandPOList.add(brandPO);
			}
    	}
    	
    	return brandPOList;
    }
    
    
    
    /**
     * 插入数据，在插入数据时填充默认的数据
     * 并且做缓存处理
     * @param productBrandModel
     * @throws Exception
     * @return null
     * */
    public void insertBrand(ProductBrandModel productBrandModel,MultipartFile file) throws Exception{
    	fillBrandModelData(productBrandModel);//填充数据
    	uploadImageToImgServer(file,productBrandModel.getBrandCode());//上传图片到图片服务器
        productBrandDao.insert(productBrandModel);
        ProductBrandModel brandModel = getProductBrandById(productBrandModel.getBrandId());
        //存到缓存中
        this.getCachedClient().cacheObject("brand_"+brandModel.getBrandCode(), brandModel, cachePcTime);
    }
    
    
    
    
    /**
     * 填充后台的数据:brandCode,creator,updateUser,
     * createTime,updateTime,createTime
     * @param ProductBrandModel
     * @return null
     *  
     *  
     * */
    public void fillBrandModelData(ProductBrandModel productBrandModel){
    	
    	/**设置默认值*/
    	if(productBrandModel != null)
    	{
    		String countryCode = productBrandModel.getBrandCountry();
    		if(countryCode != null){
    			setBrandCode(productBrandModel,countryCode);
    		}else{
    			setBrandCode(productBrandModel,"CN");//设置默认属于中国编码
    		}
        
    		if(StringUtils.isEmpty(productBrandModel.getStoreCertificationType())){//设置默认
    			productBrandModel.setStoreCertificationType(StoreCertificateEnum.uncertified.getInt());
    		}
    		
    		if(StringUtils.isEmpty(productBrandModel.getBrandStatus())){//设置默认
    			productBrandModel.setBrandStatus(BrandStatusEnum.enable.getInt());
    		}
    		
            productBrandModel.setUpdateTime(new Date().getTime());
            productBrandModel.setCreateTime(new Date().getTime());
    		
    	}
    }
    
    
    /**
     * 设置品牌国家地域编号
     * @param operator 操作者
     * @param brandList 品牌集合
     * @throws Exception 
     * */
    public void setBrandCountryAbbr(List<ProductBrandModel> brandList,String operator) throws ZhaidouRuntimeException,Exception{
    	
    	List<CountryModel> countryList = countryService.getCountryAllList();
    	if(countryList ==null){
    		 logger.info("国家列表为空");
       		 throw new ZhaidouRuntimeException("国家列表为空");
    	}
    	
    	for (ProductBrandModel brandModel : brandList) {
    		String countryAbbr = getCountryAbbrByCountryName(brandModel.getBrandCountry(),countryList);
    		brandModel.setBrandCountry(countryAbbr);
    		brandModel.setCreator(operator);
    		brandModel.setUpdateUser(operator);
		}
    	
    }
    
    
    /**
     * 通过countryName 获取CountryAbbr(国家地域编号)
     * 
     * @param contryName 国家名称
     * @param list 国家集合
     * @return String 
     * */
    public String getCountryAbbrByCountryName(String contryName,List<CountryModel> list){
    	String countryAbbr = null;
    	for (CountryModel countryModel : list) {
			if(contryName.equals(countryModel.getCountryName())){
				countryAbbr = countryModel.getCountryAbbr();
				return countryAbbr;
			}
		}
    	return countryAbbr;
    }
    
    
    /**
     * List<DBObject> 转换成List<ProductBrandModel>
     * */
    public List<ProductBrandModel> DBList2BrandList(List<DBObject> dbObjList){
    	List<ProductBrandModel> brandList = new ArrayList<ProductBrandModel>();
    	ProductBrandModel brand = null;
    	for(DBObject dbObj:dbObjList){
    		brand =  DB2BrandModel(dbObj);
    		if(brand != null){
    			brandList.add(brand);
    		}
    	} 
    	return brandList;
    }
    
    
    /**
     * DBObject 转换 ProductBrandModel
     * */
    @SuppressWarnings("null")
	public ProductBrandModel DB2BrandModel(DBObject dbObj){
    	ProductBrandModel brand = null;
    	if(dbObj != null){
    		brand = new ProductBrandModel();
    		Set<String> keySet = dbObj.keySet();
    		Iterator<String> it =  keySet.iterator();
    		 
    		while(it.hasNext()){
    			String key = it.next();
    			//对brandId ,brandStatus ,storeCertificationType 做处理，从String 转成 Integer 类型,因为反射不能将Object 类型赋值给Integer类型
    			Object value = null;
    			if("brandId".equals(key) || "brandStatus".equals(key) || "storeCertificationType".equals(key) ){
    				Object tmpValue = dbObj.get(key);
    				if(tmpValue != null){
    					Integer intVal = Integer.valueOf(tmpValue.toString());
    					ReflectUtil.setVal(brand, key, intVal);
    				}
    			}else{
    				value = dbObj.get(key);
    				ReflectUtil.setVal(brand, key, value);
    			}
    		}
    	}
    	return brand;
    }
    
    
    /**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(ProductBrandModel productBrandModel){
    	
    	if(productBrandModel != null)
    	{
    		Long createTime = productBrandModel.getCreateTime();
        	Long updateTime = productBrandModel.getUpdateTime();
        	
        	if(createTime !=null && !"".equals(createTime)){
        		
        		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd HH:mm:ss");
        		productBrandModel.setCreateTimes(createTimes);
        		
        	}
        	
        	if(updateTime !=null && !"".equals(updateTime)){
        		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd HH:mm:ss");
        		productBrandModel.setUpdateTimes(updateTimes);
        	}
    		
    	}
    	
    }

    
    /**
     * 统一处long转到date
     * */
    public void long2DateList(List<ProductBrandModel> brandList){
    	if(brandList != null){
    		for (ProductBrandModel productBrandModel : brandList) {
    			long2DateStringFormat(productBrandModel);
			}
    	}
    }

    
    /**
     * 设置品牌的编号,组装品牌编码格式
     * */
    public void setBrandCode(ProductBrandModel productBrandModel,String country){
    	Long maxId = productBrandDao.getMaxBrandId();
    	
    	if(StringUtils.isEmpty(maxId)){
    		maxId = 0L;
    	}
    	Long nextId = maxId+1;
    	String brandCode = country;
    	if( 0<nextId && nextId<10 ){
    		brandCode +=  "0000" + nextId;
    	}else if(10<=nextId  &&  nextId<100){
    		brandCode +=  "000" + nextId;
    	}else if(100<=nextId  &&  nextId<1000){
    		brandCode +=  "00" + nextId;
    	}else if(1000<=nextId  &&  nextId<10000){
    		brandCode +=  "0" + nextId;
    	}else{
    		brandCode += nextId;
    	}
    	
    	productBrandModel.setBrandCode(brandCode);
    }

    
    /**
     * 设置品牌国家以国家名称显示
     * @throws Exception 
     * */
    private void setCountryShowByName(List<ProductBrandModel> brandList) throws Exception {
    	List<CountryModel> countryList = null;
    	boolean isHasCountryAbbr = false;//判断是否有国家地域编号,如果有国家地域编号，则给相应的国家名称，否则国家名称为“其他”
    	
    	if(brandList !=null){
    		countryList = countryService.getCountryAllList();
    		for(ProductBrandModel brand : brandList){
    			for (CountryModel country:countryList) {//遍历国家列表，找到国家地域编号对应的国家名称
    				
    				String countryName = brand.getBrandCountry();
    				if(!StringUtils.isEmpty(countryName)){
    					if(countryName.equals(country.getCountryAbbr())){//有国家地域编号，则给相应的国家名称
        					brand.setBrandCountry(country.getCountryName());
        					isHasCountryAbbr = true;
        				}
    				}
				}
    			
    			if(!isHasCountryAbbr){//没有国家地域编号 则国家名称为“其他”
    				brand.setBrandCountry("其他");
    			}
    			
    		}
		}
		
	}
    
    
	@Override
	public boolean checkExistByName(String brandName) throws Exception {
		ProductBrandModel brandModel = productBrandDao.getBrandByName(brandName);
		if(brandModel != null){
			return true;
		}
		return false;
	}



}
