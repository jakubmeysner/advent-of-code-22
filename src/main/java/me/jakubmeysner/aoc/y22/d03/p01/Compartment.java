package me.jakubmeysner.aoc.y22.d03.p01;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Compartment {
    private final List<Item> itemList = new ArrayList<>();

    public Compartment() {
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Set<Character> getItemTypeSet() {
        return itemList.stream().map(Item::getType).collect(Collectors.toSet());
    }
}
