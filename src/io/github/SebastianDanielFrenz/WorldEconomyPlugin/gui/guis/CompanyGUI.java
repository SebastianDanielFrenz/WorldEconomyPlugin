package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.RegisterProductChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotImplementedException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.ResearchItemChooserEvent;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class CompanyGUI extends WEGUI {

	public CompanyGUI(WEGUI parent, Company company, Player player) {
		super(parent, new GUIItem[] {}, company.companyName, player);

		CompanyGUI _this = this;

		List<GUIItem> items = new ArrayList<GUIItem>();

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, company.companyName)) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});
		items.add(new GUIItem(1, 0, mkItem(Material.WOOL, 1, 1, Lang.getGuiItemCompany_Products(player))) {
			@Override
			public void event(InventoryClickEvent event) {
				new ProductsFromCompanyGUI(_this, company, player).openInventory();
			}
		});
		items.add(new GUIItem(2, 0, mkItem(Material.WOOL, 1, 13, Lang.getGuiItemCompany_RegisterProduct(player))) {

			@Override
			public void event(InventoryClickEvent event) {
				new ChooseResearchedItemGUI(_this,
						Lang.getGuiItemCompany_RegisterProduct(player) + " - " + company.companyName, player,
						new ResearchItemChooserEvent() {

							@Override
							public boolean filter(ResearchItem researchItem) {
								return researchItem.getResearchableObject() instanceof CustomItem;
							}

							@Override
							public void event(ResearchItem researchItem) {
								new RegisterProductChatDialog(player, (CustomItem) researchItem.getResearchableObject(),
										company);
							}
						}).openInventory();
			}
		});
		items.add(new GUIItem(1, 1, mkItem(Material.WOOL, 1, 4, Lang.getGuiItemCompany_Sales(player))) {
			@Override
			public void event(InventoryClickEvent event) {
				throw new NotImplementedException();
			}
		});
		items.add(new GUIItem(1, 2, mkItem(Material.WOOL, 1, 5, Lang.getGuiItemCompany_Employees(player))) {
			@Override
			public void event(InventoryClickEvent event) {
				new EmployeesFromCompanyGUI(_this, company, player).openInventory();
			}
		});

		setItems(convert(items));
	}

}
