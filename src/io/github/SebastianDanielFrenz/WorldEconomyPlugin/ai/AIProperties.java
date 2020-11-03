package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import java.util.Random;

public class AIProperties {

	private Random random = new Random();

	private double[] property_values;

	public void create() {
		property_values = new double[AIPropertyRegistry.getProperties().size()];
		for (AIProperty property : AIPropertyRegistry.getProperties()) {
			property_values[property.getIndex()] = random.nextGaussian();
		}
	}

	public void load() {
		int i;
		// pls i
	}

	public void setValue(AIProperty property, double value) {
		property_values[property.getIndex()] = value;
	}

	public double getValue(AIProperty property) {
		return property_values[property.getIndex()];
	}

}