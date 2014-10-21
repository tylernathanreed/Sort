Sort
====

Contains various Sorting Algorithms using various languages.

Languages for each Algorithm:
 - Merge Sort - `Java`
 - Quick Sort - `Java`

<hr>

Below is an overview of each algorithm, as well as specific details that may vary between implementations.

<hr>
**Selection Sort** *(http://en.wikipedia.org/wiki/Selection_sort)*
<hr>

Selection Sort is one of the most basic algorithms for Comparison Sorts.

Basically, given a set of comparable elements, let's say numbers, the algorithm finds the smallest (or largest, depending on sort order) number within the set of remaining numbers, and swaps it with the index the algorithm is currently on. This repeats until everything is sorted.

An example of this would be the domain `[5,3,6,8,2,0,-9,1]`. The algorithm would scan each element from left to right, keeping track of the smallest number it has seen. In this example, it would take 7 comparisons to find the minimum (That's one less than the length of the list, due to `5` being assumed as the minimum). As such, the algorithm would find `-9` to be the minimum, and therefore the next domain would be `[-9,3,6,8,2,0,5,1]`.

Knowing that all elements up to index 0 are sorted, the algorthm starts looking for the next minimum, starting at `3`. Eventually, this will yield a sorted list of `[-9,0,1,2,3,5,6,8]` after Θ(n<sup>2</sup>) comparisons.

<hr>

While this is the typical algorithm for Selection Sort, I take it a step further by Multi-Threading it. To ensure that the threads don't walk all over each other and cause collision issues, I use a nested algorithm that I've called `selection`, in the spirit of Selection Sort.

This algorithm takes two parameters: a list, and some index `k` that maps to an index within the list. This algorithm is tasked to find the `k`<sup>th</sup> smallest (or largest) element within the list. By creating as many threads as there are elements, this becomes an exhaustive and disjoint approach to the original Selection Sort algorithm.

While the `selection` algorithm has Θ(n) runtime, giving Selection Sort the same runtime as before (being Θ(n<sup>2</sup>)), this runtime is now partially parallel, and thus may result in a faster actual runtime than its sequential predecessor.

<hr>
**Merge Sort** *(http://en.wikipedia.org/wiki/Merge_sort)*
<hr>

<hr>
**Quick Sort** *(http://en.wikipedia.org/wiki/Quicksort)*
<hr>
