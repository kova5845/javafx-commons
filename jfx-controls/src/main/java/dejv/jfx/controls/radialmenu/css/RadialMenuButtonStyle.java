package dejv.jfx.controls.radialmenu.css;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.value.WritableValue;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;

import dejv.jfx.controls.radialmenu.RadialMenuButton;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuButtonStyle {

    public static final double DEFAULT_SIZE = 20d;

    public static final String CSS_PROP_NAME_SIZE = "-fx-size";

    public static final List<CssMetaData<? extends Styleable, ?>> STYLEABLE_PROPERTIES;

    public static final CssMetaData<RadialMenuButton, Number> SIZE =
            new CssMetaData<RadialMenuButton, Number>(CSS_PROP_NAME_SIZE, StyleConverter.getSizeConverter(), DEFAULT_SIZE) {

                @Override
                public boolean isSettable(RadialMenuButton rmb) {
                    return !rmb.isSize() || !rmb.sizeProperty().isBound();
                }


                @SuppressWarnings("RedundantCast")
                @Override
                public StyleableProperty<Number> getStyleableProperty(RadialMenuButton rmb) {
                    return (StyleableProperty<Number>) (WritableValue<Number>) rmb.sizeProperty();
                }
            };

    static {

        final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());

        Collections.addAll(styleables, SIZE);

        STYLEABLE_PROPERTIES = Collections.unmodifiableList(styleables);
    }

}
