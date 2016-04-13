package com.zhaidou.product.solr.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.zhaidou.core.solrj.query.QueryFieldCondition;
import com.zhaidou.core.solrj.query.SolrQueryBuilder;
import com.zhaidou.core.solrj.service.GenericSolrServiceImpl;
import com.zhaidou.core.solrj.service.SearchResult;
import com.zhaidou.product.model.MountRuleModel;
import com.zhaidou.product.solr.enums.GoodsIndexFieldEnum;
import com.zhaidou.product.solr.model.GoodsSolrModel;
import com.zhaidou.product.solr.service.GoodsSolrService;

@Service("goodsSolrService")
public class GoodsSolrServiceImpl extends GenericSolrServiceImpl implements GoodsSolrService{

	private static Logger LOG = Logger.getLogger(GoodsSolrServiceImpl.class);
	
	@Override
	public List<GoodsSolrModel> searchSolr(MountRuleModel model) throws SolrServerException{
		if(model == null){
			return null;
		}
		long begin=System.currentTimeMillis();
		SolrQuery solrQuery = this.buildSolrQuery(model);
		if(solrQuery == null){
			return null;
		}
		
		LOG.info("-----------自动挂载规则[id="+model.getId()+"]生成的Query----------"+solrQuery.toString());
		SearchResult<GoodsSolrModel> result = null;
		try {
			result = super.findAll(GoodsSolrModel.class, solrQuery);
			if(result == null){
				return null;
			}
		} catch (SolrServerException e) {
			LOG.error("查询索引失败\tSolrQuery:"+solrQuery.toString()+"\tclass:"+GoodsSolrModel.class,e);
			e.printStackTrace();
			throw e;
		}
		long end=System.currentTimeMillis();
		LOG.info("query solr for goods info execute searchKeywordFromSolr method time:"+(end-begin));
		return result.getBeanList();
	}
	
	/**
	 * @Description: 构造SolrQuery
	 * @param po
	 * @return SolrQuery
	 */
	private SolrQuery buildSolrQuery(MountRuleModel po){
		SolrQueryBuilder builder = new SolrQueryBuilder();
		// 构建主参数
		QueryFieldCondition mainCond = this.createMainCondition(po);
		if(null == mainCond){
			LOG.error("查询solr主参数为空！");
			return null;
		}
		builder.addMainQueryClauses(mainCond);
		
		//返回记录量
		builder.setStart(0);
		builder.setRows(99999);
				
		/**
		 * 获取SolrQuery
		 */
		return builder.solrQueryBuilder();
	}
	
	/**
	 * 构建搜索页面的主查询逻辑<br>
	 * 不带权重，只进行boolean查询
	 * @param po
	 * @return
	 */
	private QueryFieldCondition createMainCondition(MountRuleModel po){
		BooleanQuery mainBQ = new BooleanQuery();
		BooleanQuery tempBq;
		
		boolean allEmpty = true;
		
		// 商品名称
		if(StringUtils.isNotEmpty(po.getProductName())){
			PhraseQuery pq = new PhraseQuery();
			pq.add(new Term(GoodsIndexFieldEnum.GOODS_NAME.fieldName(), po.getProductName()));
			mainBQ.add(pq, Occur.MUST);
			allEmpty = false;
		}
		
		// 商品前缀
		if(StringUtils.isNotEmpty(po.getProductPrefix())){
			PhraseQuery pq = new PhraseQuery();
			pq.add(new Term(GoodsIndexFieldEnum.GOODS_PREFIX.fieldName(), po.getProductPrefix()));
			mainBQ.add(pq, Occur.MUST);
			allEmpty = false;
		}
		
		// 商品后缀
		if(StringUtils.isNotEmpty(po.getProductSuffix())){
			PhraseQuery pq = new PhraseQuery();
			pq.add(new Term(GoodsIndexFieldEnum.GOODS_SUFFIX.fieldName(), po.getProductSuffix()));
			mainBQ.add(pq, Occur.MUST);
			allEmpty = false;
		}
		
		// 搜索词【目前只按一个处理】
		if(StringUtils.isNotEmpty(po.getProductSearchTags())){
			PhraseQuery pq = new PhraseQuery();
			pq.add(new Term(GoodsIndexFieldEnum.ITEM_TAGS.fieldName(), po.getProductSearchTags()));
			mainBQ.add(pq, Occur.MUST);
			allEmpty = false;
		}
		
		/*
		 * 品牌编号列表
		 */
		if(StringUtils.isNotEmpty(po.getBrandCodeList())){
			tempBq = new BooleanQuery();
			String[] codes = po.getBrandCodeList().split(";");
			for(String c:codes){
				tempBq.add(new TermQuery(new Term(GoodsIndexFieldEnum.BRAND_CODE.fieldName(), c)), Occur.SHOULD);
			}
			mainBQ.add(tempBq, Occur.MUST);
			allEmpty = false;
		}
		
		// 基础分类编号列表
		if(StringUtils.isNotEmpty(po.getBaseCategoryCodeList())){
			tempBq = new BooleanQuery();
			String[] codes = po.getBaseCategoryCodeList().split(";");
			for(String c:codes){
				tempBq.add(new TermQuery(new Term(GoodsIndexFieldEnum.BASE_CATEGORY_CODE.fieldName(), c)), Occur.SHOULD);
			}
			mainBQ.add(tempBq, Occur.MUST);
			allEmpty = false;
		}
		if(allEmpty){
			return null;
		}
		return new QueryFieldCondition(mainBQ);
		
	}
	
}
