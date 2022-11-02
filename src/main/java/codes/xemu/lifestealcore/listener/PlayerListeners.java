package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.handler.HeartsHandler;
import codes.xemu.lifestealcore.storage.LifestealProfile;
import codes.xemu.lifestealcore.storage.Storage;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.MessageBuilder;
import codes.xemu.lifestealcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

	protected LifestealPlugin plugin = LifestealPlugin.get();
	protected Storage storage = plugin.getStorage();
	protected HeartsHandler handler = plugin.getHeartsHandler();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if (plugin.getConfig().get("Storage." + player.getUniqueId()) != null) {
			if (plugin.getConfig().getBoolean("Storage." + player.getUniqueId() + ".banned")) {
				LifestealProfile profile = storage.getProfile(player.getUniqueId());
				profile.setHearts(ConfigValues.START_HEARTS_UNBAN.getInt());
				storage.saveData(profile);

				plugin.getConfig().set("Storage." + player.getUniqueId() + ".banned", false);
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity();
		LifestealProfile profile = storage.getProfile(player.getUniqueId());

		if (profile.getHearts() == 1) {
			Utils.ban(player);
		}

		if (player.getKiller() != null) {
			handler.setHearts(profile, profile.getHearts() - 1);
		}

		new MessageBuilder(ConfigValues.MESSAGES_HEART_STOLEN_FROM.getString())
				.setPrefix()
				.setPlaceholder("<player>", player.getKiller().getName())
				.setPlaceholder("<hearts>", String.valueOf(profile.getHearts()))
				.colorize()
				.send(player);

		new MessageBuilder(ConfigValues.MESSAGES_HEART_STOLEN_TO.getString())
				.setPrefix()
				.setPlaceholder("<player>", player.getName())
				.setPlaceholder("<hearts>", String.valueOf(profile.getHearts()))
				.colorize()
				.send(player.getKiller());

	}

}
