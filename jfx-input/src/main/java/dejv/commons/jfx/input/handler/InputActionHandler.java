package dejv.commons.jfx.input.handler;

import javafx.scene.Node;

/**
 * Common ancestor for user input event handlers.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public abstract class InputActionHandler {

    /**
     * Registers the handler on the given node.
     *
     * @param node Node to register the handler on.
     * @throws java.lang.NullPointerException when the node parameter is null.
     */
    public abstract void register(Node node);

    /**
     * Unregisters the handler from the node.
     *
     * @param node Node to unregister the handler from.
     * @throws java.lang.NullPointerException when the node parameter is null.
     */
    public abstract void unregister(Node node);

}
