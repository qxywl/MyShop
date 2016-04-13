package com.zhaidou.jobCenter.schedule.cmd;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.BaseNodeModeSchedule;
import com.zhaidou.product.model.ServerJobList;
import com.zhaidou.product.model.ServerNode;
import com.zhaidou.product.model.ServerNodeCondiction;
import com.zhaidou.product.service.ServerNodeService;

/**
 * 节点注册调度
 * 
 * @author kaili
 * 
 */
public class ServerNodeRegisterCmd extends Command {

	public static final Log logger = LogFactory.getLog(ServerNodeRegisterCmd.class);

	private static final String className = ServerNodeRegisterCmd.class.getSimpleName();
	
	
	public static Map<String, Integer> map=new HashMap<String, Integer>(); 

	/**
	 * 节点主键id
	 */
	private long nodeId = -1;

	/**
	 * 节点数
	 */
	private int nodeCount = -1;

	/**
	 * 节点序号
	 */
	private int nodeNo = -1;

	/**
	 * 正常签到节点次数
	 */
	private int normalCount = 0;

	/**
	 * 异常签到节点次数
	 */
	private int abnormalCount = 0;

	private int disabledMin = 5;

	@Resource
	private ServerNodeService serverNodeService;

	public int getNodeCount() {
		return nodeCount;
	}

	private void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
		BaseNodeModeSchedule.setTotalNodeByAuto(this.nodeCount);
	}

	public int getNodeNo() {
		return nodeNo;
	}

	private void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
		BaseNodeModeSchedule.setCurrentNodeByAuto(this.nodeNo);
	}

	public int getNormalCount() {
		return normalCount;
	}

	private void setNormalCount(int normalCount) {
		this.normalCount = normalCount;

		if (this.normalCount >= 2) {
			this.setAbnormalCount(0);
			this.startCenter();
		}

		if (this.normalCount == Integer.MAX_VALUE) {
			// 以防数据超出范围
			this.normalCount = 1000;
		}
	}

	public int getAbnormalCount() {
		return abnormalCount;
	}

	private void setAbnormalCount(int abnormalCount) {
		this.abnormalCount = abnormalCount;

		if (this.abnormalCount > 0) {
			this.setNormalCount(0);
			stopCenter();
		}
	}

	public int getDisabledMin() {
		return disabledMin;
	}

	public void setDisabledMin(int disabledMin) {
		this.disabledMin = disabledMin;
	}

	private void startCenter() {
		if (logger.isDebugEnabled()) {
			logger.debug("调度开关已经打开...");
		}
		BaseNodeModeSchedule.setSwitchStateByAuto(1);
	}

	private void stopCenter() {
		if (logger.isDebugEnabled()) {
			logger.debug("调度开关已经关闭...");
		}
		BaseNodeModeSchedule.setSwitchStateByAuto(0);
	}

	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");
		if (logger.isDebugEnabled()) {
			logger.debug("->Before register running");
			printServerNodeInfo();
		}

		long nodeIdBak = nodeId;
		int nodeCountBak = nodeCount;
		int nodeNoBak = nodeNo;
		int normalCountBak = normalCount;
		int abnormalCountBak = abnormalCount;

		// 失效时间点
		long overdueTime = System.currentTimeMillis() - 60000 * disabledMin;
		try {

			if (nodeCount == -1) {
				startUp(overdueTime);

			} else {
				running(overdueTime);
			}

			return true;
		} catch (Exception e) {
			// 回滚参数
			this.nodeId = nodeIdBak;
			this.setNodeCount(nodeCountBak);
			this.setNodeNo(nodeNoBak);
			this.setNormalCount(normalCountBak);
			this.setAbnormalCount(abnormalCountBak);

			context.put("message", e.getMessage());
			logger.error(e);
			return false;
		} finally {

			if (logger.isDebugEnabled()) {
				logger.debug("->After register running");
				printServerNodeInfo();
			}
			logExit(className, "doExecute");
		}
	}

	// 启动系统
	private void startUp(long overdueTime) {
		ServerNodeCondiction condiction = new ServerNodeCondiction();
		List<ServerNode> serverNodeList = this.serverNodeService.getServerNodes(condiction);
		//初始化集群相对应的任务
		ServerJobList serverJobList=this.serverNodeService.findByName(BaseNodeModeSchedule.getJobListName());
		if(null!=serverJobList&&null!=serverJobList.getJobList()){
			String list=serverJobList.getJobList();
			String[] job=list.split(",");
			for (int i = 0; i < job.length; i++) {
				map.put(job[i], 1);
				logger.debug("add job"+job[i]);
			}
		}
		if (serverNodeList.isEmpty()) {
			firstSignIn();
		} else {
			// 假定节点全部正常
			boolean normal = true;
			for (ServerNode item : serverNodeList) {
				Timestamp signTime = item.getUpdateTime();
				// 签到过期，有节点停止了服务
				if (signTime == null || signTime.getTime() < overdueTime) {
					normal = false;
					break;
				}
			}

			// 存在过期的签到数据
			if (!normal) {
				this.serverNodeService.deleteAll();
			}
			firstSignIn();
		}
	}

	// 系统运行
	private void running(long overdueTime) {
		Timestamp overdueTimePoint = new Timestamp(overdueTime);
		// 刷新签到
		int r = this.serverNodeService.refreshSign(this.nodeId);
		if (r > 0) {// 签到存在

			ServerNodeCondiction c = new ServerNodeCondiction();
			c.setUpdateTimeStart(overdueTimePoint);
			long cnt = this.serverNodeService.getServerNodeCount(c);

			if (nodeCount == (int) cnt) {
				// 签到正常
				this.setNormalCount(this.normalCount + 1);
			} else {
				// 查找过期签到的记录数
				c = new ServerNodeCondiction();
				c.setUpdateTimeEnd(overdueTimePoint);
				cnt = this.serverNodeService.getServerNodeCount(c);
				if (cnt > 0) {
					this.setAbnormalCount(this.abnormalCount + 1);
					cleanAndSignIn();
				} else {
					// 全部都是正常签到
					refreshSignIn();
				}

			}

		} else {// 不存在，签到被清除
			this.setAbnormalCount(this.abnormalCount + 1);

			ServerNodeCondiction c = new ServerNodeCondiction();
			c.setUpdateTimeEnd(overdueTimePoint);
			long cnt = this.serverNodeService.getServerNodeCount(c);
			if (cnt > 0) {
				// 存在过期的签到数据
				this.serverNodeService.deleteAll();
			}
			firstSignIn();

		}
	}

	// 清除并签到
	private void cleanAndSignIn() {
		if (this.abnormalCount >= 2) {
			this.serverNodeService.deleteAll();
			firstSignIn();
		}
	}

	// 首次签到
	private void firstSignIn() {
		long id = this.serverNodeService.signIn();
		ServerNodeCondiction c = new ServerNodeCondiction();
		long cnt = this.serverNodeService.getServerNodeCount(c);
		ServerNode serverNode = this.serverNodeService.getServerNode(id);
		this.nodeId = id;

		this.setNodeCount((int) cnt);
		this.setNodeNo(serverNode.getNodeNo());

		this.setNormalCount(1);
		this.setAbnormalCount(0);

	}

	// 刷新本次签到信息
	private void refreshSignIn() {
		ServerNodeCondiction c = new ServerNodeCondiction();
		long cnt = this.serverNodeService.getServerNodeCount(c);
		ServerNode serverNode = this.serverNodeService.getServerNode(this.nodeId);

		this.setNodeCount((int) cnt);
		this.setNodeNo(serverNode.getNodeNo());

		this.setNormalCount(this.nodeCount + 1);
		this.setAbnormalCount(0);
	}

	private void printServerNodeInfo() {
		logger.debug("ServerNodeInfo->nodeId=" + this.nodeId + ",nodeNo=" + this.nodeNo + ",nodeCount=" + this.nodeCount
				+ ",normalCount=" + this.normalCount + ",abnormalCount=" + this.abnormalCount);
	}

}
