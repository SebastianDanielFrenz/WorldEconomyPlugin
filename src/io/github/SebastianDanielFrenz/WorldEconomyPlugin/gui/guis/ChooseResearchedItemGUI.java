package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResearchItemChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class ChooseResearchedItemGUI extends WEGUI {

	public ChooseResearchedItemGUI(WEGUI parent, String title, Player player, ResearchItemChooserEvent chooserEvent) {
		super(parent, new GUIItem[] {}, title);

		int slot = 9;

		List<GUIItem> items = new ArrayList<GUIItem>();
		try {
			for (ResearchItem researchItem : WEDB.getUserProfile(player).getResearchedItems()) {
				if (chooserEvent.filter(researchItem)) {
					items.add(new GUIItem(slot, ((CustomItem) researchItem.getResearchableObject()).toItemStack()) {

						@Override
						public void event(InventoryClickEvent event) {
							chooserEvent.event(researchItem);
						}
					});
					slot++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
