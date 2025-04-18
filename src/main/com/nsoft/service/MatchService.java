package com.nsoft.service;

import com.nsoft.model.Match;
import com.nsoft.util.TeamProvider;

import java.util.*;
import java.util.stream.Collectors;

import static com.nsoft.model.Match.createMatch;
import static com.nsoft.model.MatchValidator.isDurationInvalid;

public class MatchService {
    private List<Match> scoreboard;
    private static List<String> teams;

    static {
        teams = new ArrayList<>(TeamProvider.getTeams());
    }

    public MatchService(List<Match> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public static Match createRandomMatch() {
        if (teams.size() < 2) {
            throw new IllegalStateException("Not enough teams to create matches");
        }

        Collections.shuffle(teams);

        String homeTeam = teams.remove(0);
        String awayTeam = teams.remove(0);

        return createMatch(homeTeam, awayTeam);
    }

    public void startMatch(Match matchToBeStarted) {
        if (scoreboard.stream().anyMatch(match -> match.getAwayTeam().equals(matchToBeStarted.getAwayTeam()) || match.getHomeTeam().equals(matchToBeStarted.getHomeTeam()))) {
            throw new IllegalArgumentException("Team " + matchToBeStarted.getAwayTeam() + " is already playing");
        }

        scoreboard.add(matchToBeStarted);
    }

    public Match updateMatch(Match match, int homeTeamScore, int awayTeamScore, int duration) {
        Match updatedMatch  = null;

        if(isDurationInvalid(duration + match.getDuration())){
            updatedMatch  = finishMatch(match);
        }
        else {
            match.setHomeTeamScore(homeTeamScore);
            match.setAwayTeamScore(awayTeamScore);
            match.updateTotalScore();
            match.setDuration(duration);
            updatedMatch  = match;
        }

        return updatedMatch ;
    }

    public Match updateRandomMatch() {
        if (!scoreboard.isEmpty()) {
            Random random = new Random();

            Match match = scoreboard.get(random.nextInt(scoreboard.size()));

            int newHomeTeamScore = match.getHomeTeamScore() + random.nextInt(2);
            int newAwayTeamScore = match.getAwayTeamScore() + random.nextInt(2);
            int newDuration = match.getDuration() + random.nextInt(60);

            return updateMatch(match, newHomeTeamScore, newAwayTeamScore, newDuration);
        }
        return null;
    }

    public Match finishMatch(Match match) {
        if (match.isFinished()) {
            throw new IllegalArgumentException("Match not found for home team: " + match.getHomeTeam() + " and away team: " + match.getAwayTeam());
        }
        match.updateTotalScore();
        match.setFinished(true);

        scoreboard.remove(match);

        return match;
    }

    public List<Match> getScoreboard() {
        return Collections.unmodifiableList(
                scoreboard.stream()
                        .filter(match -> !match.isFinished())
                        .sorted(
                                Comparator.comparingInt(Match::getTotalScore).reversed()
                                        .thenComparing(Match::getStartTime)
                        )
                        .collect(Collectors.toList())
        );
    }

    public boolean clearScoreboard() {
        scoreboard.clear();
        return scoreboard.isEmpty();
    }

    public static boolean resetTeamsList() {
        if (!teams.equals(TeamProvider.getTeams())) {
            teams = new ArrayList<>(TeamProvider.getTeams());
            return true;
        }
        return false;
    }

}
