package com.nsoft;

import com.nsoft.service.MatchService;
import com.nsoft.util.TeamProvider;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import static com.nsoft.util.TeamProvider.TOTAL_MATCHES;

public class LiveScoreSimulation {

    private final MatchService matchService;
    private static final int SLEEP_TIME = 3000;

    private static final Logger LOGGER = Logger.getLogger(LiveScoreSimulation.class.getName());

    public LiveScoreSimulation(MatchService matchService) {
        this.matchService = matchService;
    }

    public static void main(String[] args) {
        runSimulation();
    }

    private static void runSimulation(){
        LiveScoreSimulation liveScoreSimulation = new LiveScoreSimulation(new MatchService(new ArrayList<>()));

        liveScoreSimulation.printTeams();

        liveScoreSimulation.startMatches(TOTAL_MATCHES);

        liveScoreSimulation.startLiveUpdates();
    }

    public void startMatches(int totalMatches){
        for (int i = 0; i < totalMatches; i++) {
            matchService.startMatch(matchService.createRandomMatch());
        }
    }

    public void printTeams() {
        System.out.println("\n--- TEAMS ---\n");
        TeamProvider.getTeams().forEach(System.out::println);
    }

    public void printScoreboard() {
        if(matchService.getScoreboard().size() > 1){
            System.out.println("\n--- SCOREBOARD ---\n");
            matchService.getScoreboard().forEach(System.out::println);
        }
    }

    public void startLiveUpdates() {
        Thread updaterThread = new Thread(() -> {
            while (!matchService.getScoreboard().isEmpty()) {
                try {
                    Thread.sleep(SLEEP_TIME);

                    matchService.updateRandomMatch();

                    printScoreboard();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    LOGGER.log(Level.SEVERE, "Live updates stopped due to interruption " + e.getMessage());

                    break;
                }
            }
        });
        updaterThread.start();
    }

    public MatchService getMatchService() {
        return matchService;
    }

}
