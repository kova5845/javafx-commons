package dejv.commons.jfx.input.properties;

import javafx.scene.input.GestureEvent;

import dejv.commons.jfx.input.Modifiers;

/**
 * Holder class for desired properties of GestureEvent.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class GestureEventProperties {

    private final Modifiers modifiers;


    /**
     * Construct new instance of GestureEventProperties with given Modifiers.
     *
     * @param modifiers Valid Modifiers, or null for none required.
     */
    public GestureEventProperties(Modifiers modifiers) {
        if (modifiers != null) {
            this.modifiers = modifiers;
        } else {
            this.modifiers = Modifiers.empty();
        }
    }


    /**
     * Requests the current Modifiers setting.
     *
     * @return The current set of Modifiers for this GestureEvent.
     */
    public Modifiers getModifiers() {
        return modifiers;
    }


    /**
     * Check, whether the given GestureEvent is matching the current properties.
     *
     * @param event GestureEvent to test.
     * @return True, if the event matches these properties, false if not (or if the event parameter is null).
     */
    public boolean isMatching(GestureEvent event) {
        return (event != null) && !event.isConsumed()
                && (event.isAltDown() == getModifiers().isAlt())
                && (event.isShiftDown() == getModifiers().isShift())
                && (event.isControlDown() == getModifiers().isControl())
                && (event.isMetaDown() == getModifiers().isMeta())
                && (event.isShortcutDown() == getModifiers().isShortcut());
    }

}
