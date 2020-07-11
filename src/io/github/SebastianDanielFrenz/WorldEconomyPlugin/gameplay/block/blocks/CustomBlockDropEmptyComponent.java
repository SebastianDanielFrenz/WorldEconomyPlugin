package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropComponent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class CustomBlockDropEmptyComponent extends CustomBlockDropComponent {

	@Override
	public List<CustomItemStack> getDrop() {
		List<CustomItemStack> out = new ArrayList<CustomItemStack>(1);
		out.add(new CustomItemStack(CustomItemRegistry.DIRT, 0));
		return out;
	}

}
