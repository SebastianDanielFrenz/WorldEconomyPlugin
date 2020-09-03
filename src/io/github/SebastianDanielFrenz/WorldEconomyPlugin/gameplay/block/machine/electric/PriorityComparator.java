package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import java.util.Comparator;

import org.bukkit.block.Block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerConnectedBlockData;

public class PriorityComparator implements Comparator<Block> {

	@Override
	public int compare(Block o1, Block o2) {
		int p1 = ((PowerConnectedBlockData) CustomBlockTypeRegistry.getBlockDetails(o1).getBlockData()).getPriority();
		int p2 = ((PowerConnectedBlockData) CustomBlockTypeRegistry.getBlockDetails(o2).getBlockData()).getPriority();
		if (p1 > p2) {
			return 1;
		} else if (p1 == p2) {
			return 0;
		}
		return -1;
	}

}
