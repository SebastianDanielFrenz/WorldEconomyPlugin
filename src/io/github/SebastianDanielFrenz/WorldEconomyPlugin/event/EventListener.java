package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Permissions;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Utils;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.VolatileCooldowns;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyCommandExecutor;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtension;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtensionRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.UserProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.CreateBankAccountChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis.TradeResourcesGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.ShopSignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SupplyChestData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.WorldEconomyBlockPopulator;

public class EventListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		try {
			UserProfile profile = WEDB.getUserProfile(event.getPlayer());
			if (profile == null) {
				WEDB.registerUserProfile(event.getPlayer());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		MailSubsystem.playerJoin(event.getPlayer());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) throws SQLException {
		for (int i = 0; i < WorldEconomyPlugin.research_age_bypass.size(); i++) {
			if (WorldEconomyPlugin.research_age_bypass.get(i).uuid.equals(event.getPlayer().getUniqueId())) {
				WorldEconomyPlugin.research_age_bypass.remove(i);
				break;
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) throws SQLException {
		Player player = event.getPlayer();

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			if (block.getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) block.getState();
				String[] lines = sign.getLines();
				if (lines[0].equalsIgnoreCase("[§4Shop§0]")) {
					if (WorldEconomyCommandExecutor.hasPermission(player, Permissions.SIGN_SHOP_USE, Age.LATE_MIDDLE_AGES)) {
						ShopSignData signData = WEDB.getShopSign(block.getLocation());

						if (signData == null) {
							player.sendMessage("not a world economy sign!");
						} else {
							PlayerInventory playerInv = player.getInventory();
							ItemStack itemStack = playerInv.getItemInMainHand();
							if (itemStack.getType() == Material.PAPER) {
								List<String> lore = itemStack.getItemMeta().getLore();
								if (lore != null) {
									if (lore.get(0).equalsIgnoreCase("Credit Card")) {
										if (lore.size() == 1) {
											event.getPlayer()
													.sendMessage(WorldEconomyPlugin.PREFIX + "§4This credit card has no banking information!");
										}
										String bankAccountName = lore.get(1);
										UserProfile profile = WEDB.getUserProfile(player);
										BankAccount bankAccount = WEDB.getBankAccount(profile.bankingID, bankAccountName);
										if (bankAccount == null) {
											player.sendMessage(
													WorldEconomyPlugin.PREFIX + "§4The bank account connected to the credit card does not exist!");
										} else {
											double price = Double.parseDouble(lines[3]);

											if (bankAccount.getBalance() >= price) {
												player.sendMessage(WorldEconomyPlugin.PREFIX + "Your bank account has enough money to buy the item.");

												// start WEDB replacement
												SupplyChestData chestData = WEDB.getSupplyChest(signData.supplyChestID);
												String productName = lines[2];

												WEDB.buyProductFromChest(profile, chestData, WEDB.getProduct(chestData.ownerCompanyID, productName),
														bankAccount, price);
												// end WEDB replacement

											} else {
												player.sendMessage(WorldEconomyPlugin.PREFIX + "§4The bank account does not have enough money");
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
	}

	@EventHandler
	public void onSignChangeEvent(SignChangeEvent event) throws SQLException {

		Sign sign = (Sign) event.getBlock().getState();
		String[] lines = event.getLines();
		System.out.println(lines[0]);

		if (lines[0].equalsIgnoreCase("[WE - Shop]")) {
			if (WorldEconomyCommandExecutor.hasPermission(event.getPlayer(), Permissions.SIGN_SHOP_CREATE, Age.LATE_MIDDLE_AGES)) {
				long productID = Long.parseLong(lines[1]);
				double price = Double.parseDouble(lines[2]);
				long supplyChestID = Long.parseLong(lines[3]);

				ResultSet r1 = WorldEconomyPlugin
						.runSQLquery("SELECT productManifacturerID, productName FROM products WHERE productID = " + productID);

				if (r1.next()) {
					long companyID = r1.getLong("productManifacturerID");
					String productName = r1.getString("productName");

					ResultSet r2 = WorldEconomyPlugin.runSQLquery("SELECT companyName FROM companies WHERE companyID = " + companyID);
					r2.next();
					String companyName = r2.getString("companyName");

					WorldEconomyPlugin
							.runSQL("INSERT INTO signs (signID, signType, signX, signY, signZ, signWorld) VALUES (" + WEDB.getNextEnumerator("signID")
									+ ", \"shop\", " + sign.getLocation().getBlockX() + ", " + sign.getLocation().getBlockY() + ", "
									+ sign.getLocation().getBlockZ() + ", \"" + sign.getLocation().getWorld().getName() + "\")");
					WorldEconomyPlugin.runSQL("INSERT INTO shop_signs (signID, supplyChestID, signOwnerCompanyID, productID, signPrice) VALUES ("
							+ WEDB.getNextEnumerator("signID") + ", " + supplyChestID + ", " + companyID + ", " + productID + ", " + price + ")");

					WEDB.moveEnumerator("signID");

					event.setLine(0, "[§4Shop§0]");
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

	@EventHandler
	public void onSignBreakEvent(BlockBreakEvent event) throws SQLException {
		signBreakHandler(event.getBlock());
	}

	@EventHandler
	public void onSignFadeEvent(BlockFadeEvent event) throws SQLException {
		signBreakHandler(event.getBlock());
	}

	@EventHandler
	public void onSignBurnEvent(BlockBurnEvent event) throws SQLException {
		signBreakHandler(event.getBlock());
	}

	@EventHandler
	public void onSignExplodeEvent(BlockExplodeEvent event) throws SQLException {
		signBreakHandler(event.getBlock());
	}

	public static void signBreakHandler(Block block) throws SQLException {
		if (block.getType() == Material.WALL_SIGN) {
			if (((Sign) block.getState()).getLine(0).equals("[§4Shop§0]")) {
				ShopSignData sign = WEDB.getShopSign(block.getLocation());
				if (sign != null) {
					WEDB.removeShopSign(block);
				}
			}
		}
	}

	@EventHandler
	public void onVillagerInteractEvent(PlayerInteractEntityEvent event) throws SQLException {
		System.out.println("Villager Interact Event!");

		Entity entity = event.getRightClicked();
		if (entity instanceof Villager) {
			Villager villager = (Villager) entity;
			Map<String, String> args;
			args = Utils.getTagsAfter(villager.getScoreboardTags(), "WorldEconomy_Bank_CreateAccount", new String[] { "BankName" });
			if (args != null) {
				if (VolatileCooldowns.useVillagerInteractCooldown(event.getPlayer())) {
					new CreateBankAccountChatDialog(event.getPlayer(), WEDB.getBank(args.get("BankName")));
					return;
				}
			}
			args = Utils.getTagsAfter(villager.getScoreboardTags(), "WorldEconomy_Bank_Resources", new String[] {});
			if (args != null) {
				if (VolatileCooldowns.useVillagerInteractCooldown(event.getPlayer())) {
					new TradeResourcesGUI().openInventory(event.getPlayer());
					return;
				}
			}
		}
	}

	@EventHandler
	public void onWorldInitEvent(WorldInitEvent event) throws SQLException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// blocks from addons should be registered by now.

		if (event.getWorld().getName().equals("world")) {

			WorldEconomyPlugin.plugin.getLogger().info("Loading extensions...");

			for (WorldEconomyExtension extension : WorldEconomyExtensionRegistry.getExtensions()) {
				WorldEconomyPlugin.plugin.getLogger().info("Loading extension " + extension.getName());
				extension.constructionEvent();
			}

			CustomBlockTypeRegistry.check();

			for (BlockPopulator pop : event.getWorld().getPopulators()) {
				System.out.println(pop.getClass().getCanonicalName());
			}
			event.getWorld().getPopulators().clear();

			event.getWorld().getPopulators().add(new WorldEconomyBlockPopulator());

			// fill custom block cache

			Block mcBlock;
			for (CustomBlock customBlock : WEDB.getAllCustomBlocks(event.getWorld())) {
				mcBlock = customBlock.getLocation().toLocation().getBlock();
				mcBlock.setType(customBlock.getType().material);
				mcBlock.setMetadata("customBlockType", new CustomBlockMetadataValue(customBlock.getType(), customBlock.getData()));
			}

			if (Bukkit.getWorld("heaven") == null) {

				Bukkit.createWorld(new WorldCreator("heaven"));
				World heaven = Bukkit.getWorld("heaven");
				for (int x = -100; x < 100; x++) {
					for (int y = 0; y < 200; y++) {
						for (int z = -100; z < 100; z++) {
							heaven.getBlockAt(new Location(heaven, x, y, z)).setType(Material.WOOL);
						}
					}
				}
				for (int x = -99; x < 99; x++) {
					for (int y = 1; y < 199; y++) {
						for (int z = -99; z < 99; z++) {
							heaven.getBlockAt(new Location(heaven, x, y, z)).setType(Material.AIR);
						}
					}
				}
			}
		}
	}

}
