package com.catimer.config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ZulrahTimes {
    OFF("Off", 0),
    ELITE("1min20s", 83600),
    MASTER("1min", 63600),
    GRANDMASTER("54s", 57600);

    private final String name;
    private final long time;

    @Override
    public String toString()
    {
        return name;
    }
}