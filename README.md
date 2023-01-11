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

## Graphs

In graph theory and computer science, an adjacency list is a collection of unordered lists used to represent a finite 
graph. Each unordered list within an adjacency list describes the set of neighbors of a particular vertex in the graph. 
This is one of several commonly used representations of graphs for use in computer programs.

Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree. The only catch here is, 
that, unlike trees, graphs may contain cycles (a node may be visited twice). To avoid processing a node more than once, 
use a boolean visited array. A graph can have more than one DFS traversal.

As for trees, there exists inorder/preorder/postorder traversal. The followin is an example of an inorder
traversal algorithm (which is very similar to the tree one):

    public void dfs(List<List<Integer>> adjVertices, int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        dfsRecursive(adjVertices, start, isVisited);
    }
    
    private void dfsRecursive(List<List<Integer>> adjVertices, int currentIndex, boolean[] isVisited) {
        isVisited[currentIndex] = true;
        visit(currentIndex);
        for (int dest : adjVertices.get(currentIndex)) {
            if (!isVisited[dest]) {
                dfsRecursive(dest, isVisited);
            }
        }
    }

https://www.baeldung.com/java-depth-first-search


## Tree

### Binary Tree Traversal

Binary Tree is defined as a tree data structure where each node has at most 2 children. Since each element in a binary 
tree can have only 2 children, we typically name them the left and right child.

             1
           /   \
          12    9
         /  \
        5    6

There are a few types of tree traversals:

* Inorder traversal 

First, visit all the nodes in the left subtree. Then the root node. Visit all the nodes in the right subtree.

    inorder(root->left)
    display(root->data)
    inorder(root->right)

Result of the previous tree is: [5, 12, 6, 1, 9]

* Preorder traversal

Visit root node. Visit all the nodes in the left subtree. Visit all the nodes in the right subtree.

    display(root->data)
    preorder(root->left)
    preorder(root->right)

Result of the previous tree is: [1, 12, 5, 6, 9]

* Postorder traversal

Visit all the nodes in the left subtree. Visit all the nodes in the right subtree. Visit the root node.

    postorder(root->left)
    postorder(root->right)
    display(root->data)

Result of the previous tree is: [5, 6, 12, 1, 9]


Each algorithm can be done iteratively also, but a stack has to be used for this. The following is an example of the 
preorder algorithm implemented iteratively:

    if (node = null)
        return
    s —> empty stack
    s.push(node)
    while (not s.isEmpty())
        node —> s.pop()
        visit(node)
        if (node.right != null)
            s.push(node.right)
        if (node.left != null)
            s.push(node.left)

## Search Algorithms

### Binary search algorithm code and complexity

TODO

## Sorting Algorithms

### Time complexity for sorting algorithms

* BubbleSort - O(n^2)
* MergeSort - O(n logn)
* Quicksort - O(n logn)
* TimSort - O(n logn) - a variation on MergeSort

## Math

### Line between two or more points (slope)

In mathematics, the slope or gradient of a line is a number that describes both the direction and the steepness of the 
line. Slope is often denoted by the letter m;

Slope is calculated by finding the ratio of the "vertical change" to the "horizontal change" between (any) two distinct 
points on a line. Sometimes the ratio is expressed as a quotient ("rise over run"), giving the same number for every 
two distinct points on the same line. A line that is decreasing has a negative "rise". The line may be practical – 
as set by a road surveyor, or in a diagram that models a road or a roof either as a description or as a plan.

The steepness, incline, or grade of a line is measured by the absolute value of the slope. A slope with a greater 
absolute value indicates a steeper line. The direction of a line is either increasing, decreasing, horizontal or vertical.
* A line is increasing if it goes up from left to right. The slope is positive, m>0 
* A line is decreasing if it goes down from left to right. The slope is negative, m<0
* If a line is horizontal the slope is zero. This is a constant function. 
* If a line is vertical the slope is undefined (see below).

The rise of a road between two points is the difference between the altitude of the road at those two points, say y1 
and y2, or in other words, the rise is (y2 − y1) = Δy. For relatively short distances, where the Earth's curvature may 
be neglected, the run is the difference in distance from a fixed point measured along a level, horizontal line, or in 
other words, the run is (x2 − x1) = Δx. Here the slope of the road between the two points is simply described as the 
ratio of the altitude change to the horizontal distance between any two points on the line.

In mathematical language, the slope m of the line is: m = (y2 - y1) / (x2 - x1).
