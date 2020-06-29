package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AIProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Company;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Employee;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeeAI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeePlayer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.BlockLib;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUI;

public class EmployeesFromCompanyGUI extends WEGUI {

	public EmployeesFromCompanyGUI(WEGUI parent, Company company, Player player) {
		super(parent, new GUIItem[] {}, Lang.GUI_TITLE_EMPLOYEES_FROM_COMPANY(player, company), player);

		List<GUIItem> items = new ArrayList<GUIItem>();
		int slot = 9;

		items.add(new GUIItem(0, 4, mkItem(Material.SIGN, Lang.GUI_TITLE_EMPLOYEES_FROM_COMPANY(player, company))) {
			@Override
			public void event(InventoryClickEvent event) {
			}
		});

		try {
			List<Employee> employees = WEDB.getEmployeesFromCompany(company);
			for (Employee employee : employees) {
				if (employee instanceof EmployeePlayer) {
					items.add(new GUIItem(slot,
							mkItem(BlockLib.PLAYER,
									Bukkit.getOfflinePlayer(((EmployeePlayer) employee).playerUUID).getName(),
									new String[] { Lang.get(player, Lang.OTHER_PLAYER) })) {
						@Override
						public void event(InventoryClickEvent event) {
						}
					});
				} else if (employee instanceof EmployeeAI) {
					AIProfile ai = WEDB.getAI(((EmployeeAI) employee).aiID);
					items.add(new GUIItem(slot, BlockLib.ai(ai.username)) {
						@Override
						public void event(InventoryClickEvent event) {
						}
					});
				}
				slot++;

				setItems(convert(items));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}
	}

}
