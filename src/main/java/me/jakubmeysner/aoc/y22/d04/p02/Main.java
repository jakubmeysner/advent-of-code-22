package me.jakubmeysner.aoc.y22.d04.p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (
            var reader = new BufferedReader(
                new FileReader("./resources/d04.txt")
            )
        ) {
            var rangePairs = new ArrayList<RangePair>();

            reader.lines().forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }

                rangePairs.add(RangePair.fromString(line));
            });

            var overlapCount = rangePairs
                .stream()
                .filter(RangePair::overlap)
                .count();

            System.out.println(overlapCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
