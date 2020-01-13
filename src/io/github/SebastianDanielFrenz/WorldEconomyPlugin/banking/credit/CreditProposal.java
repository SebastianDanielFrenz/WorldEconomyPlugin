package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit;

public class CreditProposal {

	private boolean accepted = true;
	private double interest;
	private double amount; // new debt = amount*(1+interest)

	public static CreditProposal getDeniedCredit() {
		return new CreditProposal();
	}

	private CreditProposal() {
		accepted = false;
	}

	public CreditProposal(double interest, double amount) {
		this.interest = interest;
		this.amount = amount;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public double getInterest() {
		return interest;
	}

	public double getAmount() {
		return amount;
	}

}
