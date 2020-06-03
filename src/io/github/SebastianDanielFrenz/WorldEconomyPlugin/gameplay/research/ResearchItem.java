package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public abstract class ResearchItem implements ResearchableObject {

	public ResearchItem(String ID, ResearchableObject researchableObject, ResearchItem[] parents, ResearchCondition[] conditions, Age age,
			String name, Material repr, int repr_dmg) {
		this.ID = ID;
		this.researchableObject = researchableObject;
		this.parents = parents;
		this.conditions = conditions;
		this.age = age;

		this.name = name;
		this.repr = repr;
		this.repr_dmg = repr_dmg;
	}

	public ResearchItem(String ID, ResearchableObject researchableObject, ResearchItem[] parents, ResearchCondition condition, Age age, String name,
			Material repr, int repr_dmg) {
		this.ID = ID;
		this.researchableObject = researchableObject;
		this.parents = parents;
		this.conditions = new ResearchCondition[] { condition };
		this.age = age;

		this.name = name;
		this.repr = repr;
		this.repr_dmg = repr_dmg;
	}

	private ResearchableObject researchableObject;
	private ResearchItem[] parents;
	private ResearchCondition[] conditions;
	private Age age;
	private String ID;
	private String name;
	private Material repr;
	private int repr_dmg;

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

	public String getName() {
		return name;
	}

	public Material getRepr() {
		return repr;
	}

	public int getReprDmg() {
		return repr_dmg;
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