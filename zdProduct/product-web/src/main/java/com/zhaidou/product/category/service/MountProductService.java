/*
 * 文 件 名:  SalecategoryProductRelationService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.MountProduct;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MountProductService {
	public void insert(MountProduct salecategoryProductRelationModel) throws ZhaidouRuntimeException;

//	public void batchInsertOrUpdate(List<MountProduct> relationList, CategoryPO saleCategory);
	
	public void update(MountProduct salecategoryProductRelationModel) throws ZhaidouRuntimeException;

	public MountProduct getById(MountProduct salecategoryProductRelationModel) throws ZhaidouRuntimeException;

	public long getCount(MountProduct salecategoryProductRelationModel) throws ZhaidouRuntimeException;

	public List<MountProduct> getList(MountProduct salecategoryProductRelationModel, Page page) throws ZhaidouRuntimeException;

	public void deleteById(MountProduct salecategoryProductRelationModel) throws Exception;

	/**
	 * @Description 处理要上传挂载的产品excel
	 * @param filename
	 * @param buf
	 * @param headerList
	 * @param fieldList
	 * @param saleCategory
	 * @return 
	 * @throws IOException 
	 * @throws Exception
	 */
	public ResponseObject processMpExcel(String filename, InputStream ins, CategoryPO saleCategory) throws ZhaidouRuntimeException, IOException;

	
	/**
	 * @Description 处理批量运营分类挂载
	 * @param filename
	 * @param InputStream excel 流
	 * @param operator 操作者
	 * 
	 * @return ResponseObject
	 * @throws IOException 
	 * @throws Exception
	 */
	public ResponseObject batchCategoryImport(String filename, InputStream ins,String operator) throws ZhaidouRuntimeException, IOException;
	
	public List<MountProduct> getProductsByCategoryId(MountProduct relationModel, Page page) throws ZhaidouRuntimeException;

	public void updateOrBatchInsert(List<MountProduct> relationList, String categoryCode) throws ZhaidouRuntimeException;
	
	/**
	 * @Description:  根据运营分类ID加载其下所有opt_type=1的商品
	 * @param model
	 * @throws Exception
	 * @return List<MountProduct>
	 */
	public List<MountProduct> selectOpt1ByCategoryId(MountProduct model) throws Exception;

	
	/**
	 * @Description:  根据运营分类ID加载其下所有的商品，用于导出excel
	 * @param categoryId
	 * @throws Exception
	 * @return List<MountProduct>
	 */
	public List<MountProduct> queryExprotProdsByCategoryId(Long categoryId) throws Exception;
	
	/**
	 * 判断在此分类下是否挂在商品
	 * @param catgoryCode
	 * @return
	 * @throws ZhaidouRuntimeException
	 */
	public boolean hasProductInCategory(List<String> catgoryCode) throws ZhaidouRuntimeException;

	public boolean hasMountProduct(Long categoryId) throws ZhaidouRuntimeException;
	
	
	/**
	 * 导出excel 表格
	 * @param mount List 集合
	 * @param titles 表格标题
	 * @param fields 模型对象字段
	 * @return Workbook
	 * */
	public Workbook exportExcel(List<MountProduct> mountList,List<String> titles, List<String> fields) throws Exception;

	
	/**
	 * 通过relationId 获取运营分类下商品的排序值
	 * @param relationId 运营分类 -- 商品关联 的 id
	 * @return MountProduct
	 * */
	public MountProduct getMpOrderValue(Long relationId)throws Exception;

	
	/**
	 * 通过relationId 更新运营分类下商品的排序值
	 * @param relationId 运营分类 -- 商品关联 的 id
	 * @return MountProduct
	 * */
	public int updateMpOrderValueByRelId(MountProduct mpModel)throws Exception;
	
	
	
}
