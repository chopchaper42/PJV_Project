package Utility;

import javafx.geometry.Point2D;

public class Pythagoras
{
    public static double leg45deg(double diagonal) {
        return diagonal * Math.cos(Math.PI / 4);
    }

    public static double diagonal(double leg1, double leg2) {
        return Math.sqrt(leg1*leg1 + leg2*leg2);
    }

    /*public static double legXLength(Point2D first, Point2D second) {
        return fi
    }

    public static double legYLength(Point2D first, Point2D second) {

    }*/
}
