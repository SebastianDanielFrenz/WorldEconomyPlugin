package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class CustomBlockDrop {

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, CustomBlockDropComponent[] components) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		this.components = components;
	}

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, CustomBlockDropComponent component) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		components = new CustomBlockDropComponent[] { component };
	}

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, CustomItemStack[] stack) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		components = new CustomBlockDropComponent[] { new CustomBlockDropDefaultComponent(stack) };
	}

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, CustomItemStack stack) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		components = new CustomBlockDropComponent[] { new CustomBlockDropDefaultComponent(stack) };
	}

	private final CustomToolType tool;
	private final CustomMaterialLevel min_lvl;
	private final CustomBlockDropComponent[] components;

	public CustomToolType getToolType() {
		return tool;
	}

	public CustomMaterialLevel getMinLvl() {
		return min_lvl;
	}

	public List<CustomItemStack> getDrop() {
		// the initial capacity of 1 means that I believe most block drops to
		// only be one item and that I am willing to sacrifice the performance
		// needed to recreate the array in the array list otherwise rather than
		// wasting RAM on 1 item drops.
		List<CustomItemStack> out = new ArrayList<CustomItemStack>(1);
		for (CustomBlockDropComponent component : components) {
			for (CustomItemStack stack : component.getDrop()) {
				out.add(stack);
			}
		}
		return out;
	}

}
