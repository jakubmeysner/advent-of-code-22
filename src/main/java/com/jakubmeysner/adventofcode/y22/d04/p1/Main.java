package com.jakubmeysner.adventofcode.y22.d04.p1;

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

            var containsCount = rangePairs
                .stream()
                .filter(RangePair::oneContainsOther)
                .count();

            System.out.println(containsCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
