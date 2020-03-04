package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.Material;

public enum MachineKategory {

	CRAFTING(Material.CRAFTING_TABLE), SMELTING(Material.FURNACE), FORGING(Material.ANVIL), CRUSHING(Material.SMITHING_TABLE);

	private MachineKategory(Material display) {
		this.display = display;
	}

	public Material display;

}
