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

	public static final String ERROR_NOT_ENOUGH_ARGUMENTS = "not_enough_arguments";
	public static final String ERROR_COMMAND_NOT_FOUND = "command_not_found";
	public static final String ERROR_INVALID_ARGUMENT = "invalid_argument";
	public static final String ERROR_INTERNAL = "internal";
	public static final String ERROR_ACCESSING_FUTURE = "accessing_future";
	public static final String ERROR_INSUFFICIENT_PERMISSION = "insufficient_permission";
	public static final String ERROR_NOT_A_PLAYER = "not_a_player";
	public static final String ERROR_HAND_EMPTY = "hand_empty";
	public static final String ERROR_ILLEGAL_ITEM = "illegal_item";
	public static final String ERROR_PRODUCT_ALREADY_EXISTS = "product_already_exists";
	public static final String ERROR_INVALID_NUMBER = "invalid_number";
	public static final String ERROR_LOOK_AT_CHEST = "look_at_chest";
	public static final String ERROR_MOVE_CLOSER = "move_closer";
	public static final String ERROR_EMPTY_HAND = "empty_hand";
	public static final String ERROR_BANK_ACCOUNT_DOES_NOT_EXIST = "bank_account_does_not_exist";
	public static final String ERROR_NOT_ENOUGH_SPACE = "not_enough_space";

	public static final String SUCCESS_REGISTER_BANK = "register_bank";

	public static final String CHATDIALOG_REGISTER_PRODUCT_NAME = "register_product_name";
	public static final String CHATDIALOG_REGISTER_PRODUCT_PRICE = "register_product_price";
	public static final String CHATDIALOG_REGISTER_PRODUCT_INVALID_PRICE = "register_product_invalid_price";

	public static final String GUI_ITEM_BACK = "gui.item.back";

	public static final String GUI_TITLE_BANK_ACCOUNT_CREDITS = "gui.title.bank_account_credits";

	public static final String GUI_ITEM_BANK_ACCOUNT__CREDITS = "gui.item.bank_account.credits";

	public static final String GUI_TITLE_BANK_ACCOUNTS = "gui.title.bank_accounts";

	public static final String GUI_TITLE_BANKS = "gui.title.banks";

	public static final String GUI_ITEM_BUY_RESOURCE__CUSTOM_AMOUNT = "gui.item.buy_resource.custom_amount";

	public static final String GUI_TITLE_CHOOSE_BANK_ACCOUNT = "gui.title.choose_bank_account";

	public static final String GUI_TITLE_COMPANIES = "gui.title.companies";

	public static final String GUI_ITEM_COMPANY__PRODUCTS = "gui.item.company.products";
	public static final String GUI_ITEM_COMPANY__REGISTER_PRODUCT = "gui.item.company.register_product";
	public static final String GUI_ITEM_COMPANY__SALES = "gui.item.company.sales";
	public static final String GUI_ITEM_COMPANY__EMPLOYEES = "gui.item.company.employees";

	public static final String GUI_TITLE_CREATE_BANK_ACCOUNT = "gui.title.create_bank_account";

	public static final String GUI_TITLE_MAIN_MENU = "gui.title.main_menu";
	public static final String GUI_ITEM_MAIN_MENU__MAILBOX = "gui.item.main_menu.mailbox";
	public static final String GUI_ITEM_MAIN_MENU__WRITE_MAIL = "gui.item.main_menu.write_mail";

	public static final String GUI_TITLE_RESEARCH = "gui.title.research";

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
		return get(sender, "age." + age.name().toLowerCase());
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
