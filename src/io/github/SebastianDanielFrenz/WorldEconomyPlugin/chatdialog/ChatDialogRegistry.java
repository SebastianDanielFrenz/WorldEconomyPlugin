package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

@SuppressWarnings("deprecation")
public class ChatDialogRegistry implements Listener {
	public static List<ChatDialog> dialogs = new ArrayList<ChatDialog>();

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChatEntry(PlayerChatEvent event) {
		WorldEconomyPlugin.plugin.getLogger()
				.info("PlayerChatEvent: " + event.getPlayer().getName() + " wrote " + event.getMessage());
		// this should be as fast as possible

		for (ChatDialog dialog : dialogs) {
			if (dialog.player == event.getPlayer()) {
				dialog.recieve(event.getMessage());
				break;
			}
		}
	}
}
