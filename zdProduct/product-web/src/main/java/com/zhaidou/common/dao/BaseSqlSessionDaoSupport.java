package com.zhaidou.common.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
*<p>Title: BaseSqlSessionDaoSupport </p>
*@Description: 注入数据源
*@Author:kaili
*@version:1.0
*@DATE:2013-8-17下午02:54:30
*@see
*/
public abstract class BaseSqlSessionDaoSupport extends SqlSessionDaoSupport {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@PostConstruct
	protected void setSqlSessionTemplate() {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	protected String getPrefix() {
		return this.getClass().getSimpleName() + ".";
	}

}
