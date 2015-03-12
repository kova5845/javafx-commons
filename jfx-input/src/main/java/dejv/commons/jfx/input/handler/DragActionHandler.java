package dejv.commons.jfx.input.handler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.action.FixedCoordinateAction;


/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class DragActionHandler
        extends InputActionHandler {

    protected EventHandler<MouseEvent> dragEnteredHandler = this::handleDragEntered;
    protected EventHandler<MouseEvent> dragHandler = this::handleDrag;
    protected EventHandler<MouseEvent> dragFinishedHandler = this::handleDragFinished;
    protected FixedCoordinateAction onDragStart;
    protected FixedCoordinateAction onDrag;
    protected FixedCoordinateAction onDragFinish;


    public FixedCoordinateAction getOnDragStart() {
        return onDragStart;
    }


    public void setOnDragStart(FixedCoordinateAction onDragStart) {
        this.onDragStart = onDragStart;
    }


    public FixedCoordinateAction getOnDrag() {
        return onDrag;
    }


    public void setOnDrag(FixedCoordinateAction onDrag) {
        this.onDrag = onDrag;
    }


    public FixedCoordinateAction getOnDragFinish() {
        return onDragFinish;
    }


    public void setOnDragFinish(FixedCoordinateAction onDragFinish) {
        this.onDragFinish = onDragFinish;
    }


    @Override
    public void register(Node node) {
        node.addEventFilter(MouseEvent.DRAG_DETECTED, dragEnteredHandler);
        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragHandler);
        node.addEventFilter(MouseDragEvent.MOUSE_RELEASED, dragFinishedHandler);
    }


    @Override
    public void unregister(Node node) {
        node.removeEventFilter(MouseEvent.DRAG_DETECTED, dragEnteredHandler);
        node.removeEventFilter(MouseEvent.MOUSE_DRAGGED, dragHandler);
        node.removeEventFilter(MouseDragEvent.MOUSE_RELEASED, dragFinishedHandler);
    }


    private void handleDragEntered(MouseEvent event) {

    }


    private void handleDrag(MouseEvent event) {

    }


    private void handleDragFinished(MouseEvent event) {

    }
}
