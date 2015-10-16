package dejv.jfx.controls.radialmenu;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Customization parameters for Radial Menu.
 * <p>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RadialMenuParams {

    /**
     * Radial direction
     */
    public enum Direction {
        /**
         * Clockwise
         */
        CW,

        /**
         * Counterclockwise
         */
        CCW
    }

    private DoubleProperty angleFromDeg = new SimpleDoubleProperty(0.0d);
    private DoubleProperty angleToDeg = new SimpleDoubleProperty(360.0d);
    private DoubleProperty gapFactor = new SimpleDoubleProperty(0.25d);
    private DoubleProperty spacingFactor = new SimpleDoubleProperty(0.3d);
    private DoubleProperty minRadius = new SimpleDoubleProperty(25.0d);

    private DoubleProperty buttonSize = new SimpleDoubleProperty(30d);
    private DoubleProperty outerPadding = new SimpleDoubleProperty(10d);

    private ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.CW);
    private String styleSheet;


    /**
     * Sets limit angles of the menu arc (in degrees).
     * <p>
     * There are two limit angles - "from" (arc start), and "to" (arc end).
     * <p>
     * If the "to" angle is greater than "from" angle, arc direction (order of menu items) is clockwise.
     * If the "to" angle is less than "from" angle, arc direction (order of menu items) is counterclockwise.
     * If the limited arc size is too small to accomodate all menu items, its radius will increase.
     *
     * @param angleFromDeg "From" angle in degrees - start angle of the menu arc
     * @param angleToDeg   "To" angle in degrees - end angle of the menu arc
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setAngles(double angleFromDeg, double angleToDeg) {
        if (angleFromDeg > angleToDeg) {
            final double tmpDeg = angleFromDeg;
            angleFromDeg = angleToDeg;
            angleToDeg = tmpDeg;

            setDirection(Direction.CCW);
        }

        this.angleFromDeg.set(angleFromDeg);
        this.angleToDeg.set(angleToDeg);
        return this;
    }


    /**
     * Returns the "from" angle value in degrees
     * @return The "from" angle value in degrees
     * @see #setAngles(double, double)
     */
    public double getAngleFromDeg() {
        return angleFromDeg.get();
    }


    /**
     * @see #getAngleFromDeg()
     */
    public ReadOnlyDoubleProperty angleFromDegProperty() {
        return angleFromDeg;
    }


    /**
     * Returns the "to" angle value in degrees
     * @return The "to" angle value in degrees
     */
    public double getAngleToDeg() {
        return angleToDeg.get();
    }


    /**
     * @see #getAngleToDeg()
     */
    public ReadOnlyDoubleProperty angleToDegProperty() {
        return angleToDeg;
    }


    /**
     * Returns the factor to specify the size of the gap between menu items, in relation to menu items size.
     * @return The gap factor
     * @see #setGapFactor(double)
     */
    public double getGapFactor() {
        return gapFactor.get();
    }


    /**
     * @see #setGapFactor(double)
     */
    public DoubleProperty gapFactorProperty() {
        return gapFactor;
    }


    /**
     * Sets the factor to specify the size of the gap between menu items, in relation to menu items size.
     * 1.0 would set the gaps to be the same size as the menu item radius. Default value is 0.25
     *
     * @param gapFactor Gap size to Menu Item size factor
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setGapFactor(double gapFactor) {
        this.gapFactor.set(gapFactor);
        return this;
    }


    /**
     * Returns the factor to specify the offset between adjacent menu arcs (levels), in relation to menu items size.
     * @return The spacing factor
     * @see #setSpacingFactor(double)
     */
    public double getSpacingFactor() {
        return spacingFactor.get();
    }


    /**
     * @see #setSpacingFactor(double)
     */
    public DoubleProperty spacingFactorProperty() {
        return spacingFactor;
    }


    /**
     * Sets the factor to specify the offset between adjacent menu arcs (levels), in relation to menu items size.
     * 1.0 would set the offset to be the same size as the menu item radius. Default value is 0.3
     *
     * @param spacingFactor Arc offset to Menu Item size factor
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setSpacingFactor(double spacingFactor) {
        this.spacingFactor.set(spacingFactor);
        return this;
    }


    /**
     * Returns the minimum radius of the menu arc.
     * @return The minimum radius of the menu arc.
     * @see #setMinRadius(double)
     */
    public double getMinRadius() {
        return minRadius.get();
    }


    /**
     * @see #setMinRadius(double)
     */
    public DoubleProperty minRadiusProperty() {
        return minRadius;
    }


    /**
     * Sets the minimum radius of the top-level menu arc.
     * Minimum radius is driven by the menu arc size (difference between limit angles), and the number of contained menu items.
     * In other words, Minimum radius is automatically calculated to create arc large enough to accomodate all menu items (together with gaps between them) inside the preset angles.
     * Setting the Minimum radius higher than this automatic value will make the menu arc larger, and the menu items will no longer span all the way to limit angles.
     *
     * @param minRadius Minimum radius value.
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setMinRadius(double minRadius) {
        this.minRadius.set(minRadius);
        return this;
    }


    /**
     * Returns the Menu Item button size.
     * @return The Menu Item button size.
     * @see #setButtonSize(double)
     */
    public double getButtonSize() {
        return buttonSize.get();
    }


    /**
     * @see #setButtonSize(double)
     */
    public DoubleProperty buttonSizeProperty() {
        return buttonSize;
    }


    /**
     * Sets the Menu Item button size.
     *
     * @param buttonSize Menu Item size. Default is 30.
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setButtonSize(double buttonSize) {
        this.buttonSize.set(buttonSize);
        return this;
    }


    /**
     * Returns the padding value between Menu Items and the outer rim of the Menu arc.
     * @return The padding value between Menu Items and the outer rim of the Menu arc.
     * @see #setOuterPadding(double)
     */
    public double getOuterPadding() {
        return outerPadding.get();
    }


    /**
     * @see #setOuterPadding(double)
     */
    public DoubleProperty outerPaddingProperty() {
        return outerPadding;
    }


    /**
     * Sets the padding value between Menu Items and the outer rim of the Menu arc.
     * This is the area where a little "arrow" symbol is placed for Menu Items with children.
     *
     * @param outerPadding Padding value. Default is 10.
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setOuterPadding(double outerPadding) {
        this.outerPadding.set(outerPadding);
        return this;
    }


    /**
     * Returns the Menu Items radial direction.
     * @return The Menu Items direction.
     * @see #setDirection(Direction)
     */
    public Direction getDirection() {
        return direction.get();
    }


    /**
     * @see #setDirection(Direction)
     */
    public ObjectProperty<Direction> directionProperty() {
        return direction;
    }


    /**
     * Sets the Menu Items direction.
     * CW (Clockwise) means, that the first Menu Item is closest towards "from" angle.
     * CCW (Counterclockwise) means, that the first Menu Item is closest towards "to" angle.
     *
     * @param direction CW or CCW. Default is CW.
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setDirection(Direction direction) {
        this.direction.set(direction);
        return this;
    }


    /**
     * Returns the stylesheet name for the Radial Menu.
     * @return Stylesheet name.
     */
    public String getStyleSheet() {
        return styleSheet;
    }


    /**
     * Set the stylesheet for the Radial Menu.
     *
     * @param styleSheet Stylesheet path (for example "fxml/style.css")
     * @return The current RadialMenuParams instance for fluent setup
     */
    public RadialMenuParams setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
        return this;
    }
}
