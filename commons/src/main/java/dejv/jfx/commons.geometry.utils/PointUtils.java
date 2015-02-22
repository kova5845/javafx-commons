package dejv.jfx.commons.geometry.utils;

import javafx.geometry.Point2D;


/**
 * Useful Point2D operations.
 *
 * @author dejv78 (www.dejv.info)
 */
public class PointUtils {

    public static double angle(Point2D p1, Point2D p2) {
        final double xDiff = p2.getX() - p1.getX();
        final double yDiff = p2.getY() - p1.getY();
        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }


//    public static Point2D projection(Point2D p, Line2D line) {
//        final double negReciprocalSlope = -1d / line.getSlope();
//        final double perpendicularYIntercept = p.getY() - negReciprocalSlope * p.getX();
//        final double xc = (perpendicularYIntercept - line.getYIntercept()) / (line.getSlope() - negReciprocalSlope);
//        final double yc = line.y(xc);
//        return new Point2D(xc, yc);
//    }
}
