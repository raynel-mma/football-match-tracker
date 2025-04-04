package com.nsoft;

import com.nsoft.service.MatchService;

import java.util.ArrayList;

public class LiveScoreSimulationTest {

    private MatchService matchService;

    public LiveScoreSimulationTest(MatchService matchService) {
        this.matchService = matchService;
    }

    public static void main(String[] args) {
        LiveScoreSimulationTest liveScoreSimulationTest = new LiveScoreSimulationTest(new MatchService(new ArrayList<>()));

        liveScoreSimulationTest.runTests();
    }

    private void runTests() {
    }//todo
}
