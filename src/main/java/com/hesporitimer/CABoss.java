package com.hesporitimer;

import com.google.common.collect.ImmutableMap;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import net.runelite.api.ItemID;
import net.runelite.api.NpcID;
import java.util.ArrayList;
import java.util.List;

enum CABoss {
    HESPORI(NpcID.HESPORI, new long[]{48, 36}, ChronoUnit.SECONDS, ItemID.BOTTOMLESS_COMPOST_BUCKET),
    VORKATH(NpcID.VORKATH_8061, new long[]{75, 54}, ChronoUnit.SECONDS, ItemID.VORKI),
//    HYDRA(NpcID.ALCHEMICAL_HYDRA, new long[]{105, 80}, ChronoUnit.SECONDS, ItemID.IKKLE_HYDRA),
    ZULRAH(NpcID.ZULRAH, new long[]{80, 60, 54}, ChronoUnit.SECONDS, ItemID.PET_SNAKELING);
//    GALVEK(NpcID.GALVEK, new long[] {180}, ChronoUnit.SECONDS, ItemID.MYTHICAL_CAPE),
//    SEREN(NpcID.FRAGMENT_OF_SEREN, new long[] {240}, ChronoUnit.SECONDS, ItemID.CRYSTAL_OF_TRAHAEARN);

    private static final Map<Integer, CABoss> bosses;

    private final int id;
    private final List<Duration> killTimes;
    private final int itemSpriteId;

    static {
        ImmutableMap.Builder<Integer, CABoss> builder = new ImmutableMap.Builder<>();

        for (CABoss boss : values()) {
            builder.put(boss.getId(), boss);
        }

        bosses = builder.build();
    }

    CABoss(int id, long[] times, ChronoUnit unit, int itemSpriteId) {
        this.id = id;
        this.killTimes = new ArrayList<Duration>();
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0) {
                killTimes.add(Duration.of(times[i], unit));
            }
        };
        this.itemSpriteId = itemSpriteId;
    }

    public int getId() {
        return id;
    }

    public List<Duration> getKillTimes() {
        return killTimes;
    }

    public int getItemSpriteId() {
        return itemSpriteId;
    }

    public static CABoss find(int id) {
        return bosses.get(id);
    }

}