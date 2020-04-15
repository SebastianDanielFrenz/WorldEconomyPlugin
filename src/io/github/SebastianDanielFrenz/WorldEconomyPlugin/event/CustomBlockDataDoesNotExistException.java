package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.block.Block;

public class CustomBlockDataDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4680607995812146324L;

	public CustomBlockDataDoesNotExistException(Block block) {
		super("The block at " + block.getLocation() + " does not contain metadata!");
	}

}
