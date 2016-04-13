package com.zhaidou.product.category.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.zhaidou.common.util.Response;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.category.model.CategoryPO;

/**
 * @Title: CategoryService.java 
 *
 * @Package com.teshehui.product.category.service 
 *
 * @Description:   分类Service[运营分类、基础分类]
 *
 * @author lvshuding 
 *
 * @date 2015年3月25日 上午10:29:18 
 *
 * @version V1.0
 */
public interface CategoryService {

	/**
	 * @Description: 添加
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response insert(CategoryPO po,Category_Type type);
	
	/**
	 * @Description: 修改
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response update(CategoryPO po,Category_Type type);
	
	/**
	 * @Description: 显示分类
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response show(CategoryPO po, Category_Type saleCategory);
	
	/**
	 * @Description: 批量显示
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response showBatch(CategoryPO po, String idsStr, Category_Type type);
	
	/**
	 * @Description: 隐藏分类
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response hidden(CategoryPO po,Category_Type type);
	
	/**
	 * @Description: 批量隐藏
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response hiddenBatch(CategoryPO po, String idsStr, Category_Type type);
	
	
	/**
	 * @Description: 删除
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response delete(CategoryPO po,Category_Type type);
	
	/**
	 * @Description: 批量删除
	 * @param po
	 * @param type
	 * return Response
	 */
	public Response deleteBatch(CategoryPO po,String ids,Category_Type type);
	
	/**
	 * @Description: 查询所有分类数据
	 * return List<CategoryPO>
	 */
	public List<CategoryPO> queryAll(Category_Type type);
	
	/**
	 * @Description: 根据编号或ID查询分类
	 * @param po 
	 * return CategoryPO
	 */
	public CategoryPO queryOne(CategoryPO po,Category_Type type);

	/**
	 * @Description: 根据父分类编号查询直接子分类列表[对外接口]
	 * 
	 * @param parent   父分类对象【如果父分类对象中categoryCode==null，返回所有一级分类】
	 * 
	 * @param type     数据源类型；BASE_CATEGORY：基础分类；SALE_CATEGORY:运营分类
	 * 
	 * return Response  返回对象 ，model类型为List<CategoryPO> ,如果查询成功 status=true,code=200; 如果查询失败 status=false, code=404
	 */
	public Response queryDirectSonListByParentCode(CategoryPO parent,Category_Type type);
	
	/**
	 * @Description: 根据基础分类编号查询颜色和尺码的展示名称[对外接口]
	 * @param code   基础分类编号
	 * return Response  返回对象 ，model类型为CategoryPO ,如果查询成功 status=true,code=200; 如果查询失败 status=false, code=404
	 */
	public Response queryBaseCategoryColorAndSizeByCode(String code);

	public Map<String, Long> getListByCategoryCodes(List<String> categoryCodeList);

	List<String> getListByProductCode(String productCode);
	
	/**
	 * @Description: 判断一批分类ID中是否存在一级分类 
	 * @param ids    多个ID之间用逗号分隔
	 * @return boolean
	 */
	public boolean checkExistLevel1ByIds(String ids,Category_Type type);
	
	/**
	 * @Description:根据分类名称和当前分类的父分类ID查找对象
	 * @param po
	 * @param type
	 * @return CategoryPO
	 */
	public CategoryPO selectByParentIdAndCategoryName(CategoryPO po,Category_Type type) ;

	public String buildCurrentCode(CategoryPO po, Category_Type type) throws Exception;

	public boolean isLeafNode(Long categoryId);

	public boolean isLeafNode(String categoryCode);
	/**
	 * 根据id列表查询运营分类code列表
	 * @param ids
	 * @return
	 * @throws ZhaidouRuntimeException
	 * @throws NumberFormatException
	 */
	public List<String> queryCodeListByIds(String ids) throws ZhaidouRuntimeException, NumberFormatException;

	/**
	 * 根据id列表查询基础分类code列表
	 * @param ids
	 * @return
	 * @throws ZhaidouRuntimeException
	 * @throws NumberFormatException
	 */
	public List<String> queryBaseCodeListByIds(String ids) throws ZhaidouRuntimeException, NumberFormatException;
	
	
	
	/**
     * @Description: 查询等级分类下的所有分类,如果等级分类为空，则返回所有
     * @param level 等级分类
     * @param type 类型
     * @return List<CategoryModel>
     * */
    public List<CategoryPO> selectAllByLevel(Integer level,Category_Type type) throws Exception;

    
    /**
	 * @Description:通过运营分类code，查找当前节点和节点下的所有子分类,如果不传categoryCode,则返回所有
	 * @param categoryCode 
	 * @param type 基础分类类型或者运营分类类型
	 * @throws Exception
	 * return List<CategoryModel>
	 * */
    public List<CategoryPO> selectParentAndSonByCode(String categoryCode,Category_Type type) throws Exception;
  
    
    /**
     * 导出运营分类
     * */
	public Workbook writeExcel(List<String> titleList, 
			List<CategoryPO> catePOList);
}
