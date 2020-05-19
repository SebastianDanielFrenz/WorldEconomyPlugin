package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class CompaniesGUI extends WEGUI {

	public CompaniesGUI(WEGUI parent) {
		super(parent, new GUIItem[] {}, "Companies");

		CompaniesGUI _this = this;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, "Companies")) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Company> companies = WEDB.getAllCompanies();

			for (Company company : companies) {

				if (company.companyType.equals("corporation")) {
					items.add(new GUIItem(slot, BlockLib.company(company)) {
						@Override
						public void event(InventoryClickEvent event) {
							new CompanyGUI(_this, company).openInventory((Player) event.getWhoClicked());
						}
					});
				} else if (company.companyType.equalsIgnoreCase("private")) {
					items.add(new GUIItem(slot, BlockLib.company(company)) {
						@Override
						public void event(InventoryClickEvent event) {
							new CompanyGUI(_this, company).openInventory((Player) event.getWhoClicked());
						}
					});
				} else {
					items.add(new GUIItem(slot, mkItem(Material.BARRIER, company.companyName,
							new String[] { "§4INVALID COMPANY TYPE \"" + company.companyType + "\"!" })) {
						@Override
						public void event(InventoryClickEvent event) {
							new CompanyGUI(_this, company).openInventory((Player) event.getWhoClicked());
						}
					});
				}

				slot++;

				setItems(convert(items));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
