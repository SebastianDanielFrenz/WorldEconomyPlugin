package io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;

public class MailSubsystem {

	public static void playerJoin(Player player) throws SQLException {
		long count = WEDB.getMessageCount(WEDB.getMailboxID(player));
		if (count == 0) {
			player.sendMessage(WorldEconomyPlugin.PREFIX + "You have no new messages.");
		} else if (count == 1) {
			player.sendMessage(
					WorldEconomyPlugin.PREFIX + "You have §41§e new message. Type \"we mail read\" to see it.");
		} else {
			player.sendMessage(WorldEconomyPlugin.PREFIX + "You have §4" + count
					+ "§e new messages. Type \"/we mail read\" to see them.");
		}
	}

	public static void deliveryNotification(long sender, long reciever, String msg) throws SQLException {
		MailboxOwner owner = WEDB.getMailboxOwner(reciever);
		if (owner instanceof UserProfile) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(((UserProfile) owner).uuid);
			if (player.isOnline()) {
				Player nplayer = (Player) player;
				nplayer.sendMessage(WorldEconomyPlugin.PREFIX + "You recieved a mail!");
			}
		}
	}

	public static void showPlayerInbox(Player player) {
		try {
			List<Mail> mails = WEDB.getMails(player, 10);
			player.sendMessage(WorldEconomyPlugin.PREFIX + "Displaying at most 10 mails:");
			for (int i = 0; i < mails.size() && i < 10; i++) {
				player.sendMessage("§e[§4" + mails.get(i).ID + "§e]: Mail from "
						+ WEDB.getMailboxOwner(mails.get(i).senderMailboxID).getMailboxDisplayName() + ":");
				player.sendMessage("§e" + mails.get(i).message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			player.sendMessage(Lang.getError(player, Lang.ERROR_INTERNAL));
		}
	}

	public static void showCompanyInbox(Player player, Company company) {
		try {
			List<Mail> mails = WEDB.getMails(company, 10);
			player.sendMessage(WorldEconomyPlugin.PREFIX + "Displaying at most 10 mails:");
			for (int i = 0; i < mails.size() || i < 10; i++) {
				player.sendMessage("§e[§4" + mails.get(i).ID + "§e]: Mail from §f"
						+ WEDB.getMailboxOwner(mails.get(i).senderMailboxID).getMailboxDisplayName() + "§e:");
				player.sendMessage("§e" + mails.get(i).message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			player.sendMessage(Lang.getError(player, Lang.ERROR_INTERNAL));
		}
	}

}
