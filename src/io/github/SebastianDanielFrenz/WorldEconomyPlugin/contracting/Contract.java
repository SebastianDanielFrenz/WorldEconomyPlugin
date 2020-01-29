package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public abstract class Contract {

	public long ID;
	public String type;

	public Contract(long ID, String type, String data) {
		this.ID = ID;
		this.type = type;
		loadData(data);
	}

	public abstract void loadData(String data);

	public abstract String saveData();

}
