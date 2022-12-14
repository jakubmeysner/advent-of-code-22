package me.jakubmeysner.aoc.y22.d02.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (
                var reader = new BufferedReader(
                        new FileReader("./resources/d02.txt")
                )
        ) {
            List<Round> roundList = new ArrayList<>();
            reader.lines().forEach(line -> roundList.add(Round.fromString(line)));
            var totalScore = roundList.stream().mapToInt(Round::getScore).sum();
            System.out.println(totalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
