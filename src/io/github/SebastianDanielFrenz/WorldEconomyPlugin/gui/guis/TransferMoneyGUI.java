package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AIProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.TransferMoneyChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BankAccountChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class TransferMoneyGUI extends WEGUI {

	public TransferMoneyGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, "Transfer Money");

		TransferMoneyGUI out = this;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<UserProfile> users = WEDB.getAllUserProfiles();
			for (UserProfile user : users) {
				items.add(new GUIItem(slot, mkItem(BlockLib.PLAYER, user.username, new String[] { "player" })) {
					@Override
					public void event(InventoryClickEvent event) {
						// this user.bankingID is NOT the GUI viewer's profile!
						new ChooseBankAccountGUI(out, "Choose the recipient's bank account", user.bankingID, new BankAccountChooserEvent() {
							@Override
							public void event(InventoryClickEvent event2, BankAccount dst) {
								try {
									new ChooseBankAccountGUI(parent, "Choose your bank account", WEDB.getUserProfile(player).bankingID,
											new BankAccountChooserEvent() {
												@Override
												public void event(InventoryClickEvent event, BankAccount src) {
													new TransferMoneyChatDialog(player, src, dst);
												}
											}).openInventory(player);
								} catch (SQLException e) {
									e.printStackTrace();
									player.sendMessage(WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
								}
							}
						}).openInventory(player);
					}
				});
				slot++;
			}
			List<AIProfile> AIs = WEDB.getAllAIs();
			for (AIProfile AI : AIs) {
				items.add(new GUIItem(slot, BlockLib.ai(AI.username)) {

					@Override
					public void event(InventoryClickEvent event) {
						new ChooseBankAccountGUI(out, "Choose the recipient's bank account", AI.bankingID, new BankAccountChooserEvent() {
							@Override
							public void event(InventoryClickEvent event2, BankAccount dst) {
								try {
									new ChooseBankAccountGUI(parent, "Choose your bank account", WEDB.getUserProfile(player).bankingID,
											new BankAccountChooserEvent() {
												@Override
												public void event(InventoryClickEvent event, BankAccount src) {
													new TransferMoneyChatDialog(player, src, dst);
												}
											}).openInventory(player);
								} catch (SQLException e) {
									e.printStackTrace();
									player.sendMessage(WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
								}
							}
						}).openInventory(player);
					}
				});
				slot++;
			}

			List<Company> companies = WEDB.getAllCompanies();
			for (Company company : companies) {
				if (company.companyType.equals("corporation")) {
					items.add(new GUIItem(slot, BlockLib.company(company)) {
						@Override
						public void event(InventoryClickEvent event) {
							new ChooseBankAccountGUI(out, "Choose the recipient's bank account", company.bankingID, new BankAccountChooserEvent() {
								@Override
								public void event(InventoryClickEvent event2, BankAccount dst) {
									try {
										new ChooseBankAccountGUI(parent, "Choose your bank account", WEDB.getUserProfile(player).bankingID,
												new BankAccountChooserEvent() {
													@Override
													public void event(InventoryClickEvent event, BankAccount src) {
														new TransferMoneyChatDialog(player, src, dst);
													}
												}).openInventory(player);
									} catch (SQLException e) {
										e.printStackTrace();
										player.sendMessage(WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
									}
								}
							}).openInventory(player);
						}
					});
				} else if (company.companyType.equals("private")) {
					items.add(new GUIItem(slot, BlockLib.company(company)) {
						@Override
						public void event(InventoryClickEvent event) {
							new ChooseBankAccountGUI(out, "Choose the recipient's bank account", company.bankingID, new BankAccountChooserEvent() {
								@Override
								public void event(InventoryClickEvent event2, BankAccount dst) {
									try {
										new ChooseBankAccountGUI(parent, "Choose your bank account", WEDB.getUserProfile(player).bankingID,
												new BankAccountChooserEvent() {
													@Override
													public void event(InventoryClickEvent event, BankAccount src) {
														new TransferMoneyChatDialog(player, src, dst);
													}
												}).openInventory(player);
									} catch (SQLException e) {
										e.printStackTrace();
										player.sendMessage(WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
									}
								}
							}).openInventory(player);
						}
					});
				} else {
					items.add(new GUIItem(slot, mkItem(Material.BARRIER, company.companyName,
							new String[] { "§4INVALID COMPANY TYPE \"" + company.companyType + "\"!" })) {
						@Override
						public void event(InventoryClickEvent event) {
						}
					});
				}
				slot++;
			}

			setItems(convert(items));

		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
