Sort
====

Contains various Sorting Algorithms using various languages.

Languages for each Algorithm:
 - Merge Sort - `Java`
 - Quick Sort - `Java`

Below is an overview of each algorithm, as well as specific details that may vary between implementations.

**Selection Sort** *(http://en.wikipedia.org/wiki/Selection_sort)*

Selection Sort is one of the most basic algorithms for Comparison Sorts.

Basically, given a set of comparable elements, let's say numbers, the algorithm finds the smallest (or largest, depending on sort order) number within the set of remaining numbers, and swaps it with the index the algorithm is currently on. This repeats until everything is sorted.

An example of this would be the domain `[5,3,6,8,2,0,-9,1]`. The algorithm would scan each element from left to right, keeping track of the smallest number it has seen. In this example, it would take 7 comparisons to find the minimum (That's one less than the length of the list, due to `5` being assumed as the minimum). As such, the algorithm would find `-9` to be the minimum, and therefore the next domain would be `[-9,3,6,8,2,0,5,1]`.

Knowing that all elements up to index 0 are sorted, the algorthm starts looking for the next minimum, starting at `3`. Eventually, this will yield a sorted list of `[-9,0,1,2,3,5,6,8]` after `Θ(n lg n)` comparisons.

**Merge Sort** *(http://en.wikipedia.org/wiki/Merge_sort)*

**Quick Sort** *(http://en.wikipedia.org/wiki/Quicksort)*
