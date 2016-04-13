package com.zhaidou.jobCenter.schedule;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaidou.jobCenter.chain.Chain;


/**
 * 责任链调度
 * 
 * @author kaili
 * 
 */
public class ChainSchedule extends BaseNodeModeSchedule {

	public static final Log logger = LogFactory.getLog(ChainSchedule.class);

	private String scheduleName;

	private Chain chain;

	@Override
	public void doPerform() {
		if (chain == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("The Chain is null. The schedule '" + getScheduleName() + "' is terminated...");
			}
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("ChainSchedule '" + getScheduleName() + "' starting...");
		}

		HashMap<String, Object> context = new HashMap<String, Object>();
		context.put("scheduleConfiguration", this);

		this.chain.doExecute(context);

		if (logger.isDebugEnabled()) {
			logger.debug("ChainSchedule '" + getScheduleName() + "' stoped...");
		}
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Chain getChain() {
		return chain;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}

}
