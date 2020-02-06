package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class WEGUIs {

	public static ItemStack mkItem(Material material, String name, String[] lore) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();
		ArrayList<String> _lore = new ArrayList<String>();

		for (int i = 0; i < lore.length; i++) {
			_lore.add(lore[i]);
		}

		meta.setLore(_lore);
		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, String name) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();

		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static WEGUI main = new WEGUI(new GUIItem[] { new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Main Menu")) {
		@Override
		public void event(InventoryClickEvent event) {
			event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "There is nothing to do here!");
		}
	}, new GUIItem(1, 0, mkItem(Material.RED_WOOL, "§eCompanies")) {
		@Override
		public void event(InventoryClickEvent event) {
			event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "Companies GUI!");
		}
	}, new GUIItem(1, 1, mkItem(Material.YELLOW_WOOL, "My employers")) {
		@Override
		public void event(InventoryClickEvent event) {
			event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "my employers GUI!");
		}
	}, new GUIItem(1, 2, mkItem(Material.GREEN_WOOL, "Banks")) {
		@Override
		public void event(InventoryClickEvent event) {
			event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "bank GUI!");
		}
	}, new GUIItem(1, 3, mkItem(Material.LIME_WOOL, "My banks")) {
		@Override
		public void event(InventoryClickEvent event) {
			event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "my bank GUI!");
		}
	} });

	public static WEGUI[] all = new WEGUI[] { main };
}
