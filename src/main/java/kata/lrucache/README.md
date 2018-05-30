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