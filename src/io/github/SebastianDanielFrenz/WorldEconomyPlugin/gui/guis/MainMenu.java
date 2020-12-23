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
		super(new GUIItem[] {}, Lang.getGuiTitleMainMenu(player), player);
		MainMenu _this = this;

		setItems(new GUIItem[] { new GUIItem(0, 4, mkItem(Material.SIGN, Lang.getGuiTitleMainMenu(player))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		}, new GUIItem(1, 0, mkItem(Material.WOOL, 1, 14, "§4" + Lang.getGuiItemMainMenu_Mailbox(player))) {
			@Override
			public void event(InventoryClickEvent event) {
				MailSubsystem.showPlayerInbox((Player) event.getWhoClicked());
			}
		}, new GUIItem(2, 0, mkItem(Material.WOOL, 1, 10, Lang.getGuiItemMainMenu_WriteMail(player))) {
			@Override
			public void event(InventoryClickEvent event) {
				new WriteMailChatDialog((Player) event.getWhoClicked());
			}
		}, new GUIItem(1, 1, mkItem(Material.WOOL, 1, 7, Lang.getGuiTitleBanks(player)), Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				Player player = (Player) event.getWhoClicked();
				player.sendMessage(WorldEconomyPlugin.PREFIX + "banks GUI!");

				player.closeInventory(); // not sure weather this handles the
											// inventory close event or not
				new BanksGUI(_this, player).openInventory();
			}
		}, new GUIItem(2, 1, mkItem(Material.WOOL, 1, 15, Lang.getGuiTitleBankAccounts(player)), Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new BankAccountsGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(3, 1, mkItem(Material.WOOL, 1, 12, Lang.getGuiTitleCreateBankAccount(player)), Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new CreateBankAccountGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(4, 1, mkItem(Material.WOOL, 1, 15, Lang.getGuiTitleTransferMoney(player)), Age.MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new TransferMoneyGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 2, mkItem(Material.WOOL, 1, 8, Lang.getGuiTitleCompanies(player)), Age.LATE_MIDDLE_AGES) {
			@Override
			public void event(InventoryClickEvent event) {
				new CompaniesGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 3, mkItem(Material.GLASS_BOTTLE, 1, 0, "§a" + Lang.getGuiTitleResearch(player))) {

			@Override
			public void event(InventoryClickEvent event) {
				new ResearchGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}, new GUIItem(1, 4, mkItem(Material.PAPER, "§e" + Lang.getGuiTitleStockMarket(player)), Age.LATE_MIDDLE_AGES) {

			@Override
			public void event(InventoryClickEvent event) {
				new StockMarketGUI(_this, (Player) event.getWhoClicked()).openInventory();
			}
		}

		});
	}
}
