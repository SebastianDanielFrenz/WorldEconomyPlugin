package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AIProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.CreateBankAccountChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.TransferMoneyChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Employee;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeeAI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeePlayer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;

public class WEGUIs {

	public static ItemStack mkItem(Material material, String name, String[] lore) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();
		ArrayList<String> _lore = new ArrayList<String>();

		for (int i = 0; i < lore.length; i++) {
			_lore.add(lore[i]);
		}

		meta.setLore(_lore);
		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, String name) {
		ItemStack out = new ItemStack(material);
		ItemMeta meta = out.getItemMeta();

		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, int amount, String name, String[] lore) {
		ItemStack out = new ItemStack(material, amount);
		ItemMeta meta = out.getItemMeta();
		ArrayList<String> _lore = new ArrayList<String>();

		for (int i = 0; i < lore.length; i++) {
			_lore.add(lore[i]);
		}

		meta.setLore(_lore);
		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static ItemStack mkItem(Material material, int amount, String name) {
		ItemStack out = new ItemStack(material, amount);
		ItemMeta meta = out.getItemMeta();

		meta.setDisplayName(name);
		out.setItemMeta(meta);
		return out;
	}

	public static WEGUI getMainGUI() {
		return new MainMenu();
	}

	public static WEGUI getErrorGUI(WEGUI parent, String title) {
		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {

					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		return new WEGUI(parent, convert(items), title);
	}

	public static WEGUI getErrorGUI(String title) {
		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {

					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		return new WEGUI(convert(items), title);
	}

	public static WEGUI getErrorGUI() {
		List<GUIItem> items = new ArrayList<GUIItem>();

		ItemStack item;
		ItemMeta meta;

		for (int x = 0; x < 9; x++) {
			for (int y = 1; y < 5; y++) {
				item = new ItemStack(Material.BARRIER);
				meta = item.getItemMeta();
				meta.setDisplayName("§4§lERROR");
				item.setItemMeta(meta);

				items.add(new GUIItem(y, x, item) {

					@Override
					public void event(InventoryClickEvent event) {
					}
				});
			}
		}
		return new WEGUI(convert(items));
	}

	public static WEGUI getBanksGUI() {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Banks")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			ItemStack item;
			ItemMeta meta;
			for (Bank bank : banks) {
				item = new ItemStack(BlockLib.BANK);
				meta = item.getItemMeta();
				meta.setDisplayName(bank.name);
				item.setItemMeta(meta);

				items.add(new GUIItem(slot, item) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});

				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI("Banks");
		}

		return new WEGUI(convert(items), "Banks");
	}

	public static WEGUI getBanksGUI(WEGUI parent) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Banks")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			ItemStack item;
			ItemMeta meta;
			for (Bank bank : banks) {
				item = new ItemStack(BlockLib.BANK);
				meta = item.getItemMeta();
				meta.setDisplayName(bank.name);
				item.setItemMeta(meta);

				items.add(new GUIItem(slot, item) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});

				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Banks");
		}

		return new WEGUI(parent, convert(items), "Banks");
	}

	public static GUIItem[] convert(List<GUIItem> list) {
		GUIItem[] out = new GUIItem[list.size()];
		for (int i = 0; i < list.size(); i++) {
			out[i] = list.get(i);
		}
		return out;
	}

	public static WEGUI getCompaniesGUI(WEGUI parent) {
		WEGUI out = new WEGUI(parent, new GUIItem[] {}, "Companies");
		;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Companies")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Company> companies = WEDB.getAllCompanies();

			for (Company company : companies) {

				if (company.companyType.equals("corporation")) {
					items.add(new GUIItem(slot, mkItem(BlockLib.COMPANY_CORPORATION, company.companyName,
							new String[] { company.companyType })) {
						@Override
						public void event(InventoryClickEvent event) {
							getCompanyGUI(out, company).openInventory((Player) event.getWhoClicked());
						}
					});
				} else if (company.companyType.equalsIgnoreCase("private")) {
					items.add(new GUIItem(slot, mkItem(BlockLib.COMPANY_PRIVATE, company.companyName,
							new String[] { company.companyType })) {
						@Override
						public void event(InventoryClickEvent event) {
							getCompanyGUI(out, company).openInventory((Player) event.getWhoClicked());
						}
					});
				} else {
					items.add(new GUIItem(slot, mkItem(Material.BARRIER, company.companyName,
							new String[] { "§4INVALID COMPANY TYPE \"" + company.companyType + "\"!" })) {
						@Override
						public void event(InventoryClickEvent event) {
							getCompanyGUI(parent, company).openInventory((Player) event.getWhoClicked());
						}
					});
				}

				slot++;
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Companies");
		}

		out.setItems(

				convert(items));

		return out;
	}

	public static WEGUI getCompanyGUI(WEGUI parent, Company company) {
		List<GUIItem> items = new ArrayList<GUIItem>();

		WEGUI out = new WEGUI(parent, new GUIItem[] {}, company.companyName);

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, company.companyName)) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 0, mkItem(Material.ORANGE_WOOL, "Products")) {
			@Override
			public void event(InventoryClickEvent event) {
				getProductFromCompanyGUI(out, company).openInventory((Player) event.getWhoClicked());
			}
		});
		items.add(new GUIItem(1, 1, mkItem(Material.YELLOW_WOOL, "Sales")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 2, mkItem(Material.LIME_WOOL, "Employees")) {
			@Override
			public void event(InventoryClickEvent event) {
				getEmployeesFromCompanyGUI(parent, company).openInventory((Player) event.getWhoClicked());
			}
		});

		out.setItems(convert(items));

		return out;
	}

	public static WEGUI getBankAccountsGUI(WEGUI parent, Player player) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		WEGUI out = new WEGUI(parent, new GUIItem[] {}, "Bank Accounts");

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Bank Accounts")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<BankAccount> bank_accounts = WEDB.getAllBankAccounts(player);
			for (BankAccount bank_account : bank_accounts) {
				items.add(new GUIItem(slot,
						mkItem(BlockLib.BANK_ACCOUNT, bank_account.getName(),
								new String[] { "§f" + WEDB.getBank(bank_account.getBankID()).name,
										String.valueOf(bank_account.getBalance()) })) {
					@Override
					public void event(InventoryClickEvent event) {
						event.getWhoClicked().sendMessage("bank account GUI!");
					}
				});
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Bank Accounts");
		}

		out.setItems(convert(items));

		return out;
	}

	public static WEGUI chooseBankGUI(WEGUI parent, Player player, BankChooserEvent chooserEvent) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, "Register Bank Account")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Bank> banks = WEDB.getAllBanks();
			for (Bank bank : banks) {
				items.add(new GUIItem(slot, mkItem(BlockLib.BANK, bank.name)) {
					@Override
					public void event(InventoryClickEvent event) {
						player.closeInventory();
						chooserEvent.event(event, bank);
					}
				});
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Bank Accounts");
		}

		return new WEGUI(parent, convert(items), "Register Bank Account");
	}

	public static WEGUI getCreateBankAccountGUI(WEGUI parent, Player player) {
		return chooseBankGUI(parent, player, new BankChooserEvent() {
			@Override
			public void event(InventoryClickEvent event, Bank bank) {
				new CreateBankAccountChatDialog(player, bank);
			}
		});
	}

	public static WEGUI getTransferMoneyGUI(WEGUI parent, Player player) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		WEGUI out = new WEGUI(parent, new GUIItem[] {}, "Transfer Money");

		try {
			List<WorldEconomyProfile> users = WEDB.getAllUserProfiles();
			for (WorldEconomyProfile user : users) {
				items.add(new GUIItem(slot, mkItem(BlockLib.PLAYER, user.username, new String[] { "player" })) {
					@Override
					public void event(InventoryClickEvent event) {
						// this user.bankingID is NOT the GUI viewer's profile!
						chooseBankAccountGUI(out, "Choose the recipient's bank account", user.bankingID,
								new BankAccountChooserEvent() {
									@Override
									public void event(InventoryClickEvent event2, BankAccount dst) {
										try {
											chooseBankAccountGUI(parent, "Choose your bank account",
													WEDB.getUserProfile(player).bankingID,
													new BankAccountChooserEvent() {
														@Override
														public void event(InventoryClickEvent event, BankAccount src) {
															new TransferMoneyChatDialog(player, src, dst);
														}
													}).openInventory(player);
										} catch (SQLException e) {
											e.printStackTrace();
											player.sendMessage(
													WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
										}
									}
								}).openInventory(player);
					}
				});
				slot++;
			}
			List<AIProfile> AIs = WEDB.getAllAIs();
			for (AIProfile AI : AIs) {
				items.add(new GUIItem(slot, mkItem(BlockLib.AI, AI.username, new String[] { "AI" })) {

					@Override
					public void event(InventoryClickEvent event) {
						chooseBankAccountGUI(out, "Choose the recipient's bank account", AI.bankingID,
								new BankAccountChooserEvent() {
									@Override
									public void event(InventoryClickEvent event2, BankAccount dst) {
										try {
											chooseBankAccountGUI(parent, "Choose your bank account",
													WEDB.getUserProfile(player).bankingID,
													new BankAccountChooserEvent() {
														@Override
														public void event(InventoryClickEvent event, BankAccount src) {
															new TransferMoneyChatDialog(player, src, dst);
														}
													}).openInventory(player);
										} catch (SQLException e) {
											e.printStackTrace();
											player.sendMessage(
													WorldEconomyPlugin.PREFIX + "§4Your user profile was not found!");
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
					items.add(new GUIItem(slot, mkItem(BlockLib.COMPANY_CORPORATION, company.companyName,
							new String[] { "company - " + company.companyType })) {
						@Override
						public void event(InventoryClickEvent event) {
							chooseBankAccountGUI(out, "Choose the recipient's bank account", company.bankingID,
									new BankAccountChooserEvent() {
										@Override
										public void event(InventoryClickEvent event2, BankAccount dst) {
											try {
												chooseBankAccountGUI(parent, "Choose your bank account",
														WEDB.getUserProfile(player).bankingID,
														new BankAccountChooserEvent() {
															@Override
															public void event(InventoryClickEvent event,
																	BankAccount src) {
																new TransferMoneyChatDialog(player, src, dst);
															}
														}).openInventory(player);
											} catch (SQLException e) {
												e.printStackTrace();
												player.sendMessage(WorldEconomyPlugin.PREFIX
														+ "§4Your user profile was not found!");
											}
										}
									}).openInventory(player);
						}
					});
				} else if (company.companyType.equals("private")) {
					items.add(new GUIItem(slot, mkItem(BlockLib.COMPANY_PRIVATE, company.companyName,
							new String[] { "company - " + company.companyType })) {
						@Override
						public void event(InventoryClickEvent event) {
							chooseBankAccountGUI(out, "Choose the recipient's bank account", company.bankingID,
									new BankAccountChooserEvent() {
										@Override
										public void event(InventoryClickEvent event2, BankAccount dst) {
											try {
												chooseBankAccountGUI(parent, "Choose your bank account",
														WEDB.getUserProfile(player).bankingID,
														new BankAccountChooserEvent() {
															@Override
															public void event(InventoryClickEvent event,
																	BankAccount src) {
																new TransferMoneyChatDialog(player, src, dst);
															}
														}).openInventory(player);
											} catch (SQLException e) {
												e.printStackTrace();
												player.sendMessage(WorldEconomyPlugin.PREFIX
														+ "§4Your user profile was not found!");
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

		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Transfer Money");
		}

		out.setItems(

				convert(items));

		return out;
	}

	public static WEGUI chooseBankAccountGUI(WEGUI parent, String title, long bankingID,
			BankAccountChooserEvent chooserEvent) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<BankAccount> accounts = WEDB.getBankAccounts(bankingID);
			for (BankAccount account : accounts) {
				items.add(new GUIItem(slot, mkItem(BlockLib.BANK_ACCOUNT, account.getName())) {
					@Override
					public void event(InventoryClickEvent event) {
						event.getWhoClicked().closeInventory();
						chooserEvent.event(event, account);
					}
				});
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, title);
		}

		return new WEGUI(parent, convert(items), title);
	}

	public static WEGUI chooseBankAccountGUI(WEGUI parent, long bankingID, BankAccountChooserEvent chooserEvent) {
		return chooseBankAccountGUI(parent, "Choose a bank account", bankingID, chooserEvent);
	}

	public static WEGUI getProductFromCompanyGUI(WEGUI parent, Company company) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, company.companyName + "'s Products")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Product> products = WEDB.getProductsFromCompany(company);
			for (Product product : products) {
				items.add(new GUIItem(slot,
						mkItem(Material.getMaterial(product.itemID), product.itemAmount, product.name)) {
					@Override
					public void event(InventoryClickEvent event) {
					}
				});
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, company.companyName + "'s Products");
		}
		return new WEGUI(parent, convert(items), company.companyName + "'s Products");
	}

	public static WEGUI getEmployeesFromCompanyGUI(WEGUI parent, Company company) {
		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.OAK_SIGN, company.companyName + "'s Employees")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Employee> employees = WEDB.getEmployeesFromCompany(company);
			for (Employee employee : employees) {
				if (employee instanceof EmployeePlayer) {
					items.add(new GUIItem(slot,
							mkItem(BlockLib.PLAYER,
									Bukkit.getOfflinePlayer(((EmployeePlayer) employee).playerUUID).getName(),
									new String[] { "player" })) {
						@Override
						public void event(InventoryClickEvent event) {
						}
					});
				} else if (employee instanceof EmployeeAI) {
					AIProfile ai = WEDB.getAI(((EmployeeAI) employee).aiID);
					items.add(new GUIItem(slot, mkItem(BlockLib.AI, ai.username, new String[] { "AI" })) {
						@Override
						public void event(InventoryClickEvent event) {
						}
					});
				}
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, company.companyName + "'s Employees");
		}

		return new WEGUI(parent, convert(items), company.companyName + "'s Employees");
	}
}
