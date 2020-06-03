package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResearchAgeGUI extends WEGUI {

	public ResearchAgeGUI(WEGUI parent, String title, UserProfile profile) {
		super(parent, new GUIItem[] {}, title);

		try {
			List<ResearchItem> researchItems = profile.getResearchedItems();
			ResearchItem item;
			items = new GUIItem[researchItems.size()];
			for (int i = 0; i < researchItems.size(); i++) {
				item = researchItems.get(i);
				items[i] = new GUIItem(i + 9, mkItem(item.getRepr(), 1, item.getReprDmg(), item.getName())) {

					@Override
					public void event(InventoryClickEvent event) {
						// no click event yet...
					}
				};
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
