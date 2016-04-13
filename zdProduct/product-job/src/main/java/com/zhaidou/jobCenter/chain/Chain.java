package com.zhaidou.jobCenter.chain;

import java.util.HashMap;
import java.util.List;

public class Chain extends Command {

	private List<Command> commands = null;
	private CommandNode head = null;
	private String chainName = null;

	@Override
	public boolean doExecute(HashMap<String, Object> context) {

		initChain();

		if (head == null) {
			return true;
		}

		if (context == null) {
			context = new HashMap<String, Object>();
		}

		return head.execute(context);

	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	private void initChain() {

		if (commands == null || commands.isEmpty()) {
			return;
		}

		if (head != null) {
			return;
		}

		head = new CommandNode();
		head.command = commands.get(0);
		CommandNode pre = head;
		CommandNode next;

		int size = commands.size();
		for (int i = 1; i < size; i++) {
			next = new CommandNode();
			next.setCommand(commands.get(i));
			pre.setNext(next);
			pre = next;
		}

	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	private class CommandNode {
		private CommandNode next = null;
		private Command command = null;

		public boolean execute(HashMap<String, Object> context) {

			if (command == null) {
				return true;
			}

			// 执行本节点任务
			if (!command.doExecute(context)) {

				context.put("errMsg", command.getCommandName() + "-->" + context.get("errMsg"));
				return false;
			}

			if (getNext() == null) {
				return true;
			}

			// 递归执行下一节点任务
			return getNext().execute(context);
		}

		public CommandNode getNext() {
			return next;
		}

		public void setNext(CommandNode next) {
			this.next = next;
		}

		public void setCommand(Command command) {
			this.command = command;
		}

	}

}
