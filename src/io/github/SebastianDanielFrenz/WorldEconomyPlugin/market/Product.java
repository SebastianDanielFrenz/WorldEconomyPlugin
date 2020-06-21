package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class Product {

	public long ID;
	public String name;
	public long manifacturerCompanyID;
	public String itemID;
	public double price;

	public Product(long ID, String name, long manifacturerCompanyID, String itemID, int itemAmount, double price) {
		this.ID = ID;
		this.name = name;
		this.manifacturerCompanyID = manifacturerCompanyID;
		this.itemID = itemID;
		this.price = price;
	}

}
