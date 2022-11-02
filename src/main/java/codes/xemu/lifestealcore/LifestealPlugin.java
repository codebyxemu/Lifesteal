package codes.xemu.lifestealcore;

import codes.xemu.lifestealcore.command.AdminCommand;
import codes.xemu.lifestealcore.command.HeartsCommand;
import codes.xemu.lifestealcore.handler.HeartsHandler;
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

		this.heartsHandler = new HeartsHandler();

		getCommand("hearts").setExecutor(new HeartsCommand());
		getCommand("admin").setExecutor(new AdminCommand());

		if (ConfigValues.SETTINGS_STORAGE_TYPE.getString().equalsIgnoreCase("flat")) {
			this.storage = new FlatFileStorage();
			new MessageBuilder("Initialized Storage Type: " + storage.getTypeName() + " / " + storage.getIdentifier())
					.console(true);
		}
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static LifestealPlugin get() {
		return instance;
	}
}
