package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;

public class MediumBuildingComposited extends MediumBuilding {

	public MediumBuildingComposited(SmallBuilding[] sub_buildings) {
		this.sub = sub_buildings;
	}

	protected SmallBuilding[] sub;

	@Override
	public void place(Location center, CustomBlockType pathway) throws Exception {
		int i = 0;
		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				sub[i].place(new Location(center.getWorld(), center.getBlockX() + x * (SMALL + 2) + 1, center.getBlockY(),
						center.getBlockZ() + z * (SMALL + 2) + 1), pathway);

				i++;
			}
		}
		for (int x = 0; x < MEDIUM + 4; x++) {
			CustomBlockType.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ()), pathway);
			CustomBlockType.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ() + MEDIUM + 3),
					pathway);
		}
		for (int z = 0; z < MEDIUM + 4; z++) {
			CustomBlockType.placeBlock(new Location(center.getWorld(), center.getBlockX(), center.getBlockY(), center.getBlockZ() + z), pathway);
			CustomBlockType.placeBlock(new Location(center.getWorld(), center.getBlockX() + MEDIUM + 3, center.getBlockY(), center.getBlockZ() + z),
					pathway);
		}
	}

}
