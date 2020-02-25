package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResourcePurchaseGUI extends WEGUI {

	public ResourcePurchaseGUI() {
		super(new GUIItem[] {}, "Resource Market");

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<Material> resources = WEDB.getAllResources();
			for (Material resource : resources) {
				items.add(new GUIItem(slot, mkItem(resource, resource.name())) {
					@Override
					public void event(InventoryClickEvent event) {
						// TODO
					}
				});
				slot++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
