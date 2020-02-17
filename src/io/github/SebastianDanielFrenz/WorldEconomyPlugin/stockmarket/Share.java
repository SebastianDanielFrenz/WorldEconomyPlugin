package io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket;

public class Share extends StockMarketProduct {

	public Share(long ID, String name, String type, double price, long totalAmount, long companyID, double totalPartage,
			double dividend) {
		super(ID, name, "share", price);

		this.shareType = type;
		this.shareCompanyID = companyID;
		this.shareTotalAmount = totalAmount;
		this.shareDividend = dividend;
	}

	public String shareType;
	public long shareTotalAmount;
	public long shareCompanyID;
	public double shareTotalPartage;
	public double shareDividend;

}
