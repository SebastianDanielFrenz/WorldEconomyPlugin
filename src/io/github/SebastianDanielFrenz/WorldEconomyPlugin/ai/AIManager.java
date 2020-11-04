package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDBCache;

public class AIManager {

	public static List<AIProfile> findAI(AISearchCondition aiSearchCondition) {
		List<AIProfile> out = new ArrayList<AIProfile>();
		for (AIProfile profile : WEDBCache.ai_profiles) {
			if (aiSearchCondition.meetsConditions(profile)) {
				out.add(profile);
			}
		}
		return out;
	}

	public static List<AIProfile> findAIwithPropertyMin(AIProperty property, double min_value) {
		return findAI(new AISearchCondition() {
			@Override
			public boolean meetsConditions(AIProfile profile) {
				return profile.properties.getValue(property) >= min_value;
			}
		});
	}

	public static List<AIProfile> findAIwithPropertyMax(AIProperty property, double max_value) {
		return findAI(new AISearchCondition() {
			@Override
			public boolean meetsConditions(AIProfile profile) {
				return profile.properties.getValue(property) <= max_value;
			}
		});
	}

	public static void sortAIbyProperty(List<AIProfile> ai_profiles, AIProperty property, SortingMode mode) {
		if (mode == SortingMode.ASC) {
			ai_profiles.sort(new Comparator<AIProfile>() {
				double v1;
				double v2;

				@Override
				public int compare(AIProfile o1, AIProfile o2) {
					v1 = o1.properties.getValue(property);
					v2 = o2.properties.getValue(property);
					if (v1 < v2) {
						return -1;
					} else if (v1 == v2) {
						return 0;
					}
					return 1;
				}
			});
		} else {
			ai_profiles.sort(new Comparator<AIProfile>() {
				double v1;
				double v2;

				@Override
				public int compare(AIProfile o1, AIProfile o2) {
					v1 = o1.properties.getValue(property);
					v2 = o2.properties.getValue(property);
					if (v1 < v2) {
						return -1;
					} else if (v1 == v2) {
						return 0;
					}
					return 1;
				}
			});
		}
	}

	public static List<AIProfile> sortAIbyProperty(AIProperty property, SortingMode mode) {
		List<AIProfile> out = new ArrayList<AIProfile>(WEDBCache.ai_profiles.size()); // copy
																						// of
																						// original
		for (AIProfile profile : WEDBCache.ai_profiles) {
			out.add(profile);
		}
		sortAIbyProperty(out, property, mode);
		return out;
	}

}
