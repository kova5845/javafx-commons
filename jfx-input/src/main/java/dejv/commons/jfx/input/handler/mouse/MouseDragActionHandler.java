package dejv.commons.jfx.input.handler.mouse;

import static java.util.Objects.requireNonNull;

import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.handler.DragActionHandler;
import dejv.commons.jfx.input.properties.mouse.MouseGestureEventProperties;

/**
 * Handler for drag actions performed using mouse.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseDragActionHandler
        extends DragActionHandler {

    private final MouseGestureEventProperties properties;


    protected MouseDragActionHandler(MouseGestureEventProperties properties) {
        requireNonNull(properties, "Parameter 'properties' is null");

        this.properties = properties;
    }


    /**
     * Create the handler from given properties.
     *
     * @param properties Mouse gesture properties. Must be given.
     * @return New instance of MouseDragActionHandler, based on given properties.
     * @throws java.lang.NullPointerException when properties parameter is null.
     */
    public static MouseDragActionHandler from(MouseGestureEventProperties properties) {

        return new MouseDragActionHandler(properties);
    }


    @Override
    protected boolean isApplicable(MouseEvent event) {
        return properties.isMatching(event);
    }
}
