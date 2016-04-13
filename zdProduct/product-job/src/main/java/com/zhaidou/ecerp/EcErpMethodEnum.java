package com.zhaidou.ecerp;


/**
 * Created by IntelliJ IDEA.
 * User: 黄健平
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * api_url:http://api.guanyisoft.com/document/base.htm
 * desc: 请求方式枚举
 */
public enum EcErpMethodEnum {

    /**
     * 商品添加
     */
    SHANGPIN_ADDSHANGPIN("ecerp.shangpin.addshangpin"),

    /**
     * 修改商品
     */
    SHANGPIN_MODIFY("ecerp.shangpin.modify_commodity"),

    /**
     * 商品查询
     */
    SHANGPIN_GET("ecerp.shangpin.get"),

    /**
     * 查询商品库存
     */
    STOCK_GET("ecerp.stock.get"),

    NEW_STOCK_GET("ecerp.newstock.get"),

    /**
     * 查询订单
     */
    TRADE_GET("ecerp.trade.get"),

    /**
     * 查询订单状态
     */
    TRADESTATE_GET("ecerp.tradestate.get"),

    /**
     * 新增订单new
     */
    TRADE_ADD_ORDER("ecerp.trade.add_order_new"),

    /**
     * 订单财审
     */
    TRADE_FINANCE_AUDIT_ORDER("ecerp.trade.financeaudit_order"),

    /**
     * 查询采购订单
     */
    PURCHASE_GET("ecerp.purchase.get"),

    /**
     * 查询采购退货单
     */
    THCKD_GET("ecerp.thckd.get"),

    /**
     * 查询发货单
     */
    SENDORDER_GET("ecerp.sendorder.get"),

    /**
     * 查询订单物流信息
     */
    ORDER_LOGISTIC_GET("ecerp.order_logistic.get"),

    /**
     * 查询 退换货
     */
    THD_GET("ecerp.thd.get"),

    ;

    EcErpMethodEnum(String method) {
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
