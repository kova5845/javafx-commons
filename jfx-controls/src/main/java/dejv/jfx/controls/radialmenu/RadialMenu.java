package dejv.jfx.controls.radialmenu;

import javafx.beans.DefaultProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

import dejv.jfx.controls.radialmenu.internal.RadialMenuItem;


/**
 * A JavaFX menu control with radial item layout.
 * <p>
 * <h2>Definition</h2>
 * The menu structure is based on {@link Menu}, using {@link MenuItem#getGraphic()} as the primary label for its items and {@link MenuItem#getText()} as their tooltip.
 * <p>
 * When a specific menu item is selected by the user, the action set to related <code>MenuItem</code> as {@link MenuItem#onActionProperty()} is triggered.
 * <p>
 * <h2>Customization</h2>
 * The appearance of the menu can be customized in numerous ways:
 * <ul>
 * <li>Limit angles
 * <li>Arc radius
 * <li>Item spacing
 * <li>Menu level spacing
 * <li>Menu item size
 * <li>Menu direction (clockwise / counterclockwise} - can be defined either directly, or by setting the limit angles in opposite order
 * <li>Custom style
 * </ul>
 * <p>
 * <h2>Styling</h2>
 * Menu buttons use "roundButton" as their style ID, so this can be used to override the default style with custom menu appearance.
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.2.0
 */
@SuppressWarnings("unused")
@DefaultProperty("menu")
public class RadialMenu
        extends ToggleButton {

    private Menu menu;
    private RadialMenuParams radialMenuParams = new RadialMenuParams();


    public RadialMenu() {

        selectedProperty().addListener(this::onSelected);
    }


    /**
     * Returns the menu model.
     *
     * @return The menu model.
     */
    public Menu getMenu() {
        return menu;
    }


    /**
     * Sets the menu model.
     *
     * @param menu Structure of {@link MenuItem}s to display.
     */
    public void setMenu(Menu menu) {
        this.menu = menu;

        RadialMenuItem.setupMenuButton(this, radialMenuParams, (menu != null) ? menu.getGraphic() : null, (menu != null) ? menu.getText() : null, true);
    }


    /**
     * Returns the customization parameters of the menu.
     *
     * @return Customization parameters of the <code>RadialMenu</code>.
     */
    public RadialMenuParams getRadialMenuParams() {
        return radialMenuParams;
    }


    private void onSelected(ObservableValue<? extends Boolean> observable, Boolean previouslySelected, Boolean nowSelected) {
        final Parent parent = getParent();

        if ((nowSelected) && (parent != null) && (parent instanceof Pane) && (menu != null)) {
            final ContextRadialMenu contextRadialMenu = new ContextRadialMenu((Pane) getParent(), menu, radialMenuParams);
            contextRadialMenu.setOnHide((event) -> setSelected(false));
            contextRadialMenu.showOver(this);

        } else {
            setSelected(false);
        }
    }
}
