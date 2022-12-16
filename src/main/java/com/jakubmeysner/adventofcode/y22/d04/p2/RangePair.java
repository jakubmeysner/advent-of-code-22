package com.jakubmeysner.adventofcode.y22.d04.p2;

import java.util.regex.Pattern;

public class RangePair {
    private static final Pattern stringPattern = Pattern.compile("^(\\d+)-(\\d+),(\\d+)-(\\d+)$");

    private final int firstStart;
    private final int firstEndInclusive;
    private final int secondStart;
    private final int secondEndInclusive;

    private RangePair(
        int firstStart,
        int firstEndInclusive,
        int secondStart,
        int secondEndInclusive
    ) {
        this.firstStart = firstStart;
        this.firstEndInclusive = firstEndInclusive;
        this.secondStart = secondStart;
        this.secondEndInclusive = secondEndInclusive;
    }

    public static RangePair fromString(String string) {
        var matcher = stringPattern.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        return new RangePair(
            Integer.parseInt(matcher.group(1)),
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(3)),
            Integer.parseInt(matcher.group(4))
        );
    }

    public int getFirstStart() {
        return firstStart;
    }

    public int getFirstEndInclusive() {
        return firstEndInclusive;
    }

    public int getSecondStart() {
        return secondStart;
    }

    public int getSecondEndInclusive() {
        return secondEndInclusive;
    }

    public boolean firstContainsSecond() {
        return secondStart >= firstStart && secondEndInclusive <= firstEndInclusive;
    }

    public boolean secondContainsFirst() {
        return firstStart >= secondStart && firstEndInclusive <= secondEndInclusive;
    }

    public boolean oneContainsOther() {
        return firstContainsSecond() || secondContainsFirst();
    }

    public boolean overlap() {
        return (
            (firstStart <= secondStart && firstEndInclusive >= secondStart) ||
                (secondStart < firstStart && firstStart <= secondEndInclusive)
        );
    }
}
