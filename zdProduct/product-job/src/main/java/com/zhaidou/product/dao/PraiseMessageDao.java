package com.zhaidou.product.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.PraiseMessageModel;

public interface PraiseMessageDao extends IDao{
    
    public PraiseMessageModel getProductInfo(PraiseMessageModel praiseMessageModel) throws Exception;
    
    public void changeRetryNum(PraiseMessageModel praiseMessageModel) throws Exception;
}
