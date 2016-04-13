/*
 * 文 件 名:  ChanglogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ChanglogDao;
import com.zhaidou.product.info.model.ChanglogModel;
import com.zhaidou.product.info.service.ChanglogService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("changlogService")
public class ChanglogServiceImpl implements ChanglogService
{
    private static final Log logger = LogFactory.getLog(ChanglogServiceImpl.class);

    @Resource
    private ChanglogDao  changlogDao;

    public void addChanglog(ChanglogModel changlogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addChanglog Params-->" + JSON.toJSONString(changlogModel));
        }
        changlogDao.insert(changlogModel);
    }

    public void updateChanglog(ChanglogModel changlogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addChanglog  Params-->" + JSON.toJSONString(changlogModel));
        }
        changlogDao.update(changlogModel);
    }

    public ChanglogModel getChanglogById(ChanglogModel changlogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglogById Params-->" + JSON.toJSONString(changlogModel));
        }  
        ChanglogModel result = changlogDao.queryOne(changlogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglogById result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public long getChanglogCount(ChanglogModel changlogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglogCount Params-->" + JSON.toJSONString(changlogModel));
        }
        long result = changlogDao.countListPage(changlogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglogCount result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public List<ChanglogModel> getChanglog(ChanglogModel changlogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglog Params-->" + JSON.toJSONString(changlogModel));
        }
        long count = changlogDao.countListPage(changlogModel);
        List<ChanglogModel> result=null;
        if (count > 0) {
        	 page.setTotalCount(count);
        result= changlogDao.queryListPage(changlogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--getChanglog result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public void deleteById(ChanglogModel changlogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--deleteById Params-->" + JSON.toJSONString(changlogModel));
        }
        changlogDao.delete(changlogModel);
    }
    
    @Override
	public void deleteByIds(List<Long> ids) throws Exception {
		if (logger.isDebugEnabled())
        {
            logger.debug("--deleteByIds Params-->" + JSON.toJSONString(ids));
        }
		changlogDao.deleteByIds(ids); 
		
	}

}
