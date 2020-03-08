package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemData {

	public Map<String, String> map = new TreeMap<String, String>();

	public void load(ItemStack stack) {
		if (stack.getItemMeta().getLore() == null) {
			return;
		}

		for (String line : stack.getItemMeta().getLore()) {
			String[] split = line.split(": ");
			map.put(split[0], split[1]);
		}
	}

	public void apply(ItemStack itemStack) {
		List<String> lore = new ArrayList<String>();
		Iterator<String> keys = map.keySet().iterator();
		Iterator<String> values = map.values().iterator();
		for (int i = 0; i < map.size(); i++) {
			lore.add(keys.next() + ": " + values.next());
		}
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
	}
}
