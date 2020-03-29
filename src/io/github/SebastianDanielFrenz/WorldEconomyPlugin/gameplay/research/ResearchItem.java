package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.counter.LeafStickCounter;

public enum ResearchItem implements ResearchableObject {

	// time: early stone age
	// basics
	STICK(new ItemStick(), new ResearchItem[] {}, new ResearchCondition[] {}),
	BERRIES(new ItemBerries(), new ResearchItem[] {}, new ResearchCondition[] {}),

	// research needed
	OAK_SLAB(new ItemOakPlanksSlab(), new ResearchItem[] { STICK },
			new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 10) }),
	OAK_PLANKS(new ItemOakPlanks(), new ResearchItem[] { STICK },
			new ResearchCondition[] { new ExperienceResearchCondition(new LeafStickCounter(), 50) }),

	// mid stone age
	BASIC_SIEVE_STAGE1(new ItemBasicSieveStage1(), new ResearchItem[] {},
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