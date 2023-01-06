# Notes

## Under the hood of Java
### Arrays.sort
Arrays.sort uses two sorting algorithms. One is a modification of Quicksort named dual-pivot quicksort,the other an 
adaptation of MergeSort named Timsort. Both have a time complexity of O(n log n), where n is the total number of items 
in the array. 

Quicksort accepts primitive data types while Timsort accepts generics. The reason being Timsort is a stable sorting 
algorithm while Quicksort isn't. A stable sorting algorithm means if two items of equal value sit next to each other, 
they will maintain the same order after sorting.

When dealing with primitive data types, two integers swapping positions doesn't matter since they are essentially equal 
and you can't notice a difference. On the other hand, when sorting objects, equal objects unnecessarily swapping 
positions can cause harmful effects. Not to mention objects can contain distinct properties which can identify when a 
swap is made.
(https://www.gregorygaines.com/blog/what-is-the-time-complexity-arrays-and-collections-sort/)

### Collections.sort
Collections.sort works on Lists whilst Arrays.sort works on arrays. Collections.sort converts the passed List into an 
array. After the conversion, Arrays.sort is called on the newly allocated array.
(https://www.gregorygaines.com/blog/what-is-the-time-complexity-arrays-and-collections-sort/)

### PriorityQueue
From the Javadoc: this implementation provides O(log(n)) time for the enqueing and dequeing methods
(offer, poll, remove() and add); linear time for the remove(Object) and contains(Object) methods; and constant
time for the retrieval methods (peek, element, and size).
https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html

So enqueing on a PriorityQueue takes O(log(n)), creating this for n elements would make you think that
it will lead to O(n log(n)) for creating the queue, which would be the same as sorting the collection.
However this is not entirely true. Although creating a PriorityQueue element by element would lead to an
O(n log(n)) complexity, creating a PriorityQueue from an existing collection is only O(n).
Some explications for this can be read here: https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity

## Binary search algorithm code and complexity

TODO

## Time complexity for sorting algorithms

* BubbleSort - O(n^2)
* MergeSort - O(n logn)
* Quicksort - O(n logn)
* TimSort - O(n logn) - a variation on MergeSort