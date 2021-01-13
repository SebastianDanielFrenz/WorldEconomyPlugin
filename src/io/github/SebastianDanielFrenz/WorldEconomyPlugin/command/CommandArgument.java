package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

public abstract class CommandArgument {

	public final String raw;

	public CommandArgument(String raw) {
		this.raw = raw;
	}

	public abstract void parse() throws Exception;

}
