package com.zhaidou.jobCenter.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaidou.jobCenter.schedule.cmd.ServerNodeRegisterCmd;

public abstract class BaseNodeModeSchedule implements ScheduleConfiguration {
	public static final Log logger = LogFactory.getLog(BaseNodeModeSchedule.class);

	/**
	 * 是否是签到配置的cmd，此cmd特殊处理
	 */
	private int isRegisterCmd;
	/**
	 * 是否使用自动签到节点来配置
	 */
	private int settingByAuto;
	/**
	 * 调度开关
	 */
	private int switchState;
	/**
	 * 当前节点
	 */
	private int currentNode;
	/**
	 * 总节点数
	 */
	private int totalNode;
	
	/**
	 * 集群名称
	 */
	private static String  jobListName;
	
	/**
	 * 系统信息签到调度总开关，在settingByAuto=1时有效
	 */
	private static int switchStateByAuto;

	/**
	 * 自动签到时，当前节点
	 */
	private static int currentNodeByAuto;

	/**
	 * 自动签到时，总节点数
	 */
	private static int totalNodeByAuto;
	
	
	public static String getJobListName() {
		return jobListName;
	}

	public static void setJobListName(String jobListName) {
		BaseNodeModeSchedule.jobListName = jobListName;
	}

	public int getIsRegisterCmd() {
		return isRegisterCmd;
	}

	public void setIsRegisterCmd(int isRegisterCmd) {
		this.isRegisterCmd = isRegisterCmd;
	}

	public int getSettingByAuto() {
		return settingByAuto;
	}

	public void setSettingByAuto(int settingByAuto) {
		this.settingByAuto = settingByAuto;
	}

	public int getSwitchState() {
		return switchState;
	}

	public void setSwitchState(int switchState) {
		this.switchState = switchState;
	}

	public int getCurrentNode() {
		if (settingByAuto == 1) {
			return currentNodeByAuto;
		} else {
			return currentNode;
		}
	}

	public void setCurrentNode(int currentNode) {
		this.currentNode = currentNode;
	}

	public int getTotalNode() {
		if (settingByAuto == 1) {
			return totalNodeByAuto;
		} else {
			return totalNode;
		}
	}

	public void setTotalNode(int totalNode) {
		this.totalNode = totalNode;
	}

	public static int getSwitchStateByAuto() {
		return switchStateByAuto;
	}

	public static void setSwitchStateByAuto(int switchStateByAuto) {
		BaseNodeModeSchedule.switchStateByAuto = switchStateByAuto;
	}

	public static int getCurrentNodeByAuto() {
		return currentNodeByAuto;
	}

	public static void setCurrentNodeByAuto(int currentNodeByAuto) {
		BaseNodeModeSchedule.currentNodeByAuto = currentNodeByAuto;
	}

	public static int getTotalNodeByAuto() {
		return totalNodeByAuto;
	}

	public static void setTotalNodeByAuto(int totalNodeByAuto) {
		BaseNodeModeSchedule.totalNodeByAuto = totalNodeByAuto;
	}

	public void perform() {
		// 签到器本身
		if (getIsRegisterCmd() == 1) {
			if (this.settingByAuto == 1) {
				this.doPerform();
			} else {
				logger.debug("Setting by manul. Register cmd needn't startup...");
			}

			return;
		}
		// 自动签到， 检查总开关状态
		if (this.settingByAuto == 1 && switchStateByAuto == 0) {
			logger.debug("Setting by auto. And center switch is 0. Schedule '" + getScheduleName()
					+ "' is terminated...");
			return;
		}
		//判断这个集群是够可以做这个任务
		Integer switchState=0;
		if(null!=ServerNodeRegisterCmd.map.get(getScheduleName())){
			switchState=ServerNodeRegisterCmd.map.get(getScheduleName());
		}
		// 检查调度自身开关状态
		if (switchState == 0) {
			logger.debug("The switch is 0. Schedule '" + getScheduleName() + "' is terminated...");
			return;
		} else {
			this.doPerform();
		}

	}

	public abstract String getScheduleName();

	public abstract void doPerform();

}
