package codes.xemu.lifestealcore.profile;

import codes.xemu.lifestealcore.Lifesteal;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Profile {

	private UUID uuid;
	private int hearts;

	public Profile(UUID uuid) {
		this.uuid = uuid;
		if (Lifesteal.get().getConfig().get("Storage." + uuid.toString()) == null) {
			Lifesteal.get().getConfig().set("Storage." + uuid.toString(), Lifesteal.get().getConfig().getInt("Settings.StartingHearts"));
			Lifesteal.get().saveConfig();
		}
		this.hearts = Lifesteal.get().getConfig().getInt("Storage." + uuid.toString());
	}

	public void increaseHeartsByOne() {
		setHearts(getHearts() + 1);
	}

	public void decreaseHeartsByOne() {
		setHearts(getHearts() - 1);
	}

	public void setHearts(int hearts) {
		this.hearts = hearts;
		update();
	}

	public void update() {
		Lifesteal.get().getConfig().set("Storage." + uuid.toString(), getHearts());
		Lifesteal.get().saveConfig();
	}

	public String getHeartsAsString() {
		return String.valueOf(getHearts());
	}

}
