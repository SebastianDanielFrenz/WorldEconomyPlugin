package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ResearchGUI extends WEGUI {

	public ResearchGUI(WEGUI parent, Player player) {
		super(parent, new GUIItem[] {}, Lang.get(player, Lang.GUI_TITLE_RESEARCH), player);

		WEGUI _this = this;

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;
		try {
			UserProfile profile = WEDB.getUserProfile(player);
			for (Age age : Age.values()) {
				if (age.index <= profile.getAge().index) {
					items.add(new GUIItem(slot, mkItem(age.representation, 1, age.repr_dmg, age.name())) {

						@Override
						public void event(InventoryClickEvent event) {
							new ResearchAgeGUI(_this, age, profile, player).openInventory();
						}
					});
					slot++;
				}
			}

			setItems(convert(items));
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
