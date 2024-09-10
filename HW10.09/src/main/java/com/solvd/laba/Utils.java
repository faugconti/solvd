package com.solvd.laba;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);


    protected static String readFile(String fileName,List<String> logger){
        try{
            return FileUtils.readFileToString(new File(fileName),StandardCharsets.UTF_8);
        }catch(IOException exc){
            System.out.println("error reading file "+exc.getMessage());
            logger.add("error reading file "+exc.getMessage());
            return "";
        }
    }

     protected static void writeLogFile(String fileName,List<String> logger){
         try {
             FileUtils.writeLines(new File(fileName), logger);
             System.out.println("Results written to "+fileName);
         } catch (IOException e) {
             System.out.println("Error writing results to file: " + e.getMessage());
         }
     }


    protected static void enterTextToFile(String fileName, List<String> logger){
        System.out.print("Enter new text: ");
        String newText = "\n"+scanner.nextLine();
        try{
            FileUtils.write(new File(fileName),newText,StandardCharsets.UTF_8,true);
        }catch (IOException exc) {
            throw new RuntimeException(exc);
        }

        logger.add("text saved in file: " + newText);
    }

    protected static void countUniqueWords(String text,List<String> logger){
        if(checkIfEmpty(text)) return;

        String[] words = StringUtils.split(text); //remove white spaces and create array
        int count = Arrays.stream(words).map(StringUtils::lowerCase).collect(Collectors.toSet()).size(); 
        System.out.println(count);
        logger.add("Unique words counted: " + count);
    }

    protected static void countLetters(String text,List<String> logger){
        if(checkIfEmpty(text)) return;

        text = StringUtils.deleteWhitespace(text);
        int count = StringUtils.replaceChars(text,"0123456789()!¡¿?","").length();
        System.out.println(count);
        logger.add("Letters counted: " + count);

    }

    protected static void findMatches(String text,List<String> logger){
        if(checkIfEmpty(text)) return;

        System.out.print("Enter a word: ");
        String word = new Scanner(System.in).nextLine().trim();

        if (StringUtils.length(word) <= 1) {
            System.out.println("Error: Please enter a word with more than one letter.");
            logger.add("Word match error: your input is too short");
            return;
        }


        int count = StringUtils.countMatches(StringUtils.lowerCase(text), StringUtils.lowerCase(word));
        System.out.println("The word '" + word + "' matches " + count + " times in the text.");
        logger.add("The word '" + word + "' matches " + count + " times in the text.");

    }

    private static boolean checkIfEmpty(String text){
        if (StringUtils.isBlank(text)) {
            System.out.println("No characters found.");
            return true;
        }
        return false;
    }

}
