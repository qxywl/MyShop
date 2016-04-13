package com.zhaidou.product.info.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductPurchaseStockDao;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;
import com.zhaidou.product.info.service.ProductPurchaseStockService;

@Service("productPurchaseStockService")
public class ProductPurchaseStockServiceImpl implements ProductPurchaseStockService{
	
	@Resource
	private ProductPurchaseStockDao productPurchaseStockDao;
	
	  /** slf4j*/
    private static final Logger  LOGGER = LoggerFactory.getLogger(ProductPurchaseStockServiceImpl.class);

	
	
	/**
	 * 获取采购库列表（分页）
	 */
	@Override
	public List<ProductPurchaseStockModel> purchaseStockList(ProductPurchaseStockModel productPurchaseStockModel, Page page)throws Exception {
		List<ProductPurchaseStockModel> result = null;
		if(page != null){
			long count = productPurchaseStockDao.countListPage(productPurchaseStockModel);
			if(count > 0){
				page.setTotalCount(count);
				result = productPurchaseStockDao.queryListPage(productPurchaseStockModel, page.getPageNum(), page.getNumPerPage());
			}
		}
		return result;
	}

	/**
	 * 分配公共库
	 */
	@Override
	public boolean purchaseStockDistribuTion(String[] ids, String userName) {
		if(ids == null || ids.length == 0){
			return Boolean.FALSE;
		}
		Boolean flag = Boolean.TRUE;
		Long id = null;
		for(String arrtId : ids){
			try {
				id = Long.parseLong(arrtId);
				ProductPurchaseStockModel productPurchaseStockModel = new ProductPurchaseStockModel();
				productPurchaseStockModel.setId(id);
				productPurchaseStockModel.setAssignStatus(1);
				productPurchaseStockModel.setAssignBy(userName);
				productPurchaseStockDao.update(productPurchaseStockModel);
			} catch (Exception e) {
				e.printStackTrace();
				flag = Boolean.FALSE;
			}
		}
		return flag;
	}

	/**
	 * 获取所有列表
	 */
	@Override
	public List<ProductPurchaseStockModel> getpurchaseStockListByStatus() {
		return productPurchaseStockDao.getpurchaseStockListByStatus(); 
	}

	/**
	 * 修改导出数据状态以及操作人
	 */
	@Override
	public void updateAssignStatusAndAssignBy(Map<String, Object> map) {
		productPurchaseStockDao.updateAssignStatusAndAssignBy(map);
	}
	
  @Override
    public long countListPage(Long purchaseId)  {
	  ProductPurchaseStockModel productPurchaseStockModel = new ProductPurchaseStockModel();
	  productPurchaseStockModel.setPurchaseId(purchaseId);
        try {
            return productPurchaseStockDao.countListPage(productPurchaseStockModel);
        } catch (Exception e) {
            LOGGER.error("query productPurchaseStockDao error",e);
            throw new ZhaidouRuntimeException();
        }
    }

  /**
   * 插入一条数据
   */
    @Override
    public void insertProductPurchaseStockList(List<ProductPurchaseStockModel> productPurchaseStockModels) {

        for (ProductPurchaseStockModel productPurchaseStockModel : productPurchaseStockModels) {
            try {
                productPurchaseStockDao.insert(productPurchaseStockModel);
                LOGGER.info("同步采购入库单成功 success, productPurchaseStockModel =" + productPurchaseStockModel);
            } catch (Exception e) {
                LOGGER.error("同步采购入库单异常 faile, productPurchaseStockModel =" + productPurchaseStockModel, e);
            }
        }

    }

}
