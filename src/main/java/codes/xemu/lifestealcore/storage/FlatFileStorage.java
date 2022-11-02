package codes.xemu.lifestealcore.storage;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.utils.ConfigValues;

import java.util.UUID;

public class FlatFileStorage implements Storage {
	@Override
	public String getTypeName() {
		return "FlatFile";
	}

	@Override
	public String getIdentifier() {
		return "flat";
	}

	@Override
	public LifestealProfile getProfile(UUID uuid) {
		if (LifestealPlugin.get().getConfig().get("Storage." + uuid.toString()) == null) {
			LifestealPlugin.get().getConfig().set("Storage." + uuid.toString(), ConfigValues.START_HEARTS_STARTER.getInt());
			LifestealPlugin.get().saveConfig();
		}

		return new LifestealProfile(uuid, LifestealPlugin.get().getConfig().getInt("Storage." + uuid.toString()));
	}

	@Override
	public void saveData(LifestealProfile profile) {
		LifestealPlugin.get().getConfig().set("Storage." + profile.getUuid().toString(), profile.getHearts());
		LifestealPlugin.get().saveConfig();
	}
}
