package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.WriteMailChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;

public class MainMenu extends WEGUI {

	public MainMenu(Player player) {
		super(new GUIItem[] {}, Lang.get(player, Lang.GUI_TITLE_MAIN_MENU), player);
		MainMenu _this = this;

		setItems(new GUIItem[] { new GUIItem(0, 4, mkItem(Material.SIGN, Lang.get(player, Lang.GUI_TITLE_MAIN_MENU))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		}, new GUIItem(1, 0, mkItem(Material.WOOL, 1, 14, "§4" + Lang.get(player, Lang.GUI_ITEM_MAIN_MENU__MAILBOX))) {
			@Override
			public void event(InventoryClickEvent event) {
				MailSubsystem.showPlayerInbox((Player) event.getWhoClicked());
			}
		}, new GUIItem(2, 0, mkItem(Material.WOOL, 1, 10, Lang.get(player, Lang.GUI_ITEM_MAIN_MENU__WRITE_MAIL))) {
			@Override
			public void event(InventoryClickEvent event) {
				new WriteMailChatDialog((Player) event.getWhoClicked());
			}
		}, new GUIItem(1, 1, mkItem(Material.WOOL, 1, 7, Lang.get(player, Lang.GUI_TITLE_BANKS)), Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				Player player = (Player) event.getWhoClicked();
				player.sendMessage(WorldEconomyPlugin.PREFIX + "banks GUI!");

				player.closeInventory(); // not sure weather this handles the
											// inventory close event or not
				new BanksGUI(_this, player).openInventory();
			}
		}, new GUIItem(2, 1, mkItem(Material.WOOL, 1, 15, Lang.get(player, Lang.GUI_TITLE_BANK_ACCOUNTS)),
				Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new BankAccountsGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(3, 1, mkItem(Material.WOOL, 1, 12, Lang.get(player, Lang.GUI_TITLE_CREATE_BANK_ACCOUNT)),
				Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new CreateBankAccountGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(4, 1, mkItem(Material.WOOL, 1, 15, Lang.get(player, Lang.GUI_TITLE_TRANSFER_MONEY)),
				Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new TransferMoneyGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 2, mkItem(Material.WOOL, 1, 8, Lang.get(player, Lang.GUI_TITLE_COMPANIES)),
				Age.LATE_MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new CompaniesGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 3, mkItem(Material.GLASS_BOTTLE, 1, 0, "§a" + Lang.get(player, Lang.GUI_TITLE_RESEARCH))) {

			@Override
			public void event(InventoryClickEvent event) {
				new ResearchGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 4, mkItem(Material.PAPER, "§e" + Lang.get(player, Lang.GUI_TITLE_STOCK_MARKET)),
				Age.LATE_MIDDLE_AGES) {

			@Override
			public void event(InventoryClickEvent event) {
				new StockMarketGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}

		});
	}
}
