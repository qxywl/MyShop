/*
 * 文 件 名:  ProductInfoServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
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

import com.zhaidou.product.info.dao.ProductColorDao;
import com.zhaidou.product.info.model.ProductColorModel;
import com.zhaidou.product.info.service.ProductColorService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productColorService")
public class ProductColorServiceImpl implements ProductColorService
{
    private static final Log logger = LogFactory.getLog(ProductColorServiceImpl.class);

    @Resource
    private ProductColorDao  productColorDao;

    @Override
    public List<ProductColorModel> getAllColor() throws Exception {
        return productColorDao.getAll();
    }

}
