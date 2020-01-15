package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.CreditProposal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.CreditRequest;

public abstract class Bank {

	public abstract String getName();

	public abstract String getID();

	public abstract CreditProposal answerCreditRequest(CreditRequest request);

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Bank ? ((Bank) obj).getID() == getID() : false;
	}

	public abstract void createAccount(BankCustomer customer, String name);

	public abstract double getBalance(BankAccount account);

	public abstract BankMoneyTransferResponse transferMoney(BankAccount src, BankAccount dst);

}
