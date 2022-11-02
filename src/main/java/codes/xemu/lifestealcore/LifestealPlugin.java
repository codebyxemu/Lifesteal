package codes.xemu.lifestealcore;

import codes.xemu.lifestealcore.command.AdminCommand;
import codes.xemu.lifestealcore.command.HeartsCommand;
import codes.xemu.lifestealcore.handler.HeartsHandler;
import codes.xemu.lifestealcore.listener.DeathListeners;
import codes.xemu.lifestealcore.listener.InventoryListeners;
import codes.xemu.lifestealcore.listener.PlayerListeners;
import codes.xemu.lifestealcore.storage.FlatFileStorage;
import codes.xemu.lifestealcore.storage.Storage;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.MessageBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LifestealPlugin extends JavaPlugin {

	private static LifestealPlugin instance;

	private Storage storage;

	private HeartsHandler heartsHandler;

	@Override
	public void onEnable() {
		instance = this;

		new MessageBuilder("Loading plugin...").console(true);

		config();
		commands();
		listeners();
		storage();

		this.heartsHandler = new HeartsHandler();

		new MessageBuilder("Finished loading plugin...").console(true);
		new MessageBuilder("Enjoy the plugin!").console(true);
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static LifestealPlugin get() {
		return instance;
	}

	private void config() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void commands() {
		getCommand("hearts").setExecutor(new HeartsCommand());
		getCommand("admin").setExecutor(new AdminCommand());
	}

	private void listeners() {
		getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
		getServer().getPluginManager().registerEvents(new InventoryListeners(), this);
		getServer().getPluginManager().registerEvents(new DeathListeners(), this);
	}

	private void storage() {
		if (ConfigValues.SETTINGS_STORAGE_TYPE.getString().equalsIgnoreCase("flat")) {
			this.storage = new FlatFileStorage();
			new MessageBuilder("Initialized Storage Type: " + storage.getTypeName() + " / " + storage.getIdentifier())
					.console(true);
		}
	}
}
