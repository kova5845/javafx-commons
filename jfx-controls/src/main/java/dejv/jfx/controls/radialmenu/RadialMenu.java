package dejv.jfx.controls.radialmenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;

import dejv.jfx.controls.radialmenu.skin.RadialMenuSkin;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenu
        extends RadialMenuItem {

    private final ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();


    /*******************************************************************************************************
     * Properties
     *******************************************************************************************************/


    // ---------------------- MENU ITEMS
    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new RadialMenuSkin(this);
    }
}
