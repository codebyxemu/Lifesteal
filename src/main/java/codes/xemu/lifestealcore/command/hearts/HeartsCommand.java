package codes.xemu.lifestealcore.command.hearts;

import codes.xemu.lifestealcore.command.ChatCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HeartsCommand extends ChatCommand {
	public HeartsCommand() {
		super("hearts", "View and manage yours hearts", new String[]{"heart"});
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		new HeartsGUI().open(player);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		return null;
	}
}
