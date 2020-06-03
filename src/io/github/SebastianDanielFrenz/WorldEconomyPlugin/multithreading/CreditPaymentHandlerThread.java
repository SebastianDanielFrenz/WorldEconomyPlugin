package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.Credit;

public class CreditPaymentHandlerThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				// WorldEconomyPlugin.plugin.getLogger().info("running credit
				// check...");

				List<Credit> credits = WEDB.getAllCredits();
				for (Credit credit : credits) {
					String type = WEDB.getBankingEntityType(credit.recieverBankingID);
					BankAccount bankAccount;

					if (type.equals("player")) {
						bankAccount = WEDB.getBankAccount(credit.recieverBankAccountID);
						ResultSet r = WorldEconomyPlugin.runSQLquery(
								"SELECT * FROM user_profiles WHERE playerBankingID = " + credit.recieverBankingID);
						if (!r.next()) {
							WorldEconomyPlugin.plugin.getLogger()
									.info(WorldEconomyPlugin.PREFIX
											+ "[Credit Payment Handler Thread]: Player with bankingID "
											+ credit.recieverBankingID + " is not registered!");
							continue;
						}

						OfflinePlayer offlinePlayer = Bukkit
								.getOfflinePlayer(UUID.fromString(r.getString("playerUUID")));
						if (offlinePlayer.isOnline()) {
							Player player = (Player) offlinePlayer;
							long current_time = player.getStatistic(Statistic.PLAY_ONE_TICK);

							if (current_time >= credit.start + credit.duration * 20 * 60) {
								UserProfile profile = WEDB.getUserProfile(player);

								long bankMailboxID = WEDB.getBankMailboxIDFromBankID(credit.bankID);

								if (bankAccount.getBalance() >= credit.amount) {
									WEDB.payOffCredit(credit);
									WEDB.sendMail(bankMailboxID, profile.mailboxID,
											"You automatically paid off your credit!");
								} else {
									WEDB.sendMail(bankMailboxID, profile.mailboxID,
											"Your credit is due! Please put enough money o");
									// TODO please fix mail sender ID; 1 is the
									// first
									// player
									// to register!
								}
							}
						}
					}
				}
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					WorldEconomyPlugin.plugin.getLogger().info("Detected shutdown! Stopping credit handler thread!");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				WorldEconomyPlugin.plugin.getLogger().info("Shutting down credit handler thread!");
				return;
			}
		}
	}

}
