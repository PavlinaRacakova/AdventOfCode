package day01;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");
        long finalResult = 0;

        for (String line : lines) {
            finalResult += Integer.parseInt(findCoordinates(line));
        }

        System.out.println(finalResult);
        sc.close();
    }

    public static String findCoordinates(String line) {
        line = replaceWrittenDigits(line);
        String reversedLine = reverseGivenLine(line);
        String firstCoordinate = String.valueOf(line.charAt(firstIndexOfRegex(line)));
        String secondCoordinate = String.valueOf(reversedLine.charAt(firstIndexOfRegex(reversedLine)));
        return firstCoordinate + secondCoordinate;
    }

    public static int firstIndexOfRegex(String line) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);
        int index = 0;

        if (matcher.find()) {
            index = matcher.start();
        }

        return index;
    }

    public static String replaceWrittenDigits(String line) {
        String[] replacements = {
                "one", "o1e", "two", "t2o", "three", "s3e", "four", "f4r",
                "five", "f5e", "six", "s6x", "seven", "s7n", "eight", "e8t", "nine", "n9e"
        };

        for (int i = 0; i < replacements.length; i += 2) {
            line = line.replaceAll(replacements[i], replacements[i + 1]);
        }

        return line;
    }

    public static String reverseGivenLine(String line) {
        StringBuilder builder = new StringBuilder(line);
        builder.reverse();
        return String.valueOf(builder);
    }
}