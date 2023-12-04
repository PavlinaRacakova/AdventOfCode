package day02;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");
        System.out.println("Indexes of valid games: " + countIndexesOfValidGames(lines));
        System.out.println("Sums of powers of games: " + countSumOfPowersOfAllGamesStats(lines));
    }

    private static int countIndexesOfValidGames(String[] lines) {
        int result = 0;

        for (int i = 0; i < lines.length; i++) {
            Map<String, Integer> drawnCubes = findHighestDrawnNumbersInGivenGame(lines[i]);
            if (isValidGame(drawnCubes)) {
                result += i + 1;
            }
        }
        return result;
    }

    private static Map<String, Integer> findHighestDrawnNumbersInGivenGame(String line) {
        Map<String, Integer> drawnCubes = new HashMap<>();
        String[] colors = {"red", "blue", "green"};

        for (String round : line.split(";")) {
            for (int i = 0; i < 3; i++) {
                Pattern pattern = Pattern.compile("\\d+\\s" + colors[i]);
                Matcher matcher = pattern.matcher(round);

                if (matcher.find()) {
                    int numberOfCubes = Integer.parseInt(matcher.group().replaceAll("\\D", ""));
                    drawnCubes.compute(colors[i], (key, value) -> value == null ? numberOfCubes :
                            numberOfCubes > value ? numberOfCubes : value);
                }
            }
        }

        return drawnCubes;
    }

    private static boolean isValidGame(Map<String, Integer> map) {
        return map.getOrDefault("red", 0) <= 12 &&
                map.getOrDefault("green", 0) <= 13 &&
                map.getOrDefault("blue", 0) <= 14;
    }

    private static long countSumOfPowersOfAllGamesStats(String[] lines) {
        long result = 0;

        for (String line : lines) {
            Map<String, Integer> drawnCubes = findHighestDrawnNumbersInGivenGame(line);
            result += ((long) drawnCubes.getOrDefault("red", 1) *
                    drawnCubes.getOrDefault("green", 1) *
                    drawnCubes.getOrDefault("blue", 1));
        }

        return result;
    }
}