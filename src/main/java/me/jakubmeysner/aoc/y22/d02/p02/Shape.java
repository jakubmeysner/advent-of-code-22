package me.jakubmeysner.aoc.y22.d02.p02;

public enum Shape {
    ROCK(1), PAPER(2), SCISSORS(3);

    private final int value;

    Shape(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Shape getBetterThanShape() {
        return switch (this) {
            case ROCK -> SCISSORS;
            case PAPER -> ROCK;
            case SCISSORS -> PAPER;
        };
    }

    public Shape getWorseThanShape() {
        return switch (this) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

    public int getScoreAgainst(Shape shape) {
        if (shape == getBetterThanShape()) {
            return value + 6;
        } else if (shape == this) {
            return value + 3;
        } else {
            return value;
        }
    }
}
