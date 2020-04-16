package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine;

import org.bukkit.inventory.Inventory;

public class MachineInventory {

	public MachineInventory(Inventory inventory, int slots) {
		this.slots = slots;
		this.inv = inventory;
	}

	public Inventory inv;
	public int slots;

}
