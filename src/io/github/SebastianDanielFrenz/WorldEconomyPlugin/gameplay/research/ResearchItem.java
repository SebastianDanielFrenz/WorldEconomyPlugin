package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.counter.LeafStickCounter;

public enum ResearchItem implements ResearchableObject {

	// time: early stone age
	// basics
	STICK(CustomItemRegistry.STICK, new ResearchItem[] {}, new ResearchCondition[] {}),
	BERRIES(CustomItemRegistry.BERRIES, new ResearchItem[] {}, new ResearchCondition[] {}),

	// research needed
	OAK_SLAB(CustomItemRegistry.OAK_SLAB, new ResearchItem[] { STICK },
			new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 10) }),
	OAK_PLANKS(CustomItemRegistry.OAK_PLANKS, new ResearchItem[] { STICK },
			new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 50) }),

	// mid stone age
	BASIC_SIEVE_STAGE1(CustomItemRegistry.BASIC_SIEVE_STAGE1, new ResearchItem[] {},
			new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 10) });

	private ResearchItem(ResearchableObject researchableObject, ResearchItem[] parents,
			ResearchCondition[] conditions) {
		this.researchableObject = researchableObject;
		this.parents = parents;
		this.conditions = conditions;
	}

	private ResearchableObject researchableObject;
	private ResearchItem[] parents;
	private ResearchCondition[] conditions;

	public Object getResearchableObject() {
		return researchableObject;
	}

	public ResearchItem[] getParents() {
		return parents;
	}

	public ResearchCondition[] getConditions() {
		return conditions;
	}

}