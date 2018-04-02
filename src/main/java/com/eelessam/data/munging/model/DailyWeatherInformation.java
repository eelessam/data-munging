package com.eelessam.data.munging.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DailyWeatherInformation {

    private int day;
    private int temperatureSpread;
}