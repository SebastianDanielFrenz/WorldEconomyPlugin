package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.admin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerConsumerTesterBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerConsumerBlockType;

public class BlockPowerConsumerTester extends PowerConsumerBlockType {

	public BlockPowerConsumerTester() {
		super(WorldEconomyPlugin.plugin, "power_consumer_tester", Material.IRON_BLOCK, 0, false, new CustomBlockDropTable(new CustomBlockDrop[] {}),
				PowerConsumerTesterBlockData.class);
	}

	@Override
	public double getMaxPower(Location location, CustomBlockData blockData) {
		return 1;
	}

	@Override
	public boolean acceptPower(Location location, CustomBlockData blockData, double amount) {
		return true;
	}

	@Override
	public void assignPower(Location location, CustomBlockData blockData, double amount) {
		((PowerConsumerTesterBlockData) blockData).addTmpPower(amount);
	}

	@Override
	public void usePower(Location location, CustomBlockData blockData) {
		PowerConsumerTesterBlockData data = ((PowerConsumerTesterBlockData) blockData);

		data.setPowered(data.getTmpPower() > 0);
		data.setPowerRecieved(data.getTmpPower());
		data.resetTmpPower();

	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		PowerConsumerTesterBlockData data = (PowerConsumerTesterBlockData) CustomBlockTypeRegistry.getBlockDetails(block).getBlockData();
		if (data.isPowered()) {
			event.getPlayer().sendMessage("§aPowered (" + data.getPowerRecieved() + "\t)");
		} else {
			event.getPlayer().sendMessage("§4Unpowered");
		}
	}

}
