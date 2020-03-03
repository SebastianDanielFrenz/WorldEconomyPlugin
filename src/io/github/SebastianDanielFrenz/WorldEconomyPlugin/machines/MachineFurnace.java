package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.CraftingGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources.ItemTransactionManager;

public abstract class MachineFurnace extends Machine {

	public MachineFurnace(Location location) {
		super(location);
	}

	@Override
	public MachineKategory getKategory() {
		return MachineKategory.SMELTING;
	}

	@Override
	public void playerUseEvent(Player player) {
		new CraftingGUI(new GUIItem[] { new GUIItem(4, WEGUI.mkItem(Material.OAK_SIGN, "Testing item")) {
			@Override
			public void event(InventoryClickEvent event) {
				// TODO Auto-generated method stub
			}
		} }, "Furnace", this, new int[] { 9 + 6, 9 + 7, 9 + 8, 18 + 6, 18 + 7, 18 + 8, 27 + 6, 27 + 7, 27 + 8 })
				.openInventory(player);
	}
}
