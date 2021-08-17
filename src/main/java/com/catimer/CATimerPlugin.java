package com.catimer;

import java.lang.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import com.catimer.config.VorkathTimes;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.AnimationChanged;
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
	name = "CA Timers"
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

	@Provides
	CATimerConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(CATimerConfig.class);
	}
//	@Override
//	protected void startUp() throws Exception
//	{
//		log.info("Example started!");
//	}
//
//	@Override
//	protected void shutDown() throws Exception
//	{
//		log.info("Example stopped!");
//	}


	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
//		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Time for Hespori "+ config.hesporiTime().getTimes(), null);
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

		long configTime;
        switch (npcId) {
            case NpcID.HESPORI:
                 configTime = config.hesporiTime().getTime();
                break;
            case NpcID.ZULRAH:
                configTime = config.zulrahTime().getTime();
                break;
            default:
                configTime = 0;
        }
		infoBoxManager.removeIf(t -> t instanceof Timer);
        if (configTime != 0) {
			Timer timer = new Timer(configTime, ChronoUnit.MILLIS, itemManager.getImage(boss.getItemSpriteId()), this);
			timer.setTooltip(npc.getName());
			infoBoxManager.addInfoBox(timer);
		} else {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Timer is off for "+npc.getName(), null);
		}


	}

	// Vorkath case - will adjust as new bosses are added
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
		long configTime = config.vorkathTime().getTime();
		infoBoxManager.removeIf(t -> t instanceof Timer);
		if (Objects.equals(firstAction, "Attack") && configTime != 0) {
			Timer timer = new Timer(configTime, ChronoUnit.MILLIS, itemManager.getImage(boss.getItemSpriteId()), this);
			timer.setTooltip(npc.getName());
			infoBoxManager.addInfoBox(timer);
		}
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged animationChanged) {
		Actor ac = animationChanged.getActor();
		String nam = ac.getName();
		int inter = ac.getAnimation();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Timer is off for "+nam + inter, null);
	}
}
