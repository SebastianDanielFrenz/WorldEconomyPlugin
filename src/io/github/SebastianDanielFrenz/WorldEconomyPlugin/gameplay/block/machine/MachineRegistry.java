package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires.BlockEgyptianCampfireStage1;

public class MachineRegistry {

	private static List<Machine> machineTypes = new ArrayList<Machine>();

	public static void register(Machine machine) {
		machineTypes.add(machine);
	}

	public static void init() {
		register(new BlockEgyptianCampfireStage1());
	}

	public static List<Machine> getContents() {
		return machineTypes;
	}

}
