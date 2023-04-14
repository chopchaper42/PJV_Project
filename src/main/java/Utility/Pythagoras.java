package Utility;

import javafx.geometry.Point2D;

public class Pythagoras
{
    /**
     * Calculates the length of the adjacent leg
     * @param diagonal diagonal
     * @return length of the adjacent leg
     */
    public static double leg45deg(double diagonal) {
        return diagonal * Math.cos(Math.PI / 4);
    }

    /**
     * Calculates the diagonal using the two legs
     * @param leg1 the first leg
     * @param leg2 the second leg
     * @return the length of the diagonal
     */
    public static double diagonal(double leg1, double leg2) {
        return Math.sqrt(leg1*leg1 + leg2*leg2);
    }
}
