package io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions;

public enum EmployeeProfession {

	/*
	 * ==================================================
	 * 
	 * Physical labor.
	 * 
	 * ==================================================
	 */

	ARTISN(new SkillInfluence[] { new SkillInfluence(Skill.STRENGTH, 0.25) }),
	JUMBERJACK(new SkillInfluence[] { new SkillInfluence(Skill.WOOD, 1) }),
	BUILDER(new SkillInfluence[] { new SkillInfluence(Skill.BUILDING, 1) }),
	RESEARCHER(new SkillInfluence[] { new SkillInfluence(Skill.RESEARCH, 9) });

	private EmployeeProfession(SkillInfluence[] influences) {
		this.setInfluences(influences);
	}

	public SkillInfluence[] getInfluences() {
		return influences;
	}

	public void setInfluences(SkillInfluence[] influences) {
		this.influences = influences;
	}

	private SkillInfluence[] influences;

}
