package org.example.shapes;

import org.example.drawer.CurvedShape;
import org.example.drawer.Point;
import org.example.drawer.PolygonShape;

public abstract class Shape implements PolygonShape, CurvedShape {

    public abstract String getName();

    public abstract Point[] getPoints();

    public abstract int getNumberOfSides();

    public abstract double getAngle(String str);

    public abstract void getSidesLength();

    public abstract double getRadius();

    public abstract double getCircumference();

}
