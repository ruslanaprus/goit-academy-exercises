package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public class Circle extends Shape {
    private final String name = "Circle";
    private Point[] circlePoints = new Point[3];
    private Point p1;
    private Point p2;
    private Point p3;

    //Constructor accepts coordinates of to points, first to numbers are x,y coordinates of the cetre

    public Circle(int[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("coordinates must be provided");
        }
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("coordinates must be even number");
        }
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            circlePoints[i / 2] = new Point(x, y);
        }

        p1 = circlePoints[0];
        p2 = circlePoints[1];
        p3 = circlePoints[2];

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Point[] getPoints() {
        return Arrays.copyOf(circlePoints, circlePoints.length);
    }

    @Override
    public int getNumberOfSides() {
        throw new UnsupportedOperationException("Circle doesn't have sides");
    }

    @Override
    public double getAngle(String pointName) {
        if (pointName.equals("central angle")) {
            return Point.getAngleAtPoint(
                    p1, p2, p3
            );
        }
        if (pointName.equals("inscribed angle")) {
            return 0.5d * Point.getAngleAtPoint(
                    p1, p2, p3
            );
        }
        throw new IllegalArgumentException("Unknown point");
    }

    @Override
    public void getSidesLength() {
        throw new UnsupportedOperationException("Circle doesn't have sides");
    }

    @Override
    public double getRadius() {
        return Point.getLength(p1, p2);
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * Point.getLength(p1, p2);
    }
}
