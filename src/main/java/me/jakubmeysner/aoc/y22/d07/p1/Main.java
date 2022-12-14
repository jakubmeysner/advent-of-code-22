package me.jakubmeysner.aoc.y22.d07.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try (
            var reader = new BufferedReader(
                new FileReader("./resources/d07.txt")
            )
        ) {
            var rootDirectory = new Directory(null, "");
            var currentDirectory = rootDirectory;

            for (var line : reader.lines().toList()) {
                var words = line.trim().split("\\s+");

                if (Objects.equals(words[0], "$")) {
                    if (Objects.equals(words[1], "cd")) {
                        switch (words[2]) {
                            case "/" -> currentDirectory = rootDirectory;

                            case ".." -> currentDirectory = currentDirectory.getParent();

                            default -> {
                                var existingItem = currentDirectory
                                    .getChildren()
                                    .stream()
                                    .filter(
                                        item -> Objects.equals(item.getName(), words[2])
                                    )
                                    .findFirst();

                                if (existingItem.isPresent()) {
                                    currentDirectory = (Directory) existingItem.get();
                                } else {
                                    var newDirectory = new Directory(currentDirectory, words[2]);
                                    currentDirectory.getChildren().add(newDirectory);
                                    currentDirectory = newDirectory;
                                }
                            }
                        }
                    }
                } else {
                    var existingItem = currentDirectory
                        .getChildren()
                        .stream()
                        .filter(
                            item -> Objects.equals(item.getName(), words[1])
                        )
                        .findFirst();

                    if (existingItem.isEmpty()) {
                        if (Objects.equals(words[0], "dir")) {
                            var newDirectory = new Directory(currentDirectory, words[1]);
                            currentDirectory.getChildren().add(newDirectory);
                        } else {
                            var newFile = new File(currentDirectory, words[1], Integer.parseInt(words[0]));
                            currentDirectory.getChildren().add(newFile);
                        }
                    }
                }
            }

            var allChildren = rootDirectory.getAllChildren();

            var filteredDirectoriesTotalSize = allChildren
                .stream()
                .filter(Directory.class::isInstance)
                .map(Directory.class::cast)
                .filter(directory -> directory.getSize() <= 100000)
                .mapToInt(Directory::getSize)
                .sum();

            System.out.println(filteredDirectoriesTotalSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
