# JSF 2.0 Form Components

## 1. Goal

Goal: _Create JSF components for quickly developing forms, in a given corporate style_

A **form** typically:
* consists of rows of label/control pairs.

A **row** typically:
* contains a label
* contains a (single) form control
  * or a group of controls (radio buttons, checkboxes)
* contains an (optional) info-icon
* defines markup for errors
* is responsive (stack label and control on mobile)
* more?

(The above description probably covers 90% of all controls in an average form.)

The **components** should:
* conform to the corporate styleguide
* minimize code duplication (apart from the form control itself, all other characteristics of a row are identical)
* be consistent (tag attributes with the same purpose, should have the same name)
* have descriptive names
  * if the component renders a label, make that clear in the component name (labeledTextField) or in the library name (e.g. formcontrols.labeled)
* NOT mix functionality with markup/layout

NOTE: The existing components currently violate all of the above.

## 2. Plan

How can we design forms (or form components), if we don't know the corporate style?

What is the corporate style?

### 2.1 Start with developing static HTML templates

The HTML templates (and CSS) should contain _examples_ of the corporate styleguide: How to display
* a text field, date picker, radio buttons, dropdowns, etc. inside a form
* info-icons
* error messages for an individual control
* error/warning/info messages at the form level

And all of the above should be _responsive_.

Question: Who should develop this? Preferably a **front-end expert**.

### 2.2 Based on the templates, create the necessary JSF components

To avoid code duplication
* do not mix markup/layout (bootstrap grid, error messages etc.) with functionality (the control)
* in other words: Create a component 'row' that defines all the markup (for the label, control and error messages),
  and has an insertion point for the actual control.

If it turns out to be impossible to define such a generic pluggable 'row' component, we might end up coding
(read: copy pasting) all the generic markup/behavior in each component (e.g. labeledTextField, labeledDate, labeled*, ..).
If we do so, let's make (and keep) it consistent.

### 2.3 Based on these JSF components, create pages

And because of the previous steps, you get everything for free:
* consistent markup/layout
* responsiveness
* clean code (JSF components that do exactly what they should do, nothing more nothing less; and that have a consistent interface)

## 3. Recommendations

* Start by defining the corporate styleguide (by designers)
* Create JSF components from scratch (don't use or reuse old stuff)
* This project contains a custom 'row' component that enforces consistent markup/layout/behavior; we could use it...

## 4. Useful resources

http://balusc.omnifaces.org/2013/01/composite-component-with-multiple-input.html

https://ptrthomas.wordpress.com/2009/05/15/jsf-sucks/
(zie laatste item [2010-11-18] "I hate JSF with a passion" van James Gosling).

Kan het 'targets' attribuut van composite:attribute worden gebruikt om een attribuut door te geven aan een genest component?
(i.p.v. alles voluit uit te schrijven met #{cc.attrs.attrnaam})
http://stackoverflow.com/questions/8672032/composite-component-action-attribute-reutilization


