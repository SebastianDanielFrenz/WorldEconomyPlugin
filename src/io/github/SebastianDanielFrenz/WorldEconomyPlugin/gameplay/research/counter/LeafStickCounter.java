package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.counter;

import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeePlayer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ExperienceCounter;

public class LeafStickCounter extends ExperienceCounter {

	@Override
	public double getXP(long employeeID, String entityType) {
		if (entityType.equals("player")) {
			// needs to turn into a DB statistics table arrangement.

			try {
				return Bukkit.getOfflinePlayer(((EmployeePlayer) WEDB.getEmployee(employeeID)).playerUUID).getPlayer()
						.getStatistic(Statistic.MINE_BLOCK, Material.OAK_LEAVES);
			} catch (IllegalArgumentException | SQLException e) {
				e.printStackTrace();
				try {
					Bukkit.getOfflinePlayer(((EmployeePlayer) WEDB.getEmployee(employeeID)).playerUUID).getPlayer()
							.sendMessage(WorldEconomyPlugin.PREFIX
									+ "§4An internal error occured while getting LeafStickXP!");
				} catch (SQLException e1) {
					e1.printStackTrace();
					WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
							"Failed to tell the player who's xp was not found that the error occured!");
				}
				return 0;
			}
		} else if (entityType.equals("ai")) {
			throw new RuntimeException("not implemented yet!");
		} else {
			throw new RuntimeException("Invalid entity type \"" + entityType + "\"!");
		}
	}

}
