package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResourceChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ChooseItemGUI extends WEGUI {

	public ChooseItemGUI(ResourceChooserEvent chooserEvent) {
		super(new GUIItem[] {}, "Resource Market");

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		try {
			List<CustomItem> resources = WEDB.getAllResources();
			for (CustomItem resource : resources) {
				items.add(new GUIItem(slot, resource.toItemStack()) {
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
