package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtension;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticalObject;

public abstract class CustomItem implements ResearchableObject, StatisticalObject {

	public CustomItem(String ID, Material base, Age age, String name, ItemCategory category, boolean vanilla) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, Age age, String name, ItemCategory category, ItemDetail[] details, boolean vanilla) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, Age age, String name, ItemCategory category) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = false;
	}

	public CustomItem(String ID, Material base, Age age, String name, ItemCategory category, ItemDetail[] details) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = false;
	}

	// now with data values

	public CustomItem(String ID, Material base, int data, Age age, String name, ItemCategory category, boolean vanilla) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, int data, Age age, String name, ItemCategory category, ItemDetail[] details, boolean vanilla) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, int data, Age age, String name, ItemCategory category) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = false;
	}

	public CustomItem(String ID, Material base, int data, Age age, String name, ItemCategory category, ItemDetail[] details) {
		this.plugin = WorldEconomyPlugin.plugin;
		this.ID = ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = false;
	}

	// now with external plugin support

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, Age age, String name, ItemCategory category, boolean vanilla) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = vanilla;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, Age age, String name, ItemCategory category, ItemDetail[] details,
			boolean vanilla) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = vanilla;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, Age age, String name, ItemCategory category) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = false;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, Age age, String name, ItemCategory category, ItemDetail[] details) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = 0;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = false;
	}

	// now with data values

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, int data, Age age, String name, ItemCategory category,
			boolean vanilla) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = vanilla;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, int data, Age age, String name, ItemCategory category,
			ItemDetail[] details, boolean vanilla) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = vanilla;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, int data, Age age, String name, ItemCategory category) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = false;
	}

	public CustomItem(WorldEconomyExtension plugin, String ID, Material base, int data, Age age, String name, ItemCategory category,
			ItemDetail[] details) {
		this.plugin = plugin;
		this.ID = plugin.getID() + ID;
		base_material = base;
		vanilla_data = (byte) data;
		raw_item_name = name;
		item_name = age.color.toString() + name;
		this.age = age;
		this.category = category;
		this.details = details;
		this.vanilla = false;
	}

	public final Plugin plugin;
	public final String ID;
	public final Material base_material;
	public final byte vanilla_data;
	public final String raw_item_name;
	public final String item_name;
	public final Age age;
	public final ItemCategory category;
	public final ItemDetail[] details;
	public final boolean vanilla;

	public ItemStack toItemStack() {
		if (item_name == null) {
			return new ItemStack(base_material, 1, vanilla_data);
		} else {
			ItemStack out = new ItemStack(base_material);
			ItemMeta meta = out.getItemMeta();
			meta.setDisplayName(item_name);
			out.setItemMeta(meta);
			return out;
		}
	}

	public ItemStack toItemStack(int amount) {
		if (item_name == null) {
			return new ItemStack(base_material);
		} else {
			ItemStack out = new ItemStack(base_material, amount, vanilla_data);
			ItemMeta meta = out.getItemMeta();
			meta.setDisplayName(item_name);
			out.setItemMeta(meta);
			return out;
		}
	}

	public static CustomItem getItem(Material material, byte data, String name) {
		if (name == null) {
			return getVanillaItem(material, data);
		}

		for (CustomItem item : CustomItemRegistry.getContents()) {
			if (item.base_material == material && name.equals(item.item_name)) {
				return item;
			}
		}
		return null;
	}

	public static CustomItem getVanillaItem(Material material, int data) {
		for (CustomItem item : CustomItemRegistry.getContents()) {
			if (item.vanilla && item.base_material == material && item.vanilla_data == data) {
				return item;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static CustomItem getItem(ItemStack stack) {
		if (stack == null) {
			return null;
		}
		if (!stack.hasItemMeta()) {
			return null;
		}
		return getItem(stack.getType(), stack.getData().getData(), stack.getItemMeta().getDisplayName());
	}

	public static void convertVanillaItemStack(ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		if (meta.getDisplayName().equals("")) {
			meta.setDisplayName(getItem(itemStack).item_name);
		}
	}

	public boolean hasDetail(Class<? extends ItemDetail> type) {
		for (ItemDetail _detail : details) {
			if (_detail.getClass() == type) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T extends ItemDetail> T getDetail(Class<T> type) {
		for (ItemDetail detail : details) {
			if (type.isInstance(detail)) {
				return (T) detail;
			}
		}
		return null;
	}

	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("PlayerInteractEvent: action=" + event.getAction().name());
	}

	public String getEffectiveItemName() {
		return item_name;
	}

	@Override
	public String getStatisticID() {
		return "item_" + ID;
	}

}
