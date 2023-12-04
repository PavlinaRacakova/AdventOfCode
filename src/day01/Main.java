package day01;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");
        long finalResult = 0;

        for(String line : lines) {
            finalResult += Integer.parseInt(findCoordinates(line));
        }

        System.out.println(finalResult);
    }

    public static String findCoordinates(String line) {
        line = replaceWrittenDigits(line);
        int firstCoordinate = Integer.parseInt(String.valueOf(line.charAt(firstIndexOfRegex(line))));
        StringBuilder builder = new StringBuilder(line);
        builder.reverse();
        int secondCoordinate = Integer.parseInt(String.valueOf(builder.charAt(firstIndexOfRegex(String.valueOf(builder)))));
        return String.valueOf(firstCoordinate) + secondCoordinate;
    }

    public static int firstIndexOfRegex(String line) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);
        int index = 0;

        if (matcher.find())
        {
            index = matcher.start();
        }

        return index;
    }

    public static String replaceWrittenDigits(String line) {
        line = line.replaceAll("one", "o1e");
        line = line.replaceAll("two", "t2o");
        line = line.replaceAll("three", "th3ee");
        line = line.replaceAll("four", "f4ur");
        line = line.replaceAll("five", "f5ve");
        line = line.replaceAll("six", "s6x");
        line = line.replaceAll("seven", "se7en");
        line = line.replaceAll("eight", "ei8ht");
        line = line.replaceAll("nine", "n9ine");
        return line;
    }
}