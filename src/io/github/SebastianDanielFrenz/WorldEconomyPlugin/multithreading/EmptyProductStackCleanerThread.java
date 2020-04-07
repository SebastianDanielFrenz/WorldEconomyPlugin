package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.sql.SQLException;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class EmptyProductStackCleanerThread implements Runnable {

	@Override
	public void run() {
		try {
			WorldEconomyPlugin.runSQL("DELETE FROM stock_market_possesions WHERE purchaseAmount = 0");
			Thread.sleep(5 * 60 * 1000);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Shutting down empty product stack cleaner thread...");
		}
	}

}
