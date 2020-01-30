package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryHandlerThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				ResultSet r = WorldEconomyPlugin.runSQLquery(
						"SELECT employees.employeeID, employees.employeeType, contracts_employment_default.contractSalary, contracts_employment_default.contractLastSalary"
								+ " (employee_matching INNER JOIN contracts_employment_default ON employee_matching.contractID = contracts_employment_default.contractID) "
								+ "INNER JOIN employees ON employees.employeeID = employee_matching.employeeID");
				ResultSet r2;
				while (r.next()) {
					int last_salary = r.getInt("contractLastSalary");
					long employeeID = r.getLong("employeeID");
					double salary = r.getDouble("contractSalary");
					String employeeType = r.getString("employeeType");
					long bankingID;
					
					if (employeeType.equals("player")) {
						r2 = WorldEconomyPlugin.runSQLquery("");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
