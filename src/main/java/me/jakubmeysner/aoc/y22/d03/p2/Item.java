package me.jakubmeysner.aoc.y22.d03.p2;

public class Item {
    public static int getTypePriority(char type) {
        if (type >= 'a' && type <= 'z') {
            return type - 'a' + 1;
        }

        if (type >= 'A' && type <= 'Z') {
            return type - 'A' + 27;
        }

        throw new IllegalArgumentException();
    }
}
