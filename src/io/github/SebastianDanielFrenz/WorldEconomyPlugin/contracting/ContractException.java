package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public class ContractException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722254507883575875L;

	public ContractException(String msg) {
		super(msg);
	}

	public ContractException(String msg, Contract contract) {
		String out = msg + System.lineSeparator() + "Contract:" + System.lineSeparator() + contract.toString();
	}

}
