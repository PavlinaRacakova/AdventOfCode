package day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.useDelimiter("\\A").next().split("\n");
        List<Hand> hands = new ArrayList<>();

        for(String line : lines) {
            hands.add(extractHand(line));
        }

        Collections.sort(hands);

        System.out.println(hands.stream()
                .mapToLong(hand -> (long) hand.getBid() * (hands.indexOf(hand) + 1))
                .sum());
    }

    private static Hand extractHand(String line) {
        String[] valuesToUse = line.trim().split(" ");
        return new Hand(valuesToUse[0], Integer.parseInt(valuesToUse[1]));
    }
}