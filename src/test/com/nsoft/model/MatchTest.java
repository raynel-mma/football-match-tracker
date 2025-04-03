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
        testCreateMatch();

        testCreateMatch_EmptyHomeTeamName();

        testCreateMatch_NullHomeTeamName();

        testCreateMatch_EmptyAwayTeamName();

        testCreateMatch_NullAwayTeamName();

        testCreateMatch_SameHomeAndAwayTeam();

        testUpdateMatch();

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
        Match match = null;
        try {
            match = createMatch("", "Canada");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name cannot be the empty or null", e.getMessage());
            assertTrue(match == null, "Match is not null");
        }
    }

    public void testCreateMatch_NullHomeTeamName() {
        Match match = null;
        try {
            match = createMatch(null, "Canada");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name cannot be the empty or null", e.getMessage());
            assertTrue(match == null, "Match is not null");
        }
    }

    public void testCreateMatch_EmptyAwayTeamName() {
        Match match = null;
        try {
            match = createMatch("Mexico", "");
        } catch (IllegalArgumentException e) {
            assertEquals("Away team name cannot be the empty or null", e.getMessage());
            assertTrue(match == null, "Match is not null");
        }
    }

    public void testCreateMatch_NullAwayTeamName() {
        Match match = null;
        try {
            match = createMatch("Mexico", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Away team name cannot be the empty or null", e.getMessage());
            assertTrue(match == null, "Match is not null");
        }
    }

    public void testCreateMatch_SameHomeAndAwayTeam() {
        //todo
    }

    public void testUpdateMatch() {
        //todo
    }

    public void testToString() {
        //todo
    }
}
