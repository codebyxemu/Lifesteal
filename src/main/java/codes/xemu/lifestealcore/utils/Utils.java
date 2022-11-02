package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.LifestealPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

	public static String color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	public static void ban(Player player) {
		LifestealPlugin.get().getConfig().set("Storage." + player.getUniqueId() + ".banned", true);
		LifestealPlugin.get().saveConfig();
	}

}
