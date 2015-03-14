package dejv.commons.jfx.input.properties;

import javafx.scene.input.MouseEvent;

import dejv.commons.jfx.input.MouseButtons;
import dejv.commons.jfx.input.MouseModifiers;

/**
 * Holder class for desired properties of Mouse Event.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseEventProperties {

    private final MouseModifiers mouseModifiers;
    private final MouseButtons mouseButtons;


    /**
     * Construct new instance of MouseEventProperties with given Modifiers and Buttons.
     *
     * @param mouseModifiers Valid Modifiers, or null for none required.
     * @param mouseButtons   Valid Buttons, or null for none required.
     */
    public MouseEventProperties(MouseModifiers mouseModifiers, MouseButtons mouseButtons) {

        if (mouseModifiers != null) {
            this.mouseModifiers = mouseModifiers;
        } else {
            this.mouseModifiers = MouseModifiers.none();
        }

        if (mouseButtons != null) {
            this.mouseButtons = mouseButtons;
        } else {
            this.mouseButtons = MouseButtons.none();
        }
    }


    /**
     * Requests the current Modifiers setting.
     *
     * @return The current set of Modifiers for this MouseEvent.
     */
    public MouseModifiers getModifiers() {
        return mouseModifiers;
    }


    /**
     * Requests the current Buttons setting.
     *
     * @return The current set of Buttons for this MouseEvent.
     */
    public MouseButtons getButtons() {
        return mouseButtons;
    }


    /**
     * Check, whether the given MouseEvent is matching the current properties.
     *
     * @param event MouseEvent to test.
     * @return True, if no modifiers are set, or the event matches exactly the properties.
     * False, if some modifiers are set, and the event doesn't match them, or if the event is null, or already consumed.
     */
    public boolean isMatching(MouseEvent event) {
        return (event != null) && !event.isConsumed()
                && (mouseModifiers.isNone()
                || ((event.isPrimaryButtonDown() == mouseButtons.isPrimary())
                && (event.isMiddleButtonDown() == mouseButtons.isMiddle())
                && (event.isSecondaryButtonDown() == mouseButtons.isSecondary())
                && (event.isAltDown() == mouseModifiers.isAlt())
                && (event.isShiftDown() == mouseModifiers.isShift())
                && (event.isControlDown() == mouseModifiers.isControl())
                && (event.isMetaDown() == mouseModifiers.isMeta())));
    }

}
