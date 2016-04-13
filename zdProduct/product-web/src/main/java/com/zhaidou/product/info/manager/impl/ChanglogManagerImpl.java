/*
 * 文 件 名:  ChanglogManagerImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.manager.impl;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ChanglogManager;
import com.zhaidou.product.info.model.ChanglogModel;
import com.zhaidou.product.info.service.ChanglogService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("changlogManager")
public class ChanglogManagerImpl implements ChanglogManager
{
    private static final Log    logger = LogFactory.getLog(ChanglogManagerImpl.class);

    @Resource
    private ChanglogService changlogService;

    public void addChanglog(ChanglogModel changlogModel)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + JSON.toJSONString(changlogModel));
        }
        if (changlogModel!=null)
        {
           try{
            changlogService.addChanglog(changlogModel);
            }catch(Exception e){
                logger.error("新增Changlog异常", e);
            }
        }
    }

    public void updateChanglog(ChanglogModel changlogModel)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + JSON.toJSONString(changlogModel));
        }
        if (changlogModel!=null)
        {
           try{
            changlogService.updateChanglog(changlogModel);
            }catch(Exception e){
                logger.error("更新Changlog异常", e);
            }
        }

    }

    public ChanglogModel getChanglogById(String id)
    {
    	ChanglogModel result = null;
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        if (!StringUtils.isEmpty(id))
        {
            try{
            ChanglogModel changlogModel=new ChanglogModel();
            changlogModel.setId(Long.parseLong(id));
            result = changlogService.getChanglogById(changlogModel);
            }catch(Exception e){
                logger.error("查询Changlog异常", e);
            }
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public Map<String, Object> getChanglog(ChanglogModel changlogModel,Page page)
    {
        
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + JSON.toJSONString(changlogModel));
        }
        try{
        if (changlogModel!=null)
        {          
            List<ChanglogModel> list = changlogService.getChanglog(changlogModel,page);
            result.put("page", page);
            result.put("list", list);
        }
        }catch(Exception e){
            logger.error("查询Changlog异常", e);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public void deleteById(String id)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        if (!StringUtils.isEmpty(id))
        {
            try{
            	ChanglogModel changlogModel=new ChanglogModel();
            	changlogModel.setId(Long.parseLong(id));
            changlogService.deleteById(changlogModel);
            }catch(Exception e){
                logger.error("删除Changlog异常", e);
            }
        }
    }
    
    @Override
	public void deleteByIds(List<Long> ids) {
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + ids);
        }
        if (!ids.isEmpty())
        {
            try{            	
            	 changlogService.deleteByIds(ids);
            }catch(Exception e){
                logger.error("批量删除Changlog异常", e);
            }
        }
		
	}

}
