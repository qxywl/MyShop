package com.zhaidou.product.solr.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.solr.client.solrj.SolrServerException;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.solr.model.GoodsSolrModel;

public interface GoodsSolrService {
	
	/**
	 * 分类列表搜索，带筛选区查询搜索结果方法<br>
	 * 同步进行与搜索结构匹配的分组查询(由业务逻辑制约)
	 * @param page 分页信息
	 * @param model 商品索引
	 * @return List<GoodsSolrModel>
	 * @throws SolrServerException
	 */
	public List<GoodsSolrModel> searchListFromSolr(Page page,GoodsSolrModel model) throws SolrServerException;


	/**
	 * 导出没有挂载运营分类的商品
	 * @param goodsList 商品数据
	 * @param cateList 分类数据
	 * @param titles excel表头信息
	 * @param fields 导出的GoodsSolrModel字段
	 * */
	public Workbook exportProNoCategoryExcel(List<GoodsSolrModel> goodsList,List<CategoryPO> cateList,
			List<String> titles, List<String> fields) throws Exception;

	
	/**
	 * 通过基础分类来导出没有挂载在运营分类下的商品
	 * 如果没有传基础分类code则全部导出
	 * */
	List<GoodsSolrModel> exportGoodsByBaseCatCode(GoodsSolrModel model)
			throws SolrServerException;
	
	
}
