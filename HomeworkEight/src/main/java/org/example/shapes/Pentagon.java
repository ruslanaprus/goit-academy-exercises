package org.example.shapes;

import org.example.drawer.Point;

public class Pentagon extends Shape {

    private Point p1 = points[0];
    private Point p2 = points[1];
    private Point p3 = points[2];
    private Point p4 = points[3];
    private Point p5 = points[4];

    public Pentagon(int[] coordinates) {
        super("Pentagon", coordinates);
    }

    @Override
    public double getAngle(String pointName) {
        if (pointName.equals("POINT1")) {
            return Point.getAngleAtPoint(
                    p5, p1, p2
            );
        }
        if (pointName.equals("POINT2")) {
            return Point.getAngleAtPoint(
                    p1, p2, p3
            );
        }
        if (pointName.equals("POINT3")) {
            return Point.getAngleAtPoint(
                    p2, p3, p4
            );
        }
        if (pointName.equals("POINT4")) {
            return Point.getAngleAtPoint(
                    p3, p4, p5
            );
        }
        if (pointName.equals("POINT5")) {
            return Point.getAngleAtPoint(
                    p4, p5, p1
            );
        }
        throw new IllegalArgumentException("Unknown point");
    }

    @Override
    public double getArea() {
        double[] sides = getSides();

        // the pentagon is divided into three triangles
        double area = 0.0;
        for (int i = 0; i < 3; i++) {
            // Lengths of sides of triangle
            double side1 = sides[i];
            double side2 = sides[(i + 1) % 5]; // Modulo 5 to handle last side wrapping around to the first
            double side3 = sides[(i + 2) % 5]; // Next side after the previous one

            double s = (side1 + side2 + side3) / 2;
            double triangleArea = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));

            area += triangleArea;
        }

        return area;
    }

    @Override
    public void printSidesLength() {

        double a = Point.getLength(p1, p2);
        double b = Point.getLength(p2, p3);
        double c = Point.getLength(p3, p4);
        double d = Point.getLength(p4, p5);
        double e = Point.getLength(p1, p5);

        System.out.println("Parallelogram sides: a = " + String.format("%.2f", a)
                + ", b = " + String.format("%.2f", b)
                + ", c = " + String.format("%.2f", c)
                + ", d = " + String.format("%.2f", d)
                + ", e = " + String.format("%.2f", e));
    }
}
