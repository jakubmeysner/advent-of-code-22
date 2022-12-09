package me.jakubmeysner.aoc.y22.d08.p01;

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

            var visibleCount = 0;

            for (var y = 0; y < grid.size(); y++) {
                for (var x = 0; x < grid.get(y).size(); x++) {
                    var current = grid.get(y).get(x);

                    if (y == 0 || y == grid.size() - 1 || x == 0 || x == grid.get(y).size() - 1) {
                        visibleCount++;
                    } else {
                        var sidesVisible = 4;

                        for (var topY = 0; topY < y; topY++) {
                            if (grid.get(topY).get(x) >= current) {
                                sidesVisible--;
                                break;
                            }
                        }

                        for (var bottomY = grid.size() - 1; bottomY > y; bottomY--) {
                            if (grid.get(bottomY).get(x) >= current) {
                                sidesVisible--;
                                break;
                            }
                        }

                        for (var leftX = 0; leftX < x; leftX++) {
                            if (grid.get(y).get(leftX) >= current) {
                                sidesVisible--;
                                break;
                            }
                        }

                        for (var rightX = grid.get(y).size() - 1; rightX > x; rightX--) {
                            if (grid.get(y).get(rightX) >= current) {
                                sidesVisible--;
                                break;
                            }
                        }

                        if (sidesVisible > 0) {
                            visibleCount++;
                        }
                    }
                }
            }

            System.out.println(visibleCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
