---
layout: page
title: jfx-input
excerpt: "Project documentation."
search_omit: true
---

**jfx-input** library contains code primarily intended to standardize and simplify the handling of user input in JavaFX applications. It also offers possibilities to persist the configuration of user interactions with the help of the [config](../config/) module.
So far it concentrates mainly on the support of dragging and scrolling events.
Support for keyboard shortcuts etc. will be added in future, as required.


## General concept

The functionality of **jfx-input** splits into properties of a specific event, required for the input action to trigger, and high-level handlers, designed to let the application react on the user input matching those specified conditions, without the need to duplicate all the related boiler plate.


### Event properties

Properties of a specific event are created with an appropriate combination of Event Configuration data (represented by the relevant combination of mouse buttons and modifier keys (such as Shift, or Ctrl)), and describe the complete setup of a single input event.

The relevant classes were also designed to be used within application configuration mechanism, for easy persistence of the user input preferences.


## Event handlers


Event handlers encapsulate all the lower-level stuff required to subscribe for a specified event type, and for filtering out all the faux-events (those, that don't match the specified properties).

When the specific user interaction passes the filter, it is then handed over to the application for further processing.

## Usage

* Import the library into your Maven project:

~~~ xml
<dependency>
    <groupId>com.github.dejv78.commons.jfx</groupId>
    <artifactId>jfx-input</artifactId>
    <version>1.1.0</version>
</dependency>
~~~


### Subscribing for the mouse drag event

The handler is created with a defined set of properties, and registered using the *register(Node node)* method.

{% highlight java %}
// Lets assume, we have a node, that we want to handle the "drag" event on:
final Node node;

// ...and that we want the event to only trigger, if the primary mouse button
// was pressed together with <Alt> and <Shift> keys:

final MouseEventProperties dragProperties 
    = new MouseEventProperties(
           new MouseModifiers().withAlt().withShift(), 
           new MouseButtons().withPrimary());

// We can then handle the complete "drag" session with a single command:
DragActionHandler.with(dragProperties) 
    .doOnDragStart((event) -> /* handle drag start */)
    .doOnDrag((event) -> /* handle drag */)
    .doOnDragFinish((event) -> /* handle drag finish */)
    .register(node); // This will register it on the node
{% endhighlight %}



### Subscribing for the scroll event

The logic is very similar, but the properties are defined in class "GestureEventProperties", instead of "MouseEventProperties". For gesture events, there are no mouse buttons, and also the "Shortcut" key modifier is not available in JavaFX backend.

''' java

// This time we want to respond to scrolling actions performed with <Ctrl> key:
final GestureEventProperties scrollProperties 
    = new GestureEventProperties(new GestureModifiers().withControl());

// The subscription then goes like this:
ScrollActionHandler.with(scrollProperties) 
    .doOnScroll((event) -> /* handle the scroll */)
    .register(node); 
'''



### Unsubscribing from the events

To unsubscribe from receiving the event further on, there is (of course) the *unregister(Node node)* method available.

## API Documentation
Browse the API documentation: [here](/apidocs/jfx-input/index.html).
Download the API documentation: [here]().
