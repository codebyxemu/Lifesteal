package codes.xemu.lifestealcore.command.lifesteal.sub.admin;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.command.SubCommand;
import codes.xemu.lifestealcore.profile.Profile;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LifestealAdminRevive implements SubCommand {
	@Override
	public void execute(Player player) {

	}

	public void execute(Player player, String[] args) {
		String userInput = args[0];
		Player user = Bukkit.getPlayer(userInput);
		Profile profile = new Profile(user.getUniqueId());

		// If bans are enabled
		if (Lifesteal.get().getConfig().getBoolean("Ban.Enabled")) {
			Lifesteal.get().getServer().dispatchCommand(Lifesteal.get().getServer().getConsoleSender(),
					Lifesteal.get().getConfig().getString("Ban.Unban-Command"));
		}

		profile.setHearts(Lifesteal.get().getConfig().getInt("Settings.StartingHearts"));

		new PlayerMessage("Messages.Revived_By_Admin")
				.configurable()
				.setPrefix()
				.colorize()
				.send(user);

		new PlayerMessage("Messages.Revived_For_Admin")
				.configurable()
				.setPrefix()
				.setPlaceholder("<player>", user.getName())
				.colorize()
				.send(player);

	}
}
