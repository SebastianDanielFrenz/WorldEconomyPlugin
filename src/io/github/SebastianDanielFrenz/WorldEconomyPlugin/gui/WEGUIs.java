package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;

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
				item = new ItemStack(Material.GREEN_WOOL);
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
				item = new ItemStack(Material.GREEN_WOOL);
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

				items.add(new GUIItem(slot,
						mkItem(Material.YELLOW_WOOL, company.companyName, new String[] { company.companyType })) {
					@Override
					public void event(InventoryClickEvent event) {
						getCompanyGUI(out, company).openInventory((Player) event.getWhoClicked());
					}
				});

				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getErrorGUI(parent, "Companies");
		}

		out.setItems(convert(items));

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
		items.add(new GUIItem(1, 0, mkItem(Material.RED_WOOL, "Products")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 1, mkItem(Material.RED_WOOL, "Sales")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 2, mkItem(Material.RED_WOOL, "Employees")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		out.setItems(convert(items));

		return out;
	}
}
