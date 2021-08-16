package com.hesporitimer;

import java.lang.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.NpcChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.api.events.NpcSpawned;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.Timer;

@Slf4j
@PluginDescriptor(
	name = "HesporiTimer"
)
public class CATimerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private CATimerConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		NPC npc = npcSpawned.getNpc();
		if (npc.isDead()) {
			return;
		}

		int npcId = npc.getId();
		CABoss boss = CABoss.find(npcId);

		if (boss == null)
		{
//			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Boss Could not be found {}"+npc.getName(), null);
			return;
		}

		infoBoxManager.removeIf(t -> t instanceof Timer);

		// will add toggle to which timer you want bc there are multiple tasks
		List<Duration> times = boss.getKillTimes();
		Timer timer = new Timer(times.get(times.size() - 1).toMillis(), ChronoUnit.MILLIS, itemManager.getImage(boss.getItemSpriteId()), this);
		timer.setTooltip(npc.getName());
		infoBoxManager.addInfoBox(timer);

	}

	@Subscribe
	public void onNpcChanged(NpcChanged npcChanged) {
		NPC npc =  npcChanged.getNpc();
		NPCComposition npcComp = npc.getComposition();
		String firstAction = npcComp.getActions()[1];

		int npcId = npc.getId();
		CABoss boss = CABoss.find(npcId);

		if (boss == null || boss.getId() != NpcID.VORKATH_8061)
		{
			return;
		}
		if (Objects.equals(firstAction, "Attack")) {
			infoBoxManager.removeIf(t -> t instanceof Timer);

			// will add toggle to which timer you want bc there are multiple tasks
			List<Duration> times = boss.getKillTimes();
			Timer timer = new Timer(times.get(times.size() - 1).toMillis(), ChronoUnit.MILLIS, itemManager.getImage(boss.getItemSpriteId()), this);
			timer.setTooltip(npc.getName());
			infoBoxManager.addInfoBox(timer);
		}
	}
	@Provides
	CATimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CATimerConfig.class);
	}
}
