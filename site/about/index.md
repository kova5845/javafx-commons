---
layout: page
title: About jfx.commons
excerpt: "Project description."
search_omit: true
---

jfx.commons is a small library, that contains some generally useful, JavaFX related code.
Current version is 1.0.1.

## Contents

### dejv.jfx.commons.binding package
* **FunctionDoubleBinding** - Applies a Function on a DoubleExpression dependency
* **ReductionDoubleBinding** - Reduces multiple DoubleExpression dependencies with a BinaryOperator

### dejv.jfx.commons.geometry package
* **ObservableBounds** - Replacement to *javafx.geometry.Bounds* with observable properties
* **CompositeObservableBounds** - Unites multiple *javafx.geometry.Bounds* properties into single, observable bounds
* **ObservableDimension2D** - Replacement to *javafx.geometry.Dimension2D* with observable properties
* **ObservablePoint2D** - Replacement to *javafx.geometry.Point2D* with observable properties
