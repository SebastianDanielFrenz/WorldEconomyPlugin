package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import org.bukkit.World;

public class ShopSignData extends SignData {

	public ShopSignData(long ID, int x, int y, int z, World world, String type, long supplyChestID, long productID,
			double price) {
		super(ID, x, y, z, world, type);

		this.supplyChestID = supplyChestID;
		this.price = price;
		this.productID = productID;
	}

	public long supplyChestID;
	public long productID;
	public double price;

}
