package org.leaguetable;

import org.leaguetable.Model.ClubData;
import org.leaguetable.Service.LeagueService;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.leaguetable.Service.LeagueServiceImplementation;

@Slf4j
public class Main {

    public static void main(String[] args) {

        do {

            try {

                Scanner scanner = new Scanner(System.in);
                LeagueService leagueService = new LeagueServiceImplementation();

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
                        Set<String> temp = new HashSet<>();
                        List<String> results = new ArrayList<>();

                        String line;

                        while ((line = br.readLine()) != null) {
                            leagueService.storeResults(line, results, temp);
                        }
                        List<ClubData> scores = new ArrayList<>();
                        leagueService.calculatePoints(temp, results, scores);

                        int counter = 1;
                        for (ClubData s : scores) {
                            System.out.println(counter + ". " + s.getName() + ", " + s.getScore() + " pts");
                            counter++;
                        }
                        System.out.println();
                        System.out.println("1: Return to main menu");
                        System.out.println("2: Exit");

                        int choice2 = scanner.nextInt();

                        if (choice2 == 1) {
                            continue;
                        } else if (choice2 == 2) {
                            System.out.println("Goodbye");
                            System.exit(0);
                        }else{
                            System.out.println("Invalid number. Please select only numbers from the given list");
                            System.out.println("Returning to main menu");
                        }

                    } catch (IOException e) {
                        log.info(e.toString());
                    }

                } else if (choice == 2) {
                    System.out.println("Goodbye");
                    System.exit(0);
                } else {
                    System.out.println("Invalid number. Please select only numbers from the given list");
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid number. Please enter a number from the given list");
                System.out.println();
            }
        }while(true);
    }
}