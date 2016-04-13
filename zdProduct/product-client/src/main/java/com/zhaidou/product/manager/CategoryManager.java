
package com.zhaidou.product.manager;


import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;

/**
 * 
 * @Title: CategoryManager.java 
 *
 * @Package com.teshehui.product.manager 
 *
 * @Description:   分类接口
 *
 * @author lvshuding 
 *
 * @date 2015年5月6日 上午11:07:34 
 *
 * @version V1.0
 */

public interface CategoryManager {
    
   /**
	* @Description: 根据父分类编号查询直接子分类列表[对外接口]
	* 
    * @param requestObj  请求对象 ，其中属性requestParams类型为CategoryPO,父分类对象【如果父分类对象中categoryCode==null，返回所有一级分类】
    * 
    * @return ResponseObject 返回对象 ，属性data类型为List<CategoryPO> ,如果查询成功 code=0; 如果查询失败 code=-1,msg为失败原因
    */
    @SuppressWarnings("rawtypes")
	public ResponseObject queryDirectSonListByParentCode(RequestObject requestObj) ;
    
    /**
	* @Description: 根据基础分类名称，查询分类对象[对外接口]
	* 
    * @param requestObj  请求对象 ，其中属性requestParams类型为String,为分类名称
    * 
    * @return ResponseObject 返回对象 ，属性data类型为List<CategoryPO> ,如果查询成功 code=0; 如果查询失败 code=-1,msg为失败原因
    */
    @SuppressWarnings("rawtypes")
	public ResponseObject queryCategoryByName(RequestObject requestObj) ;
    
    /**
     * @Description: 根据基础分类编号，查询分类对象[对外接口]
     * 
     * @param requestObj  请求对象 ，其中属性requestParams类型为String,为分类名称
     * 
     * @return ResponseObject 返回对象 ，属性data类型为List<CategoryPO> ,如果查询成功 code=0; 如果查询失败 code=-1,msg为失败原因
     */
    @SuppressWarnings("rawtypes")
    public ResponseObject queryCategoryByCode(RequestObject requestObj) ;
    
    
    /**
     * @Description: 查询对应的等级所有的分类,如果等级分类为空，则返回所有分类
     * 
     * @param requestObj  请求对象 ，其中属性requestParams类型为Integer,为分类等级
     * 
     * @return ResponseObject 返回对象 ，属性data类型为List<CategoryPO> ,如果查询成功 code=0; 如果查询失败 code=-1,msg为失败原因
     * */
    @SuppressWarnings("rawtypes")
    public ResponseObject queryCategoryAllByLevel(RequestObject requestObj) ;
}
