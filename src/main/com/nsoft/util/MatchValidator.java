package com.nsoft.util;

public class MatchValidator {
    private MatchValidator() {
    }

    public static void validateTeams(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isEmpty()) {
            throw new IllegalArgumentException("Home team name cannot be the empty or null");
        }
        if (awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Away team name cannot be the empty or null");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home team name and away team name cannot be the same");
        }
    }

    public static void validateScores(int homeTeamScore, int awayTeamScore) {
        StringBuilder errorMessage = new StringBuilder();

        if (homeTeamScore < 0) {
            errorMessage.append("Home team score cannot be negative");
        }

        if (awayTeamScore < 0) {
            if (errorMessage.length() > 0) {
                errorMessage.append(", ");
            }
            errorMessage.append("Away team score cannot be negative");
        }

        if (errorMessage.length() > 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

}
