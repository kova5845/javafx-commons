package dejv.jfx.controls.radialmenu.skin;

import javafx.geometry.Point2D;
import javafx.scene.control.ToggleButton;

import dejv.jfx.controls.radialmenu.RadialMenu;
import dejv.jfx.controls.radialmenu.ContextRadialMenu;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuSkin
        extends RadialMenuSkinBase<RadialMenu, ToggleButton> {

    private final ToggleButton button;
    private final ContextRadialMenu popup;


    public RadialMenuSkin(RadialMenu control) {
        super(control, new ToggleButton());

        button = getButton();
        popup = new ContextRadialMenu(control.getItems());

        button.selectedProperty().addListener((sender, oldValue, newValue) -> {
            if (newValue) {
                final double offset = control.getSize() * 0.5;
                Point2D p = button.localToScreen(offset, offset);
                popup.show(button, p.getX(), p.getY());
            }
        });
    }
}
