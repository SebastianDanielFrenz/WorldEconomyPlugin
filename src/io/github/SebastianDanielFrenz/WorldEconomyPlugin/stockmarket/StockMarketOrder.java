package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public abstract class StockMarketOrder {

	public StockMarketOrder(StockMarketProduct product, long amount, double min_price, double max_price) {
		this.product = product;
		this.amount = amount;
		this.min_price = min_price;
		this.max_price = max_price;
	}

	public final StockMarketProduct product;
	public final long amount;
	public final double min_price;
	public final double max_price;

}
