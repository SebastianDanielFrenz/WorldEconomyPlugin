package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.research;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.Machine;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces.BasicBlastFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage2;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage3;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves.BasicSieveStage1;

public enum ResearchItem {

	/*
	 * ==========
	 * 
	 * Furnaces
	 * 
	 * ==========
	 */
	BASIC_FURNACE_STAGE1(1, new BasicFurnaceStage1(null), new ResearchItem[] {}, Units.HOUR),
	BASIC_FURNACE_STAGE2(2, new BasicFurnaceStage2(null), new ResearchItem[] { BASIC_FURNACE_STAGE1 },
			Units.MINUTE * 30),
	BASIC_FURNACE_STAGE3(3, new BasicFurnaceStage3(null), new ResearchItem[] { BASIC_FURNACE_STAGE2 },
			Units.MINUTE * 30),

	/*
	 * ==========
	 * 
	 * Blast Furnaces
	 * 
	 * ==========
	 */

	BASIC_BLAST_FURNACE_STAGE1(4, new BasicBlastFurnaceStage1(null), new ResearchItem[] { BASIC_FURNACE_STAGE1 },
			Units.HOUR * 2),

	/*
	 * ==========
	 * 
	 * Sieves
	 * 
	 * ==========
	 */
	BASIC_SIEVE_STAGE1(4, new BasicSieveStage1(null), new ResearchItem[] {}, Units.MINUTE * 5);

	private ResearchItem(long ID, Machine machine, ResearchItem[] parents, long duration) {
		this.ID = ID;
		this.machine = machine;
		this.duration = duration;
		this.parents = parents;
	}

	public final long ID;
	private Machine machine;
	private long duration;
	private ResearchItem[] parents;

	public Machine getMachine() {
		return machine;
	}

	public long getDuration() {
		return duration;
	}

	public ResearchItem[] getParents() {
		return parents;
	}

	public static ResearchItem getItem(long ID) {
		for (ResearchItem item : ResearchItem.values()) {
			if (item.ID == ID) {
				return item;
			}
		}
		return null;
	}

}