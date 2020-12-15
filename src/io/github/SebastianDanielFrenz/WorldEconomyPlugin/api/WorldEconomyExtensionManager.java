package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import java.util.logging.Level;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Version;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIAction;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIActionCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIActionHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIProperty;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIPropertyRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CustomCommand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CustomCommandGroup;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.WorldEconomyCustomCommandRegistryCommandExecutor;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class WorldEconomyExtensionManager {

	public static WorldEconomyExtensionManager instance = new WorldEconomyExtensionManager();

	public static void registerCustomBlock(CustomBlockType blockType) {
		CustomBlockTypeRegistry.register(blockType);
	}

	public static void registerCustomItemWithoutBlock(CustomItem item) {
		CustomItemRegistry.register(item);
		if (!Age.getAllAges().contains(item.age)) {
			WorldEconomyPlugin.plugin.getLogger().log(Level.WARNING, "Registering item " + item.ID + " ["
					+ item.getClass() + "] " + "without block using non-registered age!");
		}
	}

	public static void registerCustomItemWithBlock(CustomItem item, CustomBlockType blockType) {
		CustomItemRegistry.register(item, blockType);
		if (!Age.getAllAges().contains(item.age)) {
			WorldEconomyPlugin.plugin.getLogger().log(Level.WARNING, "Registering item " + item.ID + " ["
					+ item.getClass() + "] " + "with block using non-registered age!");
		}
	}

	public static void registerAge(Age age, ResearchItem[] requirements) {
		Age.registerAge(age, requirements);
	}

	public static void registerAIproperty(AIProperty property) {
		AIPropertyRegistry.registerProperty(property);
	}

	public static Version getExtensionVersion(Class<? extends WorldEconomyExtension> extension) {
		for (WorldEconomyExtension ext : WorldEconomyExtensionRegistry.getExtensions()) {
			if (extension.isInstance(ext)) {
				return ext.getVersion();
			}
		}
		return null;
	}

	public static void registerAIAction(AIAction action, AIActionCondition condition) {
		AIActionHandler.conditional_behaviour.put(action, condition);
	}

	public static void registerCommand(CustomCommand command) {
		command.parent.children.add(command);
	}

	public static void registerCommand(CustomCommandGroup command) {
		command.parent.children.add(command);
	}

	public static void registerCommandRoot(CustomCommandGroup command) {
		WorldEconomyCustomCommandRegistryCommandExecutor.root.put(command.command, command);
	}

}
