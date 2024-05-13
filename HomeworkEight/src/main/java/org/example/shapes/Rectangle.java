package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public class Rectangle extends Shape {

    private final String name = "Rectangle";
    private Point[] rectanglePoints = new Point[4];
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    public Rectangle(int[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("coordinates must be provided");
        }
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("coordinates must be even number");
        }
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            rectanglePoints[i / 2] = new Point(x, y);
        }

        p1 = rectanglePoints[0];
        p2 = rectanglePoints[1];
        p3 = rectanglePoints[2];
        p4 = rectanglePoints[3];

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Point[] getPoints() {
        return Arrays.copyOf(rectanglePoints, rectanglePoints.length);
    }

    @Override
    public int getNumberOfSides() {
        return this.rectanglePoints.length;
    }

    @Override
    public double getAngle(String pointName) {
        double angle1 = Math.atan2(p1.y - p2.y, p1.x - p2.x);
        double angle2 = Math.atan2(p3.y - p2.y, p3.x - p2.x);
        switch (pointName) {
            case "POINT1":
            case "POINT2":
            case "POINT3":
            case "POINT4":
                return Math.toDegrees(Math.abs(angle1 - angle2));
            default:
                throw new IllegalArgumentException("Unknown point");
        }
    }

    @Override
    public void getSidesLength() {

        double width = Math.abs(p1.x - p4.x);
        double hight = Math.abs(p1.y - p2.y);

        System.out.println("Rectangle: width = " + width + ", hight = " + hight);

    }

    @Override
    public double getRadius() {
        throw new UnsupportedOperationException("rectangle does not have a radius");
    }

    @Override
    public double getCircumference() {
        throw new UnsupportedOperationException("rectangle does not have a radius");
    }

}
