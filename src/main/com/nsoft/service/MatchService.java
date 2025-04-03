package com.nsoft.service;

import com.nsoft.model.Match;

import java.util.Collections;
import java.util.List;

public class MatchService {
    private List<Match> scoreboard;

    public MatchService(List<Match> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void startMatch(Match matchToBeStarted) {
        if (scoreboard.stream().anyMatch(match -> match.getAwayTeam().equals(matchToBeStarted.getAwayTeam()) || match.getHomeTeam().equals(matchToBeStarted.getHomeTeam()))) {
            throw new IllegalArgumentException("Team " + matchToBeStarted.getAwayTeam() + " is already playing");
        }

        scoreboard.add(matchToBeStarted);
    }

    public Match updateMatch(Match match, int homeTeamScore, int awayTeamScore) {
        if (!isMatchOngoing(match)) {
            throw new IllegalArgumentException("Match not found for home team: " + match.getHomeTeam() + " and away team: " + match.getAwayTeam());
        }

        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);

        return match;
    }

    public boolean finishMatch(Match match) {
        if (!isMatchOngoing(match)) {
            throw new IllegalArgumentException("Match not found for home team: " + match.getHomeTeam() + " and away team: " + match.getAwayTeam());
        }
        return scoreboard.remove(match);
    }

    public List<Match> getScoreboard() {
        return Collections.unmodifiableList(scoreboard);
    }

    public boolean clearScoreboard() {
        scoreboard.clear();
        return scoreboard.isEmpty();
    }

    public void showScoreboard() {
    } //todo

    private boolean isMatchOngoing(Match match) {
        for (Match scoreboardMatch : scoreboard) {
            if (scoreboardMatch.getHomeTeam().equals(match.getHomeTeam())
                    && scoreboardMatch.getAwayTeam().equals(match.getAwayTeam())) {
                return true;
            }
        }
        return false;
    }
}
