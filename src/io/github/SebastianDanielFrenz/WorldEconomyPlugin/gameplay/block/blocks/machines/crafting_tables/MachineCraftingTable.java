package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.crafting_tables;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.CustomBlockMachineData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.MachineCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.CraftingGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public abstract class MachineCraftingTable extends Machine {

	public MachineCraftingTable(String ID, boolean vanilla, CustomBlockDropTable drop_table) {
		super(ID, Material.CRAFTING_TABLE, vanilla, drop_table, CustomBlockMachineData.class);
	}

	@Override
	public MachineCategory getCategory() {
		return MachineCategory.CRAFTING;
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);

		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			new CraftingGUI(new GUIItem[] { new GUIItem(4, WEGUI.mkItem(Material.OAK_SIGN, getName())) {
				@Override
				public void event(InventoryClickEvent event) {
				}
			}, new CraftingTableDecorationGUIItem(2, 5), new CraftingTableDecorationGUIItem(2, 6), new CraftingTableDecorationGUIItem(2, 7),
					new CraftingTableDecorationGUIItem(2, 8), new CraftingTableDecorationGUIItem(3, 5), new CraftingTableDecorationGUIItem(4, 5),
					new CraftingTableDecorationGUIItem(5, 5) }, "Crafting Table", event.getClickedBlock(),
					new int[] { 9 * 3 + 6, 9 * 3 + 7, 9 * 3 + 8, 9 * 4 + 6, 9 * 4 + 7, 9 * 4 + 8, 9 * 5 + 6, 9 * 5 + 7, 9 * 5 + 8 })
							.openInventory(event.getPlayer());
		}
	}

}
