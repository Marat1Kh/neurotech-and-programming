# Iterable Table

## Description
**Iterable** is an object that may produce an Iterator. **Iterator** is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation.

Implement [`com.epam.rd.autocode.iterator.Iterators`](src/main/java/com/epam/rd/autocode/iterator/Iterators.java) method:
- `table` - returns an Iterable that allows iteration over cells of a table. Column by column.\
  Ranges of a table is given as two arrays - string columns and int rows.\
  The result iterates over pairs of given columns and rows.\
  Such pair is a concatenation of a column mark and a row mark: column "A" and row `1` would give a result "A1".
  
## Example

```java
class Main {
  public static void main(String[] args) {
    Iterable<String> table = Iterators.table(new String[]{"a", "b", "c"}, new int[]{3, 45});

    List<String> sink = new ArrayList<>();
    for (String cell : table) {
      sink.add(cell);
    }
    System.out.println(sink); //[a3, a45, b3, b45, c3, c45]
  }
}
```