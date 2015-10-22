package dejv.jfx.controls.radialmenu.structure;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuItem {

    @FunctionalInterface
    public interface RadialMenuItemActionHandler {
        void handle();
    }

    private ObjectProperty<Node> graphic;
    private StringProperty tooltip;
    private ObjectProperty<RadialMenuItemActionHandler> onAction;


    public RadialMenuItem() {
        this(null, null, null);
    }


    public RadialMenuItem(Node graphic, String tooltip) {
        this(graphic, tooltip, null);
    }


    public RadialMenuItem(Node graphic, String tooltip, RadialMenuItemActionHandler onAction) {
        setGraphic(graphic);
        setTooltip(tooltip);
        setOnAction(onAction);
    }


    public Node getGraphic() {
        return graphic.get();
    }


    public ObjectProperty<Node> graphicProperty() {
        return graphic;
    }


    public void setGraphic(Node graphic) {
        this.graphic.set(graphic);
    }


    public String getTooltip() {
        return tooltip.get();
    }


    public StringProperty tooltipProperty() {
        return tooltip;
    }


    public void setTooltip(String tooltip) {
        this.tooltip.set(tooltip);
    }


    public RadialMenuItemActionHandler getOnAction() {
        return onAction.get();
    }


    public ObjectProperty<RadialMenuItemActionHandler> onActionProperty() {
        return onAction;
    }


    public void setOnAction(RadialMenuItemActionHandler onAction) {
        this.onAction.set(onAction);
    }
}
