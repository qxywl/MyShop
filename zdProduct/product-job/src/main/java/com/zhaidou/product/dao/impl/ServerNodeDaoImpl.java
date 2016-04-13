package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ServerNodeDao;
import com.zhaidou.product.model.ServerJobList;
import com.zhaidou.product.model.ServerNode;
import com.zhaidou.product.model.ServerNodeCondiction;


@Repository("serverNodeDao")
public class ServerNodeDaoImpl extends BaseDao  implements ServerNodeDao{

	@Override
	public long findNextKey() {
		Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".findNextKey");
		return count;
	}

	@Override
	public long findCntByCondiction(ServerNodeCondiction condiction) {
		Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".findCntByCondiction",condiction);
		return count;
	}

	@Override
	public List<ServerNode> findByCondiction(ServerNodeCondiction condiction) {
		List<ServerNode> list = getSqlSession().selectList(getNameSpace() + ".findByCondiction", condiction);
		return list;
	}

	@Override
	public ServerNode findById(Long id) {
		ServerNode serverNode=getSqlSession().selectOne(getNameSpace() + ".findById", id);
		return serverNode;
	}

	@Override
	public int updateServerNode(ServerNode serverNode) {
		int i=getSqlSession().update(getNameSpace() + ".updateServerNode", serverNode);
		return i;
	}

	@Override
	public Integer insertServerNodeMaxNo(ServerNode serverNode) {
		Integer i=getSqlSession().insert(getNameSpace() + ".insertServerNodeMaxNo", serverNode);
		return i;
	}

	@Override
	public int deleteAll() {
		Integer i=getSqlSession().delete(getNameSpace() + ".deleteAll");
		return i;
	}

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public ServerJobList findByName(String name) {
		ServerJobList serverJobList=getSqlSession().selectOne(getNameSpace() + ".findByName", name);
		return serverJobList;
	}

}
