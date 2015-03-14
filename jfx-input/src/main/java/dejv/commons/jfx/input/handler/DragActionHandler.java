package dejv.commons.jfx.input.handler;

import static java.util.Objects.requireNonNull;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;


/**
 * Common handler class for drag events.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class DragActionHandler
        extends InputActionHandler {

    protected EventHandler<MouseEvent> dragHandler = this::handleDrag;
    protected EventHandler<MouseEvent> dragFinishedHandler = this::handleDragFinished;

    protected EventHandler<MouseEvent> onDragStart;
    protected EventHandler<MouseEvent> onDrag;
    protected EventHandler<MouseEvent> onDragFinish;

    protected boolean dragging = false;


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
        return true;
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
