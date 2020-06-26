package io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;

public class RegisterProductChatDialog extends ChatDialog {

	public RegisterProductChatDialog(Player player, CustomItem customItem, Company company) {
		super(player);
		player.sendMessage(Lang.getMsg(player, Lang.MSG_REGISTER_PRODUCT_NAME));
		this.company = company;
	}

	private Company company;
	private CustomItem customItem;

	private int stage = 0;
	private String productName;

	@Override
	public void recieve(String msg) {
		if (stage == 0) {
			try {
				Product product = WEDB.getProduct(company.ID, msg);
				if (product == null) {
					productName = msg;
				} else {
					reply(Lang.getError(player, Lang.ERROR_PRODUCT_ALREADY_EXISTS));
					close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				reply(Lang.getError(player, Lang.ERROR_INTERNAL));
				close();
			}
			stage = 1;
			reply(Lang.getMsg(player, Lang.MSG_REGISTER_PRODUCT_PRICE));
		} else if (stage == 1) {
			try {
				double price = Double.parseDouble(msg);
				try {
					WEDB.registerProduct(company.ID, productName, price, customItem);
				} catch (SQLException e) {
					e.printStackTrace();
					reply(Lang.getError(player, Lang.ERROR_INTERNAL));
					close();
				}
			} catch (NumberFormatException e) {
				reply(Lang.getError(player, Lang.MSG_REGISTER_PRODUCT_INVALID_PRICE));
			}
		}
	}

}
