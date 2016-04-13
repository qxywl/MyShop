package com.zhaidou.product.info.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductInfoAuthManager;
import com.zhaidou.product.info.manager.ProductInfoLogManager;
import com.zhaidou.product.info.model.ProductInfoLogModel;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value="/info/product/log")
public class ProductInfoLogController {
    private static Logger logger = Logger.getLogger(ProductInfoLogController.class);
    private static String INFOLOGLIST = "info/product/log/list";
    private static String LOGINFO = "info/product/log/log_info";
    
    @Resource
    private ProductInfoLogManager productInfoLogManager;
    @Resource
    private ProductInfoAuthManager productInfoAuthManager;
    
    /**
     * 转至 商品信息日志列表
     *
     * @param page
     * @param productInfoLogModel
     * @param map
     * @return
     */
    @RequestMapping(value="/list")
    public String infoLogList(Page page,ProductInfoLogModel productInfoLogModel,Map<String,Object> map,HttpServletRequest req){
        //按时间查询 设置起始时间
        List<ProductInfoLogModel> infoAuthList=null;
        try {
            String startTime = req.getParameter("startTime");
            String endTime = req.getParameter("endTime1");
            if(startTime!=null && !"".equals(startTime)){
                productInfoLogModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
                req.setAttribute("startTime", startTime);
            }
            if(endTime!=null && !"".equals(endTime)){
                productInfoLogModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
                req.setAttribute("endTime1", endTime);
            }
            infoAuthList = productInfoLogManager.infoLogList(page, productInfoLogModel);
            
        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        map.put("infoLogList", infoAuthList);
        return INFOLOGLIST;
    }
    
    /**
     * 查看商品信息日志详情
     *
     * @param page
     * @param productInfoLogModel
     * @param map
     * @return
     */
    @RequestMapping(value="/log_info/{id}")
    public String productLogInfo(@PathVariable Long id,HttpServletRequest req){
        try {
            ProductInfoLogModel productInfoLogModel = new ProductInfoLogModel();
            productInfoLogModel.setProductLogId(id);
            productInfoLogModel = productInfoLogManager.getProductInfoLogById(productInfoLogModel);
            req.setAttribute("productInfoLogModel", productInfoLogModel);
            Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(productInfoLogModel.getNewValue(), String.class);
            ProductModel newProduct = (ProductModel)JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
            List<ProductSkuModel> insertListSku = (List<ProductSkuModel>)JSONUtil.toCollection(mapValue.get("insert"), ProductSkuModel.class);
            req.setAttribute("newProduct",newProduct);
            req.setAttribute("insertListSku",insertListSku);
        } catch (Exception e) {
            logger.error(e.toString());
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        return LOGINFO;
    }
}
