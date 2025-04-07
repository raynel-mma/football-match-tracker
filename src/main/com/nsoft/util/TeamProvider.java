package com.nsoft.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamProvider {
    private final static List<String> TEAMS = new ArrayList<>(List.of("Mexico", "Canada", "Spain", "Brazil", "Germany", "France", "Uruguay", "Italy", "Argentina", "Australia"));

    public final static int TOTAL_MATCHES = 5;

    public static List<String> getTeams() {
        return Collections.unmodifiableList(TEAMS);
    }
}
