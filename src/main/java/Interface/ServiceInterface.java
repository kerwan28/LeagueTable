package Interface;

import org.leaguetable.Score;

import java.util.List;

public interface ServiceInterface {

    public static void storeResults(String line, List<String> results, List<String> temp) {}
    public static void calculatePoints(List<String> temp,List<String> results ,List<Score> scores){}


}
