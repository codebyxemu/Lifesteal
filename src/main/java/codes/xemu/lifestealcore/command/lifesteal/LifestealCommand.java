package codes.xemu.lifestealcore.command.lifesteal;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.command.ChatCommand;
import codes.xemu.lifestealcore.command.lifesteal.sub.LifestealHelp;
import codes.xemu.lifestealcore.command.lifesteal.sub.admin.LifestealAdminRevive;
import codes.xemu.lifestealcore.command.lifesteal.sub.admin.LifestealAdminSet;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LifestealCommand extends ChatCommand {
	public LifestealCommand() {
		super("lifesteal", "Lifesteal Command", new String[]{"ls"});
		setPermission("lifestealpro.command.lifesteal");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			new LifestealHelp().execute(player);
		} else if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("admin")) {
				String adminSubCommand = args[1].toLowerCase();
				if (adminSubCommand.equalsIgnoreCase("set")) {
					if (args.length == 4 && args[2] != null) {
						new LifestealAdminSet().execute(player, new String[]{args[2], args[3]});
					} else {
						Lifesteal.INVALID_USAGE_MESSAGE.send(player);
					}
				} else if (adminSubCommand.equalsIgnoreCase("revive")) {
					if (args.length == 3 && args[2] != null) {
						new LifestealAdminRevive().execute(player, new String[]{args[2]});
					} else {
						Lifesteal.INVALID_USAGE_MESSAGE.send(player);
					}
				} else {
					Lifesteal.INVALID_USAGE_MESSAGE.send(player);
				}
			}
		}

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		return null;
	}

}
