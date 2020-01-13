package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public interface ContractSigner {

	public default void proposeContract(ContractSigner contractSigner, Contract contract) {
		contractSigner.recieveContract(this, contract);
	}

	public Contract[] getContracts();

	public void recieveContract(ContractSigner proposer, Contract contract);

	public String getName();

}
