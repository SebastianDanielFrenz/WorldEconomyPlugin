package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockRegistry;

public class BuildingIO {

	public static void save(String path, Location center, Location pos1, Location pos2) throws IOException {
		FileWriter fw = new FileWriter(path);

		int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
		int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());

		int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
		int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());

		int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
		int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

		int centerX = center.getBlockX();
		int centerY = center.getBlockY();
		int centerZ = center.getBlockZ();

		CustomBlock block;
		Location loc;
		String ID;

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					loc = new Location(center.getWorld(), x, y, z);
					if (loc.getBlock().getType() == Material.AIR) {
						ID = "air";
					} else {
						block = CustomBlockRegistry.getBlock(loc);
						if (block == null) {
							System.out.println("Illegal block at " + loc);
							ID = "air";
						} else {
							ID = block.ID;
						}
					}

					fw.write(String.valueOf(x - centerX) + "," + String.valueOf(y - centerY) + ","
							+ String.valueOf(z - centerZ) + "," + ID + ";");
				}
			}
		}

		fw.flush();
		fw.close();
	}

	public static void load(String path, Location center)
			throws IOException, InstantiationException, IllegalAccessException, SQLException {
		String text = Files.readAllLines(Paths.get(path)).get(0);

		String[] rblocks;
		CustomBlock block = null;
		Location loc;
		String ID = null;

		for (String rblock : text.split(";")) {
			rblocks = rblock.split(",");
			loc = new Location(center.getWorld(), Integer.parseInt(rblocks[0]) + center.getBlockX(),
					Integer.parseInt(rblocks[1]) + center.getBlockY(),
					Integer.parseInt(rblocks[2]) + center.getBlockZ());

			if (rblocks[3].equals("air")) {
				loc.getBlock().setType(Material.AIR);
				// System.out.println("air at " + loc);
			} else {
				if (!rblocks[3].equals(ID)) {
					block = CustomBlockRegistry.getBlock(rblocks[3]);
				}
				CustomBlock.placeBlock(loc, block);
				ID = rblocks[3];
				// System.out.println(block.ID + " at " + loc);
			}
		}
	}

}
