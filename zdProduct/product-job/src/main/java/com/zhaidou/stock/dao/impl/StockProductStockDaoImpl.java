package com.zhaidou.stock.dao.impl;

import com.google.common.collect.Maps;
import com.zhaidou.common.dao.Base2Dao;
import com.zhaidou.stock.dao.StockProductStockDao;
import com.zhaidou.stock.model.StockProductStock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 2015-12-7
 * Time: 上午10:25
 * desc: 因此表名与product_web的t_product_stock一样,故添加stock前缀以便区别，
 *       继承数据源2
 */

@Service("stockProductStockDao")
public class StockProductStockDaoImpl extends Base2Dao implements StockProductStockDao {

    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<StockProductStock> queryBySkuCodeList(List<String> skuCodes) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("skuCodes", skuCodes);
        List<StockProductStock> resultModel = getSqlSession().selectList(getNameSpace() + ".queryBySkuCodes",
                map);

        return resultModel;
    }
}
