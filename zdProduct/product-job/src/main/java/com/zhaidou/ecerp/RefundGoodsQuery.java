package com.zhaidou.ecerp;

import com.zhaidou.ecerp.params.EcErpQuery;
import org.dom4j.Document;
import org.dom4j.Node;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 退货单查询
 */
public class RefundGoodsQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundGoodsQuery.class);

    /**
     * 查询退货单入库查询
     * @Param condition 查询条件
     */
    public static Document findRefundGoods(String condition) {
        EcErpQuery purchaseStockQuery = new EcErpQuery();
        purchaseStockQuery.setPage_no("1");
        purchaseStockQuery.setPage_size("10000000");
        purchaseStockQuery.setCondition(condition);

        Document doc = EcErpUtils.getDocument(purchaseStockQuery, EcErpMethodEnum.THD_GET.getMethod());
        LOGGER.info("查询退货入库单返回结果 :" + doc != null ? doc.asXML() : "null");
        return doc;

    }


}
