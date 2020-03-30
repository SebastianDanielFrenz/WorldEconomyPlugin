package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;

/**
 * 
 * This class should be extended as follows:<br>
 * <br>
 * 
 * Constructor(String rawData)<br>
 * Constructor()<br>
 * save() <br>
 * <br>
 * The empty constructor will be used to create new blockdata while the other
 * constructor is used to reconstruct the blockdata from the database.
 */
public abstract class CustomBlockData {

	public CustomBlockData(String rawData) throws CustomBlockDataCreationException {
	}

	public CustomBlockData() {
	}

	public abstract String save();

}
