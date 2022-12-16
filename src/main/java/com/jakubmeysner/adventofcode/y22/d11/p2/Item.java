package com.jakubmeysner.adventofcode.y22.d11.p2;

public class Item {
    private long level;

    public Item(long level) {
        this.level = level;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "level=" + level +
                '}';
    }
}
