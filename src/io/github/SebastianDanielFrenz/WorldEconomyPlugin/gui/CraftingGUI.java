package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Utils;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.CustomBlockMachineData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machine.MachineInventory;

public class CraftingGUI extends WEGUI {

	private static final int[] recipe_category_slots = new int[] { 9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31, 36, 37, 38, 39, 40, 45,
			46, 47, 48, 49 };

	protected int[] inv_representing_slots;
	protected MachineInventory storage_inv;
	protected int[] recipes_representing_slots;
	private Block machine;

	public CraftingGUI(CraftingGUI parent, GUIItem[] items, String title, Block machineBlock, int[] inv_representing_slots) {
		super(parent, items, title);

		this.inv_representing_slots = inv_representing_slots;
		storage_inv = new MachineInventory(((CustomBlockMachineData) CustomBlockType.getMetadata(machineBlock).getBlockData()).getInventory(),
				inv_representing_slots.length);
		machine = machineBlock;
	}

	public CraftingGUI(GUIItem[] items, String title, Block machineBlock, int[] inv_representing_slots) {
		super(items, title);
		// this.inv_representing_slots = inv_representing_slots;
		this.inv_representing_slots = inv_representing_slots;
		storage_inv = new MachineInventory(((CustomBlockMachineData) CustomBlockType.getMetadata(machineBlock).getBlockData()).getInventory(),
				inv_representing_slots.length);
		machine = machineBlock;
	}

	@Override
	public GUIItem[] initializeItems(GUIItem[] items) {
		GUIItem[] new_items = new GUIItem[items.length + inv_representing_slots.length + ItemCategory.values().length];
		for (int i = 0; i < items.length; i++) {
			new_items[i] = items[i];
		}

		ItemStack slot;
		for (int i = 0; i < inv_representing_slots.length; i++) {
			int x = i;
			slot = storage_inv.inv.getStorageContents()[i];
			if (slot == null) {
				slot = new ItemStack(Material.AIR);
			}
			new_items[items.length + i] = new GUIItem(inv_representing_slots[i], slot) {
				@Override
				public void event(InventoryClickEvent event) {
					event.setCancelled(false);
					event.getWhoClicked().sendMessage("inv click!");
					storage_inv.inv.setItem(x, event.getCursor());
					if (machine != null) {
						try {
							WEDB.updateBlockData(machine, InventoryIO.serialize(storage_inv.inv));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						((CustomBlockMachineData) ((CustomBlockMetadataValue) machine.getMetadata("customBlockType").get(0)).getBlockData())
								.setInventory(storage_inv.inv);
					}
				}
			};
		}

		// recipes - categories
		int i = 0;
		for (ItemCategory category : ItemCategory.values()) {
			new_items[items.length + inv_representing_slots.length + i] = new GUIItem(recipe_category_slots[i],
					mkItem(category.display, category.getTitle())) {
				@Override
				public void event(InventoryClickEvent event) {

				}
			};
			i++;
		}

		return super.initializeItems(new_items);
	}

	// You can open the inventory with this
	public void openInventory(Player player) {
		items = initializeItems(items);
		player.openInventory(inv);
	}

	// Check for clicks on items
	public void eventHandler(InventoryClickEvent e) {

		e.setCancelled(true);

		System.out.println("Raw: " + e.getRawSlot());

		if (e.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
			return;
		}

		// Using slots click is a best option for your inventory click's

		if (fits_on_one_screen) {
			// CraftingGUI specific code (also see in !fits_on_screen)
			if (e.getRawSlot() >= 54) {
				System.out.println("");
				System.out.println(e.getAction().name());
				if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
					e.getWhoClicked().sendMessage("don't shift!");
				} else {
					e.setCancelled(false);
				}
				return;
			} else {
				if (e.getAction() == InventoryAction.PICKUP_HALF) {
					e.getWhoClicked().sendMessage("you may not do that.");
					return;
				}
			}

			for (int i = 0; i < items.length; i++) {
				if (items[i].slot == e.getRawSlot()) {
					items[i].event(e);
					break;
				}
			}
		} else {

			if (e.getRawSlot() < 9) {
				System.out.println("top slot " + e.getRawSlot());
			} else if (e.getRawSlot() >= 5 * 9) {
				System.out.println("bottom slot " + (e.getRawSlot() - (9 * 6)));
			} else {
				System.out.println("middle slot " + (e.getRawSlot() + (page * 9 * 4)));
			}

			// CraftingGUI specific code (also see in fits_on_screen)
			if (e.getRawSlot() >= 54) {
				System.out.println("");
				System.out.println(e.getAction().name());
				if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
					e.getWhoClicked().sendMessage("don't shift!");
				} else {
					e.setCancelled(false);
				}
			}

			for (int i = 0; i < items.length; i++) {
				if (e.getRawSlot() < 9) {
					if (items[i].slot == e.getRawSlot()) {
						items[i].event(e);
						if (Utils.in(inv_representing_slots, e.getRawSlot())) {
							e.setCancelled(false);
							storage_inv.inv.setItem(Utils.indexOf(inv_representing_slots, e.getRawSlot()), e.getCurrentItem());
						}
						break;
					}
				} else if (e.getRawSlot() >= 5 * 9) {
					if (items[i].slot == e.getRawSlot() - 6 * 9) {
						items[i].event(e);
						if (Utils.in(inv_representing_slots, e.getRawSlot() - 6 * 9)) {
							e.setCancelled(false);
							storage_inv.inv.setItem(Utils.indexOf(inv_representing_slots, e.getRawSlot() - 6 * 9), e.getCurrentItem());
						}
						break;
					}
				} else {
					if (items[i].slot == e.getRawSlot() + page * 9 * 4) {
						items[i].event(e);
						if (Utils.in(inv_representing_slots, e.getRawSlot() + page * 9 * 4)) {
							e.setCancelled(false);
							storage_inv.inv.setItem(Utils.indexOf(inv_representing_slots, e.getRawSlot() + page * 9 * 4), e.getCurrentItem());
						}
						break;
					}

				}
			}
		}
	}

	@Override
	public void eventHandler(InventoryDragEvent event) {
		for (int x : event.getRawSlots()) {
			if (x < 54) {
				event.setCancelled(true);
				break;
			}
		}
	}

	public void setErrorGUI() {
		GUIItem[] items = new GUIItem[9 * 4];
		for (int i = 0; i < items.length; i++) {
			items[i] = new GUIItem(i + 9, mkItem(Material.BARRIER, "§4§lERROR")) {
				@Override
				public void event(InventoryClickEvent event) {
				}
			};
		}
		setItems(items);
	}
}
