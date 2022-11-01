package codes.xemu.lifestealcore.command.commands;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import codes.xemu.lifestealcore.command.LifestealCommand;
import codes.xemu.lifestealcore.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HeartsCommand extends LifestealCommand {
	public HeartsCommand() {
		super("hearts", "Hearts Command", new String[]{"heart"});
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		Profile profile = new Profile(player.getUniqueId());

		if (args.length == 0) {
			new PlayerMessage("&8&m+-----------( &c&lLIFESTEAL &8&m)-----------+").colorize().send(player);
			new PlayerMessage("&7Lifesteal is currently running on version: &a<version>").colorize().setPlaceholder("<version>", Lifesteal.get().getDescription().getVersion()).send(player);
			new PlayerMessage("&c/hearts view &8* &fView your current hearts.").colorize().send(player);
			new PlayerMessage("&8&m+-----------( &c&lLIFESTEAL &8&m)-----------+").colorize().send(player);
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("view")) {
				new PlayerMessage("&aYou currently have <hearts> hearts.")
						.setPlaceholder("<hearts>", profile.getHeartsAsString())
						.colorize()
						.send(player);
			} else {
				new PlayerMessage("&cUnknown Command, please type &e/help").colorize().send(player);
			}
		} else {
			new PlayerMessage("&cUnknown Command, please type &e/help").colorize().send(player);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		return null;
	}
}
