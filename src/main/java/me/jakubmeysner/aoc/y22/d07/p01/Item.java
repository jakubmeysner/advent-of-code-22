package me.jakubmeysner.aoc.y22.d07.p01;

public interface Item {
    Directory getParent();

    String getName();

    int getSize();
}
