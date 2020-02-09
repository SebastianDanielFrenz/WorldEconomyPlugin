package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;

public abstract class BankChooserEvent {

	public abstract void event(InventoryClickEvent event, Bank bank);

}
