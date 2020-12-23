package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;

/**
 * Capitalized methods doe not require message IDs.<br>
 * Use get(String msg) to retrieve
 * 
 * @author crash
 *
 */
public class Lang {

	public static String getLanguage(Player p) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException, SecurityException {
		Object ep = getMethod("getHandle", p.getClass()).invoke(p, (Object[]) null);
		Field f = ep.getClass().getDeclaredField("locale");
		f.setAccessible(true);
		String language = (String) f.get(ep);
		return language;
	}

	public static String getLanguageSafe(Player p) {
		try {
			return getLanguage(p);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e) {
			e.printStackTrace();
			return "en_us";
		}
	}

	public static String getAdaptedLanguage(Player p) {
		String lang = getLanguageSafe(p);
		String new_lang = lang_map.get(lang);
		if (new_lang == null) {
			new_lang = "en_us";
		}
		return new_lang;
	}

	private static Method getMethod(String name, Class<?> clazz) {
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name))
				return m;
		}
		return null;
	}

	public static Map<String, String> lang_map = new TreeMap<String, String>();

	public static String get(CommandSender sender, String msgID) {
		String lang;
		if (sender instanceof Player) {
			lang = getAdaptedLanguage((Player) sender);
		} else {
			lang = Config.getServerLanguage();
		}

		InputStream inputStream = WorldEconomyPlugin.class.getResourceAsStream("/res/lang/" + lang + ".lang");

		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String line;

		try {
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}

				if (line.startsWith(msgID)) {
					br.close();
					isr.close();
					return line.split("[=]")[1];
				}
			}

			System.out.println("Could not find " + msgID + " in " + lang + ".lang!");
			return msgID;
		} catch (IOException e2) {
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
				return msgID;
			}

			System.out.println("Error while retrieving " + msgID + " in " + lang + ".lang!");
			return msgID;
		}
	}

	public static String getErrorNotEnoughArguments(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.not_enough_arguments");
	}

	public static String getErrorCommandNotFound(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.command_not_found");
	}

	public static String getErrorInvalidArgument(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.invalid_argument");
	}

	public static String getErrorInternal(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.internal");
	}

	public static String getErrorAccessingFuture(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.accessing_future");
	}

	public static String getErrorInsufficientPermission(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.insufficient_permission");
	}

	public static String getErrorNotAPlayer(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.not_a_player");
	}

	public static String getErrorHandEmpty(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.hand_empty");
	}

	public static String getErrorIllegalItem(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.illegal_item");
	}

	public static String getErrorProductAlreadyExists(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.product_already_exists");
	}

	public static String getErrorInvalidNumber(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.invalid_number");
	}

	public static String getErrorLookAtChest(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.look_at_chest");
	}

	public static String getErrorMoveCloser(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.move_closer");
	}

	public static String getErrorEmptyHand(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.empty_hand");
	}

	public static String getErrorBankAccountDoesNotExist(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.bank_account_does_not_exist");
	}

	public static String getErrorNotEnoughSpace(CommandSender sender) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error.not_enough_space");
	}

	public static final String SUCCESS_REGISTER_BANK = "register_bank";

	public static final String MSG_MAIL_RECIEVED = "mail_recieved";

	public static final String CHATDIALOG_REGISTER_PRODUCT_NAME = "register_product_name";
	public static final String CHATDIALOG_REGISTER_PRODUCT_PRICE = "register_product_price";
	public static final String CHATDIALOG_REGISTER_PRODUCT_INVALID_PRICE = "register_product_invalid_price";

	public static String getGuiItemBack(CommandSender player) {
		return get(player, "gui.item.back");
	}

	public static String getGuiTitleBankAccountCredits(CommandSender player) {
		return get(player, "gui.title.bank_account_credits");
	}

	public static String getGuiItemBankAccount_Credits(CommandSender player) {
		return get(player, "gui.item.bank_account.credits");
	}

	public static String getGuiTitleBankAccounts(CommandSender player) {
		return get(player, "gui.title.bank_accounts");
	}

	public static String getGuiTitleBanks(CommandSender player) {
		return get(player, "gui.title.banks");
	}

	public static String getGuiItemBuyResource_CustomAmount(CommandSender player) {
		return get(player, "gui.item.buy_resource.custom_amount");
	}

	public static String getGuiTitleChooseBankAccount(CommandSender player) {
		return get(player, "gui.title.choose_bank_account");
	}

	public static String getGuiTitleCompanies(CommandSender player) {
		return get(player, "gui.title.companies");
	}

	public static String getGuiItemCompany_Products(CommandSender player) {
		return get(player, "gui.item.company.products");
	}

	public static String getGuiItemCompany_RegisterProduct(CommandSender player) {
		return get(player, "gui.item.company.register_product");
	}

	public static String getGuiItemCompany_Sales(CommandSender player) {
		return get(player, "gui.item.company.sales");
	}

	public static String getGuiItemCompany_Employees(CommandSender player) {
		return get(player, "gui.item.company.employees");
	}

	public static String getGuiTitleCreateBankAccount(CommandSender player) {
		return get(player, "gui.title.create_bank_account");
	}

	public static String getGuiTitleMainMenu(CommandSender player) {
		return get(player, "gui.title.main_menu");
	}

	public static String getGuiItemMainMenu_Mailbox(CommandSender player) {
		return get(player, "gui.item.main_menu.mailbox");
	}

	public static String getGuiItemMainMenu_WriteMail(CommandSender player) {
		return get(player, "gui.item.main_menu.write_mail");
	}

	public static String getGuiTitleResearch(CommandSender player) {
		return get(player, "gui.title.research");
	}

	public static String getGuiItemSellResource_CustomAmount(CommandSender player) {
		return get(player, "gui.item.sell_resource.custom_amount");
	}

	public static String getGuiTitleStockMarket(CommandSender player) {
		return get(player, "gui.title.stock_market");
	}

	public static String getGuiTitleTransferMoney(CommandSender player) {
		return get(player, "gui.title.transfer_money");
	}

	public static final String OTHER_PLAYER = "other.player";
	public static final String OTHER_AI = "other.ai";
	public static final String OTHER_CORPORATION = "other.corporation";
	public static final String OTHER_PRIVATE_COMPANY = "other.private_company";

	public static String getItem(CommandSender sender, String ID) {
		return get(sender, "item." + ID);
	}

	public static String getItem(CommandSender sender, CustomItem item) {
		return get(sender, "item." + item.ID);
	}

	public static String getBlock(CommandSender sender, String ID) {
		return get(sender, "block." + ID);
	}

	public static String getBlock(CommandSender sender, CustomBlockType block) {
		return get(sender, "block." + block.ID);
	}

	public static String getAge(CommandSender sender, String age) {
		return get(sender, "age." + age.toLowerCase());
	}

	public static String getAge(CommandSender sender, Age age) {
		return get(sender, "age." + age.getID().toLowerCase());
	}

	public static String getMsg(CommandSender sender, String msg) {
		return WorldEconomyPlugin.PREFIX + get(sender, "msg." + msg);
	}

	public static String getError(CommandSender sender, String msg) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "msg.error." + msg);
	}

	public static String getSuccess(CommandSender sender, String msg) {
		return WorldEconomyPlugin.PREFIX + "§a" + get(sender, "msg.success." + msg);
	}

	public static String getBankDoesNotExist(CommandSender sender, String bank_name) {
		return WorldEconomyPlugin.PREFIX + "§4" + get(sender, "bank_does_not_exist").replace("%banName%", bank_name);
	}

	public static String getRegisteredCorporation(CommandSender sender, String company_name, long companyID) {
		return WorldEconomyPlugin.PREFIX + "§a" + get(sender, "register_corporation")
				.replace("%companyName%", company_name).replace("%companyID%", String.valueOf(companyID));
	}

	public static String getRegisteredPrivateCompany(CommandSender sender, String company_name, long companyID) {
		return getSuccess(sender, "register_private_company").replace("%companyName%", company_name)
				.replace("%companyID%", String.valueOf(companyID));
	}

	public static String getInvalidCompanyType(CommandSender sender, String companyType) {
		return WorldEconomyPlugin.PREFIX + "§4"
				+ get(sender, "invalid_company_type").replace("%companyType%", companyType);
	}

	public static String getCompanyDoesNotExist(CommandSender sender, String companyName) {
		return WorldEconomyPlugin.PREFIX + "§4"
				+ get(sender, "company_does_not_exist").replace("%companyName%", companyName);
	}

	public static String getNotEnoughSpaceDetailed(CommandSender sender, long available, long needed) {
		return getError(sender, "not_enough_space_details").replace("%needed%", String.valueOf(needed))
				.replace("%available%", String.valueOf(available));
	}

	public static String getNotEnoughMoney(CommandSender sender, double balance, double price) {
		return getError(sender, "not_enough_money").replace("%balance%", String.valueOf(balance)).replace("%price%",
				String.valueOf(price));
	}

	public static String getChatDialog(CommandSender sender, String chatDialogTextID) {
		return WorldEconomyPlugin.PREFIX + get(sender, "msg.chatdialog." + chatDialogTextID);
	}

	public static String getRegisteredSupplyChest(CommandSender sender, String companyName) {
		return getSuccess(sender, "register_supply_chest").replace("%companyName%", companyName);
	}

	public static String getRegisteredSupplyChest(CommandSender sender, Company company) {
		return getRegisteredSupplyChest(sender, company.companyName);
	}

	public static String getRegisteredProduct(CommandSender sender, String productName, String companyName) {
		return getSuccess(sender, "register_product").replace("%productName%", productName).replace("%companyName%",
				companyName);
	}

	public static String getRegisteredPlayerBankAccount(CommandSender sender, String bankAccountName, String bankName) {
		return getSuccess(sender, "register_player_bank_account").replace("%bankAccountName%", bankAccountName)
				.replace("%bankName%", bankName);
	}

	public static String getRegisteredCompanyBankAccount(CommandSender sender, String bankAccountName, String bankName,
			String companyName) {
		return getSuccess(sender, "register_company_bank_account").replace("%bankAccountName%", bankAccountName)
				.replace("%bankName%", bankName).replace("%companyName%", companyName);
	}

	public static String GUI_TITLE_BANK_ACCOUNT(CommandSender sender, String bankAccountName) {
		return get(sender, "gui.title.bank_account").replace("%bankAccountName%", bankAccountName);
	}

	public static String GUI_TITLE_BUY_RESOURCE(CommandSender sender, String resourceName) {
		return get(sender, "gui.title.buy_resource").replace("%resourceName%", resourceName);
	}

	public static String GUI_ITEM_BUY_RESOURCE__SIGN(CommandSender sender, String resourceName, double price) {
		return get(sender, "gui.item.buy_resource.sign").replace("%resourceName%", resourceName)
				.replace("%resourcePrice%", String.valueOf(price));
	}

	public static String GUI_ITEM_BUY_RESOURCE__BUY_AMOUNT(CommandSender sender, long amount) {
		return get(sender, "gui.item.buy_resource.buy_amount").replace("%amount%", String.valueOf(amount));
	}

	public static String GUI_TITLE_EMPLOYEES_FROM_COMPANY(CommandSender sender, Company company) {
		return get(sender, "gui.title.employees_from_company").replace("%companyName%", company.companyName);
	}

	public static String GUI_TITLE_PRODUCTS_FROM_COMPANY(CommandSender sender, Company company) {
		return get(sender, "gui.title.products_from_company").replace("%companyName%", company.companyName);
	}

	public static String GUI_TITLE_RESOURCE(CommandSender sender, CustomItem resource) {
		return get(sender, "gui.title.resource").replace("%resourceName%", resource.item_name);
	}

	public static String GUI_TITLE_SELL_RESOURCE(CommandSender sender, CustomItem resource) {
		return get(sender, "gui.title.sell_resource").replace("%resourceName%", resource.item_name);
	}

}
