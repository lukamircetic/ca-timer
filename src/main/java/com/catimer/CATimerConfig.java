package com.catimer;

import com.catimer.config.HesporiTimes;
import com.catimer.config.VorkathTimes;
import com.catimer.config.ZulrahTimes;
import com.catimer.config.HydraTimes;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("catimers")
public interface CATimerConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "vorkath",
		name = "Vorkath Timer",
		description = "Select a kill time for the Vorkath Speedrun Task"
	)
	default VorkathTimes vorkathTime()
	{
		return VorkathTimes.GRANDMASTER;
	}

	@ConfigItem(
			position = 1,
			keyName = "hespori",
			name = "Hespori Timer",
			description = "Select a kill time for the Hespori Speedrun Task"
	)
	default HesporiTimes hesporiTime()
	{
		return HesporiTimes.MASTER;
	}

	@ConfigItem(
			position = 2,
			keyName = "zulrah",
			name = "Zulrah Timer",
			description = "Select a kill time for the Zulrah Speedrun Task"
	)
	default ZulrahTimes zulrahTime()
	{
		return ZulrahTimes.GRANDMASTER;
	}

	@ConfigItem(
			position = 3,
			keyName = "hydra",
			name = "Hydra Timer",
			description = "Select a kill time for the Hydra Speedrun Task"
	)
	default HydraTimes hydraTime()
	{
		return HydraTimes.GRANDMASTER;
	}
}
