package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.statistics.CustomStatisticObject;

public abstract class CustomItem implements ResearchableObject, CustomStatisticObject {

	public CustomItem(String ID, Material base, Tier tier, String name, ItemCategory category, boolean vanilla) {
		this.ID = ID;
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, Tier tier, String name, ItemCategory category, ItemDetail[] details,
			boolean vanilla) {
		this.ID = ID;
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		this.details = details;
		this.vanilla = vanilla;
	}

	public CustomItem(String ID, Material base, Tier tier, String name, ItemCategory category) {
		this.ID = ID;
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		details = new ItemDetail[] {};
		this.vanilla = false;
	}

	public CustomItem(String ID, Material base, Tier tier, String name, ItemCategory category, ItemDetail[] details) {
		this.ID = ID;
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		this.details = details;
		this.vanilla = false;
	}

	public final String ID;
	public final Material base_material;
	public final String raw_item_name;
	public final String item_name;
	public final Tier tier;
	public final ItemCategory category;
	public final ItemDetail[] details;
	public final boolean vanilla;

	public ItemStack toItemStack() {
		if (item_name == null) {
			return new ItemStack(base_material);
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
			ItemStack out = new ItemStack(base_material, amount);
			ItemMeta meta = out.getItemMeta();
			meta.setDisplayName(item_name);
			out.setItemMeta(meta);
			return out;
		}
	}

	public static CustomItem getItem(Material material, String name) {
		if (name.equals("")) {
			return getVanillaItem(material);
		}

		for (CustomItem item : CustomItemRegistry.getContents()) {
			if (item.base_material == material && name.equals(item.item_name)) {
				return item;
			}
		}
		return null;
	}

	public static CustomItem getVanillaItem(Material material) {
		for (CustomItem item : CustomItemRegistry.getContents()) {
			if (item.vanilla && item.base_material == material) {
				return item;
			}
		}
		return null;
	}

	public static CustomItem getItem(ItemStack stack) {
		if (stack == null) {
			return null;
		}
		if (!stack.hasItemMeta()) {
			return null;
		}
		return getItem(stack.getType(), stack.getItemMeta().getDisplayName());
	}

	public static void convertVanillaItemStack(ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		if (meta.getDisplayName().equals("")) {
			meta.setDisplayName(getItem(itemStack).item_name);
		}
	}

	public boolean hasDetail(ItemDetailType type) {
		for (ItemDetail detail : details) {
			if (detail.type == type) {
				return true;
			}
		}
		return false;
	}

	public boolean hasDetail(ItemDetail detail) {
		for (ItemDetail _detail : details) {
			if (_detail.type == detail.type) {
				return _detail.data.equals(detail.data);
			}
		}
		return false;
	}

	public ItemDetail getDetail(ItemDetailType type) {
		for (ItemDetail detail : details) {
			if (detail.type == type) {
				return detail;
			}
		}
		return null;
	}

	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("PlayerInteractEvent: action=" + event.getAction().name());
	}

}
