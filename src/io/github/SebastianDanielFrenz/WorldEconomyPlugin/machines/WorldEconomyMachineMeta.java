package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class WorldEconomyMachineMeta implements MetadataValue {

	public WorldEconomyMachineMeta(MachineGroup group) {
		this.value = group;
	}

	private MachineGroup value;

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
		return Double.NaN;
	}

	@Override
	public float asFloat() {
		return Float.NaN;
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
		return value.getName();
	}

	@Override
	public Plugin getOwningPlugin() {
		return WorldEconomyPlugin.plugin;
	}

	@Override
	public void invalidate() {
		throw new RuntimeException("Not implemented: WTF is this?");
	}

	@Override
	public MachineGroup value() {
		return value;
	}

}
