package codes.xemu.lifestealcore.command.commands;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.command.LifestealCommand;
import codes.xemu.lifestealcore.profile.Profile;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminCommand extends LifestealCommand {
	public AdminCommand() {
		super("lsadmin", "Lifesteal Administrator Command", new String[]{"admin"});
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			new PlayerMessage("&8&m+-----------( &4&lLIFESTEAL-ADMIN &8&m)-----------+").colorize().send(player);
			new PlayerMessage("&c/lsadmin give <username> <hearts> &8* &fGrants more hearts.").colorize().send(player);
			new PlayerMessage("&c/lsadmin remove <username> <hearts> &8* &fRemoves hearts.").colorize().send(player);
			new PlayerMessage("&c/lsadmin revive <username> &8* &fRemoves a current ban and resets heart count.").colorize().send(player);
			new PlayerMessage("&8&m+-----------( &4&lLIFESTEAL-ADMIN &8&m)-----------+").colorize().send(player);
		} else if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("give")) {
				if (Bukkit.getPlayer(args[1].toLowerCase()) == null) {
					new PlayerMessage("Error: Could not find a player by the name.");
					return;
				}
				if (Integer.parseInt(args[2]) == 0) {
					// Invalid number message here
					return;
				}
				executeGive(player, Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
			} else if(args[0].equalsIgnoreCase("remove")) {
				if (Bukkit.getPlayer(args[1].toLowerCase()) == null) {
					// Invalid player message
					return;
				}
				if (Integer.parseInt(args[2]) == 0) {
					// Invalid number message here
					return;
				}
				executeRemove(player, Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
			}
		} else {
			new PlayerMessage("&cUnknown Command, please type &e/help").colorize().send(player);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		return null;
	}

	private void executeGive(Player sender, Player user, int amount) {
		if (amount == 0) {
			new PlayerMessage("&cError: Invalid amount: &e" + amount).colorize().send(sender);
			return;
		}

		if (user == null) {
			new PlayerMessage("Error: Could not find a player by the name.");
		}


		Profile profile = new Profile(user.getUniqueId());
		profile.setHearts(profile.getHearts() + amount);
		profile.update();

		new PlayerMessage("&c[ADMIN] &f" + user.getName() + " now has " + profile.getHeartsAsString() + " hearts").colorize().send(sender);
		new PlayerMessage("&c[ADMIN] &fAn administrator changed your hearts to: &c" + profile.getHeartsAsString()).colorize().send(user);
	}

	private void executeRemove(Player sender, Player user, int amount) {
		if (amount == 0) {
			new PlayerMessage("&cError: Invalid amount: &e" + amount).colorize().send(sender);
			return;
		}

		if (user == null) {
			new PlayerMessage("Error: Could not find a player by the name.");
		}


		Profile profile = new Profile(user.getUniqueId());
		profile.setHearts(profile.getHearts() - amount);
		profile.update();

		new PlayerMessage("&c[ADMIN] &f" + user.getName() + " now has " + profile.getHeartsAsString() + " hearts").colorize().send(sender);
		new PlayerMessage("&c[ADMIN] &fAn administrator changed your hearts to: &c" + profile.getHeartsAsString()).colorize().send(user);
	}
}
