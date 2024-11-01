package org.leaguetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

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
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("******************************************************************************"
                    + "\n                       Ranking Calculator V1.0"
                    + "\n******************************************************************************");

            System.out.println("1: Display rankings");
            System.out.println("2: Exit");
            System.out.println();

            System.out.print("Please enter a number: ");
            int choice = scanner.nextInt();
            if (choice == 1) {

                try {
                    FileReader in = new FileReader("src\\main\\resources\\fixtures.txt");
                    BufferedReader br = new BufferedReader(in);
                    List<String> temp = new ArrayList<>();
                    List<String> results = new ArrayList<>();

                    String line;

                    while ((line = br.readLine()) != null) {
                        storeResults(line, results,temp);
                    }
                    List<Score> scores = new ArrayList<>();
                    calculatePoints(temp,results,scores);
                    scores.sort(Comparator.comparing(Score::getScore).reversed().thenComparing(Score::getName));
                    int counter = 1;
                    for(Score s : scores) {
                        System.out.println(counter + ". " + s.getName() +", " + s.getScore() + " pts");
                        counter++;
                    }
                    System.out.println();
                    System.out.println("1: Return to main menu");
                    System.out.println("2: Exit");

                    int choice2 = scanner.nextInt();

                    if(choice2 == 1){
                        continue;
                    }else if(choice2 == 2){
                        System.out.println("Goodbye");
                        System.exit(0);
                    }

                }catch(IOException e) {
                    e.printStackTrace();
                }

            } else if (choice == 2) {
                System.out.println("Goodbye");
                System.exit(0);
            } else {
                System.out.println("Invalid number. Please select only numbers from the given list");
            }
        }
    }
}