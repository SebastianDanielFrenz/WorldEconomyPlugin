package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class SalaryHandlerThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				ResultSet r = WorldEconomyPlugin.runSQLquery(
						"SELECT employees.employeeID, employees.employeeType, contracts_employment_default.contractSalary, contracts_employment_default.contractLastSalary,"
								+ " contracts_employment_default.contractID"
								+ " ((employee_matching INNER JOIN contracts_employment_default ON employee_matching.contractID = contracts_employment_default.contractID) "
								+ "INNER JOIN employees ON employees.employeeID = employee_matching.employeeID) INNER JOIN employers ON employers.employerID = employee_matchin.employerID");
				ResultSet r2;
				while (r.next()) {
					int last_salary = r.getInt("contractLastSalary");
					long employeeID = r.getLong("employeeID");
					double salary = r.getDouble("contractSalary");
					String employeeType = r.getString("employeeType");
					long employerID = r.getLong("employerID");
					long bankingID;
					int play_time;

					if (employeeType.equals("player")) {
						// please insert time check for salary oayment.

						r2 = WorldEconomyPlugin.runSQLquery(
								"SELECT bankingID, playerUUID FROM user_profiles WHERE employeeID = " + employeeID);

						OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(r2.getString("playerUUID")));
						play_time = player.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE);

						if (!(play_time - last_salary >= 3 * 60)) {
							continue;
						}

						bankingID = r2.getLong("bankingID");
					} else {
						WorldEconomyPlugin.plugin.getLogger().info("Invalid employee type \"" + employeeType + "\"!");
						continue;
					}

					BankAccount employeeAcount = WEDB.getBankAccount(bankingID, "income");
					Employer employer = WEDB.getEmployer(employerID);
					BankAccount employerAccount = WEDB.getBankAccount(employer.bankingID, "salaries");

					WEDB.bankAccountTransaction(employerAccount, employeeAcount, r.getDouble("contractSalary"));

					WorldEconomyPlugin
							.runSQL("UPDATE contracts_employment_default SET contractLastSalary = contractLastSalary + 180 WHERE contractID = "
									+ r.getLong("contractID"));

					WorldEconomyPlugin.plugin.getLogger().info(
							"Salary: Employee " + employerID + " was payed " + salary + " by employer " + employerID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
