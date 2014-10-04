//* Description *//
// Title: Quick Sort
// Author: Tyler Reed
// Defines a Simple Divide and Conquer Comparison Sort

//* Package *//
package Sort.QuickSort;

//* Libraries *//
import java.util.ArrayList;
import java.util.List;
import Sort.AbstractSort;

//* Quick Sort Class *//
public final class QuickSort<T extends Comparable<? super T>> extends AbstractSort<T>
{
	//* Class Variables *//
	// Public Variables
	public static transient volatile int COMPARISONS = 0;
	public static transient volatile boolean THREADED = true;

	//* Sort Methods *//
	// Returns a Sorted Version of the specified List of Comparable Elements
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list)
	{
		return sort(list, true);
	}

	// Returns a Sorted Version of the specified List of Comparable Elements in the specified Order
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list, boolean ascending)
	{
		// Check for Trivial Case
		if(list.size() == 1)
			return list;

		return qsort(list, 0, list.size() - 1, ascending);
	}

	//* List Methods *//
	// Recursively Sorts the List using Partitioning
	private static <T extends Comparable<? super T>> List<T> qsort(List<T> list, int left, int right, boolean ascending)
	{
		// Validate the Bounds
		if(left >= right)
			return list;

		int pivot = partition(list, left, right);

		// Check for Multi-Threading
		List<Helper<T>> helpers = THREADED ? new ArrayList<Helper<T>>(2) : null;

		if(THREADED && Runtime.getRuntime().availableProcessors() > 2)
		{
			// Recursively Sort the Partitions on separate Threads
			helpers.add(new Helper<T>(list, left, pivot - 1, ascending));
			helpers.add(new Helper<T>(list, pivot + 1, right, ascending));
		}
		else
		{
			// Recursively Sort the Partitions
			qsort(list, left, pivot - 1, ascending);
			qsort(list, pivot + 1, right, ascending);
		}

		// Wait for Threads to Finish
		if(THREADED)
			for(Helper<T> helper : helpers)
				try
				{
					helper.thread.join();
					Helper.count--;
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}

		return list;
	}

	// Partitions the specified Range within the specified List
	private static <T extends Comparable<? super T>> int partition(List<T> list, int left, int right)
	{
		// Determine the Pivot Index and Value
		int pivotIndex = left + (right - left)/2;
		T pivotValue = list.get(pivotIndex);

		// Swap the Pivot Value with the Right Value
		T swap = list.get(pivotIndex);
		list.set(pivotIndex, list.get(right));
		list.set(right, swap);

		// Initialize the Store Index
		int storeIndex = left;

		// Partition the List
		for(int i = left; i < right; i++, COMPARISONS++)
			if(list.get(i).compareTo(pivotValue) < 0)
			{
				// Swap the Current Value with the Store Value
				swap = list.get(i);
				list.set(i, list.get(storeIndex));
				list.set(storeIndex, swap);

				// Increment the Store Index
				storeIndex++;
			}

		// Swap the Store Value with the Right Value
		swap = list.get(storeIndex);
		list.set(storeIndex, list.get(right));
		list.set(right, swap);

		return storeIndex;
	}

	//* Helper Thread Class *//
	public static class Helper<T extends Comparable<? super T>> implements Runnable
	{
		//* Class Variables *//
		// Static Variables
		protected static int count = 0;
		public static int TOTAL = 0;

		// List Variables
		protected List<T> list;

		// Helper Variables
		protected Thread thread;
		protected int left;
		protected int right;
		protected boolean ascending;

		//* Constructor *//
		// Creates the specified Instance of the Class
		public Helper(List<T> list, int left, int right, boolean ascending)
		{
			this.list = list;
			this.left = left;
			this.right = right;
			this.ascending = ascending;
	
			thread = new Thread(this);
			thread.start();

			count++;
			TOTAL++;
		}

		// Runs the Thread
		public void run()
		{
			//System.out.println("Running Thread: " + thread);
			QuickSort.qsort(list, left, right, ascending);
		}
	}
}
