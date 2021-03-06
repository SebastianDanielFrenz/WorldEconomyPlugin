package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class CustomBlockMachineData extends CustomBlockData implements InventoryHolder {

	private Inventory inv;

	public CustomBlockMachineData(Location location) {
		super(location);
		inv = Bukkit.createInventory(this, 9);
	}

	public CustomBlockMachineData(Location location, String raw) throws CustomBlockDataCreationException {
		super(location, raw);
		inv = InventoryIO.loadInventory(this, raw);
	}

	@Override
	public String save() {
		return InventoryIO.serialize(inv);
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

	public void setInventory(Inventory inv) {
		this.inv = inv;
	}
}
