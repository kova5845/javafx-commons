package dejv.commons.jfx.input.handler.mouse;

import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.handler.DragActionHandler;
import dejv.commons.jfx.input.properties.mouse.MouseGestureEventProperties;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseDragActionHandler
        extends DragActionHandler {

    private final MouseGestureEventProperties properties;

    public static MouseDragActionHandler from(MouseGestureEventProperties properties) {
        return new MouseDragActionHandler(properties);
    }

    public MouseDragActionHandler(MouseGestureEventProperties properties) {
        this.properties = properties;
    }


    @Override
    protected boolean isApplicable(MouseEvent event) {
        return properties.isMatching(event);
    }
}
