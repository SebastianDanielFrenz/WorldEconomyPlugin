package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public class MachineRecipe {

	public MachineRecipe(CustomItemStack[] input, CustomItemStack[] output, double processTime) {
		this.input = input;
		this.output = output;
		this.processTime = processTime;
	}

	private CustomItemStack[] input;
	private CustomItemStack[] output;
	private double processTime;

	public CustomItemStack[] getInput() {
		return input;
	}

	public void setInput(CustomItemStack[] input) {
		this.input = input;
	}

	public CustomItemStack[] getOutput() {
		return output;
	}

	public void setOutput(CustomItemStack[] output) {
		this.output = output;
	}

	public double getProcessTime() {
		return processTime;
	}

	public void setProcessTime(double processTime) {
		this.processTime = processTime;
	}
}
