package codes.xemu.lifestealcore.command.lifesteal.sub;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.command.SubCommand;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.entity.Player;

public class LifestealHelp implements SubCommand {
	@Override
	public void execute(Player player) {
		for (String string : Lifesteal.get().getConfig().getStringList("Messages.Lifesteal_Help")) {
			PlayerMessage playerMessage = new PlayerMessage(string).setPrefix().setPlaceholder("<version>",
					Lifesteal.get().getDescription().getVersion()).colorize();
			playerMessage.send(player);
		}
	}
}
