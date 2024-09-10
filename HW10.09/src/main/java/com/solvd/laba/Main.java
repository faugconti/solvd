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
            Menu.displayMenu(inputFile,outputFile,loggerAccumulator);
        }
    }
