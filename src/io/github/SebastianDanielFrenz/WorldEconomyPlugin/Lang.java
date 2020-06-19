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
				if (line.startsWith(msgID)) {
					br.close();
					isr.close();
					return line.split("[=]")[1];
				}
			}
		} catch (IOException e2) {
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
				return msgID;
			}
			System.out.println("Could not find " + msgID + " in " + lang + ".lang!");
			return msgID;
		}
	}

	public static String ERROR_NOT_ENOUGH_ARGUMENTS = "not_enough_arguments";
	public static String ERROR_COMMAND_NOT_FOUND = "command_not_found";
	public static String ERROR_INVALID_ARGUMENT = "invalid_argument";
	public static String ERROR_INTERNAL = "internal_error";
	public static String ERROR_ACCESSING_FUTURE = "accessing_future";
	public static String ERROR_INSUFFICIENT_PERMISSION = "insufficient_permission";
	
	public static String SUCCESS_REGISTER_BANK = "register_bank";

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
		return WorldEconomyPlugin.PREFIX + "�4" + get(sender, "msg.error." + msg);
	}

	public static String getSuccess(CommandSender sender, String msg) {
		return WorldEconomyPlugin.PREFIX + "�a" + get(sender, "msg.success." + msg);
	}

}
