package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.campfires;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.CraftingGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineCategory;

public abstract class MachineCampfire extends Machine {

	public MachineCampfire(Location location) {
		super(location);
	}

	@Override
	public MachineCategory getKategory() {
		return MachineCategory.CAMPFIRE;
	}

	@Override
	public void playerUseEvent(Player player) {
		new CraftingGUI(new GUIItem[] { new GUIItem(4, WEGUI.mkItem(Material.OAK_SIGN, getName())) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		}, new CampfireDecorationGUIItem(2, 5), new CampfireDecorationGUIItem(2, 6),
				new CampfireDecorationGUIItem(2, 7), new CampfireDecorationGUIItem(2, 8),
				new CampfireDecorationGUIItem(3, 5), new CampfireDecorationGUIItem(4, 5),
				new CampfireDecorationGUIItem(5, 5) }, "Furnace", this,
				new int[] { 9 * 3 + 6, 9 * 3 + 7, 9 * 3 + 8, 9 * 4 + 6, 9 * 4 + 7, 9 * 4 + 8, 9 * 5 + 6, 9 * 5 + 7,
						9 * 5 + 8 }).openInventory(player);
	}

}
