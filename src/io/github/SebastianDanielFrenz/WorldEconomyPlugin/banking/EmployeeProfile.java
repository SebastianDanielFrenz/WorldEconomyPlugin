package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import java.util.ArrayList;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.ContractSigner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.employment.EmploymentContract;

public class EmployeeProfile {

	public final WorldEconomyProfile general_profile;
	private ArrayList<EmploymentContract> contracts;

	public EmployeeProfile(WorldEconomyProfile profile, ArrayList<EmploymentContract> contracts) {
		general_profile = profile;
		this.contracts = contracts;
	}

	public ArrayList<EmploymentContract> getContracts(ContractSigner contractSigner) {
		ArrayList<EmploymentContract> out = new ArrayList<EmploymentContract>();

		for (EmploymentContract contract : contracts) {
			if (contract.getEmployer() == contractSigner) {
				out.add(contract);
			}
		}

		return out;
	}

}
