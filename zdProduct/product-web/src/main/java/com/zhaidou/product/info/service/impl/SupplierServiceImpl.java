package com.zhaidou.product.info.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mongodb.DBObject;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.framework.service.impl.BaseServiceImpl;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.brand.service.impl.ProductBrandServiceImpl;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.info.dao.SupplierDao;
import com.zhaidou.product.info.enumration.SupplierEnum;
import com.zhaidou.product.info.model.SupplierModel;
import com.zhaidou.product.info.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl extends BaseServiceImpl implements SupplierService{
	
	private static final Log logger = LogFactory.getLog(ProductBrandServiceImpl.class);

	@Value("#{propertyConfigurerForProject2['cache_pc_time']}")
    private Integer cachePcTime;
	
    @Resource
    private SupplierDao supplierDao;

    @Override
    public SupplierModel getSupplier(SupplierModel supplierModel) throws Exception {
        return supplierDao.queryOne(supplierModel);
    }

    /**
     * 获取供货商列表(分页)
     */
    @Override
    public List<SupplierModel> getSupplier(SupplierModel supplierModel,Page page) throws Exception {
        List<SupplierModel> result= null;
        	if (page != null) { //分页
        		long count = supplierDao.countListPage(supplierModel);
        		if (count > 0) {
        			page.setTotalCount(count);//设置分页信息
        			result= supplierDao.queryListPage(supplierModel, page.getPageNum(), page.getNumPerPage());
        			long2DateList(result);
        		}
			}else{//获取所有
				result = supplierDao.queryListPage(supplierModel,1, Integer.MAX_VALUE);
			}
		return result;
    }
    
    /**
     * 导入excel数据
     */
    @Transactional
	public String importdSupplier(String filename, byte[] buf,	List<String> titleList, List<String> fieldList)
			throws Exception {
		List<DBObject> dbList = null; 
    	List<SupplierModel> supplierList = null;
    	dbList = ExcelUtil.readExcel(filename, new ByteArrayInputStream(buf),titleList,fieldList); //读取excel表格
    	
    	supplierList = DBList2SupplierList(dbList); //处理 DBObject转成ProductBrandModelz
    	StringBuffer existStr = new StringBuffer("已存在代码"); //BrandName已经存在的记录
    	StringBuffer nameNullStr =  new StringBuffer("供应商名称为空");//BrandName品牌名称为空记录
    	StringBuffer retStr = new StringBuffer("导入成功:");
    	int existInt = 0; //标记导入的表单中 , 数据库已经存在该表单记录的个数
    	int nameNullInt = 0; //记录导入的表单，品牌名称为空的记录
    	
    	if(supplierList !=null){
    		for (SupplierModel supplierModel : supplierList) {//批量插入数据 
    			if(StringUtils.isEmpty(supplierModel.getName())){
    				nameNullStr.append("["+supplierModel.toString()+"]");
    				nameNullInt ++;
    			}else if(checkExistByCode(supplierModel.getSupplierCode())){
    				if(supplierModel.getSupplierCode() != null){
    					existStr.append("["+supplierModel.getSupplierCode()+"]");	
    				}
    				existInt ++;
    			}else{
    				insertSupplier(supplierModel);
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
    
    /**
     * List<DBObject> 转换成List<SupplierModel>
     * */
    public List<SupplierModel> DBList2SupplierList(List<DBObject> dbObjList){
    	List<SupplierModel> supplierList = new ArrayList<SupplierModel>();
    	SupplierModel supplier = null;
    	for(DBObject dbObj:dbObjList){
    		supplier =  DB2BrandModel(dbObj);
    		if(supplier != null){
    			supplierList.add(supplier);
    		}
    	} 
    	return supplierList;
    }
    
    
    /**
     * DBObject 转换 SupplierModel
     * */
	public SupplierModel DB2BrandModel(DBObject dbObj){
    	SupplierModel supplier = null;
    	if(dbObj != null){
    		supplier = new SupplierModel();
    		Set<String> keySet = dbObj.keySet();
    		Iterator<String> it =  keySet.iterator();
    		 
    		while(it.hasNext()){
    			String key = it.next();
    			//对brandId ,brandStatus ,storeCertificationType 做处理，从String 转成 Integer 类型,因为反射不能将Object 类型赋值给Integer类型
    			Object value = null;
    			if("supplierCode".equals(key) || "userId".equals(key) || "departmentType".equals(key) || "status".equals(key) || "isUpdated".equals(key)  || "shopId".equals(key) || 
    				"merchantsDirectorId".equals(key) || "isDeleted".equals(key) ){
    				Object tmpValue = dbObj.get(key);
    				if(tmpValue != null && !"".equals(tmpValue)){
    					Integer intVal = Integer.valueOf(tmpValue.toString());
    					ReflectUtil.setVal(supplier, key, intVal);
    				}
    			}else if("markUpRate".equals(key)){
    				Object tmpValue = dbObj.get(key);
    				if(tmpValue != null){
    					Double intVal = Double.valueOf(tmpValue.toString());
    					ReflectUtil.setVal(supplier, key, intVal);
    				}
    			}else{
    				value = dbObj.get(key);
    				ReflectUtil.setVal(supplier, key, value);
    			}
    		}
    	}
    	return supplier;
    }
	
	/**
	 * 保存数据
	 * @param supplierModel
	 */
    public void insertSupplier(SupplierModel supplierModel)throws Exception{
    	if(supplierModel != null){
    		supplierModel.setCreateTime(new Date().getTime());
    		supplierModel.setUpdateTime(new Date().getTime());
    		supplierModel.setStatus(SupplierEnum.activation.getInt());
    		supplierDao.insert(supplierModel);
    		SupplierModel cacheSupplier = getSupplierById(supplierModel.getId());
    	        //存到缓存中
	        this.getCachedClient().cacheObject("supplier_"+cacheSupplier.getSupplierCode(), cacheSupplier, cachePcTime);
    	}
    }
    
    /**
     * 根据id获取供应商对象
     * @param id
     * @return
     * @throws Exception
     */
    public SupplierModel getSupplierById(Long id) throws Exception{
    	SupplierModel supplierModel = new SupplierModel();
    	supplierModel.setId(id);
    	SupplierModel result = supplierDao.queryOne(supplierModel);
    	if(result != null){
    		long2DateStringFormat(result);
    	}
    	return result;
    }
    
    /**
     * 供应商代码是否存在
     * @param supplierCode
     * @return
     */
    public boolean checkExistByCode(Integer supplierCode){
    	SupplierModel supplierModel = null;
    	if(supplierCode != null){
    		supplierModel = supplierDao.getSupplierByCode(supplierCode);
    		if(supplierModel != null){
    			return true;
    		}
    	}else{
    		return true;
    	}
    	return false;
    }
    
    
    
    
    /**
     * 统一处long转到date
     * */
    public void long2DateList(List<SupplierModel> supplierList){
    	if(supplierList != null){
    		for (SupplierModel supplierModel : supplierList) {
    			long2DateStringFormat(supplierModel);
			}
    	}
    }
    
    /**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(SupplierModel supplierModel){
    	
    	if(supplierModel != null)
    	{
    		Long createTime = supplierModel.getCreateTime();
        	Long updateTime = supplierModel.getUpdateTime();
        	
        	if(createTime !=null && !"".equals(createTime)){
        		
        		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd HH:mm:ss");
        		supplierModel.setCreateTimes(createTimes);
        		
        	}
        	
        	if(updateTime !=null && !"".equals(updateTime)){
        		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd HH:mm:ss");
        		supplierModel.setUpdateTimes(updateTimes);
        	}
    		
    	}
    	
    }
}
