package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.utils.PlayerMessage;
import codes.xemu.lifestealcore.handler.HeartsHandler;
import codes.xemu.lifestealcore.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player victimPlayer = event.getEntity();
		Profile victimProfile = new Profile(event.getEntity().getUniqueId());

		if (victimPlayer.getKiller() != null) {
			Player killerPlayer = event.getEntity().getKiller();
			Profile killerProfile = new Profile(killerPlayer.getUniqueId());

			HeartsHandler.stealHeart(killerProfile, victimProfile);

			new PlayerMessage("&c<murderer> stole a heart from you! (You now have <hearts> remaining)")
					.setPlaceholder("<murderer>", victimPlayer.getKiller().getName())
					.setPlaceholder("<hearts>", victimProfile.getHeartsAsString())
					.setPrefix()
					.colorize()
					.send(victimPlayer);

			new PlayerMessage("&aYou stole a heart from <victim>! You now have <hearts> hearts.")
					.setPlaceholder("<hearts>", killerProfile.getHeartsAsString())
					.setPlaceholder("<victim>", victimPlayer.getName())
					.setPrefix()
					.colorize()
					.send(victimPlayer.getKiller());

			// TODO: Teleport to spawn point (victim)
		}
	}

}
