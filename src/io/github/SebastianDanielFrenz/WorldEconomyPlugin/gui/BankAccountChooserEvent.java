package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public abstract class BankAccountChooserEvent {

	public abstract void event(InventoryClickEvent event, BankAccount account);

}
