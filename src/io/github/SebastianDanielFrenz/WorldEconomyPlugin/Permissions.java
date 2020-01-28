package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class Permissions {

	public static final String[] LIST = new String[] { "list" };

	public static final String[] REGISTER_COMPANY = new String[] { "register.*", "register.company", "user" };
	public static final String[] REGISTER_BANK = new String[] { "register.*", "register.bank" };
	public static final String[] REGISTER_SUPPLY_CHEST = new String[] { "register.*", "register.supply_chest", "user" };
	public static final String[] REGISTER_BANK_ACCOUNT = new String[] { "register.*", "register.bank_account", "user" };
	public static final String[] REGISTER_PRODUCT = new String[] { "register.*", "register.product", "user" };

	public static final String[] SIGN_SHOP_CREATE = new String[] { "sign.*", "sign.shop.*", "sign.shop.create" };
	public static final String[] SIGN_SHOP_USE = new String[] { "sign.*", "sign.shop.*", "sign.shop.use" };

	public static final String[] MODIFY_BANK_ACCOUNT_BALANCE = new String[] { "modify.*", "modify.bank_account.*",
			"modify.bank_account.balance" };
	public static final String[] MODIFY_BANK_ACCOUNT_NAME = new String[] { "modify.*", "modify.bank_account.*",
			"modify.bank_account.name", "user" };
	public static final String[] MODIFY_COMPANY_BANK_ACCOUNTS_REGISTER = new String[] { "modify.*", "modify.company.*",
			"modify.company.bank_account.*", "modify.company.bank_account.register" };
}
