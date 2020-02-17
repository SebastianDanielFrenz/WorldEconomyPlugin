package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public abstract class StockMarketProduct {

	public long stockMarketProductID;
	public String name;
	public String productType;
	public double price;

	public StockMarketProduct(long ID, String name, String productType, double price) {
		this.stockMarketProductID = ID;
		this.name = name;
		this.productType = productType;
		this.price = price;
	}

}
