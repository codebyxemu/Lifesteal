package codes.xemu.lifestealcore;

import codes.xemu.lifestealcore.command.commands.AdminCommand;
import codes.xemu.lifestealcore.command.commands.HeartsCommand;
import codes.xemu.lifestealcore.listener.DeathEvent;
import codes.xemu.lifestealcore.listener.PlayerJoin;
import codes.xemu.lifestealcore.listener.PlayerLeave;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Lifesteal extends JavaPlugin {

	private static Lifesteal instance;

	@Override
	public void onEnable() {
		instance = this;

		new HeartsCommand();
		new AdminCommand();

		Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);

		getConfig().options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static Lifesteal get() {
		return instance;
	}
}
