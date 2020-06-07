package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.Set;
import java.util.TreeSet;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions.EmployeeProfession;

public class PlayingEntity {

	public PlayingEntity(Set<EmployeeProfession> professions, double health, double maxHealth, double saturation,
			double happyness, boolean religious, double religious_satisfaction, double endurance, double max_endurance,
			boolean in_heaven, long heaven_time_end_millis, Age age) {
		this.professions = professions;
		this.health = health;
		this.maxHealth = maxHealth;
		this.saturation = saturation;
		this.happyness = happyness;
		this.religious = religious;
		this.religious_satisfaction = religious_satisfaction;
		this.endurance = endurance;
		this.max_endurance = max_endurance;
		this.in_heaven = in_heaven;
		this.heaven_end_time_millis = heaven_time_end_millis;
	}

	private Set<EmployeeProfession> professions;
	private double health;
	private double maxHealth;
	private double saturation;
	private double happyness;

	private boolean religious;
	/**
	 * Range: 0-100
	 */
	private double religious_satisfaction;

	private double endurance;
	private double max_endurance;

	private boolean in_heaven;
	/**
	 * This is real time based. If the server shuts down during your time in
	 * heaven, you shouldn't have to wait as long as before after the restart.
	 */
	private long heaven_end_time_millis;

	private Age age;

	public void addProfession(EmployeeProfession profession) {
		// WEDB add profession
		int i;
		professions.add(profession);
	}

	public double getMinHappyness() {
		double threshold = age.min_happyness;

		if (religious) {
			if (religious_satisfaction < 25) {
				threshold *= 1.2;
			} else if (religious_satisfaction >= 50) {
				threshold *= 0.5;
			}
		}

		return threshold;
	}

}