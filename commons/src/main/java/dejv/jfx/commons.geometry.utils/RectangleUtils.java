package dejv.jfx.commons.geometry.utils;

import static java.util.Objects.requireNonNull;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Useful Rectangle2D operations.
 *
 * @author dejv78 (www.dejv.info)
 */
public class RectangleUtils {

    public static Point2D center(Bounds bounds) {
        requireNonNull(bounds, "bounds is null");

        return new Point2D(bounds.getMinX() + bounds.getWidth() / 2d, bounds.getMinY() + bounds.getHeight() / 2d);
    }


    public static Point2D center(Rectangle2D bounds) {
        requireNonNull(bounds, "bounds is null");

        return new Point2D(bounds.getMinX() + bounds.getWidth() / 2d, bounds.getMinY() + bounds.getHeight() / 2d);
    }


    public static Rectangle2D union(Rectangle2D... bounds) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Rectangle2D b : bounds) {
            minX = Math.min(minX, b.getMinX());
            minY = Math.min(minY, b.getMinY());
            maxX = Math.max(maxX, b.getMaxX());
            maxY = Math.max(maxY, b.getMaxY());
        }

        return new Rectangle2D(minX, minY, maxX - minX, maxY - minY);
    }


    public static Rectangle2D centerResize(Rectangle2D bounds, double horizontalDelta, double verticalDelta) {
        requireNonNull(bounds, "bounds is null");

        double w = bounds.getWidth() + horizontalDelta;
        if (w < 0) {
            w = 0.0d;
        }

        double h = bounds.getHeight() + verticalDelta;
        if (h < 0) {
            h = 0.0d;
        }

        final double centerX = bounds.getMinX() + bounds.getWidth() * .5d;
        final double centerY = bounds.getMinY() + bounds.getHeight() * .5d;

        return new Rectangle2D(centerX - w * .5d, centerY - h * .5d, w, h);
    }


    public static Rectangle2D fromBounds(Bounds bounds) {
        requireNonNull(bounds, "bounds is null");

        return new Rectangle2D(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
    }
}
