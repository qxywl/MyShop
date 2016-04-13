package com.zhaidou.ecerp;

import com.google.common.collect.Lists;
import com.zhaidou.ecerp.params.GoodsAdd;
import com.zhaidou.ecerp.params.GoodsModify;
import com.zhaidou.product.model.ProductModel;
import com.zhaidou.product.model.ProductSkuModel;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 商品操作类
 */
public class GoodsOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsOperator.class);

    
    /**
     * 添加商品
     * @param goodsAdd
     * @return
     */
    public static Document addGoods(GoodsAdd goodsAdd) {

        Document doc = EcErpUtils.getDocument(goodsAdd, EcErpMethodEnum.SHANGPIN_ADDSHANGPIN.getMethod());
        LOGGER.info("erp添加商品返回结果 :"  + doc == null ? "null" : doc.asXML());
        return doc;
    }


    /**
     * 编辑商品
     * @param goodsModify
     * @return
     */
    public static Document modifyGoods(GoodsModify goodsModify) {

        Document doc = EcErpUtils.getDocument(goodsModify, EcErpMethodEnum.SHANGPIN_MODIFY.getMethod());
        LOGGER.info("erp编辑商品返回结果 :" + doc == null ? "null" : doc.asXML());
        return doc;
    }

    /**
     * 封装添加商品数据
     */
    public static GoodsAdd assembleGoodsAdd(ProductModel productModel){

        GoodsAdd goods = new GoodsAdd();
        String productShortName = productModel.getProductOperateModel().getProductShortName();
        if (productShortName == null || productShortName.equals("")) {
            productShortName = productModel.getProductName();
        }
        goods.setSpdm(productModel.getProductCode());
        goods.setSpdm2(productShortName);
        goods.setSpmc(productModel.getProductName());
        goods.setZl("0"); //重量
//        goods.setGys_dm("00000");
        goods.setBzsj("0");
        goods.setBzjj("0");
        goods.setBz("");
//        goods.setDesc(productModel.getProductDesc());
        goods.setGyshh("");//供应商货号
        goods.setNum("0"); //商品店存上限数量
        goods.setAuto_barcode("0");

        Collection<ProductSkuModel> productSkuList = productModel.getProductSkuList();
        int size = productSkuList.size();
        String[] skudms = new String[size];     //编码
        String[] skumcs = new String[size];       //名称
        String[] skuzls = new String[size];       //重量
        String[] skuggbzs = new String[size];    //规格备注
        String[] skubzsjs = new String[size];    //规格标准售价
        String[] skubzjjs = new String[size];    //规格标准进价
        String[] gyshhs = new String[size];  ;   //供应商货号
        String[] skunums = new String[size];  ; //sku店存数量
        int i = 0;
        for (ProductSkuModel productSkuModel : productSkuList) {
            skudms[i] = productSkuModel.getProductSkuCode();
            assembleSkuName(skumcs, i, productSkuModel);
            skuzls[i] = "0";
            skuggbzs[i] = "";
            skubzsjs[i] = "0";
            skubzjjs[i] = "0";
            gyshhs[i] = "";
            skunums[i] = "0";
            i++;
        }
        goods.setSkudms(skudms);
        goods.setSkumcs(skumcs);
        goods.setSkuzls(skuzls);
        goods.setSkuggbzs(skuggbzs);
        goods.setSkubzsjs(skubzsjs);
        goods.setSkubzjjs(skubzjjs);
        goods.setGyshhs(gyshhs);
        goods.setSkunums(skunums);

//        goods.setSpprop1();
//        goods.setSpprop2();
//        goods.setSpprop3();
//        goods.setSpprop4();
//        goods.setSpprop5();
//        goods.setSpprop6();
//        goods.setSpprop7();
//        goods.setSpprop8();
//        goods.setSpprop9();
//        goods.setSpprop10();

        goods.setDanwei("");
        goods.setBz2("");
        goods.setBz3("");
        goods.setIsBatchManagement("0");

        return goods;
    }

    private static void assembleSkuName(String[] skumcs, int i, ProductSkuModel productSkuModel) {
        String skuName = "";
        if (productSkuModel.getColorValueAlias().equals("F") &&!productSkuModel.getSpecValueAlias().equals("F") ) {
            skuName = productSkuModel.getSpecValueAlias();
        }else if(!productSkuModel.getColorValueAlias().equals("F")&&productSkuModel.getSpecValueAlias().equals("F")) {
            skuName = productSkuModel.getColorValueAlias();
        }else{
            skuName=productSkuModel.getColorValueAlias() +" "+ productSkuModel.getSpecValueAlias();
        }
        skumcs[i] = skuName;
    }

    public static GoodsModify assembleGoodsModify(ProductModel productModel,List<ProductSkuModel> insertListSku) {

        GoodsModify goodsModify = new GoodsModify();
        String productShortName = productModel.getProductOperateModel().getProductShortName();
        if (productShortName == null || productShortName.equals("")) {
            productShortName = productModel.getProductName();
        }
        goodsModify.setSpdm(productModel.getProductCode());
        goodsModify.setSpmc(productShortName);
        goodsModify.setZl("0");
//        goodsModify.setGys_dm();
        goodsModify.setBzsj("0");
        goodsModify.setBzjj("0");
        goodsModify.setBz("");
//        goodsModify.setDesc(productModel.getProductDesc());
        goodsModify.setGyshh("");
        goodsModify.setNum("0");
        goodsModify.setAuto_barcode("0");

        Collection<ProductSkuModel> productSkuList = productModel.getProductSkuList();
        if (productSkuList ==null) {
            productSkuList = Lists.newArrayList();
        }
        if (insertListSku == null) {
            insertListSku = Lists.newArrayList();
        }
        int size = productSkuList.size()+insertListSku.size();
        String[] skudms = new String[size];     //编码
        String[] skumcs = new String[size];       //名称
        String[] skuzls = new String[size];       //重量
        String[] skuggbzs = new String[size];    //规格备注
        String[] skubzsjs = new String[size];    //规格标准售价
        String[] skubzjjs = new String[size];    //规格标准进价
        String[] gyshhs = new String[size];  ;   //供应商货号
        String[] skunums = new String[size];  ; //sku店存数量
        int i = 0;
        for (ProductSkuModel productSkuModel : productSkuList) {
            skudms[i] = productSkuModel.getProductSkuCode();
            assembleSkuName(skumcs, i, productSkuModel);
            skuzls[i] = "0";
            skuggbzs[i] = "";
            skubzsjs[i] = "0";
            skubzjjs[i] = "0";
            gyshhs[i] = "";
            skunums[i] = "0";
            i++;
        }

        for (ProductSkuModel productSkuModel : insertListSku) {
            skudms[i] = productSkuModel.getProductSkuCode();
            assembleSkuName(skumcs, i, productSkuModel);
            skuzls[i] = "0";
            skuggbzs[i] = "";
            skubzsjs[i] = "0";
            skubzjjs[i] = "0";
            gyshhs[i] = "";
            skunums[i] = "0";
            i++;
        }

        goodsModify.setSkudms(skudms);
        goodsModify.setSkumcs(skumcs);
        goodsModify.setSkuzls(skuzls);
        goodsModify.setSkuggbzs(skuggbzs);
        goodsModify.setSkubzsjs(skubzsjs);
        goodsModify.setSkubzjjs(skubzjjs);
        goodsModify.setGyshhs(gyshhs);
        goodsModify.setSkunums(skunums);

        goodsModify.setSpdm2("");
        goodsModify.setBz2("");
        goodsModify.setBz3("");
        goodsModify.setDanwei("");
        goodsModify.setIsBatchManagement("0");

        return goodsModify;
    }

    public static void main(String[] args) throws Exception {

        GoodsAdd goods = new GoodsAdd();
        goods.setSpdm("spdm0003");
        goods.setSpmc("测试商品0003");
        goods.setZl("10");
//        goods.setGys_dm("00000");
        goods.setBzsj("50");
        goods.setBzjj("50");
        goods.setBz("测试使用请勿操作勿删除");
        goods.setDesc("测试使用请勿操作勿删除");
        goods.setGyshh("hh0003");
        goods.setNum("1");
        goods.setAuto_barcode("0");
        goods.setSkudms("sku0004,sku0005".split(","));
        goods.setSkumcs("测试规格4,测试规格4".split(","));
        goods.setSkuzls("10,20".split(","));
        goods.setSkuggbzs("50,60".split(","));
        goods.setSkubzsjs("50,60".split(","));
        goods.setSkubzjjs("70,80".split(","));
        goods.setGyshhs("hh0004,hh0005".split(","));
        goods.setSkunums("10,20".split(","));
//        goods.setSpprop1();
//        goods.setSpprop2();
//        goods.setSpprop3();
//        goods.setSpprop4();
//        goods.setSpprop5();
//        goods.setSpprop6();
//        goods.setSpprop7();
//        goods.setSpprop8();
//        goods.setSpprop9();
//        goods.setSpprop10();
        goods.setSpdm2("goods");
        goods.setDanwei("个");
        goods.setBz2("bz2");
        goods.setBz3("bz3");
        goods.setIsBatchManagement("0");

        addGoods(goods);

    }
}
