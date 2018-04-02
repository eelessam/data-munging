package com.eelessam.data.munging;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherDataTest {

    @Test
    public void getBiggestTemperatureSpread() {
        WeatherData weatherData = new WeatherData();
        assertEquals(9, weatherData.getBiggestTemperatureSpread());
    }

}