/*
 * 文 件 名:  ProductDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductDao;
import com.zhaidou.product.model.ProductModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("productDao")
public class ProductDaoImpl extends BaseDao implements ProductDao {
	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<ProductModel> getListByProductCodes(List<String> productCodeList) throws DaoException {
		try {
			return getSqlSession()
					.selectList(getNameSpace() + ".queryListByProductCode", productCodeList);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}
	
	/**
	 * 根据 品牌编码 查询商品
	 */
    @Override
    public Integer getProductByBrandCode(ProductModel productModel) throws DaoException {
        try {
            return getSqlSession().selectOne(getNameSpace() + ".getProductByBrandCode", productModel);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

	@Override
	public ProductModel getProductByProductCode(ProductModel productModel)
			throws DaoException {
		 try {
	            return (ProductModel)getSqlSession().selectOne(getNameSpace() + ".getProductByProductCode", productModel);
	        } catch (Exception e) {
	            throw new DaoException(e);
	        }
	}

	@Override
	public void createView(ProductModel productModel) throws DaoException {
		 try {
	            getSqlSession().update(getNameSpace() + ".creatView", productModel);
	        } catch (Exception e) {
	            throw new DaoException(e);
	        }
	}

    @Override
    public void updateByCode(ProductModel productModel) throws DaoException {
        
        try {
            getSqlSession().update(getNameSpace() + ".updateByCode", productModel);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
