package com.jakubmeysner.adventofcode.y22.d11.p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<Monkey> readMonkeys() throws IOException {
        try (
            var reader = Files.newBufferedReader(Path.of("./resources/d11.txt"))
        ) {
            var monkeys = new ArrayList<Monkey>();

            var monkeyId = 0;
            List<Item> monkeyItems = List.of();
            var monkeyOperation = Monkey.Operation.ADDITION_OF_CONSTANT;
            var monkeyOperationConstant = 0;
            var monkeyTestConstant = 0;
            var monkeyTestSuccessTargetId = 0;
            var monkeyTestFailureTargetId = 0;

            for (var line : reader.lines().toList()) {
                if (line.startsWith("Monkey ")) {
                    monkeyId = Integer.parseInt(
                        line.substring(7, line.length() - 1)
                    );
                } else if (line.startsWith("  Starting items: ")) {
                    monkeyItems = Arrays.stream(
                            line
                                .substring(18)
                                .split(", ")
                        )
                        .map(p -> new Item(Integer.parseInt(p)))
                        .toList();
                } else if (line.startsWith("  Operation: ")) {
                    var operationString = line.substring(19);

                    if (operationString.equals("old * old")) {
                        monkeyOperation = Monkey.Operation.MULTIPLICATION_BY_SELF;
                        monkeyOperationConstant = 0;
                    } else {
                        monkeyOperation = operationString.charAt(4) == '*' ?
                            Monkey.Operation.MULTIPLICATION_BY_CONSTANT :
                            Monkey.Operation.ADDITION_OF_CONSTANT;
                        monkeyOperationConstant = Integer.parseInt(operationString.substring(6));
                    }
                } else if (line.startsWith("  Test: divisible by")) {
                    monkeyTestConstant = Integer.parseInt(line.substring(21));
                } else if (line.startsWith("    If true: throw to monkey ")) {
                    monkeyTestSuccessTargetId = Integer.parseInt(line.substring(29));
                } else if (line.startsWith("    If false: throw to monkey ")) {
                    monkeyTestFailureTargetId = Integer.parseInt(line.substring(30));
                } else if (line.isEmpty()) {
                    var monkey = new Monkey(
                        monkeyId, monkeyOperation, monkeyOperationConstant,
                        monkeyTestConstant, monkeyTestSuccessTargetId, monkeyTestFailureTargetId
                    );

                    monkey.getItems().addAll(monkeyItems);
                    monkeys.add(monkey);
                }
            }

            var monkey = new Monkey(
                monkeyId, monkeyOperation, monkeyOperationConstant,
                monkeyTestConstant, monkeyTestSuccessTargetId, monkeyTestFailureTargetId
            );

            monkey.getItems().addAll(monkeyItems);
            monkeys.add(monkey);

            return monkeys;
        }
    }

    public static void main(String[] args) {
        try {
            var monkeys = readMonkeys();

            for (var i = 0; i < 20; i++) {
                for (var monkey : monkeys) {
                    for (var item : monkey.getItems()) {
                        monkey.inspectItem(item);
                        monkey.throwItem(item, monkeys);
                    }

                    monkey.getItems().clear();
                }
            }

            var sortedMonkeys = monkeys
                .stream()
                .sorted(Comparator.comparingInt(Monkey::getInspectionCount))
                .toList();

            var businessLevel = sortedMonkeys.get(sortedMonkeys.size() - 1).getInspectionCount() *
                sortedMonkeys.get(sortedMonkeys.size() - 2).getInspectionCount();

            System.out.println(businessLevel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
