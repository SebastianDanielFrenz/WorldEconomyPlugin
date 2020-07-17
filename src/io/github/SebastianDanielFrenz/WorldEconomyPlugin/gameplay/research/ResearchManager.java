package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.PlayingEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

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
		List<ResearchItem> explored = WEDB.getRawResearchItems(entity);
		List<ResearchItem> changed = new ArrayList<ResearchItem>();

		for (ResearchItem item : ResearchItemRegistry.getContents()) {
			if (explored.indexOf(item) == -1) {
				if (item.areConditionsMet(entity.getResearchSpecifiyEntityID(), entity.getResearchEntityType())) {
					WEDB.addResearchItem(entity, item);
					changed.add(item);
				}
			}
		}
		return changed;
	}

	public static Age updateAge(ResearchEntity entity) throws SQLException {
		Age current_age = ((PlayingEntity) entity).getAge();
		Age next_age = Age.getIncrementalAge(current_age);

		List<ResearchItem> explored = WEDB.getRawResearchItems(entity);
		for (ResearchItem item : next_age.getNeededItems()) {
			if (!explored.contains(item)) {
				return null;
			}
		}
		WEDB.setEntityAge((PlayingEntity) entity, next_age);
		return next_age;
	}

}
