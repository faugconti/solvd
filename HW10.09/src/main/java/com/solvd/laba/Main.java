package com.solvd.laba;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> loggerAccumulator = new ArrayList<>();
    private static final String inputFile = "src/main/resources/sample.txt";
    private static final String outputFile = "src/main/resources/log.txt";

        public static void main(String[] args) {
            while (true) {
                String text = Utils.readFile(inputFile,loggerAccumulator); //base text if existing
                System.out.println("\n1. Enter text to file\n2. Count unique words\n3. Count letters\n4. Find word matches\n5. Exit");
                System.out.print("Choose an option: ");

                String inputChoice = scanner.nextLine();

                switch (inputChoice) {
                    case "1":
                        Utils.enterTextToFile(inputFile,loggerAccumulator);
                        break;
                    case "2":
                        Utils.countUniqueWords(text,loggerAccumulator);
                        break;
                    case "3":
                        Utils.countLetters(text,loggerAccumulator);
                        break;
                    case "4":
                        Utils.findMatches(text,loggerAccumulator);
                        break;
                    case "5":
                        Utils.writeLogFile(outputFile,loggerAccumulator);
                        System.out.println("Exiting..");
                        return;
                    default:
                        System.out.println("Wrong option. Try again.");
                }
            }
        }
    }
