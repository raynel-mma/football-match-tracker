package com.nsoft.service;

import com.nsoft.model.Match;

import java.util.Collections;
import java.util.List;

public class MatchService {
    private List<Match> scoreboard;

    public MatchService(List<Match> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void startMatch(String homeTeam, String awayTeam) {
    } //todo

    public void updateMatch(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
    } //todo

    public void finishMatch(String homeTeam, String awayTeam) {
    } //todo

    public List<Match> getOngoingMatches() {
        return Collections.unmodifiableList(scoreboard);
    }

    public void showScoreboard() {
    } //todo
}
