package dejv.jfx.controls.radialmenu;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public interface RadialMenuParams {

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


    // ---------------------- FROM ANGLE (deg)

    double getAngleFromDeg();


    DoubleProperty angleFromDegProperty();


    void setAngleFromDeg(double angleFromDeg);


    // ---------------------- TO ANGLE (deg)

    double getAngleToDeg();


    DoubleProperty angleToDegProperty();


    void setAngleToDeg(double angleToDeg);


    // ---------------------- GAP FACTOR

    double getGapFactor();


    DoubleProperty gapFactorProperty();


    void setGapFactor(double gapFactor);


    // ---------------------- SPACING FACTOR

    double getSpacingFactor();


    DoubleProperty spacingFactorProperty();


    void setSpacingFactor(double spacingFactor);


    // ---------------------- MINIMAL RADIUS

    double getMinRadius();


    DoubleProperty minRadiusProperty();


    void setMinRadius(double minRadius);


    // ---------------------- BUTTON SIZE

    double getButtonSize();


    DoubleProperty buttonSizeProperty();


    void setButtonSize(double buttonSize);


    // ---------------------- OUTER PADDING

    double getOuterPadding();


    DoubleProperty outerPaddingProperty();


    void setOuterPadding(double outerPadding);


    // ---------------------- DIRECTION

    Direction getDirection();


    ObjectProperty<Direction> directionProperty();


    void setDirection(Direction direction);
    
}
