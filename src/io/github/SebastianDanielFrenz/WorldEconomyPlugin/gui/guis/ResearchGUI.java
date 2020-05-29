package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResearchGUI extends WEGUI {

	public ResearchGUI(WEGUI parent, Player player) {
		super(parent, null, "Research");

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;
		try {
			WorldEconomyProfile profile = WEDB.getUserProfile(player);
			for (Age age : Age.values()) {
				if (age.index <= profile.age.index) {
					items.add(new GUIItem(slot, mkItem(age.representation, 1, age.repr_dmg, age.name())) {

						@Override
						public void event(InventoryClickEvent event) {
							// code
						}
					});
					slot++;
				}
			}

			for (ResearchItem item : WEDB.getResearchItems(profile)) {

			}

			setItems(convert(items));
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
