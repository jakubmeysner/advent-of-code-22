package me.jakubmeysner.aoc.y22.d05.p1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern moveCommandPattern = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");

    public static void main(String[] args) {
        try (
            var reader = new BufferedReader(
                new FileReader("./resources/d05.txt")
            )
        ) {
            var lines = reader.lines().toList();
            var stackList = new ArrayList<ArrayList<Character>>();

            for (var line : lines) {
                if (line.startsWith(" 1")) {
                    var stackCount = Arrays.stream(line.trim().split("\\s+")).count();

                    while (stackList.size() < stackCount) {
                        stackList.add(new ArrayList<>());
                    }

                    stackList.forEach(Collections::reverse);
                } else if (line.startsWith("move")) {
                    var matcher = moveCommandPattern.matcher(line);

                    if (!matcher.matches()) {
                        throw new IllegalArgumentException();
                    }

                    var count = Integer.parseInt(matcher.group(1));
                    var sourceStackIndex = Integer.parseInt(matcher.group(2)) - 1;
                    var destinationStackIndex = Integer.parseInt(matcher.group(3)) - 1;

                    var sourceStack = stackList.get(sourceStackIndex);
                    var destinationStack = stackList.get(destinationStackIndex);

                    while (count > 0) {
                        destinationStack.add(sourceStack.get(sourceStack.size() - 1));
                        sourceStack.remove(sourceStack.size() - 1);
                        count--;
                    }
                } else if (!line.isEmpty()) {
                    for (var i = 1; i < line.length(); i += 4) {
                        var ch = line.charAt(i);

                        if (ch >= 'A' && ch <= 'Z') {
                            var stackIndex = (i - 1) / 4;

                            while (stackList.size() < stackIndex + 1) {
                                stackList.add(new ArrayList<>());
                            }

                            stackList.get(stackIndex).add(ch);
                        }
                    }
                }
            }

            System.out.println(
                String.join(
                    "",
                    stackList
                        .stream()
                        .map((stack) -> stack.get(stack.size() - 1).toString())
                        .toList()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
