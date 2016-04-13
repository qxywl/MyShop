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
package com.zhaidou.product.info.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductDao;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;

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
    public ProductModel getProductByCode(String productCode) throws DaoException {
        try {
            return getSqlSession().selectOne(getNameSpace() + ".getProductByCode", productCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

	@Override
	public Integer getProductCode(ProductModel productModel) throws DaoException {
		productModel.setCreateTime(new Date().getTime() / 1000);
		return (Integer) getSqlSession().insert(getNameSpace() + ".getProductCode",productModel);
	}

    @Override
    public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel, int pageNo,
            int pageSize) throws Exception {
        try {
            return getSqlSession()
                    .selectList(getNameSpace() + ".exportPrdouct", productSkuExportModel, new RowBounds(pageNo, pageSize));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <T> Long exportPrdouctCount(T t) throws Exception {
        try {
            Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".exportPrdouctCount", t);
            return count;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

	@Override
	public long countListPageAndMarkUpRate(ProductModel productModel) throws Exception {
		try {
			Long count = (Long) getSqlSession().selectOne(
					getNameSpace() + ".countListPageAndMarkUpRate", productModel);
			return count;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<ProductModel> queryListPageAndMarkUpRate(
			ProductModel productModel, int pageNo, int pageSize) throws Exception {
		 try {
	            List<ProductModel> list = null;
	            if (pageSize != Integer.MAX_VALUE) {
	                list = getSqlSession().selectList(getNameSpace() + ".queryListPageAndMarkUpRate", productModel, new RowBounds(pageNo, pageSize));
	            } else {
	                list = getSqlSession().selectList(getNameSpace() + ".queryListPageAndMarkUpRate", productModel);
	            }
	            return list;
	        } catch (Exception e) {
	            throw new Exception(e);
	        }
	}
}
