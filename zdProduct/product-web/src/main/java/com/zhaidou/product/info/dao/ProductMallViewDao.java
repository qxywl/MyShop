package com.zhaidou.product.info.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;

public interface ProductMallViewDao extends IDao{
	long countByCatCodes(List<String> categoryCodeList) throws DaoException;
}
