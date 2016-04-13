package com.zhaidou.jobCenter.chain;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Command {

	public static final Log logger = LogFactory.getLog(Command.class);

	private String commandName = null;

	/**
	 * 
	 * @return true 执行成功 false 执行失败,如果有信息需要输出放在contxt 的 errMsg 中
	 * 
	 * 
	 */
	public abstract boolean doExecute(HashMap<String, Object> context);

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	protected void logEntry(String cmdName, String methodName) {
		String s = "--" + cmdName + "--" + methodName + "--start--";
		if (logger.isDebugEnabled()) {
			logger.debug(s);
		}
	}

	protected void logExit(String cmdName, String methodName) {
		String s = "--" + cmdName + "--" + methodName + "--end--";
		if (logger.isDebugEnabled()) {
			logger.debug(s);
		}
	}
}
