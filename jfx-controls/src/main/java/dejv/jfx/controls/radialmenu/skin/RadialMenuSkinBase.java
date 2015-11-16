package dejv.jfx.controls.radialmenu.skin;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SkinBase;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

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
    private final C model;

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
        this.model = control;

        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        getChildren().add(button);

        bindSize();
        bindGraphic();
        bindTooltip();
        bindCoords();

        button.setVisible(false);
        updateVisibility();
    }


    protected final B getButton() {
        return button;
    }

    private void bindSize() {
        final DoubleProperty sizeProperty = model.sizeProperty();
        button.minWidthProperty().bind(sizeProperty);
        button.minHeightProperty().bind(sizeProperty);
        button.prefWidthProperty().bind(sizeProperty);
        button.prefHeightProperty().bind(sizeProperty);
        button.maxWidthProperty().bind(sizeProperty);
        button.maxHeightProperty().bind(sizeProperty);

        updateButtonRadius(sizeProperty.doubleValue());
        model.sizeProperty().addListener((sender, oldValue, newValue) -> updateButtonRadius(newValue.doubleValue()));
    }


    private void bindGraphic() {
        button.graphicProperty().bind(model.graphicProperty());
    }


    private void bindTooltip() {
        button.tooltipProperty().bind(model.tooltipProperty());
    }


    private void bindCoords() {
        final DoubleExpression radius = model.sizeProperty().multiply(0.5);
        final Translate translation = new Translate();
        translation.xProperty().bind(transformRadius);

        final Rotate rotation = new Rotate();
        rotation.pivotXProperty().bind(transformRadius.subtract(radius).multiply(-1));
        rotation.pivotYProperty().bind(radius);
        rotation.angleProperty().bind(transformAngle);

        button.getTransforms().addAll(translation, rotation);

        final Rotate twist = new Rotate();
        twist.pivotXProperty().bind(radius);
        twist.pivotYProperty().bind(radius);
        twist.angleProperty().bind(transformAngle.multiply(-1.0d));

        button.getTransforms().add(twist);

        model.visibleProperty().addListener((observable) -> {
            updateVisibility();
        });
    }



    private void updateVisibility() {
        if (model.isVisible()) {
            show();
        }
        else {
            hide();
        }
    }


    /**
     * Trainsit the button to "Visible" state.
     */
    private void show() {
        if (button.isVisible()) {
            return;
        }

        resetToInitialState();

        button.setVisible(true);

        if (model.getSection() != null) {
            final Timeline phaseOne = new Timeline();
            phaseOne.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(transformRadius, model.getFromRadius()), new KeyValue(transformOpacity, 0)),
                    new KeyFrame(new Duration(120), new KeyValue(transformRadius, model.getSection().getNominalRadius()), new KeyValue(transformOpacity, 1))
            );

            final Timeline phaseTwo = new Timeline();
            phaseTwo.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(transformAngle, model.getSection().getAngularAxisDeg())),
                    new KeyFrame(new Duration(80), new KeyValue(transformAngle, model.getAngle()))
            );

            phaseOne.setOnFinished(event -> phaseTwo.play());

            phaseTwo.setOnFinished(event -> button.setMouseTransparent(false));

            phaseOne.play();
        }
        else {
            button.setMouseTransparent(false);
        }
    }


    /**
     * Hide the button.
     */
    private void hide() {
        if (!button.isVisible()) {
            return;
        }
        button.setVisible(false);

        resetToInitialState();
    }

    private void updateButtonRadius(double size) {
        button.setStyle("-fx-background-radius: " + size);
    }

    private void resetToInitialState() {
        button.setMouseTransparent(true);

        transformOpacity.set(0);

        if (model.getSection() != null) {
            transformAngle.set(model.getSection().getAngularAxisDeg());
            transformRadius.set(model.getFromRadius());
        }
    }
}
