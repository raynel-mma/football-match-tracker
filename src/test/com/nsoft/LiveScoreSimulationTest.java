package com.nsoft;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.nsoft.util.AssertionUtils.assertEquals;
import static com.nsoft.util.AssertionUtils.assertTrue;

public class LiveScoreSimulationTest {

    private LiveScoreSimulation liveScoreSimulation;

    public LiveScoreSimulationTest() {
        this.liveScoreSimulation = new LiveScoreSimulation();
    }

    public static void main(String[] args) {
        LiveScoreSimulationTest liveScoreSimulationTest = new LiveScoreSimulationTest();

        liveScoreSimulationTest.runTests();
    }

    private void runTests() {
        testStartMatches(5);
        testStartMatches(6);

        testPrintTeams();

        testPrintScoreboard();
    }

    public void testStartMatches(int totalMatches) {
        try {
            liveScoreSimulation.startMatches(totalMatches);
        } catch (IllegalStateException e) {
            assertEquals("Not enough teams to create matches", e.getMessage());
        }
    }

    public void testPrintTeams() {
        ByteArrayOutputStream outputStream = redirectSystemOut();

        liveScoreSimulation.printTeams();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("TEAMS"), "Expected output to contain header 'TEAMS'");
    }

    public void testPrintScoreboard() {
        ByteArrayOutputStream outputStream = redirectSystemOut();

        liveScoreSimulation.printScoreboard();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("SCOREBOARD"), "Expected output to contain header 'SCOREBOARD'");
    }

    private ByteArrayOutputStream redirectSystemOut() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }

}
