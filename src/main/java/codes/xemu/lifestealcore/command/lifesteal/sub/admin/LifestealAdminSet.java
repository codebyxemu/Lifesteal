package codes.xemu.lifestealcore.command.lifesteal.sub.admin;

import codes.xemu.lifestealcore.command.SubCommand;
import codes.xemu.lifestealcore.profile.Profile;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LifestealAdminSet implements SubCommand {
	@Override
	public void execute(Player player) {

	}

	public void execute(Player player, String[] args) {
		String userInput = args[0];
		String amountInput = args[1];
		Player user = Bukkit.getPlayer(userInput);
		int amount = Integer.parseInt(amountInput);

		Profile profile = new Profile(user.getUniqueId());
		profile.setHearts(amount);

		new PlayerMessage("Messages.Hearts_Set_By_Admin")
				.configurable()
				.setPrefix()
				.setPlaceholder("<player>", user.getName())
				.setPlaceholder("<hearts>", profile.getHeartsAsString())
				.colorize()
				.send(user);

		new PlayerMessage("Messages.Hearts_Set_For_Admin")
				.configurable()
				.setPrefix()
				.setPlaceholder("<hearts>", profile.getHeartsAsString())
				.colorize()
				.send(player);
	}
}
