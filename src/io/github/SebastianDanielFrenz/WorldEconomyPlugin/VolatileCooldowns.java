package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class VolatileCooldowns {

	public static Map<Player, VolatileCooldown> villagerInteractCooldowns = new HashMap<Player, VolatileCooldown>();

	public static boolean useVillagerInteractCooldown(Player player) {
		VolatileCooldown c = villagerInteractCooldowns.get(player);
		if (c == null) {
			villagerInteractCooldowns.put(player, new VolatileCooldown(2000));
			return true;
		} else {
			return c.use();
		}
	}

}
