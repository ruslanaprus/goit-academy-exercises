package org.example.shapes;

import org.example.drawer.Point;

public class Parallelogram extends Shape {

    private Point p1 = points[0];
    private Point p2 = points[1];
    private Point p3 = points[2];
    private Point p4 = points[3];

    public Parallelogram(int[] coordinates) {
        super("Parallelogram", coordinates);
    }

    @Override
    public double getAngle(String pointName) {
        if (pointName.equals("POINT1") || pointName.equals("POINT3")) {
            return Point.getAngleAtPoint(
                    p4, p1, p2
            );
        }
        if (pointName.equals("POINT2") || pointName.equals("POINT4")) {
            return Point.getAngleAtPoint(
                    p1, p2, p3
            );
        }
        throw new IllegalArgumentException("Unknown point");
    }

    @Override
    public double getArea() {
        double[] sides = getSides();
        double[] angles = getAngles();

        // points[0] and points[1] represent the adjacent sides of the parallelogram
        double angle = Math.toRadians(angles[0]); // Convert angle to radians for trigonometric functions

        return sides[0] * sides[1] * Math.sin(angle);
    }

    @Override
    public void printSidesLength() {

        double a = Point.getLength(p1, p2);
        double b = Point.getLength(p2, p3);

        System.out.println("Parallelogram sides: a = " + String.format("%.2f", a) + ", b = " + String.format("%.2f", b));
    }

}
