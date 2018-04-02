package com.eelessam.data.munging;

import com.eelessam.data.munging.model.DailyWeatherInformation;
import com.eelessam.data.munging.util.FileExtractor;

import java.util.List;

public class WeatherData {

    public int getBiggestTemperatureSpread() {

        DailyWeatherInformation biggestDailyWeatherInformation = null;

        List<String> linesOfFile = FileExtractor.readInFile("data-file/weather.dat");
        linesOfFile.remove(0);
        linesOfFile.remove(0);
        linesOfFile.remove(30);

        for (String line : linesOfFile) {
            String[] splitLine = splitLine(line);
            int day = Integer.parseInt(splitLine[Columns.DAY.getPosition()].replaceAll("\\D+", ""));
            int maxTemp = Integer.parseInt(splitLine[Columns.MAX.getPosition()].replaceAll("\\D+", ""));
            int minTemp = Integer.parseInt(splitLine[Columns.MIN.getPosition()].replaceAll("\\D+", ""));
            int tempSpread = maxTemp - minTemp;

            if (biggestDailyWeatherInformation == null || tempSpread > biggestDailyWeatherInformation.getTemperatureSpread()) {
                biggestDailyWeatherInformation = new DailyWeatherInformation(day, tempSpread);
            }
        }

        return biggestDailyWeatherInformation.getDay();
    }

    private String[] splitLine(String currentLine) {
        return currentLine.split("\\s+");
    }

    public enum Columns {
        DAY(1),
        MAX(2),
        MIN(3);

        private int position;

        Columns(int position) {
            this.position = position;
        }

        public int getPosition() {
            return this.position;
        }
    }

}


