# About Persistent-HashMap
The Persistent-HashMap is basically a persistent version of the Java HashMap class.

![Build Status](https://api.travis-ci.org/reines/persistenthashmap.png)

Warning: It is not under active development, and has not been used in a production environment.

## Features
 * Implements the Map interface so can be used in the exact same way as a regular HashMap.
 * No space needs preallocated.
 * Disk space is immediately reclaimed on removing an object.
 * No maintenance required to keep the data store clean.
 * Caching option that keeps a copy of the map in memory (using a standard HashMap) to speed up access. 

## Notes
 * Because the Map interface does not allow for exceptions being thrown null values are used to indicate an error. As such, the map should not be used to store null values otherwise the behaviour is undefined.
 * The Persistent-HashMap is thread-safe, but does not perform file locking. This means the same data store should not be accessed from multiple processes at once otherwise the behaviour is undefined.
 * Since objects are written to disk the Persistent-HashMap does not have amazingly good performance. It should only be used when persistence is necesary and performance is not critical.

## License
The Persistent-HashMap library is released under the BSD 3-clause license.

## Credits
The theory is based on [HashStore](http://www.cellspark.com/hashstore.html).
