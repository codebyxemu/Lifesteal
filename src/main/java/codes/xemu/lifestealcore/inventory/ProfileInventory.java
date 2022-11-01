package codes.xemu.lifestealcore.inventory;

import codes.xemu.lifestealcore.profile.Profile;
import codes.xemu.lifestealcore.utils.ItemBuilder;
import codes.xemu.lifestealcore.utils.PlayerMessage;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ProfileInventory implements Listener {

	protected ChestGui chestGui = new ChestGui(3, "Profile");

	protected ItemBuilder itemBuilder = new ItemBuilder(Material.SPYGLASS, 1);
	protected GuiItem guiItem = new GuiItem(itemBuilder.build(), inventoryClickEvent -> inventoryClickEvent.setCancelled(true));

	public void handle(Player player) {
		Profile profile = new Profile(player.getUniqueId());

		itemBuilder.setName(new PlayerMessage("&cYour Statistics").colorize().getMessage());
		itemBuilder.setLore(
				"&7Hearts &8- &f" + profile.getHeartsAsString()
		);

		chestGui.getInventory().setItem(13, guiItem.getItem());

		player.openInventory(chestGui.getInventory());
	}


	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase("Profile")) event.setCancelled(true);
	}

}


