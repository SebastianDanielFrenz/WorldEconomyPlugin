package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import java.sql.SQLException;
import java.util.Iterator;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarketBuyOrder;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarketSellOrder;

public class StockMarketProcessorTask extends Task {

	private boolean has_finished = false;

	@Override
	public int getPriority() {
		return 100;
	}

	@Override
	public void init() {
		StockMarket.blockOrders(this);
	}

	@Override
	public void work() {
		Iterator<StockMarketBuyOrder> buys = StockMarket.getBuyOrders().iterator();
		Iterator<StockMarketSellOrder> sells = StockMarket.getSellOrders().iterator();
		// lowest seller first

		if (buys.hasNext() && sells.hasNext()) {
			return;
		}

		StockMarketBuyOrder buy = buys.next();
		StockMarketSellOrder sell = sells.next();

		double price;

		while (true) {
			if (sell.max_price >= buy.max_price) {
				if (sell.min_price > buy.max_price) {
					// sell range too high
					if (buys.hasNext()) {
						buy = buys.next();
						continue;
					} else {
						break;
					}
				} else {
					price = buy.max_price;
				}
			} else {
				if (buy.min_price > sell.max_price) {
					if (sells.hasNext()) {
						sell = sells.next();
						continue;
					} else {
						break;
					}
				} else {
					price = sell.max_price;
				}
			}

			try {
				StockMarket.buy(buy, sell, Math.min(buy.amount, sell.amount), price);
				if (buys.hasNext() && sells.hasNext()) {
					buy = buys.next();
					sell = sells.next();
				} else {
					break;
				}
			} catch (SQLException e) {
				throw new RuntimeException("Stock Market transaction failed! (buyer: " + buy.bank_account + "; seller: "
						+ sell.bank_account + ")");
			}
		}

		has_finished = true;
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return true;
	}

	@Override
	public boolean continueOnShutdown() {
		return true;
	}

	@Override
	public boolean hasFinished() {
		return has_finished;
	}

	@Override
	public String getName() {
		return "Stock Market Processor Thread";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
