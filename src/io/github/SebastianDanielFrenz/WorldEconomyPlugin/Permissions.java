package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class Permissions {

	public static final String[] ADMIN_ACCESS = new String[] {};

	public static final String[] LIST = new String[] { "list" };

	public static final String[] REGISTER_COMPANY_CMD = new String[] { "register.*", "register.company", "user" };
	public static final String[] REGISTER_BANK_CMD = new String[] { "register.*", "register.bank" };
	public static final String[] REGISTER_SUPPLY_CHEST_CMD = new String[] { "register.*", "register.supply_chest",
			"user" };
	public static final String[] REGISTER_BANK_ACCOUNT_CMD = new String[] { "register.*", "register.bank_account",
			"user" };
	public static final String[] REGISTER_PRODUCT_CMD = new String[] { "register.*", "register.product", "user" };

	public static final String[] SIGN_SHOP_CREATE = new String[] { "sign.*", "sign.shop.*", "sign.shop.create" };
	public static final String[] SIGN_SHOP_USE = new String[] { "sign.*", "sign.shop.*", "sign.shop.use" };

	public static final String[] MANAGE_BANK_ACCOUNT_BALANCE = new String[] { "manage.*", "manage.bank_account.*",
			"manage.bank_account.balance" };
	public static final String[] MANAGE_BANK_ACCOUNT_NAME_CMD = new String[] { "manage.*", "manage.bank_account.*",
			"manage.bank_account.name", "user" };
	public static final String[] MANAGE_BANK_ACCOUNT_CREDIT_TAKE_CMD = new String[] { "manage.*",
			"manage.bank_account.*", "manage.bank_account.credit.*", "manage.bank_account.credit.take", "user" };
	public static final String[] MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER_CMD = new String[] { "manage.*",
			"manage.company.*", "manage.company.bank_account.*", "manage.company.bank_account.register" };
	public static final String[] MANAGE_COMPANY_EMPLOY = new String[] { "manage.*", "manage.company.*",
			"manage.company.employ" };

	public static final String[] MANAGE_COMPANY_MAIL_READ = new String[] { "manage.*", "manage.company.*",
			"manage.company.mail.*", "manage.company.mail.read" };
	public static final String[] MANAGE_COMPANY_MAIL_REMOVE = new String[] { "manage.*", "manage.company.*",
			"manage.company.mail.*", "manage.company.mail.remove" };

	public static final String[] BENCHMARK = new String[] { "benchmark" };

	public static final String[] MAIL_READ = new String[] { "mail.*", "mail.read", "user" };
	public static final String[] MAIL_REMOVE = new String[] { "mail.*", "mail.remove", "user" };

	public static final String[] ITEM_GIVE_CMD = new String[] { "item.*", "item.give" };
	public static final String[] BLOCK_SET_CMD = new String[] { "block.*", "block.set" };

	public static final String[] SCHEM_SAVE = new String[] { "schem.*", "schem.save" };
	public static final String[] SCHEM_LOAD = new String[] { "schem.*", "schem.load" };

	public static final String[] ASCEND_SELF = new String[] { "ascend.self", "ascend.*" };
	public static final String[] ASCEND_OTHERS = new String[] { "ascend.others", "ascend.*" };

	public static final String[] PERFORMANCE = new String[] { "performance" };
	public static final String[] RESTART_THREADS = new String[] { "restartthreads" };

	public static final String[] KILL = new String[] { "kill" };
}
