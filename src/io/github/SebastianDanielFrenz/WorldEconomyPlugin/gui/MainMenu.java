package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
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
				event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "mail writing chat dialog!");
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
				event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "my bank accounts GUI!");
			}
		}, new GUIItem(3, 1, WEGUIs.mkItem(Material.BROWN_WOOL, "Register Bank Account")) {
			@Override
			public void event(InventoryClickEvent event) {
				event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "register bank account GUI!");
			}
		}, new GUIItem(4, 1, WEGUIs.mkItem(Material.BLACK_WOOL, "Transfer Money")) {
			@Override
			public void event(InventoryClickEvent event) {
				event.getWhoClicked().sendMessage(WorldEconomyPlugin.PREFIX + "transfer money GUI!");
			}
		}, new GUIItem(1, 2, WEGUIs.mkItem(Material.LIGHT_GRAY_WOOL, "Companies")) {
			@Override
			public void event(InventoryClickEvent event) {
				WEGUIs.getCompaniesGUI(_this).openInventory((Player) event.getWhoClicked());
			}
		} });
	}
}
