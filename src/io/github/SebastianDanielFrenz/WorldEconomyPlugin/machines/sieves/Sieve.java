package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.CraftingGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineKategory;

public abstract class Sieve extends Machine {

	public Sieve(Location location) {
		super(location);
	}

	@Override
	public MachineKategory getKategory() {
		return MachineKategory.SIEVING;
	}

	@Override
	public void playerUseEvent(Player player) {
		new CraftingGUI(new GUIItem[] { new GUIItem(4, WEGUI.mkItem(Material.OAK_SIGN, getName())) {
			@Override
			public void event(InventoryClickEvent event) {

			}
		}, new SieveDecorationGUIItem(2, 5), new SieveDecorationGUIItem(2, 6), new SieveDecorationGUIItem(2, 7),
				new SieveDecorationGUIItem(2, 8), new SieveDecorationGUIItem(3, 5),
				new SieveDecorationGUIItem(4, 5), new SieveDecorationGUIItem(5, 5) }, "Sieve", this,
				new int[] { 9 * 3 + 6, 9 * 3 + 7, 9 * 3 + 8, 9 * 4 + 6, 9 * 4 + 7, 9 * 4 + 8, 9 * 5 + 6, 9 * 5 + 7,
						9 * 5 + 8 }).openInventory(player);

	}

}
