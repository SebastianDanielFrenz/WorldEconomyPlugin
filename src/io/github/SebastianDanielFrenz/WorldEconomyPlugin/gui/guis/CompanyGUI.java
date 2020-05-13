package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotImplementedException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class CompanyGUI extends WEGUI {

	public CompanyGUI(WEGUI parent, Company company) {
		super(parent, new GUIItem[] {}, company.companyName);

		CompanyGUI _this = this;

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, company.companyName)) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 0, mkItem(Material.WOOL, 1, 1, "Products")) {
			@Override
			public void event(InventoryClickEvent event) {
				new ProductFromCompanyGUI(_this, company).openInventory((Player) event.getWhoClicked());
			}
		});
		items.add(new GUIItem(1, 1, mkItem(Material.WOOL, 1, 4, "Sales")) {
			@Override
			public void event(InventoryClickEvent event) {
				throw new NotImplementedException();
			}
		});
		items.add(new GUIItem(1, 2, mkItem(Material.WOOL, 1, 5, "Employees")) {
			@Override
			public void event(InventoryClickEvent event) {
				new EmployeesFromCompanyGUI(_this, company).openInventory((Player) event.getWhoClicked());
			}
		});

		setItems(convert(items));
	}

}
