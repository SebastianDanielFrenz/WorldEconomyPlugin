package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;

public class BuildingIO {

	public static void save(String path, Location center, Location pos1, Location pos2) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				try {
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

					CustomBlockType block;
					Location loc;
					String ID;

					for (int y = minY; y <= maxY; y++) {
						for (int x = minX; x <= maxX; x++) {
							for (int z = minZ; z <= maxZ; z++) {
								loc = new Location(center.getWorld(), x, y, z);
								if (loc.getBlock().getType() == Material.AIR) {
									ID = "air";
								} else {
									block = CustomBlockTypeRegistry.getBlock(loc);
									if (block == null) {
										System.out.println("Illegal block " + loc.getBlock().getType() + " at " + loc);
										ID = "air";
									} else {
										ID = block.ID;
									}
								}

								fw.write(String.valueOf(x - centerX) + "," + String.valueOf(y - centerY) + "," + String.valueOf(z - centerZ) + ","
										+ ID + ";");
							}
						}
					}

					fw.flush();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void load(String path, Location center) throws IOException, InstantiationException, IllegalAccessException, SQLException {
		String text = Files.readAllLines(Paths.get(path)).get(0);

		String[] rblocks;
		CustomBlockType block = null;
		Location loc;
		String ID = null;

		int task_size = 5; // this means how many blocks will be placed per
							// tick.
		int runs = 0;
		int delay = 0;
		List<Location> locations = new ArrayList<Location>(task_size);
		List<CustomBlockType> blocks = new ArrayList<CustomBlockType>(task_size);

		for (String rblock : text.split(";")) {
			rblocks = rblock.split(",");
			loc = new Location(center.getWorld(), Integer.parseInt(rblocks[0]) + center.getBlockX(),
					Integer.parseInt(rblocks[1]) + center.getBlockY(), Integer.parseInt(rblocks[2]) + center.getBlockZ());

			if (rblocks[3].equals("air")) {
				loc.getBlock().setType(Material.AIR);
				// System.out.println("air at " + loc);
			} else {
				if (!rblocks[3].equals(ID)) {
					block = CustomBlockTypeRegistry.getBlock(rblocks[3]);
				}
				// CustomBlock.placeBlock(loc, block);
				locations.add(loc);
				blocks.add(block);

				ID = rblocks[3];
				// System.out.println(block.ID + " at " + loc);
			}

			runs++;
			if (runs % task_size == 0) {
				Bukkit.getScheduler().runTaskLater(WorldEconomyPlugin.plugin, new CustomBlockPlacerTask(locations, blocks), delay * 1);
				// System.out.println("generated task " + runs);

				locations = new ArrayList<Location>(task_size);
				blocks = new ArrayList<CustomBlockType>(task_size);

				delay++;
			}
		}
	}

	public static void loadInternal(String path, Location center) throws IOException, InstantiationException, IllegalAccessException, SQLException {
		InputStream input = WorldEconomyPlugin.class.getResourceAsStream("/res/schem/" + path);
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(isr);

		String text = br.readLine();

		String[] rblocks;
		CustomBlockType block = null;
		Location loc;
		String ID = null;

		for (String rblock : text.split(";")) {
			rblocks = rblock.split(",");
			loc = new Location(center.getWorld(), Integer.parseInt(rblocks[0]) + center.getBlockX(),
					Integer.parseInt(rblocks[1]) + center.getBlockY(), Integer.parseInt(rblocks[2]) + center.getBlockZ());

			if (rblocks[3].equals("air")) {
				loc.getBlock().setType(Material.AIR);
				// System.out.println("air at " + loc);
			} else {
				if (!rblocks[3].equals(ID)) {
					block = CustomBlockTypeRegistry.getBlock(rblocks[3]);
				}
				CustomBlockType.placeBlock(loc, block);
				ID = rblocks[3];
				// System.out.println(block.ID + " at " + loc);
			}
		}
	}

}
