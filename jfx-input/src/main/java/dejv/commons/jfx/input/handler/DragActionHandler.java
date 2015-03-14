package dejv.commons.jfx.input.handler;

import static java.util.Objects.requireNonNull;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.properties.MouseEventProperties;


/**
 * Common handler class for drag events.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class DragActionHandler
        extends InputActionHandler {

    private final MouseEventProperties properties;

    private final EventHandler<MouseEvent> dragHandler = this::handleDrag;
    private final EventHandler<MouseEvent> dragFinishedHandler = this::handleDragFinished;

    private EventHandler<MouseEvent> onDragStart;
    private EventHandler<MouseEvent> onDrag;
    private EventHandler<MouseEvent> onDragFinish;

    protected boolean dragging = false;

    protected DragActionHandler(MouseEventProperties properties) {
        requireNonNull(properties, "Parameter 'properties' is null");

        this.properties = properties;
    }


    /**
     * Create the handler from given properties.
     *
     * @param properties Mouse gesture properties. Must be given.
     * @return New instance of DragActionHandler, based on given properties.
     * @throws java.lang.NullPointerException when properties parameter is null.
     */
    public static DragActionHandler from(MouseEventProperties properties) {

        return new DragActionHandler(properties);
    }


    /**
     * Sets the handler, that should be called, when drag action is started.
     *
     * @param onDragStart Valid Mouse Event handler, or null for no "On drag start" action.
     * @return This instance with given "On drag start" handler included.
     */
    public DragActionHandler doOnDragStart(EventHandler<MouseEvent> onDragStart) {
        this.onDragStart = onDragStart;
        return this;
    }


    /**
     * Sets the handler, that should be called, when drag action is in progress.
     *
     * @param onDrag Valid Mouse Event handler, or null for no "On drag" action.
     * @return This instance with given "On drag" handler included.
     */
    public DragActionHandler doOnDrag(EventHandler<MouseEvent> onDrag) {
        this.onDrag = onDrag;
        return this;
    }


    /**
     * Sets the handler, that should be called, when drag action is finished.
     *
     * @param onDragFinish Valid Mouse Event handler, or null for no "On drag finish" action.
     * @return This instance with given "On drag finish" handler included.
     */
    public DragActionHandler doOnDragFinish(EventHandler<MouseEvent> onDragFinish) {
        this.onDragFinish = onDragFinish;
        return this;
    }


    @Override
    public void register(Node node) {
        requireNonNull(node, "Parameter 'node' is null");

        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragHandler);
        node.addEventFilter(MouseDragEvent.MOUSE_RELEASED, dragFinishedHandler);
    }


    @Override
    public void unregister(Node node) {
        requireNonNull(node, "Parameter 'node' is null");

        node.removeEventFilter(MouseEvent.MOUSE_DRAGGED, dragHandler);
        node.removeEventFilter(MouseDragEvent.MOUSE_RELEASED, dragFinishedHandler);
    }


    protected boolean isApplicable(MouseEvent event) {
        return properties.isMatching(event);
    }


    private void handleDrag(MouseEvent event) {
        if (!dragging) {
            performAction(onDragStart, event);
            dragging = true;
        } else {
            performAction(onDrag, event);
        }
    }


    private void handleDragFinished(MouseEvent event) {
        if (dragging) {
            performAction(onDragFinish, event);
            dragging = false;
        }
    }


    private void performAction(EventHandler<MouseEvent> handler, MouseEvent event) {
        if ((handler != null) && (isApplicable(event))) {
            handler.handle(event);
        }
    }

}
