package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;

@DataBaseRepresentation
public class Product {

	public long ID;
	public String name;
	public long manifacturerCompanyID;
	public String itemID;
	public double price;

	private CustomItem customItem;

	public Product(long ID, String name, long manifacturerCompanyID, String itemID, double price) {
		this.ID = ID;
		this.name = name;
		this.manifacturerCompanyID = manifacturerCompanyID;
		this.itemID = itemID;
		this.price = price;
	}

	public CustomItem getCustomItem() {
		if (customItem != null) {
			return customItem;
		} else {
			customItem = CustomItemRegistry.getItem(itemID);
			return customItem;
		}
	}

}
