package com.catimer.config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum VorkathTimes {
    OFF("Off", 0),
    MASTER("1min15s", 75000),
    GRANDMASTER("54s", 54000);

    private final String name;
    private final long time;

    @Override
    public String toString()
    {
        return name;
    }
}
