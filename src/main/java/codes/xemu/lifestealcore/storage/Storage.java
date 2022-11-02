package codes.xemu.lifestealcore.storage;

import java.util.UUID;

public interface Storage {

	public String getTypeName();

	public String getIdentifier();

	public LifestealProfile getProfile(UUID uuid);

	public void saveData(LifestealProfile profile);

}
