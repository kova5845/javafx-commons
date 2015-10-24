package dejv.jfx.controls.radialmenu;

import java.util.List;

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
        extends PopupControl {

    private static final String DEFAULT_STYLE_CLASS = "radial-menu";

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
