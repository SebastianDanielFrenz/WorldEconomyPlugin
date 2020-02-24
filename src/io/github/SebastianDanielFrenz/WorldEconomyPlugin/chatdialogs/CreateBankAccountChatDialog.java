package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialog;

public class CreateBankAccountChatDialog extends ChatDialog {

	public CreateBankAccountChatDialog(Player player, Bank bank) {
		super(player);

		this.bank = bank;

		player.sendMessage(WorldEconomyPlugin.PREFIX + "Please enter your new bank account's name.");
	}

	private Bank bank;

	@Override
	public void recieve(String msg) {
		try {
			WEDB.registerBankAccount(new BankAccount(0, player, bank.ID, 0, msg));

			ItemStack creditCard = new ItemStack(Material.PAPER);
			ItemMeta itemMeta = creditCard.getItemMeta();

			List<String> lore = new ArrayList<String>();
			lore.add("Credit Card");
			lore.add(msg);
			itemMeta.setLore(lore);
			creditCard.setItemMeta(itemMeta);

			player.getInventory().addItem(creditCard);
			player.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully registered your account \"" + msg + "\" at "
					+ bank.name + "!");
			close();
		} catch (SQLException e) {
			e.printStackTrace();
			player.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
			close();
		}
	}

}
