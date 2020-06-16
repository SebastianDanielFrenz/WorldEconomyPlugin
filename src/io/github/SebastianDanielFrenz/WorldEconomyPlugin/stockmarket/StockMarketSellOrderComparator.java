package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import java.util.Comparator;

public class StockMarketSellOrderComparator implements Comparator<StockMarketSellOrder> {

	@Override
	public int compare(StockMarketSellOrder arg0, StockMarketSellOrder arg1) {
		return (arg0.min_price < arg1.min_price) ? -1 : (arg0.min_price > arg1.min_price) ? 1 : 0;
	}

}
