package codes.xemu.lifestealcore;

import codes.xemu.lifestealcore.command.hearts.HeartsCommand;
import codes.xemu.lifestealcore.command.hearts.HeartsGUI;
import codes.xemu.lifestealcore.command.lifesteal.LifestealCommand;
import codes.xemu.lifestealcore.inventory.ProfileInventory;
import codes.xemu.lifestealcore.listener.DeathEvent;
import codes.xemu.lifestealcore.listener.PlayerJoin;
import codes.xemu.lifestealcore.listener.PlayerLeave;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Lifesteal extends JavaPlugin {

	private static Lifesteal instance;

	public static PlayerMessage INVALID_USAGE_MESSAGE;

	@Override
	public void onEnable() {
		instance = this;

		INVALID_USAGE_MESSAGE = new PlayerMessage("Messages.Invalid_USAGE").configurable().setPrefix().colorize();

		new LifestealCommand();
		new HeartsCommand();

		Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new HeartsGUI(), this);

		getConfig().options().copyDefaults(true);
		saveConfig();

		getLogger().info("Successfully started LifestealPRO.");
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static Lifesteal get() {
		if (instance == null)
			throw new NullPointerException("[LifestealPro] (CRITICAL) The instance is a null.");
		return instance;
	}
}
