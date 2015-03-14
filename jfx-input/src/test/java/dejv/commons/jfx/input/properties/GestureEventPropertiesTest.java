package dejv.commons.jfx.input.properties;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import javafx.scene.input.GestureEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ScrollEvent.HorizontalTextScrollUnits;
import javafx.scene.input.ScrollEvent.VerticalTextScrollUnits;

import org.junit.Test;

import dejv.commons.jfx.input.GestureModifiers;

/**
 * Test for GestureEventProperties.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class GestureEventPropertiesTest {

    @Test
    public void callIsMatchingNone() {
        final GestureEventProperties cut = new GestureEventProperties(null);

        final GestureEvent event = new ScrollEvent(ScrollEvent.SCROLL, 0, 0, 0, 0, true, false, true, false, true, false, 0, 1, 0, 5, 1, 1,
                HorizontalTextScrollUnits.NONE, 0, VerticalTextScrollUnits.LINES, 1, 0, null);

        assertTrue("Properties doesn't match the matching event", cut.isMatching(event));
    }


    @Test
    public void callIsMatchingWithMatchingEvent() {
        final GestureEventProperties cut = new GestureEventProperties(new GestureModifiers().withAlt().withShift());

        final GestureEvent event = new ScrollEvent(ScrollEvent.SCROLL, 0, 0, 0, 0, true, false, true, false, true, false, 0, 1, 0, 5, 1, 1,
                HorizontalTextScrollUnits.NONE, 0, VerticalTextScrollUnits.LINES, 1, 0, null);

        assertTrue("Properties doesn't match the matching event", cut.isMatching(event));
    }


    @Test
    public void callIsMatchingWithNonMatchingEvent() {
        final GestureEventProperties cut = new GestureEventProperties(new GestureModifiers().withAlt().withShift());

        final GestureEvent event = new ScrollEvent(ScrollEvent.SCROLL, 0, 0, 0, 0, true, true, true, false, true, false, 0, 1, 0, 5, 1, 1,
                HorizontalTextScrollUnits.NONE, 0, VerticalTextScrollUnits.LINES, 1, 0, null);

        assertFalse("Properties match the unmatching event", cut.isMatching(event));
    }


    @Test
    public void callIsMatchingWithNullEvent() {
        final GestureEventProperties cut = new GestureEventProperties(new GestureModifiers().withAlt().withShift());

        assertFalse("Properties match the null event", cut.isMatching(null));
    }
}
