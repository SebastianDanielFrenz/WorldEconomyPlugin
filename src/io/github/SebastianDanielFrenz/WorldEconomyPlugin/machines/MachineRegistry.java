package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.campfires.MachineEgyptianCampfireStage1;

public class MachineRegistry {

	private static List<Machine> machineTypes = new ArrayList<Machine>();

	public static void register(Machine machine) {
		machineTypes.add(machine);
	}

	public static void init() {
		World world = Bukkit.getWorld("world");
		Location loc = new Location(world, 0, 0, 0);

		register(new MachineEgyptianCampfireStage1(loc));
	}

	public static List<Machine> getContents() {
		return machineTypes;
	}

}
