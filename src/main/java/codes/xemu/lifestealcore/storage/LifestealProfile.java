package codes.xemu.lifestealcore.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LifestealProfile {
	private UUID uuid;
	private int hearts;
}
