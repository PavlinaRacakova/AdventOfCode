package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //getting input
        Scanner sc = new Scanner(System.in);
        int[] availableSeconds = Arrays.stream(sc.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] bestTimes = Arrays.stream(sc.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> numbersOfTotalWaysToWinTheRace = new ArrayList<>();

        //main loop for getting the result
        for (int i = 0; i < 4; i++) {
            int waysToWinThisRace = 0;
            int halfTime = availableSeconds[i] / 2;

            //loop for the lower half of field
            for (int j = halfTime; j > 0; j--) {
                int millisRemained = availableSeconds[i] - j;
                int traveledDistance = j * millisRemained;
                if (traveledDistance > bestTimes[i]) {
                    waysToWinThisRace++;
                } else {
                    break;
                }
            }

            //loop for the higher half of field
            for (int j = halfTime + 1; j < availableSeconds[i]; j++) {
                int millisRemained = availableSeconds[i] - j;
                int traveledDistance = j * millisRemained;
                if (traveledDistance > bestTimes[i]) {
                    waysToWinThisRace++;
                } else {
                    break;
                }
            }

            //adds number of all possible ways to win in this race
            numbersOfTotalWaysToWinTheRace.add(waysToWinThisRace);
        }

        //prints the result
        System.out.println(numbersOfTotalWaysToWinTheRace.stream()
                .reduce(1, (subtotal, element) -> subtotal * element));
    }
}
