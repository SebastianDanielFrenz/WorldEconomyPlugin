package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class VanillaRecipe {

	public static ShapedRecipe STICK__PLANKS____CRAFTING_TABLE;

	public static void init() {
		STICK__PLANKS____CRAFTING_TABLE = new ShapedRecipe(new NamespacedKey(WorldEconomyPlugin.plugin, "crafting"),
				new ItemStack(Material.CRAFTING_TABLE));

		STICK__PLANKS____CRAFTING_TABLE.shape("SSS", "PPP", "PPP");

		STICK__PLANKS____CRAFTING_TABLE.setIngredient('S', Material.STICK);
		STICK__PLANKS____CRAFTING_TABLE.setIngredient('P', Material.OAK_PLANKS);
	}

}
