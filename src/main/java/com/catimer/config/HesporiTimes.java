package com.catimer.config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum HesporiTimes {
    OFF("Off", 0),
    ELITE("45s", 48000),
    MASTER("36s", 36000);

    private final String name;
    private final long time;

    @Override
    public String toString()
    {
        return name;
    }
}