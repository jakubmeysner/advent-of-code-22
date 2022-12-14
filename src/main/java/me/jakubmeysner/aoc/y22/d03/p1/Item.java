package me.jakubmeysner.aoc.y22.d03.p1;

public class Item {
    private final char type;

    public Item(char type) {
        if (
            (type < 'a' || type > 'z') &&
                (type < 'A' || type > 'Z')
        ) {
            throw new IllegalArgumentException();
        }

        this.type = type;
    }

    public static int getTypePriority(char type) {
        if (type >= 'a' && type <= 'z') {
            return type - 'a' + 1;
        }

        if (type >= 'A' && type <= 'Z') {
            return type - 'A' + 27;
        }

        throw new IllegalArgumentException();
    }

    public char getType() {
        return type;
    }
}
