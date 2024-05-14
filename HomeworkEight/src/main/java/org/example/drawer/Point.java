package org.example.drawer;

import java.text.DecimalFormat;

/**
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    /**
     * The x-coordinate.
     */
    public int x;

    /**
     * The y-coordinate.
     */
    public int y;

    /**
     * Creates a new Point with the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the coordinates of this point to match the given point.
     *
     * @param p The point to copy coordinates from.
     */
    public void setPoint(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param point1 The first point.
     * @param point2 The second point.
     * @return The distance between the points.
     */
    public static double getLength(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }

    /**
     * Calculates the angle at the middle point formed by three points.
     *
     * @param point1 The first point.
     * @param point2 The middle point.
     * @param point3 The third point.
     * @return The angle in degrees.
     */
    public static double getAngleAtPoint(Point point1, Point point2, Point point3) {
        double length1 = getLength(point1, point2);
        double length2 = getLength(point2, point3);
        double length3 = getLength(point1, point3);

        double angleRadians = Math.acos((length1 * length1 + length2 * length2 - length3 * length3) / (2 * length1 * length2));
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(Math.toDegrees(angleRadians)));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
