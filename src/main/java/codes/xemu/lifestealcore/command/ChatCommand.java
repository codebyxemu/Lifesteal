package codes.xemu.lifestealcore.command;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class ChatCommand extends BukkitCommand {

	public ChatCommand(String name, String description, String[] aliases) {
		super(name);

		PlayerMessage permissionMessage = new PlayerMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command.");

		this.setDescription(description);
		this.setAliases(Arrays.asList(aliases));
		this.setPermissionMessage(permissionMessage.getMessage());

		try {
			Field field = Lifesteal.get().getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			CommandMap map = (CommandMap) field.get(Lifesteal.get().getServer());
			map.register(name, this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			new PlayerMessage("&cFailed running command '" + name + "'. ERROR: " + e.getMessage())
					.colorize()
					.console(true);
		}
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		run(sender, args);
		return false;
	}

	public abstract void run(CommandSender sender, String[] args);

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args)  {
		return onTabComplete(sender, args);
	}

	public abstract List<String> onTabComplete(CommandSender sender, String[] args);

}