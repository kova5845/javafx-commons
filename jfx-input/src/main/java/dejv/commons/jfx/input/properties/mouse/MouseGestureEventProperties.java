package dejv.commons.jfx.input.properties.mouse;

import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.Buttons;
import dejv.commons.jfx.input.Modifiers;
import dejv.commons.jfx.input.properties.GestureEventProperties;

/**
 * Holder class for desired properties of Mouse Event.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseGestureEventProperties
        extends GestureEventProperties {

    private final Buttons buttons;


    /**
     * Construct new instance of MouseGestureEventProperties with given Modifiers and Buttons.
     *
     * @param modifiers Valid Modifiers, or null for none required.
     * @param buttons Valid Buttons, or null for none required.
     */
    public MouseGestureEventProperties(Modifiers modifiers, Buttons buttons) {
        super(modifiers);

        if (buttons != null) {
            this.buttons = buttons;
        } else {
            this.buttons = Buttons.none();
        }
    }


    /**
     * Requests the current Buttons setting.
     *
     * @return The current set of Buttons for this Mouse Event.
     */
    public Buttons getButtons() {
        return buttons;
    }


    /**
     * Check, whether the given MouseEvent is matching the current properties.
     *
     * @param event MouseEvent to test.
     * @return True, if the event matches these properties, false if not (or if the event parameter is null).
     */
    public boolean isMatching(MouseEvent event) {
        return (event != null) && !event.isConsumed()
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
