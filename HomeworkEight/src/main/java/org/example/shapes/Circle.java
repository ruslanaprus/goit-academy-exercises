package org.example.shapes;

import org.example.drawer.Point;

public class Circle extends Shape {

    private Point p1 = points[0];
    private Point p2 = points[1];
    private Point p3 = points[2];

    public Circle(int[] coordinates) {
        super("Circle", coordinates);
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
    public void printSidesLength() {
        throw new UnsupportedOperationException("Circle doesn't have sides");
    }

    @Override
    public double[] getSides() {
        throw new UnsupportedOperationException("Circle doesn't have sides");
    }

    @Override
    public double getPerimeter() {
        throw new UnsupportedOperationException("Calculate circumference");
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    public double getRadius() {
        return Point.getLength(p1, p2);
    }

    public double getCircumference() {
        return 2 * Math.PI * Point.getLength(p1, p2);
    }

}
