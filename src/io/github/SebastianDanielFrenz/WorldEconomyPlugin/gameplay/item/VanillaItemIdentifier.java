package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import org.bukkit.Material;

public class VanillaItemIdentifier implements Comparable<VanillaItemIdentifier> {

	public VanillaItemIdentifier(Material material, byte data) {
		this.material = material;
		this.data = data;
	}

	public VanillaItemIdentifier(Material material, int data) {
		this(material, (byte) data);
	}

	public final Material material;
	public final byte data;

	@Override
	public int compareTo(VanillaItemIdentifier o) {
		return (o.material == material && o.data == data) ? 0 : 1;
	}
}
