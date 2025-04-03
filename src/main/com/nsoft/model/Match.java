package com.nsoft.model;

import static com.nsoft.util.MatchValidator.validateScores;
import static com.nsoft.util.MatchValidator.validateTeams;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private final long startTime;

    private Match(String homeTeam, String awayTeam) {
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

    public void updateScores(int homeTeamScore, int awayTeamScore) {
        validateScores(homeTeamScore, awayTeamScore);
        this.setHomeTeamScore(homeTeamScore);
        this.setAwayTeamScore(awayTeamScore);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
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

}
