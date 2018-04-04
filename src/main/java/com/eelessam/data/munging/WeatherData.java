package com.eelessam.data.munging;

import com.eelessam.data.munging.model.DailyWeatherInformation;
import com.eelessam.data.munging.util.LineExtractor;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {

    private static final String FILE_PATH = "data-file/weather.dat";

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String DIGIT_REGEX = "\\D+";
    private static final String EMPTY_CHARACTER = "";

    public int getBiggestTemperatureSpread() {

        DailyWeatherInformation biggestDailyWeatherInformation = null;

        ArrayList<Integer> lineNumbersToExclude = new ArrayList<>();
        lineNumbersToExclude.add(1);
        lineNumbersToExclude.add(2);
        lineNumbersToExclude.add(33);

        List<String> filteredLines = LineExtractor.extractLinesFromFile(FILE_PATH, lineNumbersToExclude);

        for (String line : filteredLines) {
            String[] splitLine = splitLine(line);
            int day = Integer.parseInt(splitLine[Columns.DAY.getPosition()].replaceAll(DIGIT_REGEX, EMPTY_CHARACTER));
            int maxTemp = Integer.parseInt(splitLine[Columns.MAX.getPosition()].replaceAll(DIGIT_REGEX, EMPTY_CHARACTER));
            int minTemp = Integer.parseInt(splitLine[Columns.MIN.getPosition()].replaceAll(DIGIT_REGEX, EMPTY_CHARACTER));
            int tempSpread = maxTemp - minTemp;

            if (biggestDailyWeatherInformation == null || tempSpread > biggestDailyWeatherInformation.getTemperatureSpread()) {
                biggestDailyWeatherInformation = new DailyWeatherInformation(day, tempSpread);
            }

        }

        return biggestDailyWeatherInformation.getDay();
    }

    private String[] splitLine(String currentLine) {
        return currentLine.split(WHITESPACE_REGEX);
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


