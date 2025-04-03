package com.nsoft.model;

import static com.nsoft.model.Match.createMatch;
import static com.nsoft.util.AssertionUtils.assertEquals;
import static com.nsoft.util.AssertionUtils.assertTrue;

public class MatchTest {

    public static void main(String[] args) {
        MatchTest matchTest = new MatchTest();
        matchTest.runTests();
    }

    private void runTests() {
        runCreateMatchTests();

        runUpdateMatchTests();

        testToString();
    }

    public void testCreateMatch() {
        Match match = createMatch("Mexico", "Canada");

        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
        assertTrue(match.getStartTime() > 0, "Match is not ongoing");
    }

    public void testCreateMatch_EmptyHomeTeamName() {
        try {
            createMatch("", "Canada");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name cannot be the empty or null", e.getMessage());
        }
    }

    public void testCreateMatch_NullHomeTeamName() {
        try {
            createMatch(null, "Canada");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name cannot be the empty or null", e.getMessage());
        }
    }

    public void testCreateMatch_EmptyAwayTeamName() {
        try {
            createMatch("Mexico", "");
        } catch (IllegalArgumentException e) {
            assertEquals("Away team name cannot be the empty or null", e.getMessage());
        }
    }

    public void testCreateMatch_NullAwayTeamName() {
        try {
            createMatch("Mexico", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Away team name cannot be the empty or null", e.getMessage());
        }
    }

    public void testCreateMatch_SameHomeAndAwayTeam() {
        Match match = null;
        try {
            createMatch("Mexico", "Mexico");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name and away team name cannot be the same", e.getMessage());
            assertTrue(match == null, "Match is not null");
        }
    }

    public void testUpdateMatch() {
        Match match = createMatch("Mexico", "Canada");

        assertTrue(match != null, "Match should not be null");

        match.updateScores(0, 1);

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(1, match.getAwayTeamScore());
    }

    public void testUpdateMatch_NegativeScores(int homeTeamScore, int awayTeamScore, String expectedMessage) {
        Match match = createMatch("Mexico", "Canada");

        try {
            match.updateScores(homeTeamScore, awayTeamScore);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    public void testToString() {
        Match match = createMatch("Mexico", "Canada");
        assertEquals("Mexico 0 Canada 0", match.toString());
    }

    private void runCreateMatchTests(){
        testCreateMatch();

        testCreateMatch_EmptyHomeTeamName();

        testCreateMatch_NullHomeTeamName();

        testCreateMatch_EmptyAwayTeamName();

        testCreateMatch_NullAwayTeamName();

        testCreateMatch_SameHomeAndAwayTeam();
    }

    private void runUpdateMatchTests() {
        testUpdateMatch();

        testUpdateMatch_NegativeScores(-1, 1, "Home team score cannot be negative");
        testUpdateMatch_NegativeScores(2, -1, "Away team score cannot be negative");
        testUpdateMatch_NegativeScores(-1, -1, "Home team score cannot be negative, Away team score cannot be negative");
    }
}
