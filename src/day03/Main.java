package day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.useDelimiter("\\A").next();

        String[][] givenArray = input.lines().map(line -> line.split(""))
                .toArray(String[][]::new);

/*
 FIRST PART OF PUZZLE
        List<Integer> attachedNumbers = getNumbersAttachedBySpecialSymbol(givenArray);
        System.out.println(attachedNumbers.stream().mapToInt(Integer::intValue).sum());
 */
    }

    /*
    First part method
     */
    private static List<Integer> getNumbersAttachedBySpecialSymbol(String[][] givenArray) {
        List<Integer> attachedNumbers = new ArrayList<>();

        for (int i = 0; i < givenArray.length; i++) {
            for (int j = 0; j < givenArray[i].length; j++) {

                if (givenArray[i][j].matches("\\d")) {

                    String wholeNumber = findWholeEncounteredNumber(givenArray, i, j);
                    if (numberHasAdjacentSymbol(givenArray, wholeNumber.length(), i, j)) {
                        attachedNumbers.add(Integer.parseInt(wholeNumber));
                    }
                    j += wholeNumber.length() - 1;
                }

            }
        }
        return attachedNumbers;
    }

    /*
    First part method
     */
    private static boolean numberHasAdjacentSymbol(String[][] grid, int lengthOfDigit, int i, int j) {
        int endingJPosition = j + lengthOfDigit - 1;
        int numRows = grid.length;
        int numCols = grid[0].length;

        for (int row = Math.max(0, i - 1); row <= Math.min(i + 1, numRows - 1); row++) {
            for (int column = Math.max(0, j - 1); column <= Math.min(endingJPosition + 1, numCols - 1); column++) {
                if (!((row == i && column >= j && column <= endingJPosition))) {
                    if (!grid[row][column].matches("[.\\d]")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    First part method
     */
    public static String findWholeEncounteredNumber(String[][] grid, int i, int j) {

        StringBuilder result = new StringBuilder();

        while (j < grid[i].length && grid[i][j].matches("\\d")) {
            result.append(grid[i][j]);
            j++;
        }

        return result.toString();
    }
}
