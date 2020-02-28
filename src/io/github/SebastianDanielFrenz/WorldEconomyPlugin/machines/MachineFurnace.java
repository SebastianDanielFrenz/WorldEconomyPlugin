package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources.ItemTransactionManager;

public abstract class MachineFurnace extends Machine {
	@Override
	public MachineKategory getKategory() {
		return MachineKategory.SMELTING;
	}

	@Override
	public void playerUseEvent(Player player) {
		// TODO this only works for one item type at a time!
		for (MachineRecipe recipe : getRecipes()) {
			if (recipe.getInput().length != 1) {
				throw new RuntimeException("Furnaces can currently only deal with one item recipes!");
			}
			if (recipe.getInput()[0].getItem().matches(player.getInventory().getItemInMainHand())) {
				for (CustomItemStack stack : recipe.getOutput()) {
					ItemTransactionManager.give(player.getInventory(), stack);
				}
			}
		}
	}
}
