package day04;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");

        // System.out.println(computeResultsByPartOneRules(lines));
        System.out.println(computeResultsByPartTwoRules(lines));
    }

    private static int computeResultsByPartTwoRules(String[] lines) {
        Map<Integer, Integer> scratchcardsWon = new HashMap<>();

        for (int i = 0; i < lines.length; i++) {

            //adds one original card into map containing all retrieved scratchcards
            scratchcardsWon.merge(i + 1, 1, Integer::sum);

            //finds out how many numbers were winning
            List<Integer> givenNumbers = getGivenNumbers(lines[i]);
            givenNumbers.retainAll(getWinningNumbers(lines[i]));
            int scratchcardsWinInThisRoundCounter = givenNumbers.size();

            //updates map containing all retrieved scratchcards
            for (int j = 1; j <= scratchcardsWinInThisRoundCounter; j++) {
                scratchcardsWon.merge(i + j + 1, scratchcardsWon.get(i + 1), Integer::sum);
            }

        }

        return scratchcardsWon.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static int computeResultsByPartOneRules(String[] lines) {
        int totalPoints = 0;

        for (String line : lines) {
            int pointsInThisRound = 0;
            List<Integer> winningNumbers = getWinningNumbers(line);
            List<Integer> givenNumbers = getGivenNumbers(line);

            for (int num : givenNumbers) {
                if (winningNumbers.contains(num)) {
                    pointsInThisRound = pointsInThisRound == 0 ? 1 : pointsInThisRound * 2;
                }
            }

            totalPoints += pointsInThisRound;
        }
        return totalPoints;
    }

    private static List<Integer> getWinningNumbers(String line) {
        String numbers = line.substring(line.indexOf(":") + 1, line.indexOf("|")).trim();

        return Arrays.stream(numbers.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static List<Integer> getGivenNumbers(String line) {
        String numbers = line.substring(line.indexOf("|") + 1).trim();

        return Arrays.stream(numbers.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
