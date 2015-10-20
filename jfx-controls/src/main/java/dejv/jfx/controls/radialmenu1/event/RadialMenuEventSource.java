package dejv.jfx.controls.radialmenu1.event;

import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import dejv.jfx.controls.radialmenu1.ContextRadialMenu;

/**
 * An object, that is being sent as {@link ActionEvent#getSource()} into actions, triggered by Radial Menu items.
 * <p>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.2.0
 */
public class RadialMenuEventSource {

    private final Point2D menuTriggerCoords;
    private final Node targetNode;


    public RadialMenuEventSource(Point2D menuTriggerCoords, Node targetNode) {
        this.menuTriggerCoords = menuTriggerCoords;
        this.targetNode = targetNode;
    }


    /**
     * @return Location, at which the RadialMenu was triggered to display.
     * @see ContextRadialMenu#showAt(Node, double, double)
     */
    public Point2D getMenuTriggerCoords() {
        return menuTriggerCoords;
    }


    /**
     * @return Action target node.
     * @see ContextRadialMenu#showOver(Node)
     * @see ContextRadialMenu#showAt(Node, double, double)
     */
    public Node getTargetNode() {
        return targetNode;
    }
}
