package dejv.commons.jfx.input.handler.mouse;

import javafx.scene.input.ScrollEvent;

import dejv.commons.jfx.input.handler.ScrollActionHandler;
import dejv.commons.jfx.input.properties.GestureEventProperties;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class MouseScrollActionHandler
        extends ScrollActionHandler {

    private final GestureEventProperties properties;


    public MouseScrollActionHandler(GestureEventProperties properties) {
        this.properties = properties;
    }


    public static MouseScrollActionHandler from(GestureEventProperties properties) {
        return new MouseScrollActionHandler(properties);
    }

    @Override
    protected boolean isApplicable(ScrollEvent event) {
        return properties.isMatching(event);
    }

}
