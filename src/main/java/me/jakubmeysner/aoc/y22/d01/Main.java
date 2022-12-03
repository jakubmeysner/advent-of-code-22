package me.jakubmeysner.aoc.y22.d01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (
                var reader = new BufferedReader(
                        new FileReader("./resources/d01.txt")
                )
        ) {
            List<List<Integer>> containers = new ArrayList<>();
            List<Integer> container = new ArrayList<>();
            var lines = reader.lines().toList();

            for (var line : lines) {
                if (line.isEmpty()) {
                    containers.add(container);
                    container = new ArrayList<>();
                } else {
                    container.add(Integer.parseInt(line));
                }
            }

            var containerSums = containers
                    .stream()
                    .map(
                            con -> con
                                    .stream()
                                    .mapToInt(Integer::intValue)
                                    .sum()
                    )
                    .toList();

            var maxSum = containerSums
                    .stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElseThrow();

            System.out.println(maxSum);

            var maxThreeSum = containerSums
                    .stream()
                    .sorted()
                    .skip(containerSums.size() - 3)
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println(maxThreeSum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
