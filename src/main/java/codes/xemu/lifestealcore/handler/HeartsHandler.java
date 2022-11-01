package codes.xemu.lifestealcore.handler;

import codes.xemu.lifestealcore.Lifesteal;
import codes.xemu.lifestealcore.profile.Profile;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HeartsHandler {

	protected Lifesteal plugin = Lifesteal.get();

	public static void stealHeart(Profile player, Profile victim) {
		player.increaseHeartsByOne();
		victim.decreaseHeartsByOne();
	}

}
