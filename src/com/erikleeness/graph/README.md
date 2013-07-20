# Graph
_Includes a mathematical expression parser and object model_
_Includes animated sort algorithms as demo of the functionality_

This project was meant for this and similar use cases:

1. I want to graph some fairly complicated 2D function. Perhaps it is recursive, a fourier series, or piecewise.
2. My graphing calculator can't do it.
3. I am unable use Mathematica, Matlab, or Maple (either due to lack of knowledge about how they work or even because you are too cheap)

As any function passed is written in Java, anyone who uses the graph API can:

* Reference any Java library
* Perform computational tasks that cannot be expressed in standard calculator mathematics
* Perform logging and caching
* Graph data points
* Anything you can think to put in the calculate() method of your MathFunction (or MathParametric) object, so long as it takes a double and returns a double. 

Is is a good tool for "I want to do this and I don't want to deal with learning some lightweight mathematical scripting language". Its use cases (over better alternatives) are not large, but it's there when you need it.