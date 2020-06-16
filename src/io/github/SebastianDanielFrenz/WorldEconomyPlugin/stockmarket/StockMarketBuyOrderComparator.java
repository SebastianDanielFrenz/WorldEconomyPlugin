package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import java.util.Comparator;

public class StockMarketBuyOrderComparator implements Comparator<StockMarketBuyOrder> {

	@Override
	public int compare(StockMarketBuyOrder arg0, StockMarketBuyOrder arg1) {
		return (arg0.max_price < arg1.max_price) ? 1 : (arg0.max_price > arg1.max_price) ? -1 : 0;
	}

}
