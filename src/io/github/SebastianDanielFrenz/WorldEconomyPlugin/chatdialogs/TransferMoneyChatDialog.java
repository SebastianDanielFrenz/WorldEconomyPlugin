package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialog;

public class TransferMoneyChatDialog extends ChatDialog {

	public TransferMoneyChatDialog(Player player, BankAccount src, BankAccount dst) {
		super(player);

		this.dst = dst;
		this.src = src;

		player.sendMessage(WorldEconomyPlugin.PREFIX + "Please enter the amount you wish to tranfer.");
	}

	private BankAccount dst;
	private BankAccount src;

	@Override
	public void recieve(String msg) {
		try {
			double amount = Double.parseDouble(msg);
			if (amount > src.getBalance()) {
				reply(WorldEconomyPlugin.PREFIX + "§4Your bank account does not have enough money!");
				close();
			} else {
				try {
					WEDB.bankAccountTransaction(src, dst, amount);
					close();
				} catch (SQLException e) {
					e.printStackTrace();
					reply(WorldEconomyPlugin.PREFIX + "§4Transaction failed! An internal error occured!");
					close();
				}
			}
		} catch (NumberFormatException e) {
			reply("Invalid number!");
			close();
		}
	}

}
