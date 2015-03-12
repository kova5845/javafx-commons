package dejv.commons.jfx.input.handler;

import dejv.commons.jfx.input.MouseActionProperties;
import dejv.commons.jfx.input.handler.DragActionHandler;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseDragActionHandler
        extends DragActionHandler {

    private final MouseActionProperties properties;


    public MouseDragActionHandler(MouseActionProperties properties) {
        this.properties = properties;
    }

}
