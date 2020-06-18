package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import java.util.Iterator;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarket;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarketBuyOrder;

public class StockMarketProcessorTask extends Task {

	private boolean has_finished = false;
	private int buyer_index = 0;
	private int seller_index = 0;

	@Override
	public int getPriority() {
		return 100;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {
		Iterator<StockMarketBuyOrder> buys = StockMarket.getBuyOrders().iterator();
		
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		return true;
	}

	@Override
	public boolean hasFinished() {
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean discardOnOverload() {
		// TODO Auto-generated method stub
		return false;
	}

}
