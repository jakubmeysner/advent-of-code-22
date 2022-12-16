package com.jakubmeysner.adventofcode.y22.d09.p2;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private final List<Point> pointList = new ArrayList<>();

    public Entity() {
        pointList.add(new Point(0, 0));
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public Point getLastPoint() {
        return pointList.get(pointList.size() - 1);
    }

    public void move(Direction direction) {
        var lastPoint = getLastPoint();

        switch (direction) {
            case UP -> getPointList().add(
                new Point(lastPoint.getX(), lastPoint.getY() + 1)
            );

            case DOWN -> getPointList().add(
                new Point(lastPoint.getX(), lastPoint.getY() - 1)
            );

            case LEFT -> getPointList().add(
                new Point(lastPoint.getX() - 1, lastPoint.getY())
            );

            case RIGHT -> getPointList().add(
                new Point(lastPoint.getX() + 1, lastPoint.getY())
            );

            case UP_LEFT -> getPointList().add(
                new Point(lastPoint.getX() - 1, lastPoint.getY() + 1)
            );

            case UP_RIGHT -> getPointList().add(
                new Point(lastPoint.getX() + 1, lastPoint.getY() + 1)
            );

            case DOWN_LEFT -> getPointList().add(
                new Point(lastPoint.getX() - 1, lastPoint.getY() - 1)
            );

            case DOWN_RIGHT -> getPointList().add(
                new Point(lastPoint.getX() + 1, lastPoint.getY() - 1)
            );
        }
    }

    public void follow(Entity entity) {
        var lastPoint = getLastPoint();
        var otherLastPoint = entity.getLastPoint();

        if (
            Math.abs(otherLastPoint.getX() - lastPoint.getX()) <= 1 &&
                Math.abs(otherLastPoint.getY() - lastPoint.getY()) <= 1
        ) {
            return;
        }

        if (otherLastPoint.getX() == lastPoint.getX()) {
            move(otherLastPoint.getY() > lastPoint.getY() ? Direction.UP : Direction.DOWN);
        } else if (otherLastPoint.getY() == lastPoint.getY()) {
            move(otherLastPoint.getX() > lastPoint.getX() ? Direction.RIGHT : Direction.LEFT);
        } else {
            if (otherLastPoint.getX() > lastPoint.getX()) {
                move(otherLastPoint.getY() > lastPoint.getY() ? Direction.UP_RIGHT : Direction.DOWN_RIGHT);
            } else {
                move(otherLastPoint.getY() > lastPoint.getY() ? Direction.UP_LEFT : Direction.DOWN_LEFT);
            }
        }
    }
}
