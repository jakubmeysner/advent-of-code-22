package com.jakubmeysner.adventofcode.y22.d07.p2;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Item {
    private final Directory parent;
    private final String name;
    private final List<Item> children = new ArrayList<>();

    public Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
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
        return children
            .stream()
            .mapToInt(Item::getSize)
            .sum();
    }

    public List<Item> getChildren() {
        return children;
    }

    public List<Item> getAllChildren() {
        var allChildren = new ArrayList<Item>();

        for (var child : children) {
            allChildren.add(child);

            if (child instanceof Directory directory) {
                allChildren.addAll(directory.getAllChildren());
            }
        }

        return allChildren;
    }
}
