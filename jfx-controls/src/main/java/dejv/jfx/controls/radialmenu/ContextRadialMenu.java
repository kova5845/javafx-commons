package dejv.jfx.controls.radialmenu;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

import dejv.jfx.controls.radialmenu.skin.ContextRadialMenuSkin;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class ContextRadialMenu
        extends PopupControl
        implements RadialMenuParams {

    private static final String DEFAULT_STYLE_CLASS = "radial-menu";

    private final DoubleProperty angleFromDeg = new SimpleDoubleProperty(0.0d);
    private final DoubleProperty angleToDeg = new SimpleDoubleProperty(360.0d);
    private final DoubleProperty gapFactor = new SimpleDoubleProperty(0.25d);
    private final DoubleProperty spacingFactor = new SimpleDoubleProperty(0.3d);
    private final DoubleProperty minRadius = new SimpleDoubleProperty(25.0d);

    private final DoubleProperty buttonSize = new SimpleDoubleProperty(30d);
    private final DoubleProperty outerPadding = new SimpleDoubleProperty(10d);

    private ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.CW);

    private final ObservableList<RadialMenuItem> items;


    public ContextRadialMenu() {
        this(FXCollections.observableArrayList());
    }


    public ContextRadialMenu(ObservableList<RadialMenuItem> items) {
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
        this.items = items;
    }


    /*******************************************************************************************************
     * Properties
     *******************************************************************************************************/

    // ---------------------- FROM ANGLE (deg)
    public double getAngleFromDeg() {
        return angleFromDeg.get();
    }


    public DoubleProperty angleFromDegProperty() {
        return angleFromDeg;
    }


    public void setAngleFromDeg(double angleFromDeg) {
        this.angleFromDeg.set(angleFromDeg);
    }


    // ---------------------- TO ANGLE (deg)


    public double getAngleToDeg() {
        return angleToDeg.get();
    }


    public DoubleProperty angleToDegProperty() {
        return angleToDeg;
    }


    public void setAngleToDeg(double angleToDeg) {
        this.angleToDeg.set(angleToDeg);
    }


    // ---------------------- GAP FACTOR


    public double getGapFactor() {
        return gapFactor.get();
    }


    public DoubleProperty gapFactorProperty() {
        return gapFactor;
    }


    public void setGapFactor(double gapFactor) {
        this.gapFactor.set(gapFactor);
    }


    // ---------------------- SPACING FACTOR


    public double getSpacingFactor() {
        return spacingFactor.get();
    }


    public DoubleProperty spacingFactorProperty() {
        return spacingFactor;
    }


    public void setSpacingFactor(double spacingFactor) {
        this.spacingFactor.set(spacingFactor);
    }


    // ---------------------- MINIMAL RADIUS


    public double getMinRadius() {
        return minRadius.get();
    }


    public DoubleProperty minRadiusProperty() {
        return minRadius;
    }


    public void setMinRadius(double minRadius) {
        this.minRadius.set(minRadius);
    }


    // ---------------------- BUTTON SIZE


    public double getButtonSize() {
        return buttonSize.get();
    }


    public DoubleProperty buttonSizeProperty() {
        return buttonSize;
    }


    public void setButtonSize(double buttonSize) {
        this.buttonSize.set(buttonSize);
    }


    // ---------------------- OUTER PADDING


    public double getOuterPadding() {
        return outerPadding.get();
    }


    public DoubleProperty outerPaddingProperty() {
        return outerPadding;
    }


    public void setOuterPadding(double outerPadding) {
        this.outerPadding.set(outerPadding);
    }


    // ---------------------- DIRECTION


    public Direction getDirection() {
        return direction.get();
    }


    public ObjectProperty<Direction> directionProperty() {
        return direction;
    }


    public void setDirection(Direction direction) {
        this.direction.set(direction);
    }


    // ---------------------- MENU ITEMS
    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }


    /*******************************************************************************************************
     * Overrides
     *******************************************************************************************************/


    @Override
    protected Skin<?> createDefaultSkin() {
        return new ContextRadialMenuSkin(this);
    }


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return super.getCssMetaData();
    }
}
