package com.jakubmeysner.adventofcode.y22.d11.p1;

public class Item {
    private int level;

    public Item(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "level=" + level +
                '}';
    }
}
