package org.example.shapes;

import org.example.drawer.Point;

public class Triangle extends Shape {
    private final String name = "Triangle";
    private final Point[] trianglePoints = new Point[3];
    private final Point p1;
    private final Point p2;
    private final Point p3;

    public Triangle(int[] coordinates) {
        super("Triangle", coordinates);
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
    public double getArea() {
        double[] sides = getSides();

        // Uses Heron's formula to calculate the area of triangle
        double area = Math.sqrt(getPerimeter() / 2.0 *
                (getPerimeter() / 2.0 - sides[0]) *
                (getPerimeter() / 2.0 - sides[1]) *
                (getPerimeter() / 2.0 - sides[2]));
        return area;
    }

    @Override
    public void printSidesLength() {

        double c = Point.getLength(p1, p2);
        double a = Point.getLength(p2, p3);
        double b = Point.getLength(p1, p3);

        System.out.println("Triangle sides: [POINT1 - POINT2] = " + String.format("%.2f", c) + ", [POINT2 - POINT3] = " + String.format("%.2f", a) + ", [POINT1 - POINT3] = " + String.format("%.2f", b));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Point[] bob = (Point[]) o;
        System.out.println("What is trianglePoints" + Arrays.toString(trianglePoints));
        System.out.println("What is bob" + Arrays.toString(bob));
        return
                trianglePoints[0].x == bob[0].x
                        && trianglePoints[0].y == bob[0].y
                        && trianglePoints[1].x == bob[1].x
                        && trianglePoints[1].y == bob[1].y
                        && trianglePoints[2].x == bob[2].x
                        && trianglePoints[2].y == bob[2].y
                ;
    }

    public static boolean arePointsTheSame(Triangle tri, Point[] points) {
        return tri.trianglePoints[0].x == points[0].x &&
                tri.trianglePoints[0].y == points[0].y &&
                tri.trianglePoints[1].x == points[1].x &&
                tri.trianglePoints[1].y == points[1].y &&
                tri.trianglePoints[2].x == points[2].x &&
                tri.trianglePoints[2].y == points[2].y;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(trianglePoints);
    }
}
