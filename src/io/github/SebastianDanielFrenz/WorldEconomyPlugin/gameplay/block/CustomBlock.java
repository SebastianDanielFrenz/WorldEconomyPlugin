package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.ComparableLocation;

public class CustomBlock {

	public CustomBlock(ComparableLocation location, CustomBlockType type, CustomBlockData data) {
		this.type = type;
		this.data = data;
		this.location = location;
	}

	private CustomBlockType type;
	private CustomBlockData data;
	private ComparableLocation location;

	public CustomBlockType getType() {
		return type;
	}

	public void setType(CustomBlockType type) {
		this.type = type;
	}

	public CustomBlockData getData() {
		return data;
	}

	public void setData(CustomBlockData data) {
		this.data = data;
	}

	public ComparableLocation getLocation() {
		return location;
	}

	public void setLocation(ComparableLocation location) {
		this.location = location;
	}

}
