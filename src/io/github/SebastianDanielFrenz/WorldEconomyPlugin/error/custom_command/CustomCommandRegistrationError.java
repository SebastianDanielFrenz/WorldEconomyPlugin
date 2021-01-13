package io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.custom_command;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CustomCommandGroupContent;

public class CustomCommandRegistrationError extends CustomCommandError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3807426970808776808L;

	public CustomCommandRegistrationError(String msg, CustomCommandGroupContent content) {
		super(msg, content);
	}

}
