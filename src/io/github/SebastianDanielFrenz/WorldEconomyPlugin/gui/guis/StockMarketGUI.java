package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.Share;

public class StockMarketGUI extends WEGUI {

	public StockMarketGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, "Stock Market");

		int slot = 9;
		try {
			List<GUIItem> items = new ArrayList<GUIItem>();
			Company company;
			for (Share share : WEDB.getAllShares()) {
				company = WEDB.getCompany(share.shareCompanyID);
				items.add(new GUIItem(slot, mkItem(Material.PAPER, company.companyName)) {

					@Override
					public void event(InventoryClickEvent event) {

					}
				});
			}
		} catch (SQLException e) {
			setErrorGUI();
		}
	}

}
