package dejv.jfx.controls.radialmenu.internal;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Circle;

import dejv.commons.jfx.geometry.ObservablePoint2D;
import dejv.jfx.controls.radialmenu.ContextRadialMenu;
import dejv.jfx.controls.radialmenu.RadialMenuParams;
import dejv.jfx.controls.radialmenu.RadialMenuParams.Direction;

/**
 * One section of Radial Menu.
 * <p>
 * Each "Menu" is represented by one RadialMenuSection. Each "Menu Item" within this menu is represented by one RadialMenuItem inside that section.
 * <p>
 * Each RadialMenuSection is described by multiple properties:
 * <ul>
 * <li>Limit angles (arc start / end)
 * <li>Angular axis of the section
 * <li>Radius (Inner (inner rim of the section), Nominal (center radius of Menu Items), Outer (outer rim of the section, including parent item arrows))
 * </ul>
 *
 * @author dejv78 (http://dejv78.github.io)
 * @since 1.2.0
 */
@SuppressWarnings("unused")
public class RadialMenuSection {

    private static final double TWO_PI_DEG = 360;

    private final ContextRadialMenu owner;
    private final RadialMenuParams params;

    private final RadialPane radialPane;
    private final RadialMenuSection parentSection;

    private final double angularAxisDeg;
    private final double angularTotalSizeDeg;

    private final ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();

    private final double innerRadius;
    private final double nominalRadius;
    private final double outerRadius;


    /**
     * Create new RadialMenuSection
     *
     * @param owner          Radial Menu the new section should belong to
     * @param pane           Radial Pane on which to add the items
     * @param menu           Menu to represent
     * @param parentSection  Parent RadialMenuSection (can be null)
     * @param angularAxisDeg Explicit angular axis (can be null)
     * @return New RadialMenuSection
     */
    public static RadialMenuSection add(ContextRadialMenu owner, RadialPane pane, Menu menu, RadialMenuSection parentSection, Double angularAxisDeg) {

        final RadialMenuSection section = new RadialMenuSection(owner, pane, menu, parentSection, angularAxisDeg);

        pane.addSection(menu, section);

        return section;
    }


    private RadialMenuSection(ContextRadialMenu owner, RadialPane pane, Menu menu, RadialMenuSection parentSection, Double angularAxisDeg) {

        this.owner = owner;
        this.params = owner.getRadialMenuParams();
        this.radialPane = pane;
        this.parentSection = parentSection;

        final double itemRadiusHalf = params.getButtonSize() * 0.5;

        final double tempAngularAxisDeg = resolveAngularAxisDeg(params, angularAxisDeg);
        this.innerRadius = resolveInnerRadius(params, parentSection, itemRadiusHalf);

        final double angleDeg = params.getAngleToDeg() - params.getAngleFromDeg();
        final double angleRad = Math.toRadians(angleDeg);
        final double availablePerimeter = angleRad * (innerRadius + itemRadiusHalf);

        final int itemsCount = menu.getItems().size();
        final double gapSize = params.getButtonSize() * params.getGapFactor();
        final double allItemsSize = params.getButtonSize() * (itemsCount - 1);
        final double totalSize = allItemsSize + gapSize * (itemsCount - 1);

        final double candidateNominalRadius = (availablePerimeter > totalSize)
                ? innerRadius + itemRadiusHalf
                : totalSize / angleRad;

        final double candidateAngularTotalSizeDeg = Math.toDegrees(totalSize / candidateNominalRadius);
        final double candidateAngularItemSizeDeg = candidateAngularTotalSizeDeg / itemsCount;
        final double angularTotalSizeWithOverlapDeg = candidateAngularTotalSizeDeg + candidateAngularItemSizeDeg;

        nominalRadius = (angularTotalSizeWithOverlapDeg > TWO_PI_DEG)
                ? candidateNominalRadius + (totalSize / (angularTotalSizeWithOverlapDeg - TWO_PI_DEG))
                : candidateNominalRadius;

        angularTotalSizeDeg = Math.toDegrees(totalSize / nominalRadius);

        outerRadius = nominalRadius + itemRadiusHalf + params.getOuterPadding();

        double fromAngleDeg = tempAngularAxisDeg - angularTotalSizeDeg * 0.5;
        double toAngleDeg = fromAngleDeg + angularTotalSizeDeg;

        this.angularAxisDeg = (fromAngleDeg < params.getAngleFromDeg())
                ? tempAngularAxisDeg + params.getAngleFromDeg() - fromAngleDeg
                : (toAngleDeg > params.getAngleToDeg())
                ? tempAngularAxisDeg + params.getAngleToDeg() - toAngleDeg
                : tempAngularAxisDeg;

        final double angularItemSizeDeg = angularTotalSizeDeg / itemsCount;

        generateItems(owner, menu, itemsCount);

        radialPane.getChildren().add(setupPerimeter());
    }


    public void show() {
        items.forEach(RadialMenuItem::show);
    }


    public void hide() {
        items.forEach(RadialMenuItem::hide);
    }


    public double getInnerRadius() {
        return innerRadius;
    }


    public double getNominalRadius() {
        return nominalRadius;
    }


    public double getOuterRadius() {
        return outerRadius;
    }


    public double getAngularAxisDeg() {
        return angularAxisDeg;
    }


    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }


    private Double resolveAngularAxisDeg(RadialMenuParams params, Double candidateAngularAxisDeg) {
        if (candidateAngularAxisDeg != null) {
            return candidateAngularAxisDeg;
        } else {
            return (params.getAngleFromDeg() + (params.getAngleToDeg() - params.getAngleFromDeg()) * 0.5d);
        }
    }


    private double resolveInnerRadius(RadialMenuParams params, RadialMenuSection parentSection, double itemRadiusHalf) {
        if (parentSection != null) {
            return parentSection.getOuterRadius() + itemRadiusHalf * params.getSpacingFactor() * 2.0d;
        } else {
            return params.getMinRadius();
        }
    }


    private Circle setupPerimeter() {
        final ObservablePoint2D anchor = radialPane.getAnchorPoint();

        final Circle perimeter = new Circle();
        perimeter.centerXProperty().bind(anchor.xProperty());
        perimeter.centerYProperty().bind(anchor.yProperty());
        perimeter.setRadius(outerRadius);
        perimeter.setOpacity(0);
        perimeter.setMouseTransparent(true);
        return perimeter;
    }


    private void generateItems(ContextRadialMenu owner, Menu menu, int itemsCount) {
        for (int i = 0; i < menu.getItems().size(); i++) {
            final MenuItem item = menu.getItems().get(i);

            final double itemAngleDeg = calculateItemAngleDeg(itemsCount, i);

            final RadialMenuItem itemButton = addItem(item, itemAngleDeg);

            if (item instanceof Menu) {
                final Menu subMenu = (Menu) item;

                final RadialMenuSection subSection = add(owner, radialPane, subMenu, this, itemAngleDeg);

                createTrigger(itemButton, subMenu, subSection);
            } else {
                createAction(itemButton, item);
            }
        }
    }


    private double calculateItemAngleDeg(double itemsCount, double idx) {

        final double max = itemsCount - 1.0d;

        double itemRange = (idx / max) - 0.5d;

        if (params.getDirection() == Direction.CCW) {
            itemRange *= -1.0d;
        }

        return angularAxisDeg + (angularTotalSizeDeg * itemRange);
    }


    private RadialMenuItem addItem(MenuItem item, double itemAngleDeg) {

        final RadialMenuItemCoords itemCoords = new RadialMenuItemCoords(itemAngleDeg, (parentSection != null) ? parentSection.nominalRadius : 0);

        final RadialMenuItem itemButton = new RadialMenuItem(this, params, item, itemCoords);

        itemButton.layoutXProperty().bind(radialPane.getAnchorPoint().xProperty().subtract(params.buttonSizeProperty().multiply(0.5)));
        itemButton.layoutYProperty().bind(radialPane.getAnchorPoint().yProperty().subtract(params.buttonSizeProperty().multiply(0.5)));

        items.add(itemButton);
        radialPane.getChildren().add(itemButton);

        return itemButton;
    }


    private void createAction(RadialMenuItem itemButton, MenuItem item) {
        if (item.getOnAction() != null) {
            itemButton.setOnAction(event -> {
                item.getOnAction().handle(new ActionEvent(radialPane.getEventSource(), event.getTarget()));
                owner.hide();
            });
        }
    }


    private void createTrigger(RadialMenuItem itemButton, Menu menu, RadialMenuSection subSection) {
        itemButton.setOnTrigger(event -> {

            boolean alreadyPresent = checkPresenceAndCloseExtraSections(this, subSection);

            if (!alreadyPresent) {

                radialPane.getActiveSubSections().add(subSection);
                subSection.show();
            }
        });
    }


    private boolean checkPresenceAndCloseExtraSections(RadialMenuSection currentSection, RadialMenuSection sectionToOpen) {
        final List<RadialMenuSection> activeSubSections = radialPane.getActiveSubSections();

        if (radialPane.getActiveSubSections().size() > 0) {
            int idx = activeSubSections.indexOf(sectionToOpen);

            if (idx >= 0) { // Do not remove subsection, keep current sub-tree
                return true;
            } else {
                idx = activeSubSections.indexOf(currentSection);
            }

            for (int i = activeSubSections.size() - 1; i > idx; i--) {
                final RadialMenuSection sectionToClose = activeSubSections.get(i);
                sectionToClose.hide();
                activeSubSections.remove(sectionToClose);
            }
        }
        return false;
    }
}
