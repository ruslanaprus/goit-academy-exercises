package org.example.shapes;

import org.example.drawer.Point;

public class Rectangle extends Shape {

    private Point p1 = points[0];
    private Point p2 = points[1];
    private Point p3 = points[2];
    private Point p4 = points[3];

    public Rectangle(int[] coordinates) {
        super("Rectangle", coordinates);
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
    public double getArea() {
        double[] sides = getSides();
        return sides[0] * sides[1];
    }

    @Override
    public void printSidesLength() {

        double width = Math.abs(p1.x - p4.x);
        double hight = Math.abs(p1.y - p2.y);

        System.out.println("Rectangle: width = " + width + ", hight = " + hight);

    }

}