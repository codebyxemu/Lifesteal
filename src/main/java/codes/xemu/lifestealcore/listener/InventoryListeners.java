package codes.xemu.lifestealcore.listener;

import codes.xemu.lifestealcore.utils.ConfigValues;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListeners implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if (e.getView().getTitle().equalsIgnoreCase(ConfigValues.GUI_HEARTS_OTHER_TITLE.getString())
		|| e.getView().getTitle().equalsIgnoreCase(ConfigValues.GUI_HEARTS_SELF_TITLE.getString())) {
			e.setCancelled(true);
		}
	}

}
