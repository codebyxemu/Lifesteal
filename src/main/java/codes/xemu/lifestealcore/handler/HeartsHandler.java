package codes.xemu.lifestealcore.handler;

import codes.xemu.lifestealcore.LifestealPlugin;
import codes.xemu.lifestealcore.storage.LifestealProfile;

public class HeartsHandler {

	public void setHearts(LifestealProfile profile, int hearts) {
		LifestealPlugin.get().getStorage().saveData(new LifestealProfile(profile.getUuid(), hearts));
	}

}
