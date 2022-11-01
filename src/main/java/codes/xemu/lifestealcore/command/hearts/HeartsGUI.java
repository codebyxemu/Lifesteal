package codes.xemu.lifestealcore.command.hearts;

import codes.xemu.lifestealcore.profile.Profile;
import codes.xemu.lifestealcore.utils.ItemBuilder;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class HeartsGUI implements Listener {

	private Inventory inventory = Bukkit.createInventory(null, 9 * 3, "Hearts Management");

	public void open(Player player) {
		Profile profile = new Profile(player.getUniqueId());
		int hearts = profile.getHearts();
		String heartsString = String.valueOf(hearts);

		ItemBuilder heartsItem = new ItemBuilder(Material.SPYGLASS, 1)
				.setName(
						new PlayerMessage("").configurable("Messages.HeartsGUI.HeartsItem.Display")
								.setPlaceholder("<hearts>", heartsString)
								.colorize()
								.getMessage()
				);

		inventory.setItem(13, heartsItem.build());

		player.openInventory(inventory);
	}

	@EventHandler
	public void onPlayerClickInventory(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getView().getTitle().equalsIgnoreCase("Hearts Management")) {
			event.setCancelled(true); // Prevent stealing out of inventory
		}
	}

}
