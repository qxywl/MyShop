package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.PraiseMessageModel;

public interface PraiseMessageService {
    
    public List<PraiseMessageModel> getProductInfoAuth(PraiseMessageModel praiseMessageModel,Page page) throws Exception;
    
    public void updatePraiseMessage(PraiseMessageModel praiseMessageModel) throws Exception;
    
    public PraiseMessageModel getProductInfo(Long productId) throws Exception;
    
    public void changeRetryNum(PraiseMessageModel praiseMessageModel) throws Exception;
    
    public Long getCountPraise(PraiseMessageModel praiseMessageModel) throws Exception;
}
