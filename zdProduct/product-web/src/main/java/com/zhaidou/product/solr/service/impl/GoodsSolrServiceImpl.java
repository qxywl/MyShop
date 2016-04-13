package com.zhaidou.product.solr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.core.solrj.service.GenericSolrServiceImpl;
import com.zhaidou.core.solrj.service.SearchResult;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.solr.model.GoodsSolrModel;
import com.zhaidou.product.solr.service.GoodsSolrService;

/***
 * 查询商品索引
 * */
@Service("goodsSolrService")
public class GoodsSolrServiceImpl extends GenericSolrServiceImpl implements GoodsSolrService {

	
	private Logger logger = Logger.getLogger(GoodsSolrServiceImpl.class);
	
	
	@Override
	public List<GoodsSolrModel> searchListFromSolr(Page page,GoodsSolrModel model) throws SolrServerException{
	
	    String flFields = "id,goodsName,goodsCode,brandName,brandCode,baseCateCodes,price";
		
	    StringBuffer queryStr = new StringBuffer("-catagoryCodes:*");
	    
		if(StringUtils.isNotEmpty(model.getBrandCode())){
			queryStr.append(" ");
			queryStr.append("+brandCode:"+model.getBrandCode());
		}
		if(StringUtils.isNotEmpty(model.getBrandName())){
			queryStr.append(" ");
			queryStr.append("+brandName:"+model.getBrandName());
		}
		
		if(model.getBaseCateCodes() != null && model.getBaseCateCodes().length > 0){
			String baseCateCode = model.getBaseCateCodes()[0];
			queryStr.append(" ");
			queryStr.append("+baseCateCodes:"+baseCateCode);
		}
		
		SolrQuery query= new SolrQuery(queryStr.toString());
		query.set("rows", 0);//查找总数，不返回字段
		
		long totalCount = queryTotalCount(query);
		List<GoodsSolrModel> list = null;
		
		//查询没有挂载在基础分类下的商品
		if(totalCount >0 ){
			page.setTotalCount(totalCount);
			int start = (page.getPageNum() - 1)*page.getNumPerPage();
			query.set("start", start +"");
			query.set("rows", page.getNumPerPage() + "");
			query.setFields(flFields);
			SearchResult<GoodsSolrModel> ret = queryIndex(query);
			list = ret.getBeanList();
		}
		return list;
	}

	
	/**
	 * @Description: 查询索引
	 * @param builder
	 * @return SearchResult<GoodsSolrModel>
	 */
	private SearchResult<GoodsSolrModel> queryIndex(SolrQuery solrQuery){
		
		/**
		 * 获取SolrQuery
		 */
		SearchResult<GoodsSolrModel> ret = null;
		try {
			ret = super.findAll(GoodsSolrModel.class,solrQuery);
		} catch (SolrServerException e) {
			logger.error("查询索引失败\tSolrQuery:"+solrQuery.toString()+"\tclass:"+GoodsSolrModel.class,e);
		}
		return ret;
	}
	
	
	public long queryTotalCount(SolrQuery solrQuery){
		
		SearchResult<GoodsSolrModel> ret = null;
		
		try {
			ret = super.findAll(GoodsSolrModel.class,solrQuery);
			logger.info(ret.getResponseHeader());
			
		} catch (SolrServerException e) {
			logger.error("查询索引失败\tSolrQuery:"+solrQuery.toString()+"\tclass:"+GoodsSolrModel.class,e);
		}
		return ret.getTotalHits();
	}
	
	
	
	@Override
	public List<GoodsSolrModel> exportGoodsByBaseCatCode(GoodsSolrModel model) throws SolrServerException{
	
	    String flFields = "id,goodsName,goodsCode,brandName,brandCode,baseCateCodes,price";
	    StringBuffer queryStr = new StringBuffer("-catagoryCodes:*");
		if(model.getBaseCateCodes() != null && model.getBaseCateCodes().length > 0){
			String [] baseCatecodes = model.getBaseCateCodes();
			queryStr.append(" ");
			queryStr.append("+");
			queryStr.append("(");
			int i=0;
			for (String baseCode : baseCatecodes) {
				if(i != 0){
					queryStr.append(" ");
				}
				queryStr.append("baseCateCodes:"+baseCode);
				i++;
			}
			queryStr.append(")");
		}
		
		SolrQuery query= new SolrQuery(queryStr.toString());
		query.set("rows", 0);//查找总数，不返回字段
		
		long totalCount = queryTotalCount(query);
		List<GoodsSolrModel> list = null;
		
		//查询没有挂载在基础分类下的商品
		if(totalCount >0 ){
			query.set("start", "0");
			query.set("rows",  totalCount + "");
			query.setFields(flFields);
			SearchResult<GoodsSolrModel> ret = queryIndex(query);
			list = ret.getBeanList();
		}
		return list;
	}
	
	
	@Override
	public Workbook exportProNoCategoryExcel(List<GoodsSolrModel> goodsList,List<CategoryPO> cateList,List<String> titles, List<String> fields) throws Exception{

		
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		
		XSSFSheet sheet = workBook.createSheet("2015");// 创建一个Excel的Sheet
		ExportUtil exportUtil = new ExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();

		Map<String ,CategoryPO> levelAll =  new HashMap<String,CategoryPO>();
        for (CategoryPO cate : cateList){
        	levelAll.put(cate.getCategoryCode(),cate);
        }
		
		 //1.创建的表头
        Row sysRow = sheet.createRow(0);
        sysRow.setRowStyle(headStyle);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = sysRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles.get(i));
        }
        
        //2.填写数据
        int j = 1;
        for (GoodsSolrModel data : goodsList) {
           
        	Row sysRowJ = sheet.createRow(j); //创建行
        	/*
        	 * fields:"goodsCode","goodsName","brandCode","brandName","baseCateCode1","baseCateName1","baseCateCode2","baseCateName2",
        	 * "baseCateCode3","baseCateName3","price"
        	 * */
        	
        	Cell cell0 = sysRowJ.createCell(0);
        	cell0.setCellValue(data.getGoodsCode());
        	
        	Cell cell1 = sysRowJ.createCell(1);
        	cell1.setCellValue(data.getGoodsName());
        	
        	Cell cell2 = sysRowJ.createCell(2);
        	cell2.setCellValue(data.getBrandCode());
        	
        	Cell cell3 = sysRowJ.createCell(3);
        	cell3.setCellValue(data.getBrandName());
        	
        	Cell cell4 = sysRowJ.createCell(4);
        	Cell cell5 = sysRowJ.createCell(5);
        	
        	Cell cell6 = sysRowJ.createCell(6);
        	Cell cell7 = sysRowJ.createCell(7);
        	
        	Cell cell8 = sysRowJ.createCell(8);
        	Cell cell9 = sysRowJ.createCell(9);
        	Cell cell10 = sysRowJ.createCell(10);
        	
        	String [] baseCateCodes = data.getBaseCateCodes();
        	if(baseCateCodes != null && baseCateCodes.length>0){
        		for (String cateCode : baseCateCodes) {
            		CategoryPO category = levelAll.get(cateCode);
            		if(category != null){
            			if(cateCode.length() == 2){//一级
        					cell4.setCellValue(category.getCategoryCode());
        					cell5.setCellValue(category.getCategoryName());
        					continue;
        				}
            			if(cateCode.length() == 4){//二级
        					cell6.setCellValue(category.getCategoryCode());
        					cell7.setCellValue(category.getCategoryName());
        					continue;
        				}
            			if(cateCode.length() == 6){//三级
        					cell8.setCellValue(category.getCategoryCode());
        					cell9.setCellValue(category.getCategoryName());
        					continue;
        				}
            			
            		}
            		
    			}
        	}
        	
        	cell10.setCellValue(data.getPrice()== null?"":""+data.getPrice());
            j++;  // 控制行数
        }
        return workBook;
		
	}


	
}
