package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	protected Lifesteal plugin = Lifesteal.get();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		new Profile(player.getUniqueId());
	}

}
