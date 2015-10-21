package dejv.jfx.controls.radialmenu.skin;

import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import dejv.jfx.controls.radialmenu.RadialMenuPopup;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuPopupSkin implements Skin<RadialMenuPopup> {

    /**
     * Constructor for all SkinBase instances.
     *
     * @param popup The popup for which this Skin should attach to.
     */
    private final RadialMenuPopup popup;

    private final Pane root;

    public RadialMenuPopupSkin(RadialMenuPopup control) {
        this.popup = control;
        root = new Pane();

        Circle perimeter = new Circle();
        perimeter.setRadius(100);
        perimeter.setFill(Color.TRANSPARENT);
        perimeter.setStroke(Color.BLUEVIOLET);

        Circle center = new Circle();
        center.setRadius(1);
        center.setFill(Color.RED);
        center.setStroke(Color.BLUEVIOLET);
        root.getChildren().addAll(center, perimeter);
    }


    @Override
    public RadialMenuPopup getSkinnable() {
        return popup;
    }


    @Override
    public Node getNode() {
        return root;
    }


    @Override
    public void dispose() {

    }
}
