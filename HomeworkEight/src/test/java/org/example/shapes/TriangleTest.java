package org.example.shapes;

import org.example.drawer.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.example.shapes.Triangle;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void getName() {
        Triangle triangle = new Triangle(new int[]{0, 0, 1, 1, 2, 2});
        assertEquals("Triangle", triangle.getName());
    }

    @Test
    void getPoints() {
        // Arrange
        Triangle triangle = new Triangle(new int[]{0, 0, 1, 1, 2, 2});
        Point[] expectedPoints = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};

        // Act
        boolean arePointsEqual = Triangle.arePointsTheSame(triangle, expectedPoints);

        // Assert
        // Assertions.assertTrue(triangle.equals(expectedPoints));
        // Assertions.assertTrue(Triangle.arePointsTheSame(triangle,expectedPoints));
        Assertions.assertTrue(arePointsEqual);
    }

    @Test
    void getNumberOfSides() {
    }

    @Test
    void getAngle() {
    }

    @Test
    void getSidesLength() {
    }
}