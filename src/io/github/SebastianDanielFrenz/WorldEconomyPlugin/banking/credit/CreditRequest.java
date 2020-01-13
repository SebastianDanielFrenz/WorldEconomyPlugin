package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit;

public abstract class CreditRequest {

	public CreditRequest() {
		
	}

	public abstract boolean acceptProposal(CreditProposal proposal);

}
