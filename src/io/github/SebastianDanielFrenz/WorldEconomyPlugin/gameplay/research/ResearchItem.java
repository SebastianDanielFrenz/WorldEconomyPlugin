package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public abstract class ResearchItem implements ResearchableObject {

	public ResearchItem(String ID, ResearchableObject researchableObject, ResearchItem[] parents, ResearchCondition[] conditions, Age age) {
		this.ID = ID;
		this.researchableObject = researchableObject;
		this.parents = parents;
		this.conditions = conditions;
		this.age = age;
	}

	public ResearchItem(String ID, ResearchableObject researchableObject, ResearchItem[] parents, ResearchCondition condition, Age age) {
		this.ID = ID;
		this.researchableObject = researchableObject;
		this.parents = parents;
		this.conditions = new ResearchCondition[] { condition };
		this.age = age;
	}

	private ResearchableObject researchableObject;
	private ResearchItem[] parents;
	private ResearchCondition[] conditions;
	private Age age;
	private String ID;

	public ResearchableObject getResearchableObject() {
		return researchableObject;
	}

	public ResearchItem[] getParents() {
		return parents;
	}

	public ResearchCondition[] getConditions() {
		return conditions;
	}

	public Age getAge() {
		return age;
	}

	public String getID() {
		return ID;
	}

	public boolean areConditionsMet(long entityID, String entityType) {
		for (ResearchCondition condition : conditions) {
			if (!condition.isMet(entityID, entityType)) {
				return false;
			}
		}
		return true;
	}

}