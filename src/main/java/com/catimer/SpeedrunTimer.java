package com.catimer;
import net.runelite.client.ui.overlay.infobox.Timer;

import java.awt.image.BufferedImage;
import java.time.temporal.ChronoUnit;

import net.runelite.client.plugins.Plugin;

public class SpeedrunTimer extends Timer {
    private final CABoss boss;

    public SpeedrunTimer(CABoss boss, long period, BufferedImage bossImage, Plugin plugin)
    {
        super(period, ChronoUnit.MILLIS, bossImage, plugin);
        this.boss = boss;
    }

    public CABoss getBoss()
    {
        return boss;
    }
}
