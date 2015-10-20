package dejv.jfx.controls.radialmenu.skin;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import dejv.jfx.controls.radialmenu.RadialMenuButton;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuButtonSkin
    extends SkinBase<RadialMenuButton> {

    public RadialMenuButtonSkin(RadialMenuButton control) {
        super(control);

        final ToggleButton button = new ToggleButton();

        double size = control.getSize();
        button.setMinSize(size, size);
        button.setPrefSize(size, size);
        button.setMaxSize(size, size);
        button.setStyle("-fx-background-radius: " + control.getSize());
        button.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.CIRCLE_THIN, "0.2em"));
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        getChildren().add(button);
    }


    @Override
    public void dispose() {

    }
}
