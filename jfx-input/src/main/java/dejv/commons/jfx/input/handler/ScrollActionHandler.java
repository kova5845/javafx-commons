package dejv.commons.jfx.input.handler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

/**
 * Common handler class for scrolling events.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class ScrollActionHandler
        extends InputActionHandler {


    private EventHandler<ScrollEvent> scrollHandler = this::handleScroll;

    protected EventHandler<ScrollEvent> onScroll;


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
        return true;
    }


    private void handleScroll(ScrollEvent event) {
        if ((onScroll != null) && (isApplicable(event))) {
            onScroll.handle(event);
        }
    }

}
