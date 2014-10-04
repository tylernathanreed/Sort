//* Description *//
// Title: Selection Sort
// Author: Tyler Reed
// Performs a traditional Exponential Search

//* Package *//
package Sort.SelectionSort;

//* Libraries *//
import java.util.List;
import Sort.AbstractSort;

//* Selection Sort Class *//
public final class SelectionSort<T extends Comparable<? super T>> extends AbstractSort<T>
{
	//* Class Variables *//
	// Statistic Variables
	public static transient volatile int COMPARISONS = 0;

	//* Sort Methods *//
	// Returns a Sorted Version of the specified List of Comparable Elements in the specified Order
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list, boolean ascending)
	{
		// Check for Trivial Case
		if(list.size() == 1)
			return list;

		// Find the Index for each Element
		for(int i = 0; i < list.size() - 1; i++)
		{
			// Find the i'th Minimum Element
			int min = i;

			for(int j = i + 1; j < list.size(); j++, COMPARISONS++)
				if(ascending ? (list.get(min).compareTo(list.get(j)) > 0) : (list.get(min).compareTo(list.get(j)) < 0))
					min = j;

			// Swap Index for Minimum Element
			if(i != min)
			{
				T temp = list.get(i);
				list.set(i, list.get(min));
				list.set(min, temp);
			}
		}

		return list;
	}
}