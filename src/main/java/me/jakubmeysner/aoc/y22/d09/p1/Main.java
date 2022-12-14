package me.jakubmeysner.aoc.y22.d09.p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try (var reader = Files.newBufferedReader(Path.of("./resources/d09.txt"))) {
            var headPoint = new Point(0, 0);
            var tailPoints = new ArrayList<Point>();
            tailPoints.add(headPoint);

            for (var line : reader.lines().toList()) {
                if (!line.isEmpty()) {
                    var direction = line.charAt(0);
                    var steps = Integer.parseInt(line.substring(2));
                    headPoint = new Point(
                        direction == 'R' ?
                            headPoint.getX() + steps :
                            direction == 'L' ?
                                headPoint.getX() - steps :
                                headPoint.getX(),
                        direction == 'U' ?
                            headPoint.getY() + steps :
                            direction == 'D' ?
                                headPoint.getY() - steps :
                                headPoint.getY()
                    );

                    while (
                        Math.abs(
                            tailPoints.get(tailPoints.size() - 1).getX() - headPoint.getX()
                        ) > 1 ||
                            Math.abs(
                                tailPoints.get(tailPoints.size() - 1).getY() - headPoint.getY()
                            ) > 1
                    ) {
                        var tailPoint = tailPoints.get(tailPoints.size() - 1);

                        if (headPoint.getX() == tailPoint.getX()) {
                            if (headPoint.getY() > tailPoint.getY()) {
                                tailPoints.add(
                                    new Point(tailPoint.getX(), tailPoint.getY() + 1)
                                );
                            } else {
                                tailPoints.add(
                                    new Point(tailPoint.getX(), tailPoint.getY() - 1)
                                );
                            }
                        } else if (headPoint.getY() == tailPoint.getY()) {
                            if (headPoint.getX() > tailPoint.getX()) {
                                tailPoints.add(
                                    new Point(tailPoint.getX() + 1, tailPoint.getY())
                                );
                            } else {
                                tailPoints.add(
                                    new Point(tailPoint.getX() - 1, tailPoint.getY())
                                );
                            }
                        } else if (headPoint.getX() > tailPoint.getX()) {
                            if (headPoint.getY() > tailPoint.getY()) {
                                tailPoints.add(
                                    new Point(tailPoint.getX() + 1, tailPoint.getY() + 1)
                                );
                            } else {
                                tailPoints.add(
                                    new Point(tailPoint.getX() + 1, tailPoint.getY() - 1)
                                );
                            }
                        } else {
                            if (headPoint.getY() > tailPoint.getY()) {
                                tailPoints.add(
                                    new Point(tailPoint.getX() - 1, tailPoint.getY() + 1)
                                );
                            } else {
                                tailPoints.add(
                                    new Point(tailPoint.getX() - 1, tailPoint.getY() - 1)
                                );
                            }
                        }
                    }
                }
            }

            System.out.println(Set.copyOf(tailPoints).size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
