package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.WriteMailChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;

public class MainMenu extends WEGUI {

	public MainMenu() {
		super(new GUIItem[] {}, "World Economy");
		MainMenu _this = this;

		setItems(new GUIItem[] { new GUIItem(0, 4, WEGUIs.mkItem(Material.OAK_SIGN, "Main Menu")) {
			@Override
			public void event(InventoryClickEvent event) {
				event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "There is nothing to do here!");
			}
		}, new GUIItem(1, 0, WEGUIs.mkItem(Material.RED_WOOL, "§4Mailbox")) {
			@Override
			public void event(InventoryClickEvent event) {
				MailSubsystem.showPlayerInbox((Player) event.getWhoClicked());
			}
		}, new GUIItem(2, 0, WEGUIs.mkItem(Material.PURPLE_WOOL, "Write Mail")) {
			@Override
			public void event(InventoryClickEvent event) {
				new WriteMailChatDialog((Player) event.getWhoClicked());
			}
		}, new GUIItem(1, 1, WEGUIs.mkItem(Material.GRAY_WOOL, "Banks")) {
			@Override
			public void event(InventoryClickEvent event) {
				Player player = (Player) event.getWhoClicked();
				player.sendMessage(WorldEconomyPlugin.PREFIX + "banks GUI!");

				player.closeInventory(); // not sure weather this handles the
											// inventory close event or not
				WEGUIs.getBanksGUI(_this).openInventory(player);
			}
		}, new GUIItem(2, 1, WEGUIs.mkItem(Material.BLACK_WOOL, "My Bank Accounts")) {
			@Override
			public void event(InventoryClickEvent event) {
				WEGUIs.getBankAccountsGUI(_this, (Player) event.getWhoClicked())
						.openInventory((Player) event.getWhoClicked());
			}
		}, new GUIItem(3, 1, WEGUIs.mkItem(Material.BROWN_WOOL, "Register Bank Account")) {
			@Override
			public void event(InventoryClickEvent event) {
				WEGUIs.getCreateBankAccountGUI(_this, (Player) event.getWhoClicked())
						.openInventory((Player) event.getWhoClicked());
			}
		}, new GUIItem(4, 1, WEGUIs.mkItem(Material.BLACK_WOOL, "Transfer Money")) {
			@Override
			public void event(InventoryClickEvent event) {
				WEGUIs.getTransferMoneyGUI(_this, (Player) event.getWhoClicked())
						.openInventory((Player) event.getWhoClicked());
			}
		}, new GUIItem(1, 2, WEGUIs.mkItem(Material.LIGHT_GRAY_WOOL, "Companies")) {
			@Override
			public void event(InventoryClickEvent event) {
				WEGUIs.getCompaniesGUI(_this).openInventory((Player) event.getWhoClicked());
			}
		} });
	}
}
