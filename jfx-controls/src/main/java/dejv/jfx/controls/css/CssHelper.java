package dejv.jfx.controls.css;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class CssHelper {

    public static StyleableDoubleProperty createStyleableDoubleProperty(Object bean, String name, CssMetaData<? extends Styleable, Number> cssMetadata) {
        return new StyleableDoubleProperty() {
            @Override
            public Object getBean() {
                return bean;
            }


            @Override
            public String getName() {
                return name;
            }


            @Override
            public CssMetaData<? extends Styleable, Number> getCssMetaData() {
                return cssMetadata;
            }


            @Override
            protected void invalidated() {
                System.out.println(name + " was invalidated");
            }
        };
    }
}
