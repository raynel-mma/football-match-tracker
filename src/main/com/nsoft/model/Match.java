package com.nsoft.model;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private final long startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.startTime = System.currentTimeMillis();
    }

    public static Match createMatch(String homeTeam, String awayTeam){
        validateTeams(homeTeam, awayTeam);
        return new Match(homeTeam, awayTeam);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeTeamScore + " " + awayTeam + " " + awayTeamScore;
    }

    private static void validateTeams(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isEmpty()) {
            throw new IllegalArgumentException("Home team name cannot be the empty or null");
        }
        if (awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Away team name cannot be the empty or null");
        }
    }
}
