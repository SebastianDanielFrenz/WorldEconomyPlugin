package io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.custom_command;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CustomCommandGroupContent;

public class CustomCommandError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8946297746014320231L;
	private CustomCommandGroupContent content;

	public CustomCommandError(String msg, CustomCommandGroupContent content) {
		super(msg);
		this.content = content;
	}

	public CustomCommandGroupContent getContent() {
		return content;
	}

}
