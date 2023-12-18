package day05;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Interval {

    private long sourceStart;
    private long sourceEnd;
    private long destinationStart;
    private long rangeLength;

    public boolean isWithinRange(long numberToCheck) {
        sourceEnd = sourceStart + rangeLength;
        return numberToCheck >= sourceStart && numberToCheck <= sourceEnd;
    }

    public long getDestinationNumber(long sourceNumber) {
        long shift = getShiftBetweenSourceAndDestination();
        return sourceNumber + shift;
    }

    private long getShiftBetweenSourceAndDestination() {
        return destinationStart - sourceStart;
    }
}
