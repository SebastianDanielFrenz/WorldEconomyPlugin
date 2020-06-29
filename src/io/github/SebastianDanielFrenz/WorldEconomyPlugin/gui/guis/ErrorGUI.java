package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ErrorGUI extends WEGUI {

	public ErrorGUI(WEGUI parent, String title, Player player) {
		super(parent, new GUIItem[] {}, title, player);

		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		setItems(convert(items));
	}

	public ErrorGUI(String title, Player player) {
		super(new GUIItem[] {}, title, player);

		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		setItems(convert(items));
	}

	public ErrorGUI(Player player) {
		super(new GUIItem[] {}, player);

		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		setItems(convert(items));
	}

}
