package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.ServerJobList;
import com.zhaidou.product.model.ServerNode;
import com.zhaidou.product.model.ServerNodeCondiction;

/**
 * 服务节点业务处理
 * 
 * @author caizhan
 * 
 */
public interface ServerNodeService {

	/**
	 * 由id取得实体
	 * 
	 * @param id
	 * @return
	 */
	public ServerNode getServerNode(Long id);

	/**
	 * 由条件取得实体
	 * 
	 * @param condiction
	 * @return
	 */
	public List<ServerNode> getServerNodes(ServerNodeCondiction condiction);

	/**
	 * 由条件取得数量
	 * 
	 * @param condiction
	 * @return
	 */
	public long getServerNodeCount(ServerNodeCondiction condiction);

	/**
	 * 首次签到
	 * 
	 * @return 签到id
	 */
	public long signIn();

	/**
	 * 刷新签到
	 * 
	 * @return 记录数
	 */
	public int refreshSign(Long id);

	public int deleteAll();
	
	public ServerJobList findByName(String name);

}
