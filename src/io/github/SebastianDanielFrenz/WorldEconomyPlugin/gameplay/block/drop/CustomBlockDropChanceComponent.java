package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class CustomBlockDropChanceComponent extends CustomBlockDropComponent {

	private final double chance;
	private final CustomItemStack stack;

	public CustomBlockDropChanceComponent(CustomItemStack stack, double chance) {
		this.stack = stack;
		this.chance = chance;
	}

	@Override
	public List<CustomItemStack> getDrop() {
		List<CustomItemStack> out = new ArrayList<CustomItemStack>(chance > 0.5 ? 1 : 0);

		if (WorldEconomyPlugin.randomizer.nextDouble() <= chance) {
			out.add(stack);
		}

		return out;

	}

}
