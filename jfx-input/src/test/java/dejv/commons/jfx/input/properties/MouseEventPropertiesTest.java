package dejv.commons.jfx.input.properties;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.junit.Test;

import dejv.commons.jfx.input.MouseButtons;
import dejv.commons.jfx.input.MouseModifiers;

/**
 * Test for GestureEventProperties.
 * <p>
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseEventPropertiesTest {

    @Test
    public void callIsMatchingNone() {
        final MouseEventProperties cut = new MouseEventProperties(null, null);

        final MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, false, true, false, true, false, false, false, false, true, null);

        assertTrue("Properties doesn't match the matching event", cut.isMatching(event));
    }

    @Test
    public void callIsMatchingWithMatchingEvent() {
        final MouseEventProperties cut = new MouseEventProperties(new MouseModifiers().withAlt().withShift(), new MouseButtons().withPrimary());

        final MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, false, true, false, true, false, false, false, false, true, null);

        assertTrue("Properties doesn't match the matching event", cut.isMatching(event));
    }

    @Test
    public void callIsMatchingWithNonMatchingEvent() {
        final MouseEventProperties cut = new MouseEventProperties(new MouseModifiers().withAlt().withShift(), new MouseButtons().withPrimary());

        final MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, false, true, false, false, false, false, true, null);

        assertFalse("Properties match the unmatching event", cut.isMatching(event));
    }


    @Test
    public void callIsMatchingWithNullEvent() {
        final MouseEventProperties cut = new MouseEventProperties(new MouseModifiers().withAlt().withShift(), new MouseButtons().withPrimary());

        assertFalse("Properties match the null event", cut.isMatching(null));
    }
}
