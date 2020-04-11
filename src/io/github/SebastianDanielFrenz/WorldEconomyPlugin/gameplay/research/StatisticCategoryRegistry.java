package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.util.ArrayList;
import java.util.List;

/**
 * This class does not need an init() function because the constructor for
 * StatisticCategory already runs the register function.
 * 
 * @author crash
 *
 */
public class StatisticCategoryRegistry {

	private static List<StatisticCategory> categories = new ArrayList<StatisticCategory>();

	public static void register(StatisticCategory category) {
		categories.add(category);
	}

	public static final StatisticCategory MINED = new StatisticCategory("mined");
	public static final StatisticCategory PLACED = new StatisticCategory("placed");
	public static final StatisticCategory CRAFTED = new StatisticCategory("crafted");

	public static List<StatisticCategory> getContents() {
		return categories;
	}

}
