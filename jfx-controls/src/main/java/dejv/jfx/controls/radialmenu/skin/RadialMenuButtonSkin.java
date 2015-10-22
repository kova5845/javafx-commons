package dejv.jfx.controls.radialmenu.skin;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;

import dejv.jfx.controls.radialmenu.RadialMenuButton;
import dejv.jfx.controls.radialmenu.RadialMenuPopup;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuButtonSkin
        extends SkinBase<RadialMenuButton> {

    private final ToggleButton button;
    private final RadialMenuPopup popup = new RadialMenuPopup();


    public RadialMenuButtonSkin(RadialMenuButton control) {
        super(control);

        button = new ToggleButton();
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        getChildren().add(button);

        bindSize();
        bindGraphic();
        bindTooltip();
        bindMenu();

        button.selectedProperty().addListener((sender, oldValue, newValue) -> {
            if (newValue) {
                final double offset = control.getSize() * 0.5;
                Point2D p = button.localToScreen(offset, offset);
                popup.show(button, p.getX(), p.getY());
            }
        });
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


    private void bindMenu() {
        popup.menuProperty().bind(getSkinnable().menuProperty());
    }


    private void updateButtonRadius(double size) {
        button.setStyle("-fx-background-radius: " + size);
    }
}
