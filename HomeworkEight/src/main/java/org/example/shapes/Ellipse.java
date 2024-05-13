package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public class Ellipse extends Shape {
    private final String name = "Ellipse";
    private Point[] ellipsPoints = new Point[3];
    private Point p1;
    private Point p2;
    private Point p3;

    //Constructor accepts coordinates of to points, first to numbers are x,y coordinates of the cetre

    public Ellipse(int[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("coordinates must be provided");
        }
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("coordinates must be even number");
        }
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            ellipsPoints[i / 2] = new Point(x, y);
        }

        p1 = ellipsPoints[0];
        p2 = ellipsPoints[1];
        p3 = ellipsPoints[2];

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Point[] getPoints() {
        return Arrays.copyOf(ellipsPoints, ellipsPoints.length);
    }

    @Override
    public int getNumberOfSides() {
        throw new UnsupportedOperationException("Ellipse doesn't have sides");
    }

    @Override
    public double getAngle(String str) {
        return 0;
    }

    @Override
    public void getSidesLength() {
        throw new UnsupportedOperationException("Ellipse doesn't have sides");
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public double getCircumference() {
        return 0;
    }
}
