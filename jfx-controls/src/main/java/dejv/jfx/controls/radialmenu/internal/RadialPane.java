package dejv.jfx.controls.radialmenu.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import dejv.commons.jfx.geometry.ObservablePoint2D;
import dejv.jfx.controls.radialmenu.event.RadialMenuEventSource;

/**
 * Pane to hold the Radial Menu sections.
 * <p>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.0.0
 */
public class RadialPane
        extends Pane {

    private final Map<MenuItem, RadialMenuSection> sections = new HashMap<>();
    private final List<RadialMenuSection> activeSubSections = new ArrayList<>();

    private DoubleProperty centerOffset = new SimpleDoubleProperty(0);
    private ObservablePoint2D anchorPoint = new ObservablePoint2D(centerOffset, centerOffset);
    private RadialMenuEventSource eventSource;


    public void addSection(Menu menu, RadialMenuSection section) {
        sections.put(menu, section);

        updateCenterOffset(section.getOuterRadius());
    }


    public Map<MenuItem, RadialMenuSection> getSections() {
        return sections;
    }


    public List<RadialMenuSection> getActiveSubSections() {
        return activeSubSections;
    }


    public void updateCenterOffset(double offset) {
        if (centerOffset.get() < offset) {
            centerOffset.set(offset);
        }
    }


    public ObservablePoint2D getAnchorPoint() {
        return anchorPoint;
    }


    public RadialMenuEventSource getEventSource() {
        return eventSource;
    }


    public void setEventSource(RadialMenuEventSource eventSource) {
        this.eventSource = eventSource;
    }
}
