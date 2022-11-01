package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.Lifesteal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

@AllArgsConstructor
@Getter
public class ConfigurableMessage {

	private String path;

	public String getMessage() {
		String msg = Lifesteal.get().getConfig().getString(getPath());

		msg = ChatColor.translateAlternateColorCodes('&', msg);

		return msg;
	}

}
