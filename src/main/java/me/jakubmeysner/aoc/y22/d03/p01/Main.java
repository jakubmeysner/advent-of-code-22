package me.jakubmeysner.aoc.y22.d03.p01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (
                var reader = new BufferedReader(
                        new FileReader("./resources/d03.txt")
                )
        ) {
            var rucksackList = new ArrayList<Rucksack>();

            reader.lines().forEach((line) -> {
                if (line.isEmpty()) {
                    return;
                }

                var rucksack = new Rucksack();
                rucksackList.add(rucksack);

                for (var i = 0; i < line.length(); i++) {
                    if (i < line.length() / 2) {
                        rucksack.getFirstCompartment().getItemList().add(
                                new Item(line.charAt(i))
                        );
                    } else {
                        rucksack.getSecondCompartment().getItemList().add(
                                new Item(line.charAt(i))
                        );
                    }
                }
            });

            var prioritySum = rucksackList
                    .stream()
                    .map(
                            rucksack -> rucksack.getCommonItemTypeSet().stream().mapToInt(Item::getTypePriority).sum()
                    )
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println(prioritySum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
