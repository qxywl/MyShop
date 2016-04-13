package com.zhaidou.product.info.util;
/**
 * Created by IntelliJ IDEA.
 * User: 黄健平
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 查询请求参数,管易查询接口请求参数基本一样，所以可以通用
 */
public class EcErpQuery {

    /**
     * 需返回的字段列表。可选值：Stock结构体中的所有字段；以半角逗号(,)分隔。
     */
    private String fields;

    /**
     * 页码.传入值为1代表第一页,传入值为2代表第二页.依次类推.默认返回的数据是从第一页开始
     */
    private String page_no;

    /**
     * 每页条数.默认返回的数据为10条
     */
    private String page_size;

    /**
     * 条件 如 SL >0
     */
    private String condition;

    /**
     * 排序字段
     */
    private String orderby;

    /**
     * 排序方式
     */
    private String orderbytype;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getOrderbytype() {
        return orderbytype;
    }

    public void setOrderbytype(String orderbytype) {
        this.orderbytype = orderbytype;
    }



}


