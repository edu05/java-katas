# implement a Last Recently Used cache

The goal of this kata is to implement a LRU cache following TDD.

## Caches

A cache is a hardware device that stores addressable memory. Addressable memory is memory that can be retrieved with a key.
Users of a cache only need to remember the keys (small in memory size) to retrieve the larger pieces of memory they need storing.
(Storing new / retrieving existing) pieces of memory need to be really fast operations for a cache to be deemed a cache, and not just a plain memory device.

- Create a Cache that can store an object for a given key. It must offer the same object when asked for its key. Think about
what the Cache should return for a key that hasn't had an object assigned.