package com.zhaidou.product.manager;

import com.zhaidou.product.model.PraiseMessageModel;
import com.zhaidou.product.model.ProductObjBufCondiction;

public interface PraiseMessageManager {
    
    public void sendMessage(ProductObjBufCondiction configuration);
    
    public void imoprt(ProductObjBufCondiction configuration);
    
    public void changeRetryNum(PraiseMessageModel praiseMessageModel);
}
