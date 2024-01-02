package day07;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Hand implements Comparable<Hand> {

    private final String value;
    private final int bid;
    private int typeRank = 1;
    private final HashMap<Character, Integer> cards = new HashMap<>();

    public Hand(String value, int bid) {
        this.value = value;
        this.bid = bid;
        calculateTypeRank();
    }

    public int getCardValue(int position) {
        return CardStrengthManager.getCardStrength(String.valueOf(value.charAt(position)));
    }

    private void calculateTypeRank() {

        fillTheHashMap();

        if(cards.size() == 1) {
            typeRank = 7;
        } else if(isFourOfAKind()) {
            typeRank = 6;
        } else if (cards.size() == 2) {
            typeRank = 5;
        } else if (isThreeOfAKind()) {
            typeRank = 4;
        } else if (cards.size() == 3) {
            typeRank = 3;
        } else if (cards.size() == 4) {
            typeRank = 2;
        }
    }

    private void fillTheHashMap() {
        value.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> cards.put(c, cards.getOrDefault(c, 0) + 1));
    }

    private boolean isFourOfAKind() {
        return cards.values().stream().anyMatch(i -> i == 4);
    }

    private boolean isThreeOfAKind() {
        return cards.values().stream().anyMatch(i -> i == 3);
    }

    @Override
    public int compareTo(Hand o) {

        if (this.typeRank != o.typeRank) {
            return Integer.compare(this.typeRank, o.typeRank);
        }

        for (int i = 0; i < 5; i++) {
            int comparison = Integer.compare(this.getCardValue(i), o.getCardValue(i));
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }
}
