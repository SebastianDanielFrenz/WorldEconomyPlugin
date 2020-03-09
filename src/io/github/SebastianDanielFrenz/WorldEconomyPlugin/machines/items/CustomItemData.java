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
		if (stack == null) {
			return;
		}
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

	/**
	 * returns whether the item contains all of the data in this object. (the
	 * ItemStack may contain more)
	 * 
	 * @param stack
	 * @return
	 */
	public boolean matches(ItemStack stack) {

		CustomItemData other = new CustomItemData();
		other.load(stack);
		if (other.map.size() != map.size()) {
			return false;
		}
		Iterator<String> keys = map.keySet().iterator();
		Iterator<String> values = map.values().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = values.next();
			Iterator<String> other_keys = other.map.keySet().iterator();
			Iterator<String> other_values = other.map.values().iterator();
			boolean found = false;
			while (other_keys.hasNext()) {
				String other_value = other_values.next();
				if (other_keys.next().equals(key)) {
					found = other_value.equals(value);
					break;
				}
			}

			if (!found) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}
}
