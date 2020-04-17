package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class CustomBlockDropDefaultComponent extends CustomBlockDropComponent {

	private final List<CustomItemStack> items;

	public CustomBlockDropDefaultComponent(final CustomItemStack[] items) {
		this.items = new ArrayList<CustomItemStack>(items.length);
		for (CustomItemStack item : items) {
			this.items.add(item);
		}
	}

	public CustomBlockDropDefaultComponent(final CustomItemStack item) {
		this.items = new ArrayList<CustomItemStack>(1);
		this.items.add(item);
	}

	@Override
	public List<CustomItemStack> getDrop() {
		return items;
	}

}
