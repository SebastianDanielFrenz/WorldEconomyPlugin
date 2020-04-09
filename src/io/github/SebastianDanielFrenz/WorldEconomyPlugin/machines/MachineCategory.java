package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.Material;

public enum MachineCategory {

	CRAFTING(Material.CRAFTING_TABLE),
	SMELTING(Material.FURNACE),
	FORGING(Material.ANVIL),
	CRUSHING(Material.SMITHING_TABLE),
	SIEVING(Material.CAULDRON),
	BLAST_FURNACE(Material.BLAST_FURNACE),
	CAMPFIRE(Material.CAMPFIRE);

	private MachineCategory(Material display) {
		this.display = display;
	}

	public Material display;

}
