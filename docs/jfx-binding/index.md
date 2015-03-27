---
layout: page
title: jfx-binding
excerpt: "Project documentation."
search_omit: true
---

**jfx-binding** library contains some useful classes for enhanced JavaFX property binding.

## Usage

* Import the library into your Maven project:

~~~ xml
<dependency>
    <groupId>com.github.dejv78.commons.jfx</groupId>
    <artifactId>jfx-binding</artifactId>
    <version>1.1.0</version>
</dependency>
~~~



### FunctionDoubleBinding

FunctionDoubleBinding applies the given [Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) on its dependency.
For example, to bind one property to a sinus of other property value, you can use it like this:

~~~ java

final DoubleProperty angleRad = new SimpleDoubleProperty(Math.PI);
final DoubleProperty angleSin = new SimpleDoubleProperty();

// This binds the property sinAngle to angleRad property, 
// and applies the "sin" function on it's value.
angleSin.bind(new FunctionDoubleBinding(angleRad, Math::sin));


angleSin.get(); //returns 1.0

angleRad.set(0);

sinAngle.get(); //returns 0.0

~~~



### ReductionDoubleBinding

ReductionDoubleBinding can reduce multiple dependencies into single value, using associative [BinaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html). 
It is well suited for those use cases, where the number of dependencies varies over time, as they can be added and removed "on the fly" easily.

~~~ java
// First, lets declare some properties for later:
final DoubleProperty property1 = new SimpleDoubleProperty(50.d);
final DoubleProperty property2 = new SimpleDoubleProperty(20.d);
final DoubleProperty property3 = new SimpleDoubleProperty(10.d);

final DoubleProperty result = new SimpleDoubleProperty();
~~~

~~~ java
// It is possible to start with no dependecies at all ... :
final ReductionDoubleBinding reductionBinding =  
        new ReductionDoubleBinding(Math::min);

result.bind(reductionBinding);

result.get(); // Returns Double.NaN by default

// ... and add the dependencies later:

reductionBinding.add(property1); // Result is 50.0 now ...

reductionBinding.add(property2); // ... and now it's 20.0
~~~

~~~ java
// Or we can start already with as many dependecies, as we want:
final ReductionDoubleBinding reductionBinding =  
        new ReductionDoubleBinding(Math::min, property1, property2, property3);

result.bind(reductionBinding); // Now the result is 10.0
~~~

~~~ java
// Optionally, if we want to specify the default value ourselves, we can 
// do that also:
final ReductionDoubleBinding reductionBinding =  
        new ReductionDoubleBinding(Math::min)
        .withDefault(150.d);

result.bind(reductionBinding); // The binding is still "empty" like in 
                               // the first case, but the result is now 150.0
                               // instead of Double.NaN
~~~

~~~ java
// Dependencies can be removed with "remove":

reductionBinding.remove(property2); // back at 50.0

// ... or dumped completely with "clear":

reductionBinding.clear();

result.get(); // Now it returns "default value" again.
~~~

## API Documentation
Browse the API documentation: [here](/apidocs/config/index.html).
Download the API documentation: [here]().