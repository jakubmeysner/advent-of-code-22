package me.jakubmeysner.aoc.y22.d03.p02;

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
            var lines = reader.lines().toList();

            var groups = new ArrayList<Group>();
            var group = new Group();

            for (var i = 0; i < lines.size(); i++) {
                group.getItemListList().add(
                    lines
                        .get(i)
                        .chars()
                        .mapToObj(n -> (char) n)
                        .toList()
                );

                if ((i + 1) % 3 == 0) {
                    groups.add(group);
                    group = new Group();
                }
            }

            var prioritySum = groups
                .stream()
                .mapToInt((g) -> g.getCommonItemPriority().orElse(0))
                .sum();

            System.out.println(prioritySum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
