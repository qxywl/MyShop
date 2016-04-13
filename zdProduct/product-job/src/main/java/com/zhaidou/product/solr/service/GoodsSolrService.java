package com.zhaidou.product.solr.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.zhaidou.product.model.MountRuleModel;
import com.zhaidou.product.solr.model.GoodsSolrModel;

public interface GoodsSolrService {
	
	/**
	 * @Description: 根据自动挂载规则中的各字段值搜索商品列表
	 * @param model
	 * @return List<GoodsSolrModel>
	 */
	public List<GoodsSolrModel> searchSolr(MountRuleModel model) throws SolrServerException;
	
	
}
