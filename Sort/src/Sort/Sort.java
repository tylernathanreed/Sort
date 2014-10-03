//* Description *//
// Title: Sort
// Author: Tyler Reed
// Implements various Sorting Algorithms

//* Package *//
package Sort;

//* Libraries *//
import java.util.List;
import Sort.MergeSort.MergeSort;

//* Sort Interface *//
public final class Sort
{
	// Sorts the specified List using the Merge Sort Method
	public static <T extends Comparable<? super T>> List<T> mergeSort(List<T> list)
	{
		return MergeSort.sort(list);
	}
}
