package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.undefined.ImpossibleResearchItem;

public class Age {

	public static final Age EARLY_STONE_AGE = new Age("early_stone_age", ChatColor.BLACK, Material.STONE, 0, 20, 40);
	public static final Age MID_STONE_AGE = new Age("mid_stone_age", EARLY_STONE_AGE, ChatColor.DARK_GRAY,
			Material.STONE, 1, 20, 50);
	public static final Age NEW_STONE_AGE = new Age("new_stone_age", MID_STONE_AGE, ChatColor.GRAY,
			Material.COBBLESTONE, 0, 30, 60);
	public static final Age COPPER_AGE = new Age("copper_age", NEW_STONE_AGE, ChatColor.DARK_PURPLE, Material.BRICK, 0,
			50, 100);
	public static final Age IRON_AGE = new Age("iron_age", COPPER_AGE, ChatColor.DARK_BLUE, Material.IRON_INGOT, 0, 60,
			150);
	public static final Age EARLY_MIDDLE_AGES = new Age("early_middle_ages", IRON_AGE, ChatColor.BLUE, Material.LOG, 0,
			100, 200);
	public static final Age MIDDLE_AGES = new Age("middle_ages", EARLY_MIDDLE_AGES, ChatColor.AQUA, Material.WOOD, 0,
			120, 200);
	public static final Age LATE_MIDDLE_AGES = new Age("late_middle_ages", MIDDLE_AGES, ChatColor.GREEN, Material.BOOK,
			0, 150, 300);

	public static final Age UNDEFINED = new Age("undefined", Integer.MAX_VALUE, ChatColor.MAGIC, Material.BARRIER, 0, 0,
			Double.MAX_VALUE);

	private String ID;
	private final Plugin plugin;

	public final int index;
	public final ChatColor color;
	public final Material representation;
	public final double min_happyness;
	public final double max_happyness;
	/**
	 * item damage of the representing item.
	 */
	public final int repr_dmg;

	private Age(String ID, Age prev, ChatColor color, Material representation, int repr_dmg, double min_happyness,
			double max_happyness) {
		this.ID = ID;
		this.plugin = WorldEconomyPlugin.plugin;

		index = prev.index + 1;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	private Age(String ID, ChatColor color, Material representation, int repr_dmg, double min_happyness,
			double max_happyness) {
		this.ID = ID;
		this.plugin = WorldEconomyPlugin.plugin;

		index = 0;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	private Age(String ID, int stage, ChatColor color, Material representation, int repr_dmg, double min_happyness,
			double max_happyness) {
		this.ID = ID;
		this.plugin = WorldEconomyPlugin.plugin;

		this.index = stage;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	public Age(Plugin plugin, String ID, Age prev, ChatColor color, Material representation, int repr_dmg,
			double min_happyness, double max_happyness) {
		this.ID = ID;
		this.plugin = plugin;

		index = prev.index + 1;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	public Age(Plugin plugin, String ID, ChatColor color, Material representation, int repr_dmg, double min_happyness,
			double max_happyness) {
		this.ID = ID;
		this.plugin = plugin;

		index = 0;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	public Age(Plugin plugin, String ID, int stage, ChatColor color, Material representation, int repr_dmg,
			double min_happyness, double max_happyness) {
		this.ID = ID;
		this.plugin = plugin;

		this.index = stage;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
		this.min_happyness = min_happyness;
		this.max_happyness = max_happyness;
	}

	private ResearchItem[] needed_items;

	private static List<Age> ages = new ArrayList<Age>();

	public static void registerAge(Age age, ResearchItem[] items) {
		age.needed_items = items;
		ages.add(age);
		System.out.println("registered age " + age.ID + " from " + age.plugin.getName());
	}

	public static void init() {
		registerAge(EARLY_STONE_AGE, new ResearchItem[] {});
		registerAge(MID_STONE_AGE, new ResearchItem[] {});
		registerAge(NEW_STONE_AGE, new ResearchItem[] {});
		registerAge(COPPER_AGE, new ResearchItem[] {});
		registerAge(IRON_AGE, new ResearchItem[] {});
		registerAge(EARLY_MIDDLE_AGES, new ResearchItem[] {});
		registerAge(MIDDLE_AGES, new ResearchItem[] {});
		registerAge(LATE_MIDDLE_AGES, new ResearchItem[] {});

		registerAge(UNDEFINED, new ResearchItem[] { new ImpossibleResearchItem() });
	}

	public static void check() {
		boolean broken = false;

		Map<Integer, LinkedList<Age>> index_map = new TreeMap<Integer, LinkedList<Age>>();
		for (Age age : ages) {
			System.out.println("checking age " + age.ID + " for index duplicates...");
			if (index_map.get(age.index) != null) {
				System.out.println("found matching age with ID " + index_map.get(age.index).get(0).ID);
				broken = true;
				index_map.get(age.index).add(age);
			} else {
				LinkedList<Age> list = new LinkedList<Age>();
				list.add(age);
				index_map.put(age.index, list);
			}
		}

		if (broken) {
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
					"Found duplicate age indexes during age registry check!");
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE, "List of registered ages:");
			String out;
			for (int key : index_map.keySet()) {
				out = key + ": (";
				LinkedList<Age> list = index_map.get(key);
				for (int i = 0; i < list.size() - 1; i++) {
					out += list.get(i).ID + "[" + list.get(i).plugin.getName() + "], ";
				}
				if (list.size() != 0) {
					out += list.get(list.size() - 1).ID + "[" + list.get(list.size() - 1).plugin.getName() + "]";
					// this code should always be run, since the list's size
					// should always be 1 or greater. For debugging purposes, it
					// remains as a condition.
				}
				out += ")";
				WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE, out);
			}
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
					"For security reasons, the server will now shut down! Please remove the problematic extion or manually resolve extionsion conflicts, ask the WorldEconomy developer team for support or just file an issue at https://www.github.com/SebastianDanielFrenz/WorldEconomyPlugin/issues/");
			Bukkit.shutdown();
			return;
		}

		// broken should always be false here

		Map<String, LinkedList<Age>> ID_map = new TreeMap<String, LinkedList<Age>>();
		for (Age age : ages) {
			if (ID_map.get(age.ID) != null) {
				broken = true;
				ID_map.get(age.ID).add(age);
			} else {
				LinkedList<Age> list = new LinkedList<Age>();
				list.add(age);
				ID_map.put(age.ID, list);
			}
		}
		if (broken) {
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
					"Found duplicate age IDs during age registry check!");
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE, "List of registered ages:");
			String out;
			for (String key : ID_map.keySet()) {
				out = key + ": (";
				LinkedList<Age> list = ID_map.get(key);
				for (int i = 0; i < list.size() - 1; i++) {
					out += list.get(i).plugin + ", ";
				}
				if (list.size() != 0) {
					out += list.get(list.size() - 1).plugin;
					// this code should always be run, since the list's size
					// should always be 1 or greater. For debugging purposes, it
					// remains as a condition.
				}
				out += ")";
				WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE, out);
			}
			WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
					"For security reasons, the server will now shut down! Please remove the problematic extion or manually resolve extionsion conflicts, ask the WorldEconomy developer team for support or just file an issue at https://www.github.com/SebastianDanielFrenz/WorldEconomyPlugin/issues/");
			Bukkit.shutdown();
			return;
		}

		ages.sort(new Comparator<Age>() {

			@Override
			public int compare(Age o1, Age o2) {
				if (o1.index < o2.index) {
					return -1;
				} else if (o1.index == o2.index) {
					return 0;
				} else {
					return 1;
					// this implies that no two ages have the same
					// index, but even if it is executed before check(), it does
					// not matter as the order of two ages with the same index
					// does not matter.
				}
			}
		});
	}

	public ResearchItem[] getNeededItems() {
		return needed_items;
	}

	public String getID() {
		return ID;
	}

	public static Age getAge(String ID) {

		for (Age age : ages) {
			if (age.ID.equals(ID)) {
				return age;
			}
		}
		return null;
	}

	public static List<Age> getAllAges() {
		return ages;
	}

	/**
	 * This method only works if the age registry contents were properly sorted
	 * after the check() call and not modified after that.
	 * 
	 * @param current_age
	 * @return
	 */
	public static Age getIncrementalAge(Age current_age) {
		int i = ages.indexOf(current_age);
		if (i == ages.size() - 1) {
			return null;
		}
		return ages.get(i + 1);
	}

	public static String[] dump() {
		String[] out = new String[ages.size() + 1];
		out[0] = "Age registry:";
		for (int i = 0; i < ages.size(); i++) {
			out[i + 1] = ages.get(i).index + " - " + ages.get(i).ID + "[" + ages.get(i).plugin + "]";
		}
		return out;
	}

}
