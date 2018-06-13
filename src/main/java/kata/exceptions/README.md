# implement a news aggregator for a variety of flaky sources

The goal of this kata is to practice exception handling while practicing TDD.


Carmen is a psychology researcher at UCL. Thanks to UCL she has access to a variety of research repositories like ArXiv
and JSTOR. Every morning she likes to read any recently published papers so she's up to date with the latest news.
To do this, she used to manually go to the ArXiv and JSTOR websites and download all new papers. She's not sure if she's
got a bad internet connection or the ArXiv and JSTOR websites are flaky but she often has to click on the download button
several times for a successful download. She's sure there's a way of automating this menial process, can you help her?

1. Write a `ScientificPaperAggregator` that will aggregate new scientific papers from `ArXivRepository` and
`JSTORRepository` and push them to Carmen's `MobileReaderApp` for her to read in the future.

## Throwables

The `ArXivRepository`'s `getNewScientificPapers` method declares it can throw an `IOException`. The java compiler
obliges its users to either catch or declare it can throw the exception - what approach should we take?. You've probably
encountered other exceptions before like `NullPointerException`s, but those don't need to be caught or declared - why?

![alt text](throwables.png)

- `Throwable` is the root class of any object that can be thrown or caught in Java. They can be thrown with the `throw` clause
to indicate the program has reached an undesired state that must be "resolved" before normal execution can continue.
- To "resolve" a `Throwable` it must be caught with the `try-catch` clause. The `try` block defines the perimeter
of the code where a `Throwable` is expected to "appear" and the `catch` block defines the resolution for such an undesired
state of affairs.
- If a line of code can result in a `Throwable` being raised, its declaring method must catch it or the declare it can throw it
with the `throws` clause in the method signature.
- If not caught, a `Throwable` will crawl up the program's call-stack* until it reaches the `main` method of the program, at which
point it'll terminate.
- `Throwable`s can be caught at any point in the call-stack and the program will resume normal execution from that moment
onwards.
- `Error` is a subclass of `Throwable` representing a serious problem in the application like running out of memory (`OutOfMemoryError`).
Due to their severity `Error`s are not meant to be caught, and for that reason they do not need to be caught or declared to be thrown.
- `Exception` is another subclass of `Throwable` created to represent ill conditions a Java program might want to recover from (i.e. `catch`).
- `RuntimeException` is a subclass of `Exception` created to represent unexpected ill conditions that might arise during the execution
of a Java program. Many things could go wrong while running a program and writing code to cover all possible disaster scenarios is unfeasible,
a method might be called on a `null` object (resulting in a `NullPointerException`), or the n+1th item of a n-item long array might be accessed
by mistake (resulting in an `ArrayIndexOutOfBoundsException`). Most of the Java classes can throw multiple of these, so to prevent loitering code with
a gazillion `catch` clauses, the Java designers decided `RuntimeException`s don't need to be caught or declared to be thrown either.
`RuntimeException`s are also called unchecked exceptions, all other `Exception`s are also called checked exceptions.

- `Throwable`, `Error`, `Exception` and `RuntimeException` are `Object`s just like `String` or `ArrayList` - as such you can
create your own classes that extend them. They all have similar mechanics and purposes so often deciding whether to extend `Exception` or
`RuntimeException` can be a matter of judgement.

In short:
- Instances of `Throwable` and its subclasses can be thrown to interrupt the execution of a program.
- A `Throwable` will crawl up the call-stack until terminating the program unless it gets caught.
- Catching a `Throwable` will resume the program's execution from that point onwards.
- `Error`s are `Throwable`s that are not meant to be caught, as such they don't need to be caught or declared to be thrown.
- `Exception`s are `Throwable`s that a program might want to catch.
- `RuntimeException`s are `Exception`s that normally represent a bug in the code that needs fixing. They don't need to be caught or declared
to be thrown.

*A program's call-stack or call-chain is the list of active method calls for a program's execution. In the example below the program's current call-stack
is `main()` &rarr; `callA()` &rarr; `callB()` as illustrated by the debugger.
![alt text](myclass.png)

## After a small divergence

2. When `ArXivRepository` throws an `IOException` while getting new papers, retry the operation a maximum of 3 times until successful.
Send only the papers retrieved from the `JSTORRepository` if unsuccessful after the third time.
