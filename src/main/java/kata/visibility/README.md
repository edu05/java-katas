# The case for visibility modifiers

Having different visibility modifiers was a design choice by the Java language designers. Because not all languages have the same
visibility modifiers it can be tricky to decide what visibility modifier to use in each case. There's also multiple solutions
to a single problem, which is best? This can sometimes be subjective.

## What are visibility modifiers

Visibility modifiers are keywords that affect the lines of code that other lines of code can invoke. So...visibility modifiers
are used to avoid certain lines of code from invoking them, otherwise we wouldn't need them. You can think of it as the simplest
form of cybersecurity (although it's a very loose analogy). Normally we want to be as restrictive as possible and only allow
access to lines of code we feel comfortable sharing with other code.

Visibility modifiers go in front of class and class member declarations (class members are class variables and methods).

The other thing to note before diving into different visibility modifiers are the following guidelines:
1. `Class` names are usually `nouns`, this helps `class instances represent a real world entity`.
2. `Method` names are usually `verbs`, methods are invoked on class instances, i.e. `they are actions performed on the object`.

You might recall previous code like `match.score(NADAL)`, match is a noun and score is a verb. The important take from these
two guidelines is we only want to permit access to those actions that are natural to their noun. It makes sense for Nadal to score
a point in a tennis match, so the score method is open for anyone to invoke. However, we don't want outsiders manually tampering
with Nadal's score without him having scored first - and that's why the score variables for the `SimplifiedTennisMatch` are
hidden away.

## public
The `public` keyword allows universal access to its following method/variable/class for every other class. Use the `public`
modifier to indicate users they are allowed to make use of it. Users of the `SimplifiedTennisMatch` class are allowed to score
points for a player. We say `public` methods establish a class' contract (or API) because they define the legal actions that
can be performed on them.

## private
The `private` keyword stops anything outside the declaring class from accessing the following method/variable/class.
Use the `private` modifier to break complex code into simpler methods that other classes shouldn't be aware of or to hide inner
variables.

## default
If no visibility modifier is specified then we say it has `default` (or package private) visibility. Items with no visibility
modifier can be accessed from within the class or from classes inside the same package. Giving access only to the classes
within the same package is useful for classes whose behaviour is somehow interrelated without giving anything away to the outside world
(classes in a different package). This modifier is mostly used for utility classes within a library. Take as an example
the `Integer` class in the `java.lang` package in Java, it is full of members with no visibility modifier. Other classes
in the same `java.lang` package can benefit from them, but no other class.


## protected
The `protected` keyword has the same meaning as the `default` visibility modifier with one exception - subclasses are also
allowed to access the class or class member. The most frequent use case for `protected` is the declaration of abstract
methods. Abstract methods need to be overridden by all subclasses (by definition) and are normally used only inside some other
non-abstract method of the abstract class. The `protected` keyword allows subclasses to override the behaviour of said
method while still keeping it a secret from every other class outside the package.



## Wrap-up

The public and private visibility modifiers fit the most use cases and 95% of the time you'll be using those. On rare occasions
default or protected are more appropriate.


Here's a tip for remembering what code can be accessed from where according to its visibility modifier - place the modifiers in
order of restrictiveness; and the access levels by separation. Then notice how the Ys and Ns are separated by a nice diagonal.

   | modifier | class | package | subclass | world |
   |----------|-------|---------|----------|-------|
   | public   | Y | Y | Y | Y |
   | protected| Y | Y | Y | N |
   | default  | Y | Y | N | N |
   | private  | Y | N | N | N |

This table was taken from https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html