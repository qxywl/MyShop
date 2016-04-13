/*
 * 文 件 名:  ProductPriceListServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductDao;
import com.zhaidou.product.info.dao.ProductOperateDao;
import com.zhaidou.product.info.dao.ProductPriceDao;
import com.zhaidou.product.info.dao.ProductPriceJobDao;
import com.zhaidou.product.info.dao.ProductPriceListDao;
import com.zhaidou.product.info.dao.ProductPriceLogDao;
import com.zhaidou.product.info.dao.ProductShelvesDao;
import com.zhaidou.product.info.dao.ProductShelvesTmpDao;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductOperateModel;
import com.zhaidou.product.info.model.ProductPriceListModel;
import com.zhaidou.product.info.model.ProductPriceLogModel;
import com.zhaidou.product.info.model.ProductPriceModel;
import com.zhaidou.product.info.model.ProductShelvesModel;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;
import com.zhaidou.product.info.service.ProductPriceListService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceListService")
public class ProductPriceListServiceImpl implements ProductPriceListService
{
    private static final Log logger = LogFactory.getLog(ProductPriceListServiceImpl.class);

    @Resource
    private ProductPriceListDao  productPriceListDao;
    
    @Resource
    private ProductPriceJobDao  productPriceJobDao;
    
    @Resource
    private ProductPriceLogDao  productPriceLogDao;
    
    @Resource
    private ProductPriceDao productPriceDao;
    
    @Resource
    private ProductOperateDao productOperateDao;
    
    @Resource
    private ProductDao productDao;
    
    

    public void addProductPriceList(List<List<String>> list,ProductPriceListModel bb)throws Exception
    {
        if (list != null){
            
			for (int i = 1; i < list.size(); i++) {
			    //成本价
                ProductPriceModel productPriceModel = null;
                
				    List<String> cellList = list.get(i);
					ProductPriceListModel productPriceListModel = new ProductPriceListModel();
					if(cellList.get(0)!=null && !"".equals(cellList.get(0).trim())){
					    productPriceListModel.setProductSkuCode(cellList.get(0));
	                    
                        try {
                            productPriceModel = new ProductPriceModel();
                            productPriceModel.setProductSkuCode(cellList.get(0));
                            productPriceModel = productPriceDao.queryOne(productPriceModel);
                            
                        } catch (Exception e) {
                            logger.error("获取成本价错误：",e);
                            throw new ZhaidouRuntimeException("第"+i+"行,SKU编号不存在！");
                        }
                        if(productPriceModel==null){
                            throw new ZhaidouRuntimeException("第"+i+"行,SKU编号不存在！"+cellList.get(0));
                        }
	                    productPriceListModel.setCostPrice(productPriceModel.getCostPrice());
					}else{
					    logger.error("第"+i+"行,价格导入操作,SKU编号不能为空！");
                        throw new ZhaidouRuntimeException("第"+i+"行,SKU编号不能为空");
					}
					productPriceListModel.setCreateBy(bb.getCreateBy());
					productPriceListModel.setCreateTime(new Date().getTime()/1000);
					productPriceListModel.setCreateUserName(bb.getCreateUserName());
					if(cellList.get(1)!=null && !"".equals(cellList.get(1).trim())){
					    try {
                            productPriceListModel.setTshPrice(Double.valueOf(cellList.get(1)));
                        } catch (Exception e) {
                            logger.error("第"+i+"行,价格导入操作,销售价转Double类型失败！");
                            throw new ZhaidouRuntimeException("第"+i+"行,销售价不是有效数字");
                        }
					}else{
					    productPriceListModel.setTshPrice(productPriceModel.getTshPrice());
					}
					if(cellList.get(2)!=null && !"".equals(cellList.get(2).trim())){
					    try {
                            productPriceListModel.setMarketPrice(Double.valueOf(cellList.get(2)));
                        } catch (Exception e) {
                            logger.error("第"+i+"行,价格导入操作,市场价转Double类型失败！");
                            throw new ZhaidouRuntimeException("第"+i+"行,市场价不是有效数字");
                        }
					}else{
					    productPriceListModel.setMarketPrice(productPriceModel.getMarketPrice());
					}
					
					if(productPriceListModel.getCostPrice()>=productPriceListModel.getMarketPrice()){
					    logger.error("第"+i+"行,市场价不能小于或等于该商品的供货价 ！");
                        throw new ZhaidouRuntimeException("第"+i+"行,市场价不能小于或等于该商品的供货价 ");
					}
					Long tb = (long) Math.ceil(productPriceListModel.getMarketPrice()-productPriceListModel.getTshPrice());
					if(tb<=0){
					    logger.error("第"+i+"行,价格计算出的特币额不能小于或等于零 ！");
                        throw new ZhaidouRuntimeException("第"+i+"行,价格计算出的特币额不能小于或等于零");
					}
					productPriceListModel.setTb(tb);
					productPriceListModel.setReason(cellList.get(3));
					productPriceListModel.setStatus(0);
					if(cellList.get(4)!=null && !"".equals(cellList.get(4))){
					    if(cellList.get(4).length()==19){
                            try {
                                productPriceListModel.setTimeStart(DatetimeUtil.stringToDate(cellList.get(4)).getTime()/1000);
                            } catch (Exception e) {
                                logger.error("第"+i+"行,价格导入操作,生效时间 格式不符合！");
                                throw new ZhaidouRuntimeException("第"+i+"行,生效时间 格式不符合");
                            }
                        }else{
                            logger.error("第"+i+"行,价格导入操作,生效时间 格式不符合！");
                            throw new ZhaidouRuntimeException("第"+i+"行,生效时间 格式不符合");
                        }
					}
					productPriceListDao.insert(productPriceListModel);
			}
		}
    }
    
    @Resource
    private ProductShelvesTmpDao productShelvesTmpDao;
    @Resource
    private ProductShelvesDao productShelvesDao;
    
    public void updateProductPriceList(Long[] ids ,ProductPriceListModel bb)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + ids);
        }
        for (int i = 0; i < ids.length; i++) {
        ProductPriceListModel productPriceListModel=new ProductPriceListModel();
        productPriceListModel.setProductPriceList(ids[i]);
        productPriceListModel.setStatus(bb.getStatus());
        productPriceListModel.setReason(bb.getReason());
        productPriceListModel.setUpdateBy(bb.getUpdateBy());
        productPriceListModel.setUpdateTime(new Date().getTime()/1000);
        productPriceListModel.setUpdateUserName(bb.getUpdateUserName());
        
        if(bb.getStatus()==2){
            ProductPriceListModel priceListMod = productPriceListDao.queryOne(productPriceListModel);
            if(priceListMod!=null){
                ProductShelvesTmpModel productShelvesTmpModel = new ProductShelvesTmpModel();
                String productCode = priceListMod.getProductSkuCode().substring(0,12);
                productShelvesTmpModel.setProductCode(productCode);
                productShelvesTmpModel = productShelvesTmpDao.queryOne(productShelvesTmpModel);
                if(productShelvesTmpModel!=null){
                    boolean skuFlag = false;
                    
                    if(productShelvesTmpModel.getTotalSkuCode().equals(priceListMod.getProductSkuCode())){
                        skuFlag = true;
                    }
                    
                    if(skuFlag){
                        productShelvesTmpModel.setPassSkuCode(priceListMod.getProductSkuCode());
                        productShelvesTmpModel.setStatus(2);
                        productShelvesTmpDao.update(productShelvesTmpModel);
                    }
                }
            }
        }
        productPriceListDao.update(productPriceListModel);
        }
    }

    public ProductPriceListModel getProductPriceListById(ProductPriceListModel productPriceListModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel.getProductPriceList());
        }    
        ProductPriceListModel result = productPriceListDao.queryOne(productPriceListModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductPriceListCount(ProductPriceListModel productPriceListModel)throws Exception
    {
        long result = productPriceListDao.countListPage(productPriceListModel);
        return result;
    }

    public List<ProductPriceListModel> getProductPriceList(ProductPriceListModel productPriceListModel, Page page)throws Exception
    {
        long count = productPriceListDao.countListPage(productPriceListModel);
        List<ProductPriceListModel> result=null;
        if (count > 0) {
        page.setTotalCount(count);
        result= productPriceListDao.queryListPage(productPriceListModel, page.getPageNum(), page.getNumPerPage());
        }
        return result;
    }

    public void deleteById(ProductPriceListModel productPriceListModel)throws Exception
    {
        productPriceListDao.delete(productPriceListModel);
    }

    @Override
    public void addProductPriceList(ProductPriceListModel productPriceListModel) throws Exception {
        productPriceListDao.insert(productPriceListModel);
        
    }

    @Override
    public void updateProductPriceList(ProductPriceListModel productPriceListModel,ProductPriceListModel oldPriceModel) throws Exception {
        ProductModel productModel = productDao.getProductByCode(productPriceListModel.getProductSkuCode().substring(0,12));
        //修改加价率
        ProductOperateModel productOperateModel = new ProductOperateModel();
        productOperateModel.setProductId(productModel.getProductId());
        productOperateModel.setProductPriceRate(productPriceListModel.getPriceRate());
        productOperateDao.updateByProductId(productOperateModel);
        //修改价格
        productPriceListDao.update(productPriceListModel);
        
        ProductPriceLogModel productPriceLogModel = new ProductPriceLogModel();
        productPriceLogModel.setProductSkuCode(productPriceListModel.getProductSkuCode());
        productPriceLogModel.setCostPrice(productPriceListModel.getCostPrice());
        productPriceLogModel.setMarketPrice(productPriceListModel.getMarketPrice());
        productPriceLogModel.setTshPrice(productPriceListModel.getTshPrice());
        productPriceLogModel.setTb(productPriceListModel.getTb());
        productPriceLogModel.setOldCostPrice(oldPriceModel.getCostPrice());
        productPriceLogModel.setOldMarketPrice(oldPriceModel.getMarketPrice());
        productPriceLogModel.setOldTshPrice(oldPriceModel.getTshPrice());
        productPriceLogModel.setOldTb(oldPriceModel.getTb());
        productPriceLogModel.setCreateBy(oldPriceModel.getCreateBy());
        productPriceLogModel.setCreateUserName(oldPriceModel.getCreateUserName());
        productPriceLogModel.setCreateTime(oldPriceModel.getCreateTime());
        productPriceLogModel.setUpdateBy(oldPriceModel.getUpdateBy());
        productPriceLogModel.setUpdateTime(oldPriceModel.getUpdateTime());
        productPriceLogModel.setUpdateUserName(oldPriceModel.getUpdateUserName());
        productPriceLogModel.setSourceType(1);
        productPriceLogModel.setStatus(100);
        productPriceLogModel.setReason("加价率变更,旧值："+oldPriceModel.getPriceRate()+", 新值："+productPriceListModel.getPriceRate());
        productPriceLogDao.insert(productPriceLogModel);
    }

}
