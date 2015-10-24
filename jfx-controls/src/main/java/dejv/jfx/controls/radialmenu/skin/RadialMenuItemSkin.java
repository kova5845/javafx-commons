package dejv.jfx.controls.radialmenu.skin;

import javafx.scene.control.Button;

import dejv.jfx.controls.radialmenu.RadialMenuItem;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuItemSkin
    extends RadialMenuSkinBase<RadialMenuItem, Button> {


    private final Button button;


    public RadialMenuItemSkin(RadialMenuItem control) {
        super(control, new Button());

        this.button = getButton();
    }
}
