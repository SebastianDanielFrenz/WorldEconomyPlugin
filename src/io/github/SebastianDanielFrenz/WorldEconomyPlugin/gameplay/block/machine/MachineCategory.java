package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine;

import org.bukkit.Material;

public enum MachineCategory {

	CRAFTING(Material.WORKBENCH),
	SMELTING(Material.FURNACE),
	FORGING(Material.ANVIL),
	CRUSHING(Material.DROPPER),
	SIEVING(Material.CAULDRON),
	BLAST_FURNACE(Material.FURNACE),
	CAMPFIRE(Material.WOOD_STEP);

	private MachineCategory(Material display) {
		this.display = display;
	}

	public Material display;

}
