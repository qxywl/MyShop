package com.zhaidou.product.manager.impl;

import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.message.manager.MessageManager;
import com.zhaidou.message.po.EmailPO;
import com.zhaidou.product.enums.MountProductOptType;
import com.zhaidou.product.enums.MountType;
import com.zhaidou.product.manager.MountRuleManager;
import com.zhaidou.product.model.*;
import com.zhaidou.product.service.MountProductLogService;
import com.zhaidou.product.service.MountProductService;
import com.zhaidou.product.service.MountRuleService;
import com.zhaidou.product.service.ProductSalecategoryRelationService;
import com.zhaidou.product.solr.model.GoodsSolrModel;
import com.zhaidou.product.solr.service.GoodsSolrService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("mountRuleManager")
public class MountRuleManagerImpl implements MountRuleManager {

private final static int PAGE_SIZE=900;
	
	@Value("#{propertyConfigurerForProject2['mount_rule_job_user']}")
	private  String OPERATE_USER;//操作人
	
	@Value("#{propertyConfigurerForProject2['mount_rule_included_maxSize']}")
	private Integer mountRuleIncludedMaxSize;//一个自动挂载规则最大允许的商品数量
	
	@Value("#{propertyConfigurerForProject2['mount_rule_error_email_receive_list']}")
	private String mountRuleErrorEmailReceiveList;//自动规则设置异常时的收件人列表
	
	@Value("#{propertyConfigurerForProject2['mount_rule_error_email_cc_list']}")
	private String mountRuleErrorEmailCCList;//自动规则设置异常时的抄送人列表
	
	@Value("#{propertyConfigurerForProject2['mount_rule_email_subject']}")
	private String emalSubject;//邮件主题
	
	@Value("#{propertyConfigurerForProject2['mount_rule_email_body']}")
	private String emalBody;//邮件正文
	
	
	private static  boolean JOB_LOCK = false;	 
	
	//默认用户名
	private static final String DEFAULT_USER_NAME="admin";
	
	private static Logger LOG = Logger.getLogger(MountRuleManagerImpl.class);
	
	@Autowired
	private MountRuleService mountRuleService;
	
	@Autowired
	private GoodsSolrService goodsSolrService;
	
	@Autowired
	private MountProductService mountProductService;
	
	@Autowired
	private MountProductLogService mountProductLogService;
	
	@Autowired
	private ProductSalecategoryRelationService productSalecategoryRelationService;
	
	@Autowired
    private MessageManager messageManager;
	
	List<GoodsSolrModel> gList; //查询索引后得到的商品列表
	Set<String> gSet;//索引中商品编号集合
	MountProductModel spr;
	Map<String,MountProductModel> opt1Map;//opt_type=1的商品集合  key:productCode  value：对象
	Map<String,MountProductModel> opt2Map;//opt_type=2的商品集合 key:productCode  value：对象
	Set<String> mount2Set;//手动挂载的列表 [value:productCode]
	List<MountProductModel> sprList;//运营分类与商品对应关系表中opt_type=1的商品列表
	List<MountProductModel> optSprList;//需要删除的或保存的
	
	@Override
	public boolean doJob(ProductObjBufCondiction conf) {
		if(JOB_LOCK){
			LOG.info("===================================自动规则解析JOB 正在 运行，退出本次运行======================================"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
			return true;
		}
		//为空时给默认值
		if(mountRuleIncludedMaxSize == null || mountRuleIncludedMaxSize.intValue() < 1){
			mountRuleIncludedMaxSize = 5000;
		}
		
		JOB_LOCK = true;
		long totStart = System.currentTimeMillis();
		long start = System.currentTimeMillis();
		LOG.info("===================================自动规则解析JOB运行开始======================================"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(totStart)));
		if(StringUtils.isEmpty(OPERATE_USER)){
			OPERATE_USER = DEFAULT_USER_NAME;
		}
		
		// 0.查询总记录数
		Page page = new Page();
		page.setNumPerPage(PAGE_SIZE);
		page.setPageNum(1);
		BalanceInfoModel balance = new BalanceInfoModel();
		balance.setRemainder(conf.getCurrentNode());
		balance.setTotal(conf.getTotalNode());
		try {
			Long tot = mountRuleService.totCountByEffective(balance);
			if(tot==null){
				LOG.info("\t\t===============有效的自动挂载规则总量为空===============");
				//return false;
			}
			if(tot.longValue() == 0){
				LOG.info("\t\t===============有效的自动挂载规则总量为0===============");
				//return false;
			}
			page.setTotalCount(tot.longValue());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("\t\t===============查询有效的自动挂载规则总量，出错===============");
			this.setJobLockValueToFalse();
			return false;
		}
		LOG.info("\t===================================0.查询总记录数======================================共耗时："+(System.currentTimeMillis()-start)+" 毫秒");
		
		
		// 1. 分页加载有效的自动挂载规则
		List<MountRuleModel> mrList = null;
		try {
			//{pageStart},#{numPerPage}
			balance.setStart(page.getPageStart());
			balance.setRows(page.getNumPerPage());
			mrList = mountRuleService.queryPage(balance);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("\t\t===============1.分页[pageNum=1]查询自动挂载规则数据出错===============");
			this.setJobLockValueToFalse();
			return false;
		}
		if(mrList == null || mrList.size() == 0){
			LOG.info("\t\t===============1. 分页[pageNum=1]加载有效的自动挂载规则,没有规则数据===============");
		}
		
		/**
		 * 超出最大允许的商品数量的运营分类编码集合
		 */
		Set<String> errorCateCodeSet = new HashSet<String>();
		
		spr = new MountProductModel();
		while(mrList != null && mrList.size()>0){
			for(MountRuleModel po:mrList){
				// A.解析自动挂载规则，得到每个运营分类对应的商品商品列表
				try {
					gList = goodsSolrService.searchSolr(po);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.info("\t\t===============1.A.查询索引出错===============");
					this.setJobLockValueToFalse();
					throw new RuntimeException("查询索引出错");
				}
				if(gList == null || gList.size()==0){
					LOG.info("\t\t--------1.A.自动挂载规则[id="+po.getId()+"]解析后，没有商品---------");
					continue; 
				}
				
				if(gList.size() > mountRuleIncludedMaxSize){
					errorCateCodeSet.add(po.getCategoryCode());
					continue;
				}
				
				this.tiquProductCodeSet();
				//B. 更新数据 t_salecategory_product_relation
				 //B-1.先根据分类查询原挂载商品数据 (opt_type=1  ，以备后边使用)，不使用条件  mount_type=2
				spr.setCategoryId(po.getCategoryId());
				try {
					sprList = mountProductService.selectOpt1ByCategoryId(spr);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.info("\t\t===============1.B-1.根据运营分类ID加载其下所有opt_type=1的商品,出错===============");
					this.setJobLockValueToFalse();
					throw new RuntimeException("根据运营分类ID加载其下所有opt_type=1的商品,出错");
				}
				this.tiquMountRuleProductCodes();
				// B-2.再根据分类删除分类下原挂载商品数据 (软删除：opt_type=2 , )
				try{
					this.deleteOldRuleProduct(po);
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t===============1.B-2.根据分类删除分类下原挂载商品数据,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
				/*
				 * 写日志  
				 */
				try{
					this.insertRuleProductLog();
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============== 1.B-2.写 分类-商品关系表的操作日志,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
				// B-3.插入新的列表
				try{
					this.insertNewRuleProduct(po);
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============== 1.B-3分类下新挂载商品数据插入,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
				// C. 更新表t_product_salecategory_relation
				//C-1.先根据分类删除分类下原挂载商品数据 (直接update 把;categoryCode;替换为空)
				//C-2.剔除categoryCodes字段为空的记录
				try{
					this.deleteOldRedundantProduct();
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============== 1.C-1、2.根据分类删除分类下原挂载商品数据 && 剔除categoryCodes字段为空的记录,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
				// C-3. 插入新的值
				try{
					this.insertNewRedundantProduct(po);
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============== 1.C-3.【冗余表】根据商品编号添加分类编号,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
			}
			
			//这个判断有点怪，原因：page.getPageNum()中先判断了pageNum>totalPage时做了赋值pageNum=totalPage
			if (page.getPageNum() == page.getTotalPage()) {
				break;
			}
			page.setPageNum(page.getPageNum()+1);
			try {
				balance.setStart(page.getPageStart());
				balance.setRows(page.getNumPerPage());
				mrList = mountRuleService.queryPage(balance);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.info("\t\t============While. 分页[pageNum="+page.getPageNum()+"]查询自动挂载规则数据出错================");
				this.setJobLockValueToFalse();
				throw new RuntimeException("解析自动挂载规则出错[分页(pageNum="+page.getPageNum()+")查询自动挂载规则数据]");
			}
		}
		LOG.info("\t===================================1.分页加载有效的自动挂载规则======================================共耗时："+(System.currentTimeMillis()-start)+" 毫秒");
		
		//有异常情况的挂载规则，发邮件
		if(errorCateCodeSet.size() > 0){
			start = System.currentTimeMillis();
			boolean result = this.sendEmail(errorCateCodeSet);
			LOG.info("\t===================================1.1. 发送邮件===============result:"+result+"=======================共耗时："+(System.currentTimeMillis()-start)+" 毫秒");
		}
		
		start = System.currentTimeMillis();
		// 2. 查询昨天删除的自动挂载规则
		mrList = null;
		String yesterdayStr = this.buildYesterdayStr();
		try {
			balance.setStart(0);
			balance.setRows(0);
			balance.setDateStr(yesterdayStr);
			mrList = mountRuleService.queryDeleteRuleByDelDate(balance);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("\t\t==================2. 查询昨天删除的自动挂载规则,出错====================");
			this.setJobLockValueToFalse();
			return false;
		}
		if(mrList == null || mrList.size()==0){
			LOG.info("\t\t--------2. 昨天["+yesterdayStr+"]没有删除的自动挂载规则数据--------");
			//return true;
		}else{
			for(MountRuleModel po:mrList){
				// A.解析自动挂载规则，得到每个运营分类对应的商品商品列表
				try {
					gList = goodsSolrService.searchSolr(po);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.info("\t\t=============== 2.A. 查询索引出错,出错===============");
					this.setJobLockValueToFalse();
					throw new RuntimeException("查询索引出错");
				}
				if(gList == null || gList.size()==0){
					LOG.info("\t\t--------2.A. 自动挂载规则[id="+po.getId()+"]解析后，没有商品---------");
					continue;
				}
				this.tiquProductCodeSet();
				
				//B. 更新数据 t_salecategory_product_relation
				 //B-1.先根据分类查询原挂载商品数据 (opt_type=1  ，以备后边使用)，不使用条件  mount_type=2
				spr.setCategoryId(po.getCategoryId());
				try {
					sprList = mountProductService.selectOpt1ByCategoryId(spr);
				} catch (Exception e) {
					e.printStackTrace();
					LOG.info("\t\t=============2.B-1. 根据运营分类ID加载其下所有opt_type=1的商品,出错==================");
					this.setJobLockValueToFalse();
					throw new RuntimeException("查询索引出错");
				}
				
				this.tiquMountRuleProductCodes();
				
				// B-2.再根据分类删除分类下原挂载商品数据 (软删除：opt_type=2 , )
				try{
					this.deleteOldRuleProduct(po);
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============2.B-2.再根据分类删除分类下原挂载商品数据,出错==================");
					this.setJobLockValueToFalse();
					throw e;
				}
				/*
				 * 写日志
				 */
				try{
					this.insertRuleProductLog();
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t===============2.B-2.根据分类删除分类下原挂载商品数据,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
				
				// C. 更新表t_product_salecategory_relation
				//C-1.先根据分类删除分类下原挂载商品数据 (直接update 把;categoryCode;替换为空)
				//C-2.剔除categoryCodes字段为空的记录
				try{
					this.deleteOldRedundantProduct();
				}catch(RuntimeException e){
					e.printStackTrace();
					LOG.info("\t\t=============== 2.C-1、2.根据分类删除分类下原挂载商品数据 && 剔除categoryCodes字段为空的记录,出错===============");
					this.setJobLockValueToFalse();
					throw e;
				}
			}
		}
		LOG.info("\t===================================2. 处理昨天删除的自动挂载规则======================================共耗时："+(System.currentTimeMillis()-start)+" 毫秒");
		
		long totEnd = System.currentTimeMillis();
		LOG.info("===================================自动规则解析JOB运行结束======================================共耗时："+(totEnd-totStart)+" 毫秒");
		
		this.setJobLockValueToFalse();
		
		return true;
	}
	
	private void setJobLockValueToFalse(){
		JOB_LOCK = false;
	}
	
	/**
	 * @Description: 发送邮件
	 * @param codeSet
	 * @return boolean
	 */
	private boolean sendEmail(Set<String> codeSet) {
		if(StringUtils.isBlank(mountRuleErrorEmailReceiveList)){
			return false;
		}
		EmailPO po = new EmailPO();
		po.setMto(mountRuleErrorEmailReceiveList);
		if(StringUtils.isNotBlank(mountRuleErrorEmailCCList)){
			po.setCc(mountRuleErrorEmailCCList);
		}
		po.setSubject(emalSubject);
		
		String currentBody = "";
		if(StringUtils.isNotBlank(emalBody)){
			currentBody = emalBody;
			currentBody = currentBody.replace("{maxCount}", mountRuleIncludedMaxSize.toString());
			currentBody = currentBody.replace("{codeList}", this.buildEmailBody(codeSet));
		}
		po.setBody(currentBody);
		po.setCreateBy(OPERATE_USER);

		ResponseObject resp = null;
		try{
			resp = messageManager.addEmail(po);
		 } catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("自动挂载规则解析时，发送Email出错：",e);
			 return false;
		 }
		if(resp.getCode() == 0){
			return true;
		}
		LOG.info("自动挂载规则解析时，发送Email出错:"+resp.getMsg());
		return false;
	}
	
	private String buildEmailBody(Set<String> codeSet){
		if(codeSet.size() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String c:codeSet){
			sb.append(c).append(";");
		}
		return sb.toString();
	}
	
	/**
	 * @Description:  返回当前时间点对应的昨天日期
	 * @return  String   格式  yyyy-MM-dd
	 */
	private String buildYesterdayStr(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * @Description: 提取商品编号
	 * return void
	 */
	private void tiquProductCodeSet(){
		gSet = new HashSet<String>();
		for(GoodsSolrModel g:gList){
			gSet.add(g.getGoodsCode());
		}
	}
	
	/**
	 * @Description: 把分类下对应的商品列表归类
	 * return void
	 */
	private void tiquMountRuleProductCodes(){
		opt1Map = new HashMap<String,MountProductModel>();
		opt2Map = new HashMap<String,MountProductModel>();
		mount2Set = new HashSet<String>();
		if(sprList != null && sprList.size() > 0){
			for(MountProductModel tmp:sprList){
				/*
				 * 此处的处理逻辑是：自动挂载不能覆盖手工挂载，反之可以
				 */
				if(tmp.getMountType().intValue() != MountType.AUTO.getValue().intValue()){
					mount2Set.add(tmp.getProductCode());
					continue;
				}
				if(tmp.getOptType().intValue() == MountProductOptType.UPDATE.getValue()){
					opt1Map.put(tmp.getProductCode(),tmp);
					continue;
				}
				opt2Map.put(tmp.getProductCode(),tmp);
			}
		}
	}
	
	/**
	 * @Description:  写 分类-商品关系表的操作日志
	 * @throws RuntimeException
	 * return void
	 */
	private void insertRuleProductLog() throws RuntimeException{
		if(optSprList.size()>0){
			try{
				mountProductLogService.batchInsert(this.getMountProductLogList(optSprList, null));
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("写 分类-商品关系表的操作日志,失败：",e);
				throw new RuntimeException("写 分类-商品关系表的操作日志,失败",e);
			}
		}
	}
	
	/**
	 * @Description:  构造修改日志
	 * @param mpList
	 * @param failedReson
	 * return List<MountProductLog>
	 */
	private List<MountProductLogModel> getMountProductLogList(List<MountProductModel> mpList, String failedReson) {
		if (mpList == null || mpList.size() == 0) {
			return null;
		}
		List<MountProductLogModel> logList = new ArrayList<MountProductLogModel>(mpList.size());
		MountProductLogModel mountProductLog ;
		for (MountProductModel m : mpList) {
			mountProductLog = new MountProductLogModel();
			mountProductLog.setRelationId(m.getRelationId());
			mountProductLog.setCategoryId(m.getCategoryId());
			mountProductLog.setCategoryCode(m.getCategoryCode());
			mountProductLog.setProductId(m.getProductId());
			mountProductLog.setProductCode(m.getProductCode());
			mountProductLog.setMountType(m.getMountType());
			mountProductLog.setOrderValue(m.getOrderValue());
			mountProductLog.setOptType(m.getOptType());
			if(failedReson != null && failedReson.length() != 0){
				mountProductLog.setDescription(failedReson);
			}
			mountProductLog.setOperateUser(m.getLastUpdateUser());
			mountProductLog.setOperateTime(m.getLastUpdateTime());
			mountProductLog.setLogDate(new Date(System.currentTimeMillis()));
			logList.add(mountProductLog);
		}
		return logList;
	}
	
	/**
	 * @Description: 根据分类删除分类下原挂载商品数据 (软删除：opt_type=2 , )
	 * @param po
	 * @throws RuntimeException
	 * return void
	 */
	private void deleteOldRuleProduct(MountRuleModel po) throws RuntimeException{
		optSprList = new ArrayList<MountProductModel>();
		Set<String> tmpSet = opt1Map.keySet();;//productCate列表
		MountProductModel tmpSpr;
		for(String c:tmpSet){
			if(gSet.contains(c)){
				continue;
			}
			tmpSpr = new MountProductModel();
			tmpSpr.setRelationId(opt1Map.get(c).getRelationId());
			tmpSpr.setOptType(MountProductOptType.DELETE.getValue());
			tmpSpr.setLastUpdateTime(new Date(System.currentTimeMillis()));
			tmpSpr.setLastUpdateUser(OPERATE_USER);
			//删除
			try {
				mountProductService.update(tmpSpr);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("根据运ID["+tmpSpr.getRelationId()+"]删除数据出错：",e);
				throw new RuntimeException("根据运ID["+tmpSpr.getRelationId()+"]删除数据出错");
			}
			tmpSpr.setCategoryCode(po.getCategoryCode());
			tmpSpr.setCategoryId(po.getCategoryId());
			tmpSpr.setProductCode(c);
			optSprList.add(tmpSpr);
		}
	}
	
	/**
	 * @Description: 分类下新挂载商品数据插入
	 * @param po
	 * @throws RuntimeException
	 * return void
	 */
	private void insertNewRuleProduct(MountRuleModel po) throws RuntimeException{
		optSprList = new ArrayList<MountProductModel>();
		MountProductModel tmpSpr;
		for(GoodsSolrModel g:gList){
			//如果原来是手工挂载的，不能覆盖，直接跳过
			if(mount2Set.contains(g.getGoodsCode())){
				continue;
			}
			tmpSpr = new MountProductModel();
			tmpSpr.setCategoryCode(po.getCategoryCode());
			tmpSpr.setCategoryId(po.getCategoryId());
			tmpSpr.setProductCode(g.getGoodsCode());
			tmpSpr.setProductId(g.getId().longValue());
			tmpSpr.setOptType(MountProductOptType.UPDATE.getValue());
			tmpSpr.setMountType(MountType.AUTO.getValue());
			//默认排序值给0
			tmpSpr.setOrderValue(0l);
			tmpSpr.setMountTime(new Date(System.currentTimeMillis()));
			tmpSpr.setMountUser(OPERATE_USER);
			tmpSpr.setLastUpdateTime(new Date(System.currentTimeMillis()));
			tmpSpr.setLastUpdateUser(OPERATE_USER);
//				try {
//					mountProductService.insert(tmpSpr);
//				} catch (Exception e) {
//					e.printStackTrace();
//					LOG.error("根据运ID["+tmpSpr.getRelationId()+"]删除数据出错：",e);
//					throw new RuntimeException("根据运ID["+tmpSpr.getRelationId()+"]删除数据出错");
//				}
			
			optSprList.add(tmpSpr);
		}
		if(optSprList.size()>0){
			try {
				mountProductService.updateOrBatchInsert(optSprList, po.getCategoryCode());
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("批量插入运营分类-商品关系表数据，出错：",e);
				throw new RuntimeException("批量插入运营分类-商品关系表数据,出错");
			}
		}
		
	}
	
	/**
	 * @Description:  【冗余表】根据商品编号删除分类下原挂载商品数据 (直接update 把;categoryCode;替换为空) ,并且剔除categoryCodes字段为空的记录
	 * @throws RuntimeException
	 * return void
	 */
	private void deleteOldRedundantProduct() throws RuntimeException{
		Set<String> tmpSet = opt1Map.keySet();
		ProductSalecategoryRelationModel psr;
		for(String c:tmpSet){
			//修改时过滤掉本次要添加的
			if(gSet.contains(c)){
				continue;
			}
			psr = new ProductSalecategoryRelationModel();
			psr.setProductCode(c);
			psr.setCategoryCodes(";"+opt1Map.get(c).getCategoryCode()+";");
			
			try {
				productSalecategoryRelationService.updateCategorys(psr);
			} catch (Exception e) {
				LOG.error("根据商品编号 ，把运营分类编号替换为空,操作失败:",e);
				throw new RuntimeException("根据商品编号 ，把运营分类编号替换为空,操作失败",e);
			}
		}
		// 剔除categoryCodes字段为空的记录
		try {
			productSalecategoryRelationService.deleteByCategorysIsEmpty();
		} catch (Exception e) {
			LOG.error("剔除categoryCodes字段为空的记录,操作失败：",e);
		}
	}
	
	/**
	 * @Description: 【冗余表】根据商品编号添加分类编号
	 * @param po
	 * @throws RuntimeException
	 * return void
	 */
	private void insertNewRedundantProduct(MountRuleModel po) throws RuntimeException{
		//List<ProductSalecategoryRelationModel> psrList = new ArrayList<ProductSalecategoryRelationModel>();//商品对应运营分类列表集合
		ProductSalecategoryRelationModel psr;
		for(GoodsSolrModel g:gList){
			//如果原来是手工挂载的，不能覆盖，直接跳过
			if(mount2Set.contains(g.getGoodsCode())){
				continue;
			}
			psr = new ProductSalecategoryRelationModel();
			psr.setProductCode(g.getGoodsCode());
			psr.setCategoryCodes(po.getCategoryCode());
			try {
				productSalecategoryRelationService.insertOrUpdate(psr, MountProductOptType.UPDATE.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("【冗余表】根据商品编号添加分类编号,操作失败:",e);
				throw new RuntimeException("【冗余表】根据商品编号添加分类编号,操作失败",e);
			}
			//psrList.add(psr);
		}
		//没有批量
//			if(psrList.size()>0){
//				/*
//				 * 批量插入
//				 */
//			}
	}

	public void setMountRuleService(MountRuleService mountRuleService) {
		this.mountRuleService = mountRuleService;
	}

	public void setGoodsSolrService(GoodsSolrService goodsSolrService) {
		this.goodsSolrService = goodsSolrService;
	}

	public void setMountProductService(MountProductService mountProductService) {
		this.mountProductService = mountProductService;
	}

	public void setMountProductLogService(
			MountProductLogService mountProductLogService) {
		this.mountProductLogService = mountProductLogService;
	}

	public void setProductSalecategoryRelationService(
			ProductSalecategoryRelationService productSalecategoryRelationService) {
		this.productSalecategoryRelationService = productSalecategoryRelationService;
	}

	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}
	
}
