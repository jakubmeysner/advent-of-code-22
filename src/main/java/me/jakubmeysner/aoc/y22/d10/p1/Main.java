package me.jakubmeysner.aoc.y22.d10.p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (
            var reader = Files.newBufferedReader(Path.of("./resources/d10.txt"))
        ) {
            var lines = reader.lines().toList();

            var values = new ArrayList<Integer>();
            values.add(1);

            for (var line : lines) {
                var lastValue = values.get(values.size() - 1);
                values.add(lastValue);

                if (line.startsWith("addx")) {
                    var delta = Integer.parseInt(line.substring(5));
                    values.add(lastValue + delta);
                }
            }

            var sum = IntStream
                .rangeClosed(0, 5)
                .map(i -> (20 + 40 * i) * values.get(20 + 40 * i - 1))
                .sum();

            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
