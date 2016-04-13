package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ServerJobList;
import com.zhaidou.product.model.ServerNode;
import com.zhaidou.product.model.ServerNodeCondiction;

/**
 * 商品中心对象临时表操作类
 * 
 * @author caizhan
 * 
 */
public interface ServerNodeDao  extends IDao {

	/**
	 * 查询下一个key
	 * 
	 * @return
	 */
	public long findNextKey();

	public long findCntByCondiction(ServerNodeCondiction condiction);

	/**
	 * 按条件查询
	 * 
	 * @param condiction
	 * @return
	 */
	public List<ServerNode> findByCondiction(ServerNodeCondiction condiction);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	public ServerNode findById(Long id);

	/**
	 * 更新实体
	 * 
	 * @param serverNode
	 * @return
	 */
	public int updateServerNode(ServerNode serverNode);

	/**
	 * 新增实体
	 * 
	 * @param serverNode
	 * @return
	 */
	public Integer insertServerNodeMaxNo(ServerNode serverNode);

	public int deleteAll();
	
	public ServerJobList findByName(String name);

}
