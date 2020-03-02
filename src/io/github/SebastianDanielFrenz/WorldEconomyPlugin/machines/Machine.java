package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceTier1;

public abstract class Machine implements InventoryHolder {

	Location location;

	@Override
	public Inventory getInventory() {
		return MachineInventoryRegistry.getInventory(location);
	}

	public static MachineRecipe[] mergeRecipes(MachineRecipe[] recipes1, MachineRecipe[] recipes2) {
		MachineRecipe[] recipes = new MachineRecipe[recipes1.length + recipes2.length];
		for (int i = 0; i < recipes1.length; i++) {
			recipes[i] = recipes1[i];
		}
		for (int i = 0; i < recipes2.length; i++) {
			recipes[i + recipes1.length] = recipes2[i];
		}

		return recipes;
	}

	public abstract MachineKategory getKategory();

	public abstract double getMaintenanceCost();

	public abstract double getMaintenanceFrequency();

	public abstract void playerUseEvent(Player player);

	public abstract MachineRecipe[] getRecipes();

	public abstract double getProcessTimeMultiplier();

	public static boolean canBeMachine(Material material) {
		return material == MachineKategory.CRAFTING.display || material == MachineKategory.SMELTING.display
				|| material == MachineKategory.FORGING.display;
	}

	public static int getMachineLevel(Block block) {
		Block down = block.getRelative(BlockFace.DOWN);
		if (down.getType() == Material.WHITE_WOOL) {
			return 1;
		} else if (down.getType() == Material.ORANGE_WOOL) {
			return 2;
		} else if (down.getType() == Material.MAGENTA_WOOL) {
			return 3;
		} else if (down.getType() == Material.LIGHT_BLUE_WOOL) {
			return 4;
		} else if (down.getType() == Material.YELLOW_WOOL) {
			return 5; // TODO more tiers
		} else {
			return 0;
		}
	}

	public static Material getBlockForLevel(int lvl) {
		switch (lvl) {
		case 1:
			return Material.WHITE_WOOL;
		case 2:
			return Material.ORANGE_WOOL;
		case 3:
			return Material.MAGENTA_WOOL;
		case 4:
			return Material.LIGHT_BLUE_WOOL;
		case 5:
			return Material.YELLOW_WOOL;
		default:
			return null;
		}
	}

	public static MachineGroup getMachineGroup(Block block) {
		List<MetadataValue> meta = block.getMetadata("machineGroup");
		return (MachineGroup) meta.get(0).value();
	}

	public static MachineGroup getMachineGroup(String name) {
		if (name.equals(MachineGroup.BASIC_FURNACE.getName())) {
			return MachineGroup.BASIC_FURNACE;
		} else {
			return null;
		}
	}

	public static void setMachine(Block block, MachineKategory kategory, MachineGroup group, int lvl) {
		block.setType(kategory.display);
		block.setMetadata("machineGroup", new WorldEconomyMachineMeta(group));
		block.getRelative(BlockFace.DOWN).setType(getBlockForLevel(lvl));
		try {
			WEDB.registerMachine(block.getLocation(), group);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void turnIntoMachine(Block block, MachineGroup group, int lvl) {
		block.setMetadata("machineGroup", new WorldEconomyMachineMeta(group));
		block.getRelative(BlockFace.DOWN).setType(getBlockForLevel(lvl));
		try {
			WEDB.registerMachine(block.getLocation(), group);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setMachine(Block block, MachineGroup group, int lvl) {
		setMachine(block, group.getKategory(), group, lvl);
	}

	public static Machine getMachine(Block block) {
		MachineGroup group = getMachineGroup(block);
		if (group == null) {
			return null;
		}
		int lvl = getMachineLevel(block);

		if (group == MachineGroup.BASIC_FURNACE) {
			if (lvl == 1) {
				return new BasicFurnaceTier1();
			} else {
				throw new MachineNotSupportedException("There is no basic furnace tier " + lvl + "!");
			}
		} else {
			throw new RuntimeException("There is no such machine group as \"" + group.name());
		}
	}

}
