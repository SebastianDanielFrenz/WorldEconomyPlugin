package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Version;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIProperty;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIPropertyRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class WorldEconomyExtensionManager {

	public static WorldEconomyExtensionManager instance = new WorldEconomyExtensionManager();

	public void registerCustomBlock(CustomBlockType blockType) {
		CustomBlockTypeRegistry.register(blockType);
	}

	public void registerCustomItemWithoutBlock(CustomItem item) {
		CustomItemRegistry.register(item);
	}

	public void registerCustomItemWithBlock(CustomItem item, CustomBlockType blockType) {
		CustomItemRegistry.register(item, blockType);
	}

	public void registerAge(Age age, ResearchItem[] requirements) {
		Age.registerAge(age, requirements);
	}

	public void registerAIproperty(AIProperty property) {
		AIPropertyRegistry.registerProperty(property);
	}

	public Version getExtensionVersion(Class<? extends WorldEconomyExtension> extension) {
		for (WorldEconomyExtension ext : WorldEconomyExtensionRegistry.getExtensions()) {
			if (extension.isInstance(ext)) {
				return ext.getVersion();
			}
		}
		return null;
	}

}
