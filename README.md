# java-katas
programming katas in java to exercise TDD, OO, pair programming and other software development practices

## Pair Programming
development is done in pairs, with devs taking the roles of a driver and a co-pilot. following the analogy, the driver is in control
of the keyboard and does most of the coding; whereas the co-pilot takes a strategical role by helping the driver decide what
should be the next path to explore. roles ought be interchanged every so often but it's not mandatory. effective pair programming should
bring the following benefits:

- improved code quality
- knowledge sharing of code base
- driver and co-pilot can learn from each others' expertise
- reduction of slacking (not a textbook answer)

although there are also downsides:

- cost (there's only one person typing)

to achieve effective pair programming as a driver remember to:

- do not rush with the coding, pair programming is not a demonstration of coding
- speak out your thoughts to make it easier for the co-pilot to follow through
- when you get stuck, stop and discuss with your co-driver possible solutions

to achieve effective pair programming as a co-pilot remember to:

- not fallback into the trap of disconnecting from the exercise
- question the driver's decisions when you don't understand them, suggest improvements
- do some thinking ahead, the driver is both coding and speaking out their thoughts whereas you're just thinking

## TDD

Test Driven Development is a set of practices/processes/state-of-mind that emphasises the use of unit tests to guide
software design. If one had to summarise TDD in one sentence that'd be:

- write the test, see it fail, make it pass, refactor

This cycle describes the normal TDD practice of first thinking what you're going to test / how do you proof your code is working
/ what problem are you trying to solve; solving it; and then refactoring code into a more elegant solution if appropriate

These are the common benefits of TDD:

- thinking about the tests before writing the code allows for simpler designs, leading to less over-engineering;
remember: write the minimum amount of code that will make the tests pass
- writing the tests first usually leads to the correct object encapsulation (the whole principle of OO)
- tests give you confidence that your code still works after you've made changes to it after a refactoring exercise or adding new functionality


## Unit Testing

A unit test is a test for a single unit, the word unit is deliberately ambiguous as different programming languages have different
definitions for a unit, but in general a unit refers to the smallest unit of code - in Java that'd be a class.

Unit testing allows developers to think about each class (I'll use class and unit interchangeably now) in isolation without having to worry about the correct workings of the other components.

Classes don't often work on their own but rather interact with other classes. To be able to successfully unit test a class the developer
must be able to idealise the behaviour of the interacting components and test the subject class under said idealised interactions.

Dos of unit testing:

- test small amounts of behaviour in each test case
- only test business logic
- write the minimum amount of code that'll make the tests pass
- if it's expected behaviour in your code, write a test for it
- see the test case fail before writing the code that'll make it pass
- keep tests up to date
- give your test cases meaningful names

Donts of unit testing:

- overdo testing, don't test setters and getters, focus only on business logic

Because unit tests idealise the interactions of the subject class with the other classes, other types of testing are still
necessary (integration testing being one of them).

## Static Methods

When a method doesn't need use any state from an object's instance it can be made static. static methods belong to the jvm
rather than an object instance. as a consequence of this static methods are usually very simple, short and used primarily as
helpers. example usages of static methods are :

- numerical computations (look at all the Math's package methods...Math.abs(), Math.pow(),
Math.random(), Math.max(). None of these methods need of an object's instance state to work.
- factory methods (methods used to create object instances) for classes with awkward constructors, look at LocalDate.now(), LocalDate.from(), LocalDate.of(), Collections.singletonList()

## Public, private, provided, default visibility modifiers

 - public is used for those methods that are intended to be freely called by all other classes
 - private methods can't be called by any other class other than the class declaring said method, it is used to
 hide the complexity of code into submethods
 - default methods don't have a visibility modifier keyword, they're used to allow classes in the same package to make use
 of them but prevent classes outside of its package from accessing them. an example usage is the getPlayer1Points() method in SimplifiedTennisMatch,
 we wanted the SimplifiedTennisMatchFormatter to have access to the player names, but we don't want developers using our library to have access to it
 - protected methods can be seen by classes in the same package and subclasses, i.e. like protected but subclasses can also see said method.
 used similarly as default methods but used to also allow subclasses to override the behaviour of the proctected methods. abstract classes normally have
 protected methods.