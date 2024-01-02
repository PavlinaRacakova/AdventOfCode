package day07;

import java.util.HashMap;
import java.util.Map;

public class CardStrengthManager {

    private static final Map<String, Integer> strengths = new HashMap<>();

    static {
        String[] cards = {"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};

        for (String card : cards) {
            int value = switch (card) {
                case "A" -> 14;
                case "K" -> 13;
                case "Q" -> 12;
                case "J" -> 11;
                case "T" -> 10;
                default -> Integer.parseInt(card);
            };
            strengths.put(card, value);
        }
    }

    public static int getCardStrength(String card) {
        return strengths.get(card);
    }
}
