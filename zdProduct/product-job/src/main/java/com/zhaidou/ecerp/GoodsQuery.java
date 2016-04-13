package com.zhaidou.ecerp;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.zhaidou.ecerp.params.EcErpQuery;
import com.zhaidou.framework.util.numberrule.NumberingRuleUtil;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 商品查询类
 */
public class GoodsQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsQuery.class);

    /**
     * 根据商品编码查询商品信息
     * @param productCode
     */
    public static Document findGoods(String productCode) {
        EcErpQuery goodsStockQuery = new EcErpQuery();
        goodsStockQuery.setFields(null);
        goodsStockQuery.setPage_no("1");
        goodsStockQuery.setPage_size("20");
        String conditions = "[{'paramname':'spdm', 'paramcompair': '=', 'paramvalue':'"+productCode+"'}]";

        goodsStockQuery.setCondition(conditions);
        goodsStockQuery.setOrderby("spdm");
        goodsStockQuery.setOrderbytype("DESC");

        Document doc = EcErpUtils.getDocument(goodsStockQuery, EcErpMethodEnum.SHANGPIN_GET.getMethod());
        LOGGER.info("查询商品返回结果 :doc " + doc != null ? doc.asXML() : "null");
        return doc;

    }

    /**
     * 根据条件查询商品信息
     * @param conditions
     */
    public static Document findGoodsByConditions(String conditions) {
        EcErpQuery goodsStockQuery = new EcErpQuery();
        goodsStockQuery.setFields(null);
        goodsStockQuery.setPage_no("1");
        goodsStockQuery.setPage_size("20000");

        goodsStockQuery.setCondition(conditions);

        Document doc = EcErpUtils.getDocument(goodsStockQuery, EcErpMethodEnum.SHANGPIN_GET.getMethod());
        LOGGER.info("查询商品返回结果 :doc " + doc != null ? doc.asXML() : "null");
        return doc;

    }



    public static void main(String[] args) {


//        findGoods("991100000001");
        String starTdate = "2015-12-24";
        String endDate = "2016-01-05";
        String fileDate = "2016-01-06";
        String condition = "[{\"paramname\":\"cjsj\",\"paramcompair\":\">\",\"paramvalue\":\"" + starTdate + " 00:00:00\"};" +
                "{\"paramname\":\"cjsj\",\"paramvalue\":\"" + endDate + " 23:59:59\",\"paramcompair\":\"<\"}]";
        Document document = findGoodsByConditions(condition);
        if (document == null) {
            System.out.println("无数据");
        }


        List<String> aa=Lists.newArrayList("111100186489","111100186491","111100186499","111100186501",
                "111100186502","111100186518","111200186524","121100186519","121300186521","121300186529",
                "121300186536","121300186541","121300186543","121400186532","121500186522","121600186544","131100186548",
                "131300186530","131400186527","131400186531","131400186534","131400186535","131400186542","141400186533",
                "141700186525","141700186547","151400186528","161500186549","161600186526","161700186545",
                "111100186490","111100186492","111100186493","111100186508","111100186511","111300020007","111300020008","111300020009","111300020010","111300020011","111400030031","111400030032","111700060013","111700060016","111700060017","121100186510","141100220045","141800290002","151200340007","151300350032","161300440009","161300440010","161400450007","161400450008","161400450009","161400450010","161400450011","161400450012","162100560022","171200520017","171200520019","171200520020","171200520021","171300580002","171300580006");
        try {
            Node node = document.selectSingleNode("//shangpin_get_response/total_results");
            if ("0".equals(node.getText())) {
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File("/Users/rainysun/erp/erpToApp_" + fileDate + ".sql")));

            List<Node> list = document.selectNodes("//shangpin_get_response/shangpins/shangpin");
            List<String> productCodeList = Lists.newArrayList();
            for (Node goodsNode : list) {
                Node spdmNode = goodsNode.selectSingleNode("spdm");
                Node spmcNode = goodsNode.selectSingleNode("spmc");
                Node cjsjNode = goodsNode.selectSingleNode("cjsj");
                Node xgsjNode = goodsNode.selectSingleNode("xgsj");
                String productCode = spdmNode.getText().trim();
                String productName = spmcNode.getText().trim();
                if (aa.contains(productCode)) {
                    System.out.println("------>>>>"+productCode);
                    continue;
                }

                productCodeList.add("'"+productCode+"'");
                writer.write("-- start 商品:" + productName + " code :" + productCode + "\r\n");
                writer.write("-- select * from `product_web`.`t_product` where product_code ='" + productCode + "';\r\n");
                String insertGoods = "INSERT INTO `product_web`.`t_product` (`product_name`, `product_code`, `product_desc`, `product_shelves`, `create_time`, `create_by`, `create_user_name`, `update_time`, `update_by`, `update_user_name`, `cost_price`, `tsh_price`, `market_price`, `brand_code`, `brand_name`, `cat_code`, `cat_name`, `type`, `staus`, `main_pic`, `supplier_id`, `shop_id`) \n" +
                        " VALUES ('" + productName + "', '" + productCode + "', '', '0', UNIX_TIMESTAMP('" + cjsjNode.getText() + "'), 1, " +
                        "'admin', UNIX_TIMESTAMP('" + xgsjNode.getText() + "')" +
                        ", 1, 'admin', 0, 0, 0, 'CN04757', '宅豆家居', '111111', '桌面收纳', '01', '2', 'http://imgs.zhaidou" +
                        ".com/goods/08/040400185908/040400185908001/sk1_20150001.jpg', 1, 1)\t;\r\n";

                writer.write(insertGoods);
                writer.write("\r\n");

                String insertOperator = "INSERT INTO `product_web`.`t_product_operate` ( `product_id`, `product_short_name`, `product_down_show`, `product_auto_up`) \n" +
                        "VALUES ( (SELECT product_id FROM product_web.t_product where product_code='" + productCode + "')" +
                        ", '" + productName + "'," +
                        " 2, 2);\r\n";
                writer.write(insertOperator);
                writer.write("\r\n");

                String productInfo = "INSERT INTO `product_web`.`t_product_info`(`product_id`,`pc_product_info`,`app_product_info`)\n" +
                        "VALUES ((SELECT product_id FROM product_web.t_product where product_code='" + productCode + "'), '','');\r\n";
                writer.write(productInfo);
                writer.write("\r\n");

                String productMall = "INSERT INTO `product_web`.`t_product_mall`(`product_id`)\n" +
                        "VALUES ((SELECT product_id FROM product_web.t_product where product_code='" + productCode + "'));\r\n";
                writer.write(productMall);
                writer.write("\r\n");


                List<Node> spskuNodes = goodsNode.selectNodes("spskus/spsku");
                int i = 0;
                for (Node cgddmxNode : spskuNodes) {
                    Node skudmNode = cgddmxNode.selectSingleNode("skudm");
                    Node skumcNode = cgddmxNode.selectSingleNode("skumc");
                    String mainSku = "1";
                    if (i != 0) {
                        mainSku = "0";
                    }

                    String skuCode = skudmNode.getText().trim();
                    String productSku = "INSERT INTO `product_web`.`t_product_sku` (`product_id`, `product_sku_code`, `attr_color_name`, `attr_color_value`, `color_value_alias`, `attr_spec_name`, `attr_spec_value`, `spec_value_alias`, `main_sku`, `if_show`, `is_available`, `supplier_sku_code`) \n" +
                            "VALUES ((SELECT product_id FROM product_web.t_product where " +
                            "product_code='" + productCode + "'), '" + skuCode + "', '颜色', '无', '" + skumcNode.getText().trim() + "'," +
                            " '型号', 'F', 'F', " + mainSku + ", 1, 1, 1);\r\n";
                    writer.write(productSku);
                    writer.write("\r\n");

                    String price = "INSERT INTO `product_web`.`t_product_price` (`product_sku_code`, `product_id`, `product_sku_id`, `cost_price`, `tsh_price`, `tb`, `market_price`, `zero_price`) \n" +
                            "VALUES ('" + skuCode + "', (SELECT product_id FROM product_web.t_product where " +
                            "product_code='" + productCode + "'), \n" +
                            "(select product_sku_id from product_web.t_product_sku where product_sku_code='" + skuCode + "'), 0, 0, 0, 0, 0);\r\n";
                    writer.write(price);
                    writer.write("\r\n");

                    String stock = "INSERT INTO `product_web`.`t_product_stock` (`sku_code`, `sku_id`, `product_id`, `virtual_stock`, `manual_stock`, `entity_stock`)\n" +
                            " VALUES ('" + skuCode + "', (select product_sku_id from product_web.t_product_sku where product_sku_code='" + skuCode + "'), \n" +
                            " (SELECT product_id FROM product_web.t_product where product_code='" + productCode + "'), 0, 0, 0);\r\n";
                    writer.write(stock);
                    writer.write("\r\n");

                    String stockCode = getCode("ST", skuCode);

                    String productStock = "INSERT INTO `product_stock`.`t_product_stock` (`stock_code`, `sku_id`, `sku_code`, `product_id`, `product_code`, `product_name`, `product_ename`, `brand_id`, `brand_code`, `brand_name`, `category_id`, `category_code`, `category_name`, `virtual_stock`, `manual_stock`, `entity_stock`, `stock_type`, `stock_percent`) \n" +
                            "VALUES ('" + stockCode + "', (select product_sku_id from product_web.t_product_sku where " +
                            "product_sku_code='" + skuCode + "'), '" + skuCode + "', " +
                            "(select product_sku_id from product_web.t_product_sku where " +
                            "product_sku_code='" + productCode + "'),'" + productCode + "', '" + productName + "', '', 00000004757, 'CN04757', '宅豆家居', 840, '111111', '桌面收纳', 0, 0, 0, 3, 100);\r\n";
                    writer.write(productStock);
                    writer.write("\r\n");
                    i++;

                }

                writer.write("-- end 商品:" + productName + " code :" + productCode + "\r\n");
                writer.write("\r\n");
            }
            System.out.println("--->>>" + Joiner.on(",").join(productCodeList));

            writer.close();


        } catch (Exception e) {

        }

    }


    /**
     * 获取编码
     * @param codeHeader
     * @return
     */
    public static String getCode(String codeHeader,String skuCode)
    {
        return NumberingRuleUtil.newBaseCode(codeHeader, skuCode, "", false);
    }
}
