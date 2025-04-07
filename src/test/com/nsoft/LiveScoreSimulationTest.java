package com.nsoft;

import com.nsoft.service.MatchService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.nsoft.util.AssertionUtils.assertEquals;
import static com.nsoft.util.AssertionUtils.assertTrue;
import static com.nsoft.util.TeamProvider.TOTAL_MATCHES;

public class LiveScoreSimulationTest {

    private LiveScoreSimulation liveScoreSimulation;

    public LiveScoreSimulationTest() {
        this.liveScoreSimulation = new LiveScoreSimulation(new MatchService(new ArrayList<>()));
    }

    public static void main(String[] args) {
        LiveScoreSimulationTest liveScoreSimulationTest = new LiveScoreSimulationTest();

        liveScoreSimulationTest.runTests();
    }

    private void runTests() {
        testStartMatches(TOTAL_MATCHES);
        tearDown();

        testStartMatches(6);
        tearDown();

        testPrintTeams();
        tearDown();

        testPrintScoreboard();
        tearDown();

        testStartLiveUpdates();
        tearDown();
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

        try {
            liveScoreSimulation.startMatches(TOTAL_MATCHES);
        } catch (IllegalStateException e) {
            assertEquals("Not enough teams to create matches", e.getMessage());
        }

        liveScoreSimulation.printScoreboard();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("SCOREBOARD"), "Expected output to contain header 'SCOREBOARD'");
    }

    public void testStartLiveUpdates() {
        liveScoreSimulation.startMatches(TOTAL_MATCHES);

        Thread updaterThread = new Thread(liveScoreSimulation::startLiveUpdates);
        updaterThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Test sleep interrupted: " + e.getMessage());
        }

        updaterThread.interrupt();
        assertTrue(!updaterThread.isAlive(), "Updater thread should be interrupted.");

        try {
            updaterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread join interrupted: " + e.getMessage());
        }
    }

    private ByteArrayOutputStream redirectSystemOut() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }

    private void tearDown() {
        if (liveScoreSimulation.getMatchService().resetTeamsList()) {
            System.out.println("Teams list has been set to the default state");
        }
        if (!liveScoreSimulation.getMatchService().clearScoreboard()) {
            System.out.println("Scoreboard has not been cleared");
        }
    }

}
