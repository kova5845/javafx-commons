package dejv.jfx.controls.radialmenu1.internal;

/**
 * Location coordinates of Radial Menu Item.
 * <p>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.2.0
 */
public class RadialMenuItemCoords {

    private final double angle;
    private final double fromRadius;


    public RadialMenuItemCoords(double angle, double fromRadius) {
        this.angle = angle;
        this.fromRadius = fromRadius;
    }


    /**
     * @return Item angle in degrees.
     */
    public double getAngle() {
        return angle;
    }


    /**
     * @return Item "Start" radius.
     */
    public double getFromRadius() {
        return fromRadius;
    }
}
