package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResearchAgeGUI extends WEGUI {

	public ResearchAgeGUI(WEGUI parent, Age age, UserProfile profile) {
		super(parent, new GUIItem[] {}, "Research - " + age.name());

		try {
			List<ResearchItem> researchItems = profile.getResearchedItems();
			ResearchItem item;
			List<GUIItem> items = new ArrayList<GUIItem>();
			for (int i = 0; i < researchItems.size(); i++) {
				item = researchItems.get(i);
				if (item.getAge().index == age.index) {
					items.add(new GUIItem(i + 9, mkItem(item.getRepr(), 1, item.getReprDmg(), item.getName())) {

						@Override
						public void event(InventoryClickEvent event) {
							// no click event yet...
						}
					});
				}
			}

			setItems(convert(items));
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
