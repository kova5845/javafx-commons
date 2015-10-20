package dejv.jfx.controls.radialmenu;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import dejv.jfx.controls.css.CssHelper;
import dejv.jfx.controls.radialmenu.css.RadialMenuButtonStyle;
import dejv.jfx.controls.radialmenu.skin.RadialMenuButtonSkin;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuButton
        extends Control {
    private static final String DEFAULT_STYLE_CLASS = "radial-menu-button";

    private static final String PROP_NAME_SIZE = "size";

    private final ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();

    private DoubleProperty size;


    public RadialMenuButton() {
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
    }


    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }


    public boolean isSize() {
        return (size != null);
    }


    public double getSize() {
        return (size == null) ? RadialMenuButtonStyle.DEFAULT_SIZE : sizeProperty().get();
    }


    public DoubleProperty sizeProperty() {
        if (size == null) {
            size = CssHelper.createStyleableDoubleProperty(this, PROP_NAME_SIZE, RadialMenuButtonStyle.SIZE);
        }
        return size;
    }


    public void setSize(double size) {
        System.out.println("Set size: " + size);
        this.sizeProperty().set(size);
    }


    /**
     * @return The CssMetaData associated with this class, which may include the
     * <p>
     * CssMetaData of its super classes.
     * @since JavaFX 8.0
     */

    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        System.out.println("Get CssMetadata" + RadialMenuButtonStyle.STYLEABLE_PROPERTIES);
        return RadialMenuButtonStyle.STYLEABLE_PROPERTIES;
    }




    @Override
    protected Skin<?> createDefaultSkin() {
        return new RadialMenuButtonSkin(this);
    }
}
