package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.LifestealPlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class MessageBuilder {

	private String message;

	public MessageBuilder setPrefix() {
		this.message = message.replaceAll("<prefix>",
				ChatColor.translateAlternateColorCodes('&', LifestealPlugin.get().getConfig().getString("Settings.General.Prefix")));
		return this;
	}

	public MessageBuilder setPlaceholder(String placeholder, String replacement) {
		this.message = message.replaceAll(placeholder, replacement);
		return this;
	}

	public MessageBuilder colorize() {
		this.message = ChatColor.translateAlternateColorCodes('&', getMessage());
		return this;
	}

	public MessageBuilder placeholders(Player player) {
		this.message = PlaceholderAPI.setPlaceholders(player, getMessage());
		return this;
	}

	public MessageBuilder configurable() {
		this.message = LifestealPlugin.get().getConfig().getString(getMessage());
		return this;
	}

	public void console(boolean prefix) {
		ConsoleCommandSender s = Bukkit.getConsoleSender();
		if (prefix) {
			s.sendMessage("§7[§cLifesteal§4Pro§7] §r" + getMessage());
		}
		s.sendMessage(getMessage());
	}

	public void send(Player player) {
		player.sendMessage(getMessage());
	}
}