package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

public enum MachineGroup {

	BASIC_FURNACE("§eBasic Furnace", MachineCategory.SMELTING);

	private MachineGroup(String name, MachineCategory kategory) {
		this.name = name;
		this.kategory = kategory;
	}

	private String name;
	private MachineCategory kategory;

	public String getName() {
		return name;
	}

	public MachineCategory getKategory() {
		return kategory;
	}

}
