package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.Lifesteal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class PlayerMessage {

	private String message;

	public PlayerMessage setPrefix() {
		this.message = message.replaceAll("<prefix>",
				ChatColor.translateAlternateColorCodes('&', Lifesteal.get().getConfig().getString("Settings.Prefix")));
		return this;
	}

	public PlayerMessage setPlaceholder(String placeholder, String replacement) {
		this.message = message.replaceAll(placeholder, replacement);
		return this;
	}

	public PlayerMessage colorize() {
		this.message = ChatColor.translateAlternateColorCodes('&', getMessage());
		return this;
	}

	public PlayerMessage placeholders(Player player) {
		this.message = PlaceholderAPI.setPlaceholders(player, getMessage());
		return this;
	}

	public PlayerMessage configurable() {
		this.message = Lifesteal.get().getConfig().getString(getMessage());
		return this;
	}

	public void console(boolean prefix) {
		if (prefix) {
			Bukkit.getLogger().info("[LifestealCore] " + getMessage());
		}
		Bukkit.getLogger().info(getMessage());
	}

	public void send(Player player) {
		player.sendMessage(getMessage());
	}
}