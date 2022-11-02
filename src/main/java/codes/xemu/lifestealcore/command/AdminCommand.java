package codes.xemu.lifestealcore.command;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.storage.LifestealProfile;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.MessageBuilder;
import codes.xemu.lifestealcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.text.ViewFactory;

public class AdminCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;

		if (!player.hasPermission("lifesteal.admin")) {
			new MessageBuilder(ConfigValues.MESSAGES_NO_PERMISSION.getString())
					.setPrefix()
					.setPlaceholder("<permission>", "lifesteal.admin")
					.colorize()
					.send(player);
			return true;
		}

		if (args.length == 0) {
			for (String message : ConfigValues.MESSAGES_ADMIN_HELP.getStringList()) {
				new MessageBuilder(message)
						.setPrefix().colorize().send(player);
			}
			return true;
		} else if (args.length >= 1) {
			String sub = args[0].toLowerCase();

			if (sub.equalsIgnoreCase("set")) {
				if (args.length != 3) {
					new MessageBuilder(ConfigValues.MESSAGES_INVALID_USAGE.getString())
							.setPrefix()
							.colorize()
							.send(player);
					return true;
				}
				Player target;
				if (Bukkit.getPlayer(args[1]) != null) {
					target = Bukkit.getPlayer(args[1]);
				} else {
					new MessageBuilder(ConfigValues.MESSAGES_INVALID_TARGET.getString())
							.setPrefix()
							.colorize()
							.send(player);
					return true;
				}
				int hearts;
				if (Integer.parseInt(args[2]) != 0) {
					hearts = Integer.parseInt(args[2]);
				} else {
					new MessageBuilder(ConfigValues.MESSAGES_INVALID_USAGE.getString())
							.setPrefix()
							.colorize()
							.send(player);
					return true;
				}

				LifestealProfile profile = LifestealPlugin.get().getStorage().getProfile(target.getUniqueId());
				profile.setHearts(hearts);

				LifestealPlugin.get().getStorage().saveData(profile);

				new MessageBuilder(ConfigValues.MESSAGES_ADMIN_FROM_SET.getString())
						.setPrefix()
						.setPlaceholder("<player>", target.getName())
						.setPlaceholder("<hearts>", String.valueOf(hearts))
						.colorize()
						.send(player);

				new MessageBuilder(ConfigValues.MESSAGES_ADMIN_TO_SET.getString())
						.setPrefix()
						.setPlaceholder("<hearts>", String.valueOf(hearts))
						.colorize()
						.send(target);

				return true;
			} else if (sub.equalsIgnoreCase("revive")) {
				if (args.length != 2) {
					new MessageBuilder(ConfigValues.MESSAGES_INVALID_USAGE.getString())
							.setPrefix()
							.colorize()
							.send(player);
					return true;
				}

				Player target;
				if (Bukkit.getPlayer(args[1]) != null) {
					target = Bukkit.getPlayer(args[1]);
				} else {
					new MessageBuilder(ConfigValues.MESSAGES_INVALID_TARGET.getString())
							.setPrefix()
							.colorize()
							.send(player);
					return true;
				}

				LifestealPlugin.get().getConfig().set("Storage." + target.getUniqueId() + ".banned", false);
				LifestealPlugin.get().saveConfig();

				LifestealProfile profile = LifestealPlugin.get().getStorage().getProfile(target.getUniqueId());
				profile.setHearts(ConfigValues.START_HEARTS_UNBAN.getInt());

				LifestealPlugin.get().getStorage().saveData(profile);

				new MessageBuilder(ConfigValues.MESSAGES_ADMIN_REVIVE.getString())
						.setPrefix()
						.setPlaceholder("<player>", target.getName())
						.colorize()
						.send(player);

				Utils.unban(player);

				return true;
			}
		}
		return true;
	}
}