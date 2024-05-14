package org.example.shapes;

import org.example.drawer.Point;

import java.util.Arrays;

public class Triangle extends Shape {
    private final String name = "Triangle";
    private final Point[] trianglePoints = new Point[3];
    private final Point p1;
    private final Point p2;
    private final Point p3;

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
