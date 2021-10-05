package com.catimer.config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum HydraTimes {
    OFF("Off", 0),
    MASTER("1min45s", 105000),
    GRANDMASTER("1min20s", 80000);


    private final String name;
    private final long time;

    @Override
    public String toString()
    {
        return name;
    }
}