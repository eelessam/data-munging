package com.eelessam.data.munging;

import com.eelessam.data.munging.model.TeamInformation;
import com.eelessam.data.munging.util.LineExtractor;

import java.util.ArrayList;
import java.util.List;

public class FootballData {

    private static final String FILE_PATH = "data-file/football.dat";

    private static final String WHITESPACE_REGEX = "\\s+";

    public String getDifferenceInGoals() {

        TeamInformation teamInformation = null;

        ArrayList<Integer> lineNumbersToExclude = new ArrayList<>();
        lineNumbersToExclude.add(1);
        lineNumbersToExclude.add(19);

        List<String> filteredLines = LineExtractor.extractLinesFromFile(FILE_PATH, lineNumbersToExclude);

        for (String line : filteredLines) {
            String[] splitLine = line.split(WHITESPACE_REGEX);

            String teamName = splitLine[Columns.TEAM_NAME.getPosition()];
            int goalsFor = Integer.parseInt(splitLine[Columns.GOALS_FOR.getPosition()]);
            int goalsAgainst = Integer.parseInt(splitLine[Columns.GOALS_AGAINST.getPosition()]);

            int differenceInGoals = (goalsFor < goalsAgainst) ? goalsAgainst - goalsFor : goalsFor - goalsAgainst;

            if (teamInformation == null || teamInformation.getDifferenceInGoals() > differenceInGoals) {
                teamInformation = new TeamInformation(differenceInGoals, teamName);
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
