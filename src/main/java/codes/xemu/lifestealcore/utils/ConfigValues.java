package codes.xemu.lifestealcore.utils;

import codes.xemu.lifestealcore.LifestealPlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum ConfigValues {

	SETTINGS_GENERAL_PREFIX("Settings.General.Prefix"),
	SETTINGS_STORAGE_TYPE("Settings.Storage.StorageType"),

	START_HEARTS_STARTER("Hearts.Starter"),
	START_HEARTS_UNBAN("Hearts.Unban"),
	HEARTS_MAX("Hearts.Max"),

	HEARTS_LOSS_MOBS_ENABLED("Hearts.HeartLoss.mobsEnabled"),

	WORLD_SETTINGS_DISABLED_WORLDS("Hearts.WorldSettings.Disabled-Worlds"),
	WORLD_SETTINGS_INSTANT_ELIMINATION_WORLDS("Hearts.WorldSettings.InstantEliminationWorlds"),

	BANS_ENABLED("Bans.Enabled"),
	BANS_BAN_COMMAND("Bans.BanCommand"),
	BANS_UNBAN_COMMAND("Bans.UnbanCommand"),

	GUI_HEARTS_SELF_TITLE("GUI.Hearts_Self.Title"),
	GUI_HEARTS_SELF_ITEM_DISPLAY("GUI.Hearts_Self.Item-DisplayName"),
	GUI_HEARTS_SELF_ITEM_LORE("GUI.Hearts_Self.Item-Lore"),

	GUI_HEARTS_OTHER_TITLE("GUI.Hearts_Other.Title"),
	GUI_HEARTS_OTHER_ITEM_DISPLAY("GUI.Hearts_Other.Item-DisplayName"),
	GUI_HEARTS_OTHER_ITEM_LORE("GUI.Hearts_Other.Item-Lore"),

	MESSAGES_NO_PERMISSION("Messages.NoPermission"),
	MESSAGES_INVALID_TARGET("Messages.InvalidTarget"),
	MESSAGES_INVALID_USAGE("Messages.InvalidUsage"),
	MESSAGES_INVALID_PROFILE("Messages.InvalidProfile"),

	MESSAGES_ADMIN_HELP("Messages-Admin.HelpPage"),
	MESSAGES_ADMIN_FROM_SET("Messages-Admin.FromSet"),
	MESSAGES_ADMIN_TO_SET("Messages-Admin.ToSet"),
	MESSAGES_ADMIN_REVIVE("Messages-Admin.Revive"),
	;

	String path;

	public Object get() {
		return LifestealPlugin.get().getConfig().get(path);
	}

	public String getString() {
		return LifestealPlugin.get().getConfig().getString(path);
	}

	public Integer getInt() {
		return LifestealPlugin.get().getConfig().getInt(path);
	}

	public boolean getBoolean() {
		return LifestealPlugin.get().getConfig().getBoolean(path);
	}

	public String[] getStringListAsArray() {
		return (String[]) LifestealPlugin.get().getConfig().getStringList(path).toArray();
	}

	public List<String> getStringList() {
		return LifestealPlugin.get().getConfig().getStringList(path);
	}

}
