package com.jakubmeysner.adventofcode.y22.d03.p2;

import java.util.*;

public class Group {
    private final List<List<Character>> itemListList = new ArrayList<>();

    public List<List<Character>> getItemListList() {
        return itemListList;
    }

    public Optional<Character> getCommonItem() {
        Set<Character> commonItemSet = null;

        for (var itemList : itemListList) {
            var itemSet = new HashSet<>(itemList);

            if (commonItemSet == null) {
                commonItemSet = itemSet;
            } else {
                commonItemSet.retainAll(itemSet);
            }
        }

        return commonItemSet != null ?
            commonItemSet.stream().findFirst() :
            Optional.empty();
    }

    public Optional<Integer> getCommonItemPriority() {
        return getCommonItem().map(Item::getTypePriority);
    }
}
