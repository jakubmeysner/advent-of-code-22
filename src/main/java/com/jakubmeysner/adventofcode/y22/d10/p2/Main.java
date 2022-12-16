package com.jakubmeysner.adventofcode.y22.d10.p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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

            var pixelsRows = new boolean[6][40];

            for (var i = 1; i < values.size(); i++) {
                var value = values.get(i);
                var pixelRow = pixelsRows[i / 40 % 6];
                var index = i % 40;

                if (index >= value - 1 && index <= value + 1) {
                    pixelRow[index] = true;
                }
            }

            for (var pixelRow : pixelsRows) {
                for (var pixel : pixelRow) {
                    System.out.print(pixel ? "#" : ".");
                }

                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
