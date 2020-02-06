package io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyProfile;

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
		if (owner instanceof WorldEconomyProfile) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(((WorldEconomyProfile) owner).uuid);
			if (player.isOnline()) {
				Player nplayer = (Player) player;
				nplayer.sendMessage(WorldEconomyPlugin.PREFIX + "You recieved a mail!");
			}
		}
	}

}
