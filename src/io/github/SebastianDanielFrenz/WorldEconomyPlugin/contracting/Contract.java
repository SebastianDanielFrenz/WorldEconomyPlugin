package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public abstract class Contract {

	public long ID;

	public Contract(long ID) {
		this.ID = ID;
	}

	public abstract String getType();

}
