package io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions;

public class SkillInfluence {

	private Skill skill;
	private double amount;

	public SkillInfluence(Skill skill, double amount) {
		this.skill = skill;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
