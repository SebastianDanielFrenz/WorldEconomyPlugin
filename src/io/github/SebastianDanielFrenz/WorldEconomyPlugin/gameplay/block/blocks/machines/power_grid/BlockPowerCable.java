package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.power_grid;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerCableBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerCableBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

public class BlockPowerCable extends PowerCableBlockType {

	public BlockPowerCable() {
		super(WorldEconomyPlugin.plugin, "power_cable", Material.WOOL, 0, false, easyDrop(CustomItemRegistry.POWER_CABLE), PowerCableBlockData.class);
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		((PowerCableBlockData) ((CustomBlockMetadataValue) event.getClickedBlock().getMetadata("customBlockType").get(0)).getBlockData()).powerGrid
				.dump(event.getPlayer());
	}

}
