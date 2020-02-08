package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

@SuppressWarnings("deprecation")
public abstract class ChatDialog {

	public Player player;

	public ChatDialog(Player player) {
		this.player = player;
		ChatDialogRegistry.dialogs.add(this);
	}

	public abstract void recieve(String msg);

	public void onEvent(PlayerChatEvent event) {
		event.setCancelled(true);
		recieve(event.getMessage());
	}

	public void reply(String msg) {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(WorldEconomyPlugin.plugin, new Runnable() {
			@Override
			public void run() {
				player.sendMessage(msg);
			}
		}, 1);
	}

	public void close() {
		ChatDialogRegistry.dialogs.remove(this);
	}
}
