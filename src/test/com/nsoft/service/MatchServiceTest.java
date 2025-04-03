package com.nsoft.service;

import com.nsoft.model.Match;

import java.util.ArrayList;

import static com.nsoft.model.Match.createMatch;
import static com.nsoft.util.AssertionUtils.assertEquals;
import static com.nsoft.util.AssertionUtils.assertTrue;

public class MatchServiceTest {

    private MatchService matchService;

    public MatchServiceTest(MatchService matchService) {
        this.matchService = matchService;
    }

    public static void main(String[] args) {
        MatchServiceTest matchServiceTest = new MatchServiceTest(new MatchService(new ArrayList<>()));

        matchServiceTest.runTests();
    }

    public void runTests() {
        testStartMatch();

        testStartMatch_TeamIsAlreadyPlaying();

        testUpdateMatch();

        testFinishMatch();

        testFinishMatch_MatchDoesNotExist();
    }

    public void testStartMatch() {
        Match match = createMatch("Mexico", "Canada");

        matchService.startMatch(match);

        assertTrue(!matchService.getScoreboard().isEmpty(), "Match was not added");

        Match ongoingMatch = matchService.getScoreboard().get(0);

        assertEquals(ongoingMatch.getHomeTeam(), "Mexico");
        assertEquals(ongoingMatch.getAwayTeam(), "Canada");
        assertEquals("Home team score should be initialized to 0", 0, ongoingMatch.getHomeTeamScore());
        assertEquals("Away team score should be initialized to 0", 0, ongoingMatch.getAwayTeamScore());
    }

    /**
     * Test: Ensure that a team cannot be added to the scoreboard more than once while it is already involved in an ongoing match
     * Ex1. Spain - Brazil, Uruguay - Spain, Ex2. Spain - Brazil, Uruguay - Brazil
     */
    public void testStartMatch_TeamIsAlreadyPlaying() {
        Match match1 = createMatch("Spain", "Brazil");
        Match match2 = createMatch("Uruguay", "Spain");

        try {
            matchService.startMatch(match1);
            matchService.startMatch(match2);
        } catch (IllegalArgumentException e) {
            assertEquals("Team Spain is already playing", e.getMessage());
        }

        Match match3 = createMatch("Spain", "Brazil");
        Match match4 = createMatch("Uruguay", "Brazil");

        try {
            matchService.startMatch(match3);
            matchService.startMatch(match4);
        } catch (IllegalArgumentException e) {
            assertEquals("Team Brazil is already playing", e.getMessage());
        }
    }

    public void testUpdateMatch() {
        //todo
    }

    public void testFinishMatch() {
        //todo
    }

    public void testFinishMatch_MatchDoesNotExist() {
        //todo
    }
}
