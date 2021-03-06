package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class StockMarketProductStack {

	public StockMarketProductStack(long stackID, long productID, long ownerBankAccountID, long purchaseTime,
			double purchasePrice, long purchaseAmount) {
		this.stackID = stackID;
		this.productID = productID;
		this.ownerBankAccountID = ownerBankAccountID;
		this.purchaseTime = purchaseTime;
		this.purchaseAmount = purchaseAmount;
		this.purchasePrice = purchasePrice;
	}

	public long stackID;
	public long productID;
	public long ownerBankAccountID;
	public long purchaseTime;
	public double purchasePrice;
	public long purchaseAmount;

	public static StockMarketProductStack create(long productID, long ownerBankAccountID, long purchaseTime,
			double purchasePrice, long purchaseAmount) {
		return new StockMarketProductStack(0, productID, ownerBankAccountID, purchaseTime, purchasePrice,
				purchaseAmount);
	}

}
