package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.Lifesteal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	protected Lifesteal plugin = Lifesteal.get();

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
	}

}
