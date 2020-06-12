package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public class StockMarketBuyOrder extends StockMarketOrder {

	public StockMarketBuyOrder(StockMarketProduct product, long amount, double min_price, double max_price) {
		super(product, amount, min_price, max_price);
	}

}
