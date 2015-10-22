package dejv.jfx.controls.radialmenu.structure;

import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
@DefaultProperty("items")
public class RadialMenu
        extends RadialMenuItem {

    private final ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();


    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }
}
