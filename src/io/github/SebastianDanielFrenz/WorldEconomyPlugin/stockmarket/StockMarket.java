package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotSupportedException;

public class StockMarket {

	private static Queue<StockMarketBuyOrder> buy_orders = new PriorityQueue<StockMarketBuyOrder>(new StockMarketBuyOrderComparator());
	private static Queue<StockMarketSellOrder> sell_orders = new PriorityQueue<StockMarketSellOrder>(new StockMarketSellOrderComparator());

	private static boolean accept_orders = true;

	public static void registerOrder(StockMarketOrder order) {
		int i; // accept orders; if not, task
		if (order instanceof StockMarketBuyOrder) {
			buy_orders.add((StockMarketBuyOrder) order);
		} else if (order instanceof StockMarketSellOrder) {
			sell_orders.add((StockMarketSellOrder) order);
		} else {
			throw new NotSupportedException(
					"Stock Market can only register StockMarketBuyOrder and StockMarketSellOrder, not orders of type "
							+ order.getClass().getCanonicalName() + "!");
		}
	}

	public static void blockOrders() {
		accept_orders = false;
	}

	public static void reopenOrders() {
		accept_orders = true;
	}

	public static Queue<StockMarketBuyOrder> getBuyOrders() {
		return buy_orders;
	}

	public static Queue<StockMarketSellOrder> getSellOrders() {
		return sell_orders;
	}

}
