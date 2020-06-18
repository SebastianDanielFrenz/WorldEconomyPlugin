package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public abstract class StockMarketOrder {

	public StockMarketOrder(BankAccount bank_account, StockMarketProduct product, long amount, double min_price, double max_price) {
		this.bank_account = bank_account;
		this.product = product;
		this.amount = amount;
		this.min_price = min_price;
		this.max_price = max_price;
	}

	public final StockMarketProduct product;
	public final long amount;
	public final double min_price;
	public final double max_price;
	public final BankAccount bank_account;

}
