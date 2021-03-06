package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.sql.SQLException;
import java.util.List;
import org.bukkit.Bukkit;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.PlayingEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchManager;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticalObject;

public class ResearchHandlerThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			// System.out.println("Checking for research changes...");
			List<ResearchEntity> entities = ResearchManager.getOnlineEntites();
			for (ResearchEntity entity : entities) {
				// System.out.println("Checking research changes for " +
				// entity.getResearchEntityType() + " with ID = "
				// + entity.getResearchSpecifiyEntityID());
				/*
				 * try { List<ResearchItem> prev =
				 * WEDB.getResearchItems(entity); System.out.println("prev: " +
				 * prev.toString()); } catch (SQLException e1) {
				 * e1.printStackTrace(); }
				 */

				try {
					List<ResearchItem> explored = ResearchManager.updateResearch(entity);
					if (entity instanceof UserProfile) {
						for (ResearchItem item : explored) {
							Bukkit.getPlayer(((UserProfile) entity).uuid)
									.sendMessage(WorldEconomyPlugin.PREFIX + "�aYou explored " + item.getID());

							if (item.getResearchableObject() instanceof CustomBlockType
									|| item.getResearchableObject() instanceof CustomItem) {
								// only items and blocks appear in statistics;
								// not recipes
								WEDB.addAllStatistics((StatisticalObject) item.getResearchableObject(),
										entity.getResearchSpecifiyEntityID(), entity.getResearchEntityType());
							}
						}
					}
					Age new_age = ResearchManager.updateAge(entity);
					if (new_age != null) {
						if (entity instanceof UserProfile) {
							Bukkit.getPlayer(((UserProfile) entity).uuid).sendMessage(
									WorldEconomyPlugin.PREFIX + "�aYou are now in: " + new_age.color + new_age.getID());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				WorldEconomyPlugin.plugin.getLogger().info("Shutting down research handler thread!");
				break;
			}
		}
	}

}
