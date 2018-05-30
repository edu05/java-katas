# implement a Last Recently Used cache

The goal of this kata is to implement a LRU cache following TDD.

## Caches

A cache is a hardware device that stores addressable memory. Addressable memory is memory that can be retrieved with a key.
Users of a cache only need to remember the keys (small in memory size) to retrieve the larger pieces of memory they need storing.
(Storing new / retrieving existing) pieces of memory need to be really fast operations for a cache to be deemed a cache, and not just a plain memory device.

- Create a Cache that can store an object for a given key. It must offer the same object when asked for its key. Think about
what the Cache should return for a key that hasn't had an object assigned.


## Why isn't every memory device a cache?

If caches are the fastest memory devices, why isn't all memory in a PC a cache? Mostly distance.

CPUs are so fast crunching numbers these days most time is spent by memory travelling from the CPU to the devices that hold them via wires.

CPUs and memory devices take up physical space, so there's a limited amount of caches you can put around a CPU before completely surrounding
it - at which point the next closest memory device to the CPU is at a considerable disadvantage.

PC designers have remedied this by stacking up layers of memory, each layer is slower but can also hold more memory. This way memory is
as close as it can be to the CPU (L1 and L2 caches in PC specs starting to sound familiar?).

- Limit your cache to hold only N items, after it's been filled ignore any requests to put in any new items. Items for existing
keys can still be updated


## What items should be placed in each memory layer?

To reduce memory travelling time, an utopic PC would know in advance what pieces of memory it would need next and keep those
close by, while keeping the unused memories farther away. Some PCs do try to predict what data they'll need next, and place them near the CPU, but they're not perfect.

Another successful strategy to decide what layer a piece of memory should reside in is the Last Recently Used policy.
The rationale behind this strategy is pieces of memory that have been used recently are more likely to be used in the near future than
items that haven't been used in a long time - this is a pretty good heuristic.

A LRUCache follows the LRU policy by keeping the N last recently used items inside it.

- Transform your Cache into a LRUCache, i.e. a cache where new items are always inserted and only holds the N last recently
used items.

## An optimal implementation of a LRUCache

Optimal implementations of a LRUCache have really fast `get` and `put` operations, this normally translates to them being O(1).
Proving our LRUCache is O(1) for both operations via TDD is pretty hard, what we can do instead is convince ourselves there are
no loops in the code or recursion in the code, i.e. only a number of constant code lines ever gets executed - while still maintaining
the correct functionality of the code

- Make `get` and `put` operations in the LRUCache O(1)

Hint 1: We can achieve O(1) for both operations if we can maintain the cache entries ordered by their last access time at all times
Hint 2: In a `put` we need to place the new item in the front of the collection and discard the last item of the collection from the cache if it was full
Hint 3: In a `get` we need to pull the entry from it's current position and place it in the front of the collection