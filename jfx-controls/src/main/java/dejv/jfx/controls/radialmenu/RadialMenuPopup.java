package dejv.jfx.controls.radialmenu;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

import dejv.jfx.controls.radialmenu.skin.RadialMenuPopupSkin;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuPopup
        extends PopupControl {

    private static final String DEFAULT_STYLE_CLASS = "radial-menu-popup";

    private final ObjectProperty<RadialMenu> menu = new SimpleObjectProperty<>();


    public RadialMenuPopup() {
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
    }


    /*******************************************************************************************************
     * Properties
     *******************************************************************************************************/

     // ---------------------- RADIAL MENU


    public RadialMenu getMenu() {
        return menu.get();
    }


    public ObjectProperty<RadialMenu> menuProperty() {
        return menu;
    }


    public void setMenu(RadialMenu menu) {
        this.menu.set(menu);
    }


    /*******************************************************************************************************
     * Overrides
     *******************************************************************************************************/


    @Override
    protected Skin<?> createDefaultSkin() {
        return new RadialMenuPopupSkin(this);
    }


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return super.getCssMetaData();
    }
}
