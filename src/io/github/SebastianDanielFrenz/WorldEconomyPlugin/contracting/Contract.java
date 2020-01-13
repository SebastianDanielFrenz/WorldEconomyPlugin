package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public abstract class Contract {

	public final long ID;

	protected ContractSigner[] parties;

	public Contract(long ID, ContractSigner[] parties, ContractCancellationDetails[] cancellation_details) {
		this.parties = parties;
		this.cancellation_details = cancellation_details;

		this.ID = ID;
	}

	protected ContractAgreementStatus agreement_status = ContractAgreementStatus.WAITING;
	protected ContractCancellationDetails[] cancellation_details;

	public ContractAgreementStatus getAgreementStatus() {
		return agreement_status;
	}

	public ContractCancellationDetails getCancellationDetailsFor(ContractSigner party) {
		for (int i = 0; i < parties.length; i++) {
			if (parties[i] == party) {
				return cancellation_details[i];
			}
		}
		throw new ContractCancellationDetailsNotFoundException("Contract cancellation details not found!", this, party);
	}

	/**
	 * Returns the remaining ticks before the contract would end after a
	 * cancellation was to be sent.
	 * 
	 * @param party
	 * @return
	 */
	public long getContractCancellationTimestamp(ContractSigner party) {
		return getCancellationDetailsFor(party).getDueTime(WorldEconomyPlugin.tick_counter);
	}

	public abstract boolean isFinished();

	public String toString() {
		return "Contract ID: " + ID;
	}

}
