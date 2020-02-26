package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResourceChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ChooseResourceGUI extends WEGUI {

	public ChooseResourceGUI(ResourceChooserEvent chooserEvent) {
		super(new GUIItem[] {}, "Resource Market");

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<Material> resources = WEDB.getAllResources();
			for (Material resource : resources) {
				items.add(new GUIItem(slot, new ItemStack(resource)) {
					@Override
					public void event(InventoryClickEvent event) {
						chooserEvent.event(event, resource);
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
