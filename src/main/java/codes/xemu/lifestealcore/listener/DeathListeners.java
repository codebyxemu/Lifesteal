package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.storage.LifestealProfile;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.MessageBuilder;
import codes.xemu.lifestealcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListeners implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			LifestealProfile playerProfile = LifestealPlugin.get().getStorage().getProfile(player.getUniqueId());
			if (player.getKiller() != null) {
				LifestealProfile killerProfile = LifestealPlugin.get().getStorage().getProfile(player.getKiller().getUniqueId());

				if (playerProfile.getHearts() <= 1) {
					Utils.ban(player);
				}

				LifestealPlugin.get().getHeartsHandler().setHearts(killerProfile, killerProfile.getHearts() + 1);
				LifestealPlugin.get().getHeartsHandler().setHearts(playerProfile, playerProfile.getHearts() - 1);

				new MessageBuilder(ConfigValues.MESSAGES_HEART_STOLEN_FROM.getString())
						.setPlaceholder("<player>", player.getKiller().getName())
						.setPlaceholder("<hearts>", String.valueOf(playerProfile.getHearts() - 1))
						.setPrefix()
						.colorize()
						.send(player);

				new MessageBuilder(ConfigValues.MESSAGES_HEART_STOLEN_TO.getString())
						.setPlaceholder("<player>", player.getName())
						.setPlaceholder("<hearts>", String.valueOf(killerProfile.getHearts() + 1))
						.setPrefix()
						.colorize()
						.send(player.getKiller());

				// TODO: Customizable Title
				player.getKiller().sendTitle("§c§lHEART STOLEN", "§7You stole a heart from: §a<player>".replaceAll("<player>", player.getName()));
			}
		}
	}

}
