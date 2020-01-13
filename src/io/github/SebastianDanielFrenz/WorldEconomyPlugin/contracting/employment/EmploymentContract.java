package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.employment;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.ContractCancellationDetails;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.ContractSigner;

public abstract class EmploymentContract extends Contract {

	public EmploymentContract(long ID, ContractSigner employer, ContractSigner employee,
			ContractCancellationDetails employer_cancellation_details,
			ContractCancellationDetails employee_cancellation_details) {
		super(ID, new ContractSigner[] { employer, employee },
				new ContractCancellationDetails[] { employer_cancellation_details, employee_cancellation_details });
	}
	
	public ContractSigner getEmployer() {
		return parties[0];
	}
	
	public ContractSigner getEmployee() {
		return parties[1];
	}

}
