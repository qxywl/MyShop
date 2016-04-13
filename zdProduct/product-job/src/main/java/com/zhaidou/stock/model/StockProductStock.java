package com.zhaidou.stock.model;
/**
 * 
 * @author donnie
 * @version 1.0.0
 *
 */
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class StockProductStock implements Serializable {
	
	private static final long serialVersionUID = -8774508451816385141L;

    /** 库存记录id */
    private Long stockId ;

    /** 库存编号 */
    private String stockCode ;

    /** sku id */
    private Long skuId ;

    /** sku 编码 */
    private String skuCode ;

    /** 商品id */
    private Long productId ;

    /** 商品编号 */
    private String productCode ;

    /** 商品名称 */
    private String productName ;

    /** 商品别名 */
    private String productEname ;

    /** 品牌id */
    private Long brandId ;

    /** 品牌编码 */
    private String brandCode ;

    /** 品牌名称 */
    private String brandName ;

    /** 分类id */
    private Long categoryId ;

    /** 分类编号 */
    private String categoryCode ;

    /** 分类名称 */
    private String categoryName ;

    /** 对接库存数 */
    private Double virtualStock ;

    /** 手工虚拟库 */
    private Double manualStock ;

    /** 实库数 */
    private Double entityStock ;

    /** 虚拟库类型：1：手工库；2：对接库 */
    private Integer stockType ;

    /** 库存百分比 */
    private Integer stockPercent ;


    public Long  getStockId(){
        return stockId ;
    }
    public StockProductStock setStockId(Long stockId){
        this.stockId = stockId ;
        return this;
    }

    public String  getStockCode(){
        return stockCode ;
    }
    public StockProductStock setStockCode(String stockCode){
        this.stockCode = stockCode ;
        return this;
    }

    public Long  getSkuId(){
        return skuId ;
    }
    public StockProductStock setSkuId(Long skuId){
        this.skuId = skuId ;
        return this;
    }

    public String  getSkuCode(){
        return skuCode ;
    }
    public StockProductStock setSkuCode(String skuCode){
        this.skuCode = skuCode ;
        return this;
    }

    public Long  getProductId(){
        return productId ;
    }
    public StockProductStock setProductId(Long productId){
        this.productId = productId ;
        return this;
    }

    public String  getProductCode(){
        return productCode ;
    }
    public StockProductStock setProductCode(String productCode){
        this.productCode = productCode ;
        return this;
    }

    public String  getProductName(){
        return productName ;
    }
    public StockProductStock setProductName(String productName){
        this.productName = productName ;
        return this;
    }

    public String  getProductEname(){
        return productEname ;
    }
    public StockProductStock setProductEname(String productEname){
        this.productEname = productEname ;
        return this;
    }

    public Long  getBrandId(){
        return brandId ;
    }
    public StockProductStock setBrandId(Long brandId){
        this.brandId = brandId ;
        return this;
    }

    public String  getBrandCode(){
        return brandCode ;
    }
    public StockProductStock setBrandCode(String brandCode){
        this.brandCode = brandCode ;
        return this;
    }

    public String  getBrandName(){
        return brandName ;
    }
    public StockProductStock setBrandName(String brandName){
        this.brandName = brandName ;
        return this;
    }

    public Long  getCategoryId(){
        return categoryId ;
    }
    public StockProductStock setCategoryId(Long categoryId){
        this.categoryId = categoryId ;
        return this;
    }

    public String  getCategoryCode(){
        return categoryCode ;
    }
    public StockProductStock setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode ;
        return this;
    }

    public String  getCategoryName(){
        return categoryName ;
    }
    public StockProductStock setCategoryName(String categoryName){
        this.categoryName = categoryName ;
        return this;
    }

    public Double  getVirtualStock(){
        return virtualStock ;
    }
    public StockProductStock setVirtualStock(Double virtualStock){
        this.virtualStock = virtualStock ;
        return this;
    }

    public Double  getManualStock(){
        return manualStock ;
    }
    public StockProductStock setManualStock(Double manualStock){
        this.manualStock = manualStock ;
        return this;
    }

    public Double  getEntityStock(){
        return entityStock ;
    }
    public StockProductStock setEntityStock(Double entityStock){
        this.entityStock = entityStock ;
        return this;
    }

    public Integer  getStockType(){
        return stockType ;
    }
    public StockProductStock setStockType(Integer stockType){
        this.stockType = stockType ;
        return this;
    }

    public Integer  getStockPercent(){
        return stockPercent ;
    }
    public StockProductStock setStockPercent(Integer stockPercent){
        this.stockPercent = stockPercent ;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
