package dejv.jfx.controls.radialmenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.WritableValue;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.StyleableStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.sun.javafx.css.StyleManager;

import dejv.jfx.controls.css.CssHelper;
import dejv.jfx.controls.radialmenu.skin.RadialMenuItemSkin;
import dejv.jfx.controls.radialmenu.skin.internal.RadialMenuSection;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuItem
        extends Control {

    private static final String DEFAULT_STYLE_CLASS = "radial-menu-button";
    private static final double DEFAULT_SIZE = 20d;

    private static final String PROP_NAME_SIZE = "size";
    private static final String PROP_NAME_GRAPHIC = "graphic";
    private static final String PROP_NAME_IMAGE_URL = "imageUrl";

    private static final String CSS_PROP_NAME_SIZE = "-fx-size";
    private static final String CSS_PROP_NAME_GRAPHIC = "-fx-graphic";

    private DoubleProperty size;
    private ObjectProperty<Node> graphic = new SimpleObjectProperty<>(this, PROP_NAME_GRAPHIC);
    private StyleableStringProperty imageUrl = null;

    private ObjectProperty<RadialMenuSection> section  = new SimpleObjectProperty<>();
    private DoubleProperty angle = new SimpleDoubleProperty();
    private DoubleProperty fromRadius = new SimpleDoubleProperty();


    public RadialMenuItem() {
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
    }


    public double getAngle() {
        return angle.get();
    }


    public DoubleProperty angleProperty() {
        return angle;
    }


    public void setAngle(double angle) {
        this.angle.set(angle);
    }


    public double getFromRadius() {
        return fromRadius.get();
    }


    public DoubleProperty fromRadiusProperty() {
        return fromRadius;
    }


    public void setFromRadius(double fromRadius) {
        this.fromRadius.set(fromRadius);
    }


    public RadialMenuSection getSection() {
        return section.get();
    }


    public ObjectProperty<RadialMenuSection> sectionProperty() {
        return section;
    }


    public void setSection(RadialMenuSection section) {
        this.section.set(section);
    }

    // ---------------------- SIZE


    public double getSize() {
        return (size == null) ? DEFAULT_SIZE : sizeProperty().get();
    }


    public DoubleProperty sizeProperty() {
        if (size == null) {
            size = CssHelper.createStyleableDoubleProperty(this, PROP_NAME_SIZE, SIZE);
        }
        return size;
    }


    public void setSize(double size) {
        this.sizeProperty().set(size);
    }



    // ---------------------- GRAPHIC


    public final Node getGraphic() {
        return graphic.get();
    }


    public final ObjectProperty<Node> graphicProperty() {

        return graphic;
    }


    public final void setGraphic(Node value) {

        graphic.setValue(value);
    }



    // ---------------------- IMAGE URL


    private StyleableStringProperty imageUrlProperty() {

        if (imageUrl == null) {
            imageUrl = CssHelper.createStyleableStringProperty(this, PROP_NAME_IMAGE_URL, GRAPHIC, () -> {
                final String url = imageUrlProperty().get();

                if (url != null) {
                    final Image img = StyleManager.getInstance().getCachedImage(url);

                    if (img != null) {
                        graphicProperty().set(new ImageView(img));
                    }
                } else {
                    graphicProperty().set(null);
                }
            });
        }
        return imageUrl;
    }


    /*******************************************************************************************************
     * Overrides
     *******************************************************************************************************/

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RadialMenuItemSkin(this);
    }


    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return STYLEABLE_PROPERTIES;
    }


    /*******************************************************************************************************
     * CSS Support
     *******************************************************************************************************/


    private static final CssMetaData<RadialMenuItem, Number> SIZE =
            new CssMetaData<RadialMenuItem, Number>(CSS_PROP_NAME_SIZE, StyleConverter.getSizeConverter(), DEFAULT_SIZE) {

                @Override
                public boolean isSettable(RadialMenuItem rmi) {
                    return (rmi.size == null) || !rmi.size.isBound();
                }


                @SuppressWarnings("RedundantCast")
                @Override
                public StyleableProperty<Number> getStyleableProperty(RadialMenuItem rmi) {
                    return (StyleableProperty<Number>) (WritableValue<Number>) rmi.sizeProperty();
                }
            };


    private static final CssMetaData<RadialMenuItem, String> GRAPHIC =
            new CssMetaData<RadialMenuItem, String>(CSS_PROP_NAME_GRAPHIC, StyleConverter.getUrlConverter(), null) {

                @Override
                public boolean isSettable(RadialMenuItem rmi) {
                    return ((rmi.graphic == null) || !rmi.graphic.isBound());
                }


                @SuppressWarnings("RedundantCast")
                @Override
                public StyleableProperty<String> getStyleableProperty(RadialMenuItem rmi) {
                    return rmi.imageUrlProperty();
                }
            };


    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLE_PROPERTIES;


    static {

        final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());

        Collections.addAll(styleables, SIZE, GRAPHIC);

        STYLEABLE_PROPERTIES = Collections.unmodifiableList(styleables);
    }


}
