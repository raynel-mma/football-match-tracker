package com.nsoft.service;

import java.util.ArrayList;

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
        //todo
    }

    /**
     * Test: Ensure that a team cannot be added to the scoreboard more than once while it is already involved in an ongoing match
     * Ex1. Spain - Brazil, Uruguay - Spain, Ex2. Spain - Brazil, Uruguay - Brazil
     */
    public void testStartMatch_TeamIsAlreadyPlaying() {
        //todo
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
