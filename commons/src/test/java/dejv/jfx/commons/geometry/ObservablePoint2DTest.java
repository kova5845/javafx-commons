package dejv.jfx.commons.geometry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

/**
 * Test class for ObservablePoint2D.
 * <br/>
 *
 * @author dejv78 (dejv78.github.io)
 */
public class ObservablePoint2DTest {

    @Test
    public void createObservablePoint2DWithDefaultConstructor() {
        final ObservablePoint2D cut = new ObservablePoint2D();

        assertNotNull("X property", cut.xProperty());
        assertNotNull("Y property", cut.yProperty());
        assertEquals("X value", 0.0d, cut.getX(), Precision.EPSILON);
        assertEquals("Y value", 0.0d, cut.getY(), Precision.EPSILON);
    }


    @Test
    public void createObservablePoint2DWithFixedParams() {
        final double x = 20.0d;
        final double y = 30.5d;
        final ObservablePoint2D cut = new ObservablePoint2D(x, y);

        assertNotNull("X property", cut.xProperty());
        assertNotNull("Y property", cut.yProperty());
        assertEquals("X value", x, cut.getX(), Precision.EPSILON);
        assertEquals("Y value", y, cut.getY(), Precision.EPSILON);
    }


    @Test
    public void createObservablePoint2DWithBinding() {
        final DoubleProperty x = new SimpleDoubleProperty(20.0d);
        final DoubleProperty y = new SimpleDoubleProperty(30.5d);
        final ObservablePoint2D cut = new ObservablePoint2D(x, y);

        assertNotNull("X property", cut.xProperty());
        assertNotNull("Y property", cut.yProperty());
        assertEquals("X value", x.doubleValue(), cut.getX(), Precision.EPSILON);
        assertEquals("Y value", y.doubleValue(), cut.getY(), Precision.EPSILON);

        x.set(50.0d);
        y.set(120.1d);
        assertEquals("X value", x.doubleValue(), cut.getX(), Precision.EPSILON);
        assertEquals("Y value", y.doubleValue(), cut.getY(), Precision.EPSILON);
    }
}
