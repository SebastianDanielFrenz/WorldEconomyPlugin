package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryIO {

	public static final char SEP_ITEM = 1;
	public static final char SEP_ITEM_METADATA = 2;
	public static final char SEP_ITEM_METADATA_ENCHANTMENTS = 3;
	public static final char SEP_ITEM_METADATA_ENCHANTMENT = 4;
	public static final char SEP_ITEM_METADATA_LORE = 5;

	public static final char SEP_ITEMS = 6;

	public static final char SEP_INV = 7;

	public static String sep(char c) {
		return new String(new char[] { 0, c });
	}

	public static String[] split(String text, String searched) {
		return text.split("[" + searched + "]");
	}

	@SuppressWarnings("deprecation")
	public static String serialize(ItemStack item) {
		if (item == null) {
			return "null";
		}

		Material material = item.getType();
		int amount = item.getAmount();
		ItemMeta itemMeta = item.getItemMeta();

		String out = material.name() + sep(SEP_ITEM);
		out += amount + sep(SEP_ITEM);
		// item meta
		out += itemMeta.getDisplayName() + sep(SEP_ITEM_METADATA);
		// TODO attribute modifiers
		Set<Enchantment> ench_keys = itemMeta.getEnchants().keySet();
		Collection<Integer> ench_values = itemMeta.getEnchants().values();
		Iterator<Enchantment> ench_keys_it = ench_keys.iterator();
		Iterator<Integer> ench_values_it = ench_values.iterator();
		for (int i = 0; i < itemMeta.getEnchants().size(); i++) {
			out += ench_keys_it.next().getName() + sep(SEP_ITEM_METADATA_ENCHANTMENT) + ench_values_it.next();
		}
		out += sep(SEP_ITEM_METADATA);
		if (itemMeta.getLore() == null) {
			out += "null";
		} else {
			for (String line : itemMeta.getLore()) {
				out += line + sep(SEP_ITEM_METADATA_LORE);
			}
		}
		// TODO persistent data container

		return out;
	}

	public static String serialize(ItemStack[] items) {
		String out = "";
		for (ItemStack itemStack : items) {
			out += serialize(itemStack) + sep(SEP_ITEMS);
		}
		return out;
	}

	public static String serialize(Inventory inv) {
		String out = "";
		out += inv.getSize() + sep(SEP_INV);
		out += inv.getMaxStackSize() + sep(SEP_INV);
		out += serialize(inv.getContents());
		return out;
	}

	public static Inventory loadInventory(InventoryHolder holder, String text) {
		String[] inv_data = split(text, sep(SEP_INV));
		int size = Integer.parseInt(inv_data[0]);
		int maxStackSize = Integer.parseInt(inv_data[1]);

		String raw_items = inv_data[2];

		Inventory inv = Bukkit.createInventory(holder, size);
		ItemStack[] items = loadItemStacks(raw_items);
		inv.setContents(items);
		inv.setMaxStackSize(maxStackSize);
		return inv;
	}

	public static ItemStack[] loadItemStacks(String text) {
		String[] items = split(text, sep(SEP_ITEMS));
		ItemStack[] out = new ItemStack[items.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = loadItemStack(items[i]);
		}
		return out;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack loadItemStack(String text) {
		if (text.equals("null")) {
			return null;
		}

		String[] item_data = split(text, sep(SEP_ITEM));

		Material material = Material.getMaterial(item_data[0]);
		int amount = Integer.parseInt(item_data[1]);
		String raw_item_meta = item_data[2];

		ItemStack out = new ItemStack(material, amount);
		ItemMeta itemMeta = out.getItemMeta();

		String[] raw_item_meta_data = split(raw_item_meta, sep(SEP_ITEM_METADATA));
		String display_name = raw_item_meta_data[0];
		itemMeta.setDisplayName(display_name);
		String[] enchantments = split(raw_item_meta_data[1], sep(SEP_ITEM_METADATA_ENCHANTMENTS));

		String[] ench_data;
		for (String enchantment : enchantments) {
			ench_data = split(enchantment, sep(SEP_ITEM_METADATA_ENCHANTMENT));
			itemMeta.addEnchant(Enchantment.getByName(ench_data[0]), Integer.parseInt(ench_data[1]), true);
		}

		String raw_lore = raw_item_meta_data[2];
		if (!raw_lore.equals("null")) {
			String[] lore_data = split(raw_lore, sep(SEP_ITEM_METADATA_LORE));
			List<String> lore = new ArrayList<String>();
			for (String line : lore_data) {
				lore.add(line);
			}
			itemMeta.setLore(lore);
		}

		out.setItemMeta(itemMeta);

		return out;
	}

	public static Inventory loadInventoryFromFile(InventoryHolder holder, String path) throws IOException {
		return loadInventory(holder,
				new String(Files.readAllBytes(Paths.get("plugins/WorldEconomy/saved_inventories/" + path))));
	}

	public static void writeInventoryToFile(Inventory inv, String path) throws IOException {
		try {
			Files.delete(Paths.get("plugins/WorldEconomy/saved_inventories/" + path));
		} catch (IOException e) {
		}

		Files.write(Paths.get("plugins/WorldEconomy/saved_inventories/" + path), serialize(inv).getBytes(),
				StandardOpenOption.CREATE_NEW);
	}

}
