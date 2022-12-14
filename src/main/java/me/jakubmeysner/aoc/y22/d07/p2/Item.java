package me.jakubmeysner.aoc.y22.d07.p2;

public interface Item {
    Directory getParent();

    String getName();

    int getSize();
}
