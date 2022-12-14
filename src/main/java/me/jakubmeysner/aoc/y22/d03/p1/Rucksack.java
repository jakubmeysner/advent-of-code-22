package me.jakubmeysner.aoc.y22.d03.p1;

import java.util.HashSet;
import java.util.Set;

public class Rucksack {
    private final Compartment firstCompartment = new Compartment();
    private final Compartment secondCompartment = new Compartment();

    public Compartment getFirstCompartment() {
        return firstCompartment;
    }

    public Compartment getSecondCompartment() {
        return secondCompartment;
    }

    public Set<Character> getCommonItemTypeSet() {
        var set = new HashSet<>(firstCompartment.getItemTypeSet());
        set.retainAll(secondCompartment.getItemTypeSet());
        return set;
    }
}
