/*
 * 文 件 名:  SalecategoryProductRelationDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.MountProductDao;
import com.zhaidou.product.model.MountProductModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-09]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("mountProductDao")
public class MountProductDaoImpl extends BaseDao  implements MountProductDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

	@Override
	public boolean batchInsert(List<MountProductModel> list) throws DaoException{
		Map<String,List<MountProductModel>> map = new HashMap<String,List<MountProductModel>>();
		map.put("list", list);
		try{
			getSqlSession().insert(getNameSpace() + ".batchInsert" , map);
		}catch(Exception e){
			throw new DaoException("产品挂载批量插入失败：[MountProduct]", e);
		}
		return true;
	}

	@Override
	public List<MountProductModel> queryListByCategoryCode(MountProductModel mountProduct) throws DaoException{
		try{
			return getSqlSession().selectList(getNameSpace() + ".queryListByCategoryCode", mountProduct);
		}catch(Exception e){
			throw new DaoException("根据categoryCode获取产品挂载关系列表失败", e);
		}
	}

	@Override
	public List<MountProductModel> selectOpt1ByCategoryId(MountProductModel model)
			throws DaoException {
		try{
			return getSqlSession().selectList(getNameSpace() + ".selectOpt1ByCategoryId", model);
		}catch(Exception e){
			throw new DaoException("根据运营分类ID加载其下所有opt_type=1的商品,失败", e);
		}
	}

}
