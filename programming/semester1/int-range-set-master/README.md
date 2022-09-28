# Int Range Set

The purpose of this exercise is to train you in implementing Collections.

Estimated workload of this exercise is _120 min_.

### Description

Please, proceed to [IntRangeSet](src/main/java/com/epam/autotasks/collections/IntRangeSet.java)
and implement its methods.

IntRangeSet is a Set of IntRange objects.

[IntRange](src/main/java/com/epam/autotasks/collections/IntRange.java)
is a class representing range of int values between `from` (inclusive) and `to` (exclusive) values.

IntRangeSet does not allow overlap of its range elements:
- When a range is added that is completely overlapping with ranges
  that are already elements of the set, then this range must not be applied at all:
  ```
  add range [0; 10)
  add range [10; 15)
  add range [5; 13)
  ---
  result set: {[0; 10)[10; 15)}  
  ```
- When a range is added that is partially overlapping with ranges
that are already elements of the set, then only its subranges must be effectively added:
  ```
  add range [0; 10)
  add range [5; 15)
  ---
  result set: {[0; 10)[10; 15)}
  ```
  ```  
  add range [0; 10)
  add range [20; 30)
  add range [5; 25)
  --- 
  result set: {[0; 10)[10; 20)[20; 30)}  
  ```
  ```  
  add range [0; 10)
  add range [20; 30)
  add range [40; 50)
  add range [0; 50)
  --- 
  result set: {[0; 10)[10; 20)[20; 30)[30; 40)[40; 50)}  
  ```
- When a range is removed that is overlapping with ranges
that are already elements of the set, then existing overlapping elements are affected:
  ```
  add range [0; 10)
  remove range [5; 15)
  ---
  result set: {[0; 5)}
  ```
  ```  
  add range [0; 10)
  add range [20; 30)
  add range [40; 50)
  remove range [5; 45)
  --- 
  result set: {[0; 5)[45; 50)}
  ```

You need to implement following methods:

- add - adds a range into the set. When overlapping existing elements - adds its subranges accordingly.
  Returns `true` if any of range values were actually added.
- remove - removes a range from the set, affecting all the elements, that overlap with input range.
  Returns `true` if any of range values were actually removed.
- iterator - iterates over elements of the set in natural order.
- intsIterator - iterates over all the integer values covered by ranges of the set.
- size - returns number of ranges in the set.
- intsSize - returns number of int values covered by ranges of the set.

### Example

```java
IntRangeSet set = new IntRangeSet();

assertTrue(set.add(range(-5, 5)));
assertEquals(10, set.intsSize());
assertEquals("[-5; 5)", toString(set));

assertTrue(set.add(range(3, 10)));
assertEquals(15, set.intsSize());
assertEquals("[-5; 5)[5; 10)", toString(set));

assertTrue(set.remove(range(7, 9)));
assertEquals(13, set.intsSize());
assertEquals("[-5; 5)[5; 7)[9; 10)", toString(set));
```
