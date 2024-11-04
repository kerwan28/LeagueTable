package org.leaguetable.Service;

import org.leaguetable.Model.ClubData;

import java.util.List;
import java.util.Set;

public interface LeagueService {

    public void storeResults(String line, List<String> results, Set<String> teams);
    public void calculatePoints(Set<String> teams, List<String> results, List<ClubData> scores);
}
