package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class CustomBlockMetadataValue implements MetadataValue {

	public CustomBlockMetadataValue(CustomBlock block, CustomBlockData data) {
		this.block = block;
		this.data = data;
	}

	private CustomBlock block;
	private CustomBlockData data;

	public CustomBlock getBlock() {
		return block;
	}

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
