package dejv.jfx.commons.geometry;

import static junit.framework.TestCase.assertEquals;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

/**
 * Test class for CompositeObservableBounds.
 * <br/>
 *
 * @author dejv78 (www.dejv.info)
 */
public class CompositeObservableBoundsTest {

    @Test
    public void createCompositeObservableBoundsWithDefaultConstructor() {
        final CompositeObservableBounds cut = new CompositeObservableBounds();

        assertEquals("minX value", 0, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 0, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 0, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 0, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 0, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 0, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 0, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 0, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 0, cut.maxZProperty().get(), Precision.EPSILON);
    }


    @Test
    public void createCompositeObservableBoundsWithValidBounds() {
        final Bounds b1 = new BoundingBox(10, 20, 30, 100, 200, 300);
        final ObjectProperty<Bounds> boundsProperty = new SimpleObjectProperty<>(b1);

        final CompositeObservableBounds cut = new CompositeObservableBounds(boundsProperty);

        assertEquals("minX value", 10, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 20, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 30, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 60, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 120, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 180, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 110, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 220, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 330, cut.maxZProperty().get(), Precision.EPSILON);

    }


    @Test(expected = NullPointerException.class)
    public void createCompositeObservableBoundsWithNullBounds() {
        new CompositeObservableBounds(null);
    }

    @Test
    public void callAddWithValidBounds() {
        final Bounds b1 = new BoundingBox(100, 100, 100, 100, 100, 100);
        final ObjectProperty<Bounds> bounds1Property = new SimpleObjectProperty<>(b1);

        final Bounds b2 = new BoundingBox(300, 0, 300, 100, 100, 100);
        final ObjectProperty<Bounds> bounds2Property = new SimpleObjectProperty<>(b2);

        final Bounds b2a = new BoundingBox(200, 200, 200, 400, 400, 400);

        final CompositeObservableBounds cut = new CompositeObservableBounds(bounds1Property);

        assertEquals("minX value", 100, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 100, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 100, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 150, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 150, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 150, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 200, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 200, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 200, cut.maxZProperty().get(), Precision.EPSILON);

        cut.add(bounds2Property);

        assertEquals("minX value", 100, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 0, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 100, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 250, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 100, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 250, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 400, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 200, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 400, cut.maxZProperty().get(), Precision.EPSILON);

        bounds2Property.set(b2a);

        assertEquals("minX value", 100, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 100, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 100, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 350, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 350, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 350, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 600, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 600, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 600, cut.maxZProperty().get(), Precision.EPSILON);
    }


    @Test(expected = NullPointerException.class)
    public void callAddWithNullBounds() {
        final Bounds b1 = new BoundingBox(10, 20, 30, 100, 200, 300);
        final ObjectProperty<Bounds> boundsProperty = new SimpleObjectProperty<>(b1);

        final CompositeObservableBounds cut = new CompositeObservableBounds(boundsProperty);

        cut.add(null);
    }


    @Test
    public void callClear() {
        final Bounds b1 = new BoundingBox(10, 20, 30, 100, 200, 300);
        final ObjectProperty<Bounds> boundsProperty = new SimpleObjectProperty<>(b1);

        final CompositeObservableBounds cut = new CompositeObservableBounds(boundsProperty);

        assertEquals("minX value", 10, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 20, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 30, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 60, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 120, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 180, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 110, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 220, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 330, cut.maxZProperty().get(), Precision.EPSILON);

        cut.clear();

        assertEquals("minX value", 0, cut.minXProperty().get(), Precision.EPSILON);
        assertEquals("minY value", 0, cut.minYProperty().get(), Precision.EPSILON);
        assertEquals("minZ value", 0, cut.minZProperty().get(), Precision.EPSILON);
        assertEquals("centerX value", 0, cut.centerXProperty().get(), Precision.EPSILON);
        assertEquals("centerY value", 0, cut.centerYProperty().get(), Precision.EPSILON);
        assertEquals("centerZ value", 0, cut.centerZProperty().get(), Precision.EPSILON);
        assertEquals("maxX value", 0, cut.maxXProperty().get(), Precision.EPSILON);
        assertEquals("maxY value", 0, cut.maxYProperty().get(), Precision.EPSILON);
        assertEquals("maxZ value", 0, cut.maxZProperty().get(), Precision.EPSILON);
    }

}
