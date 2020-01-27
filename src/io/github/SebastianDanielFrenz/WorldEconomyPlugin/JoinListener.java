package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.ShopSignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SignData;

public class JoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			try {
				WorldEconomyPlugin.registerUserProfile(event.getPlayer());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) throws SQLException {
		Player player = event.getPlayer();

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			if (block.getType() == Material.OAK_WALL_SIGN) {
				Sign sign = (Sign) block.getState();
				String[] lines = sign.getLines();
				if (lines[0].equalsIgnoreCase("[§4World Economy§0]")) {
					SignData signData = WorldEconomyPlugin.getSign(block.getLocation());
					if (signData == null) {
						player.sendMessage("not a world economy sign!");
					} else if (signData instanceof ShopSignData) {
						PlayerInventory playerInv = player.getInventory();
						ItemStack itemStack = playerInv.getItemInMainHand();
						if (itemStack.getType() == Material.PAPER) {
							List<String> lore = itemStack.getItemMeta().getLore();
							if (lore != null) {
								if (lore.get(0).equalsIgnoreCase("Credit Card")) {
									if (lore.size() == 1) {
										event.getPlayer().sendMessage(WorldEconomyPlugin.PREFIX
												+ "§4This credit card has no banking information!");
									}
									String bankAccountName = lore.get(1);
									WorldEconomyProfile profile = WorldEconomyPlugin.getUserProfile(player);
									BankAccount bankAccount = WorldEconomyPlugin.getBankAccount(profile.bankingID,
											bankAccountName);
									if (bankAccount == null) {
										player.sendMessage(WorldEconomyPlugin.PREFIX
												+ "§4The bank account connected to the credit card does not exist!");
									} else {
										if (bankAccount.getBalance() >= Double.parseDouble(lines[3])) {
											player.sendMessage(WorldEconomyPlugin.PREFIX
													+ "Your bank account has enough money to buy the item.");
										} else {
											player.sendMessage(WorldEconomyPlugin.PREFIX
													+ "§4The bank account does not have enough money");
										}
									}
								} else {
									// not a credit card
								}

							} else {
								// no lore

							}
						} else {
							// not paper
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onSignChangeEvent(SignChangeEvent event) throws SQLException {

		Sign sign = (Sign) event.getBlock().getState();
		String[] lines = event.getLines();
		System.out.println(lines[0]);

		if (lines[0].equalsIgnoreCase("[WE - Shop]")) {
			long productID = Long.parseLong(lines[1]);
			double price = Double.parseDouble(lines[2]);
			long supplyChestID = Long.parseLong(lines[3]);

			ResultSet r1 = WorldEconomyPlugin.runSQLquery(
					"SELECT productManifacturerID, productName FROM products WHERE productID = " + productID);

			if (r1.next()) {
				long companyID = r1.getLong("productManifacturerID");
				String productName = r1.getString("productName");

				ResultSet r2 = WorldEconomyPlugin
						.runSQLquery("SELECT companyName FROM companies WHERE companyID = " + companyID);
				r2.next();
				String companyName = r2.getString("companyName");

				WorldEconomyPlugin
						.runSQL("INSERT INTO signs (signID, signType, signX, signY, signZ, signWorld) VALUES ("
								+ WorldEconomyPlugin.getNextEnumerator("signID") + ", \"shop\", "
								+ sign.getLocation().getBlockX() + ", " + sign.getLocation().getBlockY() + ", "
								+ sign.getLocation().getBlockZ() + ", \"" + sign.getLocation().getWorld().getName()
								+ "\")");
				WorldEconomyPlugin
						.runSQL("INSERT INTO shop_signs (signID, supplyChestID, signOwnerCompanyID, productID, signPrice) VALUES ("
								+ WorldEconomyPlugin.getNextEnumerator("signID") + ", " + supplyChestID + ", "
								+ companyID + ", " + productID + ", " + price + ")");

				WorldEconomyPlugin.moveEnumerator("signID");

				event.setLine(0, "[§4World Economy§0]");
				event.setLine(1, companyName);
				event.setLine(2, productName);
				event.setLine(3, String.valueOf(price));

				event.getPlayer().sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created shop sign!");

			} else {
				event.getPlayer().sendMessage(WorldEconomyPlugin.PREFIX + "§4The product does not exist!");
			}
		}
	}

}
