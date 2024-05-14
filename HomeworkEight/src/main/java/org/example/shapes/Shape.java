package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public abstract class Shape implements org.example.drawer.ShapeComposer {

    protected String name;
    protected Point[] points;

    public Shape(String name, int[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Coordinates must be provided");
        }
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("Coordinates must be even number");
        }

        this.name = name;
        this.points = new Point[coordinates.length / 2];
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            this.points[i / 2] = new Point(x, y);
        }
    }

    public String getName() {
        return name;
    }

    public Point[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    public int getNumberOfSides() {
        return points.length;
    }

    public double[] getAngles() {
        int numPoints = points.length;
        double[] angles = new double[numPoints];

        for (int i = 0; i < numPoints; i++) {
            Point point1 = points[i];
            Point point2 = points[(i + 1) % numPoints];
            Point point3 = points[(i + 2) % numPoints];
            angles[i] = Point.getAngleAtPoint(point1, point2, point3);
        }

        return Arrays.copyOf(angles, angles.length);
    }

    public double[] getSides() {
        int numPoints = points.length;
        double[] sides = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            Point point1 = points[i];
            Point point2 = points[(i + 1) % numPoints];
            sides[i] = Point.getLength(point1, point2);
        }
        return Arrays.copyOf(sides, sides.length);
    }

    public double getPerimeter() {
        double[] sides = getSides();
        double perimeter = 0.0;
        for (double side : sides) {
            perimeter += side;
        }
        return perimeter;
    }

    public abstract double getAngle(String str);

    public abstract void printSidesLength();

    public abstract double getArea();

}
