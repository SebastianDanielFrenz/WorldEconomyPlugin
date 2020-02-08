package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialog;

public class WriteMailChatDialog extends ChatDialog {

	public WriteMailChatDialog(Player player) {
		super(player);

		player.sendMessage(WorldEconomyPlugin.PREFIX + "Please enter your destination's mail ID");
	}

	private int step = 0;
	private int new_step;
	private long dst;

	@Override
	public void recieve(String msg) {
		switch (step) {
		case 0:
			try {
				dst = Long.parseLong(msg);
				reply(WorldEconomyPlugin.PREFIX + "Please enter the message.");
			} catch (NumberFormatException e) {
				reply(WorldEconomyPlugin.PREFIX + "§4That is not a number!");
				close();
			}
			new_step = 1;
			break;
		case 1:
			try {
				WEDB.sendMail(WEDB.getUserProfile(player).mailboxID, dst, msg);
				close();
			} catch (SQLException e) {
				e.printStackTrace();
				reply(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
				close();
			}
			new_step = 2;
			break;
		}

		step = new_step;

	}

}
