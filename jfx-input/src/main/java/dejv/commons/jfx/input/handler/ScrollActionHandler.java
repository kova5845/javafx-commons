package dejv.commons.jfx.input.handler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class ScrollActionHandler
        extends InputActionHandler {


    private EventHandler<ScrollEvent> scrollHandler = this::handleScroll;

    protected EventHandler<ScrollEvent> onScroll;


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
