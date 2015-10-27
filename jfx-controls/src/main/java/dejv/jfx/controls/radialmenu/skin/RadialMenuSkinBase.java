package dejv.jfx.controls.radialmenu.skin;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SkinBase;

import dejv.jfx.controls.radialmenu.RadialMenuItem;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuSkinBase<C extends RadialMenuItem, B extends ButtonBase>
        extends SkinBase<RadialMenuItem> {

    private final B button;

    private final DoubleProperty transformOpacity = new SimpleDoubleProperty();
    private final DoubleProperty transformAngle = new SimpleDoubleProperty();
    private final DoubleProperty transformRadius = new SimpleDoubleProperty();


    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public RadialMenuSkinBase(C control, B button) {
        super(control);
        this.button = button;

        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        getChildren().add(button);

        bindSize();
        bindGraphic();
        bindTooltip();
    }

    protected final B getButton() {
        return button;
    }

    private void bindSize() {
        final DoubleProperty sizeProperty = getSkinnable().sizeProperty();
        button.minWidthProperty().bind(sizeProperty);
        button.minHeightProperty().bind(sizeProperty);
        button.prefWidthProperty().bind(sizeProperty);
        button.prefHeightProperty().bind(sizeProperty);
        button.maxWidthProperty().bind(sizeProperty);
        button.maxHeightProperty().bind(sizeProperty);

        updateButtonRadius(sizeProperty.doubleValue());
        getSkinnable().sizeProperty().addListener((sender, oldValue, newValue) -> updateButtonRadius(newValue.doubleValue()));
    }


    private void bindGraphic() {
        button.graphicProperty().bind(getSkinnable().graphicProperty());
    }


    private void bindTooltip() {
        button.tooltipProperty().bind(getSkinnable().tooltipProperty());
    }


    private void updateButtonRadius(double size) {
        button.setStyle("-fx-background-radius: " + size);
    }
}
