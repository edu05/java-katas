# Implementing an optimal LRU with a puzzle game

NOTE: This exercise is a bonus installment to the previous exercise to better explain how to implement an optimal LRU cache.
Its intention is to explain how to go from a simple sub-optimal LRU cache to a more complex but optimal LRU cache.
For this reason you must have done the previous exercise up to the third AC as a pre-requisite.

We've previously implemented a suboptimal LRU cache in that the `put` operation was O(n) instead of O(1).
Coding a LRU cache where both `get` and `put` operations are O(1) can be tricky, but we'll
try to come up with one by using two handy techniques for general problem solving:

1. Asking what's preventing us from achieving our goal
2. Dividing a complex problem into multiple simpler sub-problems

## What data structures do we need to implement a LRU cache?

We'll demonstrate how asking ourselves what's preventing us from achieving success can help us reach it:

The third AC of the previous LRU cache exercise gave us a fully functioning LRU cache that recorded the last accessed
time of each of its entries as a means to identify and evict the most unused entry. This implied iterating over the whole
cache on each `put`. This iteration is what makes `put` O(n).

1. What is preventing us from reaching O(1)? Iterating over the whole cache, we need a data structure that starts ordered and remains ordered after every insertion. Having an always ordered data structure enables us to retrieve the most unused item in O(1)
2. What's a data structure that can hold ordered items? Let's start with an ArrayList
3. Can we remove an item from an ArrayList in O(1)? No, we can opt for a (doubly) LinkedList
4. Can we insert an item into a LinkedList in O(1) at the front? Yes
5. Can we move an existing item to the front in a LinkedList in O(1)? Yes

We have arrived to the conclusion that a Map whose CacheEntries are doubly linked will enable us to insert items to the front
of the list in O(1). When the cache is full the doubly linked list will also enable us to remove the most unused entry in O(1).
When an entry is updated we can also move it from its current position to the front in O(1). Excellent!

## Time to code!

Implementing larger pieces of software can be daunting, writing pseudo-code and breaking down the program
into subprograms can be useful. After each of the subprograms has been written it becomes a matter of gluing up all
of the pieces together.

In the spirit of this pseudo code has been provided for a correct implementation of an optimal LRU cache. The larger pieces
of pseudo-code have been assigned templates.<br>
Each template has a diagram to help visualise what the pseudo-code block does - only the mapping from pseudo-code blocks to diagrams is missing!

The task is to:

1. write the code for the simpler lines of pseudo-code that don't have a template assigned
2. find a suitable diagram that correctly represents each pseudo-code template
3. with the help of the diagrams, write the missing code!

