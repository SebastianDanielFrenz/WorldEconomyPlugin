package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.statistics;

public abstract class CustomStatistic {

	private double value;

	public void add(double amount) {
		value += amount;
	}

	public double get() {
		return value;
	}

}
