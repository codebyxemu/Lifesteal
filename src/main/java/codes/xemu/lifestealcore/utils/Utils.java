package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.LifestealPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

	public static String color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	public static void ban(Player player) {
		LifestealPlugin.get().getConfig().set("Storage." + player.getUniqueId() + ".banned", true);
		LifestealPlugin.get().saveConfig();

		String banCommand = ConfigValues.BANS_BAN_COMMAND.getString()
				.replaceAll("<player>", player.getName());

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), banCommand);
	}

	public static void unban(Player player) {
		String banCommand = ConfigValues.BANS_UNBAN_COMMAND.getString()
				.replaceAll("<player>", player.getName());

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), banCommand);
	}

}
