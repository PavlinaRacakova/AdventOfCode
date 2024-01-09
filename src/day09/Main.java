package day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");
        List<Integer> finalValues = new ArrayList<>();

        for (String line : lines) {
            List<Integer> dataNumbers = new ArrayList<>();
            Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .forEach(dataNumbers::add);

            finalValues.add(findMissingValue(dataNumbers));
        }

        System.out.println(finalValues.stream().mapToInt(Integer::intValue).sum());
    }

    private static Integer findMissingValue(List<Integer> dataNumbers) {
        if (arrayConsistsOnlyOfZeros(dataNumbers)) {
            return 0;
        } else {
            int intToAdd = findMissingValue(modifyTheList(dataNumbers));
            dataNumbers.add(dataNumbers.get(dataNumbers.size() - 1) + intToAdd);
        }
        return dataNumbers.get(dataNumbers.size() - 1);
    }

    private static List<Integer> modifyTheList(List<Integer> dataNumbers) {
        List<Integer> modifiedList = new ArrayList<>();
        for (int i = 0; i < dataNumbers.size() - 1; i++) {
            modifiedList.add(Math.abs(dataNumbers.get(i) - dataNumbers.get(i + 1)));
        }
        return modifiedList;
    }

    private static boolean arrayConsistsOnlyOfZeros(List<Integer> numbersToCheck) {
        for (int i : numbersToCheck) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}