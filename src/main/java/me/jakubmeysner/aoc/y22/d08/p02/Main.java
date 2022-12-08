package me.jakubmeysner.aoc.y22.d08.p02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (
            var reader = new BufferedReader(new FileReader("./resources/d08.txt"))
        ) {
            var grid = new ArrayList<List<Integer>>();

            reader.lines().forEach(line -> {
                var row = new ArrayList<Integer>();
                line.chars().forEach(row::add);
                grid.add(row);
            });

            var maxScore = 0;

            for (var y = 0; y < grid.size(); y++) {
                for (var x = 0; x < grid.get(y).size(); x++) {
                    var value = grid.get(y).get(x);

                    var upCount = 0;
                    var leftCount = 0;
                    var downCount = 0;
                    var rightCount = 0;

                    for (var upY = y - 1; upY >= 0; upY--) {
                        upCount++;

                        if (grid.get(upY).get(x) >= value) {
                            break;
                        }
                    }

                    for (var leftX = x - 1; leftX >= 0; leftX--) {
                        leftCount++;

                        if (grid.get(y).get(leftX) >= value) {
                            break;
                        }
                    }

                    for (var downY = y + 1; downY < grid.size(); downY++) {
                        downCount++;

                        if (grid.get(downY).get(x) >= value) {
                            break;
                        }
                    }

                    for (var rightX = x + 1; rightX < grid.get(y).size(); rightX++) {
                        rightCount++;

                        if (grid.get(y).get(rightX) >= value) {
                            break;
                        }
                    }

                    var score = upCount * leftCount * downCount * rightCount;

                    if (score > maxScore) {
                        maxScore = score;
                    }
                }
            }

            System.out.println(maxScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
