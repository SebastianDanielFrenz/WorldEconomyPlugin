package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public class ContractCancellationDetailsNotFoundException extends ContractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8983864933672876830L;

	public ContractCancellationDetailsNotFoundException(String msg) {
		super(msg);
	}

	public ContractCancellationDetailsNotFoundException(String msg, Contract contract, ContractSigner party) {
		super(msg + System.lineSeparator() + "Contract:" + System.lineSeparator()
				+ "Party requesting cancelation details: " + party.getName());
	}

}
