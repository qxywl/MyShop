/*
 * 文 件 名:  ProductSkuDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductSkuDao;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productSkuDao")
public class ProductSkuDaoImpl extends BaseDao  implements ProductSkuDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public List<ProductSkuModel> getSkuByProductId(ProductSkuModel productSkuModel) throws DaoException {
        try {
            List<ProductSkuModel> list = null;
           
            list = getSqlSession().selectList(getNameSpace() + ".getSkuByProductId", productSkuModel);
            
            return list;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    
    /**
     * 批量删除
     *
     * @param listStr
     * @throws Exception
     */
    @Override
    public void deleteByIds(Map<String,Object> map) {
       
        try {
           
            getSqlSession().selectList(getNameSpace() + ".deleteByIds", map);
            
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ProductSkuModel> getProductSkuListByProductId(List<Long> productIdList) throws DaoException {
        List<ProductSkuModel> list = null;
        
        list = getSqlSession().selectList(getNameSpace() + ".getProductSkuListByProductId", productIdList);
        
        return list;
    }
    
    /**
     * 批量逻辑删除 修改 is_available 状态
     *
     * @param listStr
     * @throws Exception
     */
    @Override
    public Integer updateByIds(Map<String, Object> map) throws DaoException {
        try {
            return getSqlSession().update(getNameSpace() + ".updateByIds", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    
    /**
     * sku导出excel
     */
    @Override
    public List<ProductSkuExportModel> getProductSkuExportModel(ProductSkuExportModel productSkuExportModel,int pageNo, int pageSize) throws Exception {
        try {
            List<ProductSkuExportModel> list = null;
            list = getSqlSession().selectList(getNameSpace() + ".queryProductSkuExportModel", productSkuExportModel, new RowBounds(pageNo, pageSize));
            
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /**
     * sku导出excel
     */
    public <T> Long queryProductSkuExportCount(T t) throws Exception {
        try {
            Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".queryProductSkuExportCount", t);
            return count;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<ProductSkuModel> getDelSku(Long productId) throws Exception {
        List<ProductSkuModel> list = null;
        list = getSqlSession().selectList(getNameSpace() + ".getDelSku", productId);
        
        return list;
    }
}
