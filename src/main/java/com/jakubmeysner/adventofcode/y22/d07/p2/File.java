package com.jakubmeysner.adventofcode.y22.d07.p2;

public class File implements Item {
    private final Directory parent;
    private final String name;
    private final int size;

    public File(Directory parent, String name, int size) {
        this.parent = parent;
        this.name = name;
        this.size = size;
    }

    @Override
    public Directory getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }
}
