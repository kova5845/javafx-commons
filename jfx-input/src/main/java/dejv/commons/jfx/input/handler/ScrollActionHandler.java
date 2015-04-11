package dejv.commons.jfx.input.handler;

import static java.util.Objects.requireNonNull;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

import dejv.commons.jfx.input.properties.GestureEventProperties;

/**
 * Common handler class for scrolling events.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class ScrollActionHandler
        extends InputActionHandler {


    private final GestureEventProperties properties;

    private final EventHandler<ScrollEvent> scrollHandler = this::handleScroll;

    private EventHandler<ScrollEvent> onScroll;



    protected ScrollActionHandler(GestureEventProperties properties) {
        requireNonNull(properties, "Parameter 'properties' is null");

        this.properties = properties;
    }



    /**
     * Create the handler with given properties.
     *
     * @param properties Mouse gesture properties. Must be given.
     * @return New instance of MouseScrollActionHandler, based on given properties.
     * @throws java.lang.NullPointerException when properties parameter is null.
     */
    public static ScrollActionHandler with(GestureEventProperties properties) {
        return new ScrollActionHandler(properties);
    }


    /**
     * Sets the handler, that should be called, when scrolling action occurs.
     *
     * @param onScroll Valid Scroll Event handler, or null for no "On scroll" action.
     * @return This instance with given "On scroll" handler included.
     */
    public ScrollActionHandler doOnScroll(EventHandler<ScrollEvent> onScroll) {
        this.onScroll = onScroll;
        return this;
    }


    @Override
    public void register(Node node) {
        node.addEventFilter(ScrollEvent.SCROLL, scrollHandler);
    }


    @Override
    public void unregister(Node node) {
        node.removeEventFilter(ScrollEvent.SCROLL, scrollHandler);
    }


    protected boolean isApplicable(ScrollEvent event) {
        return properties.isMatching(event);
    }


    private void handleScroll(ScrollEvent event) {
        if ((onScroll != null) && (isApplicable(event))) {
            onScroll.handle(event);
        }
    }

}
