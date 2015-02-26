package dejv.jfx.commons.geometry;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 *
 * @author dejv78 (www.dejv.info)
 */
public class ObservableDimension2D {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObservableDimension2D.class);

    protected final DoubleProperty width = new SimpleDoubleProperty();
    protected final DoubleProperty height = new SimpleDoubleProperty();


    public ObservableDimension2D() {
        LOGGER.trace("ObservableDimension()");
    }


    public ObservableDimension2D(double width, double height) {
        LOGGER.trace("ObservableDimension2D(width={}, height={})", width, height);

        setWidth(width);
        setHeight(height);
    }


    public ObservableDimension2D(DoubleExpression width, DoubleExpression height) {
        LOGGER.trace("ObservableDimension2D(width={}, height={})", width, height);

        this.width.bind(width);
        this.height.bind(height);
    }


    public double getWidth() {
        return width.get();
    }


    public final void setWidth(double width) {
        this.width.set(width);
    }


    public DoubleProperty widthProperty() {
        return width;
    }


    public double getHeight() {
        return height.get();
    }


    public final void setHeight(double height) {
        this.height.set(height);
    }


    public DoubleProperty heightProperty() {
        return height;
    }
}
