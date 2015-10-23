package dejv.jfx.controls.radialmenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenu
        extends RadialMenuItem {

    private static final String DEFAULT_STYLE_CLASS = "radial-menu-button";

    private final ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();


    public RadialMenu() {
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
    }


    /*******************************************************************************************************
     * Properties
     *******************************************************************************************************/


     // ---------------------- MENU ITEMS
    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }


}
