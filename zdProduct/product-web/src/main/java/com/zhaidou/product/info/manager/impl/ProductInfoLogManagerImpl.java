package com.zhaidou.product.info.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductInfoLogManager;
import com.zhaidou.product.info.model.ProductInfoLogModel;
import com.zhaidou.product.info.service.ProductInfoLogService;
import com.zhaidou.product.info.util.InfoUtil;

@Service("productInfoLogManager")
public class ProductInfoLogManagerImpl implements ProductInfoLogManager {
    private static Logger logger = Logger.getLogger(ProductInfoLogManagerImpl.class);
    
    @Resource
    private ProductInfoLogService productInfoLogService;
    
    
    /**
     * 商品信息日志列表
     *
     * @param page
     * @param productInfoLogModel
     * @return
     */
    @Override
    public List<ProductInfoLogModel> infoLogList(Page page, ProductInfoLogModel productInfoLogModel) {
        List<ProductInfoLogModel> infoLogList = null;
        try {
            infoLogList = productInfoLogService.getProductInfoLog(productInfoLogModel, page);
        } catch (Exception e) {
            logger.error("获取商品信息日志列表 异常！",e);
            throw new ZhaidouRuntimeException("商品信息日志列表异常！");
        }
        if(infoLogList != null && infoLogList.size()>0){
            for(ProductInfoLogModel infoLogModel:infoLogList){
                if(infoLogModel.getCreateTime()!=null){
                    infoLogModel.setCreateTimes(InfoUtil.dateLongToString(infoLogModel.getCreateTime(), InfoUtil.dateString));
                }
                if(infoLogModel.getUpdateTime()!=null){
                    infoLogModel.setUpdateTimes(InfoUtil.dateLongToString(infoLogModel.getUpdateTime(), InfoUtil.dateString));
                }
            }
        }
        return infoLogList;
    }
    
    /**
     * 商品信息日志  详情
     *
     * @param page
     * @param productInfoLogModel
     * @return
     */
    @Override
    public ProductInfoLogModel getProductInfoLogById(ProductInfoLogModel productInfoLogModel) {
        try {
            productInfoLogModel = productInfoLogService.getProductInfoLogById(productInfoLogModel);
        } catch (Exception e) {
            logger.error("", e);
            new ZhaidouRuntimeException("获取商品信息日志详情 异常");
        }
        return productInfoLogModel;
    }
}
