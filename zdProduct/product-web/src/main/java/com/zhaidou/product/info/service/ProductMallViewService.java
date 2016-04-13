package com.zhaidou.product.info.service;

import java.util.List;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;

public interface ProductMallViewService{
	/**
	 * 根据基础分类Code查询挂在在该分类下的有效商品
	 * 
	 * 有效性： 状态status=2 && (上架 or (下架 and 显示) )
	 * @param categoryCode
	 * @return
	 */
	long countByCatCode(List<String> categoryCodeList) throws ZhaidouRuntimeException;
}
