package dejv.commons.jfx.input.properties.mouse;

import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.Buttons;
import dejv.commons.jfx.input.Modifiers;
import dejv.commons.jfx.input.properties.GestureEventProperties;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseGestureEventProperties
        extends GestureEventProperties {

    private final Buttons buttons;


    public MouseGestureEventProperties(Modifiers modifiers, Buttons buttons) {
        super(modifiers);

        if (buttons != null) {
            this.buttons = buttons;
        } else {
            this.buttons = Buttons.empty();
        }
    }


    public Buttons getButtons() {
        return buttons;
    }


    public boolean isMatching(MouseEvent event) {
        return !event.isConsumed()
                && (event.isPrimaryButtonDown() == getButtons().isPrimary())
                && (event.isMiddleButtonDown() == getButtons().isMiddle())
                && (event.isSecondaryButtonDown() == getButtons().isSecondary())
                && (event.isAltDown() == getModifiers().isAlt())
                && (event.isShiftDown() == getModifiers().isShift())
                && (event.isControlDown() == getModifiers().isControl())
                && (event.isMetaDown() == getModifiers().isMeta())
                && (event.isShortcutDown() == getModifiers().isShortcut());
    }

}
