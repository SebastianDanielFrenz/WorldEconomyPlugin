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

		List<GUIItem> items = new ArrayList<GUIItem>();

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
				throw new NotImplementedException();
			}
		});
		items.add(new GUIItem(1, 2, mkItem(Material.LIME_WOOL, "Employees")) {
			@Override
			public void event(InventoryClickEvent event) {
				getEmployeesFromCompanyGUI(out, company).openInventory((Player) event.getWhoClicked());
			}
		});

		setItems(convert(items));
	}

}
