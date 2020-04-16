package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.campfires;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.CustomBlockMachineData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.MachineCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.CraftingGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public abstract class MachineCampfire extends Machine {

	public MachineCampfire(String ID, boolean vanilla, CustomBlockDropTable drop_table) {
		super(ID, Material.CAMPFIRE, vanilla, drop_table, CustomBlockMachineData.class);
	}

	@Override
	public MachineCategory getCategory() {
		return MachineCategory.CAMPFIRE;
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		new CraftingGUI(new GUIItem[] { new GUIItem(4, WEGUI.mkItem(Material.OAK_SIGN, getName())) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		}, new CampfireDecorationGUIItem(2, 5), new CampfireDecorationGUIItem(2, 6), new CampfireDecorationGUIItem(2, 7),
				new CampfireDecorationGUIItem(2, 8), new CampfireDecorationGUIItem(3, 5), new CampfireDecorationGUIItem(4, 5),
				new CampfireDecorationGUIItem(5, 5) }, "Campfire", event.getClickedBlock(),
				new int[] { 9 * 3 + 6, 9 * 3 + 7, 9 * 3 + 8, 9 * 4 + 6, 9 * 4 + 7, 9 * 4 + 8, 9 * 5 + 6, 9 * 5 + 7, 9 * 5 + 8 })
						.openInventory(event.getPlayer());
	}

}