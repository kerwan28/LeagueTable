package Interface;

import Pojo.Score;

import java.util.List;
import java.util.Set;

public interface ServiceInterface {

    public static void storeResults(String line, List<String> results, Set<String> teams) {}
    public static void calculatePoints(Set<String> teams, List<String> results, List<Score> scores){}


}
