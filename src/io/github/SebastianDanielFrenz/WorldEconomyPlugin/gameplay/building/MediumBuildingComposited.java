package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockRegistry;

public class MediumBuildingComposited extends MediumBuilding {

	public MediumBuildingComposited(SmallBuilding[] sub_buildings) {
		this.sub = sub_buildings;
	}

	protected SmallBuilding[] sub;

	@Override
	public void place(Location center, CustomBlock pathway) throws Exception {
		int i = 0;
		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				sub[i].place(
						new Location(center.getWorld(), center.getBlockX() + x * SMALL + 1, center.getBlockY(), center.getBlockZ() + z * SMALL + 1),
						pathway);

				i++;
			}
		}
		pathway = CustomBlockRegistry.granite;
		for (int x = 0; x < MEDIUM; x++) {
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ()), pathway);
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ() + MEDIUM - 1),
					pathway);
		}
		for (int z = 0; z < MEDIUM; z++) {
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX(), center.getBlockY(), center.getBlockZ() + z), pathway);
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + MEDIUM - 1, center.getBlockY(), center.getBlockZ() + z),
					pathway);
		}
	}

}
