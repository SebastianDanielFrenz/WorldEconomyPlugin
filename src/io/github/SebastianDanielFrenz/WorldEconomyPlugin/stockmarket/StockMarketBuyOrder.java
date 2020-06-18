package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class StockMarketBuyOrder extends StockMarketOrder {

	public StockMarketBuyOrder(BankAccount bank_account, StockMarketProduct product, long amount, double min_price, double max_price) {
		super(bank_account, product, amount, min_price, max_price);
	}

}
