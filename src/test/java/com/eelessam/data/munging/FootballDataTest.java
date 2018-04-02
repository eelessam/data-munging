package com.eelessam.data.munging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballDataTest {

    @Test
    public void getBiggestTemperatureSpread() {
        FootballData footballData = new FootballData();
        assertEquals("Aston_Villa", footballData.getDifferenceInGoals());
    }

}
