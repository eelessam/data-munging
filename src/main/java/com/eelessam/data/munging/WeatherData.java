package com.eelessam.data.munging;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class WeatherData {

    public int getBiggestTemperatureSpread() {

        TemperatureSpread biggestTemperatureSpread = null;

        try (LineNumberReader bufferedReader = new LineNumberReader(new FileReader(new File(WeatherData.class.getClassLoader().getResource("data-file/weather.dat").getFile())))) {

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                if (bufferedReader.getLineNumber() == 1 || bufferedReader.getLineNumber() == 2 || bufferedReader.getLineNumber() == 33) {
                    continue;
                }

                String[] splitLine = splitLine(currentLine);
                int day = Integer.parseInt(splitLine[Columns.DAY.getPosition()].replaceAll("\\D+", ""));
                int maxTemp = Integer.parseInt(splitLine[Columns.MAX.getPosition()].replaceAll("\\D+", ""));
                int minTemp = Integer.parseInt(splitLine[Columns.MIN.getPosition()].replaceAll("\\D+", ""));
                int tempSpread = maxTemp - minTemp;

                if (biggestTemperatureSpread == null || tempSpread > biggestTemperatureSpread.getTemperatureSpread()) {
                    biggestTemperatureSpread = new TemperatureSpread(day, tempSpread);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return biggestTemperatureSpread.getDay();
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


