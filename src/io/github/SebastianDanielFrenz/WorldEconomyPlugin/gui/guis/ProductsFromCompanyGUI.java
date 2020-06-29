package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;

public class ProductsFromCompanyGUI extends WEGUI {

	public ProductsFromCompanyGUI(WEGUI parent, Company company, Player player) {
		super(parent, new GUIItem[] {}, Lang.GUI_TITLE_PRODUCTS_FROM_COMPANY(player, company), player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, Lang.GUI_TITLE_PRODUCTS_FROM_COMPANY(player, company))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Product> products = WEDB.getProductsFromCompany(company);
			for (Product product : products) {
				items.add(new GUIItem(slot, mkItem(Material.getMaterial(product.itemID), product.name)) {
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
