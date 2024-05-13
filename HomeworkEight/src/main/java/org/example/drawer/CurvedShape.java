package org.example.drawer;

public interface CurvedShape {

    String getName();

    Point[] getPoints(); // returns minimum of three non-collinear points

    double getRadius();

    double getCircumference();

}
