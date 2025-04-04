package com.nsoft.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static com.nsoft.model.MatchValidator.validateScores;
import static com.nsoft.model.MatchValidator.validateTeams;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Date startTime;
    private int duration;
    private int totalScore;
    private boolean isFinished;

    private static final Logger logger = Logger.getLogger(Match.class.getName());

    private Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.totalScore = 0;
        this.duration = 0;
        this.isFinished = false;

        setStartTime();
    }

    static {
        setupLogging();
    }

    public static Match createMatch(String homeTeam, String awayTeam){
        validateTeams(homeTeam, awayTeam);
        return new Match(homeTeam, awayTeam);
    }

    public void updateScores(int homeTeamScore, int awayTeamScore) {
        validateScores(homeTeamScore, awayTeamScore);
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Date getStartTime() {
        return startTime;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void updateTotalScore() {
        this.totalScore = this.homeTeamScore + this.awayTeamScore;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        logger.info(homeTeam + " " + homeTeamScore + " " + awayTeam + " " + awayTeamScore + " Start time: " + startTime + ", Duration: " + duration);

        return homeTeam + " " + homeTeamScore + " " + awayTeam + " " + awayTeamScore;
    }

    private void setStartTime(){
        long randomTimeOffset = (long) (Math.random() * 24 * 60 * 60 * 1000);
        this.startTime = new Date(System.currentTimeMillis() - randomTimeOffset);
    }

    private static void setupLogging(){
        try {
            Files.createDirectories(Paths.get("src/main/resources/logs"));

            String logFilePath = "src/main/resources/logs/output_" + System.currentTimeMillis() + ".log";

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
