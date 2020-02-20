package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class Product {

	public long ID;
	public String name;
	public long manifacturerID;
	public String itemID;
	public int itemAmount;
	public double price;

	public Product(long ID, String name, long manifacturerID, String itemID, int itemAmount, double price) {
		this.ID = ID;
		this.name = name;
		this.manifacturerID = manifacturerID;
		this.itemID = itemID;
		this.itemAmount = itemAmount;
		this.price = price;
	}

}
