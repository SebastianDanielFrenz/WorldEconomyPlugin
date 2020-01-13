package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.CreditProposal;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.CreditRequest;

public abstract class Bank {
	
	public abstract String getName();
	
	public abstract String getID();
	
	public abstract CreditProposal answerCreditRequest(CreditRequest request);

}
