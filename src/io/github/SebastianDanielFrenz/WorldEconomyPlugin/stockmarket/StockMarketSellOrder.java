package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class StockMarketSellOrder extends StockMarketOrder {

	public StockMarketSellOrder(BankAccount bank_account, StockMarketProduct product, long amount, double min_price, double max_price) {
		super(bank_account, product, amount, min_price, max_price);
	}

}
