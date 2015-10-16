package dejv.jfx.controls.radialmenu;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

import dejv.jfx.controls.radialmenu.event.RadialMenuEventSource;
import dejv.jfx.controls.radialmenu.internal.RadialMenuSection;
import dejv.jfx.controls.radialmenu.internal.RadialPane;


/**
 * A JavaFX menu control with radial item layout (Suitable for context menus).
 * <p>
 * <code>ContextRadialMenu</code> is defined in the same way as {@link RadialMenu}, the major difference is, that
 * ContextRadialMenu doesn't display its top level element (the menu trigger button), as the menu is usually opened dynamically on specified coordinates.
 * <p>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @see RadialMenu
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ContextRadialMenu {

    private final ObjectProperty<EventHandler<ActionEvent>> onHide = new SimpleObjectProperty<>();
    private final Pane parent;
    private final Menu menu;

    private final RadialMenuParams params;

    private final RadialPane radialPane = new RadialPane();
    private final Popup popup = new Popup();
    private final Circle pointer = new Circle();


    /**
     * Creates the new <code>ContextRadialMenu</code> with default parameters.
     *
     * @param parent Parent Pane
     * @param menu   Menu structure
     */
    public ContextRadialMenu(Pane parent, Menu menu) {
        this(parent, menu, null);
    }


    /**
     * Creates the new <code>ContextRadialMenu</code> with predefined customization parameters.
     *
     * @param parent Parent Pane
     * @param menu   Menu structure
     * @param params Customization parameters
     */
    public ContextRadialMenu(Pane parent, Menu menu, RadialMenuParams params) {
        requireNonNull(parent, "Parameter 'parent' is null");
        requireNonNull(menu, "Parameter 'menu' is null");

        this.parent = parent;
        this.menu = menu;
        this.params = (params != null) ? params : new RadialMenuParams();

        setupPointer();
        setupPopup();
    }


    /**
     * Returns the menu model.
     *
     * @return The menu model.
     */
    public Menu getMenu() {
        return menu;
    }


    /**
     * Returns the customization parameters of the menu.
     *
     * @return Customization parameters of the <code>ContextRadialMenu</code>.
     */
    public RadialMenuParams getRadialMenuParams() {
        return params;
    }


    /**
     * Returns the <code>EventHandler</code> to execute, when Menu is about to hide.
     *
     * @return Preset <code>EventHandler</code> to execute, when Menu is about to hide.
     */
    public EventHandler<ActionEvent> getOnHide() {
        return onHide.get();
    }


    /**
     * @see #getOnHide()
     */
    public ObjectProperty<EventHandler<ActionEvent>> onHideProperty() {
        return onHide;
    }


    /**
     * Sets a custom action to menu "on hide" event.
     *
     * @param onHide <code>EventHandler</code> to execute, when Menu is about to hide.
     */
    public void setOnHide(EventHandler<ActionEvent> onHide) {
        this.onHide.set(onHide);
    }


    /**
     * Shows the <code>ContextRadialMenu</code> at specified coordinates.
     *
     * @param target Target node. This will be sent as parameter to all triggered actions.
     * @param x      X-coord
     * @param y      Y-coord
     */
    public void showAt(Node target, double x, double y) {
        requireNonNull(target, "Parameter 'target' is null");

        internalShow(pointer, target, x, y);
        pointer.setVisible(true);
    }


    /**
     * Shows the <code>ContextRadialMenu</code> over specified <code>Node</code>.
     *
     * @param target Target node. This will be sent as parameter to all triggered actions.
     */
    public void showOver(Node target) {
        requireNonNull(target, "Parameter 'target' is null");

        final Point2D center = center(target.localToScreen(target.getBoundsInLocal()));
        internalShow(target, target, center.getX(), center.getY());
    }


    /**
     * Hides the menu.
     */
    public void hide() {
        popup.hide();
    }


    private void internalShow(Node owner, Node target, double x, double y) {

        setupRadialPane(target, x, y);

        RadialMenuSection.add(this, radialPane, menu, null, null);

        radialPane.autosize();

        if (!parent.getChildren().contains(pointer)) {
            parent.getChildren().add(pointer);
        }

        popup.show(owner, x - popup.getWidth() * 0.5, y - popup.getHeight() * 0.5);

        centerPointer(x, y);
    }


    private void setupRadialPane(Node target, double x, double y) {
        radialPane.setEventSource(new RadialMenuEventSource(parent.screenToLocal(x, y), target));

        if (params.getStyleSheet() != null) {
            radialPane.getStylesheets().add(params.getStyleSheet());
        }
    }


    private void setupPopup() {
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        popup.setAutoFix(false);

        popup.setOnShown(event -> radialPane.getSections().get(menu).show());

        popup.setOnHiding(event -> {
            radialPane.getSections().get(menu).hide();
            parent.getChildren().remove(pointer);
            if (onHide.get() != null) {
                onHide.get().handle(null);
            }
        });

        popup.getContent().add(radialPane);
    }


    private void setupPointer() {
        pointer.setRadius(3.0);
        pointer.setFill(Color.TRANSPARENT);
        pointer.setStroke(Color.BLACK);
        pointer.setVisible(false);
    }


    private void centerPointer(double x, double y) {
        final Point2D parentCoords = popup.getOwnerNode().screenToLocal(new Point2D(x, y));

        pointer.setCenterX(parentCoords.getX());
        pointer.setCenterY(parentCoords.getY());
    }


    public static Point2D center(Bounds bounds) {
        return new Point2D(bounds.getMinX() + bounds.getWidth() / 2, bounds.getMinY() + bounds.getHeight() / 2);
    }
}
