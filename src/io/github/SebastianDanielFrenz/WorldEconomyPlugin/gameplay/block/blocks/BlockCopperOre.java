package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.EmptyCustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDrop;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.ItemCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.OreGenerator;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.OrePatchType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.PatchOreGenerator;

public class BlockCopperOre extends CustomOre {

	public BlockCopperOre() {
		super(WorldEconomyPlugin.plugin, "copper_ore", Material.GOLD_ORE, false, new CustomBlockDropTable(
				new CustomBlockDrop[] { new CustomBlockDrop(CustomToolType.PICKAXE, CustomMaterialLevel.ANDESITE,
						new CustomItemStack[] { new CustomItemStack(new ItemCopperOre(), 1) }) }),
				EmptyCustomBlockData.class, 20, 35, 45, 60, 0.05);
	}

	@Override
	public OreGenerator getOreGenerator(World world, Chunk chunk) {
		// return new NoiseOreGenerator(world, chunk, this, 1D);
		return new PatchOreGenerator(world, chunk, this, 10, 40, 4, 18, OrePatchType.WORM);
	}

}
