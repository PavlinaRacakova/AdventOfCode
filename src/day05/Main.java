package day05;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //getting seeds values
        Scanner sc = new Scanner(System.in);
        System.out.println("Seeds:");
        List<Long> identifiers = new ArrayList<>();
        String seeds = sc.nextLine().trim();
        Arrays.stream(seeds.split(" ")).mapToLong(Long::parseLong)
                .forEach(identifiers::add);


        //main loop
        for (int i = 0; i < 7; i++) {

            //getting input
            System.out.println("Another step map:");
            List<String> lines = new ArrayList<>();

            while (true) {
                String input = sc.nextLine();
                if (input.equals("/")) {
                    break;
                } else {
                    lines.add(input);
                }
            }


            List<Interval> intervals = getIntervalsFromInput(lines);

            //updating of intervals
            for (int j = 0; j < identifiers.size(); j++) {
                int indexOfSeedsInterval = findSeedsInterval(identifiers.get(j), intervals);
                if (indexOfSeedsInterval != -1) {
                    computeIdentifier(intervals.get(indexOfSeedsInterval), j, identifiers);
                }
            }
        }

        //getting final result
        Collections.sort(identifiers);
        System.out.println(identifiers.get(0));
        sc.close();

    }

    private static void computeIdentifier(Interval interval, int indexOfIdentifierToModify, List<Long> identifiers) {
        long newIdentifier = interval.getDestinationNumber(identifiers.get(indexOfIdentifierToModify));
        identifiers.set(indexOfIdentifierToModify, newIdentifier);
    }

    private static int findSeedsInterval(Long seed, List<Interval> intervals) {

        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).isWithinRange(seed)) {
                return i;
            }
        }

        return -1;
    }

    private static List<Interval> getIntervalsFromInput(List<String> lines) {
        List<Interval> intervals = new ArrayList<>();

        for (String line : lines) {
            Interval interval = new Interval();
            interval.setDestinationStart(Long.parseLong(line.substring(0, line.indexOf(" "))));
            interval.setSourceStart(Long.parseLong(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" "))));
            interval.setRangeLength(Long.parseLong(line.substring(line.lastIndexOf(" ") + 1)));
            intervals.add(interval);
        }
        return intervals;
    }
}
