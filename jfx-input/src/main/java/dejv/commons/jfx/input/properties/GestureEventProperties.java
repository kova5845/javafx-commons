package dejv.commons.jfx.input.properties;

import javafx.scene.input.GestureEvent;

import dejv.commons.jfx.input.Modifiers;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class GestureEventProperties {

    private final Modifiers modifiers;


    public GestureEventProperties(Modifiers modifiers) {
        if (modifiers != null) {
            this.modifiers = modifiers;
        } else {
            this.modifiers = Modifiers.empty();
        }
    }


    public Modifiers getModifiers() {
        return modifiers;
    }


    public boolean isMatching(GestureEvent event) {
        return !event.isConsumed()
                && (event.isAltDown() == getModifiers().isAlt())
                && (event.isShiftDown() == getModifiers().isShift())
                && (event.isControlDown() == getModifiers().isControl())
                && (event.isMetaDown() == getModifiers().isMeta())
                && (event.isShortcutDown() == getModifiers().isShortcut());
    }

}
