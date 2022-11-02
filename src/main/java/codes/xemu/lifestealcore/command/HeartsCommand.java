package codes.xemu.lifestealcore.command;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.storage.LifestealProfile;
import codes.xemu.lifestealcore.utils.ConfigValues;
import codes.xemu.lifestealcore.utils.ItemBuilder;
import codes.xemu.lifestealcore.utils.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HeartsCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			openSelfGui(player);
		} else if (args.length == 1) {
			Player target;
			if (Bukkit.getPlayer(args[0]) != null) {
				target = Bukkit.getPlayer(args[0]);
				return true;
			} else {
				target = null;
				new MessageBuilder(ConfigValues.MESSAGES_INVALID_TARGET.getString())
						.setPrefix()
						.colorize()
						.send(player);
			}

			if (LifestealPlugin.get().getStorage().getProfile(target.getUniqueId()) == null) {
				new MessageBuilder(ConfigValues.MESSAGES_INVALID_PROFILE.getString())
						.setPrefix()
						.colorize()
						.send(player);
				return true;
			}

			openOtherGui(player, target);
			return true;
		}



		return true;
	}

	private void openSelfGui(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9,
				new MessageBuilder(ConfigValues.GUI_HEARTS_SELF_TITLE.getString())
						.setPlaceholder("<target>", player.getName()).getMessage());

		LifestealProfile profile = LifestealPlugin.get().getStorage().getProfile(player.getUniqueId());
		String hearts = String.valueOf(profile.getHearts());

		// TODO: Replace current ItemBuilder with something else
		ItemBuilder itemBuilder = new ItemBuilder(Material.SPYGLASS, 1)
				.setName(
						new MessageBuilder(
								ConfigValues.GUI_HEARTS_SELF_ITEM_DISPLAY.getString()
						).setPlaceholder("<hearts>", hearts).getMessage()
				)
				.setLore(
						ConfigValues.GUI_HEARTS_SELF_ITEM_LORE.getStringList()
				)
		;

		inventory.setItem(4, itemBuilder.build());

		player.openInventory(inventory);
	}

	private void openOtherGui(Player player, Player other) {
		Inventory inventory = Bukkit.createInventory(null, 9,
				new MessageBuilder(ConfigValues.GUI_HEARTS_OTHER_TITLE.getString())
						.setPlaceholder("<target>", other.getName()).getMessage());

		LifestealProfile profile = LifestealPlugin.get().getStorage().getProfile(player.getUniqueId());
		String hearts = String.valueOf(profile.getHearts());

		// TODO: Replace current ItemBuilder with something else
		ItemBuilder itemBuilder = new ItemBuilder(Material.SPYGLASS, 1)
				.setName(
						new MessageBuilder(
								ConfigValues.GUI_HEARTS_OTHER_ITEM_DISPLAY.getString()
						).setPlaceholder("<hearts>", hearts).getMessage()
				)
				.setLore(
						ConfigValues.GUI_HEARTS_OTHER_ITEM_LORE.getStringList()
				)
				;

		inventory.setItem(4, itemBuilder.build());

		player.openInventory(inventory);
	}
}
