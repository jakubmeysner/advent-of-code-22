package me.jakubmeysner.aoc.y22.d02.p01;

public class Round {
    private final Shape opponentShape;
    private final Shape myShape;

    public Round(Shape opponentShape, Shape myShape) {
        this.opponentShape = opponentShape;
        this.myShape = myShape;
    }

    public static Round fromString(String string) {
        var opponentShape = switch (string.charAt(0)) {
            case 'A' -> Shape.ROCK;
            case 'B' -> Shape.PAPER;
            case 'C' -> Shape.SCISSORS;
            default -> throw new IllegalArgumentException();
        };

        var myShape = switch (string.charAt(2)) {
            case 'X' -> Shape.ROCK;
            case 'Y' -> Shape.PAPER;
            case 'Z' -> Shape.SCISSORS;
            default -> throw new IllegalArgumentException();
        };

        return new Round(opponentShape, myShape);
    }

    public Shape getOpponentShape() {
        return opponentShape;
    }

    public Shape getMyShape() {
        return myShape;
    }

    public int getScore() {
        return myShape.getScoreAgainst(opponentShape);
    }
}
