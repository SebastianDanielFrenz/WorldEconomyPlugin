package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotSupportedException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks.StockMarketProcessorTask;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.LimitedQueue;

public class StockMarket {

	private static Queue<StockMarketBuyOrder> buy_orders = new PriorityQueue<StockMarketBuyOrder>(
			new StockMarketBuyOrderComparator());
	private static Queue<StockMarketSellOrder> sell_orders = new PriorityQueue<StockMarketSellOrder>(
			new StockMarketSellOrderComparator());

	private static Map<StockMarketProduct, LimitedQueue<Double>> market_values = new TreeMap<StockMarketProduct, LimitedQueue<Double>>();

	private static List<Task> blockingTasks = new ArrayList<Task>();

	private static List<StockMarketOrder> order_overflow = new ArrayList<StockMarketOrder>();

	private static Thread registering;

	public synchronized static void registerOrder(StockMarketOrder order) {
		if (blockingTasks.size() == 0) {
			if (order instanceof StockMarketBuyOrder) {
				buy_orders.add((StockMarketBuyOrder) order);
			} else if (order instanceof StockMarketSellOrder) {
				sell_orders.add((StockMarketSellOrder) order);
			} else {
				throw new NotSupportedException(
						"Stock Market can only register StockMarketBuyOrder and StockMarketSellOrder, not orders of type "
								+ order.getClass().getCanonicalName() + "!");
			}
		} else {
			registering = Thread.currentThread();
			order_overflow.add(order);
			registering = null;
		}
	}

	public static Queue<StockMarketBuyOrder> getBuyOrders() {
		return buy_orders;
	}

	public static Queue<StockMarketSellOrder> getSellOrders() {
		return sell_orders;
	}

	public static double getMarketValue(StockMarketProduct product) {
		double total = 0;

		LimitedQueue<Double> queue = market_values.get(product);
		Iterator<Double> values = queue.iterator();

		if (!values.hasNext()) {
			return Double.NaN;
		}
		while (values.hasNext()) {
			total += values.next();
		}
		return total / queue.size();
	}

	public static void buy(StockMarketBuyOrder buy_order, StockMarketSellOrder sell_order, long amount, double price)
			throws SQLException {
		WEDB.transferStock(sell_order.bank_account, buy_order.bank_account, buy_order.product, amount, price);
	}

	/**
	 * This method only exists for synchronization.
	 * 
	 * @param task
	 * @param action
	 */
	public synchronized static void blockOrders(StockMarketProcessorTask task, boolean action) {
		if (action == ACTION_BLOCK) {
			blockingTasks.add(task);
		} else {
			blockingTasks.remove(task);
			if (blockingTasks.size() == 0) {
				registerOverflowedOrders();
			}
		}
	}

	public static void blockOrders(StockMarketProcessorTask task) {
		blockOrders(task, ACTION_BLOCK);
	}

	public static void unblockOrders(StockMarketProcessorTask task) {
		blockOrders(task, ACTION_UNBLOCK);
	}

	public static final boolean ACTION_BLOCK = false;
	public static final boolean ACTION_UNBLOCK = true;

	public static void registerOverflowedOrders() {
		if (registering != null) {
			for (StockMarketOrder order : order_overflow) {
				registerOrder(order);
			}
		} else {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			registerOverflowedOrders();
		}

	}
}