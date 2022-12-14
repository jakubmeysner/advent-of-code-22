package me.jakubmeysner.aoc.y22.d09.p02;

public enum Direction {
    UP, DOWN, LEFT, RIGHT,
    UP_LEFT, UP_RIGHT,
    DOWN_LEFT, DOWN_RIGHT;

    public static Direction fromChar(char ch) {
        return switch (ch) {
            case 'U' -> UP;
            case 'D' -> DOWN;
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException();
        };
    }
}
