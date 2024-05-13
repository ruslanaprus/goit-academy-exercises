package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public class Triangle extends Shape {
    private final String name = "Triangle";
    private Point[] trianglePoints = new Point[3];
    private Point p1;
    private Point p2;
    private Point p3;

    public Triangle(int[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("coordinates must be provided");
        }
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("Coordinates must be even number");
        }

        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            trianglePoints[i / 2] = new Point(x, y);
        }

        p1 = trianglePoints[0];
        p2 = trianglePoints[1];
        p3 = trianglePoints[2];

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Point[] getPoints() {
        return Arrays.copyOf(trianglePoints, trianglePoints.length);
    }

    @Override
    public int getNumberOfSides() {
        return this.trianglePoints.length;
    }

    @Override
    public double getAngle(String pointName) {
        if (pointName.equals("POINT1")) {
            return Point.getAngleAtPoint(
                    p1, p2, p3
            );
        }
        if (pointName.equals("POINT2")) {
            return Point.getAngleAtPoint(
                    p2, p3, p1
            );
        }
        if (pointName.equals("POINT3")) {
            return Point.getAngleAtPoint(
                    p3, p1, p2
            );
        }
        throw new IllegalArgumentException("Unknown point");
    }

    @Override
    public void getSidesLength() {

        double c = Point.getLength(p1, p2);
        double a = Point.getLength(p2, p3);
        double b = Point.getLength(p1, p3);

        System.out.println("Triangle sides: [POINT1 - POINT2] = " + String.format("%.2f", c) + ", [POINT2 - POINT3] = " + String.format("%.2f", a) + ", [POINT1 - POINT3] = " + String.format("%.2f", b));

    }

    @Override
    public double getRadius() {
        throw new UnsupportedOperationException("triangle does not have a radius");
    }

    @Override
    public double getCircumference() {
        throw new UnsupportedOperationException("triangle does not have a radius");
    }

}
