package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;

public class SetblockChatDialog extends ChatDialog {

	public SetblockChatDialog(Player player, CustomBlockType block,
			Constructor<? extends CustomBlockData> dataConstructor) {
		super(player);

		this.dataConstructor = dataConstructor;
		this.block = block;

		player.sendMessage("Please enter the blockdata.");
	}

	private Constructor<? extends CustomBlockData> dataConstructor;
	private CustomBlockType block;

	@Override
	public void recieve(String msg) {
		try {
			CustomBlockType.placeBlock(player.getLocation(), block, dataConstructor.newInstance(msg));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SQLException e) {
			e.printStackTrace();
			reply(WorldEconomyPlugin.PREFIX + "§4An internal error occured while creating the blockdata!");
		}
		close();
	}

}
