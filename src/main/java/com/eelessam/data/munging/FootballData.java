package com.eelessam.data.munging;

import com.eelessam.data.munging.model.TeamInformation;
import com.eelessam.data.munging.util.FileExtractor;

import java.util.List;

public class FootballData {

    public String getDifferenceInGoals() {

        TeamInformation teamInformation = null;

        List<String> linesOfFile = FileExtractor.readInFile("data-file/football.dat");
        linesOfFile.remove(0);
        linesOfFile.remove(17);

        for (String line : linesOfFile) {
            String[] splitLine = line.split("\\s+");
            String teamName = splitLine[Columns.TEAM_NAME.getPosition()];
            int goalsFor = Integer.parseInt(splitLine[Columns.GOALS_FOR.getPosition()]);
            int goalsAgainst = Integer.parseInt(splitLine[Columns.GOALS_AGAINST.getPosition()]);

            int differenceInGoals = (goalsFor < goalsAgainst) ? goalsAgainst - goalsFor : goalsFor - goalsAgainst;

            if (teamInformation == null || teamInformation.getDifferenceInGoals() > differenceInGoals) {
                teamInformation = new TeamInformation(teamName, differenceInGoals);
            }
        }

        return teamInformation.getTeamName();
    }

    public enum Columns {
        TEAM_NAME(2),
        GOALS_FOR(7),
        GOALS_AGAINST(9);

        private int position;

        Columns(int position) {
            this.position = position;
        }

        public int getPosition() {
            return this.position;
        }
    }
}
