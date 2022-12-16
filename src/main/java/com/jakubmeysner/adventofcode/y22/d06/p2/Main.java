package com.jakubmeysner.adventofcode.y22.d06.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (
            var reader = new BufferedReader(
                new FileReader("./resources/d06.txt")
            )
        ) {
            var characters = reader.readLine().toCharArray();

            var index = -1;

            for (var i = 13; i < characters.length; i++) {
                var lastCharactersSet = IntStream
                    .rangeClosed(i - 13, i)
                    .map(j -> characters[j])
                    .boxed()
                    .collect(Collectors.toSet());

                if (lastCharactersSet.size() == 14) {
                    index = i;
                    break;
                }
            }

            System.out.println(index + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
