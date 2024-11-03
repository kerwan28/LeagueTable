package Service;

import Interface.ServiceInterface;
import Pojo.Score;

import java.util.Comparator;
import java.util.List;

public class LeagueUtils implements ServiceInterface {

    public static void storeResults(String line, List<String> results, List<String> temp){
        String s = line.replaceAll("[^a-zA-Z0-9]\s", "");
        //Splitting to remove everything besides words and digits
        String[] parts = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        results.add(parts[0].stripTrailing());
        results.add(parts[1]);
        results.add(parts[2].strip());
        results.add(parts[3]);
        String team1 = parts[0].stripTrailing(), team2 = parts[2].strip();
        if(!temp.contains(team1)) {
            temp.add(team1);
        }
        if(!temp.contains(team2)) {
            temp.add(team2);
        }
    }

    public static void calculatePoints(List<String> temp,List<String> results ,List<Score> scores){
        for (String team : temp) {
            int total = 0;
            for (int j = 0; j < results.size(); j += 4) {

                if (team.equalsIgnoreCase(results.get(j))) {
                    if (Integer.parseInt(results.get(j + 1)) > Integer.parseInt(results.get(j + 3))) {
                        total += 3;
                    } else if (Integer.parseInt(results.get(j + 1)) == Integer.parseInt(results.get(j + 3))) {
                        total += 1;
                    }
                } else if (team.equalsIgnoreCase(results.get(j + 2))) {
                    if (Integer.parseInt(results.get(j + 3)) > Integer.parseInt(results.get(j + 1))) {
                        total += 3;
                    } else if (Integer.parseInt(results.get(j + 3)) == Integer.parseInt(results.get(j + 1))) {
                        total += 1;
                    }
                }
            }
            Score newScore = new Score(total, team);
            if (!scores.contains(newScore)) {
                scores.add(newScore);
            }
        }
        scores.sort(Comparator.comparing(Score::getScore).reversed().thenComparing(Score::getName));
    }

}
