package org.leaguetable;

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
        LeagueServiceImplementation.storeResults(line, teams, results);

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
        LeagueServiceImplementation.storeResults(line, teams, results);

        log.info("Checking that team names with whitespace are extracted correctly");
        List<String> sortedResults = new ArrayList<>(results);
        Collections.sort(sortedResults);
        assertEquals(sortedResults.get(0), "FC Awesome");
        assertEquals(sortedResults.get(1), "Tarantulas");

        log.info("Teams extracted: {}", sortedResults);
    }

    @Test
    public void testPointsCalculation() {
        int points1 = LeagueServiceImplementation.calculateMatchPoints(3,0,true);
        int points2 = LeagueServiceImplementation.calculateMatchPoints(1,3, true);
        int points3 = LeagueServiceImplementation.calculateMatchPoints(2,2,false);
        assertEquals(3, points1);
        assertEquals(0, points2);
        assertEquals(1, points3);
    }


}