package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit;

public class Credit {

	public long ID;
	public long recieverBankingID;
	public long bankID;
	public double amount;
	public double interest;
	public long duration;
	public long start;

	public Credit(long ID, long recieverBankingID, long bankID, double amount, double interest, long duration,
			long start) {
		this.ID = ID;
		this.recieverBankingID = recieverBankingID;
		this.bankID = bankID;
		this.amount = amount;
		this.interest = interest;
		this.duration = duration;
		this.start = start;
	}

}
