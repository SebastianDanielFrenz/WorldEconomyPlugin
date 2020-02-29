package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

public enum MachineGroup {

	BASIC_FURNACE("§eBasic Furnace", MachineKategory.SMELTING);

	private MachineGroup(String name, MachineKategory kategory) {
		this.name = name;
		this.kategory = kategory;
	}

	private String name;
	private MachineKategory kategory;

	public String getName() {
		return name;
	}

	public MachineKategory getKategory() {
		return kategory;
	}

}
