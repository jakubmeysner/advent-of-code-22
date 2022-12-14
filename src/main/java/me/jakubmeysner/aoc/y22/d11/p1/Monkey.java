package me.jakubmeysner.aoc.y22.d11.p1;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private final int id;
    private final List<Item> items = new ArrayList<>();
    private final Operation operation;
    private final int operationConstant;
    private final int testConstant;
    private final int testSuccessTargetId;
    private final int testFailureTargetId;
    private int inspectionCount = 0;

    public Monkey(
        int id, Operation operation, int operationConstant,
        int testConstant, int testSuccessMonkeyId, int testFailureMonkeyId
    ) {
        this.id = id;
        this.operation = operation;
        this.operationConstant = operationConstant;
        this.testConstant = testConstant;
        this.testSuccessTargetId = testSuccessMonkeyId;
        this.testFailureTargetId = testFailureMonkeyId;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getOperationConstant() {
        return operationConstant;
    }

    public int getTestConstant() {
        return testConstant;
    }

    public int getTestSuccessTargetId() {
        return testSuccessTargetId;
    }

    public int getTestFailureTargetId() {
        return testFailureTargetId;
    }

    public int getInspectionCount() {
        return inspectionCount;
    }

    public void inspectItem(Item item) {
        inspectionCount++;

        switch (operation) {
            case MULTIPLICATION_BY_SELF -> item.setLevel(item.getLevel() * item.getLevel());
            case MULTIPLICATION_BY_CONSTANT -> item.setLevel(item.getLevel() * operationConstant);
            case ADDITION_OF_CONSTANT -> item.setLevel(item.getLevel() + operationConstant);
        }

        item.setLevel(item.getLevel() / 3);
    }

    public void throwItem(Item item, List<Monkey> monkeys) {
        var successTarget = monkeys
            .stream()
            .filter(m -> m.id == testSuccessTargetId)
            .findFirst()
            .orElseThrow();

        var failureTarget = monkeys
            .stream()
            .filter(m -> m.id == testFailureTargetId)
            .findFirst()
            .orElseThrow();

        if (item.getLevel() % testConstant == 0) {
            successTarget.items.add(item);
        } else {
            failureTarget.items.add(item);
        }
    }

    @Override
    public String toString() {
        return "Monkey{" +
            "id=" + id +
            ", items=" + items +
            ", operation=" + operation +
            ", operationConstant=" + operationConstant +
            ", testConstant=" + testConstant +
            ", testSuccessTargetId=" + testSuccessTargetId +
            ", testFailureTargetId=" + testFailureTargetId +
            '}';
    }

    public enum Operation {
        MULTIPLICATION_BY_SELF,
        MULTIPLICATION_BY_CONSTANT,
        ADDITION_OF_CONSTANT
    }
}
