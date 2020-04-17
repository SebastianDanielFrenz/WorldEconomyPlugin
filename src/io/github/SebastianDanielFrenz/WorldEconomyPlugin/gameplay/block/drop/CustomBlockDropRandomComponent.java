package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class CustomBlockDropRandomComponent extends CustomBlockDropComponent {

	public CustomBlockDropRandomComponent(CustomItem type, final int min, final int max) {
		this.min = min;
		this.max = max;
		this.type = type;
		data = new CustomItemData();
	}

	public CustomBlockDropRandomComponent(CustomItem type, final int min, final int max, CustomItemData data) {
		this.min = min;
		this.max = max;
		this.type = type;
		this.data = data;
	}

	private final int min;
	private final int max;
	private final CustomItem type;
	private final CustomItemData data;

	private int tmp;

	@Override
	public List<CustomItemStack> getDrop() {
		List<CustomItemStack> out = new ArrayList<CustomItemStack>(1);
		tmp = WorldEconomyPlugin.randomizer.nextInt(max - min + 1) + min;
		if (tmp > 0) {
			out.add(new CustomItemStack(type, tmp, data));
		} else if (tmp < 0) {
			throw new RuntimeException(
					"Illegal CustomBlockDropRandomComponent-drop: supposed to drop " + tmp + "x" + type.ID + "(" + data.toString() + ")");
		}
		return out;
	}

}
