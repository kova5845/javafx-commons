package dejv.commons.jfx.input.properties;

import javafx.scene.input.GestureEvent;

import dejv.commons.jfx.input.GestureModifiers;

/**
 * Holder class for desired properties of GestureEvent.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class GestureEventProperties {

    private final GestureModifiers gestureModifiers;


    /**
     * Construct new instance of GestureEventProperties with given Modifiers.
     *
     * @param gestureModifiers Valid Modifiers, or null for none required.
     */
    public GestureEventProperties(GestureModifiers gestureModifiers) {
        if (gestureModifiers != null) {
            this.gestureModifiers = gestureModifiers;
        } else {
            this.gestureModifiers = GestureModifiers.none();
        }
    }


    /**
     * Requests the current Modifiers setting.
     *
     * @return The current set of Modifiers for this GestureEvent.
     */
    public GestureModifiers getModifiers() {
        return gestureModifiers;
    }


    /**
     * Check, whether the given GestureEvent is matching the current properties.
     *
     * @param event GestureEvent to test.
     * @return True, if no modifiers are set, or the event matches exactly the properties.
     * False, if some modifiers are set, and the event doesn't match them, or if the event is null.
     */
    public boolean isMatching(GestureEvent event) {
        return (event != null) && !event.isConsumed()
                && (gestureModifiers.isNone()
                || ((event.isAltDown() == gestureModifiers.isAlt())
                && (event.isShiftDown() == gestureModifiers.isShift())
                && (event.isControlDown() == gestureModifiers.isControl())
                && (event.isMetaDown() == gestureModifiers.isMeta())
                && (event.isShortcutDown() == gestureModifiers.isShortcut())));
    }

}
