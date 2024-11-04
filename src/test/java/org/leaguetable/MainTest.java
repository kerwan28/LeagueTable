package org.leaguetable;

import org.leaguetable.Model.ClubData;
import org.leaguetable.Service.LeagueService;
import org.leaguetable.Service.LeagueServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MainTest {

    @Test
    void shouldSplitTeamNamesAndScoresCorrectly() {
        String input = "FC Awesome 1, Groaches 2";
        String[] expectedOutput = {"FC Awesome", "1", "Groaches", "2"};

        List<String> actualOutput = splitTeamAndScore(input);

        log.info("Checking expected output: " + expectedOutput[0] + " against actual input");
        assertEquals(expectedOutput[0], actualOutput.get(0), "The split result should match the expected output");
    }

    private List<String> splitTeamAndScore(String s) {
        List<String> results = new ArrayList<>();
        String[] parts = s.split("\\s*,?\\s*(?<=\\D)(?=\\d)|(?<=\\d)\\s*,?\\s*(?=\\D)");

        List<String> filteredParts = Arrays.stream(parts)
                .filter(part -> !part.isEmpty())
                .map(String::trim)
                .toList();

        // Add filtered parts to results
        results.add(filteredParts.get(0));  // Team 1 name
        results.add(filteredParts.get(1));  // Team 1 score
        results.add(filteredParts.get(2));  // Team 2 name
        results.add(filteredParts.get(3));

        return results;
    }

    @Test
    public void testStoreResults() {
        String line = "Lions 4, Snakes 0";
        List<String> teams = new ArrayList<>();
        Set<String> results = new HashSet<>();
        LeagueService leagueService = new LeagueServiceImplementation();
        leagueService.storeResults(line, teams, results);

        log.info("Checking that team names are extracted correctly");
        List<String> sortedResults = new ArrayList<>(results);
        Collections.sort(sortedResults);
        assertEquals(sortedResults.get(0), "Lions");
        assertEquals(sortedResults.get(1), "Snakes");

        log.info("Teams extracted: {}", sortedResults);
    }

    @Test
    public void testStoreResults2() {
        String line = "FC Awesome 1, Tarantulas 3";
        List<String> teams = new ArrayList<>();
        Set<String> results = new HashSet<>();
        LeagueService leagueService = new LeagueServiceImplementation();
        leagueService.storeResults(line, teams, results);

        log.info("Checking that team names with whitespace are extracted correctly");
        List<String> sortedResults = new ArrayList<>(results);
        Collections.sort(sortedResults);
        assertEquals(sortedResults.get(0), "FC Awesome");
        assertEquals(sortedResults.get(1), "Tarantulas");

        log.info("Teams extracted: {}", sortedResults);
    }

    @Test
    public void calculateResults(){
        Set<String> teams = new HashSet<>();
        teams.add("Lions");
        teams.add("Tarantulas");
        teams.add("FC Awesome");

        List<String> results = new ArrayList<>();
        results.add("FC Awesome");
        results.add("2");
        results.add("Tarantulas");
        results.add("2");
        results.add("Lions");
        results.add("4");
        results.add("Tarantulas");
        results.add("0");

        List<ClubData> scores = new ArrayList<>();
        LeagueService leagueService = new LeagueServiceImplementation();
        leagueService.calculatePoints(teams,results,scores);

        assertEquals("Lions", scores.get(0).getName());
        assertEquals(3, scores.get(0).getScore());
    }
}