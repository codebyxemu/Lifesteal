package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.handler.HeartsHandler;
import codes.xemu.lifestealcore.storage.LifestealProfile;
import codes.xemu.lifestealcore.storage.Storage;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.Utils;
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

		boolean hasKiller = player.getKiller() != null;
		Player killer;
		if (hasKiller) {
			killer = player.getKiller();
		}

		if (ConfigValues.WORLD_SETTINGS_INSTANT_ELIMINATION_WORLDS.getStringList().contains(player.getWorld().getName())) {
			Utils.ban(player);
		}

		if (ConfigValues.WORLD_SETTINGS_DISABLED_WORLDS.getStringList().contains(player.getWorld().getName())) {
			// do nothing
		}

		
	}

}
