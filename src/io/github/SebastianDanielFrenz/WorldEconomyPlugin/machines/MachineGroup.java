package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

public enum MachineGroup {

	BASIC_FURNACE("§4Basic Furnace");

	private MachineGroup(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

}
