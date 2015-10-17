package dejv.jfx.controls.radialmenu.internal;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import dejv.jfx.controls.radialmenu.RadialMenuParams;

/**
 * Radial Menu Item class.
 * <p>
 * Radial Menu Item is technically a round button, with graphics and optionally a small arrow (for Menu Items with children).
 * Each item belongs to a specific {@link RadialMenuSection}.
 * Its position within the section is maintained using several transforms, and its transition to that position is animated.
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.2.0
 */
@SuppressWarnings("unused")
public class RadialMenuItem
        extends Pane {

    public static final String CSS_ID = "roundButton";
    public static final String CSS_NAME = "roundButton.css";

    private final Button button;

    private final RadialMenuSection parentSection;
    private final double angle;
    private final double fromRadius;

    private double radius;

    private final DoubleProperty transformOpacity = new SimpleDoubleProperty();
    private final DoubleProperty transformAngle = new SimpleDoubleProperty();
    private final DoubleProperty transformRadius = new SimpleDoubleProperty();


    public RadialMenuItem(RadialMenuSection parentSection, RadialMenuParams params, MenuItem item, RadialMenuItemCoords itemCoords) {

        this.parentSection = parentSection;

        this.angle = itemCoords.getAngle();
        this.fromRadius = itemCoords.getFromRadius();

        this.button = generateButton(params, item.getGraphic(), item.getText(), item.isDisable());

        this.radius = button.getPrefHeight() * 0.5;

        final Translate translation = new Translate();
        translation.xProperty().bind(transformRadius);

        final Rotate rotation = new Rotate();
        rotation.pivotXProperty().bind(transformRadius.subtract(radius).multiply(-1));
        rotation.pivotYProperty().set(radius);
        rotation.angleProperty().bind(transformAngle);

        this.getTransforms().addAll(translation, rotation);

        final Rotate twist = new Rotate();
        twist.pivotXProperty().set(radius);
        twist.pivotYProperty().set(radius);
        twist.angleProperty().bind(transformAngle.multiply(-1.0d));

        button.getTransforms().add(twist);

        getChildren().add(button);

        if (item instanceof Menu) {
            final Text caret = generateCaret(params);
            getChildren().add(caret);
        }


        setVisible(false);
    }


    /**
     * @return The rotation angle of the Item.
     */
    public double getAngle() {
        return angle;
    }


    /**
     * Sets the action to perform, when the item is activated (clicked) by the user.
     *
     * @param actionHandler Action to perform
     */
    public void setOnAction(EventHandler<ActionEvent> actionHandler) {
        button.setOnAction(actionHandler);
    }


    /**
     * Sets the action to perform, when the item is rolled over (mouse entered) by the user.
     *
     * @param triggerHandler Action to perform
     */
    public void setOnTrigger(EventHandler<MouseEvent> triggerHandler) {
        button.setOnMouseEntered(triggerHandler);
    }


    /**
     * Trainsit the button to "Visible" state.
     */
    public void show() {
        if (isVisible()) {
            return;
        }

        resetToInitialState();

        setVisible(true);

        final Timeline phaseOne = new Timeline();
        phaseOne.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(transformRadius, fromRadius), new KeyValue(transformOpacity, 0)),
                new KeyFrame(new Duration(120), new KeyValue(transformRadius, parentSection.getNominalRadius()), new KeyValue(transformOpacity, 1))
        );

        final Timeline phaseTwo = new Timeline();
        phaseTwo.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(transformAngle, parentSection.getAngularAxisDeg())),
                new KeyFrame(new Duration(80), new KeyValue(transformAngle, angle))
        );

        phaseOne.setOnFinished(event -> phaseTwo.play());

        phaseTwo.setOnFinished(event -> setMouseTransparent(false));

        phaseOne.play();
    }


    /**
     * Hide the button.
     */
    public void hide() {
        if (!isVisible()) {
            return;
        }
        setVisible(false);

        resetToInitialState();
    }


    /**
     * Setup the round button.
     *
     * @param button  Button to setup
     * @param params  Customization parameters
     * @param graphic Graphics contents
     * @param tooltip Tooltip text
     */
    public static void setupMenuButton(ButtonBase button, RadialMenuParams params, Node graphic, String tooltip) {
        button.minWidthProperty().bind(params.buttonSizeProperty());
        button.minHeightProperty().bind(params.buttonSizeProperty());
        button.prefWidthProperty().bind(params.buttonSizeProperty());
        button.prefHeightProperty().bind(params.buttonSizeProperty());
        button.maxWidthProperty().bind(params.buttonSizeProperty());
        button.maxHeightProperty().bind(params.buttonSizeProperty());

        button.setId(CSS_ID);
        button.getStylesheets().add(CSS_NAME);

        button.setGraphic(graphic);
        button.setTooltip(new Tooltip(tooltip));
    }


    private void resetToInitialState() {
        setMouseTransparent(true);

        transformOpacity.set(0);
        transformAngle.set(parentSection.getAngularAxisDeg());
        transformRadius.set(fromRadius);
    }


    private Button generateButton(RadialMenuParams params, Node graphics, String tooltip, boolean disabled) {
        final Button b = new Button();
        setupMenuButton(b, params, graphics, tooltip);

        b.setDisable(disabled);
        return b;
    }


    private Text generateCaret(RadialMenuParams params) {
        final FontAwesomeIcon icon = FontAwesomeIcon.CARET_RIGHT;
        final Text result = GlyphsDude.createIcon(icon, "1em");
        result.setY(radius);
        result.setTextOrigin(VPos.CENTER);

        final Translate caretTranslation = new Translate();
        caretTranslation.setX(button.getPrefHeight() + params.outerPaddingProperty().get() * 0.5);
        result.getTransforms().add(caretTranslation);
        return result;
    }


    @Override
    public String toString() {
        return "RadialMenuItem{Text=" + button.getTooltip().getText() + ", angle=" + angle + "}";
    }
}
