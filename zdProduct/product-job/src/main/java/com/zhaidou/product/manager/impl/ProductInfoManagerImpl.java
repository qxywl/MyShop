package com.zhaidou.product.manager.impl;

import com.zhaidou.ecerp.GoodsOperator;
import com.zhaidou.ecerp.GoodsQuery;
import com.zhaidou.ecerp.params.GoodsAdd;
import com.zhaidou.ecerp.params.GoodsModify;
import com.zhaidou.framework.jms.JmsMessageSender;
import com.zhaidou.framework.manager.impl.BaseManagerImpl;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductViewDao;
import com.zhaidou.product.manager.ProductInfoManager;
import com.zhaidou.product.model.*;
import com.zhaidou.product.service.*;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("productInfoManager")
public class ProductInfoManagerImpl  extends  BaseManagerImpl implements ProductInfoManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoManagerImpl.class);
	
	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条

	public static final int SOURCE_TYPE_ONE = 1; //后台来源

	private static  boolean JOB_Lock = true;
	
	private static  boolean IMP_Lock = true;	 
	
	private static  boolean CRE_Lock = true;	
	
	private static  boolean MEMCACHE_Lock = true;
	
	private static  boolean MQ_Lock = true;
	
    private static  boolean VIEW_Lock = true;

	
	@Resource
	private ProductInfoJobService productInfoJobService;
	
	@Resource
	private ProductInfoLogService productInfoLogService;
	
	@Resource
    private ProductService productService;

    @Resource
    private ProductViewDao productViewDao;
    
	@Resource
	private ProductInfoAuthService productInfoAuthService;
	
	@Resource
	private ProductToErpLogService productToErpLogService;
	
	@Resource(name="jmsMessageSender")
    private JmsMessageSender productInfoChangeSender;

	@Value("#{propertyConfigurerForProject2['retry_num']}")
    private Integer retryNum;

	//erp推送开关，on推送
	@Value("#{propertyConfigurerForProject2['erp_send_switch']}")
    private Integer erpSendSwitch;

	//改发送数据到erp
	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if (JOB_Lock) {
			JOB_Lock = false;

			ProductInfoJobModel infoJobModel = null;
			try {
				LOGGER.info("AutoProductInfoCmd:     do databae test");
				Page page = new Page();
				page.setNumPerPage(ROWS_ONE_TIME);
				ProductInfoJobModel productInfoJobModel = new ProductInfoJobModel();
				productInfoJobModel.setRemainder(configuration.getCurrentNode());
				productInfoJobModel.setTotal(configuration.getTotalNode());
				productInfoJobModel.setStatus(0);
				List<ProductInfoJobModel> list = productInfoJobService.getProductInfoJob(productInfoJobModel, page);
				if (null != list) {
					LOGGER.info("AutoProductInfoCmd:     list =" + ToStringBuilder.reflectionToString(list));
					for (int i = 0; i < list.size(); i++) {

						ProductInfoJobModel bb = list.get(i);
						infoJobModel = bb;
						if (bb.getRetryNum() < retryNum) {
							ProductInfoJobModel ProductInfo = list.get(i);
							if (ProductInfo.getNewvalue() != null && !"".equals(ProductInfo.getNewvalue()) &&
									ProductInfo.getSourceType() != null && ProductInfo.getSourceType() == SOURCE_TYPE_ONE
									&&"on".equals(erpSendSwitch)) {

								if (ProductInfo.getType().longValue() == 1) {
									//添加商品
									ProductModel productModel = (ProductModel) JSONUtil.toModel(ProductInfo.getNewvalue(), ProductModel.class);
									if (productModel != null) {
										Document goods = GoodsQuery.findGoods(productModel.getProductCode());
										Node node = goods.selectSingleNode("//shangpin_get_response//total_results");
										if (node != null && node.getText() != null && node.getText().equals("0")) {
											GoodsAdd goodsAdd = GoodsOperator.assembleGoodsAdd(productModel);
											LOGGER.info("goodsAdd =" + goodsAdd);
											Document rs = GoodsOperator.addGoods(goodsAdd);
											if (rs != null) {
												Node errorNode = rs.selectSingleNode("//AddSHANGPIN/ERROR");
												if (errorNode != null) {
													//添加日志
													addProductToErpLog(errorNode.getText(), goodsAdd.getSpdm(), goodsAdd.getSpmc());
													LOGGER.error("erp添加商品商品异常:" + errorNode.getText());
												} else {
													LOGGER.info("erp add goods success goodsAdd =" + goodsAdd);
												}
											} else {
												LOGGER.error("erp add goods fail goodsAdd =" + goodsAdd);
											}
										}

									}
								} else if (ProductInfo.getType().longValue() == 2 || ProductInfo.getType().longValue() == 3) {
									//修改商品
									Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(ProductInfo.getNewvalue(), String.class);

									ProductModel productModel = null;
									List<ProductSkuModel> insertListSku = null;
									if (mapValue.get("update") != null) {
										productModel = (ProductModel) JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
									}
									if (mapValue.get("insert") != null) {
										insertListSku = (List<ProductSkuModel>) JSONUtil.toCollection(mapValue.get("insert"), ProductSkuModel.class);
									}
									Document goods = GoodsQuery.findGoods(productModel.getProductCode());
									Node node = goods.selectSingleNode("//shangpin_get_response//total_results");
									if (node != null && node.getText() != null && node.getText().equals("1")) {
										GoodsModify goodsModify = GoodsOperator.assembleGoodsModify(productModel, insertListSku);
										LOGGER.info("goodsModify =" + goodsModify);
										Document rs = GoodsOperator.modifyGoods(goodsModify);
										if (rs != null) {
											Node errorNode = rs.selectSingleNode("//AddSHANGPIN/ERROR");
											if (errorNode != null) {
												//添加日志
												addProductToErpLog(errorNode.getText(), goodsModify.getSpdm(), goodsModify.getSpmc());
												LOGGER.info("erp编辑商品异常:" + errorNode.getText());
											} else {
												LOGGER.info(" erp modify goods success goodsModify =" + goodsModify);
											}
										} else {
											LOGGER.error(" erp modify goods fail goodsModify =" + goodsModify);
										}
									} else if (node != null && node.getText() != null && node.getText().equals("0")) {
										//TODO 如果是无此商品，是否重新插入
										GoodsAdd goodsAdd = GoodsOperator.assembleGoodsAdd(productModel);
										LOGGER.info("goodsModify goodsAdd =" + goodsAdd);
										Document rs = GoodsOperator.addGoods(goodsAdd);
										if (rs != null) {
											Node errorNode = rs.selectSingleNode("//AddSHANGPIN/ERROR");
											if (errorNode != null) {
												//添加日志
												addProductToErpLog(errorNode.getText(), goodsAdd.getSpdm(), goodsAdd.getSpmc());
												LOGGER.info("erp添加商品商品异常:" + errorNode.getText());
											} else {
												LOGGER.info("erp add goods success goodsAdd =" + goodsAdd);
											}
										} else {
											LOGGER.error("erp add goods fail goodsAdd =" + goodsAdd);
										}
									}
								}
							}
							bb.setStatus(1);
							productInfoJobService.updateProductInfoJob(bb);
						} else {
							bb.setStatus(5);
							productInfoJobService.updateProductInfoJob(bb);
						}
					}
				}
				LOGGER.info("AutoProductInfoCmd:     do databae  end ");
			} catch (Exception e) {
				LOGGER.error("AutoProductInfoCmd Exception infoJobModel ="+infoJobModel, e);
				throw new RuntimeException(infoJobModel.getProductLogId() + ":" + infoJobModel.getRetryNum());
			} finally {
				JOB_Lock = true;
			}
		} else {
			LOGGER.info("AutoProductInfoCmd  job  Last time not  end");
		}
	}
	
	@Override
	public void changeRetryNum(ProductInfoJobModel infoJobModel){
	    if(infoJobModel!=null){
            
            infoJobModel.setRetryNum(infoJobModel.getRetryNum()+1);
            try {
                productInfoJobService.updateProductInfoJob(infoJobModel);
            } catch (Exception e1) {
				LOGGER.error("updateProductInfoJob fail infoJobModel =" + infoJobModel, e1);
			}
		}
	}

	/**
	 * 推送商品错误日志具体方法
	 * @param msg
	 * @param productCode
	 * @param productName
	 * @throws Exception
	 */
	public void addProductToErpLog(String msg, String productCode, String productName){
		ProductToErpLogModel productToErpLogModel = new ProductToErpLogModel();
		productToErpLogModel.setErrorMessage(msg);
		productToErpLogModel.setProductCode(productCode);
		productToErpLogModel.setProductName(productName);
		try {
			productToErpLogService.addProductToErpLog(productToErpLogModel);
			LOGGER.info("添加推送商品错误日志 success productToErpLogModel =" + productToErpLogModel);
		} catch (Exception e) {
			LOGGER.error("添加推送商品错误日志 fail productToErpLogModel ="+productToErpLogModel,e);
		}
	}
	
	@Override
	public void imoprt(ProductObjBufCondiction configuration) {
		 if(IMP_Lock){
		        IMP_Lock=false;
		try {
			LOGGER.info("AutoProductInfoCmd:     do imoprt databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
			productInfoAuthModel.setRemainder(configuration.getCurrentNode());
			productInfoAuthModel.setTotal(configuration.getTotalNode());
			productInfoAuthModel.setStaus(0l);
			List<ProductInfoAuthModel> list = productInfoAuthService.getProductInfoAuth(productInfoAuthModel, page);
			if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				ProductInfoAuthModel bb=list.get(i);
				//修改状态
				bb.setStaus(1l);
				productInfoAuthService.updateProductInfoAuth(bb);
			}
			}
		} catch (Exception e) {
			LOGGER.error("",e);
			throw new RuntimeException(e);
		} finally{
			IMP_Lock=true;
		}
        }else{
        	LOGGER.info("AutoProductInfoCmd  imoprt  Last time not  end");
        }
		
	}

	//审核通过的商品变更信息，插入到t_product_info_job
	@Override
	public void createJob(ProductObjBufCondiction configuration) {
		if(CRE_Lock){
			CRE_Lock=false;
		try {
			LOGGER.info("AutoProductInfoCmd:     do createJob databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
			productInfoAuthModel.setRemainder(configuration.getCurrentNode());
			productInfoAuthModel.setTotal(configuration.getTotalNode());
			productInfoAuthModel.setStaus(2l);
			List<ProductInfoAuthModel> list = productInfoAuthService.getProductInfoAuth(productInfoAuthModel, page);
			if(null!=list && list.size()>0){
			    Map<String,Object> infoAuthMap=new HashMap<String, Object>();
	            List<Long> authIds4 = new ArrayList<Long>();
	            List<Long> authIds3 = new ArrayList<Long>();
	            List<ProductInfoJobModel> infoJobList = new ArrayList<ProductInfoJobModel>();
			    
    			for (int i = 0; i < list.size(); i++) {
    				ProductInfoAuthModel bb=list.get(i);
    				ProductInfoJobModel productInfoJobModel = new ProductInfoJobModel();
    				productInfoJobModel.setType(bb.getType());
    				productInfoJobModel.setCreatetime(bb.getCreateTime());
    				productInfoJobModel.setProductCode(bb.getProductCode());
    				productInfoJobModel.setProductSkuId(bb.getProductSkuId());
    				productInfoJobModel.setProductInfoAuthId(bb.getProductInfoAuthId());
    				productInfoJobModel.setNewvalue(bb.getNewValue());
    				productInfoJobModel.setSourceType(bb.getSourceType());
    				productInfoJobModel.setStatus(0);
    				infoJobList.add(productInfoJobModel);
    				
    				if(null==bb.getNewValue()||"".equals(bb.getNewValue())){
    				    authIds4.add(bb.getProductInfoAuthId());
    				}else{
    				    authIds3.add(bb.getProductInfoAuthId());
    				}
    			}
    			
    			//批量插入infoJob
    			long addStart = new Date().getTime()/1000;
    			productInfoJobService.addInfoJobList(infoJobList);
    			long addEnd = new Date().getTime()/1000;
    			LOGGER.info("infoTime,createJob,批量插入infoJob表,总记录数："+infoJobList.size()+"条,花费时间："+(addEnd-addStart)+"秒");
    			
    			//批量修改infoAuth状态
    			if(authIds3.size()>0){
    			    infoAuthMap.put("status", 3);
                    infoAuthMap.put("list", authIds3);
                    long updateStart3 = new Date().getTime()/1000;
                    productInfoAuthService.updateStatusList(infoAuthMap);
                    long updateEnd3 = new Date().getTime()/1000;
                    LOGGER.info("infoTime,createJob,修改infoAuth状态 2-3,总记录数："+authIds3.size()+"条,花费时间："+(updateEnd3-updateStart3)+"秒");
    			}
               
                
                //批量修改infoAuth状态
    			if(authIds4.size()>0){
    			    infoAuthMap.put("status", 4);
                    infoAuthMap.put("list", authIds4);
                    long updateStart4 = new Date().getTime()/1000;
                    productInfoAuthService.updateStatusList(infoAuthMap);
                    long updateEnd4 = new Date().getTime()/1000;
                    LOGGER.info("infoTime,createJob,修改infoAuth状态 2-4,总记录数："+authIds4.size()+"条,花费时间："+(updateEnd4-updateStart4)+"秒");
    			}
			}
		} catch (Exception e) {
			LOGGER.error("createJob Exception ",e);
			throw new RuntimeException(e);
		} finally{
			CRE_Lock=true;
		}
        }else{
        	LOGGER.info("AutoProductInfoCmd createJob   Last time not  end");
        }
	}

    @Override
    public void productToExamine(ProductObjBufCondiction configuration) {
//        if(IMP_Lock){
//            IMP_Lock=false;
//            try {
//                LOGGER.info("AutoProductInfoCmd:     do imoprt databae");
//                Page page = new Page();
//                page.setNumPerPage(ROWS_ONE_TIME);
//                SupplierInfoAuthModel supplierInfoAuthModel = new SupplierInfoAuthModel();
//                supplierInfoAuthModel.setStatus(0l);
//                supplierInfoAuthModel.setRemainder(configuration.getCurrentNode());
//                supplierInfoAuthModel.setTotal(configuration.getTotalNode());
//                List<SupplierInfoAuthModel> list = supplierInfoAuthService.getProduct(supplierInfoAuthModel, page);
//                
//                ProductInfoAuthModel productInfoAuthModel = null;
//                for (int i = 0; i < list.size(); i++) {
//                    //处理 相同商品并未审核的数据
//                    List<ProductInfoAuthModel> infoAuthList = productInfoAuthService.getInfoAuthByProductCode(list.get(i).getProductCode());
//                    if(infoAuthList!=null && infoAuthList.size()>0){
//                        for(ProductInfoAuthModel infoAuthModel:infoAuthList){
//                            if(infoAuthModel.getStaus()==1 || infoAuthModel.getStaus()==0){
//                                infoAuthModel.setStaus(6l);
//                                infoAuthModel.setReason("系统自动处理旧数据");
//                                infoAuthModel.setUpdateBy(1l);
//                                infoAuthModel.setUpdateTime(new Date().getTime()/1000);
//                                infoAuthModel.setUpdateUserName("admin");
//                                productInfoAuthService.updateProductInfoAuth(infoAuthModel);
//                            }
//                        }
//                    }
//                    productInfoAuthModel = new ProductInfoAuthModel();
//                    productInfoAuthModel.setCreateBy(list.get(i).getCreateBy());
//                    productInfoAuthModel.setCreateTime(list.get(i).getCreateTime());
//                    productInfoAuthModel.setCreateUserName(list.get(i).getCreateUserName());
//                    productInfoAuthModel.setNewValue(list.get(i).getNewValue());
//                    productInfoAuthModel.setType(list.get(i).getType());
//                    productInfoAuthModel.setStaus(list.get(i).getStatus());
//                    productInfoAuthModel.setSourceType(list.get(i).getSourceType());
//                    productInfoAuthModel.setProductCode(list.get(i).getProductCode());
//                    productInfoAuthModel.setOldValue(list.get(i).getOldValue());
//                    productInfoAuthService.addProductInfoAuth(productInfoAuthModel);
//                    
//                    ProductInfoExamineModel examineModel=new ProductInfoExamineModel();
//                    ProductModel productModel=null;
//                    if(productInfoAuthModel.getType().longValue()==1){
//                        //添加商品 
//                        productModel = (ProductModel)JSONUtil.toModel(productInfoAuthModel.getNewValue(), ProductModel.class);
//                    }else if(productInfoAuthModel.getType().longValue()==2 || productInfoAuthModel.getType().longValue()==3){
//                        //修改商品 
//                        Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(productInfoAuthModel.getNewValue(), String.class);
//                        
//                        if(mapValue.get("update")!=null){
//                            productModel = (ProductModel)JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
//                        }
//                    }
//                    
//                    examineModel.setProductInfoAuthId(productInfoAuthModel.getProductInfoAuthId());
//                    examineModel.setProductName(productModel.getProductName());
//                    examineModel.setProductCode(productModel.getProductCode());
//                    examineModel.setCatCode(productModel.getCatCode());
//                    examineModel.setCatName(productModel.getCatName());
//                    examineModel.setBrandCode(productModel.getBrandCode());
//                    examineModel.setBrandName(productModel.getBrandName());
//                    examineModel.setSupplierId(productModel.getSupplierId());
//                    examineModel.setProductDesc(productModel.getProductDesc());
//                    examineModel.setProductShelves(productModel.getProductShelves());
//                    examineModel.setProductId(productModel.getProductId());
//                    examineModel.setProductWeight(productModel.getProductMallModel().getProductWeight());
//                    examineModel.setProductLong(productModel.getProductMallModel().getProductLong());
//                    examineModel.setProductWidth(productModel.getProductMallModel().getProductWidth());
//                    examineModel.setProductHeight(productModel.getProductMallModel().getProductHeight());
//                    examineModel.setProductProducer(productModel.getProductMallModel().getProductProducer());
//                    examineModel.setProductDensity(productModel.getProductMallModel().getProductDensity());
//                    examineModel.setProductAtrNumber(productModel.getProductMallModel().getProductAtrNumber());
//                    
//                    examineModel.setProductShortName(productModel.getProductOperateModel().getProductShortName());
//                    examineModel.setProductPrefix(productModel.getProductOperateModel().getProductPrefix());
//                    examineModel.setProductSuffix(productModel.getProductOperateModel().getProductSuffix());
//                    examineModel.setProductKeyword(productModel.getProductOperateModel().getProductKeyword());
//                    examineModel.setProductDownShow(productModel.getProductOperateModel().getProductDownShow());
//                    examineModel.setProductPriceRate(productModel.getProductOperateModel().getProductPriceRate());
//                    examineModel.setProductVideoUrl(productModel.getProductOperateModel().getProductVideoUrl());
//                    productInfoExamineService.add(examineModel);
//                    
//                    supplierInfoAuthService.deleteById(list.get(i));
//                }
//                DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_ONE);
//            } catch (Exception e) {
//                LOGGER.error("",e);
//                throw new RuntimeException(e);
//            } finally{
//                IMP_Lock=true;
//            }
//        }else{
//            LOGGER.info("AutoProductInfoCmd  imoprt  Last time not  end");
//        }
    }
    
    @Override
    public void productAuditFailure(ProductObjBufCondiction configuration) {
//        if(CRE_Lock){
//            CRE_Lock=false;
//        try {
//            LOGGER.info("AutoProductInfoCmd:     do createJob databae");
//            Page page = new Page();
//            page.setNumPerPage(ROWS_ONE_TIME);
//            ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
//            productInfoAuthModel.setRemainder(configuration.getCurrentNode());
//            productInfoAuthModel.setTotal(configuration.getTotalNode());
//            productInfoAuthModel.setStaus(8l);
//            List<ProductInfoAuthModel> list = productInfoAuthService.getProductInfoAuth(productInfoAuthModel, page);
//            
//            Map<String,Object> infoAuthMap=new HashMap<String, Object>();
//            List<Long> authIds = new ArrayList<Long>();
//            List<ProductInfoLogModel> infoLogList = new ArrayList<ProductInfoLogModel>();
//            
//            if(null!=list && list.size()>0){
//            for (int i = 0; i < list.size(); i++) {
//                ProductInfoAuthModel bb=list.get(i);
//               
//                authIds.add(bb.getProductInfoAuthId());
//                //插入日志
//                ProductInfoLogModel productInfoLogModel = new ProductInfoLogModel();
//                productInfoLogModel.setCreateBy(bb.getCreateBy());
//                productInfoLogModel.setCreateTime(bb.getCreateTime());
//                productInfoLogModel.setCreateUserName(bb.getCreateUserName());
//                productInfoLogModel.setUpdateBy(bb.getUpdateBy());
//                productInfoLogModel.setUpdateTime(bb.getUpdateTime());
//                productInfoLogModel.setUpdateUserName(bb.getUpdateUserName());
//                productInfoLogModel.setNewValue(bb.getNewValue());
//                productInfoLogModel.setOldValue(bb.getOldValue());
//                productInfoLogModel.setProductCode(bb.getProductCode());
//                productInfoLogModel.setSourceType(bb.getSourceType());
//                productInfoLogModel.setStatus(5);
//                productInfoLogModel.setReason(bb.getReason());
//                productInfoLogModel.setType(bb.getType());
//                infoLogList.add(productInfoLogModel);
//                
//                // 同步到 supplier-web 供应商平台
//                ProductModel supplierPrdouctModel = new ProductModel();
//                supplierPrdouctModel.setProductCode(bb.getProductCode());
//                supplierPrdouctModel.setStaus(8l);
//                supplierPrdouctModel.setReason(bb.getReason());
////                productSupplierDao.updateSupplierProductByProductCode(supplierPrdouctModel);
//            }
//            
//            //批量修改infoAuth状态
//            infoAuthMap.put("status", 5);
//            infoAuthMap.put("synTime", new Date().getTime()/1000);
//            infoAuthMap.put("list", authIds);
//            long updateStart = new Date().getTime()/1000;
//            productInfoAuthService.updateStatusList(infoAuthMap);
//            long updateEnd = new Date().getTime()/1000;
//            LOGGER.info("infoTime,productAuditFailure, 审核失败,修改infoAuth状态 8-5,总记录数："+authIds.size()+"条,花费时间："+(updateEnd-updateStart)+"秒");
//            
//            //批量插入日志
//            long addLogStart = new Date().getTime()/1000;
//            productInfoLogService.addInfoLogList(infoLogList);
//            long addLogEnd = new Date().getTime()/1000;
//            LOGGER.info("infoTime,productAuditFailure, 审核失败,批量插入日志,总记录数："+infoLogList.size()+"条,花费时间："+(addLogEnd-addLogStart)+"秒");
//            
//            }
//        } catch (Exception e) {
//            LOGGER.error(e);
//            throw new RuntimeException(e);
//        } finally{
//            CRE_Lock=true;
//        }
//        }else{
//            LOGGER.info("AutoProductInfoCmd createJob   Last time not  end");
//        }
    }

	//发到memcache
	@Override
	public void sendMemcache(ProductObjBufCondiction configuration) {
		if(MEMCACHE_Lock){
			MEMCACHE_Lock=false;
		try {
			LOGGER.info("AutoProductInfoCmd:     do memcache");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductInfoJobModel productInfoJobModel = new ProductInfoJobModel();
			productInfoJobModel.setRemainder(configuration.getCurrentNode());
			productInfoJobModel.setTotal(configuration.getTotalNode());
			productInfoJobModel.setStatus(2);
			List<ProductInfoJobModel> list = productInfoJobService.getProductInfoJob(productInfoJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			List<Long> ids = new ArrayList<Long>();
            Map<String,Object> map1 = new HashMap<String, Object>();
			if(null!=list && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
			    ids.add(list.get(i).getProductLogId());
				map.put("product_"+list.get(i).getProductCode(), list.get(i).getProductCode());
			}
			for (String key : map.keySet()) {
				// 删除缓存
				this.getCachedClient().releaseObject(key);
				this.getCachedClient().releaseObject("product_mall_"+map.get(key));
				this.getCachedClient().releaseObject("product_info_"+map.get(key));
				this.getCachedClient().releaseObject("product_map_sku_view_"+map.get(key));
				this.getCachedClient().releaseObject("product_"+map.get(key));
			}
			
			map1.put("status", 3);
            map1.put("list", ids);
            
            long updateStart = new Date().getTime()/1000;
            productInfoJobService.updateStatusList(map1);
            long updateEnd = new Date().getTime()/1000;
            LOGGER.info("infoTime, sendMemcache, 修改JOB状态 2-3,总记录数："+ids.size()+"条,花费时间："+(updateEnd-updateStart)+"秒");
//			for (int i = 0; i < list.size(); i++) {
//				ProductInfoJobModel bb=list.get(i);
//				bb.setStatus(3);
//                productInfoJobService.updateProductInfoJob(bb);
//			}
			}
			LOGGER.info("AutoProductInfoCmd:     do memcache end");
		} catch (Exception e) {
			LOGGER.error("",e);
			throw new RuntimeException(e);
		}finally{
			MEMCACHE_Lock=true;
		}
        }else{
        	LOGGER.info("AutoProductInfoCmd   memcache job  Last time not  end");
        }
		
	}

	//发送到mq
	@Override
	public void sendMq(ProductObjBufCondiction configuration) {
		if(MQ_Lock){
			MQ_Lock=false;
		try {
			LOGGER.info("AutoProductInfoCmd:     do sendMq");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductInfoJobModel productInfoJobModel = new ProductInfoJobModel();
			productInfoJobModel.setRemainder(configuration.getCurrentNode());
			productInfoJobModel.setTotal(configuration.getTotalNode());
			productInfoJobModel.setStatus(3);
			List<ProductInfoJobModel> list = productInfoJobService.getProductInfoJob(productInfoJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			
			Map<String,Object> infoAuthMap=new HashMap<String, Object>();
			List<Long> authIds = new ArrayList<Long>();
			List<Long> jobIds = new ArrayList<Long>();
			List<ProductInfoLogModel> infoLogList = new ArrayList<ProductInfoLogModel>();
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    			    ProductInfoJobModel bb=list.get(i);
    			    
    			    authIds.add(bb.getProductInfoAuthId());
    			    jobIds.add(bb.getProductLogId());
    				map.put("product_"+bb.getProductCode(), bb.getProductCode());
    				
    				
                    ProductInfoAuthModel productInfoAuthModel=new ProductInfoAuthModel();
                    productInfoAuthModel.setProductInfoAuthId(bb.getProductInfoAuthId());
                    ProductInfoAuthModel pp = productInfoAuthService.getProductInfoAuthById(productInfoAuthModel);
                        //插入日志
                       ProductInfoLogModel productInfoLogModel = new ProductInfoLogModel();
                       productInfoLogModel.setCreateBy(pp.getCreateBy());
                       productInfoLogModel.setCreateTime(pp.getCreateTime());
                       productInfoLogModel.setCreateUserName(pp.getCreateUserName());
                       productInfoLogModel.setUpdateBy(pp.getUpdateBy());
                       productInfoLogModel.setUpdateTime(pp.getUpdateTime());
                       productInfoLogModel.setUpdateUserName(pp.getUpdateUserName());
                       productInfoLogModel.setNewValue(pp.getNewValue());
                       productInfoLogModel.setOldValue(pp.getOldValue());
                       productInfoLogModel.setProductCode(pp.getProductCode());
                       productInfoLogModel.setSourceType(pp.getSourceType());
                       productInfoLogModel.setStatus(4);
                       productInfoLogModel.setReason(pp.getReason());
                       productInfoLogModel.setType(pp.getType());
                       infoLogList.add(productInfoLogModel);
    			}
    			for (String key : map.keySet()) {
    				// 发送MQ
    				productInfoChangeSender.sendMessage(false, "product_", map.get(key));
    			}
    			//批量删除JOB
    			long deleteStart = new Date().getTime()/1000;
    			productInfoJobService.deleteByIds(jobIds);
    			long deleteEnd = new Date().getTime()/1000;
    			LOGGER.info("infoTime, sendMq, 删除JOB,总记录数："+authIds.size()+"条,花费时间："+(deleteEnd-deleteStart)+"秒");
    			
    			//批量修改infoAuth状态
    			infoAuthMap.put("status", 4);
    			infoAuthMap.put("synTime", new Date().getTime()/1000);
    			infoAuthMap.put("list", authIds);
    			long updateStart = new Date().getTime()/1000;
    			productInfoAuthService.updateStatusList(infoAuthMap);
    			long updateEnd = new Date().getTime()/1000;
    			LOGGER.info("infoTime, sendMq, 修改infoAuth状态 3-4,总记录数："+authIds.size()+"条,花费时间："+(updateEnd-updateStart)+"秒");
    			
    			//批量插入日志
    			long addLogStart = new Date().getTime()/1000;
                productInfoLogService.addInfoLogList(infoLogList);
                long addLogEnd = new Date().getTime()/1000;
                LOGGER.info("infoTime, sendMq, 批量插入日志,总记录数："+infoLogList.size()+"条,花费时间："+(addLogEnd-addLogStart)+"秒");
			}
			LOGGER.info("AutoProductInfoCmd:     do sendMq  end ");
		} catch (Exception e) {
			LOGGER.error("",e);
			throw new RuntimeException(e);
		}finally{
			MQ_Lock=true;
		}
        }else{
        	LOGGER.info("AutoProductInfoCmd sendMq job  Last time not  end");
        }
		
	}

	//创建视图
	@Override
	public void createView(ProductObjBufCondiction configuration) {
		if(VIEW_Lock){
			VIEW_Lock=false;
		try {
			LOGGER.info("AutoProductInfoCmd:     do createView");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductInfoJobModel productInfoJobModel = new ProductInfoJobModel();
			productInfoJobModel.setRemainder(configuration.getCurrentNode());
			productInfoJobModel.setTotal(configuration.getTotalNode());
			productInfoJobModel.setStatus(1);
			List<ProductInfoJobModel> list = productInfoJobService.getProductInfoJob(productInfoJobModel, page);
			List<String> codes = new ArrayList<String>();
			List<Long> ids = new ArrayList<Long>();
			Map<String,Object> map = new HashMap<String, Object>();
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    			    ids.add(list.get(i).getProductLogId());
    			    codes.add(list.get(i).getProductCode());
    			}
    			if(codes.size()>0){
    			    Long viewStart = new Date().getTime()/1000;
    			    productViewDao.deleteSkuViewByProductCodes(codes);
    			    productViewDao.addSkuViewList(codes);
    			    productViewDao.deleteMallViewByProductCodes(codes);
    			    productViewDao.addMallViewList(codes);
    			    productViewDao.updateMallViewZeroType(codes);
    			    Long viewEnd  = new Date().getTime()/1000;
    			    LOGGER.info("infoTime, createView, 横表处理数据："+codes.size()+"条,花费时间："+(viewEnd-viewStart)+"秒");
    			}
    			
    			map.put("status", 2);
    			map.put("list", ids);
    			
    			long updateStart = new Date().getTime()/1000;
    //			for (int i = 0; i < list.size(); i++) {
    //				ProductInfoJobModel bb=list.get(i);
    //				bb.setStatus(2);
    //                productInfoJobService.updateProductInfoJob(bb);
    //			}
    			productInfoJobService.updateStatusList(map);
    			long updateEnd = new Date().getTime()/1000;
    			LOGGER.info("infoTime, createView, 修改JOB状态 1-2,总记录数："+ids.size()+"条,花费时间："+(updateEnd-updateStart)+"秒");
			}
			LOGGER.info("AutoProductInfoCmd:     do createView  end ");
		} catch (Exception e) {
			LOGGER.error("",e);
			throw new RuntimeException(e);
		}finally{
			VIEW_Lock=true;
		}
        }else{
        	LOGGER.info("AutoProductInfoCmd  createView  job  Last time not  end");
        }
		
	}

}
