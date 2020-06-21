package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;

public class CustomBlockRandomItemDrop extends CustomBlockDropComponent {

	public CustomBlockRandomItemDrop(CustomBlockDropComponent[] components, double[] weights) {
		this.components = components;
		this.weights = weights;
		total_weight = 0;
		for (double weight : weights) {
			total_weight += weight;
		}
	}

	public CustomBlockRandomItemDrop(CustomItemStack[] itemStacks, double[] weights) {
		components = new CustomBlockDropComponent[itemStacks.length];
		this.weights = weights;
		total_weight = 0;
		for (double weight : weights) {
			total_weight += weight;
		}

		for (int i = 0; i < itemStacks.length; i++) {
			components[i] = new CustomBlockDropDefaultComponent(itemStacks[i]);
		}
	}

	public CustomBlockRandomItemDrop(CustomItemStack[][] itemStacks, double[] weights) {
		components = new CustomBlockDropComponent[itemStacks.length];
		this.weights = weights;
		total_weight = 0;
		for (double weight : weights) {
			total_weight += weight;
		}

		for (int i = 0; i < itemStacks.length; i++) {
			components[i] = new CustomBlockDropDefaultComponent(itemStacks[i]);
		}
	}

	private CustomBlockDropComponent[] components;
	private double[] weights;
	private double total_weight;

	@Override
	public List<CustomItemStack> getDrop() {
		double random = WorldEconomyPlugin.randomizer.nextDouble() * total_weight;
		double passed = 0;
		for (int i = 0; i < components.length; i++) {
			if (passed + weights[i] >= random) {
				return components[i].getDrop();
			}
		}
		try {
			throw new RuntimeException("could not determine a drop for " + toString() + "!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<CustomItemStack>(0);
		}
	}

	public String toString() {
		String out = "CustomBlockRandomDropComponent(components=[";
		for (CustomBlockDropComponent component : components) {
			out += component.toString() + ", ";
		}
		out += ", weights=[";
		for (double weight : weights) {
			out += weight + ", ";
		}
		out += ", total_weight=" + total_weight + ")";
		return out;
	}

}
