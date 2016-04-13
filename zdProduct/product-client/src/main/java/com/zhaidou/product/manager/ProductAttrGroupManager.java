package com.zhaidou.product.manager;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;


public interface ProductAttrGroupManager {
	
	  /**
  	 * 通过分类来获取分类下的属性,主要给商品用
  	 * 
  	 * @param reqObj 分类编码
  	 * @return ResponseObject 
  	 */
     public ResponseObject getProductAttrValByCateCode(RequestObject reqObj);
   

}
