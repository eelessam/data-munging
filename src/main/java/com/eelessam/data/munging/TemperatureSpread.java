package com.eelessam.data.munging;

public class TemperatureSpread {

    private int day;
    private int temperatureSpread;

    public TemperatureSpread(int day, int temperatureSpread) {
        this.day = day;
        this.temperatureSpread = temperatureSpread;
    }

    public int getTemperatureSpread() {
        return temperatureSpread;
    }

    public int getDay() {
        return day;
    }
}