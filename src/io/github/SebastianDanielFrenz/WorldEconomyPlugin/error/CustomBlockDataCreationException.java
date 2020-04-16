package io.github.SebastianDanielFrenz.WorldEconomyPlugin.error;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;

public class CustomBlockDataCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3847985156675778022L;

	public CustomBlockDataCreationException(CustomBlockType block, String raw) {
		super("Failed to create blockdata for block " + block.ID + ": " + raw + " -> "
				+ block.blockDataType.getCanonicalName());
	}

}
