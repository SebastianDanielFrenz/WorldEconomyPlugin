package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;

public class ResearchManager {

	public static List<ResearchEntity> getOnlineEntites() {
		// for testing reasons, this function only returns players. Companies
		// and AIs should follow afterwards!
		List<ResearchEntity> out = new ArrayList<ResearchEntity>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			try {
				out.add(WEDB.getUserProfile(player));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	public static List<ResearchItem> updateResearch(ResearchEntity entity) throws SQLException {
		List<ResearchItem> explored = WEDB.getResearchItems(entity);
		List<ResearchItem> changed = new ArrayList<ResearchItem>();

		for (ResearchItem item : ResearchItemRegistry.getContents()) {
			if (item.areConditionsMet(entity.getResearchSpecifiyEntityID(), entity.getResearchEntityType())) {
				if (!explored.contains(item)) {
					WEDB.addResearchItem(entity, item);
					changed.add(item);
				}
			}
		}
		return changed;
	}

}
