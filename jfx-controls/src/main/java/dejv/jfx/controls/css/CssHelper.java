package dejv.jfx.controls.css;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableStringProperty;

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
        };
    }


    public static <T> StyleableObjectProperty<T> createStyleableObjectProperty(Object bean, String name, CssMetaData<? extends Styleable, T> cssMetadata) {
        return new StyleableObjectProperty<T>() {
            @Override
            public Object getBean() {
                return bean;
            }


            @Override
            public String getName() {
                return name;
            }


            @Override
            public CssMetaData<? extends Styleable, T> getCssMetaData() {
                return cssMetadata;
            }
        };
    }


    public static StyleableStringProperty createStyleableStringProperty(Object bean, String name, CssMetaData<? extends Styleable, String> cssMetadata, Runnable invalidated) {
        return new StyleableStringProperty() {
            @Override
            public Object getBean() {
                return bean;
            }


            @Override
            public String getName() {
                return name;
            }


            @Override
            public CssMetaData<? extends Styleable, String> getCssMetaData() {
                return cssMetadata;
            }


            @Override
            protected void invalidated() {
                if (invalidated != null) {
                    invalidated.run();
                }
            }
        };
    }

}
