# clojure4j

Clojure4J is a partial Java binding to the Clojure programming language.  The primary purpose of the library is to provide Java with a library for persistent data structures and a functional API for working with them.



## Usage

FIXME

## FAQ for Java developers

### Why should I care?
Java developers basically have two choices:  mutable collections (e.g. java.util) and write-once-read-many immutable collections (e.g. Google's Guava).  However, this API attempts to provide a third choice by bringing persistent data structures to Java.  This provides the safety and simplicity of immutable collections but with the ability to create new versions of those collections with elements added or removed without doing full copies of the original.

### What are persistent data structures?
Persistent data 

### Is this API redundant given Java 8's java.util.streams?
In Java 8, it is still difficult to treat collections as values.  This API helps with that problem.

### Why a Clojure binding?
By making the API a Clojure binding, a large pool of support and documentation is already in place.  For example, a web search for functions in this API already has years of questions and answers on various websites.  Furthermore, Clojure has strong decision making behind its design and implementation.  Developing a similar API of the same quality from scratch would take a lot of time (and talent).

### Are Clojure4J collections compatible with java.util collections?
Yes, in the same way that "unmodifiable" collections work in java.util.Collections.  I.e. they behave as read-only java.util.Collections.

### What are the tradeoffs?
As a rule of thumb, you are trading performance for the safety of treating collections as values.  Clojure4J's collection operations tend to be 2-3x slower than java.util.  

## FAQ for Clojure developers

### Why are clojure fns object methods on persistent collections?
This makes up for the lack of "->" and "->>" in the API.  Furthermore, it makes the API more accessible since the developer does not have to reason on whether thread-first or thread-last is the right choice.  Finally, with clojure4j.core.Core most functions are also available as static methods without the object "chaperone".

### Why typed collections?
It is assumed that a traditional Java shop might resist using this API without typed collections, seeing it as a step backwards to pre-1.5 Java.  The hope is that this choice helps lower the barrier to incorporating some 
elements of functional programming into traditional Java projects by avoiding those arguments.

### Is Clojure4J slower than Clojure?
The current implementation wraps Clojure objects and IFns.  In tests, Clojure4J collections tend to be a few percentage points slower than calling Clojure 1.6's Java API directly.  A direct comparison to native Clojure has not been performed, but performance relative to Java's collections seems to be inline with Clojure's claims about persistent data structures.

### Why not just use Clojure?
If using Clojure is an option for you, then by all means use it.  This API is definitely not a substitute for Clojure.  However, there are cases where people may want to use Clojure (or a functional language) at work, but they cannot.  This API offers a means for programming with “values” and functions without leaving Java.  It might also serve as a means to expose management or peers to some of the benefits of functional programming, opening the door to functional languages such as Clojure.


## License

Copyright © 2014 Jonathan Newton

Distributed under the Eclipse Public License either version 1.0






