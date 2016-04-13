/*
 * 文 件 名:  ProductStockDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductStockDao;
import com.zhaidou.product.model.ProductStockQueueModel;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productStockDao")
public class ProductStockDaoImpl extends BaseDao  implements ProductStockDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
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
	public Integer insertQueue(ProductStockQueueModel productStockQueueModel)
			throws Exception {
		try {
			return (Integer) getSqlSession().insert(getNameSpace() + ".insertQueue", productStockQueueModel);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ProductStockQueueModel> queryQueueListPage(ProductStockQueueModel productStockQueueModel, int pageNo,
			int pageSize) throws Exception {
		try {
			List<ProductStockQueueModel> list = null;
			if (pageSize != Integer.MAX_VALUE) {
				list = getSqlSession()
						.selectList(getNameSpace() + ".queryQueueListPage", productStockQueueModel, new RowBounds(pageNo, pageSize));
			} else {
				list = getSqlSession().selectList(getNameSpace() + ".queryQueueListPage", productStockQueueModel);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteQueue(ProductStockQueueModel productStockQueueModel) {
		try {
			getSqlSession().selectList(getNameSpace() + ".deleteQueue",productStockQueueModel);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
}
