package dejv.commons.jfx.input.handler.mouse;

import static java.util.Objects.requireNonNull;

import javafx.scene.input.ScrollEvent;

import dejv.commons.jfx.input.handler.ScrollActionHandler;
import dejv.commons.jfx.input.properties.GestureEventProperties;

/**
 * Handler for scroll actions performed using mouse.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseScrollActionHandler
        extends ScrollActionHandler {

    private final GestureEventProperties properties;


    protected MouseScrollActionHandler(GestureEventProperties properties) {
        requireNonNull(properties, "Parameter 'properties' is null");

        this.properties = properties;
    }


    /**
     * Create the handler from given properties.
     *
     * @param properties Mouse gesture properties. Must be given.
     * @return New instance of MouseScrollActionHandler, based on given properties.
     * @throws java.lang.NullPointerException when properties parameter is null.
     */
    public static MouseScrollActionHandler from(GestureEventProperties properties) {
        return new MouseScrollActionHandler(properties);
    }


    @Override
    protected boolean isApplicable(ScrollEvent event) {
        return properties.isMatching(event);
    }

}
