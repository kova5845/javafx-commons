package dejv.jfx.controls.radialmenu.skin.internal;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import dejv.jfx.controls.radialmenu.RadialMenu;
import dejv.jfx.controls.radialmenu.RadialMenuItem;
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
    private static final List<RadialMenuSection> activeSubSections = new ArrayList<>();

    private final RadialMenuParams params;
    private final Pane pane;
    private final RadialMenuSection parentSection;
    private final ObservableList<RadialMenuItem> items;

    private final double angularAxisDeg;
    private final double angularTotalSizeDeg;

    private final double innerRadius;
    private final double nominalRadius;
    private final double outerRadius;


    public RadialMenuSection(RadialMenuParams params, Pane pane, ObservableList<RadialMenuItem> items, RadialMenuSection parentSection, Double angularAxisDeg) {

        this.params = params;
        this.pane = pane;
        this.parentSection = parentSection;
        this.items = items;

        final double itemRadiusHalf = params.getButtonSize() * 0.5;

        final double tempAngularAxisDeg = resolveAngularAxisDeg(params, angularAxisDeg);
        this.innerRadius = resolveInnerRadius(params, parentSection, itemRadiusHalf);

        final double angleDeg = params.getAngleToDeg() - params.getAngleFromDeg();
        final double angleRad = Math.toRadians(angleDeg);
        final double availablePerimeter = angleRad * (innerRadius + itemRadiusHalf);

        final int itemsCount = items.size();
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

        generateItems();
    }


    public void show() {
        items.forEach((item)->item.setVisible(true));
    }


    public void hide() {
        items.forEach((item)->item.setVisible(false));
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


    private void generateItems() {
        double itemsCount = items.size();
        for (int i = 0; i < itemsCount; i++) {
            final RadialMenuItem item = items.get(i);

            final double itemAngleDeg = calculateItemAngleDeg(itemsCount, i);

            setupItem(item, itemAngleDeg);

            if (item instanceof RadialMenu) {
                final RadialMenu subMenu = (RadialMenu) item;

                final RadialMenuSection subSection = new RadialMenuSection(params, pane, subMenu.getItems(), this, itemAngleDeg);

//                createAction(itemButton, item);
//                createTrigger(itemButton, subMenu, subSection);
//            } else {
//                createAction(itemButton, item);
            }
        }
    }


    private double calculateItemAngleDeg(double itemsCount, double idx) {

        final double max = itemsCount - 1.0d;

        double itemRange = (max > 0) ?(idx / max) - 0.5d : 0.5d;

        if (params.getDirection() == Direction.CCW) {
            itemRange *= -1.0d;
        }

        return angularAxisDeg + (angularTotalSizeDeg * itemRange);
    }


    private void setupItem(RadialMenuItem item, double itemAngleDeg) {
        item.setVisible(false);
        item.setSection(this);
        item.setAngle(itemAngleDeg);
        item.setFromRadius((parentSection != null) ? parentSection.nominalRadius : 0);

        item.layoutXProperty().bind(params.buttonSizeProperty().multiply(-0.5));
        item.layoutYProperty().bind(params.buttonSizeProperty().multiply(-0.5));
        item.sizeProperty().bind(params.buttonSizeProperty());

        pane.getChildren().add(item);
    }


//    private void createAction(RadialMenuItem itemButton, MenuItem item) {
//        if (item.getOnAction() != null) {
//            itemButton.setOnAction(event -> {
//                item.getOnAction().handle(new ActionEvent(radialPane.getEventSource(), event.getTarget()));
//                popup.hide();
//            });
//        }
//    }
//
//
//    private void createTrigger(RadialMenuItem itemButton, Menu menu, RadialMenuSection subSection) {
//        itemButton.setOnTrigger(event -> {
//
//            boolean alreadyPresent = checkPresenceAndCloseExtraSections(this, subSection);
//
//            if (!alreadyPresent) {
//
//                radialPane.getActiveSubSections().add(subSection);
//                subSection.show();
//            }
//        });
//    }


    private static boolean checkPresenceAndCloseExtraSections(RadialMenuSection currentSection, RadialMenuSection sectionToOpen) {
        if (activeSubSections.size() > 0) {
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
