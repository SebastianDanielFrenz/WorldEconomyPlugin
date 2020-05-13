package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;

public class ProductFromCompanyGUI extends WEGUI {

	public ProductFromCompanyGUI(WEGUI parent, Company company) {
		super(parent, new GUIItem[] {}, company.companyName + "'s products");

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, company.companyName + "'s Products")) {
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

			setItems(convert(items));
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
