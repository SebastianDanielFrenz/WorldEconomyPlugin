package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public class StockMarketSellOrder extends StockMarketOrder {

	public StockMarketSellOrder(StockMarketProduct product, long amount, double min_price, double max_price) {
		super(product, amount, min_price, max_price);
	}

}
