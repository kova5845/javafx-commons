package dejv.commons.jfx.input.handler;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public abstract class InputActionHandler {

    public abstract void register(Node node);

    public abstract void unregister(Node node);

}
