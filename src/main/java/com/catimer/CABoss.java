package com.catimer;

import com.google.common.collect.ImmutableMap;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import net.runelite.api.ItemID;
import net.runelite.api.NpcID;
import java.util.ArrayList;
import java.util.List;

enum CABoss {
    HESPORI(NpcID.HESPORI, ItemID.BOTTOMLESS_COMPOST_BUCKET),
    VORKATH(NpcID.VORKATH_8061, ItemID.VORKI),
//    HYDRA(NpcID.ALCHEMICAL_HYDRA, ItemID.IKKLE_HYDRA),
    ZULRAH(NpcID.ZULRAH, ItemID.PET_SNAKELING);
//    GALVEK(NpcID.GALVEK, ChronoUnit.SECONDS, ItemID.MYTHICAL_CAPE),
//    SEREN(NpcID.FRAGMENT_OF_SEREN, ItemID.CRYSTAL_OF_TRAHAEARN);

    private static final Map<Integer, CABoss> bosses;
    private final int id;
    private final int itemSpriteId;

    static {
        ImmutableMap.Builder<Integer, CABoss> builder = new ImmutableMap.Builder<>();

        for (CABoss boss : values()) {
            builder.put(boss.getId(), boss);
        }

        bosses = builder.build();
    }

    CABoss(int id, int itemSpriteId) {
        this.id = id;
        this.itemSpriteId = itemSpriteId;
    }

    public int getId() {
        return id;
    }

//    public Duration getKillTime() {
//        return killTime;
//    }

    public int getItemSpriteId() {
        return itemSpriteId;
    }

    public static CABoss find(int id) {
        return bosses.get(id);
    }

}