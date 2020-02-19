package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public class StockMarketProductStack {

	public StockMarketProductStack(long stackID, long productID, long ownerBankAccountID, long purchaseTime,
			double purchasePrice, long purchaseAmount) {
		this.stackID = stackID;
		this.productID = productID;
		this.ownerBankAccountID = ownerBankAccountID;
		this.purchaseTime = purchaseTime;
		this.purchaseAmoount = purchaseAmount;
		this.purchasePrice = purchasePrice;
	}

	public long stackID;
	public long productID;
	public long ownerBankAccountID;
	public long purchaseTime;
	public double purchasePrice;
	public long purchaseAmoount;

}
