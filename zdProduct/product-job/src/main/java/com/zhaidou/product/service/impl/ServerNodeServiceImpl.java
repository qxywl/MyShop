package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ServerNodeDao;
import com.zhaidou.product.model.ServerJobList;
import com.zhaidou.product.model.ServerNode;
import com.zhaidou.product.model.ServerNodeCondiction;
import com.zhaidou.product.service.ServerNodeService;

@Service("serverNodeService")
public class ServerNodeServiceImpl implements ServerNodeService {
	public static final Log logger = LogFactory.getLog(ServerNodeServiceImpl.class);

	private static final String className = ServerNodeServiceImpl.class.getSimpleName();

	@Resource
	private ServerNodeDao serverNodeDAO;

	void logEntry(String methodName) {
		String s = "--" + className + "--" + methodName + "--start--";
		logger.debug(s);
	}

	void logExit(String methodName) {
		String s = "--" + className + "--" + methodName + "--end--";
		logger.debug(s);
	}

	@Override
	public ServerNode getServerNode(Long id) {
		logEntry("getServerNode");
		logger.debug("--Params-->" + id);
		ServerNode result = this.serverNodeDAO.findById(id);
		if (result == null) {
			logger.warn("--Result-->null");
		}

		logger.debug(result);
		logExit("getServerNode");

		return result;
	}

	@Override
	public List<ServerNode> getServerNodes(ServerNodeCondiction condiction) {
		logEntry("getServerNodes");
		logger.debug("--Params-->" + condiction);
		List<ServerNode> result = this.serverNodeDAO.findByCondiction(condiction);
		if (result.isEmpty()) {
			logger.warn("--Result-->[]");
		}

		logger.debug(result);
		logExit("getServerNodes");

		return result;
	}

	@Override
	public long getServerNodeCount(ServerNodeCondiction condiction) {
		logEntry("getServerNodeCount");
		logger.debug("--Params-->" + condiction);
		long result = this.serverNodeDAO.findCntByCondiction(condiction);
		if (result < 1) {
			logger.warn("--Result-->0");
		}

		logger.debug(Long.valueOf(result));
		logExit("getServerNodeCount");

		return result;
	}

	@Override
	public long signIn() {
		logEntry("signIn");
		// logger.info("--Params-->" + );
		//long id = this.serverNodeDAO.findNextKey();
		ServerNode serverNode = new ServerNode();
		//serverNode.setId(new Long(id));
		Integer result = this.serverNodeDAO.insertServerNodeMaxNo(serverNode);
		long id = serverNode.getId();
		if (result < 1) {
			logger.warn("--Result-->0");
		}

		logger.debug(new Long(id));
		logExit("signIn");

		return id;
	}

	@Override
	public int refreshSign(Long id) {
		logEntry("refreshSign");
		logger.debug("--Params-->" + id);
		ServerNode serverNode = new ServerNode();
		serverNode.setId(new Long(id));
		int result = this.serverNodeDAO.updateServerNode(serverNode);
		if (result < 1) {
			logger.warn("--Result-->0");
		}

		logger.debug(Integer.valueOf(result));
		logExit("refreshSign");

		return result;
	}

	@Override
	public int deleteAll() {
		logEntry("deleteAll");
		// logger.info("--Params-->" + );
		int result = this.serverNodeDAO.deleteAll();
		if (result < 1) {
			logger.warn("--Result-->0");
		}

		logger.debug(Integer.valueOf(result));
		logExit("deleteAll");

		return result;
	}

	@Override
	public ServerJobList findByName(String name) {
		logEntry("findByName");
		logger.debug("--Params-->" + name);
		ServerJobList result = this.serverNodeDAO.findByName(name);
		if (result == null) {
			logger.warn("--Result-->null");
		}
		logger.debug(result);
		logExit("getServerNode");
		return result;
	}

}
