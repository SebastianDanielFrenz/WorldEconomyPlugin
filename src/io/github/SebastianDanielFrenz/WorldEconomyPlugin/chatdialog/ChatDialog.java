package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog;

import org.bukkit.entity.Player;

public abstract class ChatDialog {

	public Player player;

	public ChatDialog(Player player) {
		this.player = player;
		ChatDialogRegistry.dialogs.add(this);
	}

	public abstract void recieve(String msg);

	public void close() {
		ChatDialogRegistry.dialogs.remove(this);
	}
}
