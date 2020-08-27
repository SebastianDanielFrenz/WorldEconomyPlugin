package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class CustomBlockMetadataValue implements MetadataValue {

	public CustomBlockMetadataValue(CustomBlockType block, CustomBlockData data) {
		this.block = block;
		this.data = data;
	}

	private CustomBlockType block;
	private CustomBlockData data;

	public CustomBlockType getBlock() {
		return block;
	}

	/**
	 * Editing this data does not survive a server restart.
	 * 
	 * @return
	 */
	public CustomBlockData getBlockData() {
		return data;
	}

	@Override
	public boolean asBoolean() {
		return true;
	}

	@Override
	public byte asByte() {
		return 0;
	}

	@Override
	public double asDouble() {
		return 0;
	}

	@Override
	public float asFloat() {
		return 0;
	}

	@Override
	public int asInt() {
		return 0;
	}

	@Override
	public long asLong() {
		return 0;
	}

	@Override
	public short asShort() {
		return 0;
	}

	@Override
	public String asString() {
		return block.ID + "{" + data.toString() + "}";
	}

	@Override
	public Plugin getOwningPlugin() {
		return WorldEconomyPlugin.plugin;
	}

	@Override
	public void invalidate() {
		throw new RuntimeException("wtf is this?");
	}

	@Override
	public Object[] value() {
		return new Object[] { block, data };
	}

}
