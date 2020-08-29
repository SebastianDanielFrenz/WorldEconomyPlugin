package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.power_grid;

import org.bukkit.Material;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerCableBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerCableBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

public class BlockPowerCable extends PowerCableBlockType {

	public BlockPowerCable() {
		super(WorldEconomyPlugin.plugin, "power_cable", Material.WOOL, 0, false,
				easyDrop(CustomItemRegistry.POWER_CABLE), PowerCableBlockData.class);
	}

}
