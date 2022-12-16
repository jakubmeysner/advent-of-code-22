package com.jakubmeysner.adventofcode.y22.d09.p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (var reader = Files.newBufferedReader(Path.of("./resources/d09.txt"))) {
            var entities = IntStream
                .range(0, 10)
                .mapToObj(i -> new Entity())
                .toList();

            for (var line : reader.lines().toList()) {
                if (!line.isEmpty()) {
                    var direction = Direction.fromChar(line.charAt(0));
                    var moves = Integer.parseInt(line.substring(2));

                    while (moves > 0) {
                        moves--;
                        entities.get(0).move(direction);

                        for (var i = 1; i < entities.size(); i++) {
                            entities.get(i).follow(entities.get(i - 1));
                        }
                    }
                }
            }

            System.out.println(Set.copyOf(entities.get(entities.size() - 1).getPointList()).size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
