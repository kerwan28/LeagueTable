package org.leaguetable.Service;

import org.leaguetable.Model.ClubData;

import java.util.*;

public class LeagueServiceImplementation implements LeagueService {

    public static void storeResults(String line, List<String> results, Set<String> teams) {
        // Updated regex to handle commas and whitespace without introducing empty elements
        String[] parts = line.split("\\s*,?\\s*(?<=\\D)(?=\\d)|(?<=\\d)\\s*,?\\s*(?=\\D)");

        // Filter out any empty parts
        List<String> filteredParts = Arrays.stream(parts)
                .filter(part -> !part.isEmpty())
                .map(String::trim)
                .toList();

        // Add filtered parts to results
        results.add(filteredParts.get(0));  // Team 1 name
        results.add(filteredParts.get(1));  // Team 1 score
        results.add(filteredParts.get(2));  // Team 2 name
        results.add(filteredParts.get(3));  // Team 2 score

        // Add teams to the set to ensure no duplicates
        teams.add(filteredParts.get(0));
        teams.add(filteredParts.get(2));
    }



    public static void calculatePoints(Set<String> teams, List<String> results, List<ClubData> scores) {
        Map<String, Integer> teamPoints = new HashMap<>();

        // Initialize team points
        for (String team : teams) {
            teamPoints.put(team, 0);
        }

        // Process each fixture in the results
        for (int i = 0; i < results.size(); i += 4) {
            String team1 = results.get(i);
            int score1 = Integer.parseInt(results.get(i + 1));
            String team2 = results.get(i + 2);
            int score2 = Integer.parseInt(results.get(i + 3));

            // Calculate points for the match
            int team1Points = calculateMatchPoints(score1, score2, true);
            int team2Points = calculateMatchPoints(score1, score2, false);

            // Update total points for each team
            teamPoints.put(team1, teamPoints.get(team1) + team1Points);
            teamPoints.put(team2, teamPoints.get(team2) + team2Points);
        }

        // Populate the scores list
        scores.clear();
        for (Map.Entry<String, Integer> entry : teamPoints.entrySet()) {
            scores.add(new ClubData(entry.getValue(), entry.getKey()));
        }

        // Sort scores in descending order by points, then alphabetically by team name
        scores.sort(Comparator.comparing(ClubData::getScore).reversed().thenComparing(ClubData::getName));
    }

    public static int calculateMatchPoints(int score1, int score2, boolean isTeam1) {
        if (score1 > score2) return isTeam1 ? 3 : 0;
        if (score1 < score2) return isTeam1 ? 0 : 3;
        return 1;  // A draw
    }

}
