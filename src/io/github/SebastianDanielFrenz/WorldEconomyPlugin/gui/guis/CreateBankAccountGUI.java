package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.CreateBankAccountChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class CreateBankAccountGUI extends ChooseBankGUI {

	public CreateBankAccountGUI(WEGUI parent, Player player) {
		super(parent, player, new BankChooserEvent() {
			@Override
			public void event(InventoryClickEvent event, Bank bank) {
				new CreateBankAccountChatDialog(player, bank);
			}
		}, Lang.get(player, Lang.GUI_TITLE_CREATE_BANK_ACCOUNT));
	}

	public CreateBankAccountGUI(Player player) {
		super(player, new BankChooserEvent() {
			@Override
			public void event(InventoryClickEvent event, Bank bank) {
				new CreateBankAccountChatDialog(player, bank);
			}
		}, Lang.get(player, Lang.GUI_TITLE_CREATE_BANK_ACCOUNT));
	}

}
