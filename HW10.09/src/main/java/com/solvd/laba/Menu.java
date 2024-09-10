package com.solvd.laba;
import java.util.List;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu(String inputFilePath,String outputFilePath, List<String> logger){
        while (true) {
            String text = Utils.readFile(inputFilePath,logger); //base text if existing
            System.out.println("\n1. Enter text to file\n2. Count unique words\n3. Count letters\n4. Find word matches\n5. Exit");
            System.out.print("Choose an option: ");

            String inputChoice = scanner.nextLine();

            switch (inputChoice) {
                case "1":
                    Utils.enterTextToFile(inputFilePath,logger);
                    break;
                case "2":
                    Utils.countUniqueWords(text,logger);
                    break;
                case "3":
                    Utils.countLetters(text,logger);
                    break;
                case "4":
                    Utils.findMatches(text,logger);
                    break;
                case "5":
                    Utils.writeLogFile(outputFilePath,logger);
                    System.out.println("Exiting..");
                    return;
                default:
                    System.out.println("Wrong option. Try again.");
            }
        }
    }



}
