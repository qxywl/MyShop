package com.zhaidou.product.info.util;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhaidou.product.info.enumration.EcErpMethodEnum;


/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 采购单查询
 */
public class PurchaseQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseQuery.class);

    /**
     * 查询采购入库单
     * @Param condition 查询条件
     */
    public static Document findPurchasePutInStorage(String condition) {
        EcErpQuery purchaseStockQuery = new EcErpQuery();
        purchaseStockQuery.setPage_no("1");
        purchaseStockQuery.setPage_size("10000000");
        JSONArray json = JSONArray.fromObject(condition);
        purchaseStockQuery.setCondition(json.toString());
        Document doc = EcErpUtils.getDocument(purchaseStockQuery, EcErpMethodEnum.PURCHASE_GET.getMethod());
        LOGGER.info("查询采购入库单返回结果 :" + doc != null ? doc.asXML() : "null");
        return doc;

    }

}
