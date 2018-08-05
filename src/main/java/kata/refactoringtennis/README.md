# refactoring the simplified tennis game kata

The goal of this kata is to introduce refactoring by revisiting the simplified tennis game kata

## Background
This exercise builds on the previous `tennis` kata. The idea is to simulate a business
stakeholder asking for new requirements that go against the grain of what our current software does. It is in this scenario
where refactoring becomes a useful tool.

## The starting point

We start with a complete `SimplifiedTennisMatch` able to print the running score of a simplified tennis match. The rules of a
simplified tennis match are the same as in the `tennis` kata:

1. A game is won by a minimum of 4 points and with a score difference higher than 1
2. A score of 0 is represented with 0, 1 point with 15, 2 with 30 and 3 with 40
3. If a player has 4 points but hasn't won yet, the player with higher points has an ADVANTAGE, both players are at DEUCE if their score is equal

These rules are reflected in the initial test class for the `SimplifiedTennisMatch`.

## What is refactoring?

Quoting Martin Fowler's book on refactoring:
> Refactoring is a controlled technique for improving the design of an existing code base. Its essence is applying a series of small behavior-preserving transformations, each of which "too small to be worth doing". However the cumulative effect of each of these transformations is quite significant. By doing them in small steps you reduce the risk of introducing errors. You also avoid having the system broken while you are carrying out the restructuring - which allows you to gradually refactor a system over an extended period of time.

To break this down:
1. Refactoring improves the design of **existing** functionality. Do not change functionality on the same step (commit) as refactoring - even if you're refactoring to make future changes simpler
2. Each refactoring should be simple so as to not compromise the correctness of the code - small and frequent refactorings are preferred to a single big refactoring
3. Ideally, well written unit tests will proof the code hasn't been broken by the refactoring (point 2.)
4. Try to write unit tests before refactoring code if they don't exist (this is not always possible)

## Why do we refactor?

2. Simplify code - we've seen instances of this in previous katas, e.g. replaced `if`s nested inside `for` loops with streams and lambdas, and written private methods for readability
1. Bridge the gap between the code and the real world entities it represents - code that accurately represents the real world adapts better to new requirements. In coding terms this is achieved with appropriate domain modelling and behaviour encapsulation
3. Improve performance - we've also seen instances of this in previous katas, e.g. the LRU cache puzzle brought a speed performance to our LRU cache, unit tests proved the correctness of the code was not compromised.
Don't optimise code pre-maturely though - optimise for performance only when needed

## The exercise

1. Do not allow players to keep scoring after the match has finished (throw an `UnsupportedOperationException`)
2. The simple tennis association (STA) decides matches should be won by a minimum margin of 3 points.
    + Solving the previous AC resulted in some undesired code duplication. This gets highlighted with this new requirement. First solve the problem, then refactor.
3. The STA decides matches should be won by a minimum margin of 4 points
    + This part might be easy if you've done multiple refactorings (but notice it'd be harder if you hadn't)
4. The STA wants a more modern display for match scores, one that resembles real tennis displays. They have provided you with the code for
the new modern display in `displayModernScore()` but you'll need to refactor your code to pass an appropriate `Score` object to its subroutine.
    + Use the `SimplifiedTennisMatchRunner` to see this working rather than writing unit tests
    + Notice how `displayModernScore(Score score)` has no logic concerning the rules of tennis. This is good because we don't have to worry about a change in the rules of tennis changing our display code.
5. Refactor the `formatScore()` method to also work with a `Score` object
    + The display logic for both methods is now completely separate from that of the tennis rules. The `Score` object has all the information needed by the displays and has become the boundary between tennis rules logic and display logic
    + One can imagine adding more display styles, but that would only make our `SimpleTennisMatch` class grow and grow. Can we do better?
6. Extract the `formatScore(Score score)` and `formatScoreModern(Score score)` code into their own classes. They should both implement an
interface with a `formatScore(Score score)` method. Modify `SimplifiedTennisMatch` class to have a `formatScore(YourInterface yourInterface)` method
that gives the user the ability to switch display systems without worrying about the rules of tennis. Mint.

## What about legacy code?

Code becomes legacy code when it stops to reliably represent the real world entities it tries to model. The size of the gap
between code and real world is a measure of how legacy our software is. The gap is such that it can't be bridged by simple refactorings.
Legacy code is troublesome because a small change in how the real world behaves (a new business requirement) could entail a
big set of changes to the code so it can fit this new reality.

Code becomes legacy when the real world evolves while code remains stable. The business considers other areas deserve the investment
necessary to keep their code up to scratch while the to-be-legacy system gets no TLC because it's not seen as a money maker.
This goes on until one day the inevitable happens and a change to the now legacy system is required to support the true cash cows of
the business. At this stage the business has two options:

1. Re-write the whole or a large part of the system - costly
2. Adapt the legacy system enough to make it work with the new world - cheap

Developers (normally) dislike legacy code because:

1. They hack the code just enough to make it work instead of giving the problem the attention it deserves
2. Even small business requests require big development efforts that only introduce new complexities that further widen the code-world gap
3. They feel their work is not as appreciated given they're working on an unimportant area of the business