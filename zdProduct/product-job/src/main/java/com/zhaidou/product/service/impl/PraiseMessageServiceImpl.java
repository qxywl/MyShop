package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.PraiseMessageDao;
import com.zhaidou.product.model.PraiseMessageModel;
import com.zhaidou.product.service.PraiseMessageService;

@Service("praiseMessageService")
public class PraiseMessageServiceImpl implements PraiseMessageService {
    
    @Resource
    private PraiseMessageDao praiseMessageDao;

    @Override
    public List<PraiseMessageModel> getProductInfoAuth(PraiseMessageModel praiseMessageModel, Page page)
            throws Exception {
        return praiseMessageDao.queryListPage(praiseMessageModel, page.getPageNum(), page.getNumPerPage());
    }

    @Override
    public void updatePraiseMessage(PraiseMessageModel praiseMessageModel) throws Exception {
        praiseMessageDao.update(praiseMessageModel);
    }

    @Override
    public PraiseMessageModel getProductInfo(Long productId) throws Exception {
        PraiseMessageModel praiseMessageModel = new PraiseMessageModel();
        praiseMessageModel.setProductId(productId);
        return praiseMessageDao.getProductInfo(praiseMessageModel);
    }

    @Override
    public void changeRetryNum(PraiseMessageModel praiseMessageModel) throws Exception {
        praiseMessageDao.changeRetryNum(praiseMessageModel);
    }

    @Override
    public Long getCountPraise(PraiseMessageModel praiseMessageModel) throws Exception {
        return praiseMessageDao.countListPage(praiseMessageModel);
    }

}
