package com.nsoft;

import com.nsoft.service.MatchService;

import java.util.ArrayList;

public class LiveScoreSimulation {

    private final MatchService matchService;

    public LiveScoreSimulation() {
        this.matchService = new MatchService(new ArrayList<>());
    }

    public static void main(String[] args) {
        LiveScoreSimulation liveScoreSimulation = new LiveScoreSimulation();

        liveScoreSimulation.printTeams();

        liveScoreSimulation.startMatches(5);

        liveScoreSimulation.startLiveUpdates();
    }

    public void startMatches(int totalMatches){
        for (int i = 0; i < totalMatches; i++) {
            matchService.startMatch(matchService.createRandomMatch());
        }
    }

    public void printTeams() {
        System.out.println("\n--- TEAMS ---\n");
        matchService.getTeams().forEach(System.out::println);
    }

    public void printScoreboard() {
        if(matchService.getScoreboard().size() > 1){
            System.out.println("\n--- SCOREBOARD ---\n");
            matchService.getScoreboard().forEach(System.out::println);
        }
    }

    public void startLiveUpdates() {
        Thread updaterThread = new Thread(() -> {
            while (matchService.getScoreboard().size() > 0) {
                try {
                    Thread.sleep(3000);

                    matchService.updateRandomMatch();

                    printScoreboard();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    System.out.println("Live updates stopped.");

                    break;
                }
            }
        });
        updaterThread.start();
    }
}
