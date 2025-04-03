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

    public void updateMatch(Match match, int homeTeamScore, int awayTeamScore) {
    } //todo

    public void finishMatch(Match match) {
    } //todo

    public List<Match> getScoreboard() {
        return Collections.unmodifiableList(scoreboard);
    }

    public void showScoreboard() {
    } //todo
}
